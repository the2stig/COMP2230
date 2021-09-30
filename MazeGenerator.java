
import java.util.*;
import java.io.*;

public class MazeGenerator 
{
    public static void main(String[] args) throws IOException
    {
        System.out.println("MazeGenerator");

        int width = Integer.valueOf(args[0]);
        int height = Integer.valueOf(args[1]);

        // Create Node Matrix
        Node[][] mazeMatrix = new Node[width][height];

        // Populate Node Matrix
        Random rand = new Random();
        rand.setSeed(1); 
        
        int nodeNumber = 0;
        for (int i = 0; i < width; i++) 
        {
            for (int j = 0; j < height; j++) 
            {
                nodeNumber++;

                // Determines a nodes possible directions to travel in
                Stack<Integer> possibleDirections = new Stack<Integer>();

                // Checks to see if the node is not on the bottom row
                if(i < width-1)
                {
                    possibleDirections.push(3); // Down:3
                }

                // Checks to see if the Node is not on the top row
                if(i > 0)
                {
                    possibleDirections.push(1); // Up:1
                }

                // Checks to see if the Node is not on the right most column 
                if(j < height - 1)
                {
                    possibleDirections.push(2); // Right:2
                }

                // Checks to see if the Node is not on the left most column 
                if(j > 0)
                {
                    possibleDirections.push(4); // Left:4
                }

                // Randomise directions
                Collections.shuffle(possibleDirections, rand);

                mazeMatrix[i][j] = new Node(i, j, nodeNumber, possibleDirections);
            } 
        }

        // Generate random starting Node
        int startWidth = rand.nextInt(width); 
        int startHeight = rand.nextInt(height);
        mazeMatrix[startWidth][startHeight].setStartNode();

        // DFS Maze Generation setup
        int vertex = 0;
        int row = startWidth;
        int col = startHeight;
        int[] visitedRow = new int[width*height];
        int[] visitedCol = new int[width*height];

        // Execute DFS Maze Generation 
        while(vertex < width*height)
        {
            // Increase vertex counter
            vertex++;

            // Set current Node as visited
            mazeMatrix[row][col].setVisited();
            visitedRow[vertex-1] = row;
            visitedCol[vertex-1] = col;

            // Find next valid node 
            boolean nodeFound = false;     
            while(nodeFound == false)
            {
                int direction = 0;

                if(mazeMatrix[row][col].isEmpty())
                {
                    break;
                }
                else
                {
                    direction = mazeMatrix[row][col].getDirection();
                }
                
                // Check if direction is possible to travel to    
                switch(direction) 
                {
                    // Up
                    case 1:
                        if (mazeMatrix[row-1][col].getVisited() == false) 
                        {
                            nodeFound = true;

                            mazeMatrix[row-1][col].downPossible();

                            int tempRow = row;
                            row = mazeMatrix[row-1][col].getRow();
                            col = mazeMatrix[tempRow-1][col].getCol();
                        }
                        break;

                    // Right
                    case 2:
                        if (mazeMatrix[row][col+1].getVisited() == false) 
                        {
                            nodeFound = true;

                            mazeMatrix[row][col].rightPossible();

                            int tempRow = row;
                            row = mazeMatrix[row][col+1].getRow();
                            col = mazeMatrix[tempRow][col+1].getCol();
                        }
                        break;

                    // Down
                    case 3:
                        if (mazeMatrix[row+1][col].getVisited() == false) 
                        {
                            nodeFound = true;

                            mazeMatrix[row][col].downPossible();

                            int tempRow = row;
                            row = mazeMatrix[row+1][col].getRow();
                            col = mazeMatrix[tempRow+1][col].getCol();
                        }

                        break;

                    // Left
                    case 4:
                        if (mazeMatrix[row][col-1].getVisited() == false) 
                        {
                            nodeFound = true;

                            mazeMatrix[row][col-1].rightPossible();

                            int tempRow = row;
                            row = mazeMatrix[row][col-1].getRow();
                            col = mazeMatrix[tempRow][col-1].getCol();
                        }
                        break;  
                }
            }   

            
            // No valid nodes to travel too
            if(nodeFound == false )
            {                
                for(int stepVisited = vertex-2; stepVisited >= 0 && nodeFound == false; stepVisited--)
                {
                    while(mazeMatrix[visitedRow[stepVisited]][visitedCol[stepVisited]].isEmpty() == false && nodeFound == false)
                    {
                       int newDirection = mazeMatrix[visitedRow[stepVisited]][visitedCol[stepVisited]].getDirection();

                       switch(newDirection)
                       {    
                            // UP
                            case 1:
                                if(mazeMatrix[visitedRow[stepVisited]-1][visitedCol[stepVisited]].getVisited() == false)
                                {
                                    nodeFound = true;

                                    mazeMatrix[visitedRow[stepVisited]-1][visitedCol[stepVisited]].downPossible();


                                    row =   mazeMatrix[visitedRow[stepVisited]-1][visitedCol[stepVisited]].getRow();
                                    col =   mazeMatrix[visitedRow[stepVisited]-1][visitedCol[stepVisited]].getCol();
                                }
                                break;

                            // RIGHT
                            case 2:
                                if(mazeMatrix[visitedRow[stepVisited]][visitedCol[stepVisited]+1].getVisited() == false)
                                {
                                    nodeFound = true;

                                    mazeMatrix[visitedRow[stepVisited]][visitedCol[stepVisited]].rightPossible();

                                    row =  mazeMatrix[visitedRow[stepVisited]][visitedCol[stepVisited]+1].getRow();
                                    col =  mazeMatrix[visitedRow[stepVisited]][visitedCol[stepVisited]+1].getCol();
                                }
                                break;

                            // DOWN
                            case 3:
                                if(mazeMatrix[visitedRow[stepVisited]+1][visitedCol[stepVisited]].getVisited() == false)
                                {
                                    nodeFound = true;

                                    mazeMatrix[visitedRow[stepVisited]][visitedCol[stepVisited]].downPossible();

                                    row =  mazeMatrix[visitedRow[stepVisited]+1][visitedCol[stepVisited]].getRow();
                                    col =  mazeMatrix[visitedRow[stepVisited]+1][visitedCol[stepVisited]].getCol();
                                }
                                break;

                            // LEFT
                            case 4:
                                if(mazeMatrix[visitedRow[stepVisited]][visitedCol[stepVisited]-1].getVisited() == false)
                                {
                                    nodeFound = true;

                                    mazeMatrix[visitedRow[stepVisited]][visitedCol[stepVisited]-1].rightPossible();

                                    row =  mazeMatrix[visitedRow[stepVisited]][visitedCol[stepVisited]-1].getRow();
                                    col =  mazeMatrix[visitedRow[stepVisited]][visitedCol[stepVisited]-1].getCol();
                                }
                                break;
                       }
                    }



                }

            }

        }

        // Generate output string
        String output = width + "," + height + ":" + mazeMatrix[startWidth][startHeight].getName() + ":" + mazeMatrix[visitedRow[height*width-1]][visitedCol[height*width-1]].getName() + ":";
        
        
        for (int i = 0; i < width; i++) 
        {
            for (int j = 0; j < height; j++) 
            {
                output += mazeMatrix[i][j].getCellOpenness(); 
            } 
        }
        
        // Standard Output
        System.out.println(output);

        // Write to file
        FileWriter outputFile = new FileWriter(args[2],false);
        outputFile.write(output);
        outputFile.flush();
        outputFile.close();

    } 



}
