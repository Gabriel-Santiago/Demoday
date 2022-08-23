package mandacaru.Atividade_2.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import mandacaru.model.Imovel;
import mandacaru.repository.ImovelRepository;
import mandacaru.service.ImovelService;

public class ImovelServiceTest {

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
	private ImovelService service;
	
	@Mock
	private ImovelRepository repository;
	
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
	public void whenFindByIdThenReturnAProduct() {
		when(repository.findById(anyInt())).thenReturn(Optional.of(imovel));

		Imovel response = service.find(ID);
		
		assertNotNull(response);
		assertEquals(Imovel.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals(TITULO, response.getTitulo());
		assertEquals(STATUS, response.getStatus());
		assertEquals(METROS_QUADRADOS_DE_TERRENO, response.getMetros_quadrados_de_terreno());
		assertEquals(QUANTIDADE_DE_QUARTOS, response.getQuantidade_de_quartos());
		assertEquals(QUANTIDADE_DE_BANHEIROS, response.getQuantidade_de_banheiros());
		assertEquals(ENDERECO, response.getEndereco());
		assertEquals(PRECO, response.getPreco());
		assertEquals(QUANTIDADE_DE_VAGAS_DE_GARAGEM, response.getQuantidade_de_vagas_de_garagem());
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
	public void whenFindAllThenReturnAnList() {
		when(repository.findAll()).thenReturn(List.of(imovel));

		List<Imovel> response = service.findAll();

		assertNotNull(response);
		assertEquals(ID, response.get(0).getId());
		assertEquals(TITULO, response.get(0).getTitulo());
		assertEquals(STATUS, response.get(0).getStatus());
		assertEquals(METROS_QUADRADOS_DE_TERRENO, response.get(0).getMetros_quadrados_de_terreno());
		assertEquals(QUANTIDADE_DE_QUARTOS, response.get(0).getQuantidade_de_quartos());
		assertEquals(QUANTIDADE_DE_BANHEIROS, response.get(0).getQuantidade_de_banheiros());
		assertEquals(ENDERECO, response.get(0).getEndereco());
		assertEquals(PRECO, response.get(0).getPreco());
		assertEquals(QUANTIDADE_DE_VAGAS_DE_GARAGEM, response.get(0).getQuantidade_de_vagas_de_garagem());
	}
	
	@Test
	public void whenSaveVerifySuccess() {
		when(repository.save(any())).thenReturn(imovel);
		service.save(0, imovel);
		verify(repository).save(any());
	}
	
	@Test
	public void whenUpdateVerifySuccess() {
		when(repository.save(any())).thenReturn(imovel);
		service.save(1, imovel);
		verify(repository).save(any());
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
