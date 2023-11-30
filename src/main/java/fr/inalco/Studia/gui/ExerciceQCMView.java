package fr.inalco.Studia.gui;

import fr.inalco.Studia.entity.exercices.ExerciceQCM;
import fr.inalco.Studia.entity.reponses.ReponseACocher;
import javafx.geometry.Insets;
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
			
	}
}