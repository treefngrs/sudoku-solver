package src;

public class Board {

    public static int size;
    private static int sub;
    private int tablero[][];

    public boolean next(int row, int col) {

        if (tablero[row][col] == size) {
            return false;
        }

        int valor = ++tablero[row][col];
        boolean bool = true;

        for(int i=0;i<size;i++) {
            if ((tablero[row][i]==valor && col != i)||(tablero[i][col]==valor && row != i)) {
                bool = false;
                break;
            }
        }

        if (bool) {
            for (int i = (row / sub) * sub; i < ((row / sub) + 1) * sub; i++) {
                for (int j = (col / sub) * sub; j < ((col / sub) + 1) * sub; j++) {
                    if (tablero[i][j] == valor && i != row && j != col) {
                        bool = false;
                        break;
                    }
                }
            }
        }
        if (!bool)
            return next(row, col);

        return true;
    }


    public boolean solve(int row, int col) {

        if (row == this.size) {
            return true;
        }

        if (this.tablero[row][col] != 0) {
            if (col != this.size - 1)
                return solve(row, col + 1);
            return solve(row + 1, 0);
        }

        boolean solved = false;

        while (this.next(row, col)) {
            if (col != this.size - 1)
                solved = solve(row, col + 1);
            else
                solved = solve(row + 1, 0);
            if (solved)
                return true;
        }

        this.tablero[row][col] = 0;
        return false;
    }

    public Board(int tabla[][]) {
        this.tablero = tabla;
        this.size = tabla.length;
        this.sub = (int) Math.sqrt(tabla.length);
    }

    public String toString() {
        String cad = "";
        for(int i=0;i<size;i++) {
            for(int j=0;j<size;j++) {
               cad = cad.concat(tablero[i][j]+ " ");

            }
            cad = cad.concat("\n");
        }
        return cad;
    }
}
