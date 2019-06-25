package club.tourdejeu.metier;

import org.springframework.data.domain.Page;

import club.tourdejeu.entities.Emprunt;
import club.tourdejeu.entities.Jeu;
import club.tourdejeu.entities.Utilisateur;

public interface IEmpruntMetier {

    // returning a page of personal loans for a specific user through username
    public Page<Emprunt> listeEmpruntsPersos(String username, int page, int size);

    // returning a page of currently ongoing loans
    public Page<Emprunt> listeEmpruntsEnCours(int page, int size);

    // returning a page of past loans
    public Page<Emprunt> listeEmpruntsAnciens(int page, int size);

    // creating and returning a new loan in the table for specified user and game
    public Emprunt emprunt(Utilisateur u, Jeu j);

    // updating and returning an existing loan for which the return date has been
    // set
    public Emprunt retour(Long idEmprunt);

}
