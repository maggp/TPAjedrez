package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entidades.Jugador;
import entidades.Partida;

public class DataPartida {

	public Partida recuperarPartida(Jugador j1, Jugador j2) {
		Partida partida=null;
		ResultSet rs=null;
		PreparedStatement stmt=null;
		try {
			stmt= FactoryConexion.getInstancia().getConn().prepareStatement(
					"Select * from partidas where (dni_jugador_negras=? and dni_jugador_blancas=? )"+
			        "or (dni_jugador_negras=? and dni_jugador_blancas=? )");
			stmt.setInt(1, j1.getDni());
			stmt.setInt(2, j2.getDni());
			stmt.setInt(3, j2.getDni());
			stmt.setInt(4, j1.getDni());
			rs = stmt.executeQuery();
			if(rs.next()){
				partida = new Partida();
				partida.setIdPartida(rs.getInt("id_partida"));
				
				if(j1.getDni()==rs.getInt("dni_jugador_blancas")){
					partida.setJugadorBlancas(j1);
					partida.setJugadorNegras(j2);
				}else{
					partida.setJugadorBlancas(j2);
					partida.setJugadorNegras(j1);
				}
				
				PreparedStatement stmtPosiciones = FactoryConexion.getInstancia().getConn().prepareStatement(
						"Select * from posiciones where id_partida=?");
				stmtPosiciones.setInt(1,partida.getIdPartida());
				ResultSet rsPosiciones = stmtPosiciones.executeQuery();
				while (rsPosiciones.next()){
					int fila= rsPosiciones.getInt("fila");
					char col= rsPosiciones.getString("columna").toCharArray()[0];
					if (fila!=0 && col != 'z'){
						
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		return partida;
	}

}
