package fr.inalco.studia.repositories;

import fr.inalco.studia.entity.reponses.ReponseACocher;

public interface ReponseACocherRepository {

	public ReponseACocher findById(Long id);

	void removeNull();

}
