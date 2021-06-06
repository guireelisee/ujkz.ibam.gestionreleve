package ujkz.ibam.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import ujkz.ibam.models.Etudiant;
import ujkz.ibam.utils.ServiceDbConnection;

public class ServiceEtudiant {
  private static Scanner sc = new Scanner(System.in);

  private ServiceEtudiant() {
  }

  public static ResultSet findAll() {
    ResultSet rs = null;
    String req = "SELECT * FROM Etudiant";
    try {
      Connection connexion = ServiceDbConnection.getConnection();
      PreparedStatement ps = connexion.prepareStatement(req);
      rs = ps.executeQuery();
    } catch (SQLException exception) {
      exception.printStackTrace();
    }
    return rs;
  }

  public static ResultSet findByMatricule(int matricule) {
    ResultSet rs = null;
    String req = "SELECT * FROM Etudiant WHERE matricule = ?";
    try {
      Connection connexion = ServiceDbConnection.getConnection();
      PreparedStatement ps = connexion.prepareStatement(req);
      ps.setInt(1, matricule);
      rs = ps.executeQuery();
    } catch (SQLException exception) {
      exception.printStackTrace();
    }
    return rs;
  }

  public static boolean addEtudiant(Etudiant e) {
    boolean resultat = false;
    String req = "INSERT INTO Etudiant (matricule, nom, prenom, email, phone) VALUES (?, ?, ?, ?, ?)";
    try {
      Connection connexion = ServiceDbConnection.getConnection();
      PreparedStatement ps = connexion.prepareStatement(req, PreparedStatement.RETURN_GENERATED_KEYS);
      ps.setInt(1, 0);
      ps.setString(2, e.getNom());
      ps.setString(3, e.getPrenom());
      ps.setString(4, e.getEmail());
      ps.setString(5, e.getPhone());

      ps.executeUpdate();
      ResultSet cle = ps.getGeneratedKeys();
      if (cle.next()) {
        e.setMatricule(cle.getInt(1));
        resultat = true;
        ps.close();
      }
    } catch (SQLException exception) {
      exception.printStackTrace();
    }
    return resultat;
  }

  public static boolean updateEtudiant(Etudiant e, int matricule) {
    boolean resultat = false;
    String req = "UPDATE Etudiant SET nom = ?, prenom =? , email = ?, phone = ? WHERE matricule = ?;";
    try {
      Connection connexion = ServiceDbConnection.getConnection();
      PreparedStatement ps = connexion.prepareStatement(req, PreparedStatement.RETURN_GENERATED_KEYS);
      ps.setString(1, e.getNom());
      ps.setString(2, e.getPrenom());
      ps.setString(3, e.getEmail());
      ps.setString(4, e.getPhone());
      ps.setInt(5, matricule);

      int rs = ps.executeUpdate();
      if (rs != 0) {
        resultat = true;
        ps.close();
      }
    } catch (SQLException exception) {
      exception.printStackTrace();
    }
    return resultat;
  }

  public static boolean updateString(String champ, int matricule, String valeur) {
    boolean resultat = false;
    String req = "UPDATE Etudiant SET " + champ + "= ? WHERE matricule = ?";
    try {
      Connection connexion = ServiceDbConnection.getConnection();
      PreparedStatement ps = connexion.prepareStatement(req, PreparedStatement.RETURN_GENERATED_KEYS);
      ps.setString(1, valeur);
      ps.setInt(2, matricule);

      int rs = ps.executeUpdate();
      if (rs != 0) {
        resultat = true;
        ps.close();
      }
    } catch (SQLException exception) {
      exception.printStackTrace();
    }
    return resultat;
  }

  public static boolean deleteEtudiant(int matricule) {
    boolean resultat = false;
    try {
      Connection connexion = ServiceDbConnection.getConnection();
      PreparedStatement ps = connexion.prepareStatement("DELETE FROM Etudiant WHERE matricule = ?;",
          PreparedStatement.RETURN_GENERATED_KEYS);
      ps.setInt(1, matricule);

      int rs = ps.executeUpdate();
      if (rs != 0) {
        resultat = true;
        ps.close();
      }
    } catch (SQLException exception) {
      exception.printStackTrace();
    }
    return resultat;
  }

