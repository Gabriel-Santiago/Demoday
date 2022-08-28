package mandacaru.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
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

	@PostMapping("/uploadimagem") 
	public String uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
		
		Imagem teste = new Imagem();
		teste.setFoto(file.getBytes());
		teste.setTipo(file.getContentType());
		
		service.save(teste, 2);
		
        return "deu certo";
    }
	
	@GetMapping("/imagem/info/{id}") 
	public Imagem getImageInfo(@PathVariable("id") int id) {
		
        return service.find(id);
        
    }
	
	@GetMapping("/imagem/{id}") 
	public byte[] getImage(@PathVariable("id") int id) {
		
        return service.find(id).getFoto();
        
    }
	@GetMapping(path = {"/imagem/ver/{id}"})
    public ResponseEntity<byte[]> getImageVer(@PathVariable("id") int id){

        Imagem img = service.find(id);

        return ResponseEntity
                .ok()
                .contentType(MediaType.valueOf(img.getTipo()))
                .body(img.getFoto());
    }
	
	


}