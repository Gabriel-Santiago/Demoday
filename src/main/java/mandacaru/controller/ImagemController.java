package mandacaru.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import mandacaru.model.Imagem;
import mandacaru.service.ImagemService;

@RestController
@RequestMapping(path = "/api")
public class ImagemController {
	
	@Autowired
    ImagemService service;

	@PostMapping("/imoveis/{id}/imagens") 
	public void uploadImage(@PathVariable("id") int id,@RequestParam("file") MultipartFile file) throws IOException {
		
		Imagem teste = new Imagem();
		teste.setFoto(file.getBytes());
		teste.setTipo(file.getContentType());
		
		service.save(teste, id);
    }
	
	@GetMapping("/imoveis/{id}/imagens") 
	public ResponseEntity<byte[]> getImageofimovel (@PathVariable("id") int id){
		
		Imagem img = service.findAllOfImovel(id);

		return ResponseEntity
                .ok()
                .contentType(MediaType.valueOf(img.getTipo()))
                .body(img.getFoto());
    }
	
	@GetMapping("/imagem/info/{id}") 
	public Imagem getImageInfo(@PathVariable("id") int id) {
		
        return service.find(id);
        
    }
	
	@GetMapping("/imagem/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable("id") int id){

        Imagem img = service.find(id);

        return ResponseEntity
                .ok()
                .contentType(MediaType.valueOf(img.getTipo()))
                .body(img.getFoto());
    }
	
	


}