package mandacaru.Atividade_2.service;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import mandacaru.model.Imovel;
import mandacaru.model.Usuario;
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
	private static final String PROCESSO = "6229bb06-dabc-420d-ac72-d2cf67c775f9";
	private Usuario USUARIO;
	
	@InjectMocks
	private ImovelService service;
	
	@Mock
	private ImovelRepository repository;
	
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
}
