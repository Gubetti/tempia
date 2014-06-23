package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;

import model.Motor;
import model.Variavel;

@SuppressWarnings("serial")
public class TelaVariaveis extends JDialog {
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
					TelaVariaveis dialog = new TelaVariaveis();
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public TelaVariaveis() {
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setModal(true);
		this.setBounds(100, 100, 341, 300);
		this.setResizable(false);
		this.setTitle("Variáveis cadastradas");
		
		this.setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		DefaultTableModel modelo = new DefaultTableModel(
			     null,
			     new String[]{"Nome", "Pergunta"}){
			         boolean[] canEdit = new boolean []{
			             false, false,
			         };
			        
			         @Override
			         public boolean isCellEditable(int rowIndex, int columnIndex) {
			             return canEdit [columnIndex];
			         }
			};
		table = new JTable(modelo);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setBounds(10, 11, 1, 1);
		table.setSize(table.getPreferredSize());
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 0, 240, 268);
		getContentPane().add(scrollPane);
		
		JPanel panel = new JPanel();
		panel.setBounds(238, 0, 98, 268);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 63, 78, 113);
		panel.add(panel_1);
		
		JButton btnInserir = new JButton("Inserir");
		btnInserir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaVariavel().setVisible(true);
				adicionarVariaveis();
			}
		});
		panel_1.add(btnInserir);
		
		btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					DefaultTableModel modelo = (DefaultTableModel) table.getModel();
					for(Variavel variavel : Motor.getInstancia().getVariaveis()) {
						if(variavel.getNome().equalsIgnoreCase((String) modelo.getValueAt(table.getSelectedRow(), 0))) {
							new TelaVariavel(variavel).setVisible(true);
							adicionarVariaveis();
							break;
						}
					}
				} catch (Exception exception) {
					JOptionPane.showMessageDialog(null, "Selecione uma variável para prosseguir.", "Aviso", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		panel_1.add(btnEditar);
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					DefaultTableModel modelo = (DefaultTableModel) table.getModel();
					for(Variavel variavel : Motor.getInstancia().getVariaveis()) {
						if(variavel.getNome().equalsIgnoreCase((String) modelo.getValueAt(table.getSelectedRow(), 0))) {
							String texto = Motor.getInstancia().verificarVariavelUsada(variavel);
							if(texto.equalsIgnoreCase("")) {
								Motor.getInstancia().excluirVariavelUsada(variavel);
							} else {
								int aviso = JOptionPane.showConfirmDialog(null, "A variável " + variavel.getNome() + " está sendo usada na(s) seguinte(s) regra(s):\n" 
										+ texto + "\nSe prosseguir, a(s) regra(s) acima serão excluídas.\nDeseja continuar?", "Aviso", JOptionPane.ERROR_MESSAGE);
								if(aviso == 0) {
									Motor.getInstancia().excluirVariavelUsada(variavel);
								}
							}
							adicionarVariaveis();
							break;
						}
					}
				} catch (Exception exception) {
					JOptionPane.showMessageDialog(null, "Selecione uma variável para prosseguir.", "Aviso", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		panel_1.add(btnExcluir);
		
		adicionarVariaveis();
	}
	
	private void adicionarVariaveis() {
		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		for (int i = modelo.getRowCount() - 1; i >= 0; i--) {
			modelo.removeRow(i);
		}
		
		for(Variavel variavel : Motor.getInstancia().getVariaveis()) {
			modelo.addRow(new String[] { variavel.getNome(), variavel.getPergunta() });
		}
		
		if(modelo.getRowCount() > 0) {
			btnEditar.setEnabled(true);
			btnExcluir.setEnabled(true);
		} else {
			btnEditar.setEnabled(false);
			btnExcluir.setEnabled(false);		
		}
	}

}
