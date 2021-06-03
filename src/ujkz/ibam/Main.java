package ujkz.ibam;

import java.sql.SQLException;
import java.util.Scanner;

import ujkz.ibam.services.ServiceEcu;
import ujkz.ibam.services.ServiceEtudiant;
import ujkz.ibam.services.ServiceNote;
import ujkz.ibam.services.ServiceParcours;
import ujkz.ibam.services.ServiceSemestre;
import ujkz.ibam.services.ServiceUe;


public class Main {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {
        boolean sortie = false;
        String reponse = null;
        int matricule = 0;

        do {
            int choixPrincipal = menuPrincipal();
            switch (choixPrincipal) {
                case 1:
                    do {
                        int choixEtudiant = menuEtudiant();
                        switch (choixEtudiant) {
                            case 1:
                            ServiceEtudiant.afficherReleveEtudiants();
                                break;
                            case 2:
                                ServiceEtudiant.afficherReleveParEtudiant();
                                break;

                            case 3:
                                ServiceEtudiant.afficherListeEtudiants();
                                break;

                            case 4:
                                matricule = ServiceEtudiant.enregistrerEtudiant();
                                System.out.print("\n\n\t\t\tVoulez-vous ajouter une note? [O/n] ");
                                sc = new Scanner(System.in);
                                reponse = sc.nextLine();
                                if (reponse.equals("O") || reponse.equals("o")) {
                                    ServiceEtudiant.remplirTout(matricule);
                                }
                                break;

                            case 5:
                                ServiceEtudiant.modifierEtudiant();
                                break;

                            case 6:
                                ServiceEtudiant.supprimerEtudiant();
                                break;

                            case 7:
                                ServiceEtudiant.rechercherEtudiant();
                                break;

                            case 0:
                                sortie = true;
                                break;

                            case -1:
                                System.exit(0);
                                break;

                            default:
                                System.out.println("\n\t\t\t     Choix inexistant, réessayer!\n");
                                break;
                        }
                    } while (!sortie);
                    sortie = false;
                    break;

                case 2:
                    do {
                        int choixNote = menuNote();
                        switch (choixNote) {
                            case 1:
                                matricule = ServiceNote.enregistrerNote();
                                if (!ServiceEcu.findAll().next()) {
                                    ServiceEtudiant.remplirTout(matricule);
                                }
                                break;

                            case 2:
                            ServiceNote.modifierNote();
                                break;

                            case 3:
                                ServiceNote.supprimerNote();
                                break;

                            case 0:
                                sortie = true;
                                break;

                            case -1:
                                System.exit(0);
                                break;

                            default:
                                System.out.println("\n\t\t\t     Choix inexistant, réessayer!\n");
                                break;
                        }
                    } while (!sortie);
                    sortie = false;
                    break;

                case 3:
                    do {
                        int choixEcu = menuEcu();
                        switch (choixEcu) {
                            case 1:
                                ServiceEcu.afficherListeEcu();
                                break;
                            case 2:
                                if (!ServiceUe.findAll().next()) {
                                    ServiceParcours.enregistrerParcours();
                                    ServiceSemestre.enregistrerSemestre();
                                    ServiceUe.enregistrerUe();
                                    ServiceEcu.enregistrerEcu();
                                } else {
                                    ServiceEcu.enregistrerEcu();
                                }
                                break;

                            case 3:
                                ServiceEcu.modifierEcu();
                                break;

                            case 4:
                                ServiceEcu.supprimerEcu();
                                break;

                            case 0:
                                sortie = true;
                                break;

                            case -1:
                                System.exit(0);
                                break;

                            default:
                                System.out.println("\n\t\t\t     Choix inexistant, réessayer!\n");
                                break;
                        }
                    } while (!sortie);
                    sortie = false;
                    break;

                case 4:
                    do {
                        int choixUE = menuUe();
                        switch (choixUE) {
                            case 1:
                                ServiceUe.afficherListeUe();
                                break;

                            case 2:
                                if (!ServiceSemestre.findAll().next()) {
                                    ServiceParcours.enregistrerParcours();
                                    ServiceSemestre.enregistrerSemestre();
                                    ServiceUe.enregistrerUe();
                                } else {
                                ServiceUe.enregistrerUe();
                                }
                                break;

                            case 3:
                                ServiceUe.modifierUe();
                                break;

                            case 4:
                                ServiceUe.supprimerUe();
                                break;

                            case 0:
                                sortie = true;
                                break;

                            case -1:
                                System.exit(0);
                                break;

                            default:
                                System.out.println("\n\t\t\t     Choix inexistant, réessayer!\n");
                                break;
                        }
                    } while (!sortie);
                    sortie = false;
                    break;

                case 5:
                    do {
                        int choixSemestre = menuSemestre();
                        switch (choixSemestre) {
                            case 1:
                                ServiceSemestre.afficherListeSemestre();
                                break;

                            case 2:
                                if (!ServiceParcours.findAll().next()) {
                                    ServiceParcours.enregistrerParcours();
                                    ServiceUe.enregistrerUe();
                                    ServiceSemestre.enregistrerSemestre();
                                } else{
                                    ServiceSemestre.enregistrerSemestre();
                                }
                                break;
                            case 3:
                                ServiceSemestre.modifierSemestre();
                                break;

                            case 4:
                                ServiceSemestre.supprimerSemestre();
                                break;

                            case 0:
                                sortie = true;
                                break;

                            case -1:
                                System.exit(0);
                                break;

                            default:
                                System.out.println("\n\t\t\t     Choix inexistant, réessayer!\n");
                                break;
                        }
                    } while (!sortie);
                    sortie = false;
                    break;

                case 6:
                    do {
                        int choixParcours = menuParcours();
                        switch (choixParcours) {
                            case 1:
                                ServiceParcours.afficherListeParcours();
                                break;

                            case 2:
                                ServiceParcours.enregistrerParcours();
                                break;

                            case 3:
                                ServiceParcours.modifierParcours();
                                break;

                            case 4:
                                ServiceParcours.supprimerParcours();
                                break;

                            case 0:
                                sortie = true;
                                break;

                            case -1:
                                System.exit(0);
                                break;
                            default:
                                System.out.println("\n\t\t\t     Choix inexistant, réessayer!\n");
                                break;
                        }
                    } while (!sortie);
                    sortie = false;
                    break;

                case 0:
                    sortie = true;
                    break;

                default:
                    System.out.println("\n\t\t\t     Choix inexistant, réessayer!\n");
                    break;
            }
        } while (!sortie);

    }

