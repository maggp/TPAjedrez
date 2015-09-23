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
	
}