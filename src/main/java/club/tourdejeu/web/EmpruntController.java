package club.tourdejeu.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import club.tourdejeu.entities.Emprunt;
import club.tourdejeu.entities.Jeu;
import club.tourdejeu.entities.Utilisateur;
import club.tourdejeu.metier.IEmpruntMetier;
import club.tourdejeu.metier.IJeuMetier;
import club.tourdejeu.metier.IUtilisateurMetier;

@Controller
public class EmpruntController {

    @Autowired
    private IEmpruntMetier empruntMetier;
    @Autowired
    private IUtilisateurMetier utilisateurMetier;
    @Autowired
    private IJeuMetier jeuMetier;

    // mapping to the game loan page
    @RequestMapping(value = "/membre/emprunt", method = RequestMethod.GET)
    public ModelAndView pageEmprunt(@RequestParam(name = "page", defaultValue = "0") int p,
	    @RequestParam(name = "size", defaultValue = "25") int s,
	    @RequestParam(name = "titre", defaultValue = "") String titre) {

	ModelAndView mv = new ModelAndView();
	try {
	    Page<Jeu> pageJeux = jeuMetier.rechercheTitre(titre, p, s);
	    mv.addObject("listeJeux", pageJeux.getContent());
	    int[] pages = new int[pageJeux.getTotalPages()];
	    int totalPages = pageJeux.getTotalPages();
	    mv.addObject("pages", pages);
	    mv.addObject("totalPages", totalPages);
	    mv.addObject("titre", titre);
	    mv.addObject("size", s);
	    mv.addObject("pageCourante", p);
	} catch (Exception e) {
	    mv.addObject("exception", e);
	    mv.setViewName("emprunt");
	    return mv;
	}
	mv.setViewName("emprunt");
	return mv;
    }

    // mapping the game loan action
    @RequestMapping(value = "/membre/emprunter", method = RequestMethod.POST)
    public ModelAndView emprunter(Authentication authentication, @RequestParam(name = "idJeu") Long idJeu,
	    @RequestParam(name = "page", defaultValue = "0") int p,
	    @RequestParam(name = "size", defaultValue = "25") int s,
	    @RequestParam(name = "titre", defaultValue = "") String titre) {

	ModelAndView mv = new ModelAndView();
	Utilisateur u;

	try {
	    u = utilisateurMetier.findLogged(authentication);
	    mv.addObject("utilisateur", u);
	} catch (Exception e) {
	    mv.addObject("exception", e);
	    mv.setViewName("login");
	    return mv;
	}

	try {
	    Emprunt emprunt = empruntMetier.emprunt(u, jeuMetier.trouverUnJeu(idJeu));
	    String message = emprunt.getJeu().getTitre() + " emprunté avec succès !";
	    mv.addObject("message", message);
	} catch (Exception e) {
	    mv.addObject("exception", e);
	    mv.setViewName("emprunt");
	}

	try {
	    Page<Jeu> pageJeux = jeuMetier.rechercheTitre(titre, p, s);
	    mv.addObject("listeJeux", pageJeux.getContent());
	    int[] pages = new int[pageJeux.getTotalPages()];
	    int totalPages = pageJeux.getTotalPages();
	    mv.addObject("totalPages", totalPages);
	    mv.addObject("pages", pages);
	    mv.addObject("titre", titre);
	    mv.addObject("size", s);
	    mv.addObject("pageCourante", p);
	    mv.setViewName("emprunt");
	} catch (Exception e) {
	    mv.addObject("exception", e);
	    mv.setViewName("emprunt");
	    return mv;
	}

	return mv;
    }

    // mapping the loan return page
    @RequestMapping(value = "/admin/empruntRetour", method = RequestMethod.GET)
    public ModelAndView pageRetour(@RequestParam(name = "page", defaultValue = "0") int p,
	    @RequestParam(name = "size", defaultValue = "25") int s) {

	ModelAndView mv = new ModelAndView();

	try {
	    Page<Emprunt> pageEmprunts = empruntMetier.listeEmpruntsEnCours(p, s);
	    mv.addObject("listeEmprunts", pageEmprunts.getContent());
	    int[] pages = new int[pageEmprunts.getTotalPages()];
	    int totalPages = pageEmprunts.getTotalPages();
	    mv.addObject("pages", pages);
	    mv.addObject("totalPages", totalPages);
	    mv.addObject("size", s);
	    mv.addObject("pageCourante", p);
	} catch (Exception e) {
	    mv.addObject("exception", e);
	    mv.setViewName("retourEmprunt");
	    return mv;
	}
	mv.setViewName("retourEmprunt");
	return mv;
    }

    // mapping the loan return action
    @RequestMapping(value = "/admin/retour", method = RequestMethod.POST)
    public ModelAndView retour(@RequestParam(name = "page", defaultValue = "0") int p,
	    @RequestParam(name = "size", defaultValue = "25") int s,
	    @RequestParam(name = "idEmprunt", defaultValue = "-1") Long idEmprunt) {

	ModelAndView mv = new ModelAndView();

	try {
	    Emprunt retour = empruntMetier.retour(idEmprunt);
	    String message = retour.getJeu().getTitre() + " a été retourné avec succès !";
	    mv.addObject("message", message);
	} catch (Exception e) {
	    mv.addObject("exception", e);
	    mv.setViewName("retourEmprunt");
	}

	try {
	    Page<Emprunt> pageEmprunts = empruntMetier.listeEmpruntsEnCours(p, s);
	    mv.addObject("listeEmprunts", pageEmprunts.getContent());
	    int[] pages = new int[pageEmprunts.getTotalPages()];
	    int totalPages = pageEmprunts.getTotalPages();
	    mv.addObject("pages", pages);
	    mv.addObject("totalPages", totalPages);
	    mv.addObject("size", s);
	    mv.addObject("pageCourante", p);
	} catch (Exception e) {
	    mv.addObject("exception", e);
	    mv.setViewName("retourEmprunt");
	    return mv;
	}
	mv.setViewName("retourEmprunt");
	return mv;
    }

    // mapping the old loans page (returned loans)
    @RequestMapping(value = "/admin/anciensEmprunts", method = RequestMethod.GET)
    public ModelAndView empruntsAnciens(@RequestParam(name = "page", defaultValue = "0") int p,
	    @RequestParam(name = "size", defaultValue = "25") int s) {

	ModelAndView mv = new ModelAndView();

	try {
	    Page<Emprunt> pageEmprunts = empruntMetier.listeEmpruntsAnciens(p, s);
	    mv.addObject("listeEmprunts", pageEmprunts.getContent());
	    int[] pages = new int[pageEmprunts.getTotalPages()];
	    int totalPages = pageEmprunts.getTotalPages();
	    mv.addObject("pages", pages);
	    mv.addObject("totalPages", totalPages);
	    mv.addObject("size", s);
	    mv.addObject("pageCourante", p);
	} catch (Exception e) {
	    mv.addObject("exception", e);
	    mv.setViewName("empruntsAnciens");
	    return mv;
	}
	mv.setViewName("empruntsAnciens");
	return mv;
    }

}