  public static int enregistrerEtudiant() throws SQLException {
    int nombreEtudiant = 0;
    int matricule = 0;
    do {
      sc = new Scanner(System.in);
      System.out.println("\n\t\t------------ ENREGISTREMENT ETUDIANTS ------------\n");
      System.out.print("\tCombien d'étudiants voulez-vous enregistrer ? : ");
      try {
        nombreEtudiant = sc.nextInt();
        if (nombreEtudiant <= 0) {
          System.out.print("\n\t\t     Le nombre d'étudiants doit être supérieur à 0!\n");
        }
      } catch (Exception e) {
        System.out.print("\n\t\t            Saisie non numérique, réessayer!\n");
      }
      sc.reset();
    } while (nombreEtudiant <= 0);

    for (int i = 0; i < nombreEtudiant; i++) {
      if (nombreEtudiant == 1) {
        System.out.println("\n\t\t------------ ENREGISTREMENT D'UN ETUDIANT ------------\n");
      } else {
        System.out.println("\n\t\t------------ ENREGISTREMENT DE L'ETUDIANT N°" + (i + 1) + "------------\n");
      }
      sc = new Scanner(System.in);
      System.out.print("\tNom: ");
      String nom = sc.nextLine();
      System.out.print("\tPrénom(s): ");
      String prenom = sc.nextLine();
      System.out.print("\tEmail: ");
      String email = sc.nextLine();
      System.out.print("\tTéléphone: ");
      String phone = sc.nextLine();

      Etudiant etudiant = new Etudiant(matricule, nom, prenom, email, phone);
      if (addEtudiant(etudiant)) {
        System.out.print("\tMatricule généré: " + etudiant.getMatricule());
        System.out.print("\n\t\t              Enregistrement réussi!\n");
        matricule = etudiant.getMatricule();
      } else {
        System.out.print("\n\t\t              Enregistrement échoué!\n");
      }
    }
    return matricule;
  }

  public static void rechercherEtudiant() throws SQLException {
    int matricule = 0;
    boolean testSaisie = false;
    String trait = "\t\t    | |                                         | |";
    if (findAll().next()) {
      do {
        sc = new Scanner(System.in);
        System.out.println("\n\t\t------------ AFFICHAGE FICHE ETUDIANT ------------");
        System.out.print("\n\tMatricule de l'étudiant: ");
        try {
          matricule = sc.nextInt();
          testSaisie = true;
        } catch (Exception e) {
          System.out.print("\n\t\t            Saisie non numérique, réessayer!\n");
        }
        sc.reset();
      } while (!testSaisie);
      ResultSet rs = findByMatricule(matricule);
      if (rs.next()) {
        System.out.println("\n\t\t    .+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+++.");
        System.out.println("\t\t    | .*****************************************. |");
        System.out.println(trait);
        System.out.println("\t\t    | |              FICHE ETUDIANT             | |");
        System.out.println(trait);
        System.out.println("\t\t           Nom: " + rs.getString("nom"));
        System.out.println(trait);
        System.out.println("\t\t           Prénom(s): " + rs.getString("prenom"));
        System.out.println(trait);
        System.out.println("\t\t           Email: " + rs.getString("email"));
        System.out.println(trait);
        System.out.println("\t\t           Téléphone: " + rs.getString("phone"));
        System.out.println(trait);
        System.out.println(trait);
        System.out.println("\t\t    | '*****************************************' |");
        System.out.println("\t\t    '-+-+-+-+-+-+-+-+-+-++-+-+-+-+-+-+-+-+-+-+-+-+'");
        sc = new Scanner(System.in);
        System.out.print("\n\n\t\t          Appuyer sur Entrée pour continuer ");
        sc.nextLine();
        System.out.print("\n");
      } else {
        System.out.print("\n\t\t               Etudiant introuvable!\n");
      }
    } else {
      System.out.print("\n\t\t              Pas d'étudiant enregistré !\n");
    }
  }

