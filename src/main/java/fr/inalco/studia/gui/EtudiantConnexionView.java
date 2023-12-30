package fr.inalco.studia.gui;

import fr.inalco.studia.entity.Etudiant;
import fr.inalco.studia.entity.Langage;
import fr.inalco.studia.gui.modules.LangageSelectionToggle;
import fr.inalco.studia.gui.modules.PasswordEditorHBox;
import fr.inalco.studia.gui.modules.ProfileView;
import fr.inalco.studia.repositories.EtudiantRepository;
import fr.inalco.studia.repositories.EtudiantRepositoryImpl;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class EtudiantConnexionView extends VBox implements View {

	private boolean hasMessageErreur = false;

	private boolean langageModif = false;

	public EtudiantConnexionView()
	{
		EtudiantRepository etuRepo = new EtudiantRepositoryImpl();
		setSpacing(10);
		setPadding(new Insets(10));

		Label pseudoLabel = new Label("Nom d'utilisateur");
		TextField tfPseudo = new TextField("");
		Button creationEtudiant = new Button("Connexion étudiant");
		getChildren().addAll(pseudoLabel, tfPseudo, creationEtudiant);
		creationEtudiant.setOnAction(e -> {
			if (tfPseudo.getText().length() < 4)
			{
				setMessageErreur("Le nom d'utilisateur doit faire au moins 4 caractères.");
				return;
			}
			Etudiant etudiant = etuRepo.findByPseudo(tfPseudo.getText());
			if (etudiant == null)
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
				//tfMdp.setVisible(true);
				PasswordEditorHBox passwordEditor = new PasswordEditorHBox();
				//getChildren().add(8, new Label("Retapez votre mot de passe"));
				//getChildren().add(9, new TextField(""));
				Label lblLangage = new Label("Langage que vous souhaitez étudier");
				LangageSelectionToggle l = new LangageSelectionToggle(Langage.ANGLAIS);
				getChildren().addAll(lblNom, tfNom, lblPrenom, tfPrenom, lblMdp, passwordEditor, lblLangage, l);
				getChildren().addAll(new Label("Niveau de difficulté"), new Text("1"));
				Button inscription = new Button("Inscription étudiant");
				inscription.setOnAction(e2 ->
				{
					String pseudo = textPseudo.getText();
					String nom = tfNom.getText();
					String prenom = tfPrenom.getText();
					String mdp = passwordEditor.getPassword();
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
						// Création d'un nouvel étudiant
						Etudiant etu = new Etudiant(pseudo, tfNom.getText(), tfPrenom.getText(), passwordEditor.getPassword(), l.getSelectedLangage(), 1, 0, null);
						if (!etuRepo.insert(etu)) System.err.println("erreur insert");
						StageInitializer.setScrollableView(new EtudiantView(etu));
						StageInitializer.connexion(etu);
					}
				});
				getChildren().add(inscription);
			}
			else
			{
				Text textPseudo = new Text(((TextField)getChildren().get(1)).getText());
				clear();
				Label lblMdp = new Label("Mot de passe");
				PasswordEditorHBox passwordEditor = new PasswordEditorHBox();

				Button connexion = new Button("Connexion étudiant");
				connexion.setOnAction(e2 -> {
					if (etudiant.compareMdp(passwordEditor.getPassword()))
					{
						// TODO On ouvre la vue de l'étudiant et on l'enregistre dans une session.
						StageInitializer.setScrollableView(new EtudiantView(etudiant));
						StageInitializer.connexion(etudiant);
					}
					else
					{
						setMessageErreur("Mot de passe incorrect");
					}
				});
				getChildren().addAll(new Label("Nom d'utilisateur"), textPseudo, lblMdp, passwordEditor, connexion);
			}
		});
	}
	
	/**
	 * Pour la modification du profile
	 * TODO gestion du mdp vide
	 * @param etudiant
	 */
	public EtudiantConnexionView(Etudiant etudiant) {
		EtudiantRepository enRepo = new EtudiantRepositoryImpl();
		setSpacing(10);
		setPadding(new Insets(10));

		Label pseudoLabel = new Label("Nom d'utilisateur");
		Text tfPseudo = new Text(etudiant.getPseudo()); // Le nom d'utilisateur n'est pas modifiable
		getChildren().addAll(pseudoLabel, tfPseudo);
		Label lblNom = new Label("Nom");
		TextField tfNom = new TextField(etudiant.getNom());
		Label lblPrenom = new Label("Prénom");
		TextField tfPrenom = new TextField(etudiant.getPrenom());
		Label lblMdp = new Label("Nouveau mot de passe");
		//PasswordField tfMdp = new PasswordField();
		PasswordEditorHBox tfMdp = new PasswordEditorHBox();
		//getChildren().add(8, new Label("Retapez votre mot de passe"));
		//getChildren().add(9, new TextField(""))
		getChildren().addAll(lblNom, tfNom, lblPrenom, tfPrenom, lblMdp, tfMdp);
		
		LangageSelectionToggle lsv = new LangageSelectionToggle(etudiant.getLangage());
		getChildren().addAll(new Label("Langage"), lsv);
		
		Button inscription = new Button("Enregistrer");
		inscription.setOnAction(e2 ->
		{
			String nom = tfNom.getText();
			String prenom = tfPrenom.getText();
			String mdp = tfMdp.getPassword();
			Langage langage = lsv.getSelectedLangage();
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
			else if (langage != etudiant.getLangage() && !langageModif) // N'affiche le message qu'une seule fois.
			{
				this.setMessageErreur("Vous avez décidé de changé de langage. Toute votre progression sera perdue. Re-cliquez pour valider.");
				langageModif = true;
			}
			else
			{
				etudiant.setNom(nom);
				etudiant.setPrenom(prenom);
				if (mdp.length() >=  8) // Mise à jour uniquement si le mdp a été modifié
					etudiant.setMdp(mdp);
				if (langageModif)
				{
					etudiant.setLangage(langage);
					// Réinitialisation
					etudiant.updateScore(-etudiant.getScore());
					etudiant.setDernierExerciceQCM(null);
					etudiant.setNiveau(1);
				}
				if (!enRepo.insert(etudiant)) System.err.println("erreur insert");
				StageInitializer.setScrollableView(new EtudiantView(etudiant));
				// L'étudiant était déjà connecté, donc pas besoin d'appeler StageInitializer.connexion(ens);
				StageInitializer.setProfileView(new ProfileView(etudiant)); // Par contre on met à jour ses informations
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
