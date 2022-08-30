package mandacaru.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mandacaru.model.Imagem;

@Repository
public interface ImagemRepository extends JpaRepository<Imagem, Integer> {
	
	Imagem findFirstByImovelId(int id);
	
}