  public static void afficherListeEtudiants() throws SQLException {
    String trait = "\t\t    | |                                         | |";
    if (findAll().next()) {
      ResultSet rsAll = findAll();
      while (rsAll.next()) {
        System.out.println("\n\t\t    .+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+++.");
        System.out.println("\t\t    | .*****************************************. |");
        System.out.println(trait);
        System.out.println("\t\t    | |              FICHE ETUDIANT             | |");
        System.out.println(trait);
        System.out.println("\t\t           Matricule: " + rsAll.getInt("matricule"));
        System.out.println(trait);
        System.out.println("\t\t           Nom: " + rsAll.getString("nom"));
        System.out.println(trait);
        System.out.println("\t\t           Prénom(s): " + rsAll.getString("prenom"));
        System.out.println(trait);
        System.out.println("\t\t           Email: " + rsAll.getString("email"));
        System.out.println(trait);
        System.out.println("\t\t           Téléphone: " + rsAll.getString("phone"));
        System.out.println(trait);
        System.out.println(trait);
        System.out.println("\t\t    | '*****************************************' |");
        System.out.println("\t\t    '-+-+-+-+-+-+-+-+-+-++-+-+-+-+-+-+-+-+-+-+-+-+'");
        sc = new Scanner(System.in);
        System.out.print("\n\n\t\t          Appuyer sur Entrée pour continuer ");
        sc.nextLine();
        System.out.print("\n");
      }
    } else {
      System.out.print("\n\t\t              Pas d'étudiant enregistré !\n");
    }
  }

  public static void modifierEtudiant() throws SQLException {
    boolean saisie = false;
    boolean testSaisie = false;
    int choix = 0;
    int count = 0;
    int matricule = 0;
    String nom = null;
    String prenom = null;
    String phone = null;
    String email = null;
    String trait = "\t\t    | |                                         | |";


    if (findAll().next()) {
      do {
        sc = new Scanner(System.in);
        System.out.println("\n\t\t------------ MODIFIER INFORMATIONS ETUDIANT ------------");
        ResultSet rsAllEtu = findAll();
        while (rsAllEtu.next()) {
          if (count == 0) {
            System.out.println("\n\t\t    Etudiant(s) enregistré(s):");
            count++;
          }
          System.out.println("\n\t\t    * Nom : " + rsAllEtu.getString("nom") + "   Prénom(s) :"
              + rsAllEtu.getString("prenom") + "   CODE: " + rsAllEtu.getInt("matricule"));
        }
        System.out.print("\n\tMatricule de l'étudiant: ");
        try {
          matricule = sc.nextInt();
          testSaisie = true;
        } catch (Exception e) {
          System.out.print("\n\t\t            Saisie non numérique, réessayer!\n");
        }
        sc.reset();
      } while (!testSaisie);
      ResultSet rs = findByMatricule(matricule);
      if (rs.next()) {
        do {
          sc = new Scanner(System.in);
          System.out.println("\n\t\t    .+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+++.");
          System.out.println("\t\t    | .*****************************************. |");
          System.out.println(trait);
          System.out.println("\t\t    | |                  MODIFIER  :            | |");
          System.out.println(trait);
          System.out.println("\t\t    * *    1-    TOUT                           * *");
          System.out.println(trait);
          System.out.println("\t\t    * *    2-    Nom                            * *");
          System.out.println(trait);
          System.out.println("\t\t    * *    3-    Prénom(s)                      * *");
          System.out.println(trait);
          System.out.println("\t\t    * *    4-    Email                          * *");
          System.out.println(trait);
          System.out.println("\t\t    * *    5-    Téléphone                        * *");
          System.out.println(trait);
          System.out.println(trait);
          System.out.println("\t\t    | '*****************************************' |");
          System.out.println("\t\t    '-+-+-+-+-+-+-+-+-+-++-+-+-+-+-+-+-+-+-+-+-+-+'");
          System.out.print("\n\t\t                 Faites votre choix: ");
          try {
            choix = sc.nextInt();
            saisie = true;
          } catch (Exception e) {
            System.out.print("\n\t\t            Saisie non numérique, réessayer!\n");
          }
          sc.reset();
        } while (!saisie);
        switch (choix) {
          case 1:
            System.out.println("\n\t\t------------ MODIFICATION DE TOUTES LES INFORMATIONS ------------");
            sc = new Scanner(System.in);
            System.out.print("\n\tNouveau nom: ");
            nom = sc.nextLine();
            System.out.print("\tNouveau(x) prénom(s): ");
            prenom = sc.nextLine();
            System.out.print("\tNouvel email: ");
            email = sc.nextLine();
            System.out.print("\tNouveau phone: ");
            phone = sc.nextLine();

            Etudiant etudiant = new Etudiant(matricule, nom, prenom, email, phone);
            if (updateEtudiant(etudiant, matricule)) {
              System.out.print("\n\t\t             Modification réussie! \n");
            } else {
              System.out.print("\n\t\t             Modification échouée! \n");
            }
            break;

          case 2:
            System.out.println("\n\t\t------------ MODIFICATION DU NOM ------------");
            sc = new Scanner(System.in);
            System.out.print("\n\tNouveau nom: ");
            nom = sc.nextLine();
            if (updateString("nom", matricule, nom)) {
              System.out.print("\n\t\t             Modification réussie! \n");
            } else {
              System.out.print("\n\t\t             Modification échouée! \n");
            }
            break;

          case 3:
            System.out.println("\n\t\t------------ MODIFICATION DU PRENOM ------------");
            sc = new Scanner(System.in);
            System.out.print("\n\tNouveau(x) prénom(s): ");
            prenom = sc.nextLine();
            if (updateString("prenom", matricule, prenom)) {
              System.out.print("\n\t\t             Modification réussie! \n");
            } else {
              System.out.print("\n\t\t             Modification échouée! \n");
            }
            break;

          case 4:
            System.out.println("\n\t\t------------ MODIFICATION DU MAIL ------------");
            sc = new Scanner(System.in);
            System.out.print("\n\tNouvel email: ");
            email = sc.nextLine();
            if (updateString("email", matricule, email)) {
              System.out.print("\n\t\t             Modification réussie! \n");
            } else {
              System.out.print("\n\t\t             Modification échouée! \n");
            }
            break;

          case 5:
            System.out.println("\n\t\t------------ MODIFICATION DU CONTACT ------------");
            sc = new Scanner(System.in);
            System.out.print("\n\tNouveau téléphone: ");
            phone = sc.nextLine();
            if (updateString("phone", matricule, phone)) {
              System.out.print("\n\t\t             Modification réussie! \n");
            } else {
              System.out.print("\n\t\t             Modification échouée! \n");
            }
            break;

          default:
            System.out.println("\n\t\t\t     Choix inexistant, réessayer!\n");
            break;
        }
      } else {
        System.out.print("\n\t\t               Etudiant introuvable!\n");
      }
    } else {
      System.out.print("\n\t\t              Pas d'étudiant enregistré !\n");
    }
  }

