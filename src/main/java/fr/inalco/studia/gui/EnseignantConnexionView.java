package fr.inalco.studia.gui;

import fr.inalco.studia.entity.Enseignant;
import fr.inalco.studia.gui.modules.PasswordEditorHBox;
import fr.inalco.studia.gui.modules.ProfileView;
import fr.inalco.studia.repositories.EnseignantRepository;
import fr.inalco.studia.repositories.EnseignantRepositoryImpl;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class EnseignantConnexionView extends VBox implements View {

	private boolean hasMessageErreur = false;

	public EnseignantConnexionView()
	{
		EnseignantRepository enRepo = new EnseignantRepositoryImpl();
		setSpacing(10);
		setPadding(new Insets(10));

		Label pseudoLabel = new Label("Nom d'utilisateur");
		TextField tfPseudo = new TextField("");
		Button creationEnseignant = new Button("Connexion enseignant");
		getChildren().addAll(pseudoLabel, tfPseudo, creationEnseignant);
		creationEnseignant.setOnAction(e -> {
			if (tfPseudo.getText().length() < 4)
			{
				setMessageErreur("Le nom d'utilisateur doit faire au moins 4 caractères.");
				return;
			}
			Enseignant enseignant = enRepo.findByPseudo(tfPseudo.getText());
			if (enseignant == null)
			{
				Text textPseudo = new Text(((TextField)getChildren().get(1)).getText());

				clear();
				getChildren().add(new Label("Nom d'utilisateur"));
				getChildren().add(textPseudo);
				Label lblNom = new Label("Nom");
				TextField tfNom = new TextField("");
				Label lblPrenom = new Label("Prénom");
				TextField tfPrenom = new TextField("");
				Label lblMdp = new Label("Mot de passe");
				//PasswordField tfMdp = new PasswordField();
				PasswordEditorHBox tfMdp = new PasswordEditorHBox();
				//getChildren().add(8, new Label("Retapez votre mot de passe"));
				//getChildren().add(9, new TextField(""))
				getChildren().addAll(lblNom, tfNom, lblPrenom, tfPrenom, lblMdp, tfMdp);
				Button inscription = new Button("Inscription enseignant");
				inscription.setOnAction(e2 ->
				{
					String pseudo = textPseudo.getText();
					String nom = tfNom.getText();
					String prenom = tfPrenom.getText();
					String mdp = tfMdp.getPassword();
					if (nom.length() < 2)
					{
						this.setMessageErreur("Le nom doit faire au moins 2 caractères.");
					}
					else if (prenom.length() < 2)
					{
						this.setMessageErreur("Le prénom doit faire au moins 2 caractères.");
					}
					else if (mdp.length() < 8)
					{
						this.setMessageErreur("Le mot de passe doit faire au moins 8 caractères.");
					}
					else
					{
						Enseignant ens = new Enseignant(pseudo, nom, prenom, mdp);
						if (!enRepo.insert(ens)) System.err.println("erreur insert");
						StageInitializer.setScrollableView(new EnseignantView(ens));
						StageInitializer.connexion(ens);
					}
				});
				getChildren().add(inscription);
			}
			else
			{
				Text textPseudo = new Text(((TextField)getChildren().get(1)).getText());
				clear();
				Label lblMdp = new Label("Mot de passe");
				PasswordEditorHBox tfMdp = new PasswordEditorHBox();
				Button connexion = new Button("Connexion enseignant");
				connexion.setOnAction(e2 -> {
					if (enseignant.compareMdp(tfMdp.getPassword()))
					{
						StageInitializer.setScrollableView(new EnseignantView(enseignant));
						StageInitializer.connexion(enseignant);
					}
					else
					{
						setMessageErreur("Mot de passe incorrect");
					}
				});
				getChildren().addAll(new Label("Nom d'utilisateur"), textPseudo, lblMdp, tfMdp, connexion);
			}
		});
	}

	/**
	 * Constructeur de ConnexionView qui sert à la modificiation du profil d'un enseignant
	 * @param user
	 */
	public EnseignantConnexionView(Enseignant enseignant)
	{
		EnseignantRepository enRepo = new EnseignantRepositoryImpl();
		setSpacing(10);
		setPadding(new Insets(10));

		Label pseudoLabel = new Label("Nom d'utilisateur");
		Text tfPseudo = new Text(enseignant.getPseudo()); // Le nom d'utilisateur n'est pas modifiable
		getChildren().addAll(pseudoLabel, tfPseudo);
		Label lblNom = new Label("Nom");
		TextField tfNom = new TextField(enseignant.getNom());
		Label lblPrenom = new Label("Prénom");
		TextField tfPrenom = new TextField(enseignant.getPrenom());
		Label lblMdp = new Label("Nouveau mot de passe");
		//PasswordField tfMdp = new PasswordField();
		PasswordEditorHBox tfMdp = new PasswordEditorHBox();
		//getChildren().add(8, new Label("Retapez votre mot de passe"));
		//getChildren().add(9, new TextField(""))
		getChildren().addAll(lblNom, tfNom, lblPrenom, tfPrenom, lblMdp, tfMdp);
		Button inscription = new Button("Enregistrer");
		inscription.setOnAction(e2 ->
		{
			String nom = tfNom.getText();
			String prenom = tfPrenom.getText();
			String mdp = tfMdp.getPassword();
			if (nom.length() < 2)
			{
				this.setMessageErreur("Le nom doit faire au moins 2 caractères.");
			}
			else if (prenom.length() < 2)
			{
				this.setMessageErreur("Le prénom doit faire au moins 2 caractères.");
			}
			else if (mdp.length() > 0 && mdp.length() < 8)
			{
				this.setMessageErreur("Le mot de passe doit faire au moins 8 caractères.");
			}
			else
			{
				enseignant.setMdp(mdp);
				enseignant.setNom(nom);
				enseignant.setPrenom(prenom);
				if (mdp.length() > 0) // Mise à jour uniquement si le mdp a été modifié
					enseignant.setMdp(mdp);
				if (!enRepo.insert(enseignant)) System.err.println("erreur insert");
				StageInitializer.setScrollableView(new EnseignantView(enseignant));
				// L'enseignant était déjà connecté, donc pas besoin d'appeler StageInitializer.connexion(ens);
				StageInitializer.setProfileView(new ProfileView(enseignant)); // Par contre on met à jour ses informations
			}
		});
		getChildren().add(inscription);
	}
	
	private void clear()
	{
		getChildren().clear();
		hasMessageErreur = false;
	}
	
	private void setMessageErreur(String message)
	{

		Text messageErreur = new Text(message);
		if (hasMessageErreur)
		{
			getChildren().set(getChildren().size() - 2, messageErreur);
		}
		else
		{
			getChildren().add(getChildren().size() - 1, messageErreur);
			hasMessageErreur = true;
		}
	}

}
