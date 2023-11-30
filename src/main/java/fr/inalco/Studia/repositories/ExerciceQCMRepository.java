package fr.inalco.Studia.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import fr.inalco.Studia.entity.Langage;
import fr.inalco.Studia.entity.exercices.ExerciceQCM;

public interface ExerciceQCMRepository extends Repository<ExerciceQCM, Long> {

	@Query("SELECT e FROM ExerciceQCM e WHERE e.niveau = :niveau AND e.langage = :langage")
	Collection<ExerciceQCM> findExercicesQCMByLangageNiveau(@Param("langage") Langage langage, @Param("niveau") long niveau);
}
