package servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import model.ModelLogin;

import java.io.IOException;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.DAOUsuarioRepository;


@MultipartConfig
@WebServlet(urlPatterns = {"/ServletUsuarioController"})
public class ServletUsuarioController extends ServletGenericUtil {
	private static final long serialVersionUID = 1L;
	
	private DAOUsuarioRepository daoUsuarioRepository = new DAOUsuarioRepository();
       

    public ServletUsuarioController() {     
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			String acao = request.getParameter("acao");
			
			if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletar")) {
				String idUser = request.getParameter("id");
			
				daoUsuarioRepository.deletarUser(idUser);
				
				List<ModelLogin> modelLogins = daoUsuarioRepository.consultaUsuarioList(super.getUserLogado(request));
				request.setAttribute("modelLogins", modelLogins);	
				
				request.setAttribute("msg", "Excluído com sucesso!");
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
			}else if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletarajax")) {
			
				String idUser = request.getParameter("id");
				
				daoUsuarioRepository.deletarUser(idUser);
				
				response.getWriter().write("Excluído com sucesso!");							
				
			}else if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarUserAjax")) {

				String nomeBusca = request.getParameter("nomeBusca");
				
				List<ModelLogin> dadosJsonUser = daoUsuarioRepository.consultaUsuarioList(nomeBusca, super.getUserLogado(request));
				
				ObjectMapper mapper = new ObjectMapper();
				String json = mapper.writeValueAsString(dadosJsonUser);
				response.getWriter().write(json);
				
			}else if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarEditar")) {
				
				String id = request.getParameter("id");
				ModelLogin modelLogin = daoUsuarioRepository.consultaUsuarioID(id,super.getUserLogado(request));
				
				List<ModelLogin> modelLogins = daoUsuarioRepository.consultaUsuarioList(super.getUserLogado(request));
				request.setAttribute("modelLogins", modelLogins);	
				
				request.setAttribute("msg", "Usuário em edição");
				request.setAttribute("modolLogin", modelLogin);	
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
			
			}else if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("listarUser")) {
				
				List<ModelLogin> modelLogins = daoUsuarioRepository.consultaUsuarioList(super.getUserLogado(request));
				
				request.setAttribute("msg", "Usuários carregados");
				request.setAttribute("modelLogins", modelLogins);	
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
				
			}else if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("downloadFoto")) {	
				String idUser = request.getParameter("id");
				
				ModelLogin modelLogin = daoUsuarioRepository.consultaUsuarioID(idUser, super.getUserLogado(request));
				
				if(modelLogin.getFotoUser() != null && !modelLogin.getFotoUser().isEmpty()) {
					
					response.setHeader("Content-Disposition", "attachment;filename=arquivo." + modelLogin.getExtencaoFoto());
					new Base64();
					response.getOutputStream().write(Base64.decodeBase64(modelLogin.getFotoUser().split("\\,")[1]) );
				}
				
		
			}else {
				List<ModelLogin> modelLogins = daoUsuarioRepository.consultaUsuarioList (super.getUserLogado(request));
				request.setAttribute("modelLogins", modelLogins);	
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
				
			}
		}catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		try {
			
			String msg = "Operação realizada com sucesso!";
		
			String id = request.getParameter("id");
			String nome = request.getParameter("nome");
			String email = request.getParameter("email");
			String login = request.getParameter("login");
			String senha = request.getParameter("senha");
			String perfil = request.getParameter("perfil");
			String sexo = request.getParameter("sexo");
			String cep = request.getParameter("cep");
			String logadouro = request.getParameter("logadouro");
			String bairro = request.getParameter("bairro");
			String localidade = request.getParameter("localidade");
			String uf = request.getParameter("uf");			
			String numero = request.getParameter("numero");
			
			ModelLogin modelLogin = new ModelLogin();
			
			modelLogin.setId(id != null && !id.isEmpty() ? Long.parseLong(id) : null);	
			modelLogin.setNome(nome);
			modelLogin.setEmail(email);
			modelLogin.setLogin(login);
			modelLogin.setSenha(senha);
			modelLogin.setPerfil(perfil);
			modelLogin.setSexo(sexo);
			modelLogin.setCep(cep);
			modelLogin.setLogadouro(logadouro);
			modelLogin.setBairro(bairro);
			modelLogin.setLocalidade(localidade);
			modelLogin.setUf(uf);
			modelLogin.setNumero(numero);
			
			if(ServletFileUpload.isMultipartContent(request)) {
				
				Part part = request.getPart("fileFoto");/*Pega a foto da tela*/
				
				if (part.getSize() > 0) {
					byte[] foto = IOUtils.toByteArray(part.getInputStream());// converte a image para byte
					String imagemBase64 = "data:image/" + part.getContentType().split("\\/")[1] + ";base64," + new Base64().encodeBase64String(foto);
					
					modelLogin.setFotoUser(imagemBase64);
					modelLogin.setExtencaoFoto(part.getContentType().split("\\/")[1]);
				}
				
			}
			
			
			if(daoUsuarioRepository.validaLogin(modelLogin.getLogin()) && modelLogin.getId() == null) {
				msg = "Já existe usuarío com esse login, informe outro login";
			}else {
				if(modelLogin.isNovo()) {
					msg="Gravado com sucesso!";
				}else {
					msg="Atualizado com sucesso!";
				}
				modelLogin = daoUsuarioRepository.gravarUsuario(modelLogin, super.getUserLogado(request));
			}	
			List<ModelLogin> modelLogins = daoUsuarioRepository.consultaUsuarioList(super.getUserLogado(request));
			request.setAttribute("modelLogins", modelLogins);	
			request.setAttribute("msg", msg);
			request.setAttribute("modolLogin", modelLogin);	
			request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
		
		}catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
		}
		
	}

}
