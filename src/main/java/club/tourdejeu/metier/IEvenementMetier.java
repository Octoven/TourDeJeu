package club.tourdejeu.metier;

import org.springframework.data.domain.Page;

import club.tourdejeu.entities.Evenement;

public interface IEvenementMetier {

    public Page<Evenement> listeEvenements(int page, int size);

    public Evenement trouverUn(Long idEvenement);

    public void enregistrerEvenement(Evenement e);

}
