package mandacaru.Atividade_2.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import mandacaru.controller.UsuarioController;
import mandacaru.model.Imovel;
import mandacaru.model.Usuario;
import mandacaru.service.UsuarioService;

public class UsuarioControllerTest {

	// Usuario
	private static final int ID = 1;
	private static final String NOME = "Fiódor";
	private static final String EMAIL = "teste@gmail.com";
	private static final String SENHA = "senha123";
	private static final String CPF = "123.456.789-00";
	private static final String TELEFONE = "(99) 99999-9999";
	private static final String ENDERECO = "Av. São Paulo";
	
	// Imovel
	private static final int ID_1 = 1;
	private static final String TITULO = "Mansão UFC";
	private static final String ENDERECO_1 = "Av. Ceará";
	private static final String STATUS = "Pendente";
	private static final double METROS_QUADRADOS_DE_TERRENO = 306.90;
	private static final int QUANTIDADE_DE_QUARTOS = 6;
	private static final int QUANTIDADE_DE_BANHEIROS = 5;
	private static final double PRECO = 720963.81;
	private static final int QUANTIDADE_DE_VAGAS_DE_GARAGEM = 8;
	
	@InjectMocks
	private UsuarioController controller;
	
	@Mock
	private UsuarioService service;
	
	private Usuario usuario;
	
	private List<Imovel> listImovel;
	
	private void start() {
		Imovel imovel = new Imovel();
		imovel.setId(ID_1);
		imovel.setTitulo(TITULO);
		imovel.setEndereco(ENDERECO_1);
		imovel.setStatus(STATUS);
		imovel.setMetros_quadrados_de_terreno(METROS_QUADRADOS_DE_TERRENO);
		imovel.setQuantidade_de_quartos(QUANTIDADE_DE_QUARTOS);
		imovel.setQuantidade_de_banheiros(QUANTIDADE_DE_BANHEIROS);
		imovel.setPreco(PRECO);
		imovel.setQuantidade_de_vagas_de_garagem(QUANTIDADE_DE_VAGAS_DE_GARAGEM);
		
		listImovel = new ArrayList<Imovel>();
		listImovel.add(imovel);
		
		usuario = new Usuario(ID, NOME, EMAIL, SENHA, CPF, TELEFONE, ENDERECO, listImovel);
	}
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		start();
	}
	
	@Test
	public void whenFindByIdThenReturnSuccess() {
		when(service.find(anyInt())).thenReturn(usuario);

		ResponseEntity<Usuario> response = controller.find(ID);

		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(Usuario.class, response.getBody().getClass());
		assertEquals(ID, response.getBody().getId());
		assertEquals(NOME, response.getBody().getNome());
		assertEquals(EMAIL, response.getBody().getEmail());
		assertEquals(SENHA, response.getBody().getSenha());
		assertEquals(CPF, response.getBody().getCpf());
		assertEquals(TELEFONE, response.getBody().getTelefone());
		assertEquals(ENDERECO, response.getBody().getEndereco());
		assertEquals(listImovel, response.getBody().getImoveis());
	}
	
	@Test
	public void whenFindByIdThenReturnNotFound() {
		when(service.find(anyInt())).thenReturn(null);

		ResponseEntity<Usuario> response = controller.find(ID);

		assertNotNull(response);
		assertNull(response.getBody());
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}
	
	@Test
	public void whenFindAllThenReturnSuccess() {
		when(service.findAll()).thenReturn(List.of(usuario));
		
		ResponseEntity<List<Usuario>> response = controller.findall();
		
		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(ID, response.getBody().get(0).getId());
		assertEquals(NOME, response.getBody().get(0).getNome());
		assertEquals(EMAIL, response.getBody().get(0).getEmail());
		assertEquals(SENHA, response.getBody().get(0).getSenha());
		assertEquals(CPF, response.getBody().get(0).getCpf());
		assertEquals(TELEFONE, response.getBody().get(0).getTelefone());
		assertEquals(ENDERECO, response.getBody().get(0).getEndereco());
		assertEquals(listImovel, response.getBody().get(0).getImoveis());
	}
	
	@Test
	public void whenCreateThenReturnSuccess() {
		doNothing().when(service).save(0, usuario);

		controller.save(usuario);

		verify(service).save(0, usuario);
	}

	@Test
	public void whenUpdateThenReturnSuccess() {
		doNothing().when(service).save(ID, usuario);

		controller.update(ID, usuario);

		verify(service).save(ID, usuario);
	}

	@Test
	public void whenDeleteThenReturnSuccess() {
		doNothing().when(service).delete(ID);

		controller.delete(ID);

		verify(service).delete(ID);
	}
	
	@Test
	public void whenFindByEmailThenReturnSuccess() {
		when(service.findByEmail(anyString())).thenReturn(usuario);

		ResponseEntity<Usuario> response = controller.findByName(NOME);

		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(Usuario.class, response.getBody().getClass());
		assertEquals(ID, response.getBody().getId());
		assertEquals(NOME, response.getBody().getNome());
		assertEquals(EMAIL, response.getBody().getEmail());
		assertEquals(SENHA, response.getBody().getSenha());
		assertEquals(CPF, response.getBody().getCpf());
		assertEquals(TELEFONE, response.getBody().getTelefone());
		assertEquals(ENDERECO, response.getBody().getEndereco());
		assertEquals(listImovel, response.getBody().getImoveis());
	}

	@Test
	public void whenFindByEmailThenReturnNotFound() {
		when(service.findByEmail(anyString())).thenReturn(null);

		ResponseEntity<Usuario> response = controller.findByName(NOME);

		assertNotNull(response);
		assertNull(response.getBody());
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}	
}
