package fr.inalco.Studia.repositories;

import fr.inalco.Studia.entity.reponses.ReponseACocher;

public interface ReponseACocherRepository {

	public ReponseACocher findById(Long id);

	void removeNull();

}
