package ujkz.ibam.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import ujkz.ibam.models.Ecu;
import ujkz.ibam.utils.ServiceDbConnection;

public class ServiceEcu {
  private static Scanner sc = new Scanner(System.in);

  private ServiceEcu() {
  }

  public static ResultSet sumTable() {
    ResultSet rs = null;
    String req = "SELECT COUNT(*) AS sum FROM Ue";
    try {
      Connection connexion = ServiceDbConnection.getConnection();
      PreparedStatement ps = connexion.prepareStatement(req);
      rs = ps.executeQuery();
    } catch (SQLException exception) {
      exception.printStackTrace();
    }
    return rs;
  }

  public static ResultSet findAll() {
    ResultSet rs = null;
    String req = "SELECT * FROM Ecu";
    try {
      Connection connexion = ServiceDbConnection.getConnection();
      PreparedStatement ps = connexion.prepareStatement(req);
      rs = ps.executeQuery();
    } catch (SQLException exception) {
      exception.printStackTrace();
    }
    return rs;
  }

  public static ResultSet findByCodeEcu(int codeEcu) {
    ResultSet rs = null;
    String req = "SELECT * FROM Ecu WHERE codeEcu = ?";
    try {
      Connection connexion = ServiceDbConnection.getConnection();
      PreparedStatement ps = connexion.prepareStatement(req);
      ps.setInt(1, codeEcu);
      rs = ps.executeQuery();
    } catch (SQLException exception) {
      exception.printStackTrace();
    }
    return rs;
  }

  public static boolean addEcu(int codeUe, Ecu ecu) {
    boolean resultat = false;
    String req = "INSERT INTO Ecu (codeEcu, codeUe, libelleEcu, creditEcu) VALUES (?, ?, ?, ?)";
    try {
      Connection connexion = ServiceDbConnection.getConnection();
      PreparedStatement ps = connexion.prepareStatement(req, PreparedStatement.RETURN_GENERATED_KEYS);
      ps.setInt(1, 0);
      ps.setInt(2, codeUe);
      ps.setString(3, ecu.getLibelleEcu());
      ps.setInt(4, ecu.getCreditEcu());

      ps.executeUpdate();
      ResultSet cle = ps.getGeneratedKeys();
      if (cle.next()) {
        ecu.setCodeEcu(cle.getInt(1));
        resultat = true;
        ps.close();
      }
    } catch (SQLException exception) {
      exception.printStackTrace();
    }
    return resultat;
  }

