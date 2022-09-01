package mandacaru.controller;
 
import java.util.List;

import org.apache.http.client.methods.HttpPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import mandacaru.model.Usuario;
import mandacaru.service.UsuarioService;

@Tag(name = "Usuario")
@RestController
@RequestMapping(path = "/api/usuarios")
@SecurityRequirement(name = "oauth")
public class UsuarioController {
 
    @Autowired
    UsuarioService service;
 
    // Get
    
    @Operation(summary = "Get all Users")
    @ApiResponse(responseCode = "200", description = "Found the users",
	    content = {@Content(mediaType = "application/json", array =
	    @ArraySchema(schema = @Schema(implementation = HttpPost.class)))})
    @ApiResponse(responseCode = "401", description = "unauthorized user",
    content = @Content)
    @GetMapping
    public ResponseEntity<List<Usuario>> findall() {
        return new ResponseEntity<List<Usuario>>(service.findAll(), HttpStatus.OK);
    }
    
    @Operation(summary = "Get one User by ID")
    @ApiResponse(responseCode = "200", description = "Found the user",
    content = {@Content(mediaType = "application/json", array =
    @ArraySchema(schema = @Schema(implementation = HttpPost.class)))})
    @ApiResponse(responseCode = "404", description = "Not Found this user",
    content = @Content)
    @GetMapping(path = "/{id}")
    public ResponseEntity<Usuario> find(@Parameter(description = "id of a user to be fetched from the database") @PathVariable("id") int id) {
    	Usuario usuario = service.find(id);
		
		if(usuario != null) {
			return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);	
		} else {
			return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
		}
    }
    
    @ApiResponse(responseCode = "200", description = "Found the user",
    content = {@Content(mediaType = "application/json", array =
    @ArraySchema(schema = @Schema(implementation = HttpPost.class)))})
    @ApiResponse(responseCode = "404", description = "Not Found this user",
    content = @Content)
    @Operation(summary = "Get one User by email")
    @GetMapping(path = "/search")
	public ResponseEntity<Usuario> findByName(@Parameter(description = "email of a user to be fetched from the database")  @RequestParam("nome") String email) {
		Usuario usuario = service.findByEmail(email);
		
		if(usuario != null) {
			return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);	
		} else {
			return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
		}
	}
    
    // Post
    
    @ApiResponse(responseCode = "200", description ="Added a new user",
    	    content = {@Content(mediaType = "application/json", array =
    	    @ArraySchema(schema = @Schema(implementation = HttpPost.class)))})
    @ApiResponse(responseCode = "500", description = "An exception was generated",
    content = @Content)
    @Operation(summary = "Save one user")
    @PostMapping()
    public void save(@RequestBody Usuario usuario) {
        service.save(0, usuario);
    }
    
    // Put
    
    @ApiResponse(responseCode = "200", description ="Updated a user",
    	    content = {@Content(mediaType = "application/json", array =
    	    @ArraySchema(schema = @Schema(implementation = HttpPost.class)))})
    @ApiResponse(responseCode = "500", description = "An exception was generated",
    content = @Content)
    @Operation(summary = "Uptade one user")
    @PutMapping(path = "/{id}")
    public void update(@Parameter(description = "id of a user to be fetched from the database")  @PathVariable("id") int id, @RequestBody Usuario usuario) {
        service.save(id, usuario);
    }
    
    // Delete
    
    @ApiResponse(responseCode = "200", description ="Deleted a user",
    	    content = {@Content(mediaType = "application/json", array =
    	    @ArraySchema(schema = @Schema(implementation = HttpPost.class)))})
    @ApiResponse(responseCode = "500", description = "An exception was generated",
    content = @Content)
    @Operation(summary = "Delete one user")
    @DeleteMapping(path = "/{id}")
    public void delete(@Parameter(description = "id of a user to be fetched from the database") @PathVariable("id") int id) {
        service.delete(id);
    }
}