package fr.inalco.studia.gui;

import fr.inalco.studia.entity.Enseignant;
import fr.inalco.studia.entity.exercices.ExerciceQCM;
import fr.inalco.studia.gui.modules.ExerciceModule;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Vue principale de l'étudiant avec tous les exercices qu'il a déjà résolus et le bouton "continuer".
 */
public class EnseignantView extends GridPane implements View {
	
	private final int COL = 4;

	public EnseignantView(Enseignant enseignant) {

		setPadding(new Insets(10));
		
		Text titre = new Text("Mes exercices");
		titre.setFont(new Font(26));
		
		this.add(titre, 1, 1);
		
		int i = 0;
		for (ExerciceQCM exercice: enseignant.getExercicesCrees())
		{
			ExerciceModule em = new ExerciceModule(exercice);
			this.add(em, 1 + i % COL, 2 + i / COL); // On commence à la deuxième ligne
			em.setOnMouseClicked(e -> {
				StageInitializer.setScrollableView(new ExerciceQCMCreatorView(exercice)); // ouvrir la modification de l'exercice;
			});
			i++;
		}
	}
	
}
