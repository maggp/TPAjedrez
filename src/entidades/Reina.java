package entidades;

public class Reina extends Pieza {
	

	public Reina(Posicion posicion, String color) {
		super(posicion, color);
		this.setTipoPieza("D");
	}

	@Override
	public boolean movimientoValido(Posicion destino) {
		// TODO Auto-generated method stub
		
		
		return false;
	}

}
