/**
 * 
 */

/**
 * @author hb
 *
 */
package projet.controleur;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import projet.data.Etudiant;
import projet.data.EtudiantDAO;
import projet.data.GestionFactory;
import projet.data.Groupe;
import projet.data.GroupeDAO;
import projet.data.Note;
import projet.data.NoteDAO;

@SuppressWarnings("serial")
public class Controleur extends HttpServlet {

	private String urlEtudiants;
	private String urlGroupes;
	private String urlDetails;
	private String urlEditS;
	private String urlSaveS;
	private String urlEditMark;
	private String urlAddMark;
	private String urlShowStdtGrps;
	private String urlSetGrpNotes;
	private String urlAddEtu;

	// INIT
	@Override
	public void init() throws ServletException {
		// RÃ©cupÃ©ration des URLs en paramÃ¨tre du web.xml
		urlEtudiants = getServletConfig().getInitParameter("urlEtudiants");
		urlGroupes = getServletConfig().getInitParameter("urlGroupes");
		urlDetails = getServletConfig().getInitParameter("urlDetails");
		urlEditS = getServletConfig().getInitParameter("urlEditS");
		urlSaveS = getServletConfig().getInitParameter("urlSaveS");
		urlEditMark = getServletConfig().getInitParameter("urlEditMark");
		urlAddMark = getServletConfig().getInitParameter("urlAddMark");
		urlShowStdtGrps = getServletConfig().getInitParameter("urlShowStdtGrps");
		urlSetGrpNotes = getServletConfig().getInitParameter("urlSetGrpNotes");
		urlAddEtu = getServletConfig().getInitParameter("urlAddEtu");
		
		// CrÃ©ation de la factory permettant la crÃ©ation d'EntityManager
		// (gestion des transactions)
		GestionFactory.open();

		///// INITIALISATION DE LA BD
		// Normalement l'initialisation se fait directement dans la base de donnÃ©es
		if ((GroupeDAO.getAll().size() == 0) && (EtudiantDAO.getAll().size() == 0)) {

			// Creation des groupes
			Groupe MIAM = GroupeDAO.create("miam");
			Groupe SIMO = GroupeDAO.create("SIMO");
			Groupe MESSI = GroupeDAO.create("MESSI");

			// Creation des Ã©tudiants
			EtudiantDAO.create("Francis", "Brunet-Manquat", MIAM);
			EtudiantDAO.create("Philippe", "Martin", MIAM);
			EtudiantDAO.create("Mario", "Cortes-Cornax", MIAM);
			EtudiantDAO.create("FranÃ§oise", "Coat", SIMO);
			EtudiantDAO.create("Laurent", "Bonnaud", MESSI);
			EtudiantDAO.create("SÃ©bastien", "Bourdon", MESSI);
			EtudiantDAO.create("Mathieu", "Gatumel", SIMO);
			
			//création des notes
			NoteDAO.create(5.F,EtudiantDAO.retrieveById(4));
			NoteDAO.create(13.3F,EtudiantDAO.retrieveById(4));
			NoteDAO.create(15.3F,EtudiantDAO.retrieveById(5));
			
		}
	}

	@Override
	public void destroy() {
		super.destroy();

		// Fermeture de la factory
		GestionFactory.close();
	}

