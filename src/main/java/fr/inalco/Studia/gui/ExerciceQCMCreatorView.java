package fr.inalco.Studia.gui;

import fr.inalco.Studia.StudiaEntityManager;
import fr.inalco.Studia.entity.exercices.ExerciceQCM;
import fr.inalco.Studia.entity.reponses.ReponseACocher;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Crée la vue pour un exercice de type QCM. Cette vue sert aux enseignants qui veulent ajouter de nouveaux exercices de type QCM.
 */
public class ExerciceQCMCreatorView extends VBox {

	public ExerciceQCMCreatorView(ExerciceQCM exercice)
	{
		setSpacing(10);
		setPadding(new Insets(10));

		String consigne = exercice.getConsigne();

		TextField tfConsigne = new TextField(consigne);
		tfConsigne.textProperty().addListener((observable, oldValue, newValue) ->
		{

			exercice.setConsigne(newValue);
		});

		getChildren().add(tfConsigne);

		for (ReponseACocher reponse: exercice.getReponses())
		{
			getChildren().add(new ReponseHBox(reponse, exercice));
		}

		Button ajouter = new Button ("Ajouter");
		ajouter.setOnMouseClicked(e -> {
			ReponseACocher reponse = new ReponseACocher(false, "");
			exercice.addReponse(reponse);
			getChildren().add(getChildren().size() - 1, new ReponseHBox(reponse, exercice));
			StageInitializer.stage.show();
		});

		Button valider = new Button("Valider");
		valider.setOnMouseClicked(e -> {if (!insert(exercice)) System.out.println("erreur insert");;});
		HBox boutons = new HBox();
		boutons.getChildren().addAll(valider, ajouter);
		getChildren().add(boutons);
	}

	boolean insert(ExerciceQCM exercice)
	{
		EntityManager em = StudiaEntityManager.getEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			em.merge(exercice);
			for (ReponseACocher reponse : exercice.getReponses()) {
				reponse.setExerciceQCM(exercice);
				em.merge(reponse);
			}
			tx.commit();
		} catch (Exception ex) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
				return false;
			}
			ex.printStackTrace();
		} finally {
			em.close();
		}
		return true;
	}

	public void delReponse(ReponseHBox reponse)
	{
		this.getChildren().remove(reponse);
	}

	class ReponseHBox extends HBox
	{
		public ReponseHBox(ReponseACocher reponse, ExerciceQCM exercice)
		{
			CheckBox cb = new CheckBox();
			cb.setSelected(reponse.getReponse());

			cb.setOnMouseClicked(e -> {
				reponse.setReponse(cb.isSelected());
			});


			TextField tf = new TextField(reponse.getReponseString());
			tf.textProperty().addListener((observable, oldValue, newValue) ->
			{
				reponse.setReponseString(newValue);
				System.out.println(newValue);
			});

			Button supprimer = new Button("Supprimer");
			supprimer.setOnMouseClicked(e -> {
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
