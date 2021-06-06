package ujkz.ibam.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import ujkz.ibam.models.Note;
import ujkz.ibam.utils.ServiceDbConnection;

public class ServiceNote {
  private static Scanner sc = new Scanner(System.in);

  private ServiceNote() {
  }

  public static ResultSet sumTable() {
    ResultSet rs = null;
    String req = "SELECT COUNT(*) AS sum FROM Note";
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
    String req = "SELECT * FROM Note";
    try {
      Connection connexion = ServiceDbConnection.getConnection();
      PreparedStatement ps = connexion.prepareStatement(req);
      rs = ps.executeQuery();
    } catch (SQLException exception) {
      exception.printStackTrace();
    }
    return rs;
  }

  public static ResultSet findByMatriculeAndCodeEcu(int matricule, int codeEcu) {
    ResultSet rs = null;
    String req = "SELECT * FROM Note WHERE matricule = ? AND codeEcu = ?";
    try {
      Connection connexion = ServiceDbConnection.getConnection();
      PreparedStatement ps = connexion.prepareStatement(req);
      ps.setInt(1, matricule);
      ps.setInt(2, codeEcu);
      rs = ps.executeQuery();
    } catch (SQLException exception) {
      exception.printStackTrace();
    }
    return rs;
  }

  public static ResultSet findByMatricule(int matricule) {
    ResultSet rs = null;
    String req = "SELECT * FROM Note WHERE matricule = ? ORDER BY codeEcu ASC";
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

  public static boolean addNote(int matricule, int codeEcu, Note n) {
    boolean resultat = false;
    long timeInMilliSeconds = 0;
    java.sql.Date date = null;
    String req = "INSERT INTO Note (codeNote, matricule, codeEcu, valeur, dateDevoir, session) VALUES (?, ?, ?, ?, ?, ?)";
    try {
      Connection connexion = ServiceDbConnection.getConnection();
      PreparedStatement ps = connexion.prepareStatement(req, PreparedStatement.RETURN_GENERATED_KEYS);
      ps.setInt(1, 0);
      ps.setInt(2, matricule);
      ps.setInt(3, codeEcu);
      ps.setDouble(4, n.getValeur());
      timeInMilliSeconds = n.getDateDevoir().getTime();
      date = new java.sql.Date(timeInMilliSeconds);
      ps.setDate(5, date);
      ps.setString(6, n.getSession());

      ps.executeUpdate();
      ResultSet cle = ps.getGeneratedKeys();
      if (cle.next()) {
        n.setCodeNote(cle.getInt(1));
        resultat = true;
        ps.close();
      }
    } catch (SQLException exception) {
      exception.printStackTrace();
    }
    return resultat;
  }

  public static boolean updateNote(int matricule, int codeEcu, Note n) {
    boolean resultat = false;
    String req = "UPDATE Note SET valeur = ?, dateDevoir =? , session = ? WHERE matricule = ? AND codeEcu = ?;";

    try {
      Connection connexion = ServiceDbConnection.getConnection();
      PreparedStatement ps = connexion.prepareStatement(req, PreparedStatement.RETURN_GENERATED_KEYS);
      ps.setDouble(1, n.getValeur());
      ps.setDate(2, (java.sql.Date) n.getDateDevoir());
      ps.setString(3, n.getSession());
      ps.setInt(4, matricule);
      ps.setInt(5, codeEcu);

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

  public static boolean updateSession(int matricule, int codeEcu, String session) {
    boolean resultat = false;
    String req = "UPDATE Note SET session = ? WHERE matricule = ? AND codeEcu = ?";
    try {
      Connection connexion = ServiceDbConnection.getConnection();
      PreparedStatement ps = connexion.prepareStatement(req, PreparedStatement.RETURN_GENERATED_KEYS);
      ps.setString(1, session);
      ps.setInt(2, matricule);
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

  public static boolean updateValeur(int matricule, int codeEcu, Double valeur) {
    boolean resultat = false;
    String req = "UPDATE Note SET valeur = ? WHERE matricule = ? AND codeEcu = ?";
    try {
      Connection connexion = ServiceDbConnection.getConnection();
      PreparedStatement ps = connexion.prepareStatement(req, PreparedStatement.RETURN_GENERATED_KEYS);
      ps.setDouble(1, valeur);
      ps.setInt(2, matricule);
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

  public static boolean updateDate(int matricule, int codeEcu, Date valeur) {
    boolean resultat = false;
    long timeInMilliSeconds = 0;
    java.sql.Date date = null;
    String req = "UPDATE Note SET dateDevoir = ? WHERE matricule = ? AND codeEcu = ?";
    try {
      Connection connexion = ServiceDbConnection.getConnection();
      PreparedStatement ps = connexion.prepareStatement(req, PreparedStatement.RETURN_GENERATED_KEYS);
      timeInMilliSeconds = valeur.getTime();
      date = new java.sql.Date(timeInMilliSeconds);
      ps.setDate(1, date);
      ps.setInt(2, matricule);
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

  public static boolean deleteNote(int matricule, int codeEcu) {
    boolean resultat = false;
    String req = "DELETE FROM Note WHERE matricule = ? AND codeEcu = ?";
    try {
      Connection connexion = ServiceDbConnection.getConnection();
      PreparedStatement ps = connexion.prepareStatement(req, PreparedStatement.RETURN_GENERATED_KEYS);
      ps.setInt(1, matricule);
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

  public static int enregistrerNote() throws SQLException {
    boolean testSaisie = false;
    int codeEcu = 0;
    int matricule = 0;
    String session = null;
    int count = 0;

    if (ServiceEtudiant.findAll().next()) {
      ResultSet rsAllEtu = ServiceEtudiant.findAll();
      while (rsAllEtu.next()) {
        if (count == 0) {
          System.out.println("\n\t\t    Etudiant(s) enregistré(s):");
          count++;
        }
        System.out
            .println("\n\t\t    * Nom : " + rsAllEtu.getString("nom") + "   Prénom(s) :" + rsAllEtu.getString("prenom")+"   CODE: " + rsAllEtu.getInt("matricule"));
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
      testSaisie = false;
      if (ServiceEtudiant.findByMatricule(matricule).next()) {
        if (ServiceEcu.findAll().next()) {
          count = 0;
          ResultSet rsAllEcu = ServiceEcu.findAll();
          while (rsAllEcu.next()) {
            if (count == 0) {
              System.out.println("\n\t\t    ECU(s) disponible(s):");
              count++;
            }
            System.out
                .println("\n\t\t    * ECU : " + rsAllEcu.getString("libelleEcu") + "   CODE: " + rsAllEcu.getInt("codeEcu"));
          }
          do {
            sc = new Scanner(System.in);
            System.out.print("\n\tCode de l'ECU: ");
            try {
              codeEcu = sc.nextInt();
              testSaisie = true;
            } catch (Exception e) {
              System.out.print("\n\t\t            Saisie non numérique, réessayer!\n");
            }
            sc.reset();
          } while (!testSaisie);
          testSaisie = false;
          if (ServiceEcu.findByCodeEcu(codeEcu).next()) {
            System.out.println("\n\t\t------------ ENREGISTREMENT D'UNE NOTE ------------\n");
            Double valeur = 0.0;
            do {
              sc = new Scanner(System.in);
              System.out.print("\tValeur: ");
              try {
                valeur = sc.nextDouble();
                testSaisie = true;
                if (valeur < 0 || valeur > 20) {
                  System.out.print("\n\t\t            La note doit être comprise entre 0-20!\n");
                  testSaisie = false;
                }
              } catch (Exception e) {
                System.out.print("\n\t\t            Saisie non numérique, réessayer!\n");
              }
              sc.reset();
            } while (!testSaisie);
            testSaisie = false;
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date date = null;
            String dateString;
            java.sql.Date dateSQL = null;
            ResultSet rsEcu = ServiceEcu.findByCodeEcu(codeEcu);
            rsEcu.next();
            int codeUe = rsEcu.getInt("codeUe");
            ResultSet rsUe = ServiceUe.findByCodeUe(codeUe);
            rsUe.next();
            int codeSem = rsUe.getInt("codeSemestre");
            ResultSet rsSem = ServiceSemestre.findByCodeSemestre(codeSem);
            rsSem.next();
            Calendar cal = Calendar.getInstance();
            int jourD = 0;
            int moisD = 0;
            int anneeD = 0;

            int jourF = 0;
            int moisF = 0;
            int anneeF = 0;
            cal.setTime(rsSem.getDate("dateDebut"));
            jourD = cal.get(Calendar.DAY_OF_MONTH);
            moisD = (cal.get(Calendar.MONTH) + 1);
            anneeD = cal.get(Calendar.YEAR);

            cal.setTime(rsSem.getDate("dateFin"));
            jourF = cal.get(Calendar.DAY_OF_MONTH);
            moisF = (cal.get(Calendar.MONTH) + 1);
            anneeF = cal.get(Calendar.YEAR);
            do {
              sc = new Scanner(System.in);
              System.out.print("\tDate du devoir(dd/MM/yyyy): ");
              dateString = sc.nextLine();
              try {
                date = dateFormat.parse(dateString);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String formattedDate = simpleDateFormat.format(date);
                dateSQL = java.sql.Date.valueOf(formattedDate);
                testSaisie = true;
                // Erreur si l'heure saisie n'est pas dans l'intervalle de la période du semestre
                if (dateSQL.before(rsSem.getDate("dateDebut")) || dateSQL.after(rsSem.getDate("dateFin"))) {
                  System.out.println("\n\t\t            La date du devoir doit être comprise entre le "+jourD+"/"+moisD+"/"+anneeD+" et le "+ jourF + "/" + moisF + "/" + anneeF + "!\n");
                  testSaisie = false;
                }
              } catch (Exception e) {
                System.out.println("\n\t\t            Format de la date incorrecte!\n");
              }
              sc.reset();
            } while (!testSaisie);
            sc = new Scanner(System.in);
            testSaisie = false;
            System.out.print("\tSession: ");
            session = sc.nextLine();

            Note n = new Note(valeur, dateSQL, session);
            if (addNote(matricule, codeEcu, n)) {
              System.out.print("\tCode généré: " + n.getCodeNote());
              System.out.print("\n\t\t              Enregistrement réussi!\n");
            } else {
              System.out.print("\n\t\t              Enregistrement échoué!\n");
            }
          } else {
            System.out.print("\n\t\t            ECU inexistant, réessayer!\n");
          }
        } else{
          System.out.print("\n\t\t               Aucun ECU enregistré!\n");
        }
      } else {
        System.out.print("\n\t\t               Etudiant introuvable!\n");
      }
    } else {
      System.out.print("\n\t\t              Pas d'étudiant enregistré !\n");
    }
    return matricule;
  }

  public static void modifierNote() throws SQLException {
    boolean testSaisie = false;
    int choix = 0;
    int matricule = 0;
    int codeEcu = 0;
    Double valeur = 0.0;
    Date date = null;
    String session = null;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    String dateString = null;
    java.sql.Date dateSQL = null;
    String trait = "\t\t    | |                                         | |";
    int count = 0;
    ResultSet rsEcu = null;
    int codeUe = 0;
    ResultSet rsUe = null;
    int codeSem = 0;
    ResultSet rsSem = null;
    Calendar cal = null;
    int jourD = 0;
    int moisD = 0;
    int anneeD = 0;

    int jourF = 0;
    int moisF = 0;
    int anneeF = 0;

    if(findAll().next()) {
      if (ServiceEtudiant.findAll().next()) {
        ResultSet rsAllEtu = ServiceEtudiant.findAll();
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
        testSaisie = false;
        if (ServiceEtudiant.findByMatricule(matricule).next()) {
          if(ServiceEcu.findAll().next()){
            count = 0;
            ResultSet rsAllEcu = ServiceEcu.findAll();
            while (rsAllEcu.next()) {
              if (count == 0) {
                System.out.println("\n\t\t    ECU(s) disponible(s):");
                count++;
              }
              System.out.println(
                  "\n\t\t    * ECU : " + rsAllEcu.getString("libelleEcu") + "   CODE: " + rsAllEcu.getInt("codeEcu"));
            }
            do {
              sc = new Scanner(System.in);
              System.out.print("\n\tCode de l'ECU: ");
              try {
                codeEcu = sc.nextInt();
                testSaisie = true;
              } catch (Exception e) {
                System.out.print("\n\t\t            Saisie non numérique, réessayer!\n");
              }
              sc.reset();
            } while (!testSaisie);
            testSaisie = false;
              if (ServiceEcu.findByCodeEcu(codeEcu).next()) {
                do {
                  sc = new Scanner(System.in);
                  System.out.println("\n\t\t    .+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+++.");
                  System.out.println("\t\t    | .*****************************************. |");
                  System.out.println(trait);
                  System.out.println("\t\t    | |                  MODIFIER  :            | |");
                  System.out.println(trait);
                  System.out.println("\t\t    * *    1-    TOUT                           * *");
                  System.out.println(trait);
                  System.out.println("\t\t    * *    2-    Valeur                         * *");
                  System.out.println(trait);
                  System.out.println("\t\t    * *    3-    Date du devoir                 * *");
                  System.out.println(trait);
                  System.out.println("\t\t    * *    4-    Session                        * *");
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
                switch (choix) {
                  case 1:
                    System.out.println("\n\t\t------------ MODIFICATION DE TOUTES LES INFORMATIONS ------------");
                    testSaisie = false;
                    do {
                      sc = new Scanner(System.in);
                      System.out.print("\tNouvelle valeur: ");
                      try {
                        valeur = sc.nextDouble();
                        testSaisie = true;
                        if (valeur < 0 || valeur > 20) {
                          System.out.print("\n\t\t            La note doit être comprise entre 0-20!\n");
                          testSaisie = false;
                        }
                      } catch (Exception e) {
                        System.out.print("\n\t\t            Saisie non numérique, réessayer!\n");
                      }
                      sc.reset();
                    } while (!testSaisie);
                    rsEcu = ServiceEcu.findByCodeEcu(codeEcu);
                    rsEcu.next();
                    codeUe = rsEcu.getInt("codeUe");
                    rsUe = ServiceUe.findByCodeUe(codeUe);
                    rsUe.next();
                    codeSem = rsUe.getInt("codeSemestre");
                    rsSem = ServiceSemestre.findByCodeSemestre(codeSem);
                    rsSem.next();
                    cal = Calendar.getInstance();
                    jourD = 0;
                    moisD = 0;
                    anneeD = 0;

                    jourF = 0;
                    moisF = 0;
                    anneeF = 0;
                    cal.setTime(rsSem.getDate("dateDebut"));
                    jourD = cal.get(Calendar.DAY_OF_MONTH);
                    moisD = (cal.get(Calendar.MONTH) + 1);
                    anneeD = cal.get(Calendar.YEAR);

                    cal.setTime(rsSem.getDate("dateFin"));
                    jourF = cal.get(Calendar.DAY_OF_MONTH);
                    moisF = (cal.get(Calendar.MONTH) + 1);
                    anneeF = cal.get(Calendar.YEAR);
                    do {
                      sc = new Scanner(System.in);
                      System.out.print("\tDate du devoir(dd/MM/yyyy): ");
                      dateString = sc.nextLine();
                      try {
                        date = dateFormat.parse(dateString);
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        String formattedDate = simpleDateFormat.format(date);
                        dateSQL = java.sql.Date.valueOf(formattedDate);
                        testSaisie = true;
                        // Erreur si l'heure saisie n'est pas dans l'intervalle de la période du
                        // semestre
                        if (dateSQL.before(rsSem.getDate("dateDebut")) || dateSQL.after(rsSem.getDate("dateFin"))) {
                          System.out.println("\n\t\t            La date du devoir doit être comprise entre le " + jourD
                              + "/" + moisD + "/" + anneeD + " et le " + jourF + "/" + moisF + "/" + anneeF + "!\n");
                          testSaisie = false;
                        }
                      } catch (Exception e) {
                        System.out.println("\n\t\t            Format de la date incorrecte!\n");
                      }
                      sc.reset();
                    } while (!testSaisie);
                    sc = new Scanner(System.in);
                    testSaisie = false;
                    do {
                      sc = new Scanner(System.in);
                      System.out.print("\tNouvelle session: ");
                      try {
                        session = sc.nextLine();
                        testSaisie = true;
                      } catch (Exception e) {
                        System.out.print("\n\t\t            Saisie non numérique, réessayer!\n");
                      }
                      sc.reset();
                    } while (!testSaisie);
                    Note n = new Note(valeur, dateSQL, session);
                    updateNote(matricule, codeEcu, n);
                    System.out.print("\n\t\t             Modification réussie! \n");
                    break;

                  case 2:
                    System.out.println("\n\t\t------------ MODIFICATION DE LA VALEUR ------------");
                    testSaisie = false;
                    do {
                      sc = new Scanner(System.in);
                      System.out.print("\tNouvelle valeur: ");
                      try {
                        valeur = sc.nextDouble();
                        testSaisie = true;
                        if (valeur < 0 || valeur > 20) {
                          System.out.print("\n\t\t            La note doit être comprise entre 0-20!\n");
                          testSaisie = false;
                        }
                      } catch (Exception e) {
                        System.out.print("\n\t\t            Saisie non numérique, réessayer!\n");
                      }
                      sc.reset();
                    } while (!testSaisie);
                    updateValeur(matricule, codeEcu, valeur);
                    System.out.print("\n\t\t             Modification réussie! \n");
                    break;

                  case 3:
                    System.out.println("\n\t\t------------ MODIFICATION DE LA DATE DE DEVOIR ------------");
                    rsEcu = ServiceEcu.findByCodeEcu(codeEcu);
                    rsEcu.next();
                    codeUe = rsEcu.getInt("codeUe");
                    rsUe = ServiceUe.findByCodeUe(codeUe);
                    rsUe.next();
                    codeSem = rsUe.getInt("codeSemestre");
                    rsSem = ServiceSemestre.findByCodeSemestre(codeSem);
                    rsSem.next();
                    cal = Calendar.getInstance();
                    jourD = 0;
                    moisD = 0;
                    anneeD = 0;

                    jourF = 0;
                    moisF = 0;
                    anneeF = 0;
                    cal.setTime(rsSem.getDate("dateDebut"));
                    jourD = cal.get(Calendar.DAY_OF_MONTH);
                    moisD = (cal.get(Calendar.MONTH) + 1);
                    anneeD = cal.get(Calendar.YEAR);

                    cal.setTime(rsSem.getDate("dateFin"));
                    jourF = cal.get(Calendar.DAY_OF_MONTH);
                    moisF = (cal.get(Calendar.MONTH) + 1);
                    anneeF = cal.get(Calendar.YEAR);
                    do {
                      sc = new Scanner(System.in);
                      System.out.print("\tDate du devoir(dd/MM/yyyy): ");
                      dateString = sc.nextLine();
                      try {
                        date = dateFormat.parse(dateString);
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        String formattedDate = simpleDateFormat.format(date);
                        dateSQL = java.sql.Date.valueOf(formattedDate);
                        testSaisie = true;
                        // Erreur si l'heure saisie n'est pas dans l'intervalle de la période du
                        // semestre
                        if (dateSQL.before(rsSem.getDate("dateDebut")) || dateSQL.after(rsSem.getDate("dateFin"))) {
                          System.out.println("\n\t\t            La date du devoir doit être comprise entre le " + jourD
                              + "/" + moisD + "/" + anneeD + " et le " + jourF + "/" + moisF + "/" + anneeF + "!\n");
                          testSaisie = false;
                        }
                      } catch (Exception e) {
                        System.out.println("\n\t\t            Format de la date incorrecte!\n");
                      }
                      sc.reset();
                    } while (!testSaisie);
                    updateDate(matricule, codeEcu, dateSQL);
                    System.out.print("\n\t\t             Modification réussie! \n");
                    break;

                  case 4:
                    System.out.println("\n\t\t------------ MODIFICATION DE LA SESSION ------------");
                    testSaisie = false;
                    do {
                      sc = new Scanner(System.in);
                      System.out.print("\tNouvelle session: ");
                      try {
                        session = sc.nextLine();
                        testSaisie = true;
                      } catch (Exception e) {
                        System.out.print("\n\t\t            Saisie non numérique, réessayer!\n");
                      }
                      sc.reset();
                    } while (!testSaisie);
                    updateSession(matricule, codeEcu, session);
                    System.out.print("\n\t\t             Modification réussie! \n");
                    break;

                  default:
                    System.out.println("\n\t\t\t     Choix inexistant, réessayer!\n");
                    break;
                }
              } else {
                System.out.print("\n\t\t               ECU introuvable!\n");
              }

          } else {
            System.out.print("\n\t\t               Aucun ECU enregistré!\n");
          }
        } else {
          System.out.print("\n\t\t               Etudiant introuvable!\n");
        }
      } else {
        System.out.print("\n\t\t              Pas d'étudiant enregistré !\n");
      }
    } else {
      System.out.print("\n\t\t              Pas de note enregistrée !\n");
    }
  }

  public static void supprimerNote() throws SQLException {
    boolean testSaisie = false;
    int matricule = 0;
    int codeEcu = 0;
    int count = 0;
    if (ServiceEtudiant.findAll().next()) {
      ResultSet rsAllEtu = ServiceEtudiant.findAll();
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
      testSaisie = false;
      if (ServiceEtudiant.findByMatricule(matricule).next()) {
        if (ServiceEcu.findAll().next()) {
          count = 0;
          ResultSet rsAllEcu = ServiceEcu.findAll();
          while (rsAllEcu.next()) {
            if (count == 0) {
              System.out.println("\n\t\t    ECU(s) disponible(s):");
              count++;
            }
            System.out.println(
                "\n\t\t    * ECU : " + rsAllEcu.getString("libelleEcu") + "   CODE: " + rsAllEcu.getInt("codeEcu"));
          }
          do {
            sc = new Scanner(System.in);
            System.out.print("\n\tCode de l'ECU: ");
            try {
              codeEcu = sc.nextInt();
              testSaisie = true;
            } catch (Exception e) {
              System.out.print("\n\t\t            Saisie non numérique, réessayer!\n");
            }
            sc.reset();
          } while (!testSaisie);
          testSaisie = false;
          ResultSet rsEcu = ServiceNote.findByMatriculeAndCodeEcu(matricule, codeEcu);
          if (rsEcu.next()) {
              System.out.println("\n\t\t------------ SUPPRESSION NOTE ------------");
              System.out.print("\n\t\t          Voulez-vous vraiment continuer? [O/n] ");
              sc = new Scanner(System.in);
              String reponse = sc.nextLine();
              if (reponse.equals("O") || reponse.equals("o")) {
                deleteNote(matricule, codeEcu);
              System.out.print("\n\t\t                Suppression validée!\n");
              } else {
                System.out.print("\n\t\t                Suppression annulée!\n");
              }
            } else {
              System.out.print("\n\t\t            Pas de note pour cet ECU!\n");
            }
        } else {
          System.out.print("\n\t\t               Aucun ECU enregistré!\n");
        }
      } else {
        System.out.print("\n\t\t               Etudiant introuvable!\n");
      }
    } else {
      System.out.print("\n\t\t              Pas d'étudiant enregistré !\n");
    }
  }
}
