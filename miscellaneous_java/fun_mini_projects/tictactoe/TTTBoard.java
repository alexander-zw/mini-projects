package tictactoe;

/*
 * Note: from Fundamentals of Java
 */
public class TTTBoard {
	
	private char[][] grid;
	
	public TTTBoard(){
		grid = new char[3][3];
	}
	
	public void reset(){	//resets all grids to null
		grid = new char[3][3];
	}
	
	public char getGrid(int row, int column) throws IllegalArgumentException{
		if(row < 1 || row > 3 || column < 1 || column > 3)
				throw new IllegalArgumentException("Invalid grid");
		return grid[row - 1][column - 1];
	}
	
	//places an "X" for player 1 and "O" for player 2
	public void place(int player, int row, int column) throws RuntimeException{
		if(row < 1 || row > 3 || column < 1 || column > 3)	//check validity of row and column
			throw new IndexOutOfBoundsException("Invalid grid");
		char symbol;
		switch(player){	//determines the player's symbol
		case 1:
			symbol = 'X';
			break;
		case 2:
			symbol = 'O';
			break;
		default:
			throw new IllegalArgumentException("Invalid player");
		}
		if(grid[row - 1][column - 1] == (char)0)
			grid[row - 1][column - 1] = symbol;
		else
			throw new UnsupportedOperationException("Grid already occupied");
		
	}
	
	public int winner(){	//returns winner or 0 if there is none
		
		for(char[] row: grid){	//horizontals
			if(row[0] == 'X' && row[1] == 'X' && row[2] == 'X')
				return 1;
			if(row[0] == 'O' && row[1] == 'O' && row[2] == 'O')
				return 2;
		}
		
		for(int i = 0; i < 3; i++){	//verticals
			if(grid[0][i] == 'X' && grid[1][i] == 'X' && grid[2][i] == 'X')
				return 1;
			if(grid[0][i] == 'O' && grid[1][i] == 'O' && grid[2][i] == 'O')
				return 2;
		}
		
		//diagonals
		if(grid[0][0] == 'X' && grid[0][0] == grid[1][1] && grid[1][1] == grid[2][2])
			return 1;
		if(grid[0][0] == 'O' && grid[0][0] == grid[1][1] && grid[1][1] == grid[2][2])
			return 2;
		if(grid[0][2] == 'X' && grid[0][2] == grid[1][1] && grid[1][1] == grid[2][0])
			return 1;
		if(grid[0][2] == 'O' && grid[0][2] == grid[1][1] && grid[1][1] == grid[2][0])
			return 2;
		
		return 0;
	}
	
	public boolean full(){	//whether board is full
		return tools.ArrayMethods.search2D(grid, (char)0) == null;
	}
	
	@Override
	public String toString(){	//returns the board
		String board = "";
		for(char[] row: grid){
			for(char aGrid: row){
				if(aGrid == (char)0)	//if the cell is empty, null
					board += "- ";
				else
					board += aGrid + " ";
			}
			board += "\n";
		}
		return board;
	}
	
}
