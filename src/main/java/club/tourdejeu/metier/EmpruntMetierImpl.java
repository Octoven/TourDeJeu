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

	Page<Emprunt> empruntsPerso = empruntRepository.listeEmpruntsPersos(username, new PageRequest(page, size));

	if (!empruntsPerso.hasContent()) {
	    throw new RuntimeException("Vous n'avez aucun emprunt en cours !");
	}

	return empruntsPerso;
    }

    @Override
    public Page<Emprunt> listeEmpruntsEnCours(int page, int size) {

	Page<Emprunt> empruntsEnCours = empruntRepository.listeEmpruntsEnCours(new PageRequest(page, size));

	if (!empruntsEnCours.hasContent()) {
	    throw new RuntimeException("Aucun jeu n'a été emprunté récemment !");
	}

	return empruntsEnCours;
    }

    @Override
    public Emprunt emprunt(Utilisateur u, Jeu j) {

	Emprunt check = empruntRepository.checkDispo(j.getIdJeu());
	Emprunt em;

	if (check != null) {
	    throw new RuntimeException("Ce jeu n'est pas disponible à l'heure actuelle !");
	}

	else {

	    em = new Emprunt();

	    j.setDispoPret(false);

	    em.setJeu(j);
	    em.setUtilisateur(u);
	    em.setDateEmprunt(new Date());

	    empruntRepository.save(em);
	    jeuRepository.save(j);
	}

	return em;
    }

    @Override
    public Emprunt retour(Long idEmprunt) {

	Emprunt retour = empruntRepository.findOne(idEmprunt);

	if (retour.getDateRetour() != null) {
	    throw new RuntimeException("Ce jeu n'a pas été emprunté !");
	}

	else {
	    retour.setDateRetour(new Date());
	    Jeu j = retour.getJeu();
	    j.setDispoPret(true);
	    empruntRepository.save(retour);
	    jeuRepository.save(j);
	}

	return retour;
    }

    @Override
    public Page<Emprunt> listeEmpruntsAnciens(int page, int size) {

	Page<Emprunt> empruntsAnciens = empruntRepository.listeEmpruntsAncien(new PageRequest(page, size));

	if (!empruntsAnciens.hasContent()) {
	    throw new RuntimeException("Aucun jeu n'a été emprunté !");
	}

	return empruntsAnciens;
    }

}
