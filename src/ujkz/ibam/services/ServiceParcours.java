package ujkz.ibam.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import ujkz.ibam.models.Parcours;
import ujkz.ibam.utils.ServiceDbConnection;

public class ServiceParcours {
  private static Scanner sc = new Scanner(System.in);

  private ServiceParcours(){
  }

  public static ResultSet findAll() {
    ResultSet rs = null;
    String req = "SELECT * FROM Parcours";
    try {
      Connection connexion = ServiceDbConnection.getConnection();
      PreparedStatement ps = connexion.prepareStatement(req);
      rs = ps.executeQuery();
    } catch (SQLException exception) {
      exception.printStackTrace();
    }
    return rs;
  }

  public static ResultSet findByCodeParcours(int codeParcours) {
    ResultSet rs = null;
    String req = "SELECT * FROM Parcours WHERE codeParcours = ?";
    try {
      Connection connexion = ServiceDbConnection.getConnection();
      PreparedStatement ps = connexion.prepareStatement(req);
      ps.setInt(1, codeParcours);
      rs = ps.executeQuery();
    } catch (SQLException exception) {
      exception.printStackTrace();
    }
    return rs;
  }

  public static boolean addParcours(Parcours par) {
    boolean resultat = false;
    String req = "INSERT INTO Parcours (codeParcours, libelleParcours, diplomeParcours) VALUES (?, ?, ?)";
    try {
      Connection connexion = ServiceDbConnection.getConnection();
      PreparedStatement ps = connexion.prepareStatement(req, PreparedStatement.RETURN_GENERATED_KEYS);
      ps.setInt(1, 0);
      ps.setString(2, par.getLibelleParcours());
      ps.setString(3, par.getDiplomeParcours());

      ps.executeUpdate();
      ResultSet cle = ps.getGeneratedKeys();
      if (cle.next()) {
        par.setCodeParcours(cle.getInt(1));
        resultat = true;
        ps.close();
      }
    } catch (SQLException exception) {
      exception.printStackTrace();
    }
    return resultat;
  }

