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

import mandacaru.model.Imovel;
import mandacaru.model.Usuario;
import mandacaru.repository.UsuarioRepository;
import mandacaru.service.UsuarioService;

public class UsuarioServiceTest {

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
		private UsuarioService service;
		
		@Mock
		private UsuarioRepository repository;
		
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
		public void whenFindByIdThenReturnAProduct() {
			when(repository.findById(anyInt())).thenReturn(Optional.of(usuario));

			Usuario response = service.find(ID);

			assertNotNull(response);
			assertEquals(Usuario.class, response.getClass());
			assertEquals(ID, response.getId());
			assertEquals(NOME, response.getNome());
			assertEquals(EMAIL, response.getEmail());
			assertEquals(SENHA, response.getSenha());
			assertEquals(CPF, response.getCpf());
			assertEquals(TELEFONE, response.getTelefone());
			assertEquals(ENDERECO, response.getEndereco());
			assertEquals(listImovel, response.getImoveis());
		}
		
		@Test
		public void whenFindByIdThenReturnNullIfIdLessThan1() {
			Usuario response = service.find(0);

			assertNull(response);
		}

		@Test
		public void whenFindByIdThenReturnNullIfOptionalNotPresent() {
			when(repository.findById(anyInt())).thenReturn(Optional.empty());

			Usuario response = service.find(ID);

			assertNull(response);
		}
		
		@Test
		public void whenFindAllThenReturnAnList() {
			when(repository.findAll()).thenReturn(List.of(usuario));

			List<Usuario> response = service.findAll();

			assertNotNull(response);
			assertEquals(ID, response.get(0).getId());
			assertEquals(NOME, response.get(0).getNome());
			assertEquals(EMAIL, response.get(0).getEmail());
			assertEquals(SENHA, response.get(0).getSenha());
			assertEquals(CPF, response.get(0).getCpf());
			assertEquals(TELEFONE, response.get(0).getTelefone());
			assertEquals(ENDERECO, response.get(0).getEndereco());
			assertEquals(listImovel, response.get(0).getImoveis());
		}
		
		@Test
		public void whenSaveVerifySuccess() {
			when(repository.save(any())).thenReturn(usuario);
			service.save(0, usuario);
			verify(repository).save(any());
		}
		
		@Test
		public void whenUpdateVerifySuccess() {
			when(repository.save(any())).thenReturn(usuario);
			service.save(1, usuario);
			verify(repository).save(any());
		}
		
		@Test
		public void whenDeleteVerifySuccess() {
			when(repository.findById(anyInt())).thenReturn(Optional.of(usuario));
			doNothing().when(repository).delete(usuario);
				
			service.delete(ID);

			verify(repository).findById(anyInt());
			verify(repository).delete(any());
		}		
}
