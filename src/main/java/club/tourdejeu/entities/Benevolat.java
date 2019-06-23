package club.tourdejeu.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Benevolat implements Serializable {

    @Id
    @GeneratedValue
    private Long idBenevolat;
    @ManyToOne
    @JoinColumn(name = "ID_EVENEMENT")
    private Evenement evenement;
    @ManyToOne
    @JoinColumn(name = "PSEUDO_UTILISATEUR")
    private Utilisateur utilisateur;

    public Benevolat() {
	// TODO Auto-generated constructor stub
    }

    public Benevolat(Evenement evenement, Utilisateur utilisateur) {
	this.evenement = evenement;
	this.utilisateur = utilisateur;
    }

    public Long getIdBenevolat() {
	return idBenevolat;
    }

    public void setIdBenevolat(Long idBenevolat) {
	this.idBenevolat = idBenevolat;
    }

    public Evenement getEvenement() {
	return evenement;
    }

    public void setEvenement(Evenement evenement) {
	this.evenement = evenement;
    }

    public Utilisateur getUtilisateur() {
	return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
	this.utilisateur = utilisateur;
    }

}
