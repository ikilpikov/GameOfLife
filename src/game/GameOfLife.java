package game;

import cell.*;

public class GameOfLife {
    private final int rows;
    private final int cols;
    private Cell[][] matrix;

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public GameOfLife(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        matrix = new Cell[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < rows; j++) {
                matrix[i][j] = new Cell();
                matrix[i][j].setState(State.DEAD);
            }
        }
    }

    public boolean isGameOver(){
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < rows; j++) {
                if(matrix[i][j].isAlive()){
                    return false;
                }
            }
        }

        return true;
    }

    public void makeNextGeneration() {
        Cell[][] nextGenerationMatrix = new Cell[rows][cols];
        int aliveNeighboursAmount;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                aliveNeighboursAmount = calculateAliveNeighbours(i, j);

                if (matrix[i][j].isAlive() && (aliveNeighboursAmount == 2 || aliveNeighboursAmount == 3)) {
                    nextGenerationMatrix[i][j] = new Cell(State.ALIVE);
                } else if (!matrix[i][j].isAlive() && aliveNeighboursAmount == 3) {
                    nextGenerationMatrix[i][j] = new Cell(State.ALIVE);
                } else {
                    nextGenerationMatrix[i][j] = new Cell(State.DEAD);
                }
            }
        }

        matrix = nextGenerationMatrix;
    }

    public void reviveCell(int row, int col) {
        matrix[row][col].setState(State.ALIVE);
    }

    private int calculateAliveNeighbours(int row, int col) {
        int totalAmount = 0;

        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (!isCellExisting(i, j)) {
                    continue;
                }

                if (i == row && j == col) {
                    continue;
                }

                if (matrix[i][j].isAlive()) {
                    totalAmount++;
                }
            }
        }

        return totalAmount;
    }

    private boolean isCellExisting(int i, int j) {
        if (i < 0 || j < 0) {
            return false;
        } else if (i >= rows || j >= cols) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("  ");

        for (int i = 0; i < cols; i++) {
            sb.append(i + " ");
        }
        sb.append('\n');

        for (int i = 0; i < rows; i++) {
            sb.append(i + " ");
            for (int j = 0; j < cols; j++) {
                sb.append(matrix[i][j] + " ");
            }

            sb.append('\n');
        }

        return sb.toString();

    }


}
