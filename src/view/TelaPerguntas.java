package view;

import java.awt.EventQueue;
import java.awt.Font;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.Motor;
import model.RespostaVariavel;
import model.Sentenca;
import model.TipoVariavel;
import model.Variavel;

@SuppressWarnings("serial")
public class TelaPerguntas extends JDialog {
	private JTextField textField;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sentenca sentenca = new Sentenca(new Variavel("Teste", false,
							TipoVariavel.NUMERICO));
					sentenca.getVariavel().getRespostas().add(new RespostaVariavel("10"));
					sentenca.getVariavel().getRespostas().add(new RespostaVariavel("50"));
					TelaPerguntas dialog = new TelaPerguntas(sentenca, "Regra 01", 1);
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
	public TelaPerguntas(final Sentenca premissa, String descricaoRegra, int ordem) {
		if(!descricaoRegra.equalsIgnoreCase("")) {
			setTitle(descricaoRegra);
		} else {
			setTitle("Regra de ordem " + ordem);
		}
		//this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		// alterar para a linha de baixo depois, evita que o usuario feche essa janela.
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.setResizable(false);
		getContentPane().setLayout(null);
		setModal(true);
		int larguraMaxima = 500 - 8;
		int alturaPanelPergunta = 50;
		int qtdRespostas = premissa.getVariavel().getRespostas().size();
		int espacamentoRespostas = 1;
		int alturaRespIndividual = 23;
		int borda = 40;
		int alturaPanelRespostas = espacamentoRespostas + (qtdRespostas * espacamentoRespostas) + (qtdRespostas * alturaRespIndividual) + borda - (borda / 3);
		int yRespostas = espacamentoRespostas + alturaRespIndividual;
		int alturaPanelBotoes = 60;
		int yPanelBotoes = alturaPanelPergunta + alturaPanelRespostas;
		int alturaBotao = 23;
		int larguraBotao = 89;
		int xBotaoOk = (larguraMaxima - larguraBotao) / 2;
		int yBotaoOk = (alturaPanelBotoes - alturaBotao) / 2;
		int espacamentoValor = 15;
		int alturaLblValor = 14;
		int larguraLblValor = 33;
		int alturaTfValor = 20;
		int larguraTfValor = 86;
		int larguraSlider = 200;
		int xLblValor = (larguraMaxima - (larguraLblValor + larguraTfValor) - espacamentoValor) / 2;
		int xTfValor = xLblValor + espacamentoValor + larguraLblValor;
		int alturaSlider = 25;
		int xSlider = (larguraMaxima - larguraSlider) / 2;
		int yLblValor = (alturaPanelRespostas - (alturaSlider + alturaLblValor) - espacamentoValor) / 2;
		int yTfValor = (alturaPanelRespostas - (alturaSlider + alturaTfValor) - espacamentoValor) / 2;
		int ySlider = yLblValor + espacamentoValor + alturaLblValor;
		
		JPanel panelPergunta = new JPanel();
		panelPergunta.setBounds(0, 0, larguraMaxima, alturaPanelPergunta);
		getContentPane().add(panelPergunta);
		panelPergunta.setLayout(null);
		
		JLabel lblPerguntaPersonalizada = new JLabel();
		lblPerguntaPersonalizada.setFont(new Font("SansSerif", Font.BOLD, 16));
		lblPerguntaPersonalizada.setBounds(10, 10, 10, 10);
		if (premissa.getVariavel().getPergunta().equalsIgnoreCase("")) {
			lblPerguntaPersonalizada.setText("Qual o valor de " + premissa.getVariavel().getNome() + "?");
		} else {
			lblPerguntaPersonalizada.setText(premissa.getVariavel().getPergunta() + ":");
		}
		lblPerguntaPersonalizada.setSize(lblPerguntaPersonalizada.getPreferredSize());
		int xPerguntaPersonalizada = (larguraMaxima - lblPerguntaPersonalizada.getSize().width) / 2;
		int yPerguntaPersonalizada = (alturaPanelPergunta - lblPerguntaPersonalizada.getSize().height) / 2;
		lblPerguntaPersonalizada.setLocation(xPerguntaPersonalizada, yPerguntaPersonalizada);
		panelPergunta.add(lblPerguntaPersonalizada);
		
		JPanel panelRespostas = new JPanel();
		panelRespostas.setBounds(0, alturaPanelPergunta, larguraMaxima, alturaPanelRespostas);
		getContentPane().add(panelRespostas);
		panelRespostas.setLayout(null);
						
		JPanel panelBotoes = new JPanel();
		panelBotoes.setBounds(0, yPanelBotoes, larguraMaxima, alturaPanelBotoes);
		getContentPane().add(panelBotoes);
		panelBotoes.setLayout(null);
		
		int alturaMaxima = alturaPanelPergunta + alturaPanelRespostas + alturaPanelBotoes;
		this.setBounds(100, 100, larguraMaxima + 8, alturaMaxima + 35);
		
		final List<JRadioButton> jRadioList = new ArrayList<JRadioButton>();
		final List<JCheckBox> jCheckBoxList = new ArrayList<JCheckBox>();
		ButtonGroup buttonGroup = new ButtonGroup();
		//comentar/deletar os "fores"(for) abaixo quando descomentar os conteudos do switch logo mais abaixo
		//UNIVALODARO
//		for (int i = 0; i < qtdRespostas; i++) {
//			jRadioList.add(new JRadioButton("Radio " + i));
//			jRadioList.get(i).setBounds(10, (yRespostas * i) + (borda / 2), 109, alturaRespIndividual);
//			jRadioList.get(i).setSize(jRadioList.get(i).getPreferredSize());
//			panelRespostas.add(jRadioList.get(i));
//			buttonGroup.add(jRadioList.get(i));
//		}
		//MULTIVALORADO
//		for (int i = 0; i < qtdRespostas; i++) {
//			jCheckBoxList.add(new JCheckBox("Check " + i));
//			jCheckBoxList.get(i).setBounds(10, (yRespostas * i) + (borda / 2), 109, alturaRespIndividual);
//			jCheckBoxList.get(i).setSize(jCheckBoxList.get(i).getPreferredSize());
//			panelRespostas.add(jCheckBoxList.get(i));
//		}
		
		switch (premissa.getVariavel().getTipo()) {
		case UNIVALORADO:
			//descomentar quando estiver chamando pelo executar do motor
			for (int i = 0; i < qtdRespostas; i++) {
				jRadioList.add(new JRadioButton(premissa.getVariavel().getRespostas().get(i).getValor()));
				jRadioList.get(i).setBounds(10, (yRespostas * i) + (borda / 2), 109, alturaRespIndividual);
				jRadioList.get(i).setSize(jRadioList.get(i).getPreferredSize());
				panelRespostas.add(jRadioList.get(i));
				buttonGroup.add(jRadioList.get(i));
			}
			panelRespostas.setBorder(BorderFactory.createTitledBorder("Opções"));
			break;
		case MULTIVALORADO:
			//descomentar quando estiver chamando pelo executar do motor
			for (int i = 0; i < qtdRespostas; i++) {
				jCheckBoxList.add(new JCheckBox(premissa.getVariavel().getRespostas().get(i).getValor()));
				jCheckBoxList.get(i).setBounds(10, (yRespostas * i) + (borda / 2), 109, alturaRespIndividual);
				jCheckBoxList.get(i).setSize(jCheckBoxList.get(i).getPreferredSize());
				panelRespostas.add(jCheckBoxList.get(i));
			}
			panelRespostas.setBorder(BorderFactory.createTitledBorder("Opções"));
			break;
		case NUMERICO:
			JLabel lblValor = new JLabel("Valor");
			lblValor.setFont(new Font("SansSerif", Font.PLAIN, 14));
			lblValor.setBounds(xLblValor, yLblValor, larguraLblValor, alturaLblValor);
			panelRespostas.add(lblValor);
			
			textField = new JTextField();
			textField.setEditable(false);
			textField.setBounds(xTfValor, yTfValor, larguraTfValor, alturaTfValor);
			panelRespostas.add(textField);
			textField.setColumns(10);			

			final JSlider slider = new JSlider();
			slider.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent arg0) {
					textField.setText(String.valueOf(slider.getValue()));
				}
			});
			slider.setBounds(xSlider, ySlider, larguraSlider, alturaSlider);
			// Adicionar erro quando o texto for digitado no textField e o valor for maior que o permitido
			// setar o minimo e o maximo
			int minimo = Integer.parseInt(premissa.getVariavel().getRespostas().get(0).getValor());
			slider.setMinimum(minimo);
			slider.setMaximum(Integer.parseInt(premissa.getVariavel().getRespostas().get(premissa.getVariavel().getRespostas().size() - 1).getValor()));
			slider.setValue(minimo);
			panelRespostas.add(slider);
			
			panelRespostas.setBorder(BorderFactory.createTitledBorder(""));
			break;
		default: //FERROU
			break;
		}
		
		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				List<String> valorSelecao = new ArrayList<String>(); 
				switch (premissa.getVariavel().getTipo()) {
				case UNIVALORADO:
					for(JRadioButton jRadioButton : jRadioList) {
						if(jRadioButton.isSelected()) {
							valorSelecao.add(jRadioButton.getText());
							break;
						}
					}
					break;
				case MULTIVALORADO:
					for(JCheckBox jCheckBox : jCheckBoxList) {
						if(jCheckBox.isSelected()) {
							valorSelecao.add(jCheckBox.getText());
						}
					}
					break;
				case NUMERICO:
					valorSelecao.add(textField.getSelectedText());
				}

				if(!valorSelecao.isEmpty()) {
					for(RespostaVariavel respostaVariavel : premissa.getVariavel().getRespostas()) {
						for(String resposta : valorSelecao) {
							if(respostaVariavel.getValor().equalsIgnoreCase(resposta)) {
								respostaVariavel.setSelecionado(true);
								Motor.getInstancia().setResultado(Motor.getInstancia().getResultado() + "\nResposta: " + premissa.getVariavel().getNome() + " é IGUAL a " + respostaVariavel.getValor());
								break;
							}
						}
					}
					dispose();				
				} else {
					JOptionPane.showMessageDialog(null, "Por Favor.\nPreencha com um valor!", "Aviso", JOptionPane.WARNING_MESSAGE);
				}
				
			}
		});
		btnOk.setBounds(xBotaoOk, yBotaoOk, larguraBotao, alturaBotao);
		panelBotoes.add(btnOk);
		
		this.setLocationRelativeTo(null);
	}
}
