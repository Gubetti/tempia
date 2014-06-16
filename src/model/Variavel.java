package model;

public class Variavel {

	private String nome;
	private String pergunta;
	private boolean objetivo;
	private TipoVariavel tipo;
	private String[][] valores;
	public final String TRUE = "true";
	public final String FALSE = "false";
	
	public Variavel(String nome, boolean objetivo, TipoVariavel tipo, String[][] valores) {
		this.nome = nome;
		this.objetivo = objetivo;
		this.tipo = tipo;
		this.valores = valores;
	}

	public void inserirValor(String valor) {
		for(int i = 0; i < valores.length; i++) {
			
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
		this.tipo = tipo;
	}
	
	public String[][] getValores() {
		return valores;
	}
	
	public void setValores(String[][] valores) {
		this.valores = valores;
	}
}
