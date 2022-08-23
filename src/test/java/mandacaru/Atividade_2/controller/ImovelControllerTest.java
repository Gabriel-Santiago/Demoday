package mandacaru.Atividade_2.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import mandacaru.controller.ImovelController;
import mandacaru.model.Imovel;
import mandacaru.service.ImovelService;

public class ImovelControllerTest {

	private static final int ID = 1;
	private static final String TITULO = "Mansão UFC";
	private static final String ENDERECO = "Av. Ceará";
	private static final String STATUS = "Pendente";
	private static final double METROS_QUADRADOS_DE_TERRENO = 306.90;
	private static final int QUANTIDADE_DE_QUARTOS = 6;
	private static final int QUANTIDADE_DE_BANHEIROS = 5;
	private static final double PRECO = 720963.81;
	private static final int QUANTIDADE_DE_VAGAS_DE_GARAGEM = 8;
	
	@InjectMocks
	private ImovelController controller;
	
	@Mock
	private ImovelService service;
	
	private Imovel imovel;
	
	private void start() {
		Imovel imovel = new Imovel();
		imovel.setId(ID);
		imovel.setTitulo(TITULO);
		imovel.setEndereco(ENDERECO);
		imovel.setStatus(STATUS);
		imovel.setMetros_quadrados_de_terreno(METROS_QUADRADOS_DE_TERRENO);
		imovel.setQuantidade_de_quartos(QUANTIDADE_DE_QUARTOS);
		imovel.setQuantidade_de_banheiros(QUANTIDADE_DE_BANHEIROS);
		imovel.setPreco(PRECO);
		imovel.setQuantidade_de_vagas_de_garagem(QUANTIDADE_DE_VAGAS_DE_GARAGEM);
	}
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		start();
	}
	
	@Test
	public void whenFindByIdThenReturnSuccess() { // consertar
		when(service.find(anyInt())).thenReturn(imovel);

		ResponseEntity<Imovel> response = controller.find(ID);

		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(Imovel.class, response.getBody().getClass());
		assertEquals(ID, response.getBody().getId());
		assertEquals(TITULO, response.getBody().getTitulo());
		assertEquals(STATUS, response.getBody().getStatus());
		assertEquals(METROS_QUADRADOS_DE_TERRENO, response.getBody().getMetros_quadrados_de_terreno());
		assertEquals(QUANTIDADE_DE_QUARTOS, response.getBody().getQuantidade_de_quartos());
		assertEquals(QUANTIDADE_DE_BANHEIROS, response.getBody().getQuantidade_de_banheiros());
		assertEquals(ENDERECO, response.getBody().getEndereco());
		assertEquals(PRECO, response.getBody().getPreco());
		assertEquals(QUANTIDADE_DE_VAGAS_DE_GARAGEM, response.getBody().getQuantidade_de_vagas_de_garagem());
	}
	
	@Test
	public void whenFindByIdThenReturnNotFound() {
		when(service.find(anyInt())).thenReturn(null);

		ResponseEntity<Imovel> response = controller.find(ID);

		assertNotNull(response);
		assertNull(response.getBody());
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}
	
	@Test
	public void whenFindAllThenReturnSuccess() { // consertar
		when(service.findAll()).thenReturn(List.of(imovel));
		
		ResponseEntity<List<Imovel>> response = controller.findall();
		
		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(ID, response.getBody().get(0).getId());
		assertEquals(TITULO, response.getBody().get(0).getTitulo());
		assertEquals(STATUS, response.getBody().get(0).getStatus());
		assertEquals(METROS_QUADRADOS_DE_TERRENO, response.getBody().get(0).getMetros_quadrados_de_terreno());
		assertEquals(QUANTIDADE_DE_QUARTOS, response.getBody().get(0).getQuantidade_de_quartos());
		assertEquals(QUANTIDADE_DE_BANHEIROS, response.getBody().get(0).getQuantidade_de_banheiros());
		assertEquals(ENDERECO, response.getBody().get(0).getEndereco());
		assertEquals(PRECO, response.getBody().get(0).getPreco());
		assertEquals(QUANTIDADE_DE_VAGAS_DE_GARAGEM, response.getBody().get(0).getQuantidade_de_vagas_de_garagem());
	}

	@Test
	public void whenUpdateThenReturnSuccess() { 
		doNothing().when(service).save(ID, imovel);

		controller.update(ID, imovel);
	}

	@Test
	public void whenDeleteThenReturnSuccess() { 
		doNothing().when(service).delete(ID);

		controller.delete(ID);

		verify(service).delete(ID);
	}	
}
