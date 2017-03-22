package projet.data;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class NoteDAO {

	
	public static Note retrieveById(int id) {
		
		// Creation de l'entity manager
		EntityManager em = GestionFactory.factory.createEntityManager();
			
		//
		Note note = em.find(Note.class, id);
		
	    // Close the entity manager
	    em.close();
				
		return note;
	}
	
	
	public static Note create(Float note, Etudiant etu) {
		
		// Creation de l'entity manager
		EntityManager em = GestionFactory.factory.createEntityManager();
		
		em.getTransaction().begin();

		// create new Note
		Note n = new Note();
		n.setEtudiant(etu);
		n.setNote(note);

		em.persist(n);

		// Commit
		em.getTransaction().commit();

		// Close the entity manager
		em.close();
		
		return n;
	}

	public static Note update(Note note) {
			
			// Creation de l'entity manager
			EntityManager em = GestionFactory.factory.createEntityManager();
			
			//
			em.getTransaction().begin();

			// Attacher une entité persistante (etudiant) à l’EntityManager courant
			em.merge(note);
			
			// Commit
			em.getTransaction().commit();
			
			// Close the entity manager
			em.close();
			
			return note;
	}
	
	public static void remove(Note note) {
		
		// Creation de l'entity manager
		EntityManager em = GestionFactory.factory.createEntityManager();
	
		//
		em.getTransaction().begin();
		
		// Retrouver l'entité persistante et ses liens avec d'autres entités en vue de la suppression
		note = em.find(Note.class, note.getId());
		em.remove(note);
		
		// Commit
		em.getTransaction().commit();
				
		// Close the entity manager
		em.close();
		
	}
	
	public static int removeAll() {

		// Creation de l'entity manager
		EntityManager em = GestionFactory.factory.createEntityManager();

		//
		em.getTransaction().begin();
		
		// RemoveAll
		int deletedCount = em.createQuery("DELETE FROM Note").executeUpdate();

		// Commit
		em.getTransaction().commit();
				
		// Close the entity manager
		em.close();

		return deletedCount;
	}

	// Retourne l'ensemble des notes
	public static List<Note> getAll() {

			// Creation de l'entity manager
			EntityManager em = GestionFactory.factory.createEntityManager();
					
			// Recherche 
			Query q = em.createQuery("SELECT n FROM Note n");

			@SuppressWarnings("unchecked")
			List<Note> listNote = q.getResultList();
			
			return listNote;
		}
		
}

