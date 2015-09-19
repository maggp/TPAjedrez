package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

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

}
