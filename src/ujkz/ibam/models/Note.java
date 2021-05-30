package ujkz.ibam.models;

import java.util.Date;

/* Un objet Note comprend:
  - un code
  - une valeur
  - une date de composition
  - une sesion (Normale / Rattrapage)
*/
public class Note {
  private int codeNote;
  private double valeur;
  private Date dateDevoir;
  private String session;

  // Les constructeurs
  public Note() {
  }

  public Note(double valeur, Date dateDevoir, String session) {
    this.valeur = valeur;
    this.dateDevoir = dateDevoir;
    this.session = session;
  }

  // Les getters et les setters
  public int getCodeNote() {
    return this.codeNote;
  }

  public void setCodeNote(int codeNote) {
    this.codeNote = codeNote;
  }

  public double getValeur() {
    return this.valeur;
  }

  public void setValeur(double valeur) {
    this.valeur = valeur;
  }

  public Date getDateDevoir() {
    return this.dateDevoir;
  }

  public void setDateDevoir(Date dateDevoir) {
    this.dateDevoir = dateDevoir;
  }

  public String getSession() {
    return this.session;
  }

  public void setSession(String session) {
    this.session = session;
  }

}
