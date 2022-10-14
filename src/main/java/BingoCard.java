import java.util.Random;
import java.util.Scanner;

public class BingoCard {
    public static final int ROWS = 5;
    public static final int COLS = 5;
    public int[][] bingoCard;
    Random generator;

    public BingoCard() {
        this.generator = new Random();
        bingoCard = new int[ROWS][COLS];
    }

    private boolean isADuplicate(int colNumber, int input) {
        boolean duplicate = false;
        for (int i = 0; i < COLS; i++) {
            if (bingoCard[i][colNumber] == input) {
                duplicate = true;
                break;
            }
        }
        return duplicate;
    }

    private void generateColumnNumbers(int columnNumber, int randMin) {
        boolean duplicate = true;
        int generatedNumber = 0;
        for (int i = 0; i < COLS; i++) {
            do {
                generatedNumber = generator.nextInt(15) + randMin;
                duplicate = isADuplicate(columnNumber, generatedNumber);
            }
            while (duplicate);
            if (i == 2 && columnNumber == 2) {
                bingoCard[i][columnNumber] = 0;
            } else {
                bingoCard[i][columnNumber] = generatedNumber;
            }
        }
    }

    protected void generateBingoCard() {
        generateColumnNumbers(0, 1);
        generateColumnNumbers(1, 16);
        generateColumnNumbers(2, 31);
        generateColumnNumbers(3, 46);
        generateColumnNumbers(4, 61);
    }

    protected void printCard() {
        System.out.println("B\tI\tN\tG\tO");
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                System.out.print(bingoCard[i][j] + "\t");
            }
            System.out.println();
        }
    }

    protected boolean isWinningCard() {
        boolean playerWin = false;

        //looks for a winning pattern in the column
        for (int i = 0; i < ROWS; i++) {
            int columnSum = 0;
            for (int j = 0; j < COLS; j++) {
                columnSum += bingoCard[j][i];
            }
            if (columnSum == 0) {
                playerWin = true;
                return playerWin;
            }
        }

        //looks for a winning pattern in the row
        for (int i = 0; i < ROWS; i++) {
            int rowSum = 0;
            for (int j = 0; j < COLS; j++) {
                rowSum += bingoCard[i][j];
            }
            if (rowSum == 0) {
                playerWin = true;
                return playerWin;
            }
        }

        //looks for a winning pattern in the main diagonal
        int mainDiagonalSum = 0;
        for (int i = 0; i < ROWS; i++) {
            mainDiagonalSum += bingoCard[i][i];
        }
        if (mainDiagonalSum == 0) {
            playerWin = true;
            return playerWin;
        }

        //looks for a winning pattern in the antidiagonal
        int antiDiagonalSum = 0;
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if ((i + j) == COLS - 1) {
                    antiDiagonalSum += bingoCard[i][j];
                }
            }
        }
        if (antiDiagonalSum == 0) {
            playerWin = true;
            return playerWin;
        }
        return playerWin;
    }

    protected void markCard(int callingNumber) {
        char bingoColumn;
        if (callingNumber <= 15)
            bingoColumn = 'B';
        else if (callingNumber <= 30)
            bingoColumn = 'I';
        else if (callingNumber <= 45)
            bingoColumn = 'N';
        else if (callingNumber <= 60)
            bingoColumn = 'G';
        else
            bingoColumn = 'O';

        switch (bingoColumn) {
            case 'B':
                for (int i = 0; i < ROWS; i++) {
                    if (bingoCard[i][0] == callingNumber) {
                        bingoCard[i][0] = 0;
                    }
                }
                break;
            case 'I':
                for (int i = 0; i < ROWS; i++) {
                    if (bingoCard[i][1] == callingNumber) {
                        bingoCard[i][1] = 0;
                    }
                }
                break;
            case 'N':
                for (int i = 0; i < ROWS; i++) {
                    if (bingoCard[i][2] == callingNumber) {
                        bingoCard[i][2] = 0;
                    }
                }
                break;
            case 'G':
                for (int i = 0; i < ROWS; i++) {
                    if (bingoCard[i][3] == callingNumber) {
                        bingoCard[i][3] = 0;
                    }
                }
                break;
            case 'O':
                for (int i = 0; i < ROWS; i++) {
                    if (bingoCard[i][4] == callingNumber) {
                        bingoCard[i][4] = 0;
                    }
                }
                break;

        }
    }

    public static void main(String[] args) {
        BingoCard b = new BingoCard();
        b.generateBingoCard();
        b.printCard();
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter rolled number:");
            int callingNumber = sc.nextInt();
            b.markCard(callingNumber);
            b.printCard();
            if (b.isWinningCard()) {
                System.out.println("BING OHHHHHHHHHHHHHHHHHHHHHHHHHHHHH!!!!You have won!");
                break;
            }
        }
    }
}
