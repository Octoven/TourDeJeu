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

import club.tourdejeu.entities.Evenement;
import club.tourdejeu.metier.IEvenementMetier;

@Controller
public class EvenementController {

    @Autowired
    private IEvenementMetier evMetier;

    @RequestMapping(value = "/listeEvenments", method = RequestMethod.GET)
    public ModelAndView listeEvenments(@RequestParam(name = "page", defaultValue = "0") int p,
	    @RequestParam(name = "size", defaultValue = "5") int s) {

	ModelAndView mv = new ModelAndView();
	Page<Evenement> listeEvenements = evMetier.listeEvenements(p, s);
	mv.addObject("listeEvenements", listeEvenements.getContent());
	int[] pages = new int[listeEvenements.getTotalPages()];
	int totalPages = listeEvenements.getTotalPages();
	mv.addObject("totalPages", totalPages);
	mv.addObject("pages", pages);
	mv.addObject("size", s);
	mv.addObject("pageCourante", p);
	mv.setViewName("evenementsListe");
	return mv;
    }

    @RequestMapping(value = "/consulterEvenement", method = RequestMethod.GET)
    public ModelAndView listeEvenments(@RequestParam(name = "idEvenement", defaultValue = "-1") Long idEvenement) {

	ModelAndView mv = new ModelAndView();

	try {
	    mv.addObject("evenement", evMetier.trouverUn(idEvenement));
	} catch (Exception e) {
	    mv.addObject("exception", e);
	}

	mv.setViewName("evenement");
	return mv;
    }

    @RequestMapping(value = "/admin/ajouterEvenement")
    public ModelAndView inscriptionEvenement() {

	ModelAndView mv = new ModelAndView();
	mv.addObject("evenement", new Evenement());
	mv.setViewName("ajoutEvenement");

	return mv;
    }

    @RequestMapping(value = "/admin/enregistrerEvenement", method = RequestMethod.POST)
    public ModelAndView enregisterJeu(@Valid Evenement e, BindingResult bindingResult,
	    @RequestParam(name = "guerriers") String benevoles) {

	ModelAndView mv = new ModelAndView();

	if (bindingResult.hasErrors()) {
	    mv.setViewName("ajoutEvenement");
	    return mv;
	}

	if (benevoles.equals("oui")) {
	    e.setBenevoles(true);
	}

	try {
	    evMetier.enregistrerEvenement(e);
	    mv.addObject("evenement", e);
	} catch (Exception ex) {
	    mv.addObject("exception", ex);
	    mv.setViewName("ajoutEvenement");
	    mv.addObject("evenement", e);
	    return mv;
	}

	mv.setViewName("evenement");

	return mv;
    }

}
