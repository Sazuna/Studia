package fr.inalco.studia.entity.exercices;

import java.util.ArrayList;
import java.util.List;

import fr.inalco.Studia.repositories.ReponseACocherRepositoryImpl;
import fr.inalco.studia.entity.Langage;
import fr.inalco.studia.entity.reponses.ReponseACocher;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="exercice_qcm")
public class ExerciceQCM extends Exercice {

	@OneToMany
    @JoinColumn(name = "exercice_qcm_id")
	private List<ReponseACocher> reponses;

	public ExerciceQCM(int niveau, Langage langage, String consigne, List<ReponseACocher> reponses) {
		super(niveau, langage, consigne);
		this.reponses = reponses;
	}

	public ExerciceQCM()
	{
	}

	/*
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	*/

	public List<ReponseACocher> getReponses() {
		return reponses;
	}

	public void setReponses(List<ReponseACocher> reponses) {
		this.reponses = reponses;
	}
	
	public void addReponse(boolean bonneReponse, String reponse)
	{
		this.reponses.add(new ReponseACocher(bonneReponse, reponse));
		
	}

	public void addReponse(ReponseACocher reponse)
	{
		this.reponses.add(reponse);
	}

	public void removeReponse(int reponseIdx)
	{
		ReponseACocher r = this.reponses.get(reponseIdx);
		this.removeReponse(r);
	}
	
	public void removeReponse(ReponseACocher reponse)
	{
		this.reponses.remove(reponse);
		ReponseACocherRepositoryImpl.factory().removeReponse(reponse);
	}
	
	/**
	 * Calcule le F-score en fonction des réponses. 
	 * @param reponses
	 * @return
	 */
	public float fscore(ArrayList<Boolean> reponses)
	{
		int vp = 0;
		int fp = 0;
		int fn = 0;
		//int vn = 0;
		
		for (int i = 0; i < reponses.size(); i++)
		{
			boolean correcte = this.reponses.get(i).correcte(reponses.get(i));
			if (correcte)
			{
				if (reponses.get(i))
				{
					vp ++;
				}
				/*else
				{
					vn ++;
				}*/
			}
			else
			{
				if (reponses.get(i))
				{
					fp ++;
				}
				else
				{
					fn ++;
				}
			}
		}
		//int vn = this.reponses.size() - vp - fp - fn;
		float fscore = 2 * vp / (2 * vp + fp  + fn);
		return fscore;
	}
}
