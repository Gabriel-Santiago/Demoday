package mandacaru.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mandacaru.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	Usuario findFirstByNome(String nome);
}
