package mandacaru.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mandacaru.service.util.Pdt;
import mandacaru.model.Imovel;
import mandacaru.model.Usuario;
import mandacaru.repository.ImovelRepository;
import mandacaru.repository.UsuarioRepository;
import mandacaru.service.util.PdfGenerator;

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
	
	public void status(Imovel entity, Pdt pdt, String token) throws ParseException {
		if(pdt.pdtCheckProcess(token, entity.getProcesso()).equals("DONE")) {
			entity.setStatus("Pronto");
		}
		imovelRepository.save(entity);				
	}
	
	public String save(int usuario_id, Imovel entity) throws ParseException, IOException {
		Pdt pdt = new Pdt();
		String token = pdt.pdtToken();
    	String processId = pdt.pdtProcess(token);
    	String documentId = pdt.pdtDocument(token, processId);
    	Usuario usuario = usuarioRepository.findById(usuario_id).get();
    	
    	entity.setUsuario(usuario);    	

    	pdt.pdtUpDocument(token, processId, documentId,new PdfGenerator().criarPdf(entity));
    	
    	while(!pdt.pdtCheckDocument(token, processId, documentId).equals("DONE")) {
    		try {
    		    Thread.sleep(1 * 1000);
    		} catch (InterruptedException ie) {
    		    Thread.currentThread().interrupt();
    		}
    	}
    	
    	pdt.patch(token, processId);
    	
		entity.setProcesso(processId);
		entity.setStatus("Pendente");
		entity = imovelRepository.save(entity);
		imovelRepository.flush();
		return Integer.toString(entity.getId());
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
		Pdt pdt = new Pdt();
		String token = null;
		try {
			token = pdt.pdtToken();
		} catch (ParseException e) {}
		
		List<Imovel> imoveis = imovelRepository.findByUsuarioId(usuario_id);
		
		for (Imovel imovel : imoveis) {
			try {
				status(imovel,pdt,token);
			} catch (ParseException e) {}
			
		}
		return imovelRepository.findByUsuarioId(usuario_id);
	}
	
	public List<Imovel> findAll() {
		return imovelRepository.findAll();
	}
	
	public List<Imovel> findAllDone() {
		
		
		return imovelRepository.findByStatus("Pronto");
	}
}
