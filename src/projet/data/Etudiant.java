package projet.data;


import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: Groupe
 *
 */
@Entity
@Table
public class Etudiant implements Serializable {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(nullable=false) 
	private String prenom;
	
	@Column(nullable=false)
	private String nom;
	
	private int nbAbsences;
	
	@ManyToOne
	private Groupe groupe;
	
	@OneToMany(mappedBy="etudiant", fetch=FetchType.LAZY)	// LAZY = fetch when needed, EAGER = fetch immediately
	private List<Note> notes;
	
	private static final long serialVersionUID = 1L;

	public Etudiant() {
		super();
		nbAbsences = 0;
	}  
	
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}   
	public String getPrenom() {
		return this.prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}   
	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public Groupe getGroupe() {
		return this.groupe;
	}
	
	public void setGroupe(Groupe groupe) {
        this.groupe = groupe;
        if (!groupe.getEtudiants().contains(this)) {
        	groupe.getEtudiants().add(this);
        }
    }
	
	public List<Note> getNotes() {
		return this.notes;
	}
	public void setNotes(List<Note> note) {
        this.notes = note;
    }
	
	public int getNbAbsences() {
		return nbAbsences;
	}

	public void setNbAbsences(int nbAbsences) {
		this.nbAbsences = nbAbsences;
	}
   
	@Override
	public String toString() {
		return "[" + this.getId() + "] " + this.getPrenom() + " " + this.getNom();
	}
}