  public static void supprimerEtudiant() throws SQLException {
    boolean testSaisie = false;
    int matricule = 0;
    int count = 0;

    if (findAll().next()) {
      do {
        sc = new Scanner(System.in);
        System.out.println("\n\t\t------------ SUPPRESSION ETUDIANT ------------");
        ResultSet rsAllEtu = findAll();
        while (rsAllEtu.next()) {
          if (count == 0) {
            System.out.println("\n\t\t    Etudiant(s) enregistré(s):");
            count++;
          }
          System.out.println("\n\t\t    * Nom : " + rsAllEtu.getString("nom") + "   Prénom(s) :"
              + rsAllEtu.getString("prenom") + "   CODE: " + rsAllEtu.getInt("matricule"));
        }
        System.out.print("\n\tMatricule de l'étudiant: ");
        try {
          matricule = sc.nextInt();
          testSaisie = true;
        } catch (Exception e) {
          System.out.print("\n\t\t            Saisie non numérique, réessayer!\n");
        }
        sc.reset();
      } while (!testSaisie);
      if (findByMatricule(matricule).next()) {
        System.out.print("\n\t\t          Voulez-vous vraiment continuer? [O/n] ");
        sc = new Scanner(System.in);
        String reponse = sc.nextLine();
        if (reponse.equals("O") || reponse.equals("o")) {
          deleteEtudiant(matricule);
          System.out.print("\n\t\t                Suppression validée!\n");
        } else {
          System.out.print("\n\t\t                Suppression annulée!\n");
        }
      } else {
        System.out.print("\n\t\t               Etudiant introuvable!\n");
      }
    } else {
      System.out.print("\n\t\t              Pas d'étudiant enregistré !\n");
    }
  }

