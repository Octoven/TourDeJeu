package club.tourdejeu.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import club.tourdejeu.entities.Emprunt;

public interface EmpruntRepository extends JpaRepository<Emprunt, Long> {

    public final static String USER_EN_COURS = "select e from Emprunt e where e.utilisateur.username=:x and e.dateRetour is null order by e.dateEmprunt desc";
    public final static String TOUS_EN_COURS = "select e from Emprunt e where e.dateRetour is null order by e.dateEmprunt desc";
    public final static String CHECK_EN_COURS_JEU = "select e from Emprunt e where e.jeu.idJeu=:x and e.dateRetour is null";
    public final static String TOUS_PASSE = "select e from Emprunt e where e.dateRetour is not null order by e.dateEmprunt desc";

    // fetching personal board games loans page using username as a search criteria
    @Query(USER_EN_COURS)
    public Page<Emprunt> listeEmpruntsPersos(@Param("x") String username, Pageable page);

    // fetching all current board games loans page
    @Query(TOUS_EN_COURS)
    public Page<Emprunt> listeEmpruntsEnCours(Pageable page);

    // fetching specific board game current loan using game id as a search criteria
    @Query(CHECK_EN_COURS_JEU)
    public Emprunt checkDispo(@Param("x") Long idJeu);

    // fetching all past board games loans page
    @Query(TOUS_PASSE)
    public Page<Emprunt> listeEmpruntsAncien(Pageable page);

}
