package entidades;

public class Alfil extends Pieza {
	
	public Alfil(Posicion posicion, String color) {
		super(posicion, color);
		this.setTipoPieza("A");
	}

	//ALFIL se mueve en diagonales 
		@Override
		public boolean movimientoValido(Posicion destino) {
				char columnaOrigen=this.getPosicion().getColumna();
				int filaOrigen=this.getPosicion().getFila();
				//FIJARSE QUE NO SEA LA MISMA POSICION QUE LA INICIAL
				if(super.esMismaPosicion(columnaOrigen, filaOrigen, destino.getColumna(), destino.getFila())){
					 return false;
				}
				else{
					//valido si el movimiento es valido
				for(int f=-1; f<=1; f+=2){
					for(int c=-1;c<=1;c+=2){
						if(super.movimiento(columnaOrigen,filaOrigen,destino.getColumna(),destino.getFila(),f,c))
							return true;
					}
				}
				return false;
			
			}
		}
		
	


}