  public static void remplirTout(int matricule) throws SQLException {
    String reponse = null;

    if (findByMatricule(matricule).next()) {
      if (!ServiceEcu.findAll().next()) {
        System.out.print("\n\t\t\t               ECU vide!\n\n\t\t\tVoulez-vous d'abord en ajouter? [O/n] ");
        sc = new Scanner(System.in);
        reponse = sc.nextLine();
        if (reponse.equals("O") || reponse.equals("o")) {
          if (!ServiceUe.findAll().next()) {
            System.out.print("\n\t\t\t               UE vide!\n\n\t\t\tVoulez-vous d'abord en ajouter? [O/n] ");
            sc = new Scanner(System.in);
            reponse = sc.nextLine();
            if (reponse.equals("O") || reponse.equals("o")) {
              if (!ServiceSemestre.findAll().next()) {
                System.out.print("\n\t\t\t           Semestre vide!\n\n\t\t\tVoulez-vous d'abord en ajouter? [O/n] ");
                sc = new Scanner(System.in);
                reponse = sc.nextLine();
                if (reponse.equals("O") || reponse.equals("o")) {
                  if (!ServiceParcours.findAll().next()) {
                    System.out
                        .print("\n\t\t\t           Parcours vide!\n\n\t\t\tVoulez-vous d'abord en ajouter? [O/n] ");
                    sc = new Scanner(System.in);
                    reponse = sc.nextLine();
                    if (reponse.equals("O") || reponse.equals("o")) {
                      ServiceParcours.enregistrerParcours();
                      ServiceSemestre.enregistrerSemestre();
                      ServiceUe.enregistrerUe();
                      ServiceEcu.enregistrerEcu();
                      ServiceNote.enregistrerNote();
                    } else {
                      System.out.print("\n\t\t                    Ajout annulé!\n");
                    }
                  } else {
                    ServiceSemestre.enregistrerSemestre();
                    ServiceUe.enregistrerUe();
                    ServiceEcu.enregistrerEcu();
                    ServiceNote.enregistrerNote();
                  }
                } else {
                  System.out.print("\n\t\t                    Ajout annulé!\n");
                }
              } else {
                ServiceUe.enregistrerUe();
                ServiceEcu.enregistrerEcu();
                ServiceNote.enregistrerNote();
              }
            } else {
              System.out.print("\n\t\t                    Ajout annulé!\n");
            }
          } else {
            ServiceEcu.enregistrerEcu();
            ServiceNote.enregistrerNote();
          }
        } else {
          System.out.print("\n\t\t                    Ajout annulé!\n");
        }
      } else {
        ServiceNote.enregistrerNote();
      }
    }
  }

