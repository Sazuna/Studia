package fr.inalco.studia.gui;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import fr.inalco.studia.StudiaApplicationUI.StageReadyEvent;
import fr.inalco.studia.entity.Utilisateur;
import fr.inalco.studia.gui.modules.ProfileView;
import fr.inalco.studia.gui.modules.StudiaMenu;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

@Component
public class StageInitializer implements ApplicationListener<StageReadyEvent> {

	private static BorderPane borderPane;
	
	private static ScrollPane scrollPane;
	
	// Gestion de la session
	public static Utilisateur connecte = null;
	
	public static ConnexionType connexionType = ConnexionType.DECONNECTE;
	
	public static Stage stage;
	@Override
	public void onApplicationEvent(StageReadyEvent event) {
	    borderPane = new BorderPane();
	    borderPane.setTop(new StudiaMenu());
		scrollPane = new ScrollPane();
		borderPane.setCenter(scrollPane); // La partie centrale est scrollable

	    stage = event.getStage();
		stage.setTitle("Studia <3");
		stage.setScene(new Scene(borderPane, 800, 600));
		stage.show();
	}
	
	public static void setScrollableView(Node node)
	{
		scrollPane.setContent(node);
		borderPane.setBottom(null); // On supprime le contenu du bas
	}

	public static void setProfileView(ProfileView profileView) {
		profileView.setFillWidth(true);
		profileView.setMaxWidth(400);
		borderPane.setRight(profileView);
	}

	public static void deconnexion() {
		connexionType = ConnexionType.DECONNECTE;
		connecte = null;
		borderPane.setRight(null);
		borderPane.setTop(new StudiaMenu());
		scrollPane.setContent(null);
	}

	public static void setMenu(StudiaMenu studiaMenu) {
		borderPane.setTop(studiaMenu);
	}

	public static void connexion(Utilisateur user) {
		connexionType = user.getConnexionType();
		connecte = user;
		setMenu(new StudiaMenu(connexionType));
		setProfileView(new ProfileView(user));
	}

	public static void setBottom(Node node) {
		borderPane.setBottom(node);
	}

}
