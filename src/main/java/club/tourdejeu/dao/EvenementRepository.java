package club.tourdejeu.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import club.tourdejeu.entities.Evenement;

public interface EvenementRepository extends JpaRepository<Evenement, Long> {

    public final static String VERIFIER_DOUBLON = "select e from Evenement e where e.titre like :x and e.date like :y";

    // fetching event using event title and date as search criteria
    @Query(VERIFIER_DOUBLON)
    public Evenement verifierDoublon(@Param("x") String titre, @Param("y") String date);

    // fetching all events ordered by date
    public Page<Evenement> findAllByOrderByDateDesc(Pageable pageable);

}
