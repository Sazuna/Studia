package fr.inalco.studia.entity.exercices;

import java.util.ArrayList;
import java.util.List;

import fr.inalco.studia.repositories.ReponseACocherRepositoryImpl;
import fr.inalco.studia.entity.Enseignant;
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

	public ExerciceQCM(int niveau, Langage langage, String consigne, List<ReponseACocher> reponses, Enseignant createur) {
		super(niveau, langage, consigne, createur);
		this.reponses = reponses;
	}

	public ExerciceQCM() // Les entités doivent avoir un constructeur par défaut.
	{
		super();
	}
	
	public ExerciceQCM(Enseignant createur)
	{
		super(createur);
		this.reponses = new ArrayList<ReponseACocher>();
	}

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
	 * Calcule le score en fonction des bonnes et mauvaises réponses.
	 * Formule: score = (VP + VN) / (VP + VN + 2 * FP + 2 * FN)
	 * @param reponses, la liste des réponses de l'utilisateur qui sera comparée avec les vraies réponses
	 * @return le score (valeur entre 0 et 1)
	 */
	public double score(ArrayList<Boolean> reponses)
	{

		double vp = 0;
		double fp = 0;
		double fn = 0;
		double vn = 0;
		
		for (int i = 0; i < reponses.size(); i++)
		{
			boolean correcte = this.reponses.get(i).correcte(reponses.get(i));
			if (correcte)
			{
				if (reponses.get(i))
				{
					vp ++;
				}
				else
				{
					vn ++;
				}
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
		double score = (vp + vn) / (vp + vn + 2 * fp  + 2 * fn);
		return score;
	}
	
	@Override
	public String getType()
	{
		return "QCM";
	}
	
	public boolean equals(ExerciceQCM exo)
	{
		return exo.getId() == this.getId();
	}
}
