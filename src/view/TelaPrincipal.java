package view;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;

import model.Motor;
import model.Regra;

public class TelaPrincipal {

	private JFrame frmTtulo;
	private JTable table;
	private JButton btnEditar;
	private JButton btnExcluir;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal window = new TelaPrincipal();
					window.frmTtulo.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTtulo = new JFrame();
		frmTtulo.setResizable(false);
		frmTtulo.setTitle("Motor de inferência");
		frmTtulo.setBounds(100, 100, 450, 300);
		frmTtulo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTtulo.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 105, 266);
		frmTtulo.getContentPane().add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnAbrirArquivo = new JButton("Abrir Base");
		panel.add(btnAbrirArquivo);
		
		JButton btnSalvarArquivo = new JButton("Salvar Base");
		panel.add(btnSalvarArquivo);
		
		JButton btnVariveis = new JButton("Vari\u00E1veis");
		btnVariveis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new TelaVariaveis().setVisible(true);
			}
		});
		panel.add(btnVariveis);
		
		JButton btnExecutar = new JButton("Executar");
		btnExecutar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(Motor.getInstancia().getRegras().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Insira algumas regras!", "Aviso", JOptionPane.WARNING_MESSAGE);
					return;
				}
				Motor.getInstancia().executar();
			}
		});
		panel.add(btnExecutar);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(104, 0, 247, 266);
		frmTtulo.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		table = new JTable(new RegraTableModel(Motor.getInstancia().getRegras()));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setBounds(10, 11, 1, 1);
		table.setSize(table.getPreferredSize());
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 10, panel_1.getWidth() - 20, panel_1.getHeight() - 20);
		panel_1.add(scrollPane);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(350, 0, 92, 266);
		frmTtulo.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(0, 55, 92, 125);
		panel_2.add(panel_3);
		
		JButton btnInserir = new JButton("Inserir");
		btnInserir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new TelaSentencas(null).setVisible(true);
				atualizarTabelaRegras();
			}
		});
		panel_3.add(btnInserir);
		
		btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				editar();
			}
		});
		panel_3.add(btnEditar);
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				excluir();
			}
		});
		panel_3.add(btnExcluir);
		estadoBotoes();
	}	
	
	private void atualizarTabelaRegras() {
		((RegraTableModel) table.getModel()).notificarInsercao();
		estadoBotoes();
	}
	
	private void estadoBotoes() {
		if(!Motor.getInstancia().getRegras().isEmpty()) {
			btnEditar.setEnabled(true);
			btnExcluir.setEnabled(true);
		} else {
			btnEditar.setEnabled(false);
			btnExcluir.setEnabled(false);		
		}
	}
	
	private void editar() {
		try {
			new TelaSentencas(((RegraTableModel) table.getModel()).getRegra(table.getSelectedRow())).setVisible(true);
			atualizarTabelaRegras();
		} catch(Exception exception) {
			JOptionPane.showMessageDialog(null, "Selecione a regra que você deseja editar.", "Aviso", JOptionPane.WARNING_MESSAGE);
			exception.printStackTrace();
		}		
	}
	
	private void excluir() {
		try {
			((RegraTableModel) table.getModel()).removeRow(table.getSelectedRow());
			estadoBotoes();
		} catch(Exception exception) {
			JOptionPane.showMessageDialog(null, "Selecione a regra que você deseja remover.", "Aviso", JOptionPane.WARNING_MESSAGE);
		}
	}
}

@SuppressWarnings("serial")
class RegraTableModel extends AbstractTableModel{
	private List<Regra> regras = new ArrayList<Regra>();
	private String[] colunas = new String[] { "Regra" };
	
	public RegraTableModel(List<Regra> regras) {
		this.regras = regras;
	}

	@Override
	public int getRowCount() {
		return regras.size();
	}
	
	@Override
	public String getColumnName(int columnIndex) {
	    return colunas[columnIndex];
	};
	
	@Override
	public int getColumnCount() {
		return colunas.length;
	}

	@Override
	public Object getValueAt(int row, int columnIndex) {
		String descricao = "Regra " + (Motor.getInstancia().getRegras().indexOf(regras.get(row)) + 1);				
		
		if(!regras.get(row).getDescricao().trim().equalsIgnoreCase("")) {
			descricao += ": " + regras.get(row).getDescricao().trim();
		}
		return descricao;
	}
	 
	public Class<?> getColumnClass(int columnIndex) {  
		return String.class;
	}
	
	  @Override
	    public boolean isCellEditable(int rowIndex, int columnIndex) {
	        return false;
	    }

	public void removeRow(int row) {
		regras.remove(row);
		fireTableRowsDeleted(row, row);
	}

	public void notificarInsercao() {
		fireTableRowsInserted(regras.size() - 1, regras.size() - 1);
	}
	
	public Regra getRegra(int row) {
		return regras.get(row);
	}
}
