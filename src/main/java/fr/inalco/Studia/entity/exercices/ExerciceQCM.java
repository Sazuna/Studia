package fr.inalco.Studia.entity.exercices;

import java.util.ArrayList;

import fr.inalco.Studia.entity.reponses.ReponseACocher;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="exercice_qcm")
public class ExerciceQCM extends Exercice {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="exercice_qcm_id")
	private Long id;



	@ManyToMany
	@JoinTable(
			name = "reponse_a_cocher",
			joinColumns = @JoinColumn(name = "reponse_id"),
			inverseJoinColumns = @JoinColumn(name = "exercice_qcm_id")
    )
	private ArrayList<ReponseACocher> reponses;

	public ExerciceQCM(Long id, ArrayList<ReponseACocher> reponses) {
		super();
		this.id = id;
		this.reponses = reponses;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ArrayList<ReponseACocher> getReponses() {
		return reponses;
	}

	public void setReponses(ArrayList<ReponseACocher> reponses) {
		this.reponses = reponses;
	}
	
	public ExerciceQCM(ArrayList<ReponseACocher> reponses)
	{
		this.reponses = reponses;
	}

	/**
	 * Ajoute une bonne réponse à la liste des réponses possibles.
	 * @param reponse
	 */
	
	public void addReponse(boolean bonneReponse, String reponse)
	{
		this.reponses.add(new ReponseACocher(bonneReponse, reponse));
	}

	public void removeReponse(int reponseIdx)
	{
		this.reponses.remove(reponseIdx);
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
