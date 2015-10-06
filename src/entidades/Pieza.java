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

	public boolean esMismaPosicion(char columnaOrigen, int filaOrigen, char columna,
			int fila){
		if(columna==columnaOrigen && fila==filaOrigen){
			return true;
			}
		else 
			return false;
	}
	
	protected boolean movimiento(char columnaOrigen, int filaOrigen, char columna,
			int fila, int itf, int itc) {	
		boolean valido=false;
		int F=filaOrigen;
		char C=columnaOrigen;
		for(;F<=8 && C<='h' && C>='a' && F>0;F+=itf , C+=itc){
			if(F==fila && C==columna){valido=true;
									break;}
				
		}
		return valido;
	}
	public boolean movimientoRey(char columnaOrigen, int filaOrigen, char columna,
			int fila, int itf, int itc){ 
        return (((filaOrigen+itf) == fila)&&((columnaOrigen+itc)==columna)); 
} 
	
}