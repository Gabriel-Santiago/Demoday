package mandacaru.Atividade_2.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import mandacaru.controller.ImagemController;
import mandacaru.model.Imagem;
import mandacaru.service.ImagemService;

public class ImagemControllerTest { 
	
	// Imagens
	private static final int ID = 1;
	private static final String NOME = "Teste";
	private static final String TIPO = MediaType.IMAGE_PNG_VALUE;
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
	public void whenGetImageThenReturnSuccess() {
		when(service.find(anyInt())).thenReturn(imagem);

		ResponseEntity<byte[]> response = controller.getImage(ID);
		assertNotNull(response);
		assertEquals(FOTO, response.getBody());
	}
	
	@Test
	public void whenUploadImageThenReturnSuccess() throws IOException {		
		doNothing().when(service).save(imagem, 0);
		
		MultipartFile[] file = new MultipartFile[1];
		byte[] b = new byte[1];
		file[0] = new MockMultipartFile("fileItem","teste", "image/png", b);
		
		controller.uploadImage(ID, file);
		
		
	}
	
	@Test
	public void whenDeleteThenReturnSuccess() {
		doNothing().when(service).delete(ID);

		controller.deleteImage(ID);

		verify(service).delete(ID);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
