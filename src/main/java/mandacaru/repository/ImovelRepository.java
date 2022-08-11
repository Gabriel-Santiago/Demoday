package mandacaru.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import mandacaru.model.Imovel;

public interface ImovelRepository extends JpaRepository<Imovel, Integer> {

	List<Imovel> findByUsuarioId(int id);
}
