package fr.inalco.studia.repositories;

import fr.inalco.studia.entity.Etudiant;

public interface EtudiantRepository {
	
	public boolean insert(Etudiant etudiant);
	
	public Etudiant findByPseudo(String pseudo);

}
