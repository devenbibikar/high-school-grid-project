import java.io.File;
import java.util.Scanner;


public class InitialPathRunner {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		File directory = new File("Presets");
		directory.mkdir();
		
		FileManager manager = new FileManager(); 
		//manager.CreateFile("file.txt");
		//manager.WriteToFile("file.txt","Coordinates: #,# ;; Direction: [n] ;; {arraylist} ;; Rows: # ;; Columns: #");
		//System.out.println(manager.ReadFile("file.txt"));
		
		//variables used in warmup program
		boolean continueToProgram = false;
		int choice = 0;
		int rowNumStart;
		int columnNumStart;
		int rows;
		int columns;
		String fileData;
		String direction;
		String rawArray = "";
		int[][] inputArray;
		
		while (!continueToProgram)
		{
			if (choice == 0)
			{
			System.out.println("[1] Create Preset\n[2] Load Preset\n[3] Quit Program\n");
			choice = scan.nextInt();
			}
			if (choice == 1)
			{
				
				System.out.println("Rows?");
				rows = scan.nextInt();
				
				System.out.println("Columns?");	
				columns = scan.nextInt();
				
				System.out.println("Choose a direction. [n/s/e/w]");
				scan.nextLine();
				direction = scan.nextLine();
				
				System.out.println("Enter coordinates of robot: Row #,Column #");
				String coordinates = scan.nextLine();
				rowNumStart = Integer.parseInt(coordinates.substring( 0, coordinates.indexOf(",")));
				columnNumStart = Integer.parseInt(coordinates.substring(coordinates.indexOf(",") + 1, coordinates.length()));
					
				//SquareGrid board = new SquareGrid(rows, columns, direction, rowNumStart, columnNumStart);
				int[][] tempArray1 = new int[rows][columns];
				SquareGrid displayBoard = new SquareGrid(rows, columns, direction, rowNumStart, columnNumStart); //visual purposes only
				
				String secondChoice = "n";
				System.out.println("Would you like to set any custom values? [y/n]");
				secondChoice = scan.nextLine();
				
				if (secondChoice.equals("y"))
				{
					System.out.println("Rows?");
					int tempRow = scan.nextInt();
					
					System.out.println("Columns?");	
					int tempColumn = scan.nextInt();
					
					
					
					//System.out.println("Previous Value at (" + tempRow + "," + tempColumn + "): " + tempArray1[tempRow][tempColumn] + "\nNew Value?");	
					
					System.out.println("New Value?");	
					int newValue = scan.nextInt();
					tempArray1[tempRow][tempColumn] = newValue;
					displayBoard.setLocationValue(tempRow, tempColumn, newValue);
					System.out.println("\nSuccess!\nSet value " + newValue + " at (" + tempRow + "," + tempColumn + ")");
				}
				
				
				for (int r = 0; r < tempArray1.length; r++)
				{
					for (int c = 0; c < tempArray1[r].length; c++)
					{
						rawArray += tempArray1[r][c] + " "; 
					}
				
				}
					
				scan.nextLine();
				System.out.println("Name of File?");
				manager.WriteToFile(scan.nextLine() + ".txt","Start: " 
				+ rowNumStart + "," + columnNumStart +" ;"
						+ "; Direction: [" + direction + "] ;"
						+ "; {"+ rawArray +"} ;"
						+ ";" + " Rows: "+ rows +" ;"
						+ "; Columns: "+ columns );
				
				choice = 0;
				
				
				displayBoard.printGrid();
				System.out.println("\n");
				
			}
			else if (choice == 2)
			{

				continueToProgram = true;
			}
			else
			{
				System.out.println("\nExiting Program..");
				System.exit(0);
			}
		}

		//CHOICE 2 FINISHING -- LOADING PORTION
		manager.listFiles();
		System.out.println("\nWhich preset would you like to load?");
		scan.nextLine();
		fileData = manager.ReadFile(scan.nextLine());
		
		rowNumStart = Integer.parseInt(fileData.substring( fileData.indexOf(":") + 2, fileData.indexOf(",")));
		columnNumStart = Integer.parseInt(fileData.substring(fileData.indexOf(",") + 1, fileData.indexOf(";") - 1));
		//System.out.println(rowNumStart + " " + columnNumStart);
		direction = fileData.substring(fileData.indexOf("[") + 1, fileData.indexOf("]"));
		//System.out.println(direction);
		rows = Integer.parseInt(fileData.substring(fileData.indexOf("Rows:") + 6, fileData.indexOf("Rows:") + 7));
		columns = Integer.parseInt(fileData.substring(fileData.indexOf("Columns:") + 9, fileData.indexOf("Columns:") + 10));
		
		//System.out.println("Start: " + rowNumStart + "," + columnNumStart + " ;; Direction: [" + direction + "] ;; {"+ rawArray +" } ;; Rows: "+ rows +" ;; Columns: "+ columns);
		SquareGrid board = new SquareGrid(rows, columns, direction, rowNumStart, columnNumStart);
		
		rawArray = fileData.substring(fileData.indexOf("{") + 1, fileData.indexOf("}"));
		//System.out.println(rawArray);
		inputArray = new int[rows][columns];
		int x = 0;
		int y = 1;
		
