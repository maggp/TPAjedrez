package entidades;

public class Alfil extends Pieza {
	
	public Alfil(Posicion posicion, String color) {
		super(posicion, color);
		this.setTipoPieza("A");
	}

	//ALFIL se mueve en diagonales 
		@Override
		public boolean movimientoValido(Posicion destino) {
			// TODO Auto-generated method stub
				char columnaOrigen=this.getPosicion().getColumna();
				int filaOrigen=this.getPosicion().getFila();
				for(int f=-1; f<=1; f+=2){
					for(int c=-1;c<=1;c+=2){
						if(super.movimiento(columnaOrigen,filaOrigen,destino.getColumna(),destino.getFila(),f,c))
							return true;
					}
				}
				return false;
			
			}

		
	


}
