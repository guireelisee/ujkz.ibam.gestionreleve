package ujkz.ibam.models;

public class Etudiant {
  private int matricule;
  private String nom;
  private String prenom;
  private String email;
  private String phone;

  public Etudiant() {
  }

  public Etudiant(int matricule, String nom, String prenom, String email, String phone) {
    this.matricule = matricule;
    this.nom = nom;
    this.prenom = prenom;
    this.email = email;
    this.phone = phone;
  }

  public int getMatricule() {
    return this.matricule;
  }

  public void setMatricule(int matricule) {
    this.matricule = matricule;
  }

  public String getNom() {
    return this.nom;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }

  public String getPrenom() {
    return this.prenom;
  }

  public void setPrenom(String prenom) {
    this.prenom = prenom;
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhone() {
    return this.phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public Etudiant matricule(int matricule) {
    setMatricule(matricule);
    return this;
  }

  public Etudiant nom(String nom) {
    setNom(nom);
    return this;
  }

  public Etudiant prenom(String prenom) {
    setPrenom(prenom);
    return this;
  }

  public Etudiant email(String email) {
    setEmail(email);
    return this;
  }

  public Etudiant phone(String phone) {
    setPhone(phone);
    return this;
  }


}
