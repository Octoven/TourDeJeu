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

    @Query(CHERCHER_TITRE)
    public Page<Jeu> chercherTitre(@Param("x") String titre, Pageable pageable);

    @Query(CHERCHER_JOUEURS)
    public Page<Jeu> chercherJoueurs(@Param("x") int joueurs, Pageable pageable);

    @Query(VERIFIER_DOUBLON)
    public Jeu verifierDoublon(@Param("x") String titre);

    public Page<Jeu> findAllByOrderByTitreAsc(Pageable pageable);

}
