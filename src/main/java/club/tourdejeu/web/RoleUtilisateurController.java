package club.tourdejeu.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import club.tourdejeu.dao.RoleRepository;
import club.tourdejeu.entities.Role;
import club.tourdejeu.entities.RoleUtilisateur;
import club.tourdejeu.entities.Utilisateur;
import club.tourdejeu.metier.IRoleUtilisateurMetier;
import club.tourdejeu.metier.IUtilisateurMetier;

@Controller
public class RoleUtilisateurController {

    @Autowired
    private IRoleUtilisateurMetier ruMetier;
    @Autowired
    private IUtilisateurMetier utilisateurMetier;
    @Autowired
    private RoleRepository roleRepository;

    @RequestMapping(value = "/super/supprimerRole", method = RequestMethod.POST)
    public ModelAndView supprimerRole(Long idRoUt, @RequestParam(value = "username", defaultValue = "") String pseudo,
	    @RequestParam(name = "page", defaultValue = "0") int p,
	    @RequestParam(name = "size", defaultValue = "5") int s) {

	ModelAndView mv = new ModelAndView();

	String message = "Rôle supprimé avec succès.";

	mv.addObject("utilisateur", utilisateurMetier.findOneCheckDispo(pseudo));

	ruMetier.supprimerRole(idRoUt);

	mv.addObject("message", message);

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

	return mv;
    }

    @RequestMapping(value = "/admin/ajouterRole", method = RequestMethod.POST)
    public ModelAndView ajouterRole(@RequestParam(value = "role", defaultValue = "") String role,
	    @RequestParam(value = "username", defaultValue = "") String pseudo,
	    @RequestParam(name = "page", defaultValue = "0") int p,
	    @RequestParam(name = "size", defaultValue = "5") int s) {

	ModelAndView mv = new ModelAndView();

	Utilisateur utilisateur = utilisateurMetier.findOneCheckDispo(pseudo);

	Role roleAttribué = roleRepository.findOne(role);

	RoleUtilisateur ru = new RoleUtilisateur(utilisateur, roleAttribué);

	mv.addObject("utilisateur", utilisateur);

	try {
	    ruMetier.ajouterRole(ru);
	} catch (Exception e) {
	    mv.addObject("exception", e);
	}

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

	return mv;
    }

}
