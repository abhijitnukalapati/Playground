import java.util.Arrays;

public class Sudoku {

    public static void main(String[] args) {
        int[][] validBoardOne = {
            { 5, 3, 4,  6, 7, 8,  9, 1, 2 },
            { 6, 7, 2,  1, 9, 5,  3, 4, 8 },
            { 1, 9, 8,  3, 4, 2,  5, 6, 7 },
            
            { 8, 5, 9,  7, 6, 1,  4, 2, 3 },
            { 4, 2, 6,  8, 5, 3,  7, 9, 1 },
            { 7, 1, 3,  9, 2, 4,  8, 5, 6 },
            
            { 9, 6, 1,  5, 3, 7,  2, 8, 4 },
            { 2, 8, 7,  4, 1, 9,  6, 3, 5 },
            { 3, 4, 5,  2, 8, 6,  1, 7, 9 },
        }; 

        int[][] validBoardTwo = {
            { 7, 8, 4,  1, 5, 9,  3, 2, 6},
            { 5, 3, 9,  6, 7, 2,  8, 4, 1},
            { 6, 1, 2,  4, 3, 8,  7, 5, 9},

            { 9, 2, 8,  7, 1, 5,  4, 6, 3},
            { 3, 5, 7,  8, 4, 6,  1, 9, 2},
            { 4, 6, 1,  9, 2, 3,  5, 8, 7},

            { 8, 7, 6,  3, 9, 4,  2, 1, 5},
            { 2, 4, 3,  5, 6, 1,  9, 7, 8},
            { 1, 9, 5,  2, 8, 7,  6, 3, 4}
        };

        int[][] invalidBoardOne = {
            { 5, 3, 4,  6, 7, 8,  9, 1, 2 },
            { 6, 5, 2,  1, 9, 5,  3, 4, 8 },
            { 1, 9, 8,  3, 4, 2,  5, 6, 7 },
            
            { 8, 5, 9,  7, 6, 1,  4, 2, 3 },
            { 4, 2, 6,  8, 5, 3,  7, 9, 1 },
            { 7, 1, 3,  9, 2, 4,  8, 5, 6 },
            
            { 9, 6, 1,  5, 3, 7,  2, 8, 4 },
            { 2, 8, 7,  4, 1, 9,  6, 3, 5 },
            { 3, 4, 5,  2, 8, 6,  1, 7, 9 },
        };

        int[][] invalidBoardTwo = {
            { 1, 1, 1,  1, 1, 1,  2, 2, 2},
            { 5, 3, 9,  6, 7, 2,  8, 4, 1},
            { 6, 1, 2,  4, 3, 8,  7, 5, 9},

            { 9, 2, 8,  7, 1, 5,  4, 6, 3},
            { 3, 5, 7,  8, 4, 6,  1, 9, 2},
            { 4, 6, 1,  9, 2, 3,  5, 8, 7},

            { 8, 7, 6,  3, 9, 4,  2, 1, 5},
            { 2, 4, 3,  5, 6, 1,  9, 7, 8},
            { 1, 9, 5,  2, 8, 7,  6, 3, 4}
        };


        System.out.println(isValidBoard(validBoardOne));
        System.out.println(isValidBoard(validBoardTwo));
        System.out.println(isValidBoard(invalidBoardTwo));
        System.out.println(isValidBoard(invalidBoardTwo));
    }


    private static boolean isValidBoard(int[][] board) {
        final int BOARD_LENGTH = 9;
        
        // length checks

        // rows = 9
        if(board.length != 9) {
            return false;
        }

        // colums = 9
        for(int i = 0; i < board.length; i++) {
            if(board[i].length != 9) {
                return false;
            }    
        }      


        // content checks

        boolean[] checks = new boolean[BOARD_LENGTH];

        // rows
        // 0,0  0,1  0,2
        for(int row = 0; row < BOARD_LENGTH; row++) {
            for(int col = 0; col < BOARD_LENGTH; col++) {
                if(checks[board[row][col] - 1]) {
                    return false;
                }

                checks[board[row][col] - 1] = true;
            }
            Arrays.fill(checks, false);
        }

        // cols
        // 0,0
        // 1,0
        // 2,0
        // 3,0 
        for(int col = 0; col < BOARD_LENGTH; col++) {
            for(int row = 0; row < BOARD_LENGTH; row++) {
                if(checks[board[row][col] - 1]) {
                    return false;
                }

                checks[board[row][col] - 1] = true;
            }
            Arrays.fill(checks, false);
        }

        // 3x3
        // 0,0 0,1 0,2
        // 1,0 1,1 1,2
        // 2,0 2,1 2,2
        for(int rowMult = 0; rowMult < BOARD_LENGTH; rowMult = rowMult + 3) {
            for(int colMult = 0; colMult < BOARD_LENGTH; colMult = colMult + 3) {
                for(int row = rowMult; row < rowMult + 3; row++) {
                    for(int col = colMult; col < colMult + 3; col++) {
                        if(checks[board[row][col] - 1]) {
                            return false;
                        }

                        checks[board[row][col] - 1] = true;
                    }
                }
                Arrays.fill(checks, false);
            }
        }
        
        return true;
    }

}
