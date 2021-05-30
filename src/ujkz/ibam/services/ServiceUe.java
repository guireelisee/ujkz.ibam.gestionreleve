package ujkz.ibam.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import ujkz.ibam.models.Ue;
import ujkz.ibam.utils.ServiceDbConnection;

public class ServiceUe {
  private static Scanner sc = new Scanner(System.in);

  private ServiceUe() {
  }

  public static ResultSet sumCreditUe(int codeSemestre) {
    ResultSet rs = null;
    String req = "SELECT sum(creditUe) AS sum FROM Ue WHERE codeSemestre = ?";
    try {
      Connection connexion = ServiceDbConnection.getConnection();
      PreparedStatement ps = connexion.prepareStatement(req);
      ps.setInt(1, codeSemestre);
      rs = ps.executeQuery();
    } catch (SQLException exception) {
      exception.printStackTrace();
    }
    return rs;
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
    String req = "SELECT * FROM Ue";
    try {
      Connection connexion = ServiceDbConnection.getConnection();
      PreparedStatement ps = connexion.prepareStatement(req);
      rs = ps.executeQuery();
    } catch (SQLException exception) {
      exception.printStackTrace();
    }
    return rs;
  }

  public static ResultSet findByCodeUe(int codeUe) {
    ResultSet rs = null;
    String req = "SELECT * FROM Ue WHERE codeUe = ?";
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

  public static boolean addUe(int codeSemestre, Ue ue) {
    boolean resultat = false;
    String req = "INSERT INTO Ue (codeUe,codeSemestre ,libelleUe, creditUe, typeUe) VALUES (?, ?, ?, ?, ?)";
    try {
      Connection connexion = ServiceDbConnection.getConnection();
      PreparedStatement ps = connexion.prepareStatement(req, PreparedStatement.RETURN_GENERATED_KEYS);
      ps.setInt(1, 0);
      ps.setInt(2, codeSemestre);
      ps.setString(3, ue.getLibelleUe());
      ps.setInt(4, ue.getCreditUe());
      ps.setString(5, ue.getTypeUe());

      ps.executeUpdate();
      ResultSet cle = ps.getGeneratedKeys();
      if (cle.next()) {
        ue.setCodeUe(cle.getInt(1));
        resultat = true;
        ps.close();
      }
    } catch (SQLException exception) {
      exception.printStackTrace();
    }
    return resultat;
  }

  public static boolean updateUe(int codeUe, Ue ue) {
    boolean resultat = false;
    String req = "UPDATE Ue SET libelleUe = ?, creditUe = ? WHERE codeUe = ?;";

    try {
      Connection connexion = ServiceDbConnection.getConnection();
      PreparedStatement ps = connexion.prepareStatement(req, PreparedStatement.RETURN_GENERATED_KEYS);
      ps.setString(1, ue.getLibelleUe());
      ps.setInt(2, ue.getCreditUe());
      ps.setInt(3, codeUe);

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

  public static boolean updateLibelle(int codeUe, String libelleUe) {
    boolean resultat = false;
    String req = "UPDATE Ue SET libelleUe = ? WHERE codeUe = ?";
    try {
      Connection connexion = ServiceDbConnection.getConnection();
      PreparedStatement ps = connexion.prepareStatement(req, PreparedStatement.RETURN_GENERATED_KEYS);
      ps.setString(1, libelleUe);
      ps.setInt(2, codeUe);

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

  public static boolean updateCredit(int codeUe, int creditUe) {
    boolean resultat = false;
    String req = "UPDATE Ue SET creditUe = ? WHERE codeUe = ?";
    try {
      Connection connexion = ServiceDbConnection.getConnection();
      PreparedStatement ps = connexion.prepareStatement(req, PreparedStatement.RETURN_GENERATED_KEYS);
      ps.setInt(1, creditUe);
      ps.setInt(2, codeUe);

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

  public static boolean updateType(int codeUe, String type) {
    boolean resultat = false;
    String req = "UPDATE Ue SET typeUe = ? WHERE codeUe = ?";
    try {
      Connection connexion = ServiceDbConnection.getConnection();
      PreparedStatement ps = connexion.prepareStatement(req, PreparedStatement.RETURN_GENERATED_KEYS);
      ps.setString(1, type);
      ps.setInt(2, codeUe);

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

  public static boolean deleteUe(int codeUe) {
    boolean resultat = false;
    String req = "DELETE FROM Ue WHERE codeUe = ?";
    try {
      Connection connexion = ServiceDbConnection.getConnection();
      PreparedStatement ps = connexion.prepareStatement(req, PreparedStatement.RETURN_GENERATED_KEYS);
      ps.setInt(1, codeUe);

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

  public static void afficherListeUe() throws SQLException {
    String trait = "\t\t    | |                                         | |";
    ResultSet rsAll = findAll();

    if (rsAll.next()) {
      rsAll = findAll();
      while (rsAll.next()) {
        System.out.println("\n\t\t    .+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+++.");
        System.out.println("\t\t    | .*****************************************. |");
        System.out.println(trait);
        System.out.println("\t\t    | |   UNITES D'ENSEIGNEMENTS DISPONIBLES    | |");
        System.out.println(trait);
        System.out.println("\t\t           Code: " + rsAll.getInt("codeUe"));
        System.out.println(trait);
        System.out.println("\t\t           Libellé: " + rsAll.getString("libelleUe"));
        System.out.println(trait);
        System.out.println("\t\t           Crédit: " + rsAll.getInt("creditUe"));
        System.out.println(trait);
        System.out.println("\t\t           Type: " + rsAll.getString("typeUe"));
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
      System.out.print("\n\t\t                Pas d'Ue enregistrée !\n");
    }
  }

  public static void enregistrerUe() throws SQLException {
    boolean testSaisie = false;
    int nombreUe = 0;
    int codeUe = 0;
    int codeSemestre = 0;
    int credit = 0;
    int i = 0;
    int count = 0;

    if (ServiceSemestre.findAll().next()) {
      ResultSet rsAllSem = ServiceSemestre.findAll();
      while (rsAllSem.next()) {
        if (count == 0) {
          System.out.println("\n\t\t    Semestres(s) disponible(s):");
          count++;
        }
        System.out.println("\n\t\t    * SEMESTRE : " + rsAllSem.getString("libelleSemestre") + "   CODE: "
            + rsAllSem.getInt("codeSemestre"));
      }
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
      testSaisie = false;
      if (ServiceSemestre.findByCodeSemestre(codeSemestre).next()) {
        do {
          sc = new Scanner(System.in);
          System.out.println("\n\t\t------------ ENREGISTREMENT UE ------------\n");
          System.out.print("\tCombien d'UE voulez-vous enregistrer ? : ");
          try {
            nombreUe = sc.nextInt();
            if (nombreUe <= 0) {
              System.out.print("\n\t\t     Le nombre d'UE doit être supérieur à 0!\n");
            }
          } catch (Exception e) {
            System.out.print("\n\t\t            Saisie non numérique, réessayer!\n");
          }
          sc.reset();
        } while (nombreUe <= 0);

        for (i = 0; i < nombreUe; i++) {
          if (nombreUe == 1) {
            System.out.println("\n\t\t------------ ENREGISTREMENT D'UNE UE ------------\n");
          } else {
            System.out.println("\n\t\t------------ ENREGISTREMENT DE L'UE N°" + (i + 1) + "------------\n");
          }
            sc = new Scanner(System.in);
            System.out.print("\tLibellé: ");
            String libelle = sc.nextLine();
            testSaisie = false;
            do {
              sc = new Scanner(System.in);
              System.out.print("\tCrédit: ");
              try {
                credit = sc.nextInt();
                testSaisie = true;
              } catch (Exception e) {
                System.out.print("\n\t\t            Saisie non numérique, réessayer!\n");
              }
              sc.reset();
            } while (!testSaisie);
            sc = new Scanner(System.in);
            System.out.print("\tType: ");
            String type = sc.nextLine();

            Ue ue = new Ue(codeUe, libelle, credit, type);
            if (addUe(codeSemestre, ue)) {
              System.out.print("\tCode généré: " + ue.getCodeUe());
              System.out.print("\n\t\t              Enregistrement réussi!\n");
            } else {
              System.out.print("\n\t\t              Enregistrement échoué!\n");
            }
          }
      } else {
        System.out.print("\n\t\t            Semestre inexistant, réessayer!\n");
      }
    } else {
      System.out.print("\n\t\t               Aucun semestre enregistré!\n");
    }
  }

  public static void modifierUe() throws SQLException {
    boolean saisie = false;
    boolean testSaisie = false;
    int choix = 0;
    int codeUe = 0;
    String libelle = null;
    int credit = 0;
    String type = null;
    String trait = "\t\t    | |                                         | |";

    if (findAll().next()) {
      do {
        sc = new Scanner(System.in);
        System.out.println("\n\t\t------------ MODIFIER INFORMATIONS Ue ------------");
        System.out.print("\n\tCode de l'UE: ");
        try {
          codeUe = sc.nextInt();
          testSaisie = true;
        } catch (Exception e) {
          System.out.print("\n\t\t            Saisie non numérique, réessayer!\n");
        }
        sc.reset();
      } while (!testSaisie);
      if (findByCodeUe(codeUe).next()) {
        do {
          sc = new Scanner(System.in);
          System.out.println("\n\t\t    .+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+++.");
          System.out.println("\t\t    | .*****************************************. |");
          System.out.println(trait);
          System.out.println("\t\t    | |                  MODIFIER  :            | |");
          System.out.println(trait);
          System.out.println("\t\t    * *    1-    TOUT                           * *");
          System.out.println(trait);
          System.out.println("\t\t    * *    2-    Libellé                        * *");
          System.out.println(trait);
          System.out.println("\t\t    * *    3-    Crédit                         * *");
          System.out.println(trait);
          System.out.println("\t\t    * *    4-    Type                           * *");
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
            System.out.print("\n\tNouveau libellé: ");
            libelle = sc.nextLine();
            testSaisie = false;
            do {
              sc = new Scanner(System.in);
              System.out.print("\n\tNouveau crédit: ");
              try {
                credit = sc.nextInt();
                testSaisie = true;
              } catch (Exception e) {
                System.out.print("\n\t\t            Saisie non numérique, réessayer!\n");
              }
              sc.reset();
            } while (!testSaisie);
            sc = new Scanner(System.in);
            System.out.print("\n\tNouveau type: ");
            type = sc.nextLine();

            System.out.print("\n\t\t             Modification réussie! \n");
            break;

          case 2:
            System.out.println("\n\t\t------------ MODIFICATION DU LIBELLE ------------");
            sc = new Scanner(System.in);
            System.out.print("\n\tNouveau libellé: ");
            libelle = sc.nextLine();

            Ue ue = new Ue(codeUe, libelle, credit, type);
            updateUe(codeUe, ue);
            System.out.print("\n\t\t             Modification réussie! \n");
            break;

          case 3:
            System.out.println("\n\t\t------------ MODIFICATION DU CREDIT ------------");
            testSaisie = false;
            do {
              sc = new Scanner(System.in);
              System.out.print("\n\tNouveau crédit: ");
              try {
                credit = sc.nextInt();
                testSaisie = true;
              } catch (Exception e) {
                System.out.print("\n\t\t            Saisie non numérique, réessayer!\n");
              }
              sc.reset();
            } while (!testSaisie);

            updateCredit(codeUe, credit);
            System.out.print("\n\t\t             Modification réussie! \n");
            break;

          case 4:
            System.out.println("\n\t\t------------ MODIFICATION DU TYPE ------------");
            System.out.print("\n\tNouveau type: ");
            type = sc.nextLine();

            updateType(codeUe, type);
            System.out.print("\n\t\t             Modification réussie! \n");
            break;

          default:
            System.out.println("\n\t\t\t     Choix inexistant, réessayer!\n");
            break;
        }
      } else {
        System.out.print("\n\t\t               UE introuvable!\n");
      }
    } else {
      System.out.print("\n\t\t                Pas d'UE enregistrée !\n");
    }
  }

  public static void supprimerUe() throws SQLException {
    boolean testSaisie = false;
    int codeUe = 0;

    if (findAll().next()) {
      do {
        sc = new Scanner(System.in);
        System.out.println("\n\t\t------------ SUPPRESSION UE ------------");
        System.out.print("\n\tCode de l'UE: ");
        try {
          codeUe = sc.nextInt();
          testSaisie = true;
        } catch (Exception e) {
          System.out.print("\n\t\t            Saisie non numérique, réessayer!\n");
        }
        sc.reset();
      } while (!testSaisie);
      if (findByCodeUe(codeUe).next()) {
        System.out.print("\n\t\t          Voulez-vous vraiment continuer? [O/n] ");
        sc = new Scanner(System.in);
        String reponse = sc.nextLine();
        if (reponse.equals("O") || reponse.equals("o")) {
          deleteUe(codeUe);
          System.out.print("\n\t\t                Suppression validée!\n");
        } else {
          System.out.print("\n\t\t                Suppression annulée!\n");
        }
      } else {
        System.out.print("\n\t\t               Ue introuvable!\n");
      }
    } else {
      System.out.print("\n\t\t                Pas d'UE enregistrée !\n");
    }
  }

}
