package negocio;

import appExceptions.ApplicationException;
import appExceptions.EndGameException;
import data.DataJugador;
import data.DataPartida;
import entidades.Jugador;
import entidades.Partida;
import entidades.Pieza;
import entidades.Posicion;

public class CtrlAjedrez {
	private DataPartida dp;
	private DataJugador dj;
	private Partida partida;	
	
	
	
	public CtrlAjedrez(){
		dp = new DataPartida();
		dj = new DataJugador();
	}
	
	public CtrlAjedrez(Partida partida) {
		this();
		this.partida=partida;
	}

	public Jugador identificarJugador(int dni) throws ApplicationException {
		return dj.getByDni(dni);
	}

	public Partida recuperarPartida(Jugador j1, Jugador j2) throws ApplicationException {
		partida= dp.recuperarPartida(j1, j2);
		return partida;
	}

	public void nuevaPartida(Partida p) throws ApplicationException {
		partida = p;
		this.dp.nuevaPartida(p);
	}

	public void eliminarPartida(int idPartida) throws ApplicationException {
			dp.eliminarPartida(idPartida);		
	}

	public void moverPieza(Posicion posOrigen, Posicion posDestino) throws ApplicationException {
		Pieza pieza= partida.getColPiezas().get(posOrigen);
		try{
			if (pieza.getColor().equals(partida.getTurno())){
				if (pieza.movimientoValido(posDestino)) {
					Pieza piezaObjetivo = partida.getColPiezas().get(posDestino);
					if (piezaObjetivo != null) {
							if (!piezaObjetivo.getColor().equals(partida.getTurno())) {
								partida.getColPiezas().remove(posDestino);
								//Usamos la posicion z0 para eliminar una pieza sin borrar el registro en la tabla
								Posicion posEliminado = new Posicion('z', 0);
								dp.moverPieza(piezaObjetivo, partida, posEliminado );
							} else {
								throw new ApplicationException("La posición de destino contiene una pieza del mismo jugador que está efectuando el movimiento", null);
							}
					}
					dp.moverPieza(pieza, partida, posDestino);
					pieza.setPosicion(posDestino);
					partida.getColPiezas().remove(posOrigen);
					partida.getColPiezas().put(posDestino, pieza);
					Jugador jugadorActual = null;
					if(partida.getTurno().equals("blanco")){
						partida.setTurno("negro");
						jugadorActual = partida.getJugadorBlancas();
					}else{
						partida.setTurno("blanco");
						jugadorActual = partida.getJugadorNegras();
					}
					dp.actualizarTurno(partida.getIdPartida(),partida.getTurno());
					if(piezaObjetivo!=null&&piezaObjetivo.getTipoPieza().equals("R")) {
						
						throw new EndGameException("Felicidades "+ jugadorActual.getNombre()+" "+jugadorActual.getApellido()+" ha ganado la partida!!!", null);
					}
				} else {
					throw new ApplicationException("El movimiento introducido no es válido", null);
				}
			} else {
				throw new ApplicationException("La pieza seleccionada para mover no corresponde al jugador del turno", null);
			}
		} catch (NullPointerException e){
			throw new ApplicationException("No se encontro pieza en la posicion ingresada como destino", e);
		}
	}
}