    public static int menuPrincipal() {
        boolean saisie = false;
        int choix = 0;
        String trait = "\t\t    | |                                         | |";
        do {
            sc = new Scanner(System.in);
            System.out.println("\n\t\t    .+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+++.");
            System.out.println("\t\t    | .*****************************************. |");
            System.out.println(trait);
            System.out.println("\t\t    | |     BIENVENUE À GESTIONRELEVÉ V2.0      | |");
            System.out.println(trait);
            System.out.println("\t\t    * *    1-    ETUDIANTS                      * *");
            System.out.println(trait);
            System.out.println("\t\t    * *    2-    NOTES                          * *");
            System.out.println(trait);
            System.out.println("\t\t    * *    3-    ÉLÉMENTS CONSTITUTIFS D'UE     * *");
            System.out.println(trait);
            System.out.println("\t\t    * *    4-    UNITÉS D'ENSEIGNEMENTS         * *");
            System.out.println(trait);
            System.out.println("\t\t    * *    5-    SEMESTRES                      * *");
            System.out.println(trait);
            System.out.println("\t\t    * *    6-    PARCOURS                       * *");
            System.out.println(trait);
            System.out.println("\t\t    * *    0-    QUITTER                        * *");
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

        return choix;
    }

    public static int menuEtudiant() {
        boolean saisie = false;
        int choix = 0;
        String trait = "\t\t    | |                                         | |";
        do {
            sc = new Scanner(System.in);
            System.out.println("\n\t\t    .+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+++.");
            System.out.println("\t\t    | .*****************************************. |");
            System.out.println(trait);
            System.out.println("\t\t    | |               ETUDIANTS                 | |");
            System.out.println(trait);
            System.out.println("\t\t    * *    1-    Afficher tous les relevés      * *");
            System.out.println(trait);
            System.out.println("\t\t    * *    2-    Afficher relevé d'un étudiant  * *");
            System.out.println(trait);
            System.out.println("\t\t    * *    3-    Afficher tous les étudiants    * *");
            System.out.println(trait);
            System.out.println("\t\t    * *    4-    Enregistrer un étudiant        * *");
            System.out.println(trait);
            System.out.println("\t\t    * *    5-    Modifier un étudiant           * *");
            System.out.println(trait);
            System.out.println("\t\t    * *    6-    Supprimer un étudiant          * *");
            System.out.println(trait);
            System.out.println("\t\t    * *    7-    Rechercher un étudiant         * *");
            System.out.println(trait);
            System.out.println("\t\t    * *    0-    Revenir au menu principal      * *");
            System.out.println(trait);
            System.out.println("\t\t    * *   -1-    Quitter le programme           * *");
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

        return choix;
    }

    public static int menuNote() {
        boolean saisie = false;
        int choix = 0;
        String trait = "\t\t    | |                                         | |";
        do {
            sc = new Scanner(System.in);
            System.out.println("\n\t\t    .+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+++.");
            System.out.println("\t\t    | .*****************************************. |");
            System.out.println(trait);
            System.out.println("\t\t    | |                NOTES                    | |");
            System.out.println(trait);
            System.out.println("\t\t    * *    1-    Ajouter une note               * *");
            System.out.println(trait);
            System.out.println("\t\t    * *    2-    Modifier une note              * *");
            System.out.println(trait);
            System.out.println("\t\t    * *    3-    Supprimer une note             * *");
            System.out.println(trait);
            System.out.println("\t\t    * *    0-    Revenir au menu principal      * *");
            System.out.println(trait);
            System.out.println("\t\t    * *   -1-    Quitter le programme           * *");
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

        return choix;
    }

    public static int menuEcu() {
        boolean saisie = false;
        int choix = 0;
        String trait = "\t\t    | |                                         | |";
        do {
            sc = new Scanner(System.in);
            System.out.println("\n\t\t    .+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+++.");
            System.out.println("\t\t    | .*****************************************. |");
            System.out.println(trait);
            System.out.println("\t\t    | |        ÉLÉMENTS CONSTITUTIFS D'UE       | |");
            System.out.println(trait);
            System.out.println("\t\t    * *    1-    Afficher les ECU               * *");
            System.out.println(trait);
            System.out.println("\t\t    * *    2-    Ajouter une ECU                * *");
            System.out.println(trait);
            System.out.println("\t\t    * *    3-    Modifier une ECU               * *");
            System.out.println(trait);
            System.out.println("\t\t    * *    4-    Supprimer une ECU              * *");
            System.out.println(trait);
            System.out.println("\t\t    * *    0-    Revenir au menu principal      * *");
            System.out.println(trait);
            System.out.println("\t\t    * *   -1-    Quitter le programme           * *");
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

        return choix;
    }

    public static int menuUe() {
        boolean saisie = false;
        int choix = 0;
        String trait = "\t\t    | |                                         | |";
        do {
            sc = new Scanner(System.in);
            System.out.println("\n\t\t    .+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+++.");
            System.out.println("\t\t    | .*****************************************. |");
            System.out.println(trait);
            System.out.println("\t\t    | |         UNITÉ D'ENSEIGNEMENTS           | |");
            System.out.println(trait);
            System.out.println("\t\t    * *    1-    Afficher les Ue                * *");
            System.out.println(trait);
            System.out.println("\t\t    * *    2-    Ajouter une Ue                 * *");
            System.out.println(trait);
            System.out.println("\t\t    * *    3-    Modifier une Ue                * *");
            System.out.println(trait);
            System.out.println("\t\t    * *    4-    Supprimer une Ue               * *");
            System.out.println(trait);
            System.out.println("\t\t    * *    0-    Revenir au menu principal      * *");
            System.out.println(trait);
            System.out.println("\t\t    * *   -1-    Quitter le programme           * *");
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

        return choix;
    }

    public static int menuSemestre() {
        boolean saisie = false;
        int choix = 0;
        String trait = "\t\t    | |                                         | |";
        do {
            sc = new Scanner(System.in);
            System.out.println("\n\t\t    .+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+++.");
            System.out.println("\t\t    | .*****************************************. |");
            System.out.println(trait);
            System.out.println("\t\t    | |                SEMESTRES                | |");
            System.out.println(trait);
            System.out.println("\t\t    * *    1-    Afficher les semestres         * *");
            System.out.println(trait);
            System.out.println("\t\t    * *    2-    Ajouter un semestre            * *");
            System.out.println(trait);
            System.out.println("\t\t    * *    3-    Modifier un semestre           * *");
            System.out.println(trait);
            System.out.println("\t\t    * *    4-    Supprimer un semestre          * *");
            System.out.println(trait);
            System.out.println("\t\t    * *    0-    Revenir au menu principal      * *");
            System.out.println(trait);
            System.out.println("\t\t    * *   -1-    Quitter le programme           * *");
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

        return choix;
    }

    public static int menuParcours() {
        boolean saisie = false;
        int choix = 0;
        String trait = "\t\t    | |                                         | |";
        do {
            sc = new Scanner(System.in);
            System.out.println("\n\t\t    .+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+++.");
            System.out.println("\t\t    | .*****************************************. |");
            System.out.println(trait);
            System.out.println("\t\t    | |                PARCOURS                 | |");
            System.out.println(trait);
            System.out.println("\t\t    * *    1-    Afficher les parcours          * *");
            System.out.println(trait);
            System.out.println("\t\t    * *    2-    Ajouter un parcours            * *");
            System.out.println(trait);
            System.out.println("\t\t    * *    3-    Modifier un parcours           * *");
            System.out.println(trait);
            System.out.println("\t\t    * *    4-    Supprimer un parcours          * *");
            System.out.println(trait);
            System.out.println("\t\t    * *    0-    Revenir au menu principal      * *");
            System.out.println(trait);
            System.out.println("\t\t    * *   -1-    Quitter le programme           * *");
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

        return choix;
    }

}
