package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import java.awt.BorderLayout;

import javax.swing.JButton;

import java.awt.FlowLayout;

import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

public class TelaPrincipal {

	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal window = new TelaPrincipal();
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
	public TelaPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("serial")
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 91, 266);
		frame.getContentPane().add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnVariveis = new JButton("Vari\u00E1veis");
		panel.add(btnVariveis);
		
		JButton btnExecutar = new JButton("Executar");
		panel.add(btnExecutar);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(90, 0, 261, 266);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			     null,
			     new String[]{"Regra"}){
			     
			         boolean[] canEdit = new boolean []{
			             false
			         };
			        
			         @Override
			         public boolean isCellEditable(int rowIndex, int columnIndex) {
			             return canEdit [columnIndex];
			         }
			});
//		adicionarLinha(table, "");
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
		panel_3.setBounds(0, 55, 92, 125);
		panel_2.add(panel_3);
		
		JButton btnInserir = new JButton("Inserir");
		panel_3.add(btnInserir);
		
		JButton btnEditar = new JButton("Editar");
		panel_3.add(btnEditar);
		
		JButton btnExcluir = new JButton("Excluir");
		panel_3.add(btnExcluir);
	}
	
	private void adicionarLinha(JTable table, String nomeRegra) {
		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		modelo.addRow(new String[] { nomeRegra });
	}	
}
