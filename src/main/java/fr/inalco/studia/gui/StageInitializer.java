package fr.inalco.studia.gui;

import java.util.ArrayList;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import fr.inalco.studia.StudiaApplicationUI.StageReadyEvent;
import fr.inalco.studia.entity.Langage;
import fr.inalco.studia.entity.exercices.ExerciceQCM;
import fr.inalco.studia.entity.reponses.ReponseACocher;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

@Component
public class StageInitializer implements ApplicationListener<StageReadyEvent>{

	public static BorderPane borderPane;
	
	public static Stage stage;
	@Override
	public void onApplicationEvent(StageReadyEvent event) {
	    borderPane = new BorderPane();//FXMLLoader.load(getClass().getResource("fxml_example.fxml"));
	    borderPane.setTop(new StudiaMenu());
	    ArrayList<ReponseACocher> reponses = new ArrayList<>();
	    reponses.add(new ReponseACocher(true, "Hello"));
	    reponses.add(new ReponseACocher(false, "Goodbye"));
	    ExerciceQCM exo = new ExerciceQCM((short) 1, Langage.ANGLAIS, "Comment dire 'bonjour' en Anglais ?", reponses);
	    borderPane.setCenter(new ExerciceQCMView(exo));
	    borderPane.setCenter(new ExerciceQCMCreatorView(exo));
	    stage = event.getStage();
		stage.setTitle("Studia <3");
		stage.setScene(new Scene(borderPane, 800, 600));
		stage.show();
	}

}
