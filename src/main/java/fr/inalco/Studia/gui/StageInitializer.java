package fr.inalco.Studia.gui;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import fr.inalco.Studia.StudiaApplicationUI.StageReadyEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

@Component
public class StageInitializer implements ApplicationListener<StageReadyEvent>{

	@Override
	public void onApplicationEvent(StageReadyEvent event) {
	    BorderPane parent = new BorderPane();//FXMLLoader.load(getClass().getResource("fxml_example.fxml"));
	    parent.setTop(StudiaMenu.menu);
	    Stage stage = event.getStage();
		stage.setTitle("Studia <3");
		stage.setScene(new Scene(parent, 800, 600));
		stage.show();
	}

}
