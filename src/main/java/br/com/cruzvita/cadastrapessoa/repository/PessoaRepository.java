package br.com.cruzvita.cadastrapessoa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.cruzvita.cadastrapessoa.model.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

	
	@Query (value = "SELECT * FROM pessoas WHERE identificador = :identificador", nativeQuery = true)
	Pessoa findByIdentificador(@Param("identificador") String identificador);
	
	@Query (value = "SELECT * FROM pessoas WHERE identificador = :identificador", nativeQuery = true)
	List<Pessoa> findByIdentificadores(@Param("identificador") String identificador);

	@Query (value = "DELETE * FROM pessoas WHERE identificador = :identificador", nativeQuery = true)
	void deleteByIdentificador(@Param("identificador") String identificador);


}
