package fr.inalco.studia;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class StudiaEntityManager {
	
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("fr.inalco.Studia");
	
	public static EntityManager getEntityManager() {
	    return emf.createEntityManager();
	}
}
