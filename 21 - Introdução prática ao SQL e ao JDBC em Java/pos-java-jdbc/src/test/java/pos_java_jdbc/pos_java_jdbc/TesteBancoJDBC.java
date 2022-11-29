package pos_java_jdbc.pos_java_jdbc;
import java.sql.SQLException;
import java.util.List;

import javax.swing.plaf.synth.SynthOptionPaneUI;

import org.junit.Test;

import DAO.UserPosDAO;
import conexaojdbc.SingleConnection;
import model.BeanUserFone;
import model.Telefone;
import model.Userposjava;

public class TesteBancoJDBC {
	
	@Test
	public void initBanco() {
		UserPosDAO userPosDAO = new UserPosDAO();
		Userposjava userposjava =  new Userposjava();
		
		userposjava.setNome("Romeu Teste");
		userposjava.setEmail("email@gmail.com");
		
		userPosDAO.salvar(userposjava);
	}

	@Test
	public void initListar() {
		UserPosDAO dao = new UserPosDAO();
		try {
			List<Userposjava> list = dao.listar();

			for (Userposjava userposjava : list) {
				System.out.println(userposjava);
				System.out.println("-----------------------------------------------");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@Test
	public void initBuscar() {
		UserPosDAO dao = new UserPosDAO();

		try {
			Userposjava userposjava = dao.buscar(5L);
			System.out.println(userposjava);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void initAtualizar() {
		UserPosDAO dao = new UserPosDAO();

		try {
			Userposjava objetoBanco = dao.buscar(5L);
			objetoBanco.setNome("Nome mudado com metódo atualizar");
			dao.atualizar(objetoBanco);
			System.out.println(objetoBanco);
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}
	
	@Test
	public void deletar() {
		try {

			UserPosDAO dao = new UserPosDAO();
			dao.deletar(5L);
		} catch (Exception e) {

		}
	}
	
	@Test
	public void testeInsertTelefone() {
		Telefone telefone = new Telefone();

		telefone.setNumero("(31)- 1122333664");
		telefone.setTipo("celular");
		telefone.setUsuario(14L);

		UserPosDAO dao = new UserPosDAO();
		dao.salvarTelefone(telefone);
	}
	
	@Test
	public void testeCarregaFoneUser() {
		UserPosDAO dao = new UserPosDAO();
		
		List<BeanUserFone> beanUserFones = dao.listarUserFone(15L);
		
		for(BeanUserFone beanUserFone : beanUserFones) {
			System.out.println(beanUserFone);
			System.out.println("----------------------------------------");
		}
	}
	
	@Test
	public void testeDeleteUserFone() {
		UserPosDAO dao = new UserPosDAO();
		dao.deleteFonesPorUser(15L);
		
	}
}
