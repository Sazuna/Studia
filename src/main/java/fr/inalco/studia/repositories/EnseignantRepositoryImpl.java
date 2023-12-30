package fr.inalco.studia.repositories;

import java.util.List;

import fr.inalco.studia.StudiaEntityManager;
import fr.inalco.studia.entity.Enseignant;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class EnseignantRepositoryImpl implements EnseignantRepository {
	

	public boolean insert(Enseignant enseignant) {
		EntityManager em = StudiaEntityManager.getEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			em.merge(enseignant);
			tx.commit();
		} catch (Exception ex) {
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

	@Override
	public Enseignant findByPseudo(String pseudo) {
		@SuppressWarnings("unchecked")
		List<Enseignant> result = StudiaEntityManager.getEntityManager().createQuery("SELECT e FROM Enseignant e WHERE e.pseudo = :pseudo").setParameter("pseudo", pseudo).getResultList();
		if (result.size() == 0)
			return null;
		return result.get(0);
	}


}
