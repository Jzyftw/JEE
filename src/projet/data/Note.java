package projet.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table
public class Note {
	@Id
	@GeneratedValue
	int id;
	
    float note;	
    
	@ManyToOne
	private Etudiant etudiant;
	
	public Note() {
		super();
	}  
		
	public int getId() {
		return this.id;
	}
	public void setId(Integer id) {
		this.id = id;
	}   
	
	
	public float getNote() {
		return this.note;
	}
	public void setNote(Float note){
		this.note = note;
	}
	
	
	public Etudiant getEtudiant() {
		return this.etudiant;
	}
	public void setEtudiant(Etudiant etu) {
		this.etudiant = etu;
	}

}