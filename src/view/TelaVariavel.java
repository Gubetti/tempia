package view;

import java.awt.EventQueue;

import javax.swing.JDialog;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.ListSelectionModel;

import java.awt.FlowLayout;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JCheckBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaVariavel extends JDialog {
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaVariavel dialog = new TelaVariavel();
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
	public TelaVariavel() {
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setModal(true);
		this.setBounds(100, 100, 460, 417);
		this.setResizable(false);
		
		
		this.setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNome.setBounds(27, 25, 44, 14);
		lblNome.setSize(lblNome.getPreferredSize());
		getContentPane().add(lblNome);
		
		textField = new JTextField();
		textField.setBounds(64, 22, 240, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		ButtonGroup buttonGroup = new ButtonGroup();
		JPanel panel = new JPanel();
		panel.setLocation(314, 11);
		panel.setSize(130, 103);
		JRadioButton uni = new JRadioButton("Univalorado");
		uni.setBounds(16, 17, 83, 23);
		uni.setSize(uni.getPreferredSize());
		JRadioButton multi = new JRadioButton("Multivalorado");
		multi.setBounds(16, 43, 89, 23);
		multi.setSize(multi.getPreferredSize());
		final JRadioButton numer = new JRadioButton("Númerico");
		numer.setBounds(16, 68, 69, 23);
		numer.setSize(numer.getPreferredSize());
		buttonGroup.add(uni);
		buttonGroup.add(multi);
		buttonGroup.add(numer);
		panel.setLayout(null);
		panel.add(uni);
		panel.add(multi);
		panel.add(numer);
		getContentPane().add(panel);
		panel.setBorder(BorderFactory.createTitledBorder("Tipo"));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 334, 454, 40);
		getContentPane().add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnOk = new JButton("Salvar");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (numer.isSelected()) {
					DefaultTableModel modelo = (DefaultTableModel) table.getModel();
					for (int i = 0; i < modelo.getRowCount(); i++) {
						try {
							String temp = String.valueOf(modelo.getValueAt(i, 0)).trim();
							Integer.parseInt(temp);
						} catch (NumberFormatException e) {
							JOptionPane.showMessageDialog(null, "Digite apenas números");
							break;
						}
					}
				}
			}
		});
		panel_1.add(btnOk);
		
		JLabel lblPergunta = new JLabel("Pergunta");
		lblPergunta.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPergunta.setBounds(10, 56, 46, 14);
		lblPergunta.setSize(lblPergunta.getPreferredSize());
		getContentPane().add(lblPergunta);
		
		textField_1 = new JTextField();
		textField_1.setBounds(64, 53, 240, 20);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		String[] colunas = new String[]{"Valores"};
		DefaultTableModel modelo = new DefaultTableModel(null, colunas);
		table = new JTable(modelo);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setBounds(10, 11, 1, 1);
		table.setSize(table.getPreferredSize());
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 121, 293, 202);
		getContentPane().add(scrollPane);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(314, 162, 130, 161);
		getContentPane().add(panel_2);
		
		JButton btnInserir = new JButton("Inserir");
		btnInserir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				adicionarLinha("");
			}
		});
		btnInserir.setSize(100, 25);
		panel_2.add(btnInserir);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removerLinha(table.getSelectedRow());
			}
		});
		btnExcluir.setSize(100, 25);
		panel_2.add(btnExcluir);
		
		JCheckBox chckbxObjetvo = new JCheckBox("Objetvo");
		chckbxObjetvo.setBounds(119, 80, 97, 23);
		getContentPane().add(chckbxObjetvo);
		
	}
	
	private void adicionarLinha(String valor) {
		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		modelo.addRow(new String[] { valor });
	}
	
	private void removerLinha(int linha) {
		try {
			DefaultTableModel modelo = (DefaultTableModel) table.getModel();
			modelo.removeRow(linha);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
