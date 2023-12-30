package fr.inalco.studia.gui.modules;

import fr.inalco.studia.entity.Enseignant;
import fr.inalco.studia.entity.Langage;
import fr.inalco.studia.entity.exercices.Exercice;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class ExerciceModule extends BorderPane { // extends BorderPane // extends BorderPane

	public ExerciceModule(Exercice exercice)
	{
		Enseignant createur = exercice.getCreateur();
		Langage langage = exercice.getLangage();
		
		String consigne = exercice.getConsigne();
		if (consigne.length() > 40)
		{
			consigne = consigne.substring(0, 40);
		}
		
		this.setTop(new Text(consigne));
		this.setLeft(new Text("Langage : " + langage.getLangage()));
		this.setCenter(new Text(" "+ exercice.getType()));
		this.setRight(new Text(" " + exercice.getNiveau()));
		this.setBottom(new Text("Créateur : " + createur.getPseudo() + " (" + createur.getNom() + " " + createur.getPrenom() + ")"));
		
		this.setBorder(new Border(new BorderStroke(null, null, Color.BLACK, null, null,null, BorderStrokeStyle.SOLID, null, null, BorderWidths.DEFAULT, new Insets(20))));
		this.setBackground(new Background(new BackgroundFill(Color.WHITE, null, new Insets(10))));
		
		this.setOnMouseEntered(e -> {
			this.setBorder(new Border(new BorderStroke(null, null, Color.AQUAMARINE, null, null,null, BorderStrokeStyle.SOLID, null, null, BorderWidths.DEFAULT, new Insets(20))));
		});
		this.setOnMouseExited(e -> {
			this.setBorder(new Border(new BorderStroke(null, null, Color.BLACK, null, null,null, BorderStrokeStyle.SOLID, null, null, BorderWidths.DEFAULT, new Insets(20))));
		});
		
	}

}
