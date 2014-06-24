package view;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

public class TelaResultado extends JDialog {

	private JTable tableValores;
	private JTextField txtTes;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaResultado dialog = new TelaResultado();
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
	public TelaResultado() {
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setModal(true);
		this.setBounds(100, 100, 450, 300);
		this.setResizable(false);
		
		this.setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, this.getWidth() - 8, this.getHeight() - 34);
		
		JPanel panelResultado = new JPanel();
		panelResultado.setBounds(0, 0, tabbedPane.getWidth(), tabbedPane.getHeight());
		tabbedPane.addTab("Resultado", panelResultado);
		panelResultado.setLayout(null);
		
		JLabel lblConsulta = new JLabel("Consulta:");
		lblConsulta.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblConsulta.setBounds(165, 39, 46, 14);
		lblConsulta.setSize(lblConsulta.getPreferredSize());
		panelResultado.add(lblConsulta);
		
		txtTes = new JTextField();
		txtTes.setEnabled(false);
		txtTes.setEditable(false);
		txtTes.setBounds(83, 75, 260, 25);
		panelResultado.add(txtTes);
		txtTes.setColumns(10);

		JPanel panelArvore = new JPanel();
		panelArvore.setBounds(0, 0, tabbedPane.getWidth(), tabbedPane.getHeight());
		tabbedPane.addTab("Árvore de Pesquisa", panelArvore);
		panelArvore.setLayout(null);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(0, 0, 4, 22);
		textArea.setSize(textArea.getPreferredSize());
		JScrollPane scrollPaneArvore = new JScrollPane(textArea);
		scrollPaneArvore.setBounds(0, 0, tabbedPane.getWidth(), tabbedPane.getHeight() - 30);
		panelArvore.add(scrollPaneArvore);		
		
		JPanel panelValores = new JPanel();
		panelValores.setBounds(0, 0, tabbedPane.getWidth(), tabbedPane.getHeight());
		tabbedPane.addTab("Todos os Valores", panelValores);
		panelValores.setLayout(null);
		
		@SuppressWarnings("serial")
		DefaultTableModel modelo = new DefaultTableModel(
			     null,
			     new String[]{"Variável", "Valores"}){
			         boolean[] canEdit = new boolean []{
			             false, false,
			         };
			        
			         @Override
			         public boolean isCellEditable(int rowIndex, int columnIndex) {
			             return canEdit [columnIndex];
			         }
			};
		tableValores = new JTable(modelo);
		tableValores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableValores.setBounds(10, 11, 1, 1);
		tableValores.setSize(tableValores.getPreferredSize());
		
		JScrollPane scrollPane = new JScrollPane(tableValores);
		scrollPane.setBounds(0, 0, panelValores.getWidth(), panelValores.getHeight() - 30);
		panelValores.add(scrollPane);
		
		getContentPane().add(tabbedPane);
	}
}
