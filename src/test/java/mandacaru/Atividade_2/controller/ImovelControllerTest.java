package mandacaru.Atividade_2.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyInt;
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

import mandacaru.controller.ImovelController;
import mandacaru.model.Imagem;
import mandacaru.model.Imovel;
import mandacaru.model.Usuario;
import mandacaru.service.ImovelService;

public class ImovelControllerTest {

	// Imovel
	private static final int ID = 1;
	private static final String TITULO = "Mansão UFC";
	private static final String ENDERECO = "Av. Ceará";
	private static final double METROS_QUADRADOS_DE_TERRENO = 306.90;
	private static final int QUANTIDADE_DE_QUARTOS = 6;
	private static final int QUANTIDADE_DE_BANHEIROS = 5;
	private static final int QUANTIDADE_DE_VAGAS_DE_GARAGEM = 8;
	private static final double PRECO = 720963.81;
	private static final String STATUS = "Pendente";
	private static final String PROCESSO = "sjvapoofa7v7ev498v48r4ve98f4";
	private Usuario usuario;
	
	
	
	// Imagens
	private static final int ID_1 = 1;
	private static final String NOME = "Teste";
	private static final String TIPO = "PNG";
	
	@InjectMocks
	private ImovelController controller;
	
	@Mock
	private ImovelService service;
	
	private Imovel imovel;
	
	private List<Imagem> listImagem;
	
	private void start() {
		Imagem imagem = new Imagem();
		imagem.setId(ID_1);
		imagem.setNome(NOME);
		imagem.setTipo(TIPO);
		imagem.setFoto(null);
		
		listImagem = new ArrayList<Imagem>();
		listImagem.add(imagem);
		
		imovel = new Imovel(ID, TITULO, ENDERECO, METROS_QUADRADOS_DE_TERRENO, QUANTIDADE_DE_QUARTOS, 
				QUANTIDADE_DE_BANHEIROS, QUANTIDADE_DE_VAGAS_DE_GARAGEM, PRECO,STATUS, PROCESSO,
				 listImagem, usuario);		
	}
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		start();
	}
	
	@Test
	public void whenFindByIdThenReturnSuccess() {
		when(service.find(anyInt())).thenReturn(imovel);

		ResponseEntity<Imovel> response = controller.find(ID);
		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(Imovel.class, response.getBody().getClass());
		assertEquals(ID, response.getBody().getId());
		assertEquals(TITULO, response.getBody().getTitulo());
		assertEquals(ENDERECO, response.getBody().getEndereco());
		assertEquals(METROS_QUADRADOS_DE_TERRENO, response.getBody().getMetros_quadrados_de_terreno());
		assertEquals(QUANTIDADE_DE_QUARTOS, response.getBody().getQuantidade_de_quartos());
		assertEquals(QUANTIDADE_DE_BANHEIROS, response.getBody().getQuantidade_de_banheiros());
		assertEquals(QUANTIDADE_DE_VAGAS_DE_GARAGEM, response.getBody().getQuantidade_de_vagas_de_garagem());
		assertEquals(PRECO, response.getBody().getPreco());
		assertEquals(STATUS, response.getBody().getStatus());
		assertEquals(PROCESSO, response.getBody().getProcesso());
		assertEquals(listImagem, response.getBody().getImagens());
		assertEquals(usuario, response.getBody().getUsuario());
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
	public void whenFindAllThenReturnSuccess() {
		when(service.findAll()).thenReturn(List.of(imovel));
		
		ResponseEntity<List<Imovel>> response = controller.findall();
		
		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(ID, response.getBody().get(0).getId());
		assertEquals(TITULO, response.getBody().get(0).getTitulo());
		assertEquals(ENDERECO, response.getBody().get(0).getEndereco());
		assertEquals(METROS_QUADRADOS_DE_TERRENO, response.getBody().get(0).getMetros_quadrados_de_terreno());
		assertEquals(QUANTIDADE_DE_QUARTOS, response.getBody().get(0).getQuantidade_de_quartos());
		assertEquals(QUANTIDADE_DE_BANHEIROS, response.getBody().get(0).getQuantidade_de_banheiros());
		assertEquals(QUANTIDADE_DE_VAGAS_DE_GARAGEM, response.getBody().get(0).getQuantidade_de_vagas_de_garagem());
		assertEquals(PRECO, response.getBody().get(0).getPreco());
		assertEquals(STATUS, response.getBody().get(0).getStatus());
		assertEquals(PROCESSO, response.getBody().get(0).getProcesso());
		assertEquals(listImagem, response.getBody().get(0).getImagens());
		assertEquals(usuario, response.getBody().get(0).getUsuario());
	}
	
	@Test
	public void whenFindAllOfUserThenReturnSuccess() {
		when(service.findAllOfUser(ID)).thenReturn(List.of(imovel));
		
		ResponseEntity<List<Imovel>> response = controller.findallofuser(ID);
		
		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(ID, response.getBody().get(0).getId());
		assertEquals(TITULO, response.getBody().get(0).getTitulo());
		assertEquals(ENDERECO, response.getBody().get(0).getEndereco());
		assertEquals(METROS_QUADRADOS_DE_TERRENO, response.getBody().get(0).getMetros_quadrados_de_terreno());
		assertEquals(QUANTIDADE_DE_QUARTOS, response.getBody().get(0).getQuantidade_de_quartos());
		assertEquals(QUANTIDADE_DE_BANHEIROS, response.getBody().get(0).getQuantidade_de_banheiros());
		assertEquals(QUANTIDADE_DE_VAGAS_DE_GARAGEM, response.getBody().get(0).getQuantidade_de_vagas_de_garagem());
		assertEquals(PRECO, response.getBody().get(0).getPreco());
		assertEquals(STATUS, response.getBody().get(0).getStatus());
		assertEquals(PROCESSO, response.getBody().get(0).getProcesso());
		assertEquals(listImagem, response.getBody().get(0).getImagens());
		assertEquals(usuario, response.getBody().get(0).getUsuario());
	}
	
	@Test
	public void whenFindAllDoneThenReturnSuccess() {
		when(service.findAllDone()).thenReturn(List.of(imovel));
		
		ResponseEntity<List<Imovel>> response = controller.findalldone();
		
		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(ID, response.getBody().get(0).getId());
		assertEquals(TITULO, response.getBody().get(0).getTitulo());
		assertEquals(ENDERECO, response.getBody().get(0).getEndereco());
		assertEquals(METROS_QUADRADOS_DE_TERRENO, response.getBody().get(0).getMetros_quadrados_de_terreno());
		assertEquals(QUANTIDADE_DE_QUARTOS, response.getBody().get(0).getQuantidade_de_quartos());
		assertEquals(QUANTIDADE_DE_BANHEIROS, response.getBody().get(0).getQuantidade_de_banheiros());
		assertEquals(QUANTIDADE_DE_VAGAS_DE_GARAGEM, response.getBody().get(0).getQuantidade_de_vagas_de_garagem());
		assertEquals(PRECO, response.getBody().get(0).getPreco());
		assertEquals(STATUS, response.getBody().get(0).getStatus());
		assertEquals(PROCESSO, response.getBody().get(0).getProcesso());
		assertEquals(listImagem, response.getBody().get(0).getImagens());
		assertEquals(usuario, response.getBody().get(0).getUsuario());
	}
	
	@Test
	public void whenDeleteThenReturnSuccess() {
		doNothing().when(service).delete(ID);

		controller.delete(ID);

		verify(service).delete(ID);
	}		
}
