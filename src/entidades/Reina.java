package entidades;

public class Reina extends Pieza {
	

	public Reina(Posicion posicion, String color) {
		super(posicion, color);
		this.setTipoPieza("D");
	}

	//Reina se mueve fila, columna o diagonal
	@Override
	public boolean movimientoValido(Posicion destino) {
		// TODO Auto-generated method stub
		char columnaOrigen=this.getPosicion().getColumna();
		int filaOrigen=this.getPosicion().getFila();
		//Consulta movimiento diagonal
		for(int f=-1; f<=1; f++){
			for(int c=-1;c<=1;c++){
				if(super.movimiento(columnaOrigen,filaOrigen,destino.getColumna(),destino.getFila(),f,c))
					return true;
			}
		}
		//Falta consulta en fila o columna 
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
