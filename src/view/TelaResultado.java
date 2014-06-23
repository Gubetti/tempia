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

public class TelaResultado extends JDialog {

	private JTable tableValores;
	
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
		
		JTextArea textAreaResultado = new JTextArea();
		textAreaResultado.setBounds(0, 0, panelResultado.getWidth(), panelResultado.getHeight() - 30);
		panelResultado.add(textAreaResultado);

		JPanel panelArvore = new JPanel();
		panelArvore.setBounds(0, 0, tabbedPane.getWidth(), tabbedPane.getHeight());
		tabbedPane.addTab("Árvore de Pesquisa", panelArvore);
		panelArvore.setLayout(null);
		
		JTextArea textAreaArvore = new JTextArea();
		textAreaArvore.setBounds(0, 0, panelArvore.getWidth(), panelArvore.getHeight() - 30);
		panelArvore.add(textAreaArvore);
		
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
