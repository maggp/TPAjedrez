package entidades;

public class Posicion {
	
	private char columna;
	private int fila; 
	
	
	
	public char getColumna() {
		return columna;
	}


	public void setColumna(char columna) {
		this.columna = columna;
	}


	public int getFila() {
		return fila;
	}


	public void setFila(int fila) {
		this.fila = fila;
	}


	public Posicion (char col, int fila){
		columna = col;
		this.fila = fila;
	}
	
	@Override
	public boolean equals(Object obj) {
		return (obj instanceof Posicion 
				&& this.getColumna()==((Posicion)obj).getColumna()
				&& this.getFila()==((Posicion)obj).getFila());
	}

	@Override
	public int hashCode() {
		return Character.hashCode(getColumna())*Integer.hashCode(getFila());
	}
	
	
	

}
