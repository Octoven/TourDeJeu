package club.tourdejeu.metier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import club.tourdejeu.dao.EvenementRepository;
import club.tourdejeu.entities.Evenement;

@Service
@Transactional
public class EvenementMetierImpl implements IEvenementMetier {

    @Autowired
    private EvenementRepository evRepository;

    @Override
    public Page<Evenement> listeEvenements(int page, int size) {

	// checking if events have been added to the events table
	Page<Evenement> listeEvenements = evRepository.findAllByOrderByDateDesc(new PageRequest(page, size));

	if (!listeEvenements.hasContent()) {
	    throw new RuntimeException("Aucun événement à venir !");
	}

	// sending a page of all recorded events sorted by date (upcoming and recent
	// first)
	return listeEvenements;
    }

    @Override
    public Evenement trouverUn(Long idEvenement) {

	// checking if a specific event is still in table by its Id
	Evenement evenement = evRepository.findOne(idEvenement);

	if (evenement == null) {
	    throw new RuntimeException("Aucun événement ne correspond à cette recherche !");
	}

	// returning the event
	return evenement;
    }

    @Override
    public void enregistrerEvenement(Evenement e) {

	// checking if an event with the same date and title is present is the database
	Evenement checkEvenement = evRepository.verifierDoublon(e.getTitre(), e.getDate());

	if (checkEvenement != null) {
	    throw new RuntimeException("Un événement du même titre existe déjà pour cette date !");
	}

	// adding a new event to the table
	evRepository.save(e);

    }

}
