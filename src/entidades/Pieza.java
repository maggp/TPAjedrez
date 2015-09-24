package entidades;

public abstract class Pieza {
	private String color;
	private Posicion posicion;
	private String tipoPieza;
		
	public String getTipoPieza() {
		return tipoPieza;
	}
	protected void setTipoPieza(String tipoPieza) {
		this.tipoPieza = tipoPieza;
	}
	public Pieza(Posicion posicion, String color){
		
		this.posicion = posicion;
		this.color = color;
		
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Posicion getPosicion() {
		return posicion;
	}
	public void setPosicion(Posicion posicion) {
		this.posicion = posicion;
	}
	
	
	public abstract boolean movimientoValido(Posicion destino);
	
	protected boolean movimiento(char columnaOrigen, int filaOrigen, char columna,
			int fila, int itf, int itc) {
		// TODO Auto-generated method stub
		boolean valido=false;
		char[] colum={'a','b','c','d','e','f','g'};
		for(int F=filaOrigen, C=columnaOrigen;F<=8 && C<=8 && C>0 && F>0;F+=itf , C+=itc){
			if(F==fila && colum[C-1]==columna)
				
			//FIJARSE QUE NO SEA LA MISMA POSICION QUE LA INICIAL
				if(columna==columnaOrigen && fila==filaOrigen){
					valido=false;
				}
				else{
						valido=true;
				}
			}
		return valido;
	}
	protected boolean movimientoDiagonal(char columnaOrigen, int filaOrigen, char columna,
			int fila, int itf, int itc) {
		
		// TODO Auto-generated method stub
		boolean valido=false;
		int COrigen=0;
		char[] colum={'a','b','c','d','e','f','g'};
		for(int i=0; i<=7;i++){
			if(colum[i]==columnaOrigen) {COrigen=i;
									/*salir de for*/		i=8;}
			
		}
		for(int F=filaOrigen, C=COrigen;F<=8 && C<=8 && C>=0 && F>0;F+=itf , C+=itc){
			if(F==fila && colum[C]==columna)valido=true;
				
		}
			
			
		return valido;
	}
	public boolean movimientoTorre(char columnaOrigen, int filaOrigen, char columna,
			int fila, int itf, int itc){ 
		int COrigen=0;
		char[] colum={'a','b','c','d','e','f','g'};
		for(int i=0; i<=7;i++){
			if(colum[i]==columnaOrigen) {COrigen=i;
									/*salir de for*/		i=8;}
			
		}
        return (((filaOrigen+itf) == fila)&&((colum[COrigen+itc])==columna)); 
} 
	
}