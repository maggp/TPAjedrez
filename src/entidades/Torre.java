package entidades;

public class Torre extends Pieza {
	

	public Torre(Posicion posicion, String color) {
		super(posicion, color);
		this.setTipoPieza("T");
	}

	@Override
	public boolean movimientoValido(Posicion destino) {
		// TODO Auto-generated method stub
		return false;
	}

}
