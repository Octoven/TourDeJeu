package club.tourdejeu.metier;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import club.tourdejeu.dao.EmpruntRepository;
import club.tourdejeu.dao.JeuRepository;
import club.tourdejeu.entities.Emprunt;
import club.tourdejeu.entities.Jeu;
import club.tourdejeu.entities.Utilisateur;

@Service
@Transactional
public class EmpruntMetierImpl implements IEmpruntMetier {

    @Autowired
    private EmpruntRepository empruntRepository;
    @Autowired
    private JeuRepository jeuRepository;

    @Override
    public Page<Emprunt> listeEmpruntsPersos(String username, int page, int size) {

	// checking if a specific user has ongoing board game loans
	Page<Emprunt> empruntsPerso = empruntRepository.listeEmpruntsPersos(username, new PageRequest(page, size));

	if (!empruntsPerso.hasContent()) {
	    throw new RuntimeException("Vous n'avez aucun emprunt en cours !");
	}

	// sendind personal ongoing loans as a page
	return empruntsPerso;
    }

    @Override
    public Page<Emprunt> listeEmpruntsEnCours(int page, int size) {

	// checking if there are ongoing loans at the time of the request
	Page<Emprunt> empruntsEnCours = empruntRepository.listeEmpruntsEnCours(new PageRequest(page, size));

	if (!empruntsEnCours.hasContent()) {
	    throw new RuntimeException("Aucun jeu n'a été emprunté récemment !");
	}

	// sendind ongoing loans as a page
	return empruntsEnCours;
    }

    @Override
    public Emprunt emprunt(Utilisateur u, Jeu j) {

	// checking if a game can be borrowed at the time the request was submitted
	Emprunt check = empruntRepository.checkDispo(j.getIdJeu());
	Emprunt em;

	if (check != null) {
	    throw new RuntimeException("Ce jeu n'est pas disponible à l'heure actuelle !");
	}

	// if the game can be borrowed creating a new loan for the user in the loan
	// table
	else {

	    em = new Emprunt();

	    j.setDispoPret(false);

	    em.setJeu(j);
	    em.setUtilisateur(u);
	    em.setDateEmprunt(new Date());

	    empruntRepository.save(em);
	    jeuRepository.save(j);
	}

	// returning the newly created loan
	return em;
    }

    @Override
    public Emprunt retour(Long idEmprunt) {

	// checking if a game has been borrowed and not returned yet
	Emprunt retour = empruntRepository.findOne(idEmprunt);

	if (retour.getDateRetour() != null) {
	    throw new RuntimeException("Ce jeu n'a pas été emprunté !");
	}

	// if the game hasn't been returned yet - adding a retunr date to validate its
	// return and setting its availabilty to true for further loans
	else {
	    retour.setDateRetour(new Date());
	    Jeu j = retour.getJeu();
	    j.setDispoPret(true);
	    empruntRepository.save(retour);
	    jeuRepository.save(j);
	}

	// returning the newly updated loan - Not sure whether the method should be
	// changed to void rather than returning the loan
	return retour;
    }

    @Override
    public Page<Emprunt> listeEmpruntsAnciens(int page, int size) {

	// checking if there have ever been game loans recorded in the database
	Page<Emprunt> empruntsAnciens = empruntRepository.listeEmpruntsAncien(new PageRequest(page, size));

	if (!empruntsAnciens.hasContent()) {
	    throw new RuntimeException("Aucun jeu n'a été emprunté !");
	}

	// returning a page of all past loans - loans for which the return date isn't
	// null
	return empruntsAnciens;
    }

}
