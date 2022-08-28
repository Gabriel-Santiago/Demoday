package mandacaru.controller;
 
import java.io.IOException;
import java.util.List;

import org.apache.http.client.methods.HttpPost;
import org.apache.tomcat.util.json.ParseException;
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
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import mandacaru.Pdt;
import mandacaru.model.Imovel;
import mandacaru.service.ImovelService; 

@Tag(name = "Imovel")
@RestController
@RequestMapping(path = "/api")
@SecurityRequirement(name = "oauth")
public class ImovelController {
 
    @Autowired
    ImovelService service;
    
    @ApiResponse(responseCode = "200", description ="Find all user properties",
    	    content = {@Content(mediaType = "application/json", array =
    	    @ArraySchema(schema = @Schema(implementation = HttpPost.class)))})
    @Operation(summary = "Find all properties of a user ")
    @GetMapping(path = "/usuarios/{id}/imoveis")
    public ResponseEntity<List<Imovel>> findallofuser(@Parameter(description = "id of a user to be fetched from the database") @PathVariable(value = "id") int id) {
        return new ResponseEntity<List<Imovel>>(service.findAllOfUser(id), HttpStatus.OK);
    }
    
    @ApiResponse(responseCode = "200", description = "Found all the properties",
    	    content = {@Content(mediaType = "application/json", array =
    	    @ArraySchema(schema = @Schema(implementation = HttpPost.class)))})
    @ApiResponse(responseCode = "401", description = "No authorization",
    		content = @Content)
    @Operation(summary = "Find all properties")
    @GetMapping(path = "/imoveis")
    public ResponseEntity<List<Imovel>> findall() {
        return new ResponseEntity<List<Imovel>>(service.findAll(), HttpStatus.OK);
    }
    
    @ApiResponse(responseCode = "200", description = "Found the propertie",
    	    content = {@Content(mediaType = "application/json", array =
    	    @ArraySchema(schema = @Schema(implementation = HttpPost.class)))})
    @ApiResponse(responseCode = "404", description = "property not found",
    	    content = @Content)
    @Operation(summary = "Find property by ID")
    @GetMapping(path = "/imoveis/{id}")
    public ResponseEntity<Imovel> find(@Parameter(description = "id of a propertie to be fetched from the database") @PathVariable("id") int id) {
    	Imovel imovel = service.find(id);
		
		if(imovel != null) {
			return new ResponseEntity<Imovel>(imovel, HttpStatus.OK);	
		} else {
			return new ResponseEntity<Imovel>(HttpStatus.NOT_FOUND);
		}
    }
    
    @ApiResponse(responseCode = "200", description = "User-added property",
    	    content = {@Content(mediaType = "application/json", array =
    	    @ArraySchema(schema = @Schema(implementation = HttpPost.class)))})
    @ApiResponse(responseCode = "500", description = "An exception was generated",
    		content = @Content)
    @ApiResponse(responseCode = "400", description = "Incorrect request syntax",
			content = @Content)
    @Operation(summary = "Save the property")
    @PostMapping(path = "/usuarios/{id}/imoveis")
    public void save(@Parameter(description = "id of a user to be fetched from the database")  @PathVariable("id") int usuario_id,@RequestBody Imovel imovel) throws ParseException, IOException, InterruptedException {
    		
        service.save(usuario_id, imovel);
    }
    
    @Operation(summary = "teste")
    @PostMapping(path = "/teste")
    public String teste() throws ParseException {
    	Pdt t = new Pdt();
    	t.patch(t.pdtToken(), "a13565c2-0908-4dff-adb3-a5a66826bce9");
        return "show";
    }
    
    @ApiResponse(responseCode = "200", description = "Updated property",
    	    content = {@Content(mediaType = "application/json", array =
    	    @ArraySchema(schema = @Schema(implementation = HttpPost.class)))})
    @ApiResponse(responseCode = "500", description = "An exception was generated",
    		content = @Content)
    @ApiResponse(responseCode = "400", description = "Incorrect request syntax",
			content = @Content)
    @Operation(summary = "Update a property")
    @PutMapping(path = "/imoveis/{id}")
    public void update(@Parameter(description = "id of a propertie to be fetched from the database") @PathVariable("id") int id, @RequestBody Imovel imovel) {
        service.update(id, imovel);
    }
 
    @ApiResponse(responseCode = "200", description = "Property deleted",
    		content = {@Content(mediaType = "application/json", array =
    	    @ArraySchema(schema = @Schema(implementation = HttpPost.class)))})
    @ApiResponse(responseCode = "500", description = "An exception was generated",
    		content = @Content)
    @Operation(summary = "Delete a property")
    @DeleteMapping(path = "/imoveis/{id}")
    public void delete(@Parameter(description = "id of a propertie to be fetched from the database") @PathVariable("id") int id) {
        service.delete(id);
    }
}