package curso.api.rest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import curso.api.rest.model.Usuario;
import curso.api.rest.repository.UsuarioRepository;

@RestController /*Arquitetura REST*/
@RequestMapping(value = "/usuario")
public class IndexController {
	
	@Autowired /* Se Fosse CDI seria @Inject*/
	private UsuarioRepository usuarioRepository;
	
	/*Serviço RestFull*/
	@GetMapping(value = "/{id}")
	public ResponseEntity<Usuario> init(@PathVariable (value = "id") Long id) {
		
		Optional<Usuario> usuario = usuarioRepository.findById(id);

		return new ResponseEntity<Usuario>(usuario.get(), HttpStatus.OK);		
	}
	
	@GetMapping(value = "/")
	public ResponseEntity<List<Usuario>> usuario(){
	
		List<Usuario> list = (List<Usuario>) usuarioRepository.findAll(); 
		return new ResponseEntity<List<Usuario>>(list, HttpStatus.OK);
		
	}

}
