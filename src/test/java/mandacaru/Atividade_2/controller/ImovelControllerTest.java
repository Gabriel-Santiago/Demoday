package mandacaru.Atividade_2.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.apache.tomcat.util.json.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import mandacaru.controller.ImovelController;
import mandacaru.model.Imovel;
import mandacaru.model.Usuario;
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
	private static final String PROCESSO = "6229bb06-dabc-420d-ac72-d2cf67c775f9";
	private Usuario USUARIO;
	
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
		imovel.setProcesso(PROCESSO);
		imovel.setUsuario(USUARIO);
	}
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		start();
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
	public void whenCreateThenReturnSuccess() throws ParseException, IOException, InterruptedException {
		doNothing().when(service).save(USUARIO.getId(), imovel);

		controller.save(USUARIO.getId(), imovel);

		verify(service).save(USUARIO.getId(), imovel);
	}

	@Test
	public void whenDeleteThenReturnSuccess() { 
		doNothing().when(service).delete(ID);

		controller.delete(ID);

		verify(service).delete(ID);
	}	
}
