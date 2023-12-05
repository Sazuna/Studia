package fr.inalco.studia.entity.exercices;

import fr.inalco.studia.entity.Langage;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

/**
 * Superclasse de tous les exercices. Ne pas utiliser.
 */
/*
@Entity
@Table(name="exercice")
@Inheritance(strategy = InheritanceType.JOINED)*/
@MappedSuperclass
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // Crée une table par entité héritant de la classe
public abstract class Exercice {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="exercice_id")
	private Long id;
	
	@Column(name="niveau")
	private byte niveau; // 0-128
	
	@Column(name="language")
	private Langage langage;
	
	@Column(name="consigne")
	private String consigne;

	public Exercice(Long id, byte niveau, Langage langage, String consigne) {
		this.id = id;
		this.niveau = niveau;
		this.langage = langage;
		this.consigne = consigne;
	}

	public Exercice() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Exercice(int niveau, Langage langage, String consigne) {
		this.niveau = (byte)niveau;
		this.langage = langage;
		this.consigne = consigne;
	}

	public int getNiveau() {
		return niveau;
	}

	public void setNiveau(int niveau) {
		this.niveau = (byte)niveau;
	}

	public Langage getLangage() {
		return langage;
	}

	public void setLangage(Langage langage) {
		this.langage = langage;
	}

	public String getConsigne() {
		return consigne;
	}

	public void setConsigne(String consigne) {
		this.consigne = consigne;
	}
	
}