  public static void afficherReleveParEtudiant() throws SQLException {
    String trait = "\t\t    | |                                                       | |";
    int codeSemestre = 0;
    int matricule = 0;
    int count = 0;
    boolean testSaisie = false;
    boolean semValide = false;
    Double moy = 0.0;
    Double pond = 0.0;
    boolean noteTest = false;
    int countSem = 0;
    int totalCredit = 0;
    int codeUe = 0;

    if (findAll().next()) {
      ResultSet rsAllEtu = findAll();
      while (rsAllEtu.next()) {
        if (count == 0) {
          System.out.println("\n\t\t    Etudiant(s) enregistré(s):");
          count++;
        }
        System.out.println("\n\t\t    * Nom : " + rsAllEtu.getString("nom") + "   Prénom(s) :"
            + rsAllEtu.getString("prenom") + "   CODE: " + rsAllEtu.getInt("matricule"));
      }
      do {
        sc = new Scanner(System.in);
        System.out.print("\n\tMatricule de l'étudiant: ");
        try {
          matricule = sc.nextInt();
          testSaisie = true;
        } catch (Exception e) {
          System.out.print("\n\t\t            Saisie non numérique, réessayer!\n");
        }
        sc.reset();
      } while (!testSaisie);
      ResultSet rsEtud = findByMatricule(matricule);
      if (rsEtud.next()) {
        rsEtud = findByMatricule(matricule);
        rsEtud.next();
        if (ServiceNote.findByMatricule(matricule).next()) {
          count = 0;
          ResultSet rsAllSem = ServiceSemestre.findAll();
          while (rsAllSem.next()) {
            if (count == 0) {
              System.out.println("\n\t\t    Semestre(s) disponible(s):");
              count++;
            }
            System.out.println("\n\t\t    * SEMESTRE: " + rsAllSem.getString("libelleSemestre") + "   CODE: "
                + rsAllSem.getInt("codeSemestre"));
          }
          testSaisie = false;
        do {
          sc = new Scanner(System.in);
          System.out.print("\n\tCode du semestre: ");
          try {
            codeSemestre = sc.nextInt();
            testSaisie = true;
          } catch (Exception e) {
            System.out.print("\n\t\t            Saisie non numérique, réessayer!\n");
          }
          sc.reset();
        } while (!testSaisie);
          if (ServiceParcours.findAll().next()) {
            if (ServiceSemestre.findByCodeSemestre(codeSemestre).next()) {
              try {
                count = 0;
                ResultSet rsEtudNote = ServiceNote.findByMatricule(matricule);
                while (rsEtudNote.next()) {
                  ResultSet rsEtudEcu = ServiceEcu.findByCodeEcu(rsEtudNote.getInt("codeEcu"));
                  rsEtudEcu.next();
                  ResultSet rsEtudUe = ServiceUe.findByCodeUe(rsEtudEcu.getInt("codeUe"));
                  rsEtudUe.next();
                  ResultSet rsEtudSem = ServiceSemestre.findByCodeSemestre(rsEtudUe.getInt("codeSemestre"));
                  rsEtudSem.next();
                  if (rsEtudSem.getInt("codeSemestre") == codeSemestre) {
                    ResultSet rsCreditUe = ServiceUe.sumCreditUe(codeSemestre);
                    rsCreditUe.next();
                    totalCredit = rsCreditUe.getInt("sum");

                    ResultSet rsEtudParcSem = ServiceSemestre.findParcoursByCodeSemestre(codeSemestre);
                    rsEtudParcSem.next();
                    ResultSet rsEtudParc = ServiceParcours.findByCodeParcours(rsEtudParcSem.getInt("codeParcours"));
                    rsEtudParc.next();
                    if (count == 0) {
                      System.out.println("\n\t\t    .+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+--+-+-++.");
                      System.out.println("\t\t    | .*******************************************************. |");
                      System.out.println(trait);
                      System.out.println("\t\t    | |                     RELEVE DE NOTES                   | |");
                      System.out.println(trait);
                      System.out.println("\t\t    * *       Nom: " + rsEtud.getString("nom") + "    Prénom(s): "
                          + rsEtud.getString("prenom"));
                      System.out.println(trait);
                      System.out.println("\t\t    * *       PARCOURS: " + rsEtudParc.getString("libelleParcours"));
                      System.out.println(trait);
                      System.out.println("\t\t    * *       SEMESTRE: " + rsEtudSem.getString("libelleSemestre")+"  CREDIT: "+totalCredit);
                      count++;
                    }
                    System.out.println(trait);
                    if (rsEtudUe.getInt("codeUe") != codeUe){
                      System.out.println("\t\t    * *       UE: " + rsEtudUe.getString("libelleUe") + " TYPE: "+ rsEtudUe.getString("typeUe") + " CREDIT: " + rsEtudUe.getString("creditUe"));
                    }
                    codeUe = rsEtudUe.getInt("codeUe");
                    System.out.println(trait);
                    System.out.println("\t\t    * *              ECU: " + rsEtudEcu.getString("libelleEcu"));
                    System.out.println("\t\t    * *              NOTE: " + rsEtudNote.getDouble("valeur") + " / 20");
                    System.out.println("\t\t    * *              CREDIT: " + rsEtudEcu.getInt("creditEcu"));
                    System.out.println("\t\t    * *              SESSION: " + rsEtudNote.getString("session"));

                    pond = pond + (rsEtudNote.getDouble("valeur") * rsEtudEcu.getInt("creditEcu"));
                    moy = (pond / totalCredit);
                    if (rsEtudNote.getInt("valeur") > 4) {
                      System.out.println("\t\t    * *              Statut: ECU validé!");
                      semValide = (moy >= 10) ? true : false;
                    } else {
                      System.out.println("\t\t    * *              Statut: ECU à récomposer!");
                      semValide = false;
                    }
                    noteTest = true;
                  } else {
                    countSem++;
                  }
                }
              } catch (Exception e) {
                System.out.print("\n\t\t           Pas de notes associé à ce semestre !\n");
              }
              if (noteTest) {
                String decision = (semValide) ? "Semestre validé" : "Semestre non validé";
                System.out.println(trait);
                System.out.println("\t\t    * *       MOYENNE GÉNÉRALE: " + (double)Math.round(moy * 100) / 100 + "      PONDÉRÉE: " + pond);
                System.out.println(trait);
                System.out.println("\n\t\t    * *          DÉCISION DU JURY: " + decision);
                System.out.println(trait);
                System.out.println(trait);
                System.out.println("\t\t    | '*******************************************************' |");
                System.out.println("\t\t    '-+-+-+-+-+-+-+-+-+-++-+-+-+-+-+-+-+-+-+-+-+-+-+++++-+-+-+-'");
                sc = new Scanner(System.in);
                System.out.print("\n\n\t\t                Appuyer sur Entrée pour continuer ");
                sc.nextLine();
                System.out.print("\n");
              }
              ResultSet rsCount = ServiceSemestre.sumTable();
              rsCount.next();
              if (countSem == rsCount.getInt("sum")) {
                System.out.print("\n\t\t           Pas de notes associé à ce semestre!\n");
              }
            } else{
              System.out.print("\n\t\t             Semestre inexistant, réessayer!\n");
            }
          } else {
            System.out.print("\n\t\t             Pas de parcours enregistré!\n");
          }
        } else {
          System.out.print("\n\t\t             Cet étudiant n'a pas de relevé!\n");
          }
      } else {
        System.out.print("\n\t\t               Etudiant introuvable!\n");
        }
    } else {
      System.out.print("\n\t\t              Pas d'étudiant enregistré !\n");
      }
  }

