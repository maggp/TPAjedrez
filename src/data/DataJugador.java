package data;

import java.sql.*;

import appExceptions.ApplicationException;
import entidades.Jugador;

public class DataJugador {

	public Jugador getByDni(int dni) throws ApplicationException{
		ResultSet rs=null;
		PreparedStatement stmt=null;
		Jugador j=null;
		try {
			stmt = 	FactoryConexion.getInstancia().getConn().prepareStatement(
					"select  dni, nombre, apellido from jugadores where dni = ?"
					);
			stmt.setInt(1, dni);
			rs = stmt.executeQuery();
			if(rs !=null && rs.next()){
				j=new Jugador();
				j.setDni(rs.getInt("dni"));
				j.setNombre(rs.getString("nombre"));
				j.setApellido(rs.getString("apellido"));
				
			}
		} catch (SQLException e) {
			throw new ApplicationException("Error al recuperar jugador de la base de datos", e);
		}
		finally
		{
			try {
				if(rs!=null)rs.close();
				if(stmt!=null) stmt.close();
			} catch (SQLException e) {
				throw new ApplicationException("Error al liberar recursos de la base de datos", e);
			}
			FactoryConexion.getInstancia().releaseConn();
		}
		return j;
	}
}
