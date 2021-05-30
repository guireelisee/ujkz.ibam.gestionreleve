package ujkz.ibam.models;

/* Un objet Parcours comprend:
  - un code
  - un libellé
  - un diplôme associé
*/
public class Parcours {
  private int codeParcours;
  private String libelleParcours;
  private String diplomeParcours;

  // Les constructeurs
  public Parcours() {
  }

  public Parcours(int codeParcours, String libelleParcours, String diplomeParcours) {
    this.codeParcours = codeParcours;
    this.libelleParcours = libelleParcours;
    this.diplomeParcours = diplomeParcours;
  }

  // Les getters et les setters
  public int getCodeParcours() {
    return this.codeParcours;
  }

  public void setCodeParcours(int codeParcours) {
    this.codeParcours = codeParcours;
  }

  public String getLibelleParcours() {
    return this.libelleParcours;
  }

  public void setLibelleParcours(String libelleParcours) {
    this.libelleParcours = libelleParcours;
  }

  public String getDiplomeParcours() {
    return this.diplomeParcours;
  }

  public void setDiplomeParcours(String diplomeParcours) {
    this.diplomeParcours = diplomeParcours;
  }

}
