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

import mandacaru.model.Imagem;
import mandacaru.repository.ImagemRepository;
import mandacaru.service.ImagemService;

public class ImagemServiceTest {

	// Imagens
	private static final int ID = 1;
	private static final String NOME = "Teste";
	private static final String TIPO = "Png";
	private static final byte[] FOTO = null;
	
	@InjectMocks
	private ImagemService service;
	
	@Mock
	private ImagemRepository repository;
	
	private Imagem imagem;
	
	private void start() {
		imagem = new Imagem();
		imagem.setId(ID);
		imagem.setNome(NOME);
		imagem.setTipo(TIPO);
		imagem.setFoto(FOTO);
	}
	
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		start();
	}
	
	@Test
	public void whenDeleteVerifySuccess() { 
		when(repository.findById(anyInt())).thenReturn(Optional.of(imagem));
		doNothing().when(repository).delete(imagem);
			
		service.delete(ID);

		verify(repository).findById(anyInt());
		verify(repository).delete(any());
	}
	
	@Test
	public void whenFindAllThenReturnAnList() {
		when(repository.findAll()).thenReturn(List.of(imagem));

		List<Imagem> response =  service.findAll();

		assertNotNull(response);
		assertEquals(ID, response.get(0).getId());
		assertEquals(NOME, response.get(0).getNome());
		assertEquals(TIPO, response.get(0).getTipo());
		assertEquals(FOTO, response.get(0).getFoto());
	}
	
	@Test
	public void whenFindByIdThenReturnNullIfIdLessThan1() {
		Imagem response = service.find(0);

		assertNull(response);
	}

	@Test
	public void whenFindByIdThenReturnNullIfOptionalNotPresent() {
		when(repository.findById(anyInt())).thenReturn(Optional.empty());

		Imagem response = service.find(ID);

		assertNull(response);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
