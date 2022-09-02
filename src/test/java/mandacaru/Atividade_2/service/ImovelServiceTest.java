package mandacaru.Atividade_2.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import mandacaru.model.Imagem;
import mandacaru.model.Imovel;
import mandacaru.model.Usuario;
import mandacaru.repository.ImovelRepository;
import mandacaru.service.ImovelService;

public class ImovelServiceTest {

	private static final int ID = 1;
	private static final String TITULO = "Mansão UFC";
	private static final String ENDERECO = "Av. Ceará";
	private static final String STATUS = "Pronto";
	private static final double METROS_QUADRADOS_DE_TERRENO = 306.90;
	private static final int QUANTIDADE_DE_QUARTOS = 6;
	private static final int QUANTIDADE_DE_BANHEIROS = 5;
	private static final double PRECO = 720963.81;
	private static final int QUANTIDADE_DE_VAGAS_DE_GARAGEM = 8;
	private static final String PROCESSO = "6229bb06-dabc-420d-ac72-d2cf67c775f9";
	private Usuario USUARIO;
	
	// Imagens
	private static final int ID_1 = 1;
	private static final String NOME = "Teste";
	private static final String TIPO = "PNG";
	
	@InjectMocks
	private ImovelService service;
	
	@Mock
	private ImovelRepository repository;
	
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
				 listImagem, USUARIO);	
	}
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		start();
	}
	
	
	
	@Test
	public void whenFindAllThenReturnAnList() {
		when(repository.findAll()).thenReturn(List.of(imovel));

		List<Imovel> response = service.findAll();

		assertNotNull(response);
		assertEquals(ID, response.get(0).getId());
		assertEquals(TITULO, response.get(0).getTitulo());
		assertEquals(ENDERECO, response.get(0).getEndereco());
		assertEquals(METROS_QUADRADOS_DE_TERRENO, response.get(0).getMetros_quadrados_de_terreno());
		assertEquals(QUANTIDADE_DE_QUARTOS, response.get(0).getQuantidade_de_quartos());
		assertEquals(QUANTIDADE_DE_BANHEIROS, response.get(0).getQuantidade_de_banheiros());
		assertEquals(QUANTIDADE_DE_VAGAS_DE_GARAGEM, response.get(0).getQuantidade_de_vagas_de_garagem());
		assertEquals(PRECO, response.get(0).getPreco());
		assertEquals(STATUS, response.get(0).getStatus());
		assertEquals(PROCESSO, response.get(0).getProcesso());
		assertEquals(listImagem, response.get(0).getImagens());
		assertEquals(USUARIO, response.get(0).getUsuario());
	}

	@Test
	public void whenFindAllDoneThenReturnAnList() {
		when(repository.findByStatus(STATUS)).thenReturn(List.of(imovel));

		List<Imovel> response = service.findAllDone();

		assertNotNull(response);
		assertEquals(ID, response.get(0).getId());
		assertEquals(TITULO, response.get(0).getTitulo());
		assertEquals(ENDERECO, response.get(0).getEndereco());
		assertEquals(METROS_QUADRADOS_DE_TERRENO, response.get(0).getMetros_quadrados_de_terreno());
		assertEquals(QUANTIDADE_DE_QUARTOS, response.get(0).getQuantidade_de_quartos());
		assertEquals(QUANTIDADE_DE_BANHEIROS, response.get(0).getQuantidade_de_banheiros());
		assertEquals(QUANTIDADE_DE_VAGAS_DE_GARAGEM, response.get(0).getQuantidade_de_vagas_de_garagem());
		assertEquals(PRECO, response.get(0).getPreco());
		assertEquals(STATUS, response.get(0).getStatus());
		assertEquals(PROCESSO, response.get(0).getProcesso());
		assertEquals(listImagem, response.get(0).getImagens());
		assertEquals(USUARIO, response.get(0).getUsuario());
	}
	
	@Test
	public void whenFindByIdThenReturnNullIfIdLessThan1() {
		Imovel response = service.find(0);

		assertNull(response);
	}

	@Test
	public void whenFindByIdThenReturnNullIfOptionalNotPresent() {
		when(repository.findById(anyInt())).thenReturn(Optional.empty());

		Imovel response = service.find(ID);

		assertNull(response);
	}	
	
	@Test
	public void whenDeleteVerifySuccess() {
		when(repository.findById(anyInt())).thenReturn(Optional.of(imovel));
		doNothing().when(repository).delete(imovel);
			
		service.delete(ID);

		verify(repository).findById(anyInt());
		verify(repository).delete(any());
	}
}
