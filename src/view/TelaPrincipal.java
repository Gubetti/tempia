package view;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.AbstractTableModel;

import persistence.Persistir;
import model.Motor;
import model.Regra;

public class TelaPrincipal {

	private JFrame frame;
	private JTable table;
	private JButton btnEditar;
	private JButton btnExcluir;
	private JButton btnPraCima;
	private JButton btnPraBaixo;
	private JFileChooser jFileChooser;
	private static TelaPrincipal instancia;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal window = TelaPrincipal.getInstancia();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	private TelaPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("Motor de inferência");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		jFileChooser = new JFileChooser();
		jFileChooser.setFileFilter(new FileNameExtensionFilter("Arquivos MOTINF", "motinf"));  
		jFileChooser.setAcceptAllFileFilterUsed(false);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 105, 266);
		frame.getContentPane().add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnAbrirArquivo = new JButton("Abrir Base");
		btnAbrirArquivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				abrirArquivo();
			}
		});
		panel.add(btnAbrirArquivo);
		
		JButton btnSalvarArquivo = new JButton("Salvar Base");
		btnSalvarArquivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				salvarArquivo();
			}
		});
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
		frame.getContentPane().add(panel_1);
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
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(0, 33, 92, 125);
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
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(23, 169, 45, 71);
		panel_2.add(panel_4);
		
		btnPraCima = new JButton("\u02C4");
		btnPraCima.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (table.getSelectedRow() > -1) {
					if (((RegraTableModel) table.getModel()).trocarLugarRegra(0, table.getSelectedRow())) {
						table.setRowSelectionInterval(table.getSelectedRow() - 1, table.getSelectedRow() - 1);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Selecione a regra que você deseja mover.", "Aviso", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		panel_4.add(btnPraCima);
		
		btnPraBaixo = new JButton("\u02C5");
		btnPraBaixo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() > -1) {
					if (((RegraTableModel) table.getModel()).trocarLugarRegra(2, table.getSelectedRow())) {
						table.setRowSelectionInterval(table.getSelectedRow() + 1, table.getSelectedRow() + 1);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Selecione a regra que você deseja mover.", "Aviso", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		panel_4.add(btnPraBaixo);
		estadoBotoes();
	}	
	
	public void atualizarTabelaRegras() {
		((RegraTableModel) table.getModel()).notificarInsercao();
		estadoBotoes();
	}
	
	private void estadoBotoes() {
		if(!Motor.getInstancia().getRegras().isEmpty()) {
			btnEditar.setEnabled(true);
			btnExcluir.setEnabled(true);
			if(Motor.getInstancia().getRegras().size() > 1) {
				btnPraCima.setEnabled(true);
				btnPraBaixo.setEnabled(true);
			} else {
				btnPraCima.setEnabled(false);
				btnPraBaixo.setEnabled(false);
			}
		} else {
			btnEditar.setEnabled(false);
			btnExcluir.setEnabled(false);
			btnPraCima.setEnabled(false);
			btnPraBaixo.setEnabled(false);
		}
	}
	
	private void editar() {
		try {
			new TelaSentencas(((RegraTableModel) table.getModel()).getRegra(table.getSelectedRow())).setVisible(true);
			atualizarTabelaRegras();
		} catch(Exception exception) {
			JOptionPane.showMessageDialog(null, "Selecione a regra que você deseja editar.", "Aviso", JOptionPane.WARNING_MESSAGE);
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

	private void salvarArquivo() {
		if (jFileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
	       	if (!jFileChooser.getSelectedFile().getAbsolutePath().endsWith(".motinf"))  {
	       		jFileChooser.setSelectedFile(new File(jFileChooser.getSelectedFile().getAbsolutePath() + ".motinf")); 
	       	}
			Persistir.salvar(jFileChooser.getSelectedFile());
		}
	}
	
	private void abrirArquivo() {
		if (jFileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			if(jFileChooser.getSelectedFile().exists()) {
				if(Persistir.abrir(jFileChooser.getSelectedFile())) {
					((RegraTableModel) table.getModel()).setRegras(Motor.getInstancia().getRegras());
					atualizarTabelaRegras();
				} else {
					jFileChooser.setSelectedFile(null);
					JOptionPane.showMessageDialog(null, "Arquivo inválido!", "Aviso", JOptionPane.WARNING_MESSAGE);
				}
			} else {
				jFileChooser.setSelectedFile(null);
				JOptionPane.showMessageDialog(null, "Arquivo inexistente!", "Aviso", JOptionPane.WARNING_MESSAGE);
			}
		}
	}
	
	public static TelaPrincipal getInstancia() {
		if(instancia == null) {
			instancia = new TelaPrincipal();
		}
		return instancia;
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
	
	// 0 = pra cima, 1 = pra baixo, Verdadeiro se conseguiu trocar
	public boolean trocarLugarRegra(int sentido, int linha) {
		if (sentido == 0) {
			try {
				Regra seraMenor = regras.get(linha);
				Regra seraMaior = regras.get(linha - 1);
				regras.set(linha, seraMaior);
				regras.set(linha - 1, seraMenor);
				fireTableRowsUpdated(linha - 1, linha);
				return true;
			} catch (IndexOutOfBoundsException e) {
				JOptionPane.showMessageDialog(null, "Essa já é a primeira regra.", "Aviso", JOptionPane.WARNING_MESSAGE);
			}
		} else {
			try {
				Regra seraMenor = regras.get(linha + 1);
				Regra seraMaior = regras.get(linha);
				regras.set(linha, seraMenor);
				regras.set(linha + 1, seraMaior);
				fireTableRowsUpdated(linha, linha + 1);
				return true;
			} catch (IndexOutOfBoundsException e) {
				JOptionPane.showMessageDialog(null, "Essa já é a última regra.", "Aviso", JOptionPane.WARNING_MESSAGE);
			}
		}
		return false;
	}
	
	public Regra getRegra(int row) {
		return regras.get(row);
	}
	
	public void setRegras(List<Regra> regras) {
		this.regras = regras;
	}
}
