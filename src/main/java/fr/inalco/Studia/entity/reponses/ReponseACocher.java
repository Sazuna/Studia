package fr.inalco.Studia.entity.reponses;

import fr.inalco.Studia.entity.exercices.ExerciceQCM;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="reponse_a_cocher")
public class ReponseACocher extends Reponse {
	/*@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="reponse_id")
	private Long id;*/
	
	@Column(name="reponse")
	private Boolean reponse;
	
	@Column(name="reponse_string")
	private String reponseString;

	
	@ManyToOne
	@JoinColumn(name="exercice_qcm_id")
	private ExerciceQCM exerciceQCM;

	public ReponseACocher(Boolean reponse, String reponseString) {
		super();
		this.reponse = reponse;
		this.reponseString = reponseString;
	}

	public ReponseACocher() {
		super();
	}

	public String getReponseString() {
		return reponseString;
	}

	public void setReponseString(String reponseString) {
		this.reponseString = reponseString;
	}

	public void setReponse(Boolean reponse) {
		this.reponse = reponse;
	}

	public Boolean getReponse()
	{
		return reponse;
	}

	public ExerciceQCM getExerciceQCM() {
		return exerciceQCM;
	}

	public void setExerciceQCM(ExerciceQCM exerciceQCM) {
		this.exerciceQCM = exerciceQCM;
	}

	public boolean correcte(Object reponse)
	{
		return reponse.equals(this.reponse);
	}
}
