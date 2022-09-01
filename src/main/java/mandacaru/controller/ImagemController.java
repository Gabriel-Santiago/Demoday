package mandacaru.controller;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
	
	// Get
	
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
    
    // Post
    
	@PostMapping("/imoveis/{id}/imagens") 
	public void uploadImage(@PathVariable("id") int id,@RequestParam("files") MultipartFile[] files) throws IOException {
		
	    Arrays.asList(files).stream().forEach(file -> {
	    	Imagem teste = new Imagem();
	    	
			try {
				teste.setFoto(file.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			teste.setTipo(file.getContentType());
			service.save(teste, id);
	    });
    }
	
	// Delete
	
	@DeleteMapping("/imagens/{id}")
	public void deleteImage(@PathVariable("id") int id) {
		service.delete(id);
	}
	


}