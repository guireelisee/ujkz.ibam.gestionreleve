package ujkz.ibam.models;

import java.util.Date;

/* Un objet Semestre comprend:
  - un code
  - un libellé
  - une date de debut
  - une date de fin
*/
public class Semestre {
  private int codeSemestre;
  private String libelleSemestre;
  private Date dateDebut;
  private Date dateFin;

  // Les constructeurs
  public Semestre() {
  }

  public Semestre(int codeSemestre, String libelleSemestre, Date dateDebut, Date dateFin) {
    this.codeSemestre = codeSemestre;
    this.libelleSemestre = libelleSemestre;
    this.dateDebut = dateDebut;
    this.dateFin = dateFin;
  }

  // Les getters et les setters
  public int getCodeSemestre() {
    return this.codeSemestre;
  }

  public void setCodeSemestre(int codeSemestre) {
    this.codeSemestre = codeSemestre;
  }

  public String getLibelleSemestre() {
    return this.libelleSemestre;
  }

  public void setLibelleSemestre(String libelleSemestre) {
    this.libelleSemestre = libelleSemestre;
  }

  public Date getDateDebut() {
    return this.dateDebut;
  }

  public void setDateDebut(Date dateDebut) {
    this.dateDebut = dateDebut;
  }

  public Date getDateFin() {
    return this.dateFin;
  }

  public void setDateFin(Date dateFin) {
    this.dateFin = dateFin;
  }

}
