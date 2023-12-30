package fr.inalco.studia.gui;

import fr.inalco.studia.entity.Etudiant;
import fr.inalco.studia.entity.exercices.ExerciceQCM;
import fr.inalco.studia.gui.modules.ExerciceModule;
import fr.inalco.studia.repositories.ExerciceQCMRepositoryImpl;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Vue principale de l'étudiant avec tous les exercices <= à son niveau et de sa langue.
 */
public class EtudiantView extends GridPane implements View {

	private final int COL = 4; // 4 colonnes d'exercices

	public EtudiantView(Etudiant etudiant) {
		setPadding(new Insets(10));
		
		Text mesExercices = new Text("Mes exercices");
		mesExercices.setFont(new Font(26));
		
		this.add(mesExercices, 1, 1);
		
		int i = 0;
		for (ExerciceQCM exercice: new ExerciceQCMRepositoryImpl().findExercicesQCMByLangageNiveau(etudiant.getLangage(), etudiant.getNiveau()))
		{
			ExerciceModule em = new ExerciceModule(exercice);
			this.add(em, 1 + i % COL, 2 + i / COL); // On commence à la deuxième ligne
			em.setOnMouseClicked(e -> {
				StageInitializer.setScrollableView(new ExerciceQCMView(exercice)); // ouvrir l'exercice;
			});
			i++;
		}
	}
	
}
