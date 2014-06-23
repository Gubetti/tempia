package model;

public enum Operador {
	IGUAL, DIFERENTE, MAIOR_QUE, MENOR_QUE, MAIOR_IGUAL, MENOR_IGUAL;
	
    @Override
    public String toString() {
        switch (this) {
            case IGUAL:
                return "=";
            case DIFERENTE:
                return "<>";
            case MAIOR_QUE:
                return ">";
            case MENOR_QUE:
                return "<";
            case MAIOR_IGUAL:
                return ">=";
            case MENOR_IGUAL:
                return "<=";
        }
		return null;
    }
}
