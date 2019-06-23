package club.tourdejeu.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import club.tourdejeu.entities.Commentaire;

public interface CommentaireRepository extends JpaRepository<Commentaire, Long> {

}
