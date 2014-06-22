package view;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;

@SuppressWarnings("serial")
public class TelaSentencas extends JDialog {
	private JTextField textField;
	private JTable tablePremissas;
	private JTable tableConclusoes;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaSentencas dialog = new TelaSentencas();
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
	public TelaSentencas() {
		this.setBounds(100, 100, 600, 400);
		this.setResizable(false);
		this.getContentPane().setLayout(null);
		
		int larguraMaxima = this.getWidth() - 8;
		int alturaMaxima = this.getHeight() - 35;
		int larguraPanelNome = 350;
		int alturaPanelNome = 60;
		int larguraPanelOkCancela = larguraMaxima - larguraPanelNome;
		int larguraPanelBotoes = 120;
		int alturaPremissas = alturaMaxima - alturaPanelNome;
		int larguraPremissas = (larguraMaxima - larguraPanelBotoes) / 2;
		int alturaBotao = 23;
		int larguraBotao = 89;
		int yBotaoOkCancela = (alturaPanelNome / 2) - (alturaBotao / 2);
		int espacamentoBotoes = 15;
		int xBotaoOk = (larguraPanelOkCancela - (larguraBotao * 2) - espacamentoBotoes) / 2;
		int xBotaoCancela = xBotaoOk + larguraBotao + espacamentoBotoes;
		int alturaLblDescricao = 14;
		int larguraLblDescricao = 46;
		int alturaTfDescricao = 20;
		int larguraTfDescricao = 250;
		int yLblDescricao = (alturaPanelNome - alturaLblDescricao) / 2;
		int xLblDescricao = (larguraPanelNome - larguraLblDescricao - larguraTfDescricao - espacamentoBotoes) / 2;
		int yTfDescricao = (alturaPanelNome - alturaTfDescricao) / 2;
		int xTfDescricao = xLblDescricao + larguraLblDescricao + espacamentoBotoes;
		
		JPanel panelNome = new JPanel();
		panelNome.setBounds(0, 0, larguraPanelNome, alturaPanelNome);
		this.getContentPane().add(panelNome);
		panelNome.setLayout(null);
		
		JLabel lblDescricao = new JLabel("Descrição");
		lblDescricao.setBounds(xLblDescricao, yLblDescricao, larguraLblDescricao, alturaLblDescricao);
		lblDescricao.setSize(lblDescricao.getPreferredSize());
		panelNome.add(lblDescricao);
		
		textField = new JTextField();
		textField.setBounds(xTfDescricao, yTfDescricao, larguraTfDescricao, alturaTfDescricao);
		panelNome.add(textField);
		textField.setColumns(10);

		JPanel panelOkCancela = new JPanel();
		panelOkCancela.setBounds(larguraPanelNome, 0, larguraPanelOkCancela, alturaPanelNome);
		this.getContentPane().add(panelOkCancela);
		panelOkCancela.setLayout(null);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(xBotaoOk, yBotaoOkCancela, larguraBotao, alturaBotao);
		panelOkCancela.add(btnSalvar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(xBotaoCancela, yBotaoOkCancela, larguraBotao, alturaBotao);
		panelOkCancela.add(btnCancelar);
		
		JPanel panelPremissas = new JPanel();
		panelPremissas.setBounds(0, alturaPanelNome, larguraPremissas, alturaPremissas);
		this.getContentPane().add(panelPremissas);
		panelPremissas.setLayout(null);
		
		tablePremissas = new JTable();
		tablePremissas.setModel(new DefaultTableModel(
			     null,
			     new String[]{"Variável", "Operador", "Valor"}){
			     
			         boolean[] canEdit = new boolean []{
			             false, false, false
			         };
			        
			         @Override
			         public boolean isCellEditable(int rowIndex, int columnIndex) {
			             return canEdit [columnIndex];
			         }
			});
//		adicionarLinha(tablePremissas, "", "", "");
		tablePremissas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablePremissas.setBounds(10, 11, 1, 1);
		tablePremissas.setSize(tablePremissas.getPreferredSize());
		JScrollPane scrollPanePremissas = new JScrollPane(tablePremissas);
		scrollPanePremissas.setBounds(10, 10, larguraPremissas - 20, alturaPremissas - 20);
		panelPremissas.add(scrollPanePremissas);
		
		JPanel panelConclusoes = new JPanel();
		panelConclusoes.setBounds(larguraPremissas, alturaPanelNome, larguraPremissas, alturaPremissas);
		this.getContentPane().add(panelConclusoes);
		panelConclusoes.setLayout(null);
		
		tableConclusoes = new JTable();
		tableConclusoes.setModel(new DefaultTableModel(
			     null,
			     new String[]{"Variável", "Operador", "Valor"}){
			     
			         boolean[] canEdit = new boolean []{
			             false, false, false
			         };
			        
			         @Override
			         public boolean isCellEditable(int rowIndex, int columnIndex) {
			             return canEdit [columnIndex];
			         }
			});
//		adicionarLinha(tableConclusoes, "", "", "");
		tableConclusoes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableConclusoes.setBounds(10, 11, 1, 1);
		tableConclusoes.setSize(tableConclusoes.getPreferredSize());
		JScrollPane scrollPaneConclusoes = new JScrollPane(tableConclusoes);
		scrollPaneConclusoes.setBounds(10, 10, larguraPremissas - 20, alturaPremissas - 20);
		panelConclusoes.add(scrollPaneConclusoes);
		
		JPanel panelBotoes = new JPanel();
		panelBotoes.setBounds(larguraPremissas * 2, alturaPanelNome, larguraPanelBotoes, alturaPremissas);
		this.getContentPane().add(panelBotoes);
		panelBotoes.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Premissa", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(2, 24, 116, 116);
		panelBotoes.add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(8, 16, 100, 92);
		panel_1.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnInserir = new JButton("Inserir");
		panel.add(btnInserir);
		
		JButton btnEditar = new JButton("Editar");
		panel.add(btnEditar);
		
		JButton btnExcluir = new JButton("Excluir");
		panel.add(btnExcluir);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Conclus\u00E3o", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(0, 142, 118, 116);
		panelBotoes.add(panel_3);
		panel_3.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(8, 16, 102, 92);
		panel_3.add(panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnInserir_1 = new JButton("Inserir");
		panel_2.add(btnInserir_1);
		
		JButton btnEditar_1 = new JButton("Editar");
		panel_2.add(btnEditar_1);
		
		JButton btnExcluir_1 = new JButton("Excluir");
		panel_2.add(btnExcluir_1);
	}
	
	private void adicionarLinha(JTable table, String variavel, String operador, String valor) {
		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		modelo.addRow(new String[] { variavel, operador, valor });
	}
}
