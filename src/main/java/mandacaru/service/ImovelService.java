package mandacaru.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mandacaru.Pdt;
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
	
	public void save(int usuario_id, Imovel entity) throws ParseException, IOException {
		Pdt pdt = new Pdt();
		String token = pdt.pdtToken();
    	String processId = pdt.pdtProcess(token);
    	String documentId = pdt.pdtDocument(token, processId);
    	Usuario usuario = usuarioRepository.findById(usuario_id).get();
    	
    	pdt.pdtUpDocument(token, processId, documentId);
    	
    	while(!pdt.pdtCheckDocument(token, processId, documentId).equals("DONE")) {
    		try {
    		    Thread.sleep(1 * 1000);
    		} catch (InterruptedException ie) {
    		    Thread.currentThread().interrupt();
    		}
    	}
    	
    	pdt.patch(token, processId);
    	
		entity.setUsuario(usuario);
		entity.setDocumento(documentId);
		entity.setProcesso(processId);
		entity.setStatus(pdt.pdtCheckProcess(token, processId));
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

	public List<Imovel> findAllOfUser(int usuario_id) {
		return imovelRepository.findByUsuarioId(usuario_id);
	}
	
	public List<Imovel> findAll() {
		return imovelRepository.findAll();
	}
}
