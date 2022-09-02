package mandacaru.controller;

import java.io.IOException;
import java.util.Arrays;

import org.apache.http.client.methods.HttpPost;
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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import mandacaru.model.Imagem;
import mandacaru.service.ImagemService;

@Tag(name = "Imagens")
@RestController
@RequestMapping(path = "/api")
@SecurityRequirement(name = "oauth")
public class ImagemController {
	
	@Autowired
    ImagemService service;
	
	// Get
	
	@ApiResponse(responseCode = "200", description ="Returned data from an image",
    	    content = {@Content(mediaType = "application/json", array =
    	    @ArraySchema(schema = @Schema(implementation = HttpPost.class)))})
    @Operation(summary = "find an image ")
	@GetMapping("/imagem/info/{id}") 
	public Imagem getImageInfo(@Parameter(description = "image id") @PathVariable("id") int id) {
		
        return service.find(id);
        
    }
	
	@ApiResponse(responseCode = "200", description ="Found an image with the given id",
    	    content = {@Content(mediaType = "application/json", array =
    	    @ArraySchema(schema = @Schema(implementation = HttpPost.class)))})
    @Operation(summary = "find an image ")
	@GetMapping("/imagem/{id}")
    public ResponseEntity<byte[]> getImage(@Parameter(description = "image id") @PathVariable("id") int id){

        Imagem img = service.find(id);

        return ResponseEntity
                .ok()
                .contentType(MediaType.valueOf(img.getTipo()))
                .body(img.getFoto());
    }
    
    // Post
    
    @ApiResponse(responseCode = "200", description ="Image upload done",
    	    content = {@Content(mediaType = "application/json", array =
    	    @ArraySchema(schema = @Schema(implementation = HttpPost.class)))})
    @Operation(summary = "Upload an image ")
	@PostMapping("/imoveis/{id}/imagens") 
	public void uploadImage(@Parameter(description = "image id") @PathVariable("id") int id,@RequestParam("files") MultipartFile[] files) throws IOException {
		
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
	
    @ApiResponse(responseCode = "200", description ="Image Deleted",
    	    content = {@Content(mediaType = "application/json", array =
    	    @ArraySchema(schema = @Schema(implementation = HttpPost.class)))})
    @Operation(summary = "Delete an image ")
	@DeleteMapping("/imagens/{id}")
	public void deleteImage(@Parameter(description = "image id") @PathVariable("id") int id) {
		service.delete(id);
	}
	


}