		for (int r = 0; r < inputArray.length; r++)
		{
			for (int c = 0; c < inputArray[r].length; c++)
			{
				if (rawArray.substring(x,y).equals("-"))
				{
					y++;
					x++;
					inputArray[r][c] = Integer.parseInt(rawArray.substring(x,y)) * -1;
					x = x + 2;
					y = y + 2;
				}
				else 
				{
					inputArray[r][c] = Integer.parseInt(rawArray.substring(x,y));
					x = x + 2;
					y = y + 2;
				}
			}	
		
		}
		scan.close();
		board.overwriteArray(inputArray);
		
		/* ----------------DEBUGGING FOR PRINTING GRID/CHECK LOADING
		for (int r = 0; r < inputArray.length; r++)
		{
			System.out.println();
			for (int c = 0; c < inputArray[r].length; c++)
			{
				System.out.println(inputArray[r][c]);
			}
		} */
		System.out.println("Loading the following Preset:");
		board.printGrid();
		System.out.println();
		
		
		
		
		//Actual Program
		
		int steps = 1;
		int counterLoop = 0; 
		
		while (counterLoop < (rows * columns * 10))
		{
		if (board.getDirection().equals("NORTH"))
		{
			//board.printGrid();
			//System.out.println();
			if (board.getRowNum() > 0)
			{
				if (board.getLocationValue(board.getRowNum() - 1, board.getColumnNum()) >= 0)
					{
						if (board.getLocationValue(board.getRowNum() - 1, board.getColumnNum()) == steps)
						{
							steps++;
						}
						board.setLocationValue(board.getRowNum(), board.getColumnNum(), steps);
						board.decrementRowNum();
						board.setLocationValue(board.getRowNum(), board.getColumnNum(), -2);
					}
				else if (board.getLocationValue(board.getRowNum() - 1, board.getColumnNum()) == -1) // when it hits -1
				{
					board.changeDirection("w");
				}
				
			}
			if (board.getRowNum() == 0)
			{
				//board.printGrid();
				board.changeDirection("w");
				
				//System.out.println("\n\nThe direction is now " + board.getDirection());
				//continueLoop = false;
				counterLoop++;
			}

			
		}
		else if (board.getDirection().equals("SOUTH"))
		{
			//board.printGrid();
			//System.out.println();
			if (board.getRowNum() < rows - 1)
			{
				
				if ((board.getLocationValue(board.getRowNum() + 1, board.getColumnNum())) >= 0)
					{
						if ((board.getLocationValue(board.getRowNum() + 1, board.getColumnNum())) == steps)
						{
							steps++;
						}
						board.setLocationValue(board.getRowNum(), board.getColumnNum(), steps);
						board.incrementRowNum();
						board.setLocationValue(board.getRowNum(), board.getColumnNum(), -2);
						
					}
				else if ((board.getLocationValue(board.getRowNum() + 1, board.getColumnNum())) == -1)
				{
					board.changeDirection("e");
				}
			}
			if (board.getRowNum() == rows - 1)
			{
				//board.printGrid();
				board.changeDirection("e");
				
				//System.out.println("\n\nThe direction is now " + board.getDirection());
				//continueLoop = false;
				counterLoop++;
			}
			
		}
		else if (board.getDirection().equals("EAST"))
		{
			//board.printGrid();
			//System.out.println();
			if (board.getColumnNum() < columns - 1 )
			{
				
				if (board.getLocationValue(board.getRowNum(), board.getColumnNum() + 1) >= 0)
					{
						if (board.getLocationValue(board.getRowNum(), board.getColumnNum() + 1) == steps)
						{
							steps++;
						}
						board.setLocationValue(board.getRowNum(), board.getColumnNum(), steps);
						board.incrementColumnNum();
						board.setLocationValue(board.getRowNum(), board.getColumnNum(), -2);
						//System.out.println(board.getColumnNum());
						//break;
					}
				else if (board.getLocationValue(board.getRowNum(), board.getColumnNum() + 1) == -1)
				{
					board.changeDirection("n");
				}
			}
			if (board.getColumnNum() == columns - 1)
			{
				//board.printGrid();
				board.changeDirection("n");
				
				//System.out.println("\n\nThe direction is now " + board.getDirection());
				//continueLoop = false;
				counterLoop++;
			}
			
		}
		else if (board.getDirection().equals("WEST"))
		{
			//board.printGrid();
			//System.out.println();
			if (board.getColumnNum() > 0 )
			{

				if (board.getLocationValue(board.getRowNum(), board.getColumnNum() - 1) >= 0)
					{
						if (board.getLocationValue(board.getRowNum(), board.getColumnNum() - 1) == steps)
						{
							steps++;
						}
						board.setLocationValue(board.getRowNum(), board.getColumnNum(), steps);
						board.decrementColumnNum();
						board.setLocationValue(board.getRowNum(), board.getColumnNum(), -2);
						//System.out.println(board.getColumnNum());
						//break;
					}
				else if (board.getLocationValue(board.getRowNum(), board.getColumnNum() - 1) == -1)
				{
					board.changeDirection("s");
				}
			}
			if (board.getColumnNum() == 0)
			{
				//board.printGrid();
				board.changeDirection("s");
				
				//System.out.println("\n\nThe direction is now " + board.getDirection());
				//continueLoop = false;
				counterLoop++;
			}
			
		}
		
		
		
		
		} // end of while loop1
		
		System.out.println("\nHere is the traced path:");
		board.printFinalArray();

		
		

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

} // second to last one
} // last one


