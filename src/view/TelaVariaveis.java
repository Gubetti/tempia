package view;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;

public class TelaVariaveis extends JDialog {
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaVariaveis dialog = new TelaVariaveis();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
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
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.setModal(true);
		this.setBounds(100, 100, 341, 300);
		this.setResizable(false);
		
		
		this.setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		
		String[] colunas = new String[]{"Variáveis"};
		String[][] dados = new String[][]{{""}};
		DefaultTableModel modelo = new DefaultTableModel(dados, colunas);
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
		panel_1.add(btnInserir);
		
		JButton btnEditar = new JButton("Editar");
		panel_1.add(btnEditar);
		
		JButton btnExcluir = new JButton("Excluir");
		panel_1.add(btnExcluir);
	}

}
