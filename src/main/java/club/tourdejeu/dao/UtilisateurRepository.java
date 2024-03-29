package club.tourdejeu.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import club.tourdejeu.entities.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, String> {

    public final static String CHERCHER_PSEUDO = "select u from Utilisateur u where u.username like :x order by u.username asc";
    public final static String CHECK_AVAILABILITY = "select u from Utilisateur u where u.prenom like :x and u.nom like :y and u.email like :z";

    // fetching user page by username keyword as a search criteria
    @Query(CHERCHER_PSEUDO)
    public Page<Utilisateur> chercherPseudo(@Param("x") String pseudo, Pageable pageable);

    // fetching user by first name, last name and email keywords as a search
    // criteria
    @Query(CHECK_AVAILABILITY)
    public Utilisateur checkAvailability(@Param("x") String prenom, @Param("y") String nom, @Param("z") String email);

    // fetching user page by first name keyword as a search criteria
    public Page<Utilisateur> findAllByPrenom(Pageable pageable);

}
