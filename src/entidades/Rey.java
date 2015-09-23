package entidades;

public class Rey extends Pieza {
	
	public Rey(Posicion posicion, String color) {
		super(posicion, color);
		this.setTipoPieza("R");
		
	}

	@Override
	public boolean movimientoValido(Posicion destino) {
		// TODO Auto-generated method stub
		return false;
	}

}
