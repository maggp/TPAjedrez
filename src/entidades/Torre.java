package entidades;

public class Torre extends Pieza {
	

	public Torre(Posicion posicion, String color) {
		super(posicion, color);
		this.setTipoPieza("T");
	}

	//TORRE SE MUEVE SOLO EN COLUMNA Y FILA
	@Override
	public boolean movimientoValido(Posicion destino) {
		char columnaOrigen=this.getPosicion().getColumna();
		int filaOrigen=this.getPosicion().getFila();
		if(super.esMismaPosicion(columnaOrigen, filaOrigen, destino.getColumna(), destino.getFila())){
			 return false;
		}
		else{
		for(int i=-1;i<=1;i++){
			for(int j=-1;j<=1;j++){
				if((j-i)==1 || (j-i)==-1){
					if(movimiento(columnaOrigen, filaOrigen, destino.getColumna(), destino.getFila(), i, j)){ 
			             return true; 
			         }
					
				}
			}
		}
	 
		
		return false;
	}

    }
}