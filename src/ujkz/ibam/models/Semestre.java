package ujkz.ibam.models;

import java.util.Date;

public class Semestre {
  private int codeSemestre;
  private String libelleSemestre;
  private Date dateDebut;
  private Date dateFin;

  public Semestre() {
  }

  public Semestre(int codeSemestre, String libelleSemestre, Date dateDebut, Date dateFin) {
    this.codeSemestre = codeSemestre;
    this.libelleSemestre = libelleSemestre;
    this.dateDebut = dateDebut;
    this.dateFin = dateFin;
  }

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

  public Semestre codeSemestre(int codeSemestre) {
    setCodeSemestre(codeSemestre);
    return this;
  }

  public Semestre libelleSemestre(String libelleSemestre) {
    setLibelleSemestre(libelleSemestre);
    return this;
  }

  public Semestre dateDebut(Date dateDebut) {
    setDateDebut(dateDebut);
    return this;
  }

  public Semestre dateFin(Date dateFin) {
    setDateFin(dateFin);
    return this;
  }

}