  public static void afficherReleveEtudiants() throws SQLException {
    String trait = "\t\t    | |                                                       | |";
    int codeSemestre = 0;
    int matricule = 0;
    int count = 0;
    boolean testSaisie = false;
    boolean semValide = false;
    Double moy = 0.0;
    Double pond = 0.0;
    boolean noteTest = false;
    int countSem = 0;
    int totalCredit = 0;
    int codeUe = 0;
    boolean ecuARecomposer = false;

    if (findAll().next()) {
      if (ServiceSemestre.findAll().next()) {
        ResultSet rsAllSem = ServiceSemestre.findAll();
        while (rsAllSem.next()) {
          if (count == 0) {
            System.out.println("\n\t\t    Semestre(s) disponible(s):");
            count++;
          }
          System.out.println("\n\t\t    * SEMESTRE: " + rsAllSem.getString("libelleSemestre") + "   CODE: "
              + rsAllSem.getInt("codeSemestre"));
        }
        testSaisie = false;
        do {
          sc = new Scanner(System.in);
          System.out.print("\n\tCode du semestre: ");
          try {
            codeSemestre = sc.nextInt();
            testSaisie = true;
          } catch (Exception e) {
            System.out.print("\n\t\t            Saisie non numérique, réessayer!\n");
          }
          sc.reset();
        } while (!testSaisie);
        ResultSet rsEtudAll = findAll();
        while (rsEtudAll.next()) {
          moy = 0.0;
          pond = 0.0;
          matricule = rsEtudAll.getInt("matricule");
          ResultSet rsEtud = findByMatricule(matricule);
          rsEtud.next();
          if (ServiceNote.findByMatricule(matricule).next()) {
            count = 0;
            if (ServiceParcours.findAll().next()) {
              if (ServiceSemestre.findByCodeSemestre(codeSemestre).next()) {
                try {
                  count = 0;
                  ResultSet rsEtudNote = ServiceNote.findByMatricule(matricule);
                  while (rsEtudNote.next()) {
                    ResultSet rsEtudEcu = ServiceEcu.findByCodeEcu(rsEtudNote.getInt("codeEcu"));
                    rsEtudEcu.next();
                    ResultSet rsEtudUe = ServiceUe.findByCodeUe(rsEtudEcu.getInt("codeUe"));
                    rsEtudUe.next();
                    ResultSet rsEtudSem = ServiceSemestre.findByCodeSemestre(rsEtudUe.getInt("codeSemestre"));
                    rsEtudSem.next();
                    if (rsEtudSem.getInt("codeSemestre") == codeSemestre) {
                      ResultSet rsCreditUe = ServiceUe.sumCreditUe(codeSemestre);
                      rsCreditUe.next();
                      totalCredit = rsCreditUe.getInt("sum");

                      ResultSet rsEtudParcSem = ServiceSemestre.findParcoursByCodeSemestre(codeSemestre);
                      rsEtudParcSem.next();
                      ResultSet rsEtudParc = ServiceParcours.findByCodeParcours(rsEtudParcSem.getInt("codeParcours"));
                      rsEtudParc.next();
                      if (count == 0) {
                        System.out.println("\n\t\t    .+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+--+-+-++.");
                        System.out.println("\t\t    | .*******************************************************. |");
                        System.out.println(trait);
                        System.out.println("\t\t    | |                     RELEVE DE NOTES                   | |");
                        System.out.println(trait);
                        System.out.println("\t\t    * *       Nom: " + rsEtud.getString("nom") + "    Prénom(s): "
                            + rsEtud.getString("prenom"));
                        System.out.println(trait);
                        System.out.println("\t\t    * *       PARCOURS: " + rsEtudParc.getString("libelleParcours"));
                        System.out.println(trait);
                        System.out.println("\t\t    * *       SEMESTRE: " + rsEtudSem.getString("libelleSemestre")
                            + "  CREDIT: " + totalCredit);
                        count++;
                      }
                      System.out.println(trait);
                      if (rsEtudUe.getInt("codeUe") != codeUe) {
                        System.out.println("\t\t    * *       UE: " + rsEtudUe.getString("libelleUe") + " TYPE: "
                            + rsEtudUe.getString("typeUe") + " CREDIT: " + rsEtudUe.getString("creditUe"));
                      }
                      codeUe = rsEtudUe.getInt("codeUe");
                      System.out.println(trait);
                      System.out.println("\t\t    * *              ECU: " + rsEtudEcu.getString("libelleEcu"));
                      System.out.println("\t\t    * *              NOTE: " + rsEtudNote.getDouble("valeur") + " / 20");
                      System.out.println("\t\t    * *              CREDIT: " + rsEtudEcu.getInt("creditEcu"));
                      System.out.println("\t\t    * *              SESSION: " + rsEtudNote.getString("session"));

                      pond = pond + (rsEtudNote.getDouble("valeur") * rsEtudEcu.getInt("creditEcu"));
                      moy = (pond / totalCredit);
                      if (rsEtudNote.getInt("valeur") >= 10) {
                        System.out.println("\t\t    * *              Statut: ECU validé!");
                        semValide = true;
                      }
                      if (rsEtudNote.getInt("valeur") < 5) {
                        System.out.println("\t\t    * *              Statut: ECU à récomposer!");
                        ecuARecomposer = true;
                      }
                      if (moy >= 12) {
                        if (rsEtudNote.getInt("valeur") == 5) {
                          System.out.println("\t\t    * *              Statut: ECU validé!");
                        }
                        semValide = true;
                      } else {
                        if (rsEtudNote.getInt("valeur") == 5) {
                          System.out.println("\t\t    * *              Statut: ECU à récomposer!");
                        }
                        semValide = false;
                      }
                      noteTest = true;
                    } else {
                      countSem++;
                    }
                  }
                } catch (Exception e) {
                  System.out.print("\n\t\t           Pas de notes associé à ce semestre !\n");
                }
                if (noteTest) {
                  String decision = (semValide && !ecuARecomposer) ? "Semestre validé" : "Semestre non validé";
                  System.out.println(trait);
                  System.out.println("\t\t    * *       MOYENNE GÉNÉRALE: " + (double) Math.round(moy * 100) / 100
                      + "      PONDÉRÉE: " + pond);
                  System.out.println(trait);
                  System.out.println("\n\t\t    * *          DÉCISION DU JURY: " + decision);
                  System.out.println(trait);
                  System.out.println(trait);
                  System.out.println("\t\t    | '*******************************************************' |");
                  System.out.println("\t\t    '-+-+-+-+-+-+-+-+-+-++-+-+-+-+-+-+-+-+-+-+-+-+-+++++-+-+-+-'");
                }
                ResultSet rsCount = ServiceSemestre.sumTable();
                rsCount.next();
                if (countSem == rsCount.getInt("sum")) {
                  System.out.print("\n\t\t           Pas de notes associé à ce semestre!\n");
                }
              } else {
                countSem++;
              }
              ResultSet rsCount = ServiceSemestre.sumTable();
              rsCount.next();
              if (countSem == rsCount.getInt("sum")) {
                System.out.print("\n\t\t             Semestre inexistant, réessayer!\n");
              }
            } else {
              System.out.print("\n\t\t             Pas de parcours enregistré!\n");
            }
          } else {
            System.out.print("\n\t\t             "+rsEtud.getString("nom")+" "+rsEtud.getString("prenom")+" n'a pas de relevé!\n");
          }
          ResultSet rsCount = ServiceSemestre.sumTable();
          rsCount.next();
          if (countSem != rsCount.getInt("sum")) {
            sc = new Scanner(System.in);
            System.out.print("\n\n\t\t                Appuyer sur Entrée pour continuer ");
            sc.nextLine();
            System.out.print("\n");
          }
        }
      } else{
        System.out.print("\n\t\t              Pas de semestre enregistré !\n");
      }
    } else {
      System.out.print("\n\t\t              Pas d'étudiant enregistré !\n");
      }
  }

}
