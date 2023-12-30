package fr.inalco.studia.entity;

import fr.inalco.studia.gui.ConnexionType;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;

@MappedSuperclass
public abstract class Utilisateur {

	@Id
	@Column(name="pseudo")
	@NotNull
	private String pseudo;

	@Column(name="nom")
	@NotNull
	private String nom;
	
	@Column(name="prenom")
	@NotNull
	private String prenom;

	@Column(name="mdp")
	@NotNull
	private String mdp;
	
	public abstract ConnexionType getConnexionType();

	public Utilisateur() {
	}

	public Utilisateur(String pseudo, String nom, String prenom, String mdp) {
		setPseudo(pseudo);
		setNom(nom);
		setPrenom(prenom);
		setMdp(mdp);
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public void setMdp(String mdp) {
		this.mdp = hash(mdp);
	}

	public boolean compareMdp(String mdp) {
		return this.mdp.equals(hash(mdp));
	}

	public String getMdp() {
		return this.mdp;
	}
	
	private String hash(String mdp)
	{
		return Integer.toString(mdp.hashCode());
	}
}
