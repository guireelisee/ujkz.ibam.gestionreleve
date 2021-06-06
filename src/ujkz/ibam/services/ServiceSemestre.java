package ujkz.ibam.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import ujkz.ibam.models.Semestre;
import ujkz.ibam.utils.ServiceDbConnection;

public class ServiceSemestre {
  private static Scanner sc = new Scanner(System.in);

  private ServiceSemestre() {
  }

  public static ResultSet findAll() {
    ResultSet rs = null;
    String req = "SELECT * FROM Semestre";
    try {
      Connection connexion = ServiceDbConnection.getConnection();
      PreparedStatement ps = connexion.prepareStatement(req);
      rs = ps.executeQuery();
    } catch (SQLException exception) {
      exception.printStackTrace();
    }
    return rs;
  }

  public static ResultSet sumTable() {
    ResultSet rs = null;
    String req = "SELECT COUNT(*) AS sum FROM Semestre";
    try {
      Connection connexion = ServiceDbConnection.getConnection();
      PreparedStatement ps = connexion.prepareStatement(req);
      rs = ps.executeQuery();
    } catch (SQLException exception) {
      exception.printStackTrace();
    }
    return rs;
  }

  public static ResultSet parcSem() {
    ResultSet rs = null;
    String req = "SELECT * FROM Parc_Sem";
    try {
      Connection connexion = ServiceDbConnection.getConnection();
      PreparedStatement ps = connexion.prepareStatement(req);
      rs = ps.executeQuery();
    } catch (SQLException exception) {
      exception.printStackTrace();
    }
    return rs;
  }

