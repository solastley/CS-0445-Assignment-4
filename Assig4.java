// Solomon Astley, CS 0445 Ramirez, Recitation Tuesday 10:00
// Date : March 15, 2016
// Assignment 4 - This program is a maze solving program that utilizes backtracking

import java.util.*;
import java.io.*;
import java.lang.*;
import java.math.*;

public class Assig4
{
	// Two ArrayLists of strings. One to keep track of current solution and one
	// to keep track of the shortest solution
	private ArrayList<String> solution, short_array;
	// A map of boolean values to keep track of whether or not a location has
	// been visited already while looking for solutions
	private boolean [][] beenThere;
	// Two maps of string values to visibly keep track of the current solution
	// and the shortest solution
	private String [][] maze, short_solution;
	// Instance variables to keep track of total number of solutions, recursive calls,
	// and the shortest number of solutions
	private int num_solutions, shortest;
	private double rec_calls;

	public static void main(String[] args) throws IOException
	{
		String filename = args[0]; // Read filename from command line
		new Assig4(filename);
	}

	public Assig4(String filename) throws IOException
	{				
		File inFile = new File(filename);
		Scanner inScan = new Scanner(inFile); // Create scanner for file

		while (inScan.hasNext()) // While loop for multiple input mazes
		{
			System.out.println("-------------------------\n");
			System.out.println("The new board is:");

			num_solutions = 0;
			rec_calls = 0;
			shortest = 0;

			// Read in the first two numbers for the maze, number of rows and cols
			String line1 = inScan.nextLine();
			int rows = Integer.parseInt(line1.split(" ")[0]);
			int cols = Integer.parseInt(line1.split(" ")[1]);

			// Read in the second two numbers for the maze, starting position in the maze
			String line2 = inScan.nextLine();
			int start_row = Integer.parseInt(line2.split(" ")[0]);
			int start_col = Integer.parseInt(line2.split(" ")[1]);

			maze = new String[rows][cols]; // String array to log maze
			short_solution = new String[rows][cols];
			
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

			System.out.println("Searching for solutions starting at (" + start_row + ", " + start_col + "):");

			beenThere = new boolean[rows][cols]; // Create new array same size as maze to keep track of
												// already visited locations

			// Set the new route to the original maze
			for (int i = 0; i < rows; i++)
			{
				for (int j = 0; j < cols; j++)
				{
					beenThere[i][j] = false;
				}
			}

			// Initialize the two ArrayLists
			solution = new ArrayList<String>();
			short_array = new ArrayList<String>();

			// The 
			solution.add(new String("(" + start_row + ", " + start_col + ")"));
			beenThere[start_row][start_col] = true;

			doSearch(start_row, start_col);

			System.out.println("\nThere were a total of " + num_solutions + " solutions found:");
			System.out.print("A total of ");
			System.out.format("%.0f", rec_calls);
			System.out.println(" recursive calls were made");
			if (shortest > 0)
			{
				System.out.println("The shortest solution had " + shortest + " segments");
				for (int i = 0; i < short_solution.length; i++)
				{
					for (int j = 0; j < short_solution[0].length; j++)
					{
						System.out.print(short_solution[i][j] + " ");
					}
					System.out.println();
				}
				System.out.print("Path: ");
				for (int i = 0; i < short_array.size(); i++)
				{
					System.out.print(short_array.get(i) + " ");
				}
				System.out.println();
			}
		}
	}

	public void doSearch(int x, int y)
	{
		rec_calls = rec_calls + 1; // Increment rec_calls each time a recursive call is made

		// If left is valid index
		int left = x - 1;
		if (left >= 0 && !beenThere[left][y])
		{
			// Check left
			String left_char = maze[left][y];
			if (left_char.equals("2"))
			{
				solution.add(new String("(" + left + ", " + y + ")"));
				printSolution();
				solution.remove(solution.size() - 1);
			}
			else if (left_char.equals("0"))
			{
				beenThere[left][y] = true;
				solution.add(new String("(" + left + ", " + y + ")"));
				doSearch(left, y);
				beenThere[left][y] = false;
				solution.remove(solution.size() - 1);
			}
		}

		// If top is valid index
		int top = y - 1;
		if (top >= 0 && !beenThere[x][top])
		{
			// Check top
			String top_char = maze[x][top];
			if (top_char.equals("2"))
			{
				solution.add(new String("(" + x + ", " + top + ")"));
				printSolution();
				solution.remove(solution.size() - 1);
			}
			else if (top_char.equals("0"))
			{
				beenThere[x][top] = true;
				solution.add(new String("(" + x + ", " + top + ")"));
				doSearch(x, top);
				beenThere[x][top] = false;
				solution.remove(solution.size() - 1);
			}
		}

		// If right is valid index
		int right = x + 1;
		if (right < maze.length && !beenThere[right][y])
		{
			// Check right
			String right_char = maze[right][y];
			if (right_char.equals("2"))
			{
				solution.add(new String("(" + right + ", " + y + ")"));
				printSolution();
				solution.remove(solution.size() - 1);
			}
			else if (right_char.equals("0"))
			{
				beenThere[right][y] = true;
				solution.add(new String("(" + right + ", " + y + ")"));
				doSearch(right, y);
				beenThere[right][y] = false;
				solution.remove(solution.size() - 1);
			}
		}

		// If bottom is valid index
		int bottom = y + 1;
		if (bottom < maze[0].length && !beenThere[x][bottom])
		{
			// Check bottom
			String bottom_char = maze[x][bottom];
			if (bottom_char.equals("2"))
			{
				solution.add(new String("(" + x + ", " + bottom + ")"));
				printSolution();
				solution.remove(solution.size() - 1);
			}
			else if (bottom_char.equals("0"))
			{
				beenThere[x][bottom] = true;
				solution.add(new String("(" + x + ", " + bottom + ")"));
				doSearch(x, bottom);
				beenThere[x][bottom] = false;
				solution.remove(solution.size() - 1);
			}
		}
	}

	public void printSolution()
	{
		num_solutions++;

		System.out.println("\nSolution found with " + solution.size() + " segments");

		String [][] map = new String[maze.length][maze[0].length];
		for (int i = 0; i < maze.length; i++)
		{
			for (int j = 0; j < maze[0].length; j++)
			{
				map[i][j] = maze[i][j];
			}
		}

		for (int i = 0; i < solution.size() - 1; i++)
		{
			String coord = solution.get(i);
			coord = coord.substring(1, coord.length() - 1);
			String [] val_array = coord.split(", ");
			int x = Integer.parseInt(val_array[0]);
			int y = Integer.parseInt(val_array[1]);
			map[x][y] = "x";
		}

		if (shortest == 0 || solution.size() < shortest)
		{
			shortest = solution.size();
			for (int i = 0; i < map.length; i++)
			{
				for (int j = 0; j < map[0].length; j++)
				{
					short_solution[i][j] = map[i][j];
				}
			}
			short_array.clear();
			for (int i = 0; i < solution.size(); i++)
			{
				short_array.add(solution.get(i));
			}
		}

		for (int i = 0; i < maze.length; i++)
		{
			for (int j = 0; j < maze[0].length; j++)
			{
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}

		System.out.print("Path: ");
		for (int i = 0; i < solution.size(); i++)
		{
			System.out.print(solution.get(i) + " ");
		}
		System.out.println();
	}
}