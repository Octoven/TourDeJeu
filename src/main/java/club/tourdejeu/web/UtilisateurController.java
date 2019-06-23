package club.tourdejeu.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import club.tourdejeu.entities.Emprunt;
import club.tourdejeu.entities.RoleUtilisateur;
import club.tourdejeu.entities.Utilisateur;
import club.tourdejeu.metier.IEmpruntMetier;
import club.tourdejeu.metier.IRoleUtilisateurMetier;
import club.tourdejeu.metier.IUtilisateurMetier;

@Controller
public class UtilisateurController {

    // injection des dépendances
    @Autowired
    private IUtilisateurMetier utilisateurMetier;
    @Autowired
    private IEmpruntMetier empruntMetier;
    @Autowired
    private IRoleUtilisateurMetier ruMetier;

    @RequestMapping(value = "/inscription")
    public ModelAndView inscription() {

	ModelAndView mv = new ModelAndView();
	mv.addObject("utilisateur", new Utilisateur());
	mv.setViewName("inscription");

	return mv;
    }

    @RequestMapping(value = "/enregistrerUtilisateur", method = RequestMethod.POST)
    public ModelAndView enregistrerUtilisateur(@Valid Utilisateur u, BindingResult bindingResult) {

	ModelAndView mv = new ModelAndView();

	// verification des contraintes
	if (bindingResult.hasErrors()) {
	    mv.setViewName("inscription");
	    return mv;
	}

	try {
	    utilisateurMetier.enregistrerUtilisateur(u);
	    mv.addObject("utilisateur", u);
	} catch (Exception e) {
	    mv.addObject("exception", e);
	    mv.setViewName("inscription");
	    mv.addObject("utilisateur", u);
	    return mv;
	}

	mv.setViewName("confirmation");

	return mv;
    }

    @RequestMapping(value = "/modifInfosPerso", method = RequestMethod.GET)
    public ModelAndView modifInfosPerso(Authentication authentication) {

	ModelAndView mv = new ModelAndView();
	try {
	    Utilisateur u = utilisateurMetier.findLogged(authentication);
	    mv.addObject("utilisateur", u);
	} catch (Exception e) {
	    mv.addObject("exception", e);
	    mv.setViewName("login");
	    return mv;
	}
	mv.setViewName("modifInfosPerso");
	return mv;
    }

    @RequestMapping(value = "/modifierUtilisateur", method = RequestMethod.POST)
    public ModelAndView modifierUtilisateur(@Valid Utilisateur u, BindingResult bindingResult) {

	ModelAndView mv = new ModelAndView();

	if (bindingResult.hasErrors()) {
	    mv.setViewName("modifInfosPerso");
	    return mv;
	}

	try {
	    utilisateurMetier.modifierUtilisateur(u);
	    mv.addObject("utilisateur", u);
	} catch (Exception e) {
	    mv.addObject("exception", e);
	    mv.addObject("utilisateur", u);
	    mv.setViewName("modifInfosPerso");
	    return mv;
	}

	mv.setViewName("confirmation");

	return mv;
    }

    @RequestMapping(value = "/monCompte", method = RequestMethod.GET)
    public ModelAndView moncompte(Authentication authentication,
	    @RequestParam(name = "page", defaultValue = "0") int page,
	    @RequestParam(name = "size", defaultValue = "10") int size) {

	ModelAndView mv = new ModelAndView();

	try {
	    Utilisateur u = utilisateurMetier.findLogged(authentication);
	    mv.addObject("utilisateur", u);
	} catch (Exception e) {
	    mv.addObject("exception", e);
	    mv.setViewName("login");
	    return mv;
	}

	try {
	    Page<Emprunt> pageEmprunts = empruntMetier
		    .listeEmpruntsPersos(utilisateurMetier.findLogged(authentication).getUsername(), page, size);
	    mv.addObject("listeEmprunts", pageEmprunts.getContent());
	    int[] pages = new int[pageEmprunts.getTotalPages()];
	    int totalPages = pageEmprunts.getTotalPages();
	    mv.addObject("pages", pages);
	    mv.addObject("totalPages", totalPages);
	    mv.addObject("pageCourante", page);
	    mv.setViewName("monCompte");
	} catch (Exception e) {
	    mv.addObject("exception", e);
	    mv.setViewName("monCompte");
	    return mv;
	}

	return mv;
    }

    @RequestMapping(value = "/admin/gestionUtilisateurs", method = RequestMethod.GET)
    public ModelAndView gestionUtilisateur(@RequestParam(name = "page", defaultValue = "0") int p,
	    @RequestParam(name = "size", defaultValue = "25") int s,
	    @RequestParam(name = "prenom", defaultValue = "") String prenom) {

	ModelAndView mv = new ModelAndView();
	try {
	    Page<Utilisateur> pageUtilisateurs = utilisateurMetier.chercherPrenom(prenom, p, s);
	    mv.addObject("listeUtilisateurs", pageUtilisateurs.getContent());
	    int[] pages = new int[pageUtilisateurs.getTotalPages()];
	    int totalPages = pageUtilisateurs.getTotalPages();
	    mv.addObject("pages", pages);
	    mv.addObject("size", s);
	    mv.addObject("totalPages", totalPages);
	    mv.addObject("pageCourante", p);
	    mv.addObject("prenom", prenom);
	} catch (Exception e) {
	    mv.addObject("exception", e);
	    mv.setViewName("gestionUtilisateurs");
	    return mv;
	}
	mv.setViewName("gestionUtilisateurs");
	return mv;

    }

