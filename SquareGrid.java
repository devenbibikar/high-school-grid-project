public class SquareGrid {
	
	int[][] squareGrid;
	String direction;
	int rowNum;
	int columnNum;
	int rows;
	int columns;
	
	SquareGrid(int rows, int columns, String direction, int rowNum, int columnNum)
	{	
		this.rows = rows;
		this.columns = columns;
		this.rowNum = rowNum;
		this.columnNum = columnNum;
		this.direction = direction;
		squareGrid = new int[rows][columns];
		for (int r = 0; r < squareGrid.length; r++) {
			for (int c = 0; c < squareGrid[r].length; c++)
			{
				squareGrid[r][c] = 0;
			}
		}
		//squareGrid[rowNum][columnNum] = -2; //DEBUGGING PURPOSES -- SHOW WHERE ROBOT STARTS
	}
	public void incrementRowNum()
	{
		rowNum++;
	}
	
	public void incrementColumnNum()
	{
		columnNum++;
	}
	public void decrementRowNum()
	{
		rowNum--;
	}
	
	public void decrementColumnNum()
	{
		columnNum--;
	}
	public void setRowNum(int input)
	{
		rowNum = input;
	}
	public void setColumnNum(int input) 
	{
		columnNum = input;
	}
	public void setLocationValue(int row, int column, int newValue)
	{
		squareGrid[row][column] = newValue;
	}
	public void changeDirection(String direction)
	{
		this.direction = direction;
	}
	public int getLocationValue(int row, int column)
	{
		return squareGrid[row][column];
	}
	public int[][] getSquareGrid()
	{
		return squareGrid; 
	}
	
	public String getDirection()
	{
		if (direction.contains("n"))
		{
			return "NORTH";
		}
		else if (direction.contains("s"))
		{
			return "SOUTH";
		}
		else if (direction.contains("e"))
		{
			return "EAST";
		}
			return "WEST";
	}
	
	public void printGrid()
	{
	for (int r = 0; r < squareGrid.length; r++) {
		for (int c = 0; c < squareGrid[r].length; c++)
		{
			if (c == 0)
			{
				System.out.println();
			}
			
			System.out.print(squareGrid[r][c] + " ");
		}
	}
	}
	public int getRowNum()
	{
		return rowNum;
	}
	public int getColumnNum()
	{
		return columnNum;
	}
	
	public int findMaxValue()
	{
		int maxValue = squareGrid[0][0];
		for (int r = 0; r < squareGrid.length; r++)
		{
			for (int c = 0; c < squareGrid[r].length; c++)
			{
				if (squareGrid[r][c] > maxValue)
				{
					maxValue = squareGrid[r][c];
				}
			}
		
		}
		return maxValue;
	}
	
	public void printFinalArray()
	{
	 int[][] finalArray = new int[rows][columns];
	 int almostMax = findMaxValue() - 2;
	 
	 for (int r = 0; r < squareGrid.length; r++)
		{
			for (int c = 0; c < squareGrid[r].length; c++)
			{
				if (squareGrid[r][c] >= almostMax)
					{
						finalArray[r][c] = 2;
					}
				else if (squareGrid[r][c] == -2)
				{
					finalArray[r][c] = 2;
				}
				else if (squareGrid[r][c] < 0)
				{
					finalArray[r][c] = squareGrid[r][c];
				}
				else if (squareGrid[r][c] < almostMax && squareGrid[r][c] > 0)
				{
					finalArray[r][c] = 1;
				}
			}
			
		}
	
	 for (int r = 0; r < finalArray.length; r++)
		{
			for (int c = 0; c < finalArray[r].length; c++)
			{
				if (c == 0)
				{
					System.out.println();
				}
				
				System.out.print(finalArray[r][c] + " ");
			}
		
		}
	 
	}

	public void overwriteArray( int[][] inputArray)
	{
		 for (int r = 0; r < squareGrid.length; r++)
			{
				for (int c = 0; c < squareGrid[r].length; c++)
				{
							squareGrid[r][c] = inputArray[r][c];
				}
	}
	}
}//last one
