package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.SingleConnectionBanco;
import model.ModelLogin;

public class DAOUsuarioRepository {
	
	private Connection connection;
	
	public DAOUsuarioRepository() {
		connection = SingleConnectionBanco.getConnection();
	}

	public ModelLogin gravarUsuario(ModelLogin objeto) throws Exception {
		
			if(objeto.isNovo()) {/*Grava um novo*/
				
			String sql = "INSERT INTO model_login(login, senha, nome, email) VALUES(?, ?, ?, ?);";
			PreparedStatement preparaSql = connection.prepareStatement(sql);
			
			preparaSql.setString(1, objeto.getLogin());
			preparaSql.setString(2, objeto.getSenha());
			preparaSql.setString(3, objeto.getNome());
			preparaSql.setString(4, objeto.getEmail());

			preparaSql.execute();
			connection.commit();
			
			}else {
				String sql = "UPDATE model_login SET login=?, senha=?, nome=?, email=? where id = "+objeto.getId()+";";
				PreparedStatement prepareSql = connection.prepareStatement(sql);
				prepareSql.setString(1, objeto.getLogin());
				prepareSql.setString(2, objeto.getSenha());
				prepareSql.setString(3, objeto.getNome());
				prepareSql.setString(4, objeto.getEmail());
				
				prepareSql.executeUpdate();
				
				connection.commit();
				
			}
			
			return this.consultaUsuario(objeto.getLogin());
	}
	public List<ModelLogin> consultaUsuarioList() throws SQLException{
		
		List<ModelLogin> retorno = new ArrayList<ModelLogin>();
		
		String sql = "select * from model_login where useradmin is false; ";
		PreparedStatement statement = connection.prepareStatement(sql);
			 
		ResultSet resultado = statement.executeQuery();
		
		while(resultado.next()) {/*Percorrer as linha de resultado SQL*/
			ModelLogin modelLogin = new ModelLogin();
			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setNome(resultado.getString("nome"));
			
			retorno.add(modelLogin);
		}
		
		
		return retorno;
	}
	
	public List<ModelLogin> consultaUsuarioList (String nome) throws SQLException{
		
		List<ModelLogin> retorno = new ArrayList<ModelLogin>();
		
		String sql = "select * from model_login where upper(nome) like upper(?) and useradmin is false ";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1,"%" + nome + "%");
		 
		ResultSet resultado = statement.executeQuery();
		
		while(resultado.next()) {/*Percorrer as linha de resultado SQL*/
			ModelLogin modelLogin = new ModelLogin();
			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setNome(resultado.getString("nome"));
			
			retorno.add(modelLogin);
		}
		
		
		return retorno;
	}
	
	public ModelLogin consultaUsuario(String login) throws Exception {
		
		ModelLogin modelLogin = new ModelLogin();
		
		String sql = "SELECT * FROM model_login WHERE  upper(login) = upper('"+login+"')  and useradmin is false;";
		
		PreparedStatement statement = connection.prepareStatement(sql);
			
		ResultSet resultado = statement.executeQuery();
		
		while(resultado.next()){// Se tem resultado 
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setSenha(resultado.getString("senha"));
			modelLogin.setNome(resultado.getString("nome"));
		
		}
		return modelLogin;
	}
	
	public ModelLogin consultaUsuarioID(String id) throws Exception {
		
		ModelLogin modelLogin = new ModelLogin();
		
		String sql = "SELECT * FROM model_login WHERE  id = ? and useradmin is false;";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setLong(1, Long.parseLong(id));
			
		ResultSet resultado = statement.executeQuery();
		
		while(resultado.next()){// Se tem resultado 
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setSenha(resultado.getString("senha"));
			modelLogin.setNome(resultado.getString("nome"));
		
		}
		return modelLogin;
	}
	
	public boolean validaLogin(String login) throws Exception {
		String sql = "select count(1) > 0 as existe from model_login where upper(login) = upper('"+login+"');";
		PreparedStatement statement = connection.prepareStatement(sql);
		
		ResultSet resultado = statement.executeQuery();	
	
		resultado.next();/*Para ele entrar nos resultados do sql*/
		return resultado.getBoolean("existe");

	}
	
	public void deletarUser(String idUser) throws Exception {
		String sql = "DELETE FROM model_login WHERE id = ?  and useradmin is false;";
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		prepareSql.setLong(1, Long.parseLong(idUser));
		
		prepareSql.executeUpdate();
		connection.commit();
		
	}
}