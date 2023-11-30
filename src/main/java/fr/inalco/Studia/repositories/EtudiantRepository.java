package fr.inalco.Studia.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import fr.inalco.Studia.entity.Etudiant;

/**
 * https://www.baeldung.com/spring-data-jpa-query
 */
public interface EtudiantRepository extends Repository<Etudiant, Long> {

	@Query("SELECT e FROM Etudiant e")
	public Collection<Etudiant> findAllEtudiants();
	
	@Query("SELECT e FROM Etudiant e WHERE e.nom = :nom")
	public Collection<Etudiant> findEtudiantByNom(@Param("nom") String nom);
}
