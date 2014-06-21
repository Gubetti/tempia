package view;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

public class TelaSentencas extends JDialog {

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
		
		JPanel panelNome = new JPanel();
		panelNome.setBounds(0, 0, larguraPanelNome, alturaPanelNome);
		this.getContentPane().add(panelNome);

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
		
		JPanel panelConclusoes = new JPanel();
		panelConclusoes.setBounds(larguraPremissas, alturaPanelNome, larguraPremissas, alturaPremissas);
		this.getContentPane().add(panelConclusoes);
		
		JPanel panelBotoes = new JPanel();
		panelBotoes.setBounds(larguraPremissas * 2, alturaPanelNome, larguraPanelBotoes, alturaPremissas);
		this.getContentPane().add(panelBotoes);
	}

}
