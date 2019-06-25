package club.tourdejeu.metier;

import org.springframework.data.domain.Page;

import club.tourdejeu.entities.Evenement;

public interface IEvenementMetier {

    // return a page listing all recorded events
    public Page<Evenement> listeEvenements(int page, int size);

    // return a specific event through its id
    public Evenement trouverUn(Long idEvenement);

    // add a nex event
    public void enregistrerEvenement(Evenement e);

}
