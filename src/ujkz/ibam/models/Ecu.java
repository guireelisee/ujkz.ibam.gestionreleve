package ujkz.ibam.models;

/* Un objet Ecu (consideré comme la matière) comprend:
  - un code
  - un libellé
  - un crédit
*/
public class Ecu {
    private int codeEcu;
  private String libelleEcu;
  private int creditEcu;

  // Les constructeurs
  public Ecu() {
  }

  public Ecu(int codeEcu, String libelleEcu, int creditEcu) {
    this.codeEcu = codeEcu;
    this.libelleEcu = libelleEcu;
    this.creditEcu = creditEcu;
  }

  // Les getters et les setters
  public int getCodeEcu() {
    return this.codeEcu;
  }

  public void setCodeEcu(int codeEcu) {
    this.codeEcu = codeEcu;
  }

  public String getLibelleEcu() {
    return this.libelleEcu;
  }

  public void setLibelleEcu(String libelleEcu) {
    this.libelleEcu = libelleEcu;
  }

  public int getCreditEcu() {
    return this.creditEcu;
  }

  public void setCreditEcu(int creditEcu) {
    this.creditEcu = creditEcu;
  }

}
