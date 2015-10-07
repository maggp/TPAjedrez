package data;


import java.sql.*;

import appExceptions.ApplicationException;

public class FactoryConexion {
	
	private String dbDriver="com.mysql.jdbc.Driver";
	private String host="localhost";
	private String port="3306";
	private String user="tpajedrez";
	private String pass="tpajedrez";
	private String db="ajedrez";
	
	private Connection conn;
	private int cantCon;
	
	private FactoryConexion(){
		try {
			Class.forName(dbDriver);
			conn=null;
			cantCon=0;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private static FactoryConexion instancia;
	
	public static FactoryConexion getInstancia(){
		if (instancia==null){
			instancia = new FactoryConexion();
		}
		return instancia;
	}
	
	
	
	public Connection getConn() throws ApplicationException{
		try {
			if(conn==null || conn.isClosed()){
				conn = DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+db+"?user="+user+"&password="+pass);
				cantCon++;
			}
		} catch (SQLException e) {
			throw new ApplicationException("Error al establecer la conexion con la base de datos", e);
		}
		return conn;
	}
	
	
	public void releaseConn() throws ApplicationException{
		try {
			cantCon--;
			if(cantCon==0){
				conn.close();
			}
		} catch (SQLException e) {
			throw new ApplicationException("Error al cerrar la conexion con la base de datos", e);
		}
	}

}