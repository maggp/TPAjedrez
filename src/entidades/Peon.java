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
		// TODO Auto-generated method stub
		int avanza;
		char columnaOrigen=this.getPosicion().getColumna();
		int filaOrigen=this.getPosicion().getFila();
		//MOVIMIENTO RECTO HACIA ADELANTE 
		if(isPrimerMovimiento()){
			avanza=2;
			if(getColor()=="blanca"){ 
                avanza = 2; 
            } else{ 
                avanza = -2; 
            }
			this.setPrimerMovimiento(true);
			
		}else{
			if(getColor()=="blanca"){ 
                avanza = 1; 
            } else{ 
                avanza = -1; 
            }
		
		}
		if(filaOrigen+avanza==destino.getFila() && columnaOrigen==destino.getColumna()){
			return true;
		}
		//MOVIMIENTO DIAGONAL
				if(super.movimiento(columnaOrigen,filaOrigen,destino.getColumna(),destino.getFila(),-1,1)){
					return true;
			    }
				if(super.movimiento(columnaOrigen,filaOrigen,destino.getColumna(),destino.getFila(),1,1)){
					return true;
			    }
		
		return false;
	    }
        //metodo para ver si el peon llego al final del tablero para recuperar otra pieza
		public boolean finalTablero(){
			int fila=this.getPosicion().getFila();
			if(getColor()=="blanco"){
				
				if(fila==8){
					return true;
				}
			}
			else{
				if(fila==1){
					return true;
				}
			}
			return false;
		}
}
