// Solomon Astley, CS 0445 Ramirez, Recitation Tuesday 10:00
// Date : March 15, 2016
// Assignment 4 - This program is a maze solving program that utilizes backtracking

import java.util.*;
import java.io.*;

public class Assig4
{
	private Stack<String> solution;
	private boolean [][] beenThere;
	private String [][] maze;

	public static void main(String[] args) throws IOException
	{
		new Assig4();
	}

	public Assig4()
	{
		String filename = args[0]; // Read filename from command line
				
		File inFile = new File(filename);
		Scanner inScan = new Scanner(inFile); // Create scanner for file

		while (inScan.hasNext()) // While loop for multiple input mazes
		{
			System.out.println("-------------------------\n");
			System.out.println("The new board is:");

			// Read in the first two numbers for the maze, number of rows and cols
			String line1 = inScan.nextLine();
			int rows = Integer.parseInt(line1.split(" ")[0]);
			int cols = Integer.parseInt(line1.split(" ")[1]);

			// Read in the second two numbers for the maze, starting position in the maze
			String line2 = inScan.nextLine();
			int start_row = Integer.parseInt(line2.split(" ")[0]);
			int start_col = Integer.parseInt(line2.split(" ")[1]);

			maze = new String[rows][cols]; // String array to log maze
			
			// Loop through the input maze
			for (int i = 0; i < rows; i++)
			{
				String new_row = inScan.nextLine();
				String [] char_row = new_row.split(" ");
				for (int j = 0; j < cols; j++)
				{
					// Print out the values and add them to the maze array
					System.out.print(char_row[j] + " ");
					maze[i][j] = char_row[j];
				}
				System.out.println();
			}

			System.out.println("\nSearching for solutions starting at (" + start_row + ", " + start_col + "):\n");

			beenThere = new String[rows][cols]; // Create new array same size as maze to keep track of route

			// Set the new route to the original maze
			for (int i = 0; i < rows; i++)
			{
				for (int j = 0; j < cols; j++)
				{
					beenThere[i][j] = false;
				}
			}

			solution = new Stack<String>();

			solution.push(new String("(" + start_row + ", " + start_col + ")"));
			beenThere[start_row][start_col] = true;

			doSearch(start_row, start_col);
		}
	}

	public void doSearch(int x, int y)
	{
		// If left is valid index
		int left = x - 1;
		if (left >= 0 && !beenThere[x - 1][y])
		{
			// Check left
			String left_char = maze[left][y];
			if (left_char.equals("2"))
			{
				solution.push(new String("(" + left + ", " + y + ")"));
				printSolution();
				solution.pop();
			}
			else if (left_char.equals("0"))
			{
				beenThere[left][y] = true;
				solution.push(new String("(" + left + ", " + y + ")"));
				doSearch(left, y);
				beenThere[left][y] = false;
				solution.pop();
			}
		}

		int top = y + 1;
		
	}
}