package mandacaru.Atividade_2.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import mandacaru.controller.ImagemController;
import mandacaru.model.Imagem;
import mandacaru.service.ImagemService;

public class ImagemControllerTest {
	
	// Imagens
	private static final int ID = 1;
	private static final String NOME = "Teste";
	private static final String TIPO = "Png";
	private static final byte[] FOTO = null;

	@InjectMocks
	private ImagemController controller;
	
	@Mock
	private ImagemService service;
	
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
	public void whenGetImageInfoThenReturnSuccess() {
		when(service.find(anyInt())).thenReturn(imagem);

		Imagem response = controller.getImageInfo(ID);
		assertNotNull(response);
		assertEquals(Imagem.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals(NOME, response.getNome());
		assertEquals(TIPO, response.getTipo());
		assertEquals(FOTO, response.getFoto());
	}
	
	@Test
	public void whenDeleteThenReturnSuccess() {
		doNothing().when(service).delete(ID);

		controller.deleteImage(ID);

		verify(service).delete(ID);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
