package ujkz.ibam.models;

public class Ecu {
    private int codeEcu;
  private String libelleEcu;
  private int creditEcu;

  public Ecu() {
  }

  public Ecu(int codeEcu, String libelleEcu, int creditEcu) {
    this.codeEcu = codeEcu;
    this.libelleEcu = libelleEcu;
    this.creditEcu = creditEcu;
  }

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

  public Ecu codeEcu(int codeEcu) {
    setCodeEcu(codeEcu);
    return this;
  }

  public Ecu libelleEcu(String libelleEcu) {
    setLibelleEcu(libelleEcu);
    return this;
  }

  public Ecu creditEcu(int creditEcu) {
    setCreditEcu(creditEcu);
    return this;
  }

}
