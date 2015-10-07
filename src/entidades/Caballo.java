package entidades;

public class Caballo extends Pieza {

	public Caballo(Posicion posicion, String color) {
		super(posicion, color);
		this.setTipoPieza("C");
	}

	@Override
	public boolean movimientoValido(Posicion destino) {
		char columnaOrigen=this.getPosicion().getColumna();
		int filaOrigen=this.getPosicion().getFila();
		if(super.esMismaPosicion(columnaOrigen, filaOrigen, destino.getColumna(), destino.getFila())){
			 return false;
		}
		else{
		int col= 1; 
        for(int fila=-2; fila < 3; fila++){ 
        	
        	if(fila != 0){ 
                if(movimientoCaballo(destino.getColumna(), destino.getFila(), columnaOrigen, filaOrigen, fila, col)){ 
                   return true;  
                } 
            } 
        	if(fila<0){ 
                col++; 
            }
        	else{ 
                if(fila>=0){ 
                    col--; 
                } 
            } 
        }
	}
        return false;
	}

	private boolean movimientoCaballo(char columna, int fila,
			char columnaOrigen, int filaOrigen, int f, int c) {
		int COrigen=0;
		char[] colum={'a','b','c','d','e','f','g','h'};
		for(int i=0; i<=7;i++){
			if(colum[i]==columnaOrigen) {COrigen=i;
									/*salir de for*/		i=8;}
			
		}
		return (((filaOrigen+f)==fila) && (((colum[COrigen+c])==columna) || (colum[COrigen-c])==columna));
	}
}
