package mandacaru.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import mandacaru.model.Usuario;
import mandacaru.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	UsuarioRepository usuarioRepository;
	
	// relacionado save

	public void save(int id, Usuario entity) {
		if(id != 0) {
			entity.setId(id);
		}
		entity.setSenha(new BCryptPasswordEncoder().encode(entity.getSenha()));
		usuarioRepository.save(entity);
	}
	
	// relacionado find

	public Usuario find(int id) {
		if (id < 1) {
			return null;
		}
		Optional<Usuario> usuario = usuarioRepository.findById(id);

		if (usuario.isPresent()) {
			return usuario.get();
		}
		return null;
	}

	public List<Usuario> findAll() {
		return usuarioRepository.findAll();
	}
	
	public Usuario findByEmail(String str) {
		if (str.length() < 3) {
			return null;
		}
		return usuarioRepository.findByEmail(str);
	}
	
	// relacionado delete
	
	public void delete(int id) {
		Usuario usuario = find(id);
		usuarioRepository.delete(usuario);
	}

}
