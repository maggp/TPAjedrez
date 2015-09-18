package entidades;

import java.util.HashMap;

public class Partida {
	private int idPartida;
	private String turno;
	private HashMap<Posicion, Pieza> colPiezas;
	private Jugador jugadorBlancas;
	private Jugador jugadorNegras;
	
	
	
	public Partida(Jugador j1, Jugador j2){
		
		jugadorBlancas = j1;
		jugadorNegras = j2;
		turno = "blanco";
		
	// LAS DOS TORRES
		
		Torre torreBlanca = new Torre(new Posicion('a',1),"blanco");
		colPiezas.put(torreBlanca.getPosicion(), torreBlanca);
		
		torreBlanca = new Torre(new Posicion('f',1),"blanco");
		colPiezas.put(torreBlanca.getPosicion(), torreBlanca);
		
	// LOS DOS CABALLOS	
		
		Caballo caballoBlanca = new Caballo(new Posicion('b',1),"blanco");
		colPiezas.put(caballoBlanca.getPosicion(), caballoBlanca);
		
		caballoBlanca = new Caballo(new Posicion('g',1),"blanco");
		colPiezas.put(caballoBlanca.getPosicion(), caballoBlanca);		
		
		// LOS DOS ALFILES
		
		Alfil alfilBlanca = new Alfil(new Posicion('c',1),"blanco");
		colPiezas.put(alfilBlanca.getPosicion(), alfilBlanca);
		
		alfilBlanca = new Alfil(new Posicion('h',1),"blanco");
		colPiezas.put(alfilBlanca.getPosicion(), alfilBlanca);
		
		// REINA
		
		Reina reinaBlanca = new Reina(new Posicion('d',1),"blanco");
		colPiezas.put(reinaBlanca.getPosicion(), reinaBlanca);
		
		// REY
		
		Rey reyBlanca = new Rey(new Posicion('e',1),"blanco");
		colPiezas.put(reyBlanca.getPosicion(), reyBlanca);
		
		
		
		
		char col='a';
		for (int i = 0; i < 8; i++) {
			Peon peonBlanca= new Peon(new Posicion(col, 2),"blanco");
			colPiezas.put(peonBlanca.getPosicion(), peonBlanca);
			col++;
		}		
		
		
		
	}
 
}
