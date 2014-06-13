package model;

public class Variavel {

	private String nome;
	private boolean objetivo;
	private Tipo tipo;
	private String[] valores;
	// discutir a necessidade de utilizar multivalorado
	private String valor;
	
	public Variavel(String nome, boolean objetivo, Tipo tipo, String[] valores) {
		this.nome = nome;
		this.objetivo = objetivo;
		this.tipo = tipo;
		this.valores = valores;
	}

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean isObjetivo() {
		return objetivo;
	}

	public void setObjetivo(boolean objetivo) {
		this.objetivo = objetivo;
	}
	
	public Tipo getTipo() {
		return tipo;
	}
	
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	
	public String[] getValores() {
		return valores;
	}
	
	public void setValores(String[] valores) {
		this.valores = valores;
	}
	
	public String getValor() {
		return valor;
	}
	
	public void setValor(String valor) {
		this.valor = valor;
	}
	
}
