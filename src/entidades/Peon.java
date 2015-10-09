package entidades;

public class Peon  extends Pieza{
	
	private boolean primerMovimiento=false;
	public boolean isPrimerMovimiento() {
		return primerMovimiento;
	}
	public void setPrimerMovimiento(boolean primerMovimiento) {
		this.primerMovimiento = primerMovimiento;
	}
	public Peon(Posicion posicion, String color) {
		super(posicion, color);
		setTipoPieza("P");
	}
	//PEON Solo hacia adelante si es la primera vez 2 sino uno. Come en diagonal
	
	public boolean movimientoValido(Posicion destino) {
		int avanza=0;
		boolean valido=false;
		char columnaOrigen=this.getPosicion().getColumna();
		int filaOrigen=this.getPosicion().getFila();
		//MOVIMIENTO RECTO HACIA ADELANTE 
		if(super.esMismaPosicion(columnaOrigen, filaOrigen, destino.getColumna(), destino.getFila())){
			 return false;
		}
		else{
			
			if(getColor().equals("blanco")){ 
                avanza = 1; 
            } else{ 
                avanza = -1; 
            }
			if(filaOrigen+avanza==destino.getFila() && columnaOrigen==destino.getColumna()){
				valido=true;
				this.setPrimerMovimiento(true);
			}
			
			if(!valido){				
								if(getColor().equals("blanco")){ 
					                avanza = 2; 
					            } else{ 
					                avanza = -2; 
					            }
								this.setPrimerMovimiento(true);
								if(!primerMovimiento){
									if(filaOrigen+avanza==destino.getFila() && columnaOrigen==destino.getColumna()){
										valido=true;
									}		
								}
								
						}
				//MOVIMIENTO DIAGONAL
				if(super.movimiento(columnaOrigen,filaOrigen,destino.getColumna(),destino.getFila(),-1,1)){
					valido=true;
			    }
				if(super.movimiento(columnaOrigen,filaOrigen,destino.getColumna(),destino.getFila(),1,1)){
					valido=true;
			    }
		  }
		return valido;
	    }
	
}
