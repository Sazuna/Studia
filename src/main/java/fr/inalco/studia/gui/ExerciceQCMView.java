package fr.inalco.studia.gui;

import fr.inalco.studia.entity.exercices.ExerciceQCM;
import fr.inalco.studia.entity.reponses.ReponseACocher;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ExerciceQCMView extends VBox {
	
	public ExerciceQCMView(ExerciceQCM exercice)
	{
		setSpacing(10);
		setPadding(new Insets(10));
		
		String consigne = exercice.getConsigne();

        Label questionLabel = new Label(consigne);
        getChildren().add(questionLabel);
		
		for (ReponseACocher reponse: exercice.getReponses())
		{
			CheckBox checkBox = new CheckBox(reponse.getReponseString());
			getChildren().add(checkBox);
		}
		
		getChildren().add(new Button("Valider"));
	}
}
