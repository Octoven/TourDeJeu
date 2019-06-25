package club.tourdejeu.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import club.tourdejeu.entities.Jeu;

public interface JeuRepository extends JpaRepository<Jeu, Long> {

    public final static String CHERCHER_TITRE = "select j from Jeu j where j.titre like :x order by j.titre asc";
    public final static String CHERCHER_JOUEURS = "select j from Jeu j where :x between j.joueursMin and j.joueursMax order by j.titre asc";
    public final static String VERIFIER_DOUBLON = "select j from Jeu j where j.titre like :x";

    // fetching board games page using title keywords as a search criteria
    @Query(CHERCHER_TITRE)
    public Page<Jeu> chercherTitre(@Param("x") String titre, Pageable pageable);

    // fetching board games page using number of players as a search criteria
    @Query(CHERCHER_JOUEURS)
    public Page<Jeu> chercherJoueurs(@Param("x") int joueurs, Pageable pageable);

    // fetching one board game using title keywords as a search criteria
    @Query(VERIFIER_DOUBLON)
    public Jeu verifierDoublon(@Param("x") String titre);

    // fetching board games page using title keywords as a search criteria and
    // sorting the list by title
    public Page<Jeu> findAllByOrderByTitreAsc(Pageable pageable);

}
