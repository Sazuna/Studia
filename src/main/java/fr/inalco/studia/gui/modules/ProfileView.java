package fr.inalco.studia.gui.modules;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import fr.inalco.studia.entity.Enseignant;
import fr.inalco.studia.entity.Etudiant;
import fr.inalco.studia.entity.Utilisateur;
import fr.inalco.studia.gui.ConnexionType;
import fr.inalco.studia.gui.EnseignantConnexionView;
import fr.inalco.studia.gui.EtudiantConnexionView;
import fr.inalco.studia.gui.StageInitializer;

public class ProfileView extends VBox {
	
	public ProfileView(Utilisateur user)
	{
		this.setPadding(new Insets(10));
		this.setSpacing(10);
		ConnexionType ct = user.getConnexionType();
		
		Text pseudoTxt = new Text(user.getPseudo());
		pseudoTxt.setFont(new Font(26));
		
		Text nomPrenomTxt = new Text(user.getNom() + " " + user.getPrenom());
		
		Button deconnexion = new Button("Se déconnecter");
		Button modifier = new Button("Modifier mon profile");
		
		deconnexion.setOnAction(e -> {
			StageInitializer.deconnexion();
		});
		
		
		if (ct == ConnexionType.ENSEIGNANT)
		{
			getChildren().addAll(pseudoTxt, nomPrenomTxt);
			modifier.setOnAction(e -> {
				StageInitializer.setScrollableView(new EnseignantConnexionView((Enseignant)user));
			});
			getChildren().addAll(deconnexion, modifier);
		}
		else if (ct == ConnexionType.ETUDIANT)
		{
			Etudiant etu = (Etudiant) user;
			int score = etu.getScore();
			String langage = etu.getLangage().getLangage();
			int niveau = etu.getNiveau();
			getChildren().addAll(pseudoTxt, nomPrenomTxt, new Label("Langage"), new Text("" +langage), new Label("niveau"), new Text(""+niveau), new Label("Score"), new Text("" + score)); 
			modifier.setOnAction(e -> {
				StageInitializer.setScrollableView(new EtudiantConnexionView((Etudiant)user));
			});
			getChildren().addAll(deconnexion, modifier);
		}
		else
		{
			// Pas d'utilisateur connecté
		}
	}

}
