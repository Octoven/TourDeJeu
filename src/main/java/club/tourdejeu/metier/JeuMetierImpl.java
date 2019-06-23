package club.tourdejeu.metier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import club.tourdejeu.dao.EmpruntRepository;
import club.tourdejeu.dao.JeuRepository;
import club.tourdejeu.entities.Emprunt;
import club.tourdejeu.entities.Jeu;

@Service
@Transactional
public class JeuMetierImpl implements IJeuMetier {

    @Autowired
    private JeuRepository jeuRepository;
    @Autowired
    private EmpruntRepository empruntRepository;

    @Override
    public Page<Jeu> rechercheTitre(String titre, int page, int size) {

	Page<Jeu> parTitre = jeuRepository.chercherTitre("%" + titre + "%", new PageRequest(page, size));

	if (!parTitre.hasContent()) {
	    throw new RuntimeException("Aucun titre de jeu ne contient ce mot dans notre collection !");
	}

	return parTitre;
    }

    @Override
    public Page<Jeu> rechercheJoueurs(int joueurs, int page, int size) {

	Page<Jeu> parJoueurs = jeuRepository.chercherJoueurs(joueurs, new PageRequest(page, size));

	if (!parJoueurs.hasContent()) {
	    throw new RuntimeException("Aucun jeu disponible avec ce nombre de joueurs !");
	}

	return parJoueurs;
    }

    @Override
    public Page<Jeu> trouverTousAlpha(int page, int size) {

	return jeuRepository.findAllByOrderByTitreAsc(new PageRequest(page, size));
    }

    @Override
    public Jeu trouverUnJeu(Long idJeu) {

	return jeuRepository.findOne(idJeu);
    }

    @Override
    public void enregistrerJeu(Jeu j) {

	Jeu check = jeuRepository.verifierDoublon(j.getTitre());

	if (check != null) {
	    throw new RuntimeException("Un jeu avec ce titre est déjà présent dans notre collection !");
	}

	jeuRepository.save(j);
    }

    @Override
    public void modifierJeu(Jeu j) {

	jeuRepository.save(j);
    }

    @Override
    public void supprimerJeu(Long idJeu) {

	Emprunt check = empruntRepository.checkDispo(idJeu);

	if (check != null) {
	    throw new RuntimeException(
		    "Impossible de supprimer un jeu emprunté ! Effectuez un retour pour ce jeu avant de procéder.");
	}

	jeuRepository.delete(idJeu);

    }

}
