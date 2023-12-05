package fr.inalco.Studia.repositories;

import java.util.Collection;

import fr.inalco.studia.entity.Langage;
import fr.inalco.studia.entity.exercices.ExerciceQCM;

public interface ExerciceQCMRepository {

	Collection<ExerciceQCM> findExercicesQCMByLangageNiveau(Langage langage, long niveau);

	ExerciceQCM findById(Long id);

}