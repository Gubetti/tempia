package view;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import model.RespostaVariavel;
import model.TipoVariavel;
import model.Variavel;

@SuppressWarnings("serial")
public class TelaVariavel extends JDialog {
	private JTextField txtNome;
	private JTextField txtPergunta;
	private final List<JRadioButton> jRadioList = new ArrayList<JRadioButton>();
	private JCheckBox chckbxObjetvo;
	private JTable table;
	private JButton btnInserir;
	private JButton btnExcluir;
	private Variavel variavelEditar;

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
		init();
		this.setTitle("Inserir variável");
	}
	
	public TelaVariavel(Variavel variavel) {
		this.variavelEditar = variavel;
		init();
		this.setTitle("Editar variável " + variavel.getNome());
		txtNome.setText(variavel.getNome());
		txtPergunta.setText(variavel.getPergunta());
		switch(variavel.getTipo()) {
		case UNIVALORADO:
			jRadioList.get(0).setSelected(true);
			break;
		case MULTIVALORADO:
			jRadioList.get(1).setSelected(true);
			break;
		case NUMERICO:
			jRadioList.get(2).setSelected(true);
		}
		chckbxObjetvo.setSelected(variavel.isObjetivo());
		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		
		if(variavel.getTipo() != TipoVariavel.NUMERICO) {
			for(RespostaVariavel respostaVariavel : variavel.getRespostas()) {
				modelo.addRow(new String[] { respostaVariavel.getValor() });
			}
		} else {
			btnInserir.setEnabled(false);
			btnExcluir.setEnabled(false);
			modelo.addRow(new String[] { variavel.getRespostas().get(0).getValor() });
			modelo.addRow(new String[] { variavel.getRespostas().get(variavel.getRespostas().size() - 1).getValor() });
		}
	}
	
	private void init() {
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
		
		txtNome = new JTextField();
		txtNome.setBounds(64, 22, 240, 20);
		getContentPane().add(txtNome);
		txtNome.setColumns(10);
		
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
		JRadioButton numer = new JRadioButton("Númerico");
		numer.setBounds(16, 68, 69, 23);
		numer.setSize(numer.getPreferredSize());
		numer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				acaoNumerico();
			}
		});
		buttonGroup.add(uni);
		buttonGroup.add(multi);
		buttonGroup.add(numer);
		jRadioList.add(uni);
		jRadioList.add(multi);
		jRadioList.add(numer);
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
				salvar();
			}
		});
		panel_1.add(btnOk);
		
		JLabel lblPergunta = new JLabel("Pergunta");
		lblPergunta.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPergunta.setBounds(10, 56, 46, 14);
		lblPergunta.setSize(lblPergunta.getPreferredSize());
		getContentPane().add(lblPergunta);
		
		txtPergunta = new JTextField();
		txtPergunta.setBounds(64, 53, 240, 20);
		getContentPane().add(txtPergunta);
		txtPergunta.setColumns(10);
		
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
		
		btnInserir = new JButton("Inserir");
		btnInserir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				adicionarLinha("");
			}
		});
		btnInserir.setSize(100, 25);
		panel_2.add(btnInserir);
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removerLinha(table.getSelectedRow());
			}
		});
		btnExcluir.setSize(100, 25);
		panel_2.add(btnExcluir);
		
		chckbxObjetvo = new JCheckBox("Objetvo");
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
	
	private void acaoNumerico() {
		System.out.println("Clique");
	}
	
	private void salvar() {
		// Nova
		if(variavelEditar == null) {
			variavelEditar = new Variavel(txtNome.getText(), chckbxObjetvo.isSelected(), null);
		} else {
			
		}
	}
}