package fr.inalco.studia.gui;

import java.util.ArrayList;

import fr.inalco.studia.entity.Etudiant;
import fr.inalco.studia.entity.exercices.ExerciceQCM;
import fr.inalco.studia.entity.reponses.ReponseACocher;
import fr.inalco.studia.gui.modules.ProfileView;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class ExerciceQCMView extends GridPane implements View {
	
	public ExerciceQCMView(ExerciceQCM exercice)
	{
		setPadding(new Insets(10));
		
		if (exercice == null)
		{
			this.add(new Text("Aucun exercice trouvé pour votre langue et votre niveau."), 1, 1);
			return;
		}
		
		String consigne = exercice.getConsigne();

		Label niveau = new Label("Niveau de l'exercice : " + exercice.getNiveau());
        this.add(niveau, 1, 1);
        Label question = new Label(consigne);
        question.setPadding(new Insets(10));
        this.add(question, 1, 2);
        //getChildren().add(questionLabel);
        ArrayList<CheckBox> reponsesCB = new ArrayList<CheckBox>();
		int i = 2;
		for (ReponseACocher reponse: exercice.getReponses())
		{
			CheckBox checkBox = new CheckBox(reponse.getReponseString());
			checkBox.setPadding(new Insets(10));
			reponsesCB.add(checkBox);
			i++;
			this.add(checkBox, 1, i);
		}
		i++;
		
		Button valider = new Button("Valider");
		valider.setPadding(new Insets(10));
		this.add(valider, 1, 1 + i);
		
		valider.setOnAction(e -> {
			ArrayList<Boolean> reponses = new ArrayList<Boolean>();
			for (CheckBox cb: reponsesCB)
			{
				reponses.add(cb.isSelected());
			}
			double score = exercice.score(reponses);
			int score1 = (int) (score * (float)reponses.size() * (float)10);
			
			
			// Affichage de la bonne réponse et bouton continuer
			int j = 2;
			for (ReponseACocher reponse: exercice.getReponses())
			{
				j ++;
				CheckBox reponseCheckbox = new CheckBox();
				reponseCheckbox.setSelected(reponse.getReponse());
				reponseCheckbox.setOnAction(e2 -> {reponseCheckbox.setSelected(reponse.getReponse());}); // La valeur est non modifiable
				this.add(reponseCheckbox, 2, j);
			}
			j ++;
			valider.setVisible(false);
			this.add(new Label("Score"), 1, j);
			this.add(new Text(""+score1 + "/" + reponses.size() * 10), 2, j);
			j ++;
			Etudiant etu = (Etudiant)StageInitializer.connecte;
			this.add(new Label("Ancien score"), 1, j);
			this.add(new Text(""+etu.getScore()), 2, j);
			j++;
			// Ajouter le score à l'étudiant
			boolean levelUp = etu.updateScore(score1);
			StageInitializer.setProfileView(new ProfileView(etu));
			this.add(new Label("Nouveau score"), 1, j);
			this.add(new Text(""+etu.getScore()), 2, j);
			j++;
			if (levelUp)
			{
				Text felicitations = new Text("Félicitations, vous êtes passé au niveau suivant !");
				felicitations.setFont(new Font(26));
				StageInitializer.setBottom(felicitations);
			}
			Button suivant = new Button("Suivant");
			suivant.setPadding(new Insets(10));
			this.add(suivant, 1, j);
			

			Etudiant etudiant = (Etudiant)StageInitializer.connecte;
			etudiant.setDernierExerciceQCM(exercice);

			suivant.setOnAction(e2 -> {
				ExerciceQCM exSuivant = etudiant.getExerciceQCMSuivant();
				StageInitializer.setScrollableView(new ExerciceQCMView(exSuivant));
			});
		});
	}
}