    @RequestMapping(value = "/admin/ficheUtilisateur", method = RequestMethod.GET)
    public ModelAndView ficheUtilisateur(@RequestParam(name = "username", defaultValue = "") String pseudo,
	    @RequestParam(name = "page", defaultValue = "0") int p,
	    @RequestParam(name = "size", defaultValue = "25") int s) {

	ModelAndView mv = new ModelAndView();

	mv.addObject("utilisateur", utilisateurMetier.findOneCheckDispo(pseudo));

	try {
	    Page<RoleUtilisateur> pageRole = ruMetier.quelRole(pseudo, p, s);
	    mv.addObject("listeRoles", pageRole.getContent());
	    int[] pages = new int[pageRole.getTotalPages()];
	    int totalPages = pageRole.getTotalPages();
	    mv.addObject("totalPages", totalPages);
	    mv.addObject("pages", pages);
	    mv.addObject("size", s);
	    mv.addObject("pageCourante", p);
	} catch (Exception e) {
	    mv.addObject("exception", e);
	    mv.addObject("username", pseudo);
	}

	mv.setViewName("ficheUtilisateur");
	;

	return mv;
    }

    @RequestMapping(value = "super/desactiverUtilisateur", method = RequestMethod.POST)
    public ModelAndView desactiverUtilisateur(@RequestParam(name = "page", defaultValue = "0") int p,
	    @RequestParam(name = "size", defaultValue = "25") int s,
	    @RequestParam(name = "username", defaultValue = "") String pseudo,
	    @RequestParam(name = "prenom", defaultValue = "") String prenom) {

	ModelAndView mv = new ModelAndView();

	try {
	    Utilisateur utilisateur = utilisateurMetier.findOneCheckDispo(pseudo);
	    utilisateur.setEnabled(false);
	    utilisateurMetier.modifierUtilisateurForced(utilisateur);
	} catch (Exception e) {
	    mv.addObject("exception", e);
	    mv.addObject("prenom", prenom);
	}

	try {
	    Page<Utilisateur> pageUtilisateurs = utilisateurMetier.chercherPrenom(prenom, p, s);
	    mv.addObject("listeUtilisateurs", pageUtilisateurs.getContent());
	    int[] pages = new int[pageUtilisateurs.getTotalPages()];
	    int totalPages = pageUtilisateurs.getTotalPages();
	    mv.addObject("totalPages", totalPages);
	    mv.addObject("pages", pages);
	    mv.addObject("size", s);
	    mv.addObject("pageCourante", p);
	    mv.addObject("prenom", prenom);
	} catch (Exception e) {
	    mv.addObject("exception", e);
	    mv.addObject("prenom", prenom);
	}
	mv.setViewName("gestionUtilisateurs");
	return mv;
    }

    @RequestMapping(value = "super/reactiverUtilisateur", method = RequestMethod.POST)
    public ModelAndView reactiverUtilisateur(@RequestParam(name = "page", defaultValue = "0") int p,
	    @RequestParam(name = "size", defaultValue = "25") int s,
	    @RequestParam(name = "username", defaultValue = "") String pseudo,
	    @RequestParam(name = "prenom", defaultValue = "") String prenom) {

	ModelAndView mv = new ModelAndView();

	try {
	    Utilisateur utilisateur = utilisateurMetier.findOneCheckDispo(pseudo);
	    utilisateur.setEnabled(true);
	    utilisateurMetier.modifierUtilisateurForced(utilisateur);
	} catch (Exception e) {
	    mv.addObject("exception", e);
	    mv.addObject("prenom", prenom);
	}

	try {
	    Page<Utilisateur> pageUtilisateurs = utilisateurMetier.chercherPrenom(prenom, p, s);
	    mv.addObject("listeUtilisateurs", pageUtilisateurs.getContent());
	    int[] pages = new int[pageUtilisateurs.getTotalPages()];
	    int totalPages = pageUtilisateurs.getTotalPages();
	    mv.addObject("totalPages", totalPages);
	    mv.addObject("pages", pages);
	    mv.addObject("size", s);
	    mv.addObject("pageCourante", p);
	    mv.addObject("prenom", prenom);
	} catch (Exception e) {
	    mv.addObject("exception", e);
	    mv.addObject("prenom", prenom);
	}
	mv.setViewName("gestionUtilisateurs");
	return mv;
    }

    @RequestMapping(value = "/super/supprimerUtilisateur", method = RequestMethod.POST)
    public ModelAndView supprimerUtilisateur(@RequestParam(name = "username", defaultValue = "") String pseudo,
	    @RequestParam(name = "page", defaultValue = "0") int p,
	    @RequestParam(name = "size", defaultValue = "25") int s,
	    @RequestParam(name = "prenom", defaultValue = "") String prenom) {

	ModelAndView mv = new ModelAndView();

	try {
	    String message = utilisateurMetier.findOneCheckDispo(pseudo).getPrenom() + " "
		    + utilisateurMetier.findOneCheckDispo(pseudo).getNom() + " a été supprimé avec succès";
	    utilisateurMetier.supprimerUtilisateur(pseudo, p, s);
	    ;
	    mv.addObject("message", message);
	} catch (Exception e) {
	    mv.addObject("exception", e);
	}

	try {
	    Page<Utilisateur> pageUtilisateurs = utilisateurMetier.chercherPrenom(prenom, p, s);
	    mv.addObject("listeUtilisateurs", pageUtilisateurs.getContent());
	    int[] pages = new int[pageUtilisateurs.getTotalPages()];
	    int totalPages = pageUtilisateurs.getTotalPages();
	    mv.addObject("totalPages", totalPages);
	    mv.addObject("pages", pages);
	    mv.addObject("size", s);
	    mv.addObject("pageCourante", p);
	    mv.addObject("prenom", prenom);
	} catch (Exception e) {
	    mv.addObject("exception", e);
	    mv.addObject("prenom", prenom);
	    return mv;
	}
	mv.setViewName("gestionUtilisateurs");

	return mv;

    }

}
