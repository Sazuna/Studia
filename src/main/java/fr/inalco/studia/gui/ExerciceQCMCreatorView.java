package fr.inalco.studia.gui;

import fr.inalco.studia.entity.Enseignant;
import fr.inalco.studia.entity.Langage;
import fr.inalco.studia.entity.exercices.ExerciceQCM;
import fr.inalco.studia.entity.reponses.ReponseACocher;
import fr.inalco.studia.gui.modules.LangageSelectionToggle;
import fr.inalco.studia.gui.modules.NiveauSelectionScroll;
import fr.inalco.studia.repositories.ExerciceQCMRepository;
import fr.inalco.studia.repositories.ExerciceQCMRepositoryImpl;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Cree la vue pour un exercice de type QCM. Cette vue sert aux enseignants qui veulent ajouter de nouveaux exercices de type QCM.
 */
public class ExerciceQCMCreatorView extends VBox implements View {

	public ExerciceQCMCreatorView(ExerciceQCM exercice)
	{
		setSpacing(10);
		setPadding(new Insets(10));
		ExerciceQCMRepository exoRepo = new ExerciceQCMRepositoryImpl();
		

		Langage l = exercice.getLangage();
		if (l == null)
		{
			l = Langage.ANGLAIS;
		}
		LangageSelectionToggle lsv = new LangageSelectionToggle(l);
		lsv.setOnMouseClicked(e -> {
			exercice.setLangage(lsv.getSelectedLangage()); // Mise à jour du langage de l'exercice
		});
		getChildren().addAll(new Label("Langage"), lsv);
		
		NiveauSelectionScroll nsv = new NiveauSelectionScroll(exercice.getNiveau());
		HBox labelHBox = new HBox(new Label("Niveau"), nsv.getNiveauTxt());
		labelHBox.setSpacing(10);
		labelHBox.setPadding(new Insets(10));
		getChildren().addAll(labelHBox, nsv);

		String consigne = exercice.getConsigne();

		TextField tfConsigne = new TextField(consigne);
		tfConsigne.textProperty().addListener((observable, oldValue, newValue) ->
		{

			exercice.setConsigne(newValue);
		});

		getChildren().addAll(new Label("Consigne"), tfConsigne);

		for (ReponseACocher reponse: exercice.getReponses())
		{
			getChildren().add(new ReponseHBox(reponse, exercice));
		}

		Button ajouter = new Button ("Ajouter une réponse");
		ajouter.setOnAction(e -> {
			ReponseACocher reponse = new ReponseACocher(false, "");
			exercice.addReponse(reponse);
			getChildren().add(getChildren().size() - 1, new ReponseHBox(reponse, exercice));
		});

		Button enregistrer = new Button("Enregistrer");
		enregistrer.setOnAction(e -> {
			exercice.setLangage(lsv.getSelectedLangage());
			exercice.setNiveau(nsv.getNiveau());
			if (!exoRepo.insert(exercice))
				System.err.println("erreur insert exerciceQCM");
			else
			{
				Enseignant ens = ((Enseignant) StageInitializer.connecte);
				if (!ens.getExercicesCrees().contains(exercice))
					ens.addExercice(exercice); // Mise à jour des exercices de l'enseignant
					// Pas besoin de mise à jour si c'est une modification
				StageInitializer.setScrollableView(new EnseignantView((Enseignant) StageInitializer.connecte));
			}
		});
		
		Button suivant = new Button("Suivant");
		suivant.setOnAction(e -> {
			exercice.setLangage(lsv.getSelectedLangage());
			exercice.setNiveau(nsv.getNiveau());
			if (!exoRepo.insert(exercice))
				System.err.println("erreur insert");
			else
			{
				Enseignant ens = ((Enseignant) StageInitializer.connecte);
				if (!ens.getExercicesCrees().contains(exercice))
					ens.addExercice(exercice); // Mise à jour des exercices de l'enseignant
					// Pas besoin de mise à jour si c'est une modification
				StageInitializer.setScrollableView(new ExerciceQCMCreatorView(new ExerciceQCM((Enseignant) StageInitializer.connecte)));
			}
		});
		
		HBox boutons = new HBox();
		boutons.getChildren().addAll(ajouter, enregistrer, suivant);
		boutons.setPadding(new Insets(10));
		boutons.setSpacing(10);
		getChildren().add(boutons);

	}

	public void delReponse(ReponseHBox reponse)
	{
		this.getChildren().remove(reponse);
	}

	class ReponseHBox extends HBox
	{
		public ReponseHBox(ReponseACocher reponse, ExerciceQCM exercice)
		{
			setSpacing(10);
			setPadding(new Insets(10));

			CheckBox cb = new CheckBox();
			cb.setSelected(reponse.getReponse());

			cb.setOnAction(e -> {
				reponse.setReponse(cb.isSelected());
			});


			TextField tf = new TextField(reponse.getReponseString());
			tf.textProperty().addListener((observable, oldValue, newValue) ->
			{
				reponse.setReponseString(newValue);
			});

			Button supprimer = new Button("Supprimer");
			supprimer.setOnAction(e -> {
				// Supprimer l'exo qcm dans l'exercice et dans la VBox.
				ExerciceQCMCreatorView.this.delReponse(this);
				exercice.removeReponse(reponse);
				StageInitializer.stage.show();
			});

			super.getChildren().addAll(tf, cb, supprimer);
		}
	}


	public void addReponse()
	{
		this.getChildren().add(getChildren().size() - 1, new HBox( new TextField(), new CheckBox()));
	}
}
