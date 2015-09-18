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
		
		
		Torre torreBlancaI = new Torre(new Posicion('a',1),"blanco");
		colPiezas.put(torreBlancaI.getPosicion(), torreBlancaI);
		
		Caballo caballoBlancaI = new Caballo(new Posicion('b',1),"blanco");
		colPiezas.put(caballoBlancaI.getPosicion(), caballoBlancaI);
		
		Alfil alfilBlancaI = new Alfil(new Posicion('c',1),"blanco");
		colPiezas.put(alfilBlancaI.getPosicion(), alfilBlancaI);
		
		Reina reinaBlanca = new Reina(new Posicion('d',1),"blanco");
		colPiezas.put(reinaBlanca.getPosicion(), reinaBlanca);
		
		Rey reyBlanca = new Rey(new Posicion('e',1),"blanco");
		colPiezas.put(reyBlanca.getPosicion(), reyBlanca);
		
		Torre torreBlancaD = new Torre(new Posicion('f',1),"blanco");
		colPiezas.put(torreBlancaD.getPosicion(), torreBlancaD);
		
		Caballo caballoBlancaD = new Caballo(new Posicion('g',1),"blanco");
		colPiezas.put(caballoBlancaD.getPosicion(), caballoBlancaD);
		
		Alfil alfilBlancaD = new Alfil(new Posicion('h',1),"blanco");
		colPiezas.put(alfilBlancaD.getPosicion(), alfilBlancaD);
		
		char col='a';
		for (int i = 0; i < 8; i++) {
			Peon peonBlanca= new Peon(new Posicion(col, 2),"blanco");
			colPiezas.put(peonBlanca.getPosicion(), peonBlanca);
			col++;
		}		
		
		
		
	}
 
}
