package ujkz.ibam.models;

/* Un objet Etudiant comprend:
  - un matricule
  - un nom
  - un prenom
  - une adresse mail
  - un numéro de téléphone
*/
public class Etudiant {
  private int matricule;
  private String nom;
  private String prenom;
  private String email;
  private String phone;

  // Les constructeurs
  public Etudiant() {
  }

  public Etudiant(int matricule, String nom, String prenom, String email, String phone) {
    this.matricule = matricule;
    this.nom = nom;
    this.prenom = prenom;
    this.email = email;
    this.phone = phone;
  }

  // Les getters et les setters
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

}
