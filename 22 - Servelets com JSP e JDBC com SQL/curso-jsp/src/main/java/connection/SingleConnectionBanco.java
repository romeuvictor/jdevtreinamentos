package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnectionBanco {
	
	private static String banco = "jdbc:postgresql://localhost:5433/curso-jsp?autoReconnect=true";
	private static String user = "postgres";
	private static String senha = "admin";
	private static Connection connection = null;
	
	public static Connection getConnection() {
		return connection;
	}
	
	static {//Quando chamar a classe
		conectar();
	}
	
	public SingleConnectionBanco() {/*Quando tiver uma instância vai conectar*/
		conectar();
	}
	
	private static void conectar() {
	
		try {
			
			if(connection == null) {
				
				Class.forName("org.postgresql.Driver");// Carrega o drive de conexão do banco
				connection = DriverManager.getConnection(banco, user, senha);
				connection.setAutoCommit(false);//Para não efetuar alteração no banco sem nosso comando.
			
			}
	
		} catch (Exception e) {
			e.printStackTrace(); /*Mostrar qualquer erro ao conectar*/
		}
	}

}