	// POST
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// on passe la main au GET
		doGet(request, response);
	}

	// GET
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		// On rÃ©cupÃ¨re le path
		String action = request.getPathInfo();
		
		if (action == null) {
			action = "/etudiants";
		}

		// ExÃ©cution action
		if (action.equals("/etudiants")) {
			doEtudiants(request, response);
		} else if (action.equals("/groupes")) {
			doGroupes(request, response);
		} else if (action.equals("/details")) {
			doDetails(request, response);
		} else if (action.equals("/editS")) {
			doEditS(request, response);
		} else if (action.equals("/saveS")) {
			doSaveS(request, response);
		} else if (action.equals("/editMark")) {
			doEditMark(request, response);
		} else if (action.equals("/saveMark")) {
			doSaveMark(request, response);
		} else if (action.equals("/addMark")) {
			doAddMark(request, response);
		}else if (action.equals("/saveNewMark")) {
			doSaveNewMark(request, response);	
		}else if (action.equals("/showStdGrps")) {
			doShowStdtGrps(request, response);	
		}else if (action.equals("/setGrpNotes")) {
			doSetGrpNotes(request, response);	
		}else if (action.equals("/saveGrpNotes")) {
			doSaveGrpNotes(request, response);	
		}else if (action.equals("/newEtu")) {
			doNewEtu(request, response);	
		}else if (action.equals("/addEtu")) {
			doAddEtu(request, response);	
		}else {
			// Autres cas
			doEtudiants(request, response);
		}
	}

	//création d'un étudiant
	private void doAddEtu(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String prenom = request.getParameter("prenom");
		String nom = request.getParameter("nom");
		int GrpId = Integer.parseInt(request.getParameter("grpId"));
		Groupe groupe = GroupeDAO.getById(GrpId);
		
		Etudiant etudiant = EtudiantDAO.create(prenom, nom, groupe);
		
		// Ajouter les Ã©tudiants Ã  la requÃªte pour affichage
		request.setAttribute("etudiant", etudiant);
		
		//
		loadJSP(urlDetails, request, response);
	}
	
	private void doNewEtu(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		loadJSP(urlAddEtu, request, response);
	}
	

	//Enregistre les notes pour chaque étudiant du groupe
	private void doSaveGrpNotes(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//On récupère  l'id du groupe
		int GrpId = Integer.parseInt(request.getParameter("grpId"));
		//On compte le nb d'étudiants appartenant au groupe
		int nb = EtudiantDAO.getAllByGroup(GrpId).size();
		//On ajoute la note pour chaque étudiant
		for(int i=0;i<nb;i++){
			float note = Float.parseFloat(request.getParameter("note"+i));
			int idEtu = Integer.parseInt(request.getParameter("IdEtu"+i));
			NoteDAO.create(note, EtudiantDAO.retrieveById(idEtu));
		}
		
		loadJSP(urlSaveS, request, response);
	}

	//Créer des notes de groupe
	private void doSetGrpNotes(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//récupération des paramètres
		int GrpId = Integer.parseInt(request.getParameter("Id"));
		//On récupère tous les étudiants associés au groupe
		List<Etudiant> etudiants = EtudiantDAO.getAllByGroup(GrpId);
		//On les passe en paramètre à la requête
		request.setAttribute("etudiants", etudiants);
		request.setAttribute("grpId", GrpId);
		loadJSP(urlSetGrpNotes, request, response);	
	}

	//montrer étudiants en fonction de leur groupe
	private void doShowStdtGrps(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//récupération des paramètres
		int GrpId = Integer.parseInt(request.getParameter("Id"));
		// RÃ©cupÃ©rer les Ã©tudiants
		List<Etudiant> etudiants = EtudiantDAO.getAllByGroup(GrpId);
		
		// Ajouter les Ã©tudiants Ã  la requÃªte pour affichage
		request.setAttribute("etudiants", etudiants);
		
		//
		loadJSP(urlShowStdtGrps, request, response);
		
	}

	//Enregistrer une nouvelle note
	private void doSaveNewMark(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//récupération des paramètres
		int Id = Integer.parseInt(request.getParameter("Id"));
		Float N = Float.parseFloat(request.getParameter("Note"));
		
		//On crée la nouvelle note
		NoteDAO.create(N,EtudiantDAO.retrieveById(Id));	
		
		loadJSP(urlSaveS, request, response);
	}

	private void doAddMark(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		//récupération de l'id de l'étudiant à qui sera associé la note
		int Id = Integer.parseInt(request.getParameter("Id"));
		
		request.setAttribute("Id", Id);
		loadJSP(urlAddMark, request, response);
	}

	//Ajout de note
	private void doSaveMark(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		int NoteId = Integer.parseInt(request.getParameter("Id"));
		float NoteNote = Float.parseFloat(request.getParameter("Note"));
		Note note = NoteDAO.retrieveById(NoteId);
		note.setNote(NoteNote);
		NoteDAO.update(note);
		
		loadJSP(urlSaveS, request, response);
	}
	
	//Redirige vers la modification de note
	private void doEditMark(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//récupération du paramètre
		int NoteId = Integer.parseInt(request.getParameter("Id"));
		//récupération de la note
		Note note = NoteDAO.retrieveById(NoteId);
		//passage paramètre requête
		request.setAttribute("Note", note);
		
		loadJSP(urlEditMark, request, response);
	}

	//Sauvegarde des modifications effectuées
	private void doSaveS(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// 
		
		//récupération des paramètres
		int id =  Integer.parseInt(request.getParameter("Id"));
		int nbAbs =  Integer.parseInt(request.getParameter("nbAbs"));
		
		
		//étudiant modifié
		Etudiant etudiant = EtudiantDAO.retrieveById(id);

		etudiant.setNbAbsences(nbAbs);
//		etudiant.setGroupe(grp);
		
		
		//étudiant à modifier
		EtudiantDAO etdao= new EtudiantDAO();
		etdao.update(etudiant);
		
		loadJSP(urlSaveS, request, response);
	}

	//Envoie l'étudiant pour modification
	private void doEditS(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//récupération des paramètres
		int id =  Integer.parseInt(request.getParameter("Id"));

		// Récupère l'étudiant
		Etudiant etudiant = EtudiantDAO.retrieveById(id);
		
		// Ajouter les Ã©tudiants Ã  la requÃªte pour affichage
		request.setAttribute("etudiant", etudiant);
		
		loadJSP(urlEditS, request, response);
	}

	// ///////////////////////
	//
	private void doEtudiants(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// RÃ©cupÃ©rer les Ã©tudiants
		List<Etudiant> etudiants = EtudiantDAO.getAll();
		
		// Ajouter les Ã©tudiants Ã  la requÃªte pour affichage
		request.setAttribute("etudiants", etudiants);
		
		//
		loadJSP(urlEtudiants, request, response);
	}
	
	// ///////////////////////
		//
	private void doDetails(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {

			int id =  Integer.parseInt(request.getParameter("IdEtu"));
			
			// Récupère l'étudiant
			Etudiant etudiant = EtudiantDAO.retrieveById(id);
			
			// Ajouter les Ã©tudiants Ã  la requÃªte pour affichage
			request.setAttribute("etudiant", etudiant);
			
			//
			loadJSP(urlDetails, request, response);
		}
	
	// ///////////////////////
	//
	private void doGroupes(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// RÃ©cupÃ©rer les Ã©tudiants
		List<Groupe> groupes = GroupeDAO.getAll();
		
		// Ajouter les Ã©tudiants Ã  la requÃªte pour affichage
		request.setAttribute("groupes", groupes);
		
		//
		loadJSP(urlGroupes, request, response);
	}

	/**
	 * Charge la JSP indiquÃ©e en paramÃ¨tre
	 * 
	 * @param url
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void loadJSP(String url, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// L'interface RequestDispatcher permet de transfÃ©rer le contrÃ´le Ã  une
		// autre servlet
		// Deux mÃ©thodes possibles :
		// - forward() : donne le contrÃ´le Ã  une autre servlet. Annule le flux
		// de sortie de la servlet courante
		// - include() : inclus dynamiquement une autre servlet
		// + le contrÃ´le est donnÃ© Ã  une autre servlet puis revient Ã  la servlet
		// courante (sorte d'appel de fonction).
		// + Le flux de sortie n'est pas supprimÃ© et les deux se cumulent

		ServletContext sc = getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher(url);
		rd.forward(request, response);
	}

}