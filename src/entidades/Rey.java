package entidades;

public class Rey extends Pieza {
	
	public Rey(Posicion posicion, String color) {
		super(posicion, color);
		this.setTipoPieza("R");
		
	}

	//REY SE MUEVE FILA, COLUMNA Y DIAGONAL PERO UNA SOLA POSICION
	@Override
	public boolean movimientoValido(Posicion destino) {
		// TODO Auto-generated method stub
		char columnaOrigen=this.getPosicion().getColumna();
		int filaOrigen=this.getPosicion().getFila();
		if(super.esMismaPosicion(columnaOrigen, filaOrigen, destino.getColumna(), destino.getFila())){
			 return false;
		}
		else{
		for(int f=-1; f<=1; f++){
			for(int c=-1;c<=1;c++){
				if(super.movimiento(columnaOrigen,filaOrigen,destino.getColumna(),destino.getFila(),f,c))
					return true;
			}
		}
		return false;
		}
	

	  }
}