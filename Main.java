import java.util.Scanner;

public class Main {

    // Fonction pour compter le nombre de caractères x dans le tableau t
    public static int count(char x, char[][] t) {
        int count = 0;
        for (int i = 0; i < t.length; i++) {
            for (int j = 0; j < t[i].length; j++) {
                if (t[i][j] == x) {
                    count++;
                }
            }
        }
        return count;
    }

    // Fonction pour compter le nombre de cases libres dans la grille de jeu
    public static int free_cells(char[][] t) {
        return count('-', t); // On compte le nombre de tirets '-' dans le tableau pour savoir combien de
                              // cases sont libres
    }

    // Fonction pour obtenir une colonne spécifique de la grille de jeu
    public static char[] get_column(int c, char[][] t) {
        char[] column = new char[t.length];
        for (int i = 0; i < t.length; i++) {
            column[i] = t[i][c];
        }
        return column;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Initialisation de la grille de jeu et du joueur courant
        char[][] grille = new char[3][3];
        for (int i = 0; i < grille.length; i++) {
            for (int j = 0; j < grille[i].length; j++) {
                grille[i][j] = '-'; // On initialise chaque case à un tiret '-' pour représenter une case vide
            }
        }
        char joueurCourant = 'O'; // On choisit le joueur O comme premier joueur par défaut

        // Compteur de tours de jeu
        int tour = 1;

        // Boucle principale de jeu
        while (true) {

            // Affichage du numéro du tour et du joueur courant
            System.out.println("Tour " + tour + " - Joueur " + joueurCourant+"\n");

            // Affichage de la grille de jeu
            System.out.println("      0      1      2");
            System.out.println();
            for (int i = 0; i < grille.length; i++) {
                System.out.print(i + "  ");
                for (int j = 0; j < grille[i].length; j++) {
                    System.out.print("   "+grille[i][j] + "   ");
                }
                System.out.println("\n");
            }

            // Demande des coordonnées de jeu à l'utilisateur
            System.out.println("Veuillez entrer les coordonnees de votre prochain coup (colonne puis ligne) :");
            int col = sc.nextInt();
            int ligne = sc.nextInt();

            // Vérification que les coordonnées sont valides et que la case est libre
            if (col < 0 || col > 2 || ligne < 0 || ligne > 2) {
                System.out.println("Coordonnées invalides, veuillez réessayer.");
                continue;
            } else if (grille[ligne][col] != '-') {
                System.out.println("Cette case est déjà occupée, veuillez réessayer.");
                continue;
            }

            // Placement du symbole du joueur courant dans la case choisie
            grille[ligne][col] = joueurCourant;

            // Vérifie si le joueur courant a gagné en vérifiant toutes les possibilités de
            // victoire
            // Une ligne, une colonne ou une diagonale
            if (grille[ligne][0] == joueurCourant && grille[ligne][1] == joueurCourant
                    && grille[ligne][2] == joueurCourant || // Vérifie une ligne
                    grille[0][col] == joueurCourant && grille[1][col] == joueurCourant
                            && grille[2][col] == joueurCourant
                    || // Vérifie une colonne
                    grille[0][0] == joueurCourant && grille[1][1] == joueurCourant && grille[2][2] == joueurCourant || // Vérifie
                                                                                                                       // une
                                                                                                                       // diagonale
                    grille[0][2] == joueurCourant && grille[1][1] == joueurCourant && grille[2][0] == joueurCourant) { // Vérifie
                                                                                                                       // l'autre
                                                                                                                       // diagonale
                // Le joueur courant a gagné
                System.out.println("Bravo! Le joueur " + joueurCourant + " a gagné !");
                break; // Sortir de la boucle principale et terminer le jeu
            }

            // Vérification s'il y a match nul
            if (free_cells(grille) == 0) {
                System.out.println("Match nul !");
                break; // Fin de la partie
            }

            // Changement de joueur courant pour le prochain tour
            if (joueurCourant == 'O') {
                joueurCourant = 'X';
            } else {
                joueurCourant = 'O';
            }

            // Incrémentation du compteur de tours
            tour++;
        }
    }
}
