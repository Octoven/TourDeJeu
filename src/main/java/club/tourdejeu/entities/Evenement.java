package club.tourdejeu.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Evenement implements Serializable {

    @Id
    @GeneratedValue
    private Long idEvenement;
    @NotBlank
    private String titre;
    @Lob
    @NotBlank
    private String texteInfo;
    @NotBlank
    private String date;
    private String lien;
    private boolean benevoles;
    @OneToMany(mappedBy = "evenement", fetch = FetchType.LAZY)
    private Collection<Commentaire> commentaires;
    @OneToMany(mappedBy = "evenement", fetch = FetchType.LAZY)
    private Collection<Benevolat> benevolats;

    public Evenement() {

    }

    public Evenement(String titre, String texteInfo, String date, String lien) {
	this.titre = titre;
	this.texteInfo = texteInfo;
	this.date = date;
	this.lien = lien;
    }

    public Long getIdEvenement() {
	return idEvenement;
    }

    public void setIdEvenement(Long idEvenement) {
	this.idEvenement = idEvenement;
    }

    public String getTitre() {
	return titre;
    }

    public void setTitre(String titre) {
	this.titre = titre;
    }

    public String getTexteInfo() {
	return texteInfo;
    }

    public void setTexteInfo(String texteInfo) {
	this.texteInfo = texteInfo;
    }

    public String getDate() {
	return date;
    }

    public void setDate(String date) {
	this.date = date;
    }

    public String getLien() {
	return lien;
    }

    public void setLien(String lien) {
	this.lien = lien;
    }

    public boolean isBenevoles() {
	return benevoles;
    }

    public void setBenevoles(boolean benevoles) {
	this.benevoles = benevoles;
    }

    public Collection<Commentaire> getCommentaires() {
	return commentaires;
    }

    public void setCommentaires(Collection<Commentaire> commentaires) {
	this.commentaires = commentaires;
    }

    public Collection<Benevolat> getBenevolats() {
	return benevolats;
    }

    public void setBenevolats(Collection<Benevolat> benevolats) {
	this.benevolats = benevolats;
    }

}
