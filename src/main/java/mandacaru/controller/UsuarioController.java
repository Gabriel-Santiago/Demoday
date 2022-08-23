package mandacaru.controller;
 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mandacaru.model.Usuario;
import mandacaru.service.UsuarioService;
 
@RestController
@RequestMapping(path = "/api/usuarios")
public class UsuarioController {
 
    @Autowired
    UsuarioService service;
 
    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<Usuario>> findall() {
        return new ResponseEntity<List<Usuario>>(service.findAll(), HttpStatus.OK);
    }
 
    @GetMapping(path = "/{id}")
    public ResponseEntity<Usuario> find(@PathVariable("id") int id) {
    	Usuario usuario = service.find(id);
		
		if(usuario != null) {
			return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);	
		} else {
			return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
		}
    }
    
    @GetMapping(path = "/search")
	public ResponseEntity<Usuario> findByName(@RequestParam("nome") String nome) {
		Usuario usuario = service.findByName(nome);
		
		if(usuario != null) {
			return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);	
		} else {
			return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
		}
	}
    
    //
 
    @PostMapping()
    @PreAuthorize("hasAuthority('ADMIN')")
    public void save(@RequestBody Usuario usuario) {
        service.save(0, usuario);
    }
    
    //
 
    @PutMapping(path = "/{id}")
    public void update(@PathVariable("id") int id, @RequestBody Usuario usuario) {
        service.save(id, usuario);
    }
    
    //
 
    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable("id") int id) {
        service.delete(id);
    }
}