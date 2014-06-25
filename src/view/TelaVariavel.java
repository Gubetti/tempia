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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;

import model.Motor;
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
	private JRadioButton jRadioButtonAtual;

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
		table = new JTable(new RespostaVariavelTableModel());
		this.setTitle("Inserir variável");
		init();
	}
	
	public TelaVariavel(Variavel variavel) {
		this.variavelEditar = variavel;
		table = new JTable(new RespostaVariavelTableModel());
		init();
		this.setTitle("Editar variável " + variavel.getNome());
		txtNome.setText(variavel.getNome());
		txtPergunta.setText(variavel.getPergunta());
		switch(variavel.getTipo()) {
		case UNIVALORADO:
			jRadioList.get(0).setSelected(true);
			jRadioButtonAtual = jRadioList.get(0);
			break;
		case MULTIVALORADO:
			jRadioList.get(1).setSelected(true);
			jRadioButtonAtual = jRadioList.get(1);
			break;
		case NUMERICO:
			jRadioList.get(2).setSelected(true);
			jRadioButtonAtual = jRadioList.get(2);
		}
		chckbxObjetvo.setSelected(variavel.isObjetivo());
		RespostaVariavelTableModel modelo = (RespostaVariavelTableModel) table.getModel();
		
		if(variavel.getTipo() != TipoVariavel.NUMERICO) {
			for(RespostaVariavel respostaVariavel : variavel.getRespostas()) {
				modelo.addRow(respostaVariavel);
			}
		} else {
			btnInserir.setEnabled(false);
			btnExcluir.setEnabled(false);
			modelo.addRow(variavel.getRespostas().get(0));
			modelo.addRow(variavel.getRespostas().get(variavel.getRespostas().size() - 1));
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
		final JRadioButton uni = new JRadioButton("Univalorado");
		uni.setBounds(16, 17, 83, 23);
		uni.setSize(uni.getPreferredSize());
		uni.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				acaoCliqueRadio(uni);
			}
		});
		final JRadioButton multi = new JRadioButton("Multivalorado");
		multi.setBounds(16, 43, 89, 23);
		multi.setSize(multi.getPreferredSize());
		multi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				acaoCliqueRadio(multi);
			}
		});
		final JRadioButton numer = new JRadioButton("Númerico");
		numer.setBounds(16, 68, 69, 23);
		numer.setSize(numer.getPreferredSize());
		numer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				acaoCliqueRadio(numer);
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
		
		//String[] colunas = new String[]{"Valores"};
		//DefaultTableModel modelo = new DefaultTableModel(null, colunas);
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
		RespostaVariavelTableModel modelo = (RespostaVariavelTableModel) table.getModel();
		modelo.addRow(new RespostaVariavel(valor));
	}
	
	private void removerLinha(int linha) {
		try {
			RespostaVariavelTableModel modelo = (RespostaVariavelTableModel) table.getModel();
			if(variavelEditar != null) {
				String texto = variavelEditar.verificarValorUsado(modelo.getRespostaVariavel(linha));
				if(!texto.equalsIgnoreCase("")) {
					int aviso = JOptionPane.showConfirmDialog(null, "O valor " + modelo.getRespostaVariavel(linha).getValor() + " está sendo usada na(s) seguinte(s) regra(s):\n" 
							+ texto + "\nSe prosseguir, a(s) regra(s) acima serão excluídas.\nDeseja continuar?", "Aviso", JOptionPane.ERROR_MESSAGE);
					if(aviso == 0) {
						variavelEditar.removerValor(modelo.getRespostaVariavel(linha));
						modelo.removeRow(linha);
						TelaPrincipal.getInstancia().atualizarTabelaRegras();
					}
				} else {
					modelo.removeRow(linha);
				}
			} else {
				modelo.removeRow(linha);
			}
	
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Selecione a linha que você deseja remover.", "Aviso", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	private void acaoCliqueRadio(JRadioButton jRadioButton) {
		if(jRadioButton != jRadioButtonAtual) {
			RespostaVariavelTableModel modelo = (RespostaVariavelTableModel) table.getModel();
			// Mudando para numerico
			if(jRadioList.get(2) == jRadioButton) {
				btnInserir.setEnabled(false);
				btnExcluir.setEnabled(false);
				modelo.removeAll();
				modelo.addRow(new RespostaVariavel(""));
				modelo.addRow(new RespostaVariavel(""));		
				JOptionPane.showMessageDialog(null, "Coloque na primeira linha da tabela o menor valor, e na segunda linha o valor máximo do intervalo.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
			} else {
				// Mudando de numerico para outro
				if(jRadioList.get(2) == jRadioButtonAtual) {
					btnInserir.setEnabled(true);
					btnExcluir.setEnabled(true);
					modelo.removeAll();
				}
			}
			jRadioButtonAtual = jRadioButton;
		}
	}
	
	private void salvar() {
		if(txtNome.getText().trim().equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(null, "Informe o nome da variável.", "Aviso", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		if(jRadioButtonAtual == null) {
			JOptionPane.showMessageDialog(null, "Informe qual será o tipo da variável.", "Aviso", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		RespostaVariavelTableModel modelo = (RespostaVariavelTableModel) table.getModel();
		if(modelo.getValores().isEmpty()) {
			modelo.addRow(new RespostaVariavel("Sim"));
			modelo.addRow(new RespostaVariavel("Não"));
		}
		
		for (int i = 0; i <  modelo.getValores().size(); i++) {
			if(modelo.getRespostaVariavel(i).getValor().trim().equalsIgnoreCase("")) {
				JOptionPane.showMessageDialog(null, "Existem valores não preenchido para a variável.\nInforme tais valores ou os remova para prosseguir.", "Aviso", JOptionPane.WARNING_MESSAGE);
				return;
			}
		}
		TipoVariavel tipoVariavel = TipoVariavel.UNIVALORADO;
		if(jRadioButtonAtual.equals(jRadioList.get(1))) {
			tipoVariavel = TipoVariavel.MULTIVALORADO;
		} else if(jRadioButtonAtual.equals(jRadioList.get(2))) {
			tipoVariavel = TipoVariavel.NUMERICO;
		}
		
		// Nova
		if(variavelEditar == null) {
			variavelEditar = new Variavel(txtNome.getText().trim().toLowerCase(), chckbxObjetvo.isSelected(), tipoVariavel);
			variavelEditar.setPergunta(txtPergunta.getText().trim());
			if(tipoVariavel != TipoVariavel.NUMERICO) {
				for (int i = 0; i <  modelo.getValores().size(); i++) {
					variavelEditar.inserirValor(modelo.getValores().get(i));
				}
			} else {
				try {
					int valor1 = Integer.parseInt(modelo.getValores().get(0).getValor());
					int valor2 = Integer.parseInt(modelo.getValores().get(1).getValor());
					if(valor1 < valor2) {
						for(int i = valor1; i <= valor2; i++) {
							variavelEditar.inserirValor(new RespostaVariavel(i + ""));
						}
					} else {
						JOptionPane.showMessageDialog(null, "O valor do primeiro campo deve ser menor que o valor do segundo campo.", "Aviso", JOptionPane.WARNING_MESSAGE);
						variavelEditar = null;
						return;
					}
				} catch(Exception exception) {
					JOptionPane.showMessageDialog(null, "Informe valores numéricos inteiros.", "Aviso", JOptionPane.WARNING_MESSAGE);
					variavelEditar = null;
					return;
				}
			}
		
			if(!Motor.getInstancia().inserirVariavel(variavelEditar)) {
				JOptionPane.showMessageDialog(null, "Já existe uma variável com este nome.\nRenomei para inserir.", "Aviso", JOptionPane.WARNING_MESSAGE);
				variavelEditar = null;
				return;
			}
		} else {
			for(Variavel variavel : Motor.getInstancia().getVariaveis()) {
				if(!variavel.equals(variavelEditar) && variavel.getNome().equalsIgnoreCase(txtNome.getText().trim())) {
					JOptionPane.showMessageDialog(null, "Existe outra variável com este nome.\nRenomei para inserir.", "Aviso", JOptionPane.WARNING_MESSAGE);
					return;
				}
			}
			variavelEditar.setNome(txtNome.getText());
			variavelEditar.setPergunta(txtPergunta.getText());
			variavelEditar.setObjetivo(chckbxObjetvo.isSelected());
			variavelEditar.setTipo(tipoVariavel);
			for(RespostaVariavel respostaVariavel : modelo.getValores()) {
				for(RespostaVariavel valor : modelo.getValores()) {
					if(!respostaVariavel.equals(valor) && respostaVariavel.getValor().trim().equalsIgnoreCase(valor.getValor())) {
						JOptionPane.showMessageDialog(null, "A variável possue valores repetidos.\nRenomei um deles para prosseguir.", "Aviso", JOptionPane.WARNING_MESSAGE);
						return;
					}
				}
			}
			if(tipoVariavel != TipoVariavel.NUMERICO) {
				variavelEditar.setRespostas(modelo.getValores());
			} else {
				try {
					int valor1 = Integer.parseInt(modelo.getValores().get(0).getValor());
					int valor2 = Integer.parseInt(modelo.getValores().get(1).getValor());
					if(valor1 < valor2) {
						variavelEditar.getRespostas().clear();
						for(int i = valor1; i <= valor2; i++) {
							variavelEditar.inserirValor(new RespostaVariavel(i + ""));
						}
					} else {
						JOptionPane.showMessageDialog(null, "O valor do primeiro campo deve ser menor que o valor do segundo campo.", "Aviso", JOptionPane.WARNING_MESSAGE);
						return;
					}
				} catch(Exception exception) {
					JOptionPane.showMessageDialog(null, "Informe valores numéricos inteiros.", "Aviso", JOptionPane.WARNING_MESSAGE);
					return;
				}				
			}
			
		}
		dispose();
	}
}

@SuppressWarnings("serial")
class RespostaVariavelTableModel extends AbstractTableModel{
	private List<RespostaVariavel> valores = new ArrayList<RespostaVariavel>();
	private String[] colunas = new String[] { "Valores" };
	
	@Override
	public int getRowCount() {
		return valores.size();
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
	public Object getValueAt(int row, int arg1) {
		return valores.get(row).getValor();
	}
	
	 public void setValueAt(Object aValue, int rowIndex, int columnIndex) {  
	        valores.get(rowIndex).setValor(aValue.toString().trim());
	        fireTableCellUpdated(rowIndex, columnIndex);
	 }
	 
	public Class<?> getColumnClass(int columnIndex) {  
		return String.class;
	}
	
	  @Override
	    public boolean isCellEditable(int rowIndex, int columnIndex) {
	        return true;
	    }
	  
	public RespostaVariavel getRespostaVariavel(int row) {
		return valores.get(row);
	}
	
	public List<RespostaVariavel> getValores() {
		return valores;
	}
	
	public void removeAll() {
		valores.clear();
		fireTableDataChanged();
	}

	public void removeRow(int row) {
		valores.remove(row);
		fireTableRowsDeleted(row, row);
	}

	public void addRow(RespostaVariavel respostaVariavel) {
		valores.add(respostaVariavel);
		fireTableRowsInserted(valores.size() - 1, valores.size() - 1);
	}
}