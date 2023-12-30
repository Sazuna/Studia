package fr.inalco.studia.entity;

import java.util.ArrayList;
import java.util.Map;

import fr.inalco.studia.entity.exercices.ExerciceQCM;
import fr.inalco.studia.gui.ConnexionType;
import fr.inalco.studia.repositories.EtudiantRepositoryImpl;
import fr.inalco.studia.repositories.ExerciceQCMRepositoryImpl;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="etudiant")

public class Etudiant extends Utilisateur {

	/**
	 * Un langage par étudiant
	 */
	@Column(name="langage")
	private Langage langage;

	@Column(name="niveau")
	private short niveau;

	@Column(name="score")
	private int score;

	@ManyToOne
	@JoinColumn(name="exercice_qcm_id")
	private ExerciceQCM exerciceQCM;
	
	private static final Map<Integer, Integer> paliers = Map.of(1, 300, 2, 700, 3, 1200, 4, 2000, 5, 3000, 6, 5000, 7, 7000, 8, 10000);

	/*private List<ExerciceQCM> exerciceQCMReussis;

	private List<ExerciceQCM> exerciceQCMRates;*/

	public Etudiant() {
		this.niveau = 1;
		this.langage = Langage.ANGLAIS;
		this.score = 0;
		this.exerciceQCM = this.getExerciceQCMSuivant();
	}

	public Etudiant(String pseudo, String nom, String prenom, String mdp, Langage langage, int niveau, int score, ExerciceQCM exerciceQCM) {
		super(pseudo, nom, prenom, mdp);
		this.langage = langage;
		this.niveau = (byte)niveau;
		this.score = score;
		this.exerciceQCM = exerciceQCM;

	}

	public Langage getLangage() {
		return langage;
	}

	public void setLangage(Langage langage) {
		this.langage = langage;
	}

	public short getNiveau() {
		return niveau;
	}

	public void setNiveau(int i) {
		this.niveau = (byte)i;
	}

	public int getScore()
	{
		return this.score;
	}

	/**
	 * 
	 * @param score
	 * @return true si le niveau a été mis à jour
	 */
	public boolean updateScore(int score)
	{
		this.score += score;
		if (this.score >= paliers.get((int)this.niveau))
		{
			this.niveau ++;
			return true;
		}
		new EtudiantRepositoryImpl().insert(this); // Mise à jour du score à chaque appel de cette fonction
		return false;
	}

	/**
	 * 
	 * @return le dernier exercice que l'étudiant a fait
	 */
	public ExerciceQCM getExerciceQCMSuivant() {
		ExerciceQCM suivant = null;
		ArrayList<ExerciceQCM> exos = getExercicesQCMJouables();
		if (exos.size() == 0)
		{
			return null;
		}
		
		if (exerciceQCM != null)
		{
			suivant = new ExerciceQCMRepositoryImpl().findSuivant(exerciceQCM.getId(), langage, niveau);
			if (suivant == null)
			{
				suivant = exos.get(0); // retour à zéro quand plus de suivant
			}
		}
		else
		{
			suivant = exos.get(0); // n'a jamais joué
		}
		this.setDernierExerciceQCM(suivant);
		return suivant;
	}

	public void setDernierExerciceQCM(ExerciceQCM exerciceQCM) {
		this.exerciceQCM = exerciceQCM;
	}
	
	/**
	 * 
	 * @return les exercices du même niveau et du même langage que l'étudiant
	 */
	public ArrayList<ExerciceQCM> getExercicesQCMJouables() {
		ArrayList<ExerciceQCM> exos = (ArrayList<ExerciceQCM>)new ExerciceQCMRepositoryImpl().findExercicesQCMByLangageNiveau(langage, niveau);
		return exos;
	}

	@Override
	public ConnexionType getConnexionType()
	{
		return ConnexionType.ETUDIANT;
	}
}
