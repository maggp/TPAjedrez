package negocio;

import data.DataJugador;
import data.DataPartida;
import entidades.Jugador;
import entidades.Partida;

public class CtrlAjedrez {
	private DataPartida dp;
	private DataJugador dj;
	private Partida partida;
	
	public Jugador identificarJugador(int dni) {
		return dj.getByDni(dni);
	}
}
