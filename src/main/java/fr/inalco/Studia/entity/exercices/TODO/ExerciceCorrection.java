package fr.inalco.Studia.entity.exercices.TODO;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 * L'étudiant doit, après avoir cliqué sur les erreurs, proposer une alternative correcte.
 * L'alternative peut être:
 * - une modification du token
 * - un ajout de token
 * - une suppression du token sélectionné
 */
@Entity
@Table(name="exercice_correction")
public class ExerciceCorrection extends ExerciceErreur {
	
	

}
