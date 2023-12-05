package fr.inalco.Studia.repositories;

import java.util.Collection;
import java.util.List;

import fr.inalco.studia.StudiaEntityManager;
import fr.inalco.studia.entity.Langage;
import fr.inalco.studia.entity.exercices.ExerciceQCM;

public class ExerciceQCMRepositoryImpl implements ExerciceQCMRepository {

	
	// Une seule instance pour le projet
	private static ExerciceQCMRepositoryImpl repo = new ExerciceQCMRepositoryImpl();
	
	public static ExerciceQCMRepositoryImpl factory()
	{
		return repo;
	}
	

	//@Query("SELECT e FROM ExerciceQCM e WHERE e.niveau = :niveau AND e.langage = :langage")
	@Override
	public Collection<ExerciceQCM> findExercicesQCMByLangageNiveau(Langage langage, long niveau) {
		Collection<ExerciceQCM> result = StudiaEntityManager.getEntityManager().createQuery("SELECT e FROM ExerciceQCM e WHERE e.niveau = :niveau AND e.langage = :langage").setParameter("langage", langage).setParameter("niveau", niveau).getResultList();
		return result;
	}
	
	@Override
	public ExerciceQCM findById(Long id) {
		List<ExerciceQCM> result = StudiaEntityManager.getEntityManager().createQuery("SELECT e FROM ExerciceQCM e WHERE e.id = :id").setParameter("id", id).getResultList();
		if (result.size() == 0)
			return null;
		System.out.println("result: " + result.get(0).getId() + " " + id);
		return result.get(0);
	}

}
