package mandacaru.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mandacaru.model.Imovel;

@Repository
public interface ImovelRepository extends JpaRepository<Imovel, Integer> {

	List<Imovel> findByUsuarioId(int id);
	
	List<Imovel> findByStatus(String status);
	
}