  public static ResultSet findByCodeSemestre(int codeSemestre) {
    ResultSet rs = null;
    String req = "SELECT * FROM Semestre WHERE codeSemestre = ?";
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

  public static ResultSet findParcoursByCodeSemestre(int codeSemestre) {
    ResultSet rs = null;
    String req = "SELECT * FROM Parc_Sem WHERE codeSemestre = ?";
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

  public static ResultSet findSemestreByCodeParcours(int codeParcours) {
    ResultSet rs = null;
    String req = "SELECT * FROM Parc_Sem WHERE codeParcours = ?";
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

  public static boolean addSemestre(Semestre sem) {
    boolean resultat = false;
    long timeInMilliSeconds = 0;
    java.sql.Date date = null;
    String req = "INSERT INTO Semestre (codeSemestre, libelleSemestre, dateDebut, dateFin) VALUES (?, ?, ?, ?)";
    try {
      Connection connexion = ServiceDbConnection.getConnection();
      PreparedStatement ps = connexion.prepareStatement(req, PreparedStatement.RETURN_GENERATED_KEYS);
      ps.setInt(1, 0);
      ps.setString(2, sem.getLibelleSemestre());
      timeInMilliSeconds = sem.getDateDebut().getTime();
      date = new java.sql.Date(timeInMilliSeconds);
      ps.setDate(3, date);
      timeInMilliSeconds = sem.getDateFin().getTime();
      date = new java.sql.Date(timeInMilliSeconds);
      ps.setDate(4, date);
      ps.executeUpdate();
      ResultSet cle = ps.getGeneratedKeys();
      if (cle.next()) {
        sem.setCodeSemestre(cle.getInt(1));
        resultat = true;
        ps.close();
      }
    } catch (SQLException exception) {
      exception.printStackTrace();
    }
    return resultat;
  }

  public static boolean addParSem(int codeParcours, int codeSemestre) {
    boolean resultat = false;
    String req = "INSERT INTO Parc_Sem (codeParcours, codeSemestre) VALUES (?, ?)";
    try {
      Connection connexion = ServiceDbConnection.getConnection();
      PreparedStatement ps = connexion.prepareStatement(req, PreparedStatement.RETURN_GENERATED_KEYS);
      ps.setInt(1, codeParcours);
      ps.setInt(2, codeSemestre);
      ps.executeUpdate();
      ResultSet cle = ps.getGeneratedKeys();
      if (cle.next()) {
        resultat = true;
        ps.close();
      }
    } catch (SQLException exception) {
      exception.printStackTrace();
    }
    return resultat;
  }

  public static boolean updateSemestre(int codeSemestre, Semestre sem) {
    boolean resultat = false;
    long timeInMilliSeconds = 0;
    java.sql.Date date = null;
    String req = "UPDATE Semestre SET libelleSemestre = ?, dateDebut = ?, dateFin = ?  WHERE codeSemestre = ?;";

    try {
      Connection connexion = ServiceDbConnection.getConnection();
      PreparedStatement ps = connexion.prepareStatement(req, PreparedStatement.RETURN_GENERATED_KEYS);
      ps.setString(1, sem.getLibelleSemestre());
      timeInMilliSeconds = sem.getDateDebut().getTime();
      date = new java.sql.Date(timeInMilliSeconds);
      ps.setDate(2, date);
      timeInMilliSeconds = sem.getDateFin().getTime();
      date = new java.sql.Date(timeInMilliSeconds);
      ps.setDate(3, date);
      ps.setInt(4, codeSemestre);

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

  public static boolean updateLibelle(int codeSemestre, String libelleSemestre) {
    boolean resultat = false;
    String req = "UPDATE Semestre SET libelleSemestre = ? WHERE codeSemestre = ?";
    try {
      Connection connexion = ServiceDbConnection.getConnection();
      PreparedStatement ps = connexion.prepareStatement(req, PreparedStatement.RETURN_GENERATED_KEYS);
      ps.setString(1, libelleSemestre);
      ps.setInt(2, codeSemestre);

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

  public static boolean updateDateDebut(int codeSemestre, Date dateDebut) {
    boolean resultat = false;
    long timeInMilliSeconds = 0;
    java.sql.Date date = null;
    String req = "UPDATE Semestre SET dateDebut = ? WHERE codeSemestre = ?";
    try {
      Connection connexion = ServiceDbConnection.getConnection();
      PreparedStatement ps = connexion.prepareStatement(req, PreparedStatement.RETURN_GENERATED_KEYS);
      timeInMilliSeconds = dateDebut.getTime();
      date = new java.sql.Date(timeInMilliSeconds);
      ps.setDate(1, date);
      ps.setInt(2, codeSemestre);

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

  public static boolean updateDateFin(int codeSemestre, Date dateFin) {
    boolean resultat = false;
    long timeInMilliSeconds = 0;
    java.sql.Date date = null;
    String req = "UPDATE Semestre SET dateFin = ? WHERE codeSemestre = ?";
    try {
      Connection connexion = ServiceDbConnection.getConnection();
      PreparedStatement ps = connexion.prepareStatement(req, PreparedStatement.RETURN_GENERATED_KEYS);
      timeInMilliSeconds = dateFin.getTime();
      date = new java.sql.Date(timeInMilliSeconds);
      ps.setDate(1, date);
      ps.setInt(2, codeSemestre);

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

  public static boolean deleteSemestre(int codeSemestre) {
    boolean resultat = false;
    String req = "DELETE FROM Semestre WHERE codeSemestre = ?";
    try {
      Connection connexion = ServiceDbConnection.getConnection();
      PreparedStatement ps = connexion.prepareStatement(req, PreparedStatement.RETURN_GENERATED_KEYS);
      ps.setInt(1, codeSemestre);

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

  public static boolean deleteFromParcSemSemestre(int codeSemestre) {
    boolean resultat = false;
    String req = "DELETE FROM Parc_Sem WHERE codeSemestre = ?";
    try {
      Connection connexion = ServiceDbConnection.getConnection();
      PreparedStatement ps = connexion.prepareStatement(req, PreparedStatement.RETURN_GENERATED_KEYS);
      ps.setInt(1, codeSemestre);

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

  public static void afficherListeSemestre() throws SQLException {
    String trait = "\t\t    | |                                         | |";
    Calendar cal = Calendar.getInstance();
    int jour = 0;
    int mois = 0;
    int annee = 0;

    ResultSet rsAll = findAll();
    if (rsAll.next()) {
      rsAll = findAll();
      while (rsAll.next()) {
        System.out.println("\n\t\t    .+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+++.");
        System.out.println("\t\t    | .*****************************************. |");
        System.out.println(trait);
        System.out.println("\t\t    | |             SEMESTRES DISPONIBLES       | |");
        System.out.println(trait);
        System.out.println("\t\t           Code: " + rsAll.getInt("codeSemestre"));
        System.out.println(trait);
        System.out.println("\t\t           Libellé: " + rsAll.getString("libelleSemestre"));
        System.out.println(trait);
        cal.setTime(rsAll.getDate("dateDebut"));
        jour = cal.get(Calendar.DAY_OF_MONTH);
        mois = (cal.get(Calendar.MONTH) + 1);
        annee = cal.get(Calendar.YEAR);
        System.out.println("\t\t           Date de debut: " + jour + "/" + mois + "/" + annee);
        System.out.println(trait);
        cal.setTime(rsAll.getDate("dateFin"));
        jour = cal.get(Calendar.DAY_OF_MONTH);
        mois = (cal.get(Calendar.MONTH) + 1);
        annee = cal.get(Calendar.YEAR);
        System.out.println("\t\t           Date de fin: " + jour + "/" + mois + "/" + annee);
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
      System.out.print("\n\t\t              Pas de semestre enregistré !\n");
    }
  }

  public static void enregistrerSemestre() throws SQLException {
    boolean testSaisie = false;
    int nombreSemestre = 0;
    int codeSemestre = 0;
    int codeParcours = 0;
    int count = 0;
    ResultSet rsAll = null;
    ResultSet rsAllParc = null;
    ResultSet rsPS = null;
    boolean semFound = false;

    if (ServiceParcours.findAll().next()) {
      if (findAll().next()) {
        sc = new Scanner(System.in);
        System.out.print("\n\t\t  Ajouter un semestre existant dans un parcours [O/n]: ");
        String reponse = sc.nextLine();
        if (reponse.equals("O") || reponse.equals("o")) {
          ResultSet rsAllSem = findAll();
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
          if (findByCodeSemestre(codeSemestre).next()) {
            count = 0;
            rsAllParc = ServiceParcours.findAll();
            while (rsAllParc.next()) {
              if (count == 0) {
                System.out.println("\n\t\t    Parcours disponible(s):");
                count++;
              }
              System.out.println("\n\t\t    * PARCOURS : " + rsAllParc.getString("libelleParcours") + "   CODE: "
                  + rsAllParc.getInt("codeParcours"));
            }
            do {
              sc = new Scanner(System.in);
              System.out.print("\n\tCode du parcours: ");
              try {
                codeParcours = sc.nextInt();
                testSaisie = true;
              } catch (Exception e) {
                System.out.print("\n\t\t            Saisie non numérique, réessayer!\n");
              }
              sc.reset();
            } while (!testSaisie);
            testSaisie = false;
            rsAll = findAll();
            rsPS = parcSem();
            semFound = false;
            if (ServiceParcours.findByCodeParcours(codeParcours).next()) {
              while (rsAll.next()) {
                while (rsPS.next()) {
                  if (rsPS.getInt("codeParcours") == codeParcours && rsPS.getInt("codeSemestre") == codeSemestre) {
                    System.out.print("\n\t\t      Semestre déjà existant dans le parcours!\n");
                    semFound = true;
                    break;
                  }
                }
                if (!semFound) {
                  addParSem(codeParcours, codeSemestre);
                  System.out.print("\n\t\t      Ajout du semestre dans le parcours réussie!\n");
                  break;
                }
              }
            } else {
              System.out.print("\n\t\t            Parcours inexistant, réessayer!\n");
            }
          } else {
            System.out.print("\n\t\t            Semestre inexistant, réessayer!\n");
          }
        } else {
          rsAllParc = ServiceParcours.findAll();
          while (rsAllParc.next()) {
            if (count == 0) {
              System.out.println("\n\t\t    Parcours(s) disponible(s):");
              count++;
            }
            System.out.println("\n\t\t    * PARCOURS : " + rsAllParc.getString("libelleParcours") + "   CODE: "
                + rsAllParc.getInt("codeParcours"));
          }
          do {
            sc = new Scanner(System.in);
            System.out.print("\n\tCode du parcours: ");
            try {
              codeParcours = sc.nextInt();
              testSaisie = true;
            } catch (Exception e) {
              System.out.print("\n\t\t            Saisie non numérique, réessayer!\n");
            }
            sc.reset();
          } while (!testSaisie);
          testSaisie = false;
          do {
            sc = new Scanner(System.in);
            System.out.println("\n\t\t------------ ENREGISTREMENT SEMESTRE ------------\n");
            System.out.print("\tCombien de semestre voulez-vous enregistrer ? : ");
            try {
              nombreSemestre = sc.nextInt();
            } catch (Exception e) {
              System.out.print("\n\t\t            Saisie non numérique, réessayer!\n");
            }
            sc.reset();
          } while (nombreSemestre <= 0);
          for (int i = 0; i < nombreSemestre; i++) {
            if (nombreSemestre == 1) {
              System.out.println("\n\t\t------------ ENREGISTREMENT D'UN SEMESTRE ------------\n");
            } else {
              System.out.println("\n\t\t------------ ENREGISTREMENT DU SEMESTRE N°" + (i + 1) + "------------\n");
            }
            sc = new Scanner(System.in);
            System.out.print("\tLibellé: ");
            String libelle = sc.nextLine();
            rsAll = findAll();
            rsPS = parcSem();
            semFound = false;
            while (rsAll.next()) {
              if (rsAll.getString("libelleSemestre").compareToIgnoreCase(libelle) == 0) {
                codeSemestre = rsAll.getInt("codeSemestre");
                while (rsPS.next()) {
                  if (rsPS.getInt("codeParcours") == codeParcours && rsPS.getInt("codeSemestre") == codeSemestre) {
                    semFound = true;
                    break;
                  }
                }
              }
            }
            if (!semFound) {
              SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
              Date dateDebut = null;
              Date dateFin = null;
              String dateDebutString;
              String dateFinString;
              java.sql.Date dateDebutSQL = null;
              java.sql.Date dateFinSQL = null;
              do {
                sc = new Scanner(System.in);
                System.out.print("\tDate de debut(dd/MM/yyyy): ");
                dateDebutString = sc.nextLine();
                try {
                  dateDebut = dateFormat.parse(dateDebutString);
                  SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                  String formattedDate = simpleDateFormat.format(dateDebut);
                  dateDebutSQL = java.sql.Date.valueOf(formattedDate);
                  testSaisie = true;
                } catch (Exception e) {
                  System.out.print("\n\t\t            Format de la date incorrecte!\n");
                }
                sc.reset();
              } while (!testSaisie);
              testSaisie = false;
              do {
                sc = new Scanner(System.in);
                System.out.print("\tDate de fin(dd/MM/yyyy): ");
                dateFinString = sc.nextLine();
                try {
                  dateFin = dateFormat.parse(dateFinString);
                  SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                  String formattedDate = simpleDateFormat.format(dateFin);
                  dateFinSQL = java.sql.Date.valueOf(formattedDate);
                  testSaisie = true;
                  if (dateFin.compareTo(dateDebut) < 0) {
                    System.out.print("\n\t\t            La date de fin doit être supérieure à la date de debut!\n\n");
                    testSaisie = false;
                  }
                } catch (Exception e) {
                  System.out.print("\n\t\t            Format de la date incorrecte!\n\n");
                }
                sc.reset();
              } while (!testSaisie);

              Semestre sem = new Semestre(codeSemestre, libelle, dateDebutSQL, dateFinSQL);
              if (addSemestre(sem)) {
                addParSem(codeParcours, sem.getCodeSemestre());
                System.out.print("\tCode généré: " + sem.getCodeSemestre());
                System.out.print("\n\t\t              Enregistrement réussi!\n");
              } else {
                System.out.print("\n\t\t              Enregistrement échoué!\n");
              }
            } else {
              System.out.print("\n\t\t      Semestre déjà existant dans le parcours!\n");
            }
          }
        }
      } else {
        System.out.print("\n\t\t              Pas de semestre enregistré !\n");
      }
    } else{
      rsAllParc = ServiceParcours.findAll();
      while (rsAllParc.next()) {
        if (count == 0) {
          System.out.println("\n\t\t    Parcours(s) disponible(s):");
          count++;
        }
        System.out.println("\n\t\t    * PARCOURS : " + rsAllParc.getString("libelleParcours") + "   CODE: "
            + rsAllParc.getInt("codeParcours"));
      }
      do {
        sc = new Scanner(System.in);
        System.out.print("\n\tCode du parcours: ");
        try {
          codeParcours = sc.nextInt();
          testSaisie = true;
        } catch (Exception e) {
          System.out.print("\n\t\t            Saisie non numérique, réessayer!\n");
        }
        sc.reset();
      } while (!testSaisie);
      testSaisie = false;
      do {
        sc = new Scanner(System.in);
        System.out.println("\n\t\t------------ ENREGISTREMENT SEMESTRE ------------\n");
        System.out.print("\tCombien de semestre voulez-vous enregistrer ? : ");
        try {
          nombreSemestre = sc.nextInt();
        } catch (Exception e) {
          System.out.print("\n\t\t            Saisie non numérique, réessayer!\n");
        }
        sc.reset();
      } while (nombreSemestre <= 0);
      for (int i = 0; i < nombreSemestre; i++) {
        if (nombreSemestre == 1) {
          System.out.println("\n\t\t------------ ENREGISTREMENT D'UN SEMESTRE ------------\n");
        } else {
          System.out.println("\n\t\t------------ ENREGISTREMENT DU SEMESTRE N°" + (i + 1) + "------------\n");
        }
        sc = new Scanner(System.in);
        System.out.print("\tLibellé: ");
        String libelle = sc.nextLine();
        rsAll = findAll();
        rsPS = parcSem();
        semFound = false;
        while (rsAll.next()) {
          if (rsAll.getString("libelleSemestre").compareToIgnoreCase(libelle) == 0) {
            codeSemestre = rsAll.getInt("codeSemestre");
            while (rsPS.next()) {
              if (rsPS.getInt("codeParcours") == codeParcours && rsPS.getInt("codeSemestre") == codeSemestre) {
                semFound = true;
                break;
              }
            }
          }
        }
        if (!semFound) {
          SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
          Date dateDebut = null;
          Date dateFin = null;
          String dateDebutString;
          String dateFinString;
          java.sql.Date dateDebutSQL = null;
          java.sql.Date dateFinSQL = null;
          do {
            sc = new Scanner(System.in);
            System.out.print("\tDate de debut(dd/MM/yyyy): ");
            dateDebutString = sc.nextLine();
            try {
              dateDebut = dateFormat.parse(dateDebutString);
              SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
              String formattedDate = simpleDateFormat.format(dateDebut);
              dateDebutSQL = java.sql.Date.valueOf(formattedDate);
              testSaisie = true;
            } catch (Exception e) {
              System.out.print("\n\t\t            Format de la date incorrecte!\n");
            }
            sc.reset();
          } while (!testSaisie);
          testSaisie = false;
          do {
            sc = new Scanner(System.in);
            System.out.print("\tDate de fin(dd/MM/yyyy): ");
            dateFinString = sc.nextLine();
            try {
              dateFin = dateFormat.parse(dateFinString);
              SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
              String formattedDate = simpleDateFormat.format(dateFin);
              dateFinSQL = java.sql.Date.valueOf(formattedDate);
              testSaisie = true;
              if (dateFin.compareTo(dateDebut) < 0) {
                System.out.print("\n\t\t            La date de fin doit être supérieure à la date de debut!\n\n");
                testSaisie = false;
              }
            } catch (Exception e) {
              System.out.print("\n\t\t            Format de la date incorrecte!\n\n");
            }
            sc.reset();
          } while (!testSaisie);

          Semestre sem = new Semestre(codeSemestre, libelle, dateDebutSQL, dateFinSQL);
          if (addSemestre(sem)) {
            addParSem(codeParcours, sem.getCodeSemestre());
            System.out.print("\tCode généré: " + sem.getCodeSemestre());
            System.out.print("\n\t\t              Enregistrement réussi!\n");
          } else {
            System.out.print("\n\t\t              Enregistrement échoué!\n");
          }
        } else {
          System.out.print("\n\t\t      Semestre déjà existant dans le parcours!\n");
        }
      }
    }
  }

  public static void modifierSemestre() throws SQLException {
    boolean testSaisie = false;
    int choix = 0;
    int codeSemestre = 0;
    String libelle = null;
    Date dateDebut = null;
    Date dateFin = null;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    String dateDebutString;
    String dateFinString;
    java.sql.Date dateDebutSQL = null;
    java.sql.Date dateFinSQL = null;
    String trait = "\t\t    | |                                         | |";

    if (findAll().next()) {
      do {
        sc = new Scanner(System.in);
        System.out.println("\n\t\t------------ MODIFIER INFORMATIONS SEMESTRE ------------");
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
      if (findByCodeSemestre(codeSemestre).next()) {
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
          System.out.println("\t\t    * *    3-    Date de debut                  * *");
          System.out.println(trait);
          System.out.println("\t\t    * *    4-    Date de fin                    * *");
          System.out.println(trait);
          System.out.println(trait);
          System.out.println("\t\t    | '*****************************************' |");
          System.out.println("\t\t    '-+-+-+-+-+-+-+-+-+-++-+-+-+-+-+-+-+-+-+-+-+-+'");
          System.out.print("\n\t\t                 Faites votre choix: ");
          try {
            choix = sc.nextInt();
            testSaisie = true;
          } catch (Exception e) {
            System.out.print("\n\t\t            Saisie non numérique, réessayer!\n");
          }
          sc.reset();
        } while (!testSaisie);
        testSaisie = false;
        switch (choix) {
          case 1:
          System.out.println("\n\t\t------------ MODIFICATION DE TOUTES LES INFORMATIONS ------------");
          sc = new Scanner(System.in);
          System.out.print("\n\tNouveau libellé: ");
          libelle = sc.nextLine();
          do {
            sc = new Scanner(System.in);
            System.out.print("\tNouvelle date de debut(dd/MM/yyyy): ");
            dateDebutString = sc.nextLine();
            try {
              dateDebut = dateFormat.parse(dateDebutString);
              SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
              String formattedDate = simpleDateFormat.format(dateDebut);
              dateDebutSQL = java.sql.Date.valueOf(formattedDate);
              testSaisie = true;
            } catch (Exception e) {
              System.out.print("\n\t\t            Format de la date incorrecte!\n");
            }
            sc.reset();
          } while (!testSaisie);
          testSaisie = false;
          do {
            sc = new Scanner(System.in);
            System.out.print("\tNouvelle date de fin(dd/MM/yyyy): ");
            dateFinString = sc.nextLine();
            try {
              dateFin = dateFormat.parse(dateFinString);
              SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
              String formattedDate = simpleDateFormat.format(dateFin);
              dateFinSQL = java.sql.Date.valueOf(formattedDate);
              testSaisie = true;
              if (dateFin.compareTo(dateDebut) < 0) {
                System.out.print("\n\t\t            La date de fin doit être supérieure à la date de debut!\n\n");
                testSaisie = false;
              }
            } catch (Exception e) {
              System.out.print("\n\t\t            Format de la date incorrecte!\n\n");
            }
            sc.reset();
          } while (!testSaisie);

          Semestre sem = new Semestre(codeSemestre, libelle, dateDebutSQL, dateFinSQL);
          updateSemestre(codeSemestre, sem);
          System.out.print("\n\t\t             Modification réussie! \n");
          break;

          case 2:
          System.out.println("\n\t\t------------ MODIFICATION DU LIBELLE ------------");
          sc = new Scanner(System.in);
          System.out.print("\n\tNouveau libellé: ");
          libelle = sc.nextLine();
          updateLibelle(codeSemestre, libelle);
          System.out.print("\n\t\t             Modification réussie! \n");
          break;

          case 3:
          System.out.println("\n\t\t------------ MODIFICATION DE LA DATE DE DEBUT ------------");
          do {
            sc = new Scanner(System.in);
            System.out.print("\tNouvelle date de debut(dd/MM/yyyy): ");
            dateDebutString = sc.nextLine();
            try {
              dateDebut = dateFormat.parse(dateDebutString);
              SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
              String formattedDate = simpleDateFormat.format(dateDebut);
              dateDebutSQL = java.sql.Date.valueOf(formattedDate);
              testSaisie = true;
            } catch (Exception e) {
              System.out.print("\n\t\t            Format de la date incorrecte!\n");
            }
            sc.reset();
          } while (!testSaisie);
          updateDateDebut(codeSemestre, dateDebutSQL);
          System.out.print("\n\t\t             Modification réussie! \n");
          break;

          case 4:
          System.out.println("\n\t\t------------ MODIFICATION DE LA DATE DE FIN ------------");
          do {
            sc = new Scanner(System.in);
            System.out.print("\tNouvelle date de fin(dd/MM/yyyy): ");
            dateFinString = sc.nextLine();
            try {
              dateFin = dateFormat.parse(dateFinString);
              SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
              String formattedDate = simpleDateFormat.format(dateFin);
              dateFinSQL = java.sql.Date.valueOf(formattedDate);
              testSaisie = true;
            } catch (Exception e) {
              System.out.print("\n\t\t            Format de la date incorrecte!\n\n");
            }
            sc.reset();
          } while (!testSaisie);

          updateDateFin(codeSemestre, dateFinSQL);
          System.out.print("\n\t\t             Modification réussie! \n");
          break;

          default:
          System.out.println("\n\t\t\t     Choix inexistant, réessayer!\n");
          break;
        }
      } else {
        System.out.print("\n\t\t               Semestre introuvable!\n");
      }
    } else {
      System.out.print("\n\t\t              Pas de semestre enregistré !\n");
    }
  }

  public static void supprimerSemestre() throws SQLException {
    boolean testSaisie = false;
    int codeSemestre = 0;

    if (findAll().next()) {
      do {
        sc = new Scanner(System.in);
        System.out.println("\n\t\t------------ SUPPRESSION SEMESTRE ------------");
        System.out.print("\n\tCode du parcours: ");
        try {
          codeSemestre = sc.nextInt();
          testSaisie = true;
        } catch (Exception e) {
          System.out.print("\n\t\t            Saisie non numérique, réessayer!\n");
        }
        sc.reset();
      } while (!testSaisie);
      if (findByCodeSemestre(codeSemestre).next()) {
        System.out.print("\n\t\t          Voulez-vous vraiment continuer? [O/n] ");
        sc = new Scanner(System.in);
        String reponse = sc.nextLine();
        if (reponse.equals("O") || reponse.equals("o")) {
          deleteSemestre(codeSemestre);
          deleteFromParcSemSemestre(codeSemestre);
          System.out.print("\n\t\t                Suppression validée!\n");
        } else {
          System.out.print("\n\t\t                Suppression annulée!\n");
        }
      } else {
        System.out.print("\n\t\t               Semestre introuvable!\n");
      }
    } else {
      System.out.print("\n\t\t              Pas de semestre enregistré !\n");
    }
  }

}
