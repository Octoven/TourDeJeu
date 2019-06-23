package club.tourdejeu.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import club.tourdejeu.dao.EmpruntRepository;
import club.tourdejeu.entities.Emprunt;
import club.tourdejeu.entities.Jeu;
import club.tourdejeu.metier.IJeuMetier;

@Controller
public class JeuController {

    @Autowired
    private IJeuMetier jeuMetier;
    @Autowired
    private EmpruntRepository empruntRepository;

    @RequestMapping(value = "/jeux")
    public ModelAndView indexJeux(@RequestParam(name = "page", defaultValue = "0") int p,
	    @RequestParam(name = "size", defaultValue = "25") int s) {

	ModelAndView mv = new ModelAndView();
	Page<Jeu> pageJeux = jeuMetier.trouverTousAlpha(p, s);
	mv.addObject("listeJeux", pageJeux.getContent());
	int[] pages = new int[pageJeux.getTotalPages()];
	int totalPages = pageJeux.getTotalPages();
	mv.addObject("totalPages", totalPages);
	mv.addObject("pages", pages);
	mv.addObject("size", s);
	mv.addObject("pageCourante", p);
	mv.setViewName("jeux");
	return mv;
    }

    @RequestMapping(value = "/rechercheTitre", method = RequestMethod.GET)
    public ModelAndView rechercheTitre(@RequestParam(name = "page", defaultValue = "0") int p,
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
	    mv.setViewName("jeuxTitre");
	    return mv;
	}
	mv.setViewName("jeuxTitre");
	return mv;
    }

    @RequestMapping(value = "/rechercheJoueurs", method = RequestMethod.GET)
    public ModelAndView rechercheJoueurs(@RequestParam(name = "page", defaultValue = "0") int p,
	    @RequestParam(name = "size", defaultValue = "25") int s,
	    @RequestParam(name = "joueurs", defaultValue = "2") int joueurs) {

	ModelAndView mv = new ModelAndView();
	try {
	    Page<Jeu> pageJeux = jeuMetier.rechercheJoueurs(joueurs, p, s);
	    mv.addObject("listeJeux", pageJeux.getContent());
	    int[] pages = new int[pageJeux.getTotalPages()];
	    int totalPages = pageJeux.getTotalPages();
	    mv.addObject("pages", pages);
	    mv.addObject("totalPages", totalPages);
	    mv.addObject("size", s);
	    mv.addObject("pageCourante", p);
	    mv.addObject("joueurs", joueurs);
	} catch (Exception e) {
	    mv.addObject("exception", e);
	    mv.setViewName("jeuxJoueurs");
	    return mv;
	}
	mv.setViewName("jeuxJoueurs");
	return mv;
    }

    @RequestMapping(value = "/admin/ajouterJeu")
    public ModelAndView inscriptionJeu() {

	ModelAndView mv = new ModelAndView();
	mv.addObject("jeu", new Jeu());
	mv.setViewName("ajoutJeu");

	return mv;
    }

    @RequestMapping(value = "/admin/enregistrerJeu", method = RequestMethod.POST)
    public ModelAndView enregisterJeu(@Valid Jeu j, BindingResult bindingResult,
	    @RequestParam(name = "extensionJeu") String extension,
	    @RequestParam(name = "empruntable") String empruntable) {

	ModelAndView mv = new ModelAndView();

	if (bindingResult.hasErrors()) {
	    mv.setViewName("ajoutJeu");
	    return mv;
	}

	if (extension.equals("oui")) {
	    j.setExtension(true);
	}

	if (empruntable.equals("oui")) {
	    j.setDispoPret(true);
	}

	try {
	    jeuMetier.enregistrerJeu(j);
	    mv.addObject("jeu", j);
	} catch (Exception e) {
	    mv.addObject("exception", e);
	    mv.setViewName("ajoutJeu");
	    mv.addObject("jeu", j);
	    return mv;
	}

	mv.setViewName("confirmationJeu");

	return mv;
    }

    @RequestMapping(value = "/admin/gestionJeux", method = RequestMethod.GET)
    public ModelAndView gestionJeux(@RequestParam(name = "page", defaultValue = "0") int p,
	    @RequestParam(name = "size", defaultValue = "25") int s,
	    @RequestParam(name = "titre", defaultValue = "") String titre) {

	ModelAndView mv = new ModelAndView();

	try {
	    Page<Jeu> pageJeux = jeuMetier.rechercheTitre(titre, p, s);
	    mv.addObject("listeJeux", pageJeux.getContent());
	    int[] pages = new int[pageJeux.getTotalPages()];
	    int totalPages = pageJeux.getTotalPages();
	    mv.addObject("pages", pages);
	    mv.addObject("size", s);
	    mv.addObject("totalPages", totalPages);
	    mv.addObject("pageCourante", p);
	    mv.addObject("titre", titre);
	} catch (Exception e) {
	    mv.addObject("exception", e);
	    mv.setViewName("gestionJeux");
	    return mv;
	}
	mv.setViewName("gestionJeux");
	return mv;
    }

    @RequestMapping(value = "/admin/supprimerJeu", method = RequestMethod.POST)
    public ModelAndView enregisterJeu(@RequestParam(name = "idJeu", defaultValue = "-1") Long idJeu,
	    @RequestParam(name = "page", defaultValue = "0") int p,
	    @RequestParam(name = "size", defaultValue = "25") int s,
	    @RequestParam(name = "titre", defaultValue = "") String titre) {

	ModelAndView mv = new ModelAndView();

	try {
	    String message = jeuMetier.trouverUnJeu(idJeu).getTitre() + " a été supprimé avec succès";
	    jeuMetier.supprimerJeu(idJeu);
	    mv.addObject("message", message);
	} catch (Exception e) {
	    mv.addObject("exception", e);
	}

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
	    mv.setViewName("gestionJeux");
	    return mv;
	}

	mv.setViewName("gestionJeux");

	return mv;
    }

    @RequestMapping(value = "/admin/ficheJeu", method = RequestMethod.GET)
    public ModelAndView ficheJeu(Long idJeu) {

	ModelAndView mv = new ModelAndView();

	Emprunt check = empruntRepository.checkDispo(idJeu);

	if (check != null) {
	    mv.addObject("emprunte", check);
	}

	try {
	    Jeu j = jeuMetier.trouverUnJeu(idJeu);
	    mv.addObject("jeu", j);
	} catch (Exception e) {
	    mv.addObject("exception", e);
	    mv.setViewName("gestionJeux");
	    return mv;
	}
	mv.setViewName("modifJeu");
	return mv;
    }

    @RequestMapping(value = "/admin/modifierJeu", method = RequestMethod.POST)
    public ModelAndView modifierJeu(@Valid Jeu j, BindingResult bindingResult,
	    @RequestParam(name = "extensionJeu") String extension,
	    @RequestParam(name = "empruntable") String empruntable) {

	ModelAndView mv = new ModelAndView();

	if (bindingResult.hasErrors()) {
	    mv.setViewName("ajoutJeu");
	    return mv;
	}

	if (extension.equals("oui")) {
	    j.setExtension(true);
	}

	if (empruntable.equals("oui")) {
	    j.setDispoPret(true);
	}

	try {
	    jeuMetier.modifierJeu(j);
	    mv.addObject("jeu", j);
	} catch (Exception e) {
	    mv.addObject("exception", e);
	    mv.setViewName("ajoutJeu");
	    mv.addObject("jeu", j);
	    return mv;
	}

	mv.setViewName("confirmationModifJeu");

	return mv;
    }

}
