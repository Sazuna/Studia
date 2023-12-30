package fr.inalco.studia.repositories;

import java.util.Collection;

import fr.inalco.studia.entity.Langage;
import fr.inalco.studia.entity.exercices.ExerciceQCM;

public interface ExerciceQCMRepository {

	public Collection<ExerciceQCM> findExercicesQCMByLangageNiveau(Langage langage, long niveau);

	public ExerciceQCM findById(Long id);
	
	public boolean insert(ExerciceQCM exercice);
	
	ExerciceQCM findSuivant(Long id, Langage langage, short niveau);

}