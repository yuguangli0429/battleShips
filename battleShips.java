import java.util.*;

public class battleShips {
    public static String[][] grid = new String[10][10];
    public static int enemyX[] = new int[5];
    public static int enemyY[] = new int[5];
    public static int yourleft = 5;
    public static int computerleft = 5;

    public static void main(String[] args) {
        System.out.println("**** Welcome to Battle Ships game ****\nRight now, sea is empty");
        System.out.println();

        createMap();
        System.out.println();

        deployShips();
        printMap();

        System.out.println("Computer is deploying ships");
        enemyShip();
        System.out.println();

        while ((yourleft != 0) && (computerleft != 0)) {
            player();
            computer();
            printMap();
            System.out.println("--------------------------------");
        }

        if (yourleft == 0) {System.out.println("You lose!!!");}
        else {System.out.println("You win!!!");}
    }

    public static void createMap() {
        System.out.print("  ");
        for (int a = 0; a < 10; a++) {
            System.out.print(a);
        }
        System.out.println();

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = " ";
                if (j == 0) {
                    System.out.print(i + "|" + grid[i][j]);
                } else if (j == 9) {
                    System.out.print(grid[i][j] + "|" + j);
                } else {
                    System.out.print(grid[i][j]);
                }
            }System.out.println();
        }

        System.out.print("  ");
        for (int a = 0; a < 10; a++) {
            System.out.print(a);
        }
        System.out.println();
    }

    public static void deployShips() {
        Scanner input = new Scanner(System.in);
        System.out.println("Deploy your ships");
        for (int i = 1; i <= 5; ) {
            System.out.print("Enter X coordinate for your " + i + " ship:");
            int x = input.nextInt();
            System.out.print("Enter Y coordinate for your " + i + " ship:");
            int y = input.nextInt();
            if ((x<10) && (y<10)) {
                if (grid[x][y] == " ")  {
                    grid[x][y] = "S";
                    i++;
                } else {System.out.println("You already deploy ship there! Re-enter again:");}
            } else {System.out.println("Outside! Should be [0:10]");}
        }
    }

    public static void enemyShip() {
        int i = 0;
        while(i < 5) {
            int x = (int)(Math.random() * 10);
            int y = (int)(Math.random() * 10);
                if (grid[x][y] == " ") {
                    enemyX[i] = x;
                    enemyY[i] = y;
                    i++;
                    System.out.println(i + ". computer ship DEPLOYED");}
        } System.out.println("--------------------------------");
    }

    public static void player() {
        Scanner input = new Scanner(System.in);
        System.out.println("YOUR TURN! GO!!!");
        System.out.print("Enter X Coordinate");
        int x = input.nextInt();
        System.out.print("Enter Y Coordinate");
        int y = input.nextInt();
        int i = 0;
        int s = 0;
        while((i < 5) && (s == 0)) {
            if (x == enemyX[i] && y == enemyY[i]) {
                System.out.println("\nBoom! You sunk the ship!");
                grid[x][y] = "~";
                s = 1;
                computerleft--;
            }
            i++;
        } if (s == 0) {
            if (grid[x][y] == "S") {
                System.out.println("\nOh idiot, you sunk your own ship! FOOL!");
                grid[x][y] = "X";
                yourleft--;
            } else {
                System.out.println("\nYou missed");
                grid[x][y] = "-";
            }
        }
    }

    public static void computer() {
        int i = 0;
        int s = 0;
        int x = (int) (Math.random() * 10);
        int y = (int) (Math.random() * 10);
        while ((i < 5) && (s == 0)) {
            if (x == enemyX[i] && y == enemyY[i]) {
                System.out.println("The Computer sunk one of its own ships! LOL");
                grid[x][y] = "~";
                s = 1;
                computerleft--;
            }
            i++;
        }
        if (s == 0) {
            if (grid[x][y] == "S") {
                System.out.println("The Computer sunk one of your ships!");
                grid[x][y] = "X";
                yourleft--;
            } else {
                System.out.println("Computer missed");
            }
        }
    }


    public static void printMap() {
        System.out.println();
        System.out.print("  ");
        for(int i = 0; i < 10; i++)
            System.out.print(i);
        System.out.println();

        for(int i = 0; i < 10; i++) {
            System.out.print(i + "|");
            for (int j = 0; j < 10; j++){
                System.out.print(grid[i][j]);
            }
            System.out.println("|" + i);
        }

        System.out.print("  ");
        for(int i = 0; i < 10; i++)
            System.out.print(i);
        System.out.println();

        System.out.println("\nYour ships: " + yourleft + " | " + "Computer ships: " + computerleft);
    }
}
