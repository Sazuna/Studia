package fr.inalco.studia.entity;

import java.util.ArrayList;
import java.util.List;

import fr.inalco.studia.entity.exercices.ExerciceQCM;
import fr.inalco.studia.gui.ConnexionType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="enseignant")
public class Enseignant extends Utilisateur {
	
	// L'identifiant est le pseudo
	/*@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="enseignant_id")
	private Long id;*/
	
	
	/*
	@Column(name="nom")
	private String nom;

	@Column(name="mdp")
	private String mdp;
	*/

	@OneToMany
    @JoinColumn(name="createur")
	private List<ExerciceQCM> exercicesQCMCrees; // TODO tester avec Exercice tout court.*/

	public Enseignant() {
		super();
	}

	public Enseignant(String pseudo, String nom, String prenom, String mdp)
	{
		super(pseudo, nom, prenom, mdp);
		this.exercicesQCMCrees = new ArrayList<ExerciceQCM>();
	}

	public List<ExerciceQCM> getExercicesCrees() {
		return exercicesQCMCrees;
	}

	public void addExercice(ExerciceQCM exercice) {
		this.exercicesQCMCrees.add(exercice);
	}

	@Override
	public ConnexionType getConnexionType()
	{
		return ConnexionType.ENSEIGNANT;
	}
}
