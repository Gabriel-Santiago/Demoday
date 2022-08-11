package mandacaru.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mandacaru.model.Imovel;
import mandacaru.model.Usuario;
import mandacaru.repository.ImovelRepository;
import mandacaru.repository.UsuarioRepository;

@Service
public class ImovelService {

	@Autowired
	ImovelRepository imovelRepository;
	
	@Autowired
	UsuarioRepository usuarioRepository;

	public void update(int id, Imovel entity) {
		Imovel imovel = find(id);		
		imovel.setTitulo(entity.getTitulo());
		
		imovelRepository.save(imovel);				
	}
	
	public void save(int usuario_id, Imovel entity) {
		Usuario usuario = usuarioRepository.findById(usuario_id).get();
		entity.setUsuario(usuario);
		imovelRepository.save(entity);				
	}

	public void delete(int id) {
		Imovel imovel = find(id);
		imovelRepository.delete(imovel);
	}

	public Imovel find(int id) {
		if (id < 1) {
			return null;
		}
		Optional<Imovel> imovel = imovelRepository.findById(id);

		if (imovel.isPresent()) {
			return imovel.get();
		}
		return null;
	}

	public List<Imovel> findAll(int usuario_id) {
		return imovelRepository.findByUsuarioId(usuario_id);
	}
}