  public static boolean updateParcours(int codeParcours, Parcours par) {
    boolean resultat = false;
    String req = "UPDATE Parcours SET libelleParcours = ?, diplomeParcours = ? WHERE codeParcours = ?;";

    try {
      Connection connexion = ServiceDbConnection.getConnection();
      PreparedStatement ps = connexion.prepareStatement(req, PreparedStatement.RETURN_GENERATED_KEYS);
      ps.setString(1, par.getLibelleParcours());
      ps.setString(2, par.getDiplomeParcours());
      ps.setInt(3, codeParcours);

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

  public static boolean updateLibelle(int codeParcours, String libelleParcours) {
    boolean resultat = false;
    String req = "UPDATE Parcours SET libelleParcours = ? WHERE codeParcours = ?";
    try {
      Connection connexion = ServiceDbConnection.getConnection();
      PreparedStatement ps = connexion.prepareStatement(req, PreparedStatement.RETURN_GENERATED_KEYS);
      ps.setString(1, libelleParcours);
      ps.setInt(2, codeParcours);

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

  public static boolean updateDiplome(int codeParcours, String diplomeParcours) {
    boolean resultat = false;
    String req = "UPDATE Parcours SET diplomeParcours = ? WHERE codeParcours = ?";
    try {
      Connection connexion = ServiceDbConnection.getConnection();
      PreparedStatement ps = connexion.prepareStatement(req, PreparedStatement.RETURN_GENERATED_KEYS);
      ps.setString(1, diplomeParcours);
      ps.setInt(2, codeParcours);

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

  public static boolean deleteParcours(int codeParcours) {
    boolean resultat = false;
    String req = "DELETE FROM Parcours WHERE codeParcours = ?";
    try {
      Connection connexion = ServiceDbConnection.getConnection();
      PreparedStatement ps = connexion.prepareStatement(req, PreparedStatement.RETURN_GENERATED_KEYS);
      ps.setInt(1, codeParcours);

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
  public static boolean deleteFromParcSemParcours(int codeParcours) {
    boolean resultat = false;
    String req = "DELETE FROM Parc_Sem WHERE codeParcours = ?";
    try {
      Connection connexion = ServiceDbConnection.getConnection();
      PreparedStatement ps = connexion.prepareStatement(req, PreparedStatement.RETURN_GENERATED_KEYS);
      ps.setInt(1, codeParcours);

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

  public static void afficherListeParcours() throws SQLException {
    String trait = "\t\t    | |                                         | |";
    ResultSet rsAll = findAll();

    if (rsAll.next()) {
      rsAll = findAll();
      while(rsAll.next()) {
        System.out.println("\n\t\t    .+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+++.");
        System.out.println("\t\t    | .*****************************************. |");
        System.out.println(trait);
        System.out.println("\t\t    | |             PARCOURS DISPONIBLES        | |");
        System.out.println(trait);
        System.out.println("\t\t           Code: " +rsAll.getInt("codeParcours"));
        System.out.println(trait);
        System.out.println("\t\t           Libellé: " +rsAll.getString("libelleParcours"));
        System.out.println(trait);
        System.out.println("\t\t           Diplôme: " +rsAll.getString("diplomeParcours"));
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
      System.out.print("\n\t\t              Pas de parcours enregistré !\n");
    }
    rsAll.close();
  }

  public static void enregistrerParcours() {
    int nombreParcours= 0;
    int codeParcours = 0;
    do {
      sc = new Scanner(System.in);
      System.out.println("\n\t\t------------ ENREGISTREMENT PARCOURS ------------\n");
      System.out.print("\tCombien de parcours voulez-vous enregistrer ? : ");
      try {
        nombreParcours= sc.nextInt();
        if (nombreParcours <= 0) {
          System.out.print("\n\t\t     Le nombre de parcours doit être supérieur à 0!\n");
        }
      } catch (Exception e) {
        System.out.print("\n\t\t            Saisie non numérique, réessayer!\n");
      }
      sc.reset();
    } while (nombreParcours<= 0);

    for (int i = 0; i < nombreParcours; i++) {
      if (nombreParcours== 1) {
        System.out.println("\n\t\t------------ ENREGISTREMENT D'UN PARCOURS ------------\n");
      } else {
        System.out.println("\n\t\t------------ ENREGISTREMENT DU PARCOURS N°" + (i + 1) + "------------\n");
      }
      sc = new Scanner(System.in);
      System.out.print("\tLibellé: ");
      String libelle = sc.nextLine();
      System.out.print("\tDiplôme: ");
      String diplome = sc.nextLine();
      Parcours par = new Parcours(codeParcours, libelle, diplome);
      if (addParcours(par)) {
        System.out.print("\tCode généré: " + par.getCodeParcours());
        System.out.print("\n\t\t              Enregistrement réussi!\n");
      } else {
        System.out.print("\n\t\t              Enregistrement échoué!\n");
      }
    }
  }

  public static void modifierParcours() throws SQLException {
    boolean saisie = false;
    boolean testSaisie = false;
    int choix = 0;
    int codeParcours = 0;
    String libelle = null;
    String diplome = null;
    String trait = "\t\t    | |                                         | |";

    if (findAll().next()) {
      do {
        sc = new Scanner(System.in);
        System.out.println("\n\t\t------------ MODIFIER INFORMATIONS PARCOURS ------------");
        System.out.print("\n\tCode du parcours: ");
        try {
          codeParcours = sc.nextInt();
          testSaisie = true;
        } catch (Exception e) {
          System.out.print("\n\t\t            Saisie non numérique, réessayer!\n");
        }
        sc.reset();
      } while (!testSaisie);
      if (findByCodeParcours(codeParcours).next()) {
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
          System.out.println("\t\t    * *    3-    Diplôme                        * *");
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
            System.out.print("\tNouvel diplôme: ");
            diplome = sc.nextLine();

            Parcours par = new Parcours(codeParcours, libelle, diplome);
            updateParcours(codeParcours, par);
            System.out.print("\n\t\t             Modification réussie! \n");
            break;

          case 2:
            System.out.println("\n\t\t------------ MODIFICATION DU LIBELLE ------------");
            sc = new Scanner(System.in);
            System.out.print("\n\tNouveau libellé: ");
            libelle = sc.nextLine();
            updateLibelle(codeParcours, libelle);
            System.out.print("\n\t\t             Modification réussie! \n");
            break;

          case 3:
            System.out.println("\n\t\t------------ MODIFICATION DU DIPLOME ------------");
            sc = new Scanner(System.in);
            System.out.print("\n\tNouveau diplôme ");
            diplome = sc.nextLine();
            updateDiplome(codeParcours, diplome);
            System.out.print("\n\t\t             Modification réussie! \n");
            break;

          default:
            System.out.println("\n\t\t\t     Choix inexistant, réessayer!\n");
            break;
        }
      } else {
        System.out.print("\n\t\t               Parcours introuvable!\n");
      }
    } else {
      System.out.print("\n\t\t              Pas de parcours enregistré !\n");
    }
  }

  public static void supprimerParcours() throws SQLException {
    boolean testSaisie = false;
    int codeParcours = 0;

    if (findAll().next()) {
      do {
        sc = new Scanner(System.in);
        System.out.println("\n\t\t------------ SUPPRESSION PARCOURS ------------");
        System.out.print("\n\tCode du parcours: ");
        try {
          codeParcours = sc.nextInt();
          testSaisie = true;
        } catch (Exception e) {
          System.out.print("\n\t\t            Saisie non numérique, réessayer!\n");
        }
        sc.reset();
      } while (!testSaisie);
      if (findByCodeParcours(codeParcours).next()) {
        System.out.print("\n\t\t          Voulez-vous vraiment continuer? [O/n] ");
        sc = new Scanner(System.in);
        String reponse = sc.nextLine();
        if (reponse.equals("O") || reponse.equals("o")) {
          ResultSet rsParc = findByCodeParcours(codeParcours);
          while (rsParc.next()) {
            ServiceSemestre.deleteSemestre(rsParc.getInt("codeSemestre"));
          }
          deleteParcours(codeParcours);
          System.out.print("\n\t\t                Suppression validée!\n");
        } else {
          System.out.print("\n\t\t                Suppression annulée!\n");
        }
      } else {
        System.out.print("\n\t\t               Parcours introuvable!\n");
      }
    } else {
      System.out.print("\n\t\t              Pas de parcours enregistré !\n");
    }
  }

}
