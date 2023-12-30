package fr.inalco.studia.gui.modules;

import fr.inalco.studia.entity.Enseignant;
import fr.inalco.studia.entity.Etudiant;
import fr.inalco.studia.entity.exercices.ExerciceQCM;
import fr.inalco.studia.gui.ConnexionType;
import fr.inalco.studia.gui.EnseignantConnexionView;
import fr.inalco.studia.gui.EnseignantView;
import fr.inalco.studia.gui.EtudiantConnexionView;
import fr.inalco.studia.gui.EtudiantView;
import fr.inalco.studia.gui.ExerciceQCMCreatorView;
import fr.inalco.studia.gui.ExerciceQCMView;
import fr.inalco.studia.gui.StageInitializer;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class StudiaMenu extends MenuBar{
	
	public StudiaMenu()
	{
		
		Menu connexionMenu = new Menu("Connexion");
		MenuItem connexionMenu1 = new MenuItem("Etudiant");
		MenuItem connexionMenu2 = new MenuItem("Enseignant");
		connexionMenu.getItems().addAll(connexionMenu1, connexionMenu2);
		
		connexionMenu1.setOnAction(e -> {
			StageInitializer.setScrollableView(new EtudiantConnexionView());
		});
		connexionMenu2.setOnAction(e -> {
			StageInitializer.setScrollableView(new EnseignantConnexionView());
		});
		
		getMenus().addAll(connexionMenu);
		
		//this.setLayoutX(StageInitializer.borderPane.getWidth() - this.getWidth() / 2);
		
	}
	
	public StudiaMenu(ConnexionType ct)
	{
		if (ct == ConnexionType.ENSEIGNANT)
		{

			getMenus().clear();
			Menu creerMenu = new Menu("Créer");
			MenuItem exerciceMenu1 = new MenuItem("Exercice QCM");
			creerMenu.getItems().addAll(exerciceMenu1);			

			exerciceMenu1.setOnAction(e -> {
				StageInitializer.setScrollableView(new ExerciceQCMCreatorView(new ExerciceQCM((Enseignant) StageInitializer.connecte)));
			});
			
			Menu voirMenu = new Menu("Voir");
			
			MenuItem voirMenu1 = new MenuItem("Mes exercices");
			voirMenu.getItems().add(voirMenu1);
			voirMenu1.setOnAction(e -> {
				StageInitializer.setScrollableView(new EnseignantView((Enseignant)StageInitializer.connecte));
			});
			
			getMenus().addAll(creerMenu, voirMenu);
			
		}
		else if (ct == ConnexionType.ETUDIANT)
		{
			Menu exerciceMenu = new Menu("Exercice");
			//MenuBar mb = exerciceMenu.generateMenuBar();
			MenuItem exerciceMenu1 = new MenuItem("Mes exercices");
			exerciceMenu1.setOnAction(e -> {
				StageInitializer.setScrollableView(new EtudiantView((Etudiant)StageInitializer.connecte));
			});
			MenuItem exerciceMenu2 = new MenuItem("S'exercer");
			exerciceMenu2.setOnAction(e -> {
				// Joue à partir du dernier exercice qu'il a joué et qu'on a enregistré.
				StageInitializer.setScrollableView(new ExerciceQCMView(((Etudiant)StageInitializer.connecte).getExerciceQCMSuivant()));
			});
			
			exerciceMenu.getItems().addAll(exerciceMenu1, exerciceMenu2);
			
			getMenus().addAll(exerciceMenu);
			
		}
		else if (ct == ConnexionType.DECONNECTE)
		{
			new StudiaMenu();
		}
	}
}
