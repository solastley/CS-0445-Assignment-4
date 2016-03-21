// Solomon Astley, CS 0445 Ramirez, Recitation Tuesday 10:00
// Date : March 15, 2016
// Assignment 4 - This program is a maze solving program that utilizes backtracking

import java.util.*;
import java.io.*;

public class Assig4
{
	private ArrayList<String> solution;
	private String [][] route;

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

			String [][] maze = new String[rows][cols]; // String array to log maze
			
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

			route = new String[rows][cols]; // Create new array same size as maze to keep track of route
			boolean done_searching = false;
			while (!done_searching)
			{
				// Set the new route to the original maze
				for (int i = 0; i < rows; i++)
				{
					for (int j = 0; j < cols; j++)
					{
						route[i][j] = maze[i][j];
					}
				}

				solution = new ArrayList<Integer>();
				boolean found_solution = doSearch(start_row, start_col);
			}
		}
	}

	public boolean doSearch(int x, int y)
	{
		if (route[x][y].equals("2"))
		{
			solution.add(new String("(" + x + ", " + y + ")"));
			return true;
		}
		else
		{
			
		}
	}
}