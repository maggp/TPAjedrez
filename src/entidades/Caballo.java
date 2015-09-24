package entidades;

public class Caballo extends Pieza {

	public Caballo(Posicion posicion, String color) {
		super(posicion, color);
		this.setTipoPieza("C");
	}

	@Override
	public boolean movimientoValido(Posicion destino) {
		// TODO Auto-generated method stub

		char columnaOrigen=this.getPosicion().getColumna();
		int filaOrigen=this.getPosicion().getFila();
		int col= 1; 
        for(int fila=-2; fila < 3; fila++){ 
        	
        	if(fila != 0){ 
                if(movimientoCaballo(destino.getColumna(), destino.getFila(), columnaOrigen, filaOrigen, fila, col)){ 
                   return true;  
                } 
            } 
        	if(fila<0){ 
                col++; 
            }else{ 
                if(fila>=0){ 
                    col--; 
                } 
            } 
	}
        return false;
	}

	private boolean movimientoCaballo(char columna, int fila,
			char columnaOrigen, int filaOrigen, int f, int c) {
		// TODO Auto-generated method stub
		char[] colum={'a','b','c','d','e','f','g'};
		return (((filaOrigen+f)==fila) && (((columnaOrigen+colum[c-1])==columna) || (columnaOrigen-colum[c-1])==columna));
	}
	
}
