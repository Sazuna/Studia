package fr.inalco.Studia.repositories;

import fr.inalco.studia.StudiaEntityManager;
import fr.inalco.studia.entity.reponses.ReponseACocher;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class ReponseACocherRepositoryImpl implements ReponseACocherRepository{

	// Une seule instance pour le projet
	private static ReponseACocherRepositoryImpl repo = new ReponseACocherRepositoryImpl();

	public static ReponseACocherRepositoryImpl factory()
	{
		return repo;
	}

	@Override
	public ReponseACocher findById(Long id) {
		return null;/*
		List<ReponseACocher> result = StudiaEntityManager.getEntityManager().createQuery("SELECT r FROM ReponseACocher r WHERE r.id = :id").setParameter("id", id).getResultList();
		if (result.size() == 0)
			return null;
		return result.get(0);*/
	}

	@Override
	public void removeNull() {

		//TODO remove this function
		//StudiaEntityManager.getEntityManager().createQuery("DELETE FROM ReponseACocher r WHERE r.exerciceQCM IS NULL").executeUpdate();
	}

	public void removeReponse(ReponseACocher reponse) {
		EntityManager em = StudiaEntityManager.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {

			tx.begin();
			//StudiaEntityManager.getEntityManager().createQuery("DELETE FROM ReponseACocher r WHERE r.id = :id").setParameter("id", reponse.getId());
			if (!StudiaEntityManager.getEntityManager().contains(reponse))
			{
				reponse = em.merge(reponse);
			}
			//if (em.find(ReponseACocher.class, reponse) != null)
			em.remove(reponse);
			System.out.println("remove" + reponse);
			tx.commit();


		} catch (Exception ex) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			ex.printStackTrace();
		} finally {
			//em.clear(); // Cela forcera à rappeler la bdd pour vérifier la présence d'un objet
			em.close();
		}

	}

}
