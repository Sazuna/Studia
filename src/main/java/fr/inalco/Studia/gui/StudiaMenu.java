package fr.inalco.Studia.gui;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class StudiaMenu extends MenuBar{
	
	public StudiaMenu()
	{
		Menu exerciceMenu = new Menu("Exercice");
		//MenuBar mb = exerciceMenu.generateMenuBar();
		MenuItem exerciceMenu1 = new MenuItem("Mes exercices");
		MenuItem exerciceMenu2 = new MenuItem("S'exercer");
		exerciceMenu.getItems().addAll(exerciceMenu1, exerciceMenu2);
		
		Menu connexionMenu = new Menu("Connexion");
		MenuItem connexionMenu1 = new MenuItem("Etudiant");
		MenuItem connexionMenu2 = new MenuItem("Enseignant");
		connexionMenu.getItems().addAll(connexionMenu1, connexionMenu2);
		
		getMenus().addAll(exerciceMenu, connexionMenu);
	}
	
	/*
	public static MenuBar menu = generateMenuBar();

	private static MenuBar generateMenuBar()
	{
		Menu exerciceMenu = new Menu("Exercice");
		//MenuBar mb = exerciceMenu.generateMenuBar();
		MenuItem exerciceMenu1 = new MenuItem("Mes exercices");
		MenuItem exerciceMenu2 = new MenuItem("S'exercer");
		exerciceMenu.getItems().addAll(exerciceMenu1, exerciceMenu2);
		
		Menu connexionMenu = new Menu("Connexion");
		MenuItem connexionMenu1 = new MenuItem("Etudiant");
		MenuItem connexionMenu2 = new MenuItem("Enseignant");
		connexionMenu.getItems().addAll(connexionMenu1, connexionMenu2);

		MenuBar mb = new MenuBar();
		mb.getMenus().addAll(exerciceMenu, connexionMenu);

		return mb;
	}*/
}
