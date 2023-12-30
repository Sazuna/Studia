package fr.inalco.studia.repositories;

import java.util.List;

import fr.inalco.studia.StudiaEntityManager;
import fr.inalco.studia.entity.Etudiant;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class EtudiantRepositoryImpl implements EtudiantRepository {
	

	public boolean insert(Etudiant etudiant) {
		EntityManager em = StudiaEntityManager.getEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			em.merge(etudiant); // Ajout ou mise à jour de l'étudiant. Normalement l'entity manager se
			// charge de vérifier que le pseudo n'est pas déjà pris :D
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
	public Etudiant findByPseudo(String pseudo) {
		@SuppressWarnings("unchecked")
		List<Etudiant> result = StudiaEntityManager.getEntityManager().createQuery("SELECT e FROM Etudiant e WHERE e.pseudo = :pseudo").setParameter("pseudo", pseudo).getResultList();
		if (result.size() == 0)
			return null;
		return result.get(0);
	}


}
