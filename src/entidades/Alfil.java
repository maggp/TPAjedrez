package entidades;

public class Alfil extends Pieza {
	
	public Alfil(Posicion posicion, String color) {
		super(posicion, color);
		this.setTipoPieza("A");
	}

	@Override
	public boolean movimientoValido(Posicion destino) {
		// TODO Auto-generated method stub
		return false;
	}
	


}
