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

	// checking if any games in the table contain the string of characters submitted
	// by the user
	Page<Jeu> parTitre = jeuRepository.chercherTitre("%" + titre + "%", new PageRequest(page, size));

	if (!parTitre.hasContent()) {
	    throw new RuntimeException("Aucun titre de jeu ne contient ce mot dans notre collection !");
	}

	// returning a page of games containing the string of characters submitted by
	// the user
	return parTitre;
    }

    @Override
    public Page<Jeu> rechercheJoueurs(int joueurs, int page, int size) {

	// checking if any games in the table has a minimum number of players equal or
	// below and a maxium number of players equal or above the number enterred by
	// the user
	Page<Jeu> parJoueurs = jeuRepository.chercherJoueurs(joueurs, new PageRequest(page, size));

	if (!parJoueurs.hasContent()) {
	    throw new RuntimeException("Aucun jeu disponible avec ce nombre de joueurs !");
	}

	// returning a page of games whose minimum number of players is equal or below
	// and maximum number of players is equal or above the number specified by the
	// user
	return parJoueurs;
    }

    @Override
    public Page<Jeu> trouverTousAlpha(int page, int size) {

	// returning a page of all games recorded in the concerned table ordered by
	// title alphabetically
	return jeuRepository.findAllByOrderByTitreAsc(new PageRequest(page, size));
    }

    @Override
    public Jeu trouverUnJeu(Long idJeu) {

	// return one game through its id
	return jeuRepository.findOne(idJeu);
    }

    @Override
    public void enregistrerJeu(Jeu j) {

	// check if a game with the same title is already present in the concerned table
	Jeu check = jeuRepository.verifierDoublon(j.getTitre());

	if (check != null) {
	    throw new RuntimeException("Un jeu avec ce titre est déjà présent dans notre collection !");
	}

	// adding/saving a new game in the games table
	jeuRepository.save(j);
    }

    @Override
    public void modifierJeu(Jeu j) {

	// updating a specific game informations in the database
	jeuRepository.save(j);
    }

    @Override
    public void supprimerJeu(Long idJeu) {

	// checking if a game is currently borrowed prior to removing it from the
	// collection - advising the admin to record a return of the said game before
	// deleting it
	Emprunt check = empruntRepository.checkDispo(idJeu);

	if (check != null) {
	    throw new RuntimeException(
		    "Impossible de supprimer un jeu emprunté ! Effectuez un retour pour ce jeu avant de procéder.");
	}

	// removing a game from the games table
	jeuRepository.delete(idJeu);

    }

}