  public static boolean updateEcu(int codeEcu, Ecu ecu) {
    boolean resultat = false;
    String req = "UPDATE Ecu SET libelleEcu = ?, creditEcu = ? WHERE codeEcu = ?;";

    try {
      Connection connexion = ServiceDbConnection.getConnection();
      PreparedStatement ps = connexion.prepareStatement(req, PreparedStatement.RETURN_GENERATED_KEYS);
      ps.setString(1, ecu.getLibelleEcu());
      ps.setInt(2, ecu.getCreditEcu());
      ps.setInt(3, codeEcu);

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

  public static boolean updateLibelle(int codeEcu, String libelleEcu) {
    boolean resultat = false;
    String req = "UPDATE Ecu SET libelleEcu = ? WHERE codeEcu = ?";
    try {
      Connection connexion = ServiceDbConnection.getConnection();
      PreparedStatement ps = connexion.prepareStatement(req, PreparedStatement.RETURN_GENERATED_KEYS);
      ps.setString(1, libelleEcu);
      ps.setInt(2, codeEcu);

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

  public static boolean updateCredit(int codeEcu, int creditEcu) {
    boolean resultat = false;
    String req = "UPDATE Ecu SET creditEcu = ? WHERE codeEcu = ?";
    try {
      Connection connexion = ServiceDbConnection.getConnection();
      PreparedStatement ps = connexion.prepareStatement(req, PreparedStatement.RETURN_GENERATED_KEYS);
      ps.setInt(1, creditEcu);
      ps.setInt(2, codeEcu);

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

  public static boolean deleteEcu(int codeEcu) {
    boolean resultat = false;
    String req = "DELETE FROM Ecu WHERE codeEcu = ?";
    try {
      Connection connexion = ServiceDbConnection.getConnection();
      PreparedStatement ps = connexion.prepareStatement(req, PreparedStatement.RETURN_GENERATED_KEYS);
      ps.setInt(1, codeEcu);

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

  public static ResultSet sumCreditEcu(int codeUe) {
    ResultSet rs = null;
    String req = "SELECT sum(creditEcu) AS sumCreditEcu FROM Ecu WHERE codeUe = ?";
    try {
      Connection connexion = ServiceDbConnection.getConnection();
      PreparedStatement ps = connexion.prepareStatement(req);
      ps.setInt(1, codeUe);
      rs = ps.executeQuery();
    } catch (SQLException exception) {
      exception.printStackTrace();
    }
    return rs;
  }

  public static void afficherListeEcu() throws SQLException {
    String trait = "\t\t    | |                                         | |";
    ResultSet rsAll = findAll();

    if (rsAll.next()) {
      rsAll = findAll();
      while (rsAll.next()) {
        System.out.println("\n\t\t    .+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+++.");
        System.out.println("\t\t    | .*****************************************. |");
        System.out.println(trait);
        System.out.println("\t\t    | |         ELEMENTS CONSTITUTIFS D'UE      | |");
        System.out.println(trait);
        System.out.println("\t\t           Code: " + rsAll.getInt("codeEcu"));
        System.out.println(trait);
        System.out.println("\t\t           Libell??: " + rsAll.getString("libelleEcu"));
        System.out.println(trait);
        System.out.println("\t\t           Cr??dit: " +rsAll.getInt("creditEcu"));
        System.out.println(trait);
        System.out.println(trait);
        System.out.println("\t\t    | '*****************************************' |");
        System.out.println("\t\t    '-+-+-+-+-+-+-+-+-+-++-+-+-+-+-+-+-+-+-+-+-+-+'");
        sc = new Scanner(System.in);
        System.out.print("\n\n\t\t          Appuyer sur Entr??e pour continuer ");
        sc.nextLine();
        System.out.print("\n");
      }
    } else {
      System.out.print("\n\t\t                Pas d'Ecu enregistr?? !\n");
    }
    rsAll.close();
  }

  public static void enregistrerEcu() throws SQLException {
    boolean testSaisie = false;
    int nombreEcu = 0;
    int codeEcu = 0;
    int codeUe = 0;
    int credit = 0;
    String libelle = null;
    int count = 0;

    if (ServiceUe.findAll().next()) {
      ResultSet rsAllUe = ServiceUe.findAll();
      while (rsAllUe.next()) {
        if (count == 0) {
          System.out.println("\n\t\t    UE(s) disponible(s):");
          count++;
        }
        System.out.println("\n\t\t    * UE : " + rsAllUe.getString("libelleUe") + "   CODE: "
            + rsAllUe.getInt("codeUe"));
      }
      do {
        sc = new Scanner(System.in);
        System.out.print("\n\tCode de l'UE: ");
        try {
          codeUe = sc.nextInt();
          testSaisie = true;
        } catch (Exception e) {
          System.out.print("\n\t\t            Saisie non num??rique, r??essayer!\n");
        }
        sc.reset();
      } while (!testSaisie);
      testSaisie = false;
      if (ServiceUe.findByCodeUe(codeUe).next()) {
        do {
          sc = new Scanner(System.in);
          System.out.println("\n\t\t------------ ENREGISTREMENT ECU ------------\n");
          System.out.print("\tCombien d'ECU voulez-vous enregistrer ? : ");
          try {
            nombreEcu = sc.nextInt();
            if (nombreEcu <= 0) {
              System.out.print("\n\t\t     Le nombre d'ECU doit ??tre sup??rieur ?? 0!\n");
            }
          } catch (Exception e) {
            System.out.print("\n\t\t            Saisie non num??rique, r??essayer!\n");
          }
          sc.reset();
        } while (nombreEcu <= 0);
        for (int i = 0; i < nombreEcu; i++) {
          if (nombreEcu == 1) {
            System.out.println("\n\t\t------------ ENREGISTREMENT D'UN ECU ------------\n");
          } else {
            System.out.println("\n\t\t------------ ENREGISTREMENT DE L'ECU N??" + (i + 1) + "------------\n");
          }
          sc = new Scanner(System.in);
          System.out.print("\tLibell??: ");
          libelle = sc.nextLine();
          ResultSet rsCreditUe = ServiceUe.findByCodeUe(codeUe);
          rsCreditUe.next();
          int creditTotalUe = rsCreditUe.getInt("creditUe");

          ResultSet rsCreditEcu = sumCreditEcu(codeUe);
          rsCreditEcu.next();
          int creditTotalEcu = rsCreditEcu.getInt("sumCreditEcu");

          do {
            sc = new Scanner(System.in);
            System.out.print("\tCr??dit: ");
            try {
              credit = sc.nextInt();
              if ((creditTotalEcu + credit) > creditTotalUe) {
                System.out
                    .print("\n\t\t            Cr??dit restant pour l'UE: " + (creditTotalUe - creditTotalEcu) + "\n");
                testSaisie = false;
              } else {
                testSaisie = true;
              }
            } catch (Exception e) {
              System.out.print("\n\t\t            Saisie non num??rique, r??essayer!\n");
            }
            sc.reset();
          } while (!testSaisie);

          Ecu ecu = new Ecu(codeEcu, libelle, credit);
          if (addEcu(codeUe, ecu)) {
            System.out.print("\tCode g??n??r??: " + ecu.getCodeEcu());
            System.out.print("\n\t\t              Enregistrement r??ussi!\n");
          } else {
            System.out.print("\n\t\t              Enregistrement ??chou??!\n");
          }
        }
      } else{
        System.out.print("\n\t\t            UE inexistante, r??essayer!\n");
      }
    } else {
      System.out.print("\n\t\t               Aucune UE enregistr??e!\n");
    }
  }

  public static void modifierEcu() throws SQLException {
    boolean saisie = false;
    boolean testSaisie = false;
    int choix = 0;
    int codeEcu = 0;
    String libelle = null;
    int credit = 0;
    String trait = "\t\t    | |                                         | |";

    if (findAll().next()) {
      do {
        sc = new Scanner(System.in);
        System.out.println("\n\t\t------------ MODIFIER INFORMATIONS ECU ------------");
        System.out.print("\n\tCode de l'Ecu: ");
        try {
          codeEcu = sc.nextInt();
          testSaisie = true;
        } catch (Exception e) {
          System.out.print("\n\t\t            Saisie non num??rique, r??essayer!\n");
        }
        sc.reset();
      } while (!testSaisie);
      if (findByCodeEcu(codeEcu).next()) {
        do {
          sc = new Scanner(System.in);
          System.out.println("\n\t\t    .+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+++.");
          System.out.println("\t\t    | .*****************************************. |");
          System.out.println(trait);
          System.out.println("\t\t    | |                  MODIFIER  :            | |");
          System.out.println(trait);
          System.out.println("\t\t    * *    1-    TOUT                           * *");
          System.out.println(trait);
          System.out.println("\t\t    * *    2-    Libell??                        * *");
          System.out.println(trait);
          System.out.println("\t\t    * *    3-    Cr??dit                         * *");
          System.out.println(trait);
          System.out.println(trait);
          System.out.println("\t\t    | '*****************************************' |");
          System.out.println("\t\t    '-+-+-+-+-+-+-+-+-+-++-+-+-+-+-+-+-+-+-+-+-+-+'");
          System.out.print("\n\t\t                 Faites votre choix: ");
          try {
            choix = sc.nextInt();
            saisie = true;
          } catch (Exception e) {
            System.out.print("\n\t\t            Saisie non num??rique, r??essayer!\n");
          }
          sc.reset();
        } while (!saisie);
        switch (choix) {
          case 1:
            System.out.println("\n\t\t------------ MODIFICATION DE TOUTES LES INFORMATIONS ------------");
            sc = new Scanner(System.in);
            System.out.print("\n\tNouveau libell??: ");
            libelle = sc.nextLine();
            testSaisie = false;
            do {
              sc = new Scanner(System.in);
              System.out.print("\n\tNouveau cr??dit: ");
              try {
                credit = sc.nextInt();
                testSaisie = true;
              } catch (Exception e) {
                System.out.print("\n\t\t            Saisie non num??rique, r??essayer!\n");
              }
              sc.reset();
            } while (!testSaisie);
            Ecu ecu = new Ecu(codeEcu, libelle, credit);
            updateEcu(codeEcu,ecu);
            System.out.print("\n\t\t             Modification r??ussie! \n");
            break;

          case 2:
            System.out.println("\n\t\t------------ MODIFICATION DU LIBELLE ------------");
            sc = new Scanner(System.in);
            System.out.print("\n\tNouveau libell??: ");
            libelle = sc.nextLine();

            updateLibelle(codeEcu, libelle);
            System.out.print("\n\t\t             Modification r??ussie! \n");
            break;

          case 3:
            System.out.println("\n\t\t------------ MODIFICATION DU CREDIT ------------");
            testSaisie = false;
            do {
              sc = new Scanner(System.in);
              System.out.print("\n\tNouveau cr??dit: ");
              try {
                credit = sc.nextInt();
                testSaisie = true;
              } catch (Exception e) {
                System.out.print("\n\t\t            Saisie non num??rique, r??essayer!\n");
              }
              sc.reset();
            } while (!testSaisie);

            updateCredit(codeEcu, credit);
            System.out.print("\n\t\t             Modification r??ussie! \n");
            break;

          default:
            System.out.println("\n\t\t\t     Choix inexistant, r??essayer!\n");
            break;
        }
      } else {
        System.out.print("\n\t\t               ECU introuvable!\n");
      }
    } else {
      System.out.print("\n\t\t                Pas d'ECU enregistr?? !\n");
    }
  }

  public static void supprimerEcu() throws SQLException {
    boolean testSaisie = false;
    int codeEcu = 0;

    if (findAll().next()) {
      do {
        sc = new Scanner(System.in);
        System.out.println("\n\t\t------------ SUPPRESSION Ecu ------------");
        System.out.print("\n\tCode de l'Ecu: ");
        try {
          codeEcu = sc.nextInt();
          testSaisie = true;
        } catch (Exception e) {
          System.out.print("\n\t\t            Saisie non num??rique, r??essayer!\n");
        }
        sc.reset();
      } while (!testSaisie);
      if (findByCodeEcu(codeEcu).next()) {
        System.out.print("\n\t\t          Voulez-vous vraiment continuer? [O/n] ");
        sc = new Scanner(System.in);
        String reponse = sc.nextLine();
        if (reponse.equals("O") || reponse.equals("o")) {
          deleteEcu(codeEcu);
          System.out.print("\n\t\t                Suppression valid??e!\n");
        } else {
          System.out.print("\n\t\t                Suppression annul??e!\n");
        }
      } else {
        System.out.print("\n\t\t               ECU introuvable!\n");
      }
    } else {
      System.out.print("\n\t\t                Pas d'ECU enregistr?? !\n");
    }
  }

}
