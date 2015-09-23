package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import appExceptions.ApplicationException;
import entidades.Alfil;
import entidades.Caballo;
import entidades.Jugador;
import entidades.Partida;
import entidades.Peon;
import entidades.Pieza;
import entidades.Posicion;
import entidades.Reina;
import entidades.Rey;
import entidades.Torre;

public class DataPartida {

	public Partida recuperarPartida(Jugador j1, Jugador j2) {
		Partida partida = null;
		ResultSet rs = null;
		ResultSet rsFin = null;
		ResultSet rsPosiciones = null;
		PreparedStatement stmt = null;
		PreparedStatement stmtPosiciones = null;
		PreparedStatement stmtFin = null;
		try {
			
			//Recupero de la base de datos las partidas correspondientes  los jugadores ingresados
			
			stmt= FactoryConexion.getInstancia().getConn().prepareStatement(
					"Select * from partidas where (dni_jugador_negras=? and dni_jugador_blancas=? )"+
			        "or (dni_jugador_negras=? and dni_jugador_blancas=? )");
			stmt.setInt(1, j1.getDni());
			stmt.setInt(2, j2.getDni());
			stmt.setInt(3, j2.getDni());
			stmt.setInt(4, j1.getDni());
			rs = stmt.executeQuery();
			if(rs.next()){
				
				//Verifico que la partida no este finalizada
				stmtFin = FactoryConexion.getInstancia().getConn().prepareStatement(
						"Select * from posiciones where id_partida=? and ficha=?");
				stmtFin.setInt(1, rs.getInt("id_partida"));
				stmtFin.setString(2, "R");
				rsFin = stmtFin.executeQuery();
				boolean partidaFinalizada = false;
				while(rsFin.next()){
					if (rsFin.getInt("fila")==0||rsFin.getString("columna").toCharArray()[0]=='z') partidaFinalizada=true;
				}
				if(!partidaFinalizada){
					
					//Instancio una partida y seteo los datos recuperados
				 
					partida = new Partida();
					partida.setIdPartida(rs.getInt("id_partida"));
					partida.setTurno(rs.getString("turno"));
					
					if(j1.getDni()==rs.getInt("dni_jugador_blancas")){
						partida.setJugadorBlancas(j1);
						partida.setJugadorNegras(j2);
					}else{
						partida.setJugadorBlancas(j2);
						partida.setJugadorNegras(j1);
					}
					
					//Recupero las posiciones de las fichas correspondientes a la partida
					
					stmtPosiciones = FactoryConexion.getInstancia().getConn().prepareStatement(
							"Select * from posiciones where id_partida=?");
					stmtPosiciones.setInt(1,partida.getIdPartida());
					rsPosiciones = stmtPosiciones.executeQuery();
					HashMap<Posicion, Pieza> coleccion = new HashMap<Posicion,Pieza>();
					
					//Instancio cada pieza y la agrego a la collecion de piezas de la partida
					
					while (rsPosiciones.next()){
						int fila= rsPosiciones.getInt("fila");
						char col= rsPosiciones.getString("columna").toCharArray()[0];
						if (fila!=0 && col != 'z'){
							String color= rsPosiciones.getString("color");
							char ficha = rsPosiciones.getString("ficha").toCharArray()[0];
							Pieza pieza=null;
							switch (ficha) {
							case 'R':
								pieza= new Rey(new Posicion(col, fila), color);
								break;
							case 'D':
								pieza= new Reina(new Posicion(col, fila), color);
								break;
							case 'T':
								pieza= new Torre(new Posicion(col, fila), color);
								break;
							case 'A':
								pieza= new Alfil(new Posicion(col, fila), color);
								break;
							case 'C':
								pieza= new Caballo(new Posicion(col, fila), color);
								break;
							case 'P':
								pieza= new Peon(new Posicion(col, fila), color);
								break;
							}
							coleccion.put(pieza.getPosicion(),pieza);
						}
					}
					partida.setColPiezas(coleccion);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try {
				if(rs!=null)rs.close();
				if(stmt!=null) stmt.close();
				if(stmtPosiciones!=null) stmtPosiciones.close();
				if(stmtFin!=null) stmtFin.close();
				if(rsPosiciones != null) rsPosiciones.close();
				if(rsFin != null) rsFin.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			FactoryConexion.getInstancia().releaseConn();
		}
		return partida;
	}
	
	public void nuevaPartida(Partida partida) throws ApplicationException{
		PreparedStatement stmtPartida = null;
		PreparedStatement stmtPieza = null;
		ResultSet rs = null;
		try {
			//Inserto nueva partida
			FactoryConexion.getInstancia().getConn().setAutoCommit(false);
			stmtPartida = FactoryConexion.getInstancia().getConn().prepareStatement(
					"Insert into partidas(dni_jugador_blancas,dni_jugador_negras) values (?,?)",
					PreparedStatement.RETURN_GENERATED_KEYS
					);
			stmtPartida.setInt(1, partida.getJugadorBlancas().getDni());
			stmtPartida.setInt(2, partida.getJugadorNegras().getDni());
			stmtPartida.execute();
			
			//Recupero el id asignado por la bd
			rs = stmtPartida.getGeneratedKeys();
			if(rs!=null && rs.next()){
				partida.setIdPartida(rs.getInt(1));
			}
			
			//inserto las piezas en la bd
			stmtPieza = FactoryConexion.getInstancia().getConn().prepareStatement(
					"Insert into posiciones(id_partida, ficha, color, fila, columna)"
					+ "values (?,?,?,?,?);"
					);
			for (int fila = 1; fila <= 8; fila++) {
				for (char col = 'a';  col <= 'h'; col++) {
					Pieza p = partida.getColPiezas().get(new Posicion(col, fila));
					if(p!=null){
						stmtPieza.setInt(1, partida.getIdPartida());
						stmtPieza.setString(2,p.getTipoPieza() );
						stmtPieza.setString(3, p.getColor());
						stmtPieza.setInt(4, fila);
						stmtPieza.setString(5, String.valueOf(col));
						stmtPieza.execute();
					}
				}
			}
			FactoryConexion.getInstancia().getConn().commit();
			
		} catch (SQLException e) {
			try {
				FactoryConexion.getInstancia().getConn().rollback();
			} catch (SQLException e1) {
				throw new ApplicationException("Error al recuperar estado en la base de datos", e);
			}
			throw new ApplicationException("Error al registrar nueva partida en la base de datos", e);
		}
		finally {
			try {
				if(stmtPartida!=null) stmtPartida.close();
				if(stmtPieza!=null) stmtPieza.close();
				if(rs!=null) rs.close();
				FactoryConexion.getInstancia().getConn().setAutoCommit(true);
			} catch (SQLException e) {
				throw new ApplicationException("Error al cerrar conexiones con la base de datos", e);
			}
		}

	}
   // ELIMINO LAS POSICIONES Y LUEGO ELIMINO LA PARTIDA
	public void eliminarPartida(int idPartida) {
		PreparedStatement stmt=null;
		
		//  ELIMINO
		//    LAS 
		// POSICIONES
		
		try {
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement(
					  "delete from posiciones where id_partida=?"
					);
			stmt.setInt(1, idPartida);
			stmt.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			
			try {
				if(stmt != null) stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			FactoryConexion.getInstancia().releaseConn();
		}
	
		
		// ELIMINO
		//   LA
		// PARTIDA
		
		try {
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement(
					  "delete from partidas where id_partida=?"
					);
			stmt.setInt(1, idPartida);
			stmt.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			
			try {
				if(stmt != null) stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			FactoryConexion.getInstancia().releaseConn();
		}
	}

}
