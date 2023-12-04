package fr.inalco.Studia.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="etudiant")
public class Etudiant {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="etudiant_id")
	private Long id;
	
	@Column(name="nom")
	private String nom;
	
	/**
	 * Un langage par étudiant
	 */
	@Column(name="langage")
	private Langage langage;
	
	@Column(name="niveau")
	private short niveau;
	
	public Etudiant() {
	}

	public Etudiant(Long id, String nom, Langage langage, byte niveau) {
		super();
		this.id = id;
		this.nom = nom;
		this.langage = langage;
		this.niveau = niveau;
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

	public Langage getLangage() {
		return langage;
	}

	public void setLangage(Langage langage) {
		this.langage = langage;
	}

	public short getNiveau() {
		return niveau;
	}

	public void setNiveau(byte niveau) {
		this.niveau = niveau;
	}
}
