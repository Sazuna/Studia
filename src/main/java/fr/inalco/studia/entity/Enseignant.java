package fr.inalco.studia.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="enseignant")
public class Enseignant {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="enseignant_id")
	private Long id;
	
	@Column(name="nom")
	private String nom;
	
	public Enseignant() {
	}

	public Enseignant(Long id, String nom) {
		this.id = id;
		this.nom = nom;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
}
