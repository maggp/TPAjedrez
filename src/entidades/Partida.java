package entidades;

import java.util.HashMap;

public class Partida {
	private int idPartida;
	private String turno;
	private HashMap<Posicion, Pieza> colPiezas;
	private Jugador jugadorBlancas;
	private Jugador jugadorNegras;
	
	/**
	 * Constructores
	 */
	public Partida(Jugador j1, Jugador j2){
		
		jugadorBlancas = j1;
		jugadorNegras = j2;
		turno = "blanco";
		colPiezas = new HashMap<Posicion, Pieza>();
		inicializarPiezas("blanco");
		inicializarPiezas("negro");
	}

	public Partida() {
		
	}

	/**
	 * getters y setters
	 */
	public int getIdPartida() {
		return idPartida;
	}

	public void setIdPartida(int idPartida) {
		this.idPartida = idPartida;
	}

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	public HashMap<Posicion, Pieza> getColPiezas() {
		return colPiezas;
	}

	public void setColPiezas(HashMap<Posicion, Pieza> colPiezas) {
		this.colPiezas = colPiezas;
	}

	public Jugador getJugadorBlancas() {
		return jugadorBlancas;
	}

	public void setJugadorBlancas(Jugador jugadorBlancas) {
		this.jugadorBlancas = jugadorBlancas;
	}

	public Jugador getJugadorNegras() {
		return jugadorNegras;
	}

	public void setJugadorNegras(Jugador jugadorNegras) {
		this.jugadorNegras = jugadorNegras;
	}


	/**
	 * metodos
	 */
	private void inicializarPiezas(String color) {
		// LAS DOS TORRES
			
			int fila1, fila2;
			if (color.equals("blanco")) {
				fila1=1;
				fila2=2;
			} else {
				fila1=8;
				fila2=7;
			}
			
			Torre torre = new Torre(new Posicion('a',fila1),color);
			colPiezas.put(torre.getPosicion(), torre);
			
			torre = new Torre(new Posicion('f',fila1),color);
			colPiezas.put(torre.getPosicion(), torre);
			
	    	// LOS DOS CABALLOS	
			
			Caballo caballo = new Caballo(new Posicion('b',fila1),color);
			colPiezas.put(caballo.getPosicion(), caballo);
			
			caballo = new Caballo(new Posicion('g',fila1),color);
			colPiezas.put(caballo.getPosicion(), caballo);		
			
			// LOS DOS ALFILES
			
			Alfil alfil = new Alfil(new Posicion('c',fila1),color);
			colPiezas.put(alfil.getPosicion(), alfil);
			
			alfil = new Alfil(new Posicion('h',fila1),color);
			colPiezas.put(alfil.getPosicion(), alfil);
			
			// REINA
			
			Reina reina = new Reina(new Posicion('d',fila1),color);
			colPiezas.put(reina.getPosicion(), reina);
			
			// REY
			
			Rey rey = new Rey(new Posicion('e',fila1),color);
			colPiezas.put(rey.getPosicion(), rey);
			
			//PEONES
			char col='a';
			for (int i = 0; i < 8; i++) {
				Peon peon= new Peon(new Posicion(col, fila2),color);
				colPiezas.put(peon.getPosicion(), peon);
				col++;
			}
	} 
}
