package negocio;

import data.DataJugador;
import data.DataPartida;
import entidades.Jugador;
import entidades.Partida;

public class CtrlAjedrez {
	private DataPartida dp;
	private DataJugador dj;
	private Partida partida;
	
	public CtrlAjedrez(){
		dp = new DataPartida();
		dj = new DataJugador();
	}
	
	public Jugador identificarJugador(int dni) {
		return dj.getByDni(dni);
	}

	public Partida recuperarPartida(Jugador j1, Jugador j2) {
		// TODO Auto-generated method stub
		return dp.recuperarPartida(j1,j2);
	}
}
