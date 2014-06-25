package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;

import model.Motor;
import model.Operador;
import model.Regra;
import model.RespostaVariavel;
import model.Sentenca;
import model.Variavel;

@SuppressWarnings("serial")
public class TelaSentenca extends JDialog {

	@SuppressWarnings("rawtypes")
	private JComboBox cbVariavel;
	@SuppressWarnings("rawtypes")
	private JComboBox cbOperador;
	@SuppressWarnings("rawtypes")
	private JComboBox cbValores;
	private JButton btnSalvar;
	
	private Regra regra;
	private boolean premissa;
	private Sentenca sentencaEditar;
	private TelaSentencas telaSentencas;
	/**
	 * Create the dialog.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public TelaSentenca(Regra regra, boolean premissa, Sentenca sentencaEditar, TelaSentencas telaSentencas) {
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setModal(true);
		this.setBounds(100, 100, 460, 121);
		this.setResizable(false);
		
		this.regra = regra;
		this.premissa= premissa;
		this.sentencaEditar = sentencaEditar;
		this.telaSentencas = telaSentencas;
		String txt = "Inserir ";
		if(sentencaEditar != null) {
			txt = "Editar ";
		}
		String titulo = "premissa";
		if(!premissa) {
			titulo = "conclusão";
		}
		this.setTitle(txt + titulo);
		this.setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 454, 48);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		DefaultComboBoxModel<Object> modelVariavel = new DefaultComboBoxModel<>(Motor.getInstancia().getVariaveis().toArray());
		cbVariavel = new JComboBox(modelVariavel);
		cbVariavel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				carregarCombos();
			}
		});
		cbVariavel.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				carregarCombos();
			}
		});
		cbVariavel.setBounds(10, 11, 166, 20);
		panel.add(cbVariavel);
		
		cbOperador = new JComboBox();
		cbOperador.setBounds(186, 11, 67, 20);
		panel.add(cbOperador);
		
		cbValores = new JComboBox();
		cbValores.setBounds(263, 11, 166, 20);
		panel.add(cbValores);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 47, 454, 42);
		getContentPane().add(panel_1);
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salvar();
			}
		});
		panel_1.add(btnSalvar);

	}
	
	@SuppressWarnings({ "unchecked" })
	private void carregarCombos() {
		Variavel variavelSelecionada = (Variavel) cbVariavel.getSelectedItem();
		if(variavelSelecionada != null) {
			DefaultComboBoxModel<Object> modelOperadores = new DefaultComboBoxModel<>(new Object[]{Operador.IGUAL});
			if(premissa) {
				modelOperadores = new DefaultComboBoxModel<>(variavelSelecionada.getOperadores().toArray());
			}
			cbOperador.setModel(modelOperadores);
			DefaultComboBoxModel<Object> modelValores = new DefaultComboBoxModel<>(variavelSelecionada.getRespostas().toArray());
			cbValores.setModel(modelValores);
			
			if(sentencaEditar != null) {
				cbVariavel.setSelectedItem(sentencaEditar.getVariavel());
				cbOperador.setSelectedItem(sentencaEditar.getOperadorSelecionado());
				for(RespostaVariavel respostaVariavel : variavelSelecionada.getRespostas()) {
					if(respostaVariavel.equals(sentencaEditar.getValorSelecao())) {
						cbValores.setSelectedItem(respostaVariavel);
						break;
					}
				}
			}
		} else {
			btnSalvar.setEnabled(false);
		}
	}
	
	private void salvar() {
		if(sentencaEditar == null) {
			Sentenca sentenca = new Sentenca((Variavel) cbVariavel.getSelectedItem());
			sentenca.setOperadorSelecionado((Operador)cbOperador.getSelectedItem());
			sentenca.setValorSelecao((RespostaVariavel) cbValores.getSelectedItem());
			if(premissa) {
				regra.getPremissas().add(sentenca);
			} else {
				regra.getConclusoes().add(sentenca);
			}
		} else {
			sentencaEditar.setVariavel((Variavel) cbVariavel.getSelectedItem());
			sentencaEditar.setOperadorSelecionado((Operador)cbOperador.getSelectedItem());
			sentencaEditar.setValorSelecao((RespostaVariavel) cbValores.getSelectedItem());
		}
		telaSentencas.atualizarTabelas();
		dispose();
	}
}
