package model;

import java.util.ArrayList;
import java.util.List;

public class Variavel {

	private String nome;
	private String pergunta;
	private boolean objetivo;
	private TipoVariavel tipo;
	private List<Operador> operadores;
	private List<RespostaVariavel> respostas;
	
	
	public Variavel(String nome, boolean objetivo, TipoVariavel tipo) {
		this.nome = nome;
		this.objetivo = objetivo;
		this.tipo = tipo;
		inserirOperadores();
		this.respostas = new ArrayList<RespostaVariavel>();
		this.pergunta = "";
	}

	private void inserirOperadores() {
		this.operadores = new ArrayList<>();
		
		operadores.add(Operador.IGUAL);
		operadores.add(Operador.DIFERENTE);

		if (tipo == TipoVariavel.NUMERICO) {
			operadores.add(Operador.MAIOR_IGUAL);
			operadores.add(Operador.MAIOR_QUE);
			operadores.add(Operador.MENOR_IGUAL);
			operadores.add(Operador.MENOR_QUE);
		}
	}
	
	public boolean inserirValor(RespostaVariavel valor) {
		for(RespostaVariavel respostaVariavel : respostas) {
			if(respostaVariavel.getValor().equalsIgnoreCase(valor.getValor())) {
				return false;
			}
		}
		respostas.add(valor);
		return true;
	}
	
	public String verificarValorUsado(String valor) {
		String texto = "";
		for(Regra regra : Motor.getInstancia().getRegras()) {
			for(Sentenca premissa : regra.getPremissas()) {
				if(premissa.getVariavel().equals(this) && premissa.getValorSelecao().equalsIgnoreCase(valor)) {
					texto += "\nRegra de ordem " + (Motor.getInstancia().getRegras().indexOf(regra) + 1) + ", na(s) premissa(s).";
					break;
				}
			}
			
			for(Sentenca conclusao : regra.getConclusoes()) {
				if(conclusao.getVariavel().equals(this) && conclusao.getValorSelecao().equalsIgnoreCase(valor)) {
					texto += "\nRegra de ordem " + (Motor.getInstancia().getRegras().indexOf(regra) + 1) + ", na(s) conclus�o(�es).";
					break;
				}
			}
		}		
		return texto;
	}
	
	public void removerValor(String valor) {
		for(RespostaVariavel respostaVariavel : respostas) {
			if(respostaVariavel.getValor().equalsIgnoreCase(valor)) {
				respostas.remove(respostaVariavel);
				break;
			}
		}

		List<Regra> regrasRemover = new ArrayList<Regra>();
		for(Regra regra : Motor.getInstancia().getRegras()) {
			for(Sentenca premissa : regra.getPremissas()) {
				if(premissa.getValorSelecao().equalsIgnoreCase(valor)) {
					regrasRemover.add(regra);
				}
			}
			
			for(Sentenca conclusao : regra.getConclusoes()) {
				if(conclusao.getValorSelecao().equalsIgnoreCase(valor)) {
					regrasRemover.add(regra);
				}
			}
		}
		
		for(Regra regraRemover : regrasRemover) {
			Motor.getInstancia().getRegras().remove(regraRemover);
		}
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPergunta() {
		return pergunta;
	}

	public void setPergunta(String pergunta) {
		this.pergunta = pergunta;
	}

	public boolean isObjetivo() {
		return objetivo;
	}

	public void setObjetivo(boolean objetivo) {
		this.objetivo = objetivo;
	}
	
	public TipoVariavel getTipo() {
		return tipo;
	}
	
	public void setTipo(TipoVariavel tipo) {
		if(tipo == TipoVariavel.MULTIVALORADO) {
			if(this.tipo == TipoVariavel.NUMERICO) {
				this.respostas = new ArrayList<RespostaVariavel>();
			}
		} else {
			this.respostas = new ArrayList<RespostaVariavel>();			
		}
		//verificar senten�as
		this.tipo = tipo;
		inserirOperadores();
	}

	public List<Operador> getOperadores() {
		return operadores;
	}

	public void setOperadores(List<Operador> operadores) {
		this.operadores = operadores;
	}

	public List<RespostaVariavel> getRespostas() {
		return respostas;
	}

	public void setRespostas(List<RespostaVariavel> respostas) {
		this.respostas = respostas;
	}

	@Override
	public String toString() {
		return nome;
	}
	
}
