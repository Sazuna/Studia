package fr.inalco.Studia.repositories;

import java.util.Collection;

import fr.inalco.Studia.entity.Langage;
import fr.inalco.Studia.entity.exercices.ExerciceQCM;

public interface ExerciceQCMRepository {

	Collection<ExerciceQCM> findExercicesQCMByLangageNiveau(Langage langage, long niveau);

	ExerciceQCM findById(Long id);

}