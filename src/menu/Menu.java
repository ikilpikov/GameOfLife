package menu;

import game.GameOfLife;

import java.util.Scanner;

public class Menu {

    private final Scanner scanner;
    private final String START = "s";
    private final String QUIT = "q";

    /**
     * This is a "GameOfLife" title, which looks well in terminal
     */
    private final String logo = """
              ________                      ________   _____.____    .__  _____      \s
             /  _____/_____    _____   ____ \\_____  \\_/ ____\\    |   |__|/ ____\\____ \s
            /   \\  ___\\__  \\  /     \\_/ __ \\ /   |   \\   __\\|    |   |  \\   __\\/ __ \\\s
            \\    \\_\\  \\/ __ \\|  Y Y  \\  ___//    |    \\  |  |    |___|  ||  | \\  ___/\s
             \\______  (____  /__|_|  /\\___  >_______  /__|  |_______ \\__||__|  \\___  >
                    \\/     \\/      \\/     \\/        \\/              \\/             \\/\s""";


    public Menu() {
        System.out.println(logo);
        scanner = new Scanner(System.in);
    }

    public void run() {
        System.out.println("Input 's' to play. Input 'q' to quit.");
        System.out.print("-> ");

        String input = scanner.next();

        while (input.compareTo(QUIT) != 0) {

            if (input.compareTo(START) == 0) {
                gameProcessInteraction();
                return;
            } else {
                System.out.println("Unknown command!");
            }

            System.out.print("-> ");
            input = scanner.next();
        }
        System.out.println("Bye.");
    }

    private void gameProcessInteraction() {
        System.out.println("Now input game field size.");
        int rows, cols;

        try {
            System.out.print("Rows -> ");
            rows = scanner.nextInt();
            System.out.print("Cols -> ");
            cols = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Unknown symbol.");
            return;
        }

        GameOfLife gameOfLife = new GameOfLife(rows, cols);
        aliveCellsInput(gameOfLife);

        System.out.println("Initial phase");
        System.out.println(gameOfLife);


        int generations = generationsInput();
        printGenerations(gameOfLife, generations);

    }

    private void aliveCellsInput(GameOfLife gameOfLife) {
        System.out.println("Input alive cells. ");
        int row, col;
        int i = 0;

        while(i < gameOfLife.getCols() * gameOfLife.getRows()) {
            try {
                System.out.print("Row -> ");
                String input = scanner.next();

                if (input.compareTo(QUIT) == 0) {
                    break;
                }

                row = Integer.parseInt(input);

                System.out.print("Col -> ");
                input = scanner.next();
                col = Integer.parseInt(input);

                gameOfLife.reviveCell(row, col);
            } catch (IllegalArgumentException e) {
                System.out.println("Unknown symbol.");
            }

            i++;
        }
    }

    private int generationsInput() {
        int generations = 0;

        try {
            System.out.print("Input generations -> ");
            generations = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Unknown symbol.");
        }

        return generations;
    }

    private void printGenerations(GameOfLife gameOfLife, int amount) {
        for(int i = 0; i < amount; i++){

            if(gameOfLife.isGameOver()){
                System.out.println("Game is over. Everyone's dead.");
                break;
            }
            System.out.println("Generation #" + (i + 1));
            gameOfLife.makeNextGeneration();
            System.out.println(gameOfLife);
        }
    }

}
