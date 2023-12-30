package fr.inalco.studia.repositories;

import java.util.Collection;
import java.util.List;

import fr.inalco.studia.StudiaEntityManager;
import fr.inalco.studia.entity.Langage;
import fr.inalco.studia.entity.exercices.ExerciceQCM;
import fr.inalco.studia.entity.reponses.ReponseACocher;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class ExerciceQCMRepositoryImpl implements ExerciceQCMRepository {

	
	// Une seule instance pour le projet
	private static ExerciceQCMRepositoryImpl repo = new ExerciceQCMRepositoryImpl();
	
	public static ExerciceQCMRepositoryImpl factory()
	{
		return repo;
	}
	

	//@Query("SELECT e FROM ExerciceQCM e WHERE e.niveau <= :niveau AND e.langage = :langage")
	/**
	 * Renvoie la liste des exercices qui sont du même niveau ou du niveau inférieur au joueur.
	 */
	@Override
	public Collection<ExerciceQCM> findExercicesQCMByLangageNiveau(Langage langage, long niveau) {
		@SuppressWarnings("unchecked")
		Collection<ExerciceQCM> result = StudiaEntityManager.getEntityManager().createQuery("SELECT e FROM ExerciceQCM e WHERE e.niveau <= :niveau AND e.langage = :langage").setParameter("langage", langage).setParameter("niveau", niveau).getResultList();
		return result;
	}
	
	@Override
	public ExerciceQCM findById(Long id) {
		@SuppressWarnings("unchecked")
		List<ExerciceQCM> result = StudiaEntityManager.getEntityManager().createQuery("SELECT e FROM ExerciceQCM e WHERE e.id = :id").setParameter("id", id).getResultList();
		if (result.size() == 0)
			return null;
		return result.get(0);
	}
	
	@Override
	public ExerciceQCM findSuivant(Long id, Langage langage, short niveau)
	{
		@SuppressWarnings("unchecked")
		List<ExerciceQCM> result = StudiaEntityManager.getEntityManager().createQuery("SELECT e FROM ExerciceQCM e WHERE e.id > :id AND e.langage = :langage AND e.niveau = :niveau").setParameter("id", id).setParameter("langage", langage).setParameter("niveau", niveau).getResultList();
		if (result.size() == 0)
			return null;
		return result.get(0);
	}


	@Override
	public boolean insert(ExerciceQCM exercice)
	{
		EntityManager em = StudiaEntityManager.getEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			em.merge(exercice);
			for (ReponseACocher reponse : exercice.getReponses()) {
				reponse.setExerciceQCM(exercice);
				em.merge(reponse);
			}
			tx.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			if (tx != null && tx.isActive()) {
				tx.rollback();
				return false;
			}
			ex.printStackTrace();
		} finally {
			em.close();
		}
		return true;
	}

}
