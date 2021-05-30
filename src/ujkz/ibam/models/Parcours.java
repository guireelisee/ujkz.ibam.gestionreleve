package ujkz.ibam.models;

public class Parcours {
  private int codeParcours;
  private String libelleParcours;
  private String diplomeParcours;

  public Parcours() {
  }

  public Parcours(int codeParcours, String libelleParcours, String diplomeParcours) {
    this.codeParcours = codeParcours;
    this.libelleParcours = libelleParcours;
    this.diplomeParcours = diplomeParcours;
  }

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

  public Parcours codeParcours(int codeParcours) {
    setCodeParcours(codeParcours);
    return this;
  }

  public Parcours libelleParcours(String libelleParcours) {
    setLibelleParcours(libelleParcours);
    return this;
  }

  public Parcours diplomeParcours(String diplomeParcours) {
    setDiplomeParcours(diplomeParcours);
    return this;
  }

}
