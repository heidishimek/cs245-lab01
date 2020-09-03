import java.util.*;
public class Sudoku 
{
    private int BOARD_SIZE = 9; //initializes 9x9 grid
    private int[][] board; //initializes double array grid

    // sets up the board
    public Sudoku(int[][] board)
    {
        
        int BOARD_SIZE = 9;
       this.board = new int[BOARD_SIZE][BOARD_SIZE];

       //assigns each column/row to whatever user inputs in main
       for(int row = 0; row < BOARD_SIZE; row++)
       {
           for(int col = 0; col < BOARD_SIZE; col++)
           {
               this.board[row][col] = board[row][col];
           }
       }
    }



    //checks if number already exists in column
    private boolean isInCol(int col, int number)
    {
        for(int row = 0; row < 9; row++)
        {
            if(board[row][col] == number)
            {
                //returns true if that # already exists in the column
                return true;
            }
        }
          // returns false if there's no duplicate of that number in the column
        return false;
    }

  //checks if number already exists in row
    private boolean isInRow(int row, int number)
    {
        for(int col = 0; col < BOARD_SIZE; col++)
        {
            if(board[row][col] == number)
            {
                //returns true if that # already exists in the row
                return true;
            }
        }
        // returns false if there's no duplicate of that number in the row
        return false;
    }

  //checks if number already exists in box/subgrid
    private boolean isInBox(int row, int col, int number){
        int positionRow = row-row % 3;
        int positionCol = col-col% 3;
        for(int rows = positionRow; rows < positionRow + 3; rows++) //checks +3 because each subgrid is 3x3
        {
            for(int cols = positionCol; cols < positionCol + 3; cols++)
            {
                if(board[rows][cols] == number)
                {
                    //returns true if that # already exists in the subgrid/box
                    return true;
                }
            }
        }
          // returns false if there's no duplicate of that number in the subgrid/box
        return false;
    }


    //runs if the above is false. checks to make sure it's all good
    private boolean safe(int row, int col, int number)
    {
        return !(isInRow(row, number) || isInCol(col, number) || isInBox(row, col, number));
    }

    
    public boolean finished()
    {
        for(int row = 0; row < BOARD_SIZE; row++)
        {
            for(int col = 0; col < BOARD_SIZE; col++)
            {
                if(board[row][col] == 0)    //goes through each column/row and looks for a 0. 
                {
                    //Tries to place a number in that spot, starting with 1. Then it makes sure the number is safe in that spot
                    for(int number = 1; number <= BOARD_SIZE; number++)
                    {
                        if(safe(row, col, number))    //makes sure the attempted number is safe with the selected spot
                        {
                            //changes is to that number if it works
                            board[row][col] = number;
                            // returns true if it works
                            if(finished())
                            {
                                return true;
                            } 

                            else 
                            {
                                // if it doesn't work, it resets to 0 and backtracks
                                board[row][col] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        // true if solved
        return true; //base case
    }

    

    // prints it
    public void print()
    {
        for (int row = 0; row < BOARD_SIZE; row++) 
        {
			for (int col = 0; col < 9; col++) 
            {
                System.out.print(" " + board[row][col]);
            }
			System.out.println();
		}
		
		System.out.println();
    }
    
    public static void main(String[] args)
    {
   

        int BOARD_SIZE = 9;

        //user enterss  their numbers
        System.out.println("Enter your numbers one at a time. Click enter/return to type your new number. For blanks, enter 0");
        Scanner scan = new Scanner(System.in);
        int[][] grid = new int[BOARD_SIZE][BOARD_SIZE];
        for(int row=0;row<grid.length;row++)
            for(int col=0;col < grid [row].length; col++)
            grid[row][col]=scan.nextInt();

    
        
        Sudoku sudoku = new Sudoku(grid); //object
        
        System.out.println("solving...\n");
        sudoku.print();
        
        //tries to solve it, continues if solve is true
        if(sudoku.finished())
        {
            System.out.println("Solved sudoku:\n");
       
            //prints it solved
            sudoku.print();
           
        }
        else 
        {
            //if it is false and cannot be solved, then it prints this
            System.out.println("It is unsolvable, sorry!");
        }
    }
   
}


   
