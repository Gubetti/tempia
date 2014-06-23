package view;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;

import model.Motor;
import model.Regra;
import model.Sentenca;

@SuppressWarnings("serial")
public class TelaSentencas extends JDialog {
	private JTextField textField;
	private JTable tablePremissas;
	private JTable tableConclusoes;
	private Regra regraInserir;
	private Regra regraEditar;
	private Regra regraEditarBackup;
	private TelaSentencas telaSentencas;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaSentencas dialog = new TelaSentencas(null);
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
	public TelaSentencas(Regra regraEdit) {
		telaSentencas = this;
		if(regraEdit == null) {
			regraInserir = new Regra();
			this.setTitle("Inserir regra");
		} else {
			regraEditar = regraEdit;
			copiarRegraEdit();
			this.setTitle("Editar regra");
		}
		
		this.setBounds(100, 100, 600, 400);
		this.setResizable(false);
		this.getContentPane().setLayout(null);
		this.setLocationRelativeTo(null);
		
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
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salvar();
			}
		});
		btnSalvar.setBounds(xBotaoOk, yBotaoOkCancela, larguraBotao, alturaBotao);
		panelOkCancela.add(btnSalvar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelar();
			}
		});
		btnCancelar.setBounds(xBotaoCancela, yBotaoOkCancela, larguraBotao, alturaBotao);
		panelOkCancela.add(btnCancelar);
		
		JPanel panelPremissas = new JPanel();
		panelPremissas.setBounds(0, alturaPanelNome, larguraPremissas, alturaPremissas);
		this.getContentPane().add(panelPremissas);
		panelPremissas.setLayout(null);
		
		List<Sentenca> premissas;
		List<Sentenca> conclusoes;
		if(regraEdit == null) {
			premissas = regraInserir.getPremissas();
			conclusoes = regraInserir.getConclusoes();
		} else {
			premissas = regraEdit.getPremissas();
			conclusoes = regraEdit.getConclusoes();
		}
		tablePremissas = new JTable(new SentencaTableModel(premissas));
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
		
		tableConclusoes = new JTable(new SentencaTableModel(conclusoes));
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
		
		JButton btnInserirPremissa = new JButton("Inserir");
		btnInserirPremissa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(regraInserir != null) {
					new TelaSentenca(regraInserir, true, null, telaSentencas).setVisible(true);
				} else {
					new TelaSentenca(regraEditar, true, null, telaSentencas).setVisible(true);
				}
			}
		});
		panel.add(btnInserirPremissa);
		
		JButton btnEditarPremissa = new JButton("Editar");
		btnEditarPremissa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editar(true);
			}
		});
		panel.add(btnEditarPremissa);
		
		JButton btnExcluirPremissa = new JButton("Excluir");
		btnExcluirPremissa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluir(tablePremissas);
			}
		});
		panel.add(btnExcluirPremissa);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Conclus\u00E3o", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(0, 142, 118, 116);
		panelBotoes.add(panel_3);
		panel_3.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(8, 16, 102, 92);
		panel_3.add(panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnInserirConclusao = new JButton("Inserir");
		btnInserirConclusao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(regraInserir != null) {
					new TelaSentenca(regraInserir, false, null, telaSentencas).setVisible(true);
				} else {
					new TelaSentenca(regraEditar, false, null, telaSentencas).setVisible(true);
				}
			}
		});
		panel_2.add(btnInserirConclusao);
		
		JButton btnEditarConclusao = new JButton("Editar");
		btnEditarConclusao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editar(false);
			}
		});
		panel_2.add(btnEditarConclusao);
		
		JButton btnExcluirConclusao = new JButton("Excluir");
		btnExcluirConclusao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluir(tableConclusoes);
			}
		});
		panel_2.add(btnExcluirConclusao);
	}
	
	public void atualizarTabelas() {
		SentencaTableModel modelo = (SentencaTableModel) tablePremissas.getModel();
		modelo.notificarInsercao();
		modelo = (SentencaTableModel) tableConclusoes.getModel();
		modelo.notificarInsercao();
	}
	
	private void editar(boolean premissa) {
		try {
			Regra regra = regraEditar;
			if(regraInserir != null) {
				regra = regraInserir;
			}
				if(premissa) {
					new TelaSentenca(regra, true, ((SentencaTableModel) tablePremissas.getModel()).getSentenca(tablePremissas.getSelectedRow()), telaSentencas).setVisible(true);
				} else {
					new TelaSentenca(regra, false, ((SentencaTableModel) tableConclusoes.getModel()).getSentenca(tableConclusoes.getSelectedRow()), telaSentencas).setVisible(true);
				}
		} catch(Exception exception) {
			JOptionPane.showMessageDialog(null, "Selecione a sentença que você deseja editar.", "Aviso", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	private void excluir(JTable tabela) {
		try {
			((SentencaTableModel) tabela.getModel()).removeRow(tabela.getSelectedRow());
		} catch(Exception exception) {
			JOptionPane.showMessageDialog(null, "Selecione a sentença que você deseja remover.", "Aviso", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	private void salvar() {
		if(regraInserir != null) {
			if(regraInserir.getPremissas().isEmpty() || regraInserir.getConclusoes().isEmpty()) {
				JOptionPane.showMessageDialog(null, "É necessário informar pelo menos uma premissa e uma conclusão.", "Aviso", JOptionPane.WARNING_MESSAGE);
				return;
			}
			for(Sentenca premissa : regraInserir.getPremissas()) {
				for(Sentenca conclusao : regraInserir.getConclusoes()) {
					if(premissa.getVariavel().equals(conclusao.getVariavel())) {
						JOptionPane.showMessageDialog(null, "Uma variável não pode estar simultaneamente em uma premissa e em uma conclusão.", "Aviso", JOptionPane.WARNING_MESSAGE);
						return;
					}
				}
			}
			regraInserir.setDescricao(textField.getText());
			Motor.getInstancia().getRegras().add(regraInserir);
		}
		
		if(regraEditar != null) {
			if(regraEditar.getPremissas().isEmpty() || regraEditar.getConclusoes().isEmpty()) {
				JOptionPane.showMessageDialog(null, "É necessário informar pelo menos uma premissa e uma conclusão.", "Aviso", JOptionPane.WARNING_MESSAGE);
				return;
			}
			for(Sentenca premissa : regraEditar.getPremissas()) {
				for(Sentenca conclusao : regraEditar.getConclusoes()) {
					if(premissa.getVariavel().equals(conclusao.getVariavel())) {
						JOptionPane.showMessageDialog(null, "Uma variável não pode estar simultaneamente em uma premissa e em uma conclusão.", "Aviso", JOptionPane.WARNING_MESSAGE);
						return;
					}
				}
			}
			regraEditar.setDescricao(textField.getText());
		}
		dispose();
	}
	
	private void cancelar() {
		if(regraEditarBackup != null) {
			for(int i = 0; i < Motor.getInstancia().getRegras().size(); i++) {
				if(Motor.getInstancia().getRegras().get(i).equals(regraEditar)) {
					Motor.getInstancia().getRegras().set(i, regraEditarBackup);
					break;
				}
			}
		}
		dispose();
	}
	
	private void copiarRegraEdit() {
		regraEditarBackup = new Regra();
		regraEditarBackup.setDescricao(regraEditar.getDescricao());
		for(Sentenca premissa : regraEditar.getPremissas()) {
			Sentenca sentenca = new Sentenca(premissa.getVariavel());
			sentenca.setOperadorSelecionado(premissa.getOperadorSelecionado());
			sentenca.setValorSelecao(premissa.getValorSelecao());
			regraEditarBackup.getPremissas().add(sentenca);
		}
		for(Sentenca conclusao : regraEditar.getConclusoes()) {
			Sentenca sentenca = new Sentenca(conclusao.getVariavel());
			sentenca.setOperadorSelecionado(conclusao.getOperadorSelecionado());
			sentenca.setValorSelecao(conclusao.getValorSelecao());
			regraEditarBackup.getConclusoes().add(sentenca);
		}
	}
}

@SuppressWarnings("serial")
class SentencaTableModel extends AbstractTableModel{
	private List<Sentenca> sentencas = new ArrayList<Sentenca>();
	private String[] colunas = new String[] { "Variável", "Operador", "Valor" };
	
	public SentencaTableModel(List<Sentenca> sentencas) {
		this.sentencas = sentencas;
	}

	@Override
	public int getRowCount() {
		return sentencas.size();
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
		switch(columnIndex) {
		case 0:
			return sentencas.get(row).getVariavel().getNome();
		case 1:
			return sentencas.get(row).getOperadorSelecionado();
		default:
			return sentencas.get(row).getValorSelecao();
		}
	}
	 
	public Class<?> getColumnClass(int columnIndex) {  
		return String.class;
	}
	
	  @Override
	    public boolean isCellEditable(int rowIndex, int columnIndex) {
	        return false;
	    }

	public void removeRow(int row) {
		sentencas.remove(row);
		fireTableRowsDeleted(row, row);
	}

	public void notificarInsercao() {
		fireTableRowsInserted(sentencas.size() - 1, sentencas.size() - 1);
	}
	
	public Sentenca getSentenca(int row) {
		return sentencas.get(row);
	}
}
