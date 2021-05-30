package ujkz.ibam.models;

/* Un objet Ue comprend:
  - un code
  - un libellé
  - un crédit
  - un type (Fondamentale / Transversale / Complémentaire)
*/
public class Ue {
  private int codeUe;
  private String libelleUe;
  private int creditUe;
  private String typeUe;

  // Les constructeurs
  public Ue() {
  }

  public Ue(int codeUe, String libelleUe, int creditUe, String typeUe) {
    this.codeUe = codeUe;
    this.libelleUe = libelleUe;
    this.creditUe = creditUe;
    this.typeUe = typeUe;
  }

  // Les getters et les setters
  public int getCodeUe() {
    return this.codeUe;
  }

  public void setCodeUe(int codeUe) {
    this.codeUe = codeUe;
  }

  public String getLibelleUe() {
    return this.libelleUe;
  }

  public void setLibelleUe(String libelleUe) {
    this.libelleUe = libelleUe;
  }

  public int getCreditUe() {
    return this.creditUe;
  }

  public void setCreditUe(int creditUe) {
    this.creditUe = creditUe;
  }

  public String getTypeUe() {
    return this.typeUe;
  }

  public void setTypeUe(String typeUe) {
    this.typeUe = typeUe;
  }

}
