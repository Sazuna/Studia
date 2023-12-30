package fr.inalco.studia.repositories;

import fr.inalco.studia.entity.Enseignant;

public interface EnseignantRepository {
	
	public boolean insert(Enseignant etudiant);
	
	public Enseignant findByPseudo(String pseudo);

}
