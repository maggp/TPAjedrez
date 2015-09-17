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
	}
		
	
	
	// piezas blancas. al final de cada variable hay una I izquierdo y  D derecho
	
	//CORREGIR
	
	Torre torreBlancaI = new Torre(new Posicion("a",1));
	Caballo caballoBlancaI = new Caballo("b",1);
	Alfil alfilBlancaI = new Alfil("c",1);
	Reina reinaBlanca = new Reina("d",1);
	Rey reyBlanca = new Rey("e",1);
	Torre torreBlancaD = new Torre("f",1);
	Caballo caballoBlancaD = new Caballo("g",1);
	Alfil alfilBlancaD = new Alfil("h",1);
	Torre torreBlancaD = new Torre(new Posicion("a",1));
	
	
	for (int i = 0; i < 8; i++){
		
		Peon PeonBlanca = new Peon(new posicion("a",2));
		
			
			
		
		
		
	}
 
	
	// piezas negras
	
	

	
	
}
