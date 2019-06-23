package club.tourdejeu.metier;

import org.springframework.data.domain.Page;

import club.tourdejeu.entities.Emprunt;
import club.tourdejeu.entities.Jeu;
import club.tourdejeu.entities.Utilisateur;

public interface IEmpruntMetier {

    public Page<Emprunt> listeEmpruntsPersos(String username, int page, int size);

    public Page<Emprunt> listeEmpruntsEnCours(int page, int size);

    public Page<Emprunt> listeEmpruntsAnciens(int page, int size);

    public Emprunt emprunt(Utilisateur u, Jeu j);

    public Emprunt retour(Long idEmprunt);

}
