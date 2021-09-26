
import java.util.*;
import java.io.*;

public class MazeGenerator 
{
    public static void main(String[] args) throws IOException
    {
        System.out.println("MazeGenerator");

        int width = Integer.valueOf(args[0]);
        int length =Integer.valueOf(args[1]);

        // Create Node Matrix
        Node[][] mazeMatrix = new Node[width][length];


        // Populate Node Matrix
        int nodeNumber = 0;
        for (int i = 0; i < width; i++) 
        {
            for (int j = 0; j < length; j++) 
            {
                nodeNumber++;

                // Determines a nodes possible directions to travel in
                Stack<Integer> possibleDirections = new Stack<Integer>();
                // Checks to see if the node is not on the bottom row
                if(i < width-1)
                {
                    possibleDirections.push(3); // Down:3
                    //mazeMatrix[i][j].setDownNode(mazeMatrix[i+1][j]);
                }

                // Checks to see if the Node is not on the top row
                if(i > 0)
                {
                    possibleDirections.push(1); // Up:1
                    //mazeMatrix[i][j].setUpNode(mazeMatrix[i-1][j]);
                }

                // Checks to see if the Node is not on the right most column 
                if(j < length - 1)
                {
                    possibleDirections.push(2); // Right:2
                    //mazeMatrix[i][j].setRightNode(mazeMatrix[i][j+1]);
                }

                // Checks to see if the Node is not on the left most column 
                if(j > 0)
                {
                    possibleDirections.push(4); // Left:4
                    //mazeMatrix[i][j].setLeftNode(mazeMatrix[i][j-1]);
                }
                Collections.shuffle(possibleDirections);

                mazeMatrix[i][j] = new Node(i, j, nodeNumber, possibleDirections);
            } 
        }

        // Generate random starting Node

        Random rand = new Random(); 
        int startWidth = rand.nextInt(width); 
        int startLength = rand.nextInt(length);
        mazeMatrix[startWidth][startLength].setStartNode();

        
        // DFS Maze Generation setup

        boolean[] visited = new boolean[width*length];
        for (int i = 0; i < width*length; i++) 
        {   
            visited[i] = false;
        }

        int vertex = 0;
        int row = startWidth;
        int col = startLength;
        int[] visitedRow = new int[width*length];
        int[] visitedCol = new int[width*length];



        // Execute DFS Maze Generation 
        
        while(vertex < width*length)
        {
            // Increase vertex counter
            vertex++;

            // Set current Node as visited
            mazeMatrix[row][col].setVisited();
            visited[mazeMatrix[row][col].getName()-1] = true;
            visitedRow[vertex-1] = row;
            visitedCol[vertex-1] = col;

            //System.out.println("Vertex: " + vertex + "    Current Node: " + row + " " + col + "  " + mazeMatrix[row][col].getName());
            //System.out.println();

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
                    //System.out.println("direction: " + direction);
                }
                
                // Check if direction is possible to travel to    
                switch(direction) 
                {
                    // Up
                    case 1:
                        if (mazeMatrix[row-1][col].getVisited() == false) 
                        {
                            nodeFound = true;

                            mazeMatrix[row][col].setUpNode(mazeMatrix[row-1][col]);
                            mazeMatrix[row-1][col].setDownNode(mazeMatrix[row][col]);

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

                            mazeMatrix[row][col].setRightNode(mazeMatrix[row][col+1]);
                            mazeMatrix[row][col+1].setLeftNode(mazeMatrix[row][col]);

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

                            mazeMatrix[row][col].setDownNode(mazeMatrix[row+1][col]);
                            mazeMatrix[row+1][col].setUpNode(mazeMatrix[row][col]);

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

                            mazeMatrix[row][col].setLeftNode(mazeMatrix[row][col-1]);
                            mazeMatrix[row][col-1].setRightNode(mazeMatrix[row][col]);

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
                //System.out.println("No Nodes");
                
                for(int stepVisited = vertex-2; stepVisited >= 0 && nodeFound == false; stepVisited--)
                {
                    //System.out.println(stepVisited + "  " + mazeMatrix[visitedRow[stepVisited]][visitedCol[stepVisited]].getName());

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

                                    mazeMatrix[visitedRow[stepVisited]][visitedCol[stepVisited]].setUpNode(mazeMatrix[visitedRow[stepVisited]-1][visitedCol[stepVisited]]);
                                    mazeMatrix[visitedRow[stepVisited]-1][visitedCol[stepVisited]].setDownNode(mazeMatrix[visitedRow[stepVisited]][visitedCol[stepVisited]]);

                                    row =   mazeMatrix[visitedRow[stepVisited]-1][visitedCol[stepVisited]].getRow();
                                    col =   mazeMatrix[visitedRow[stepVisited]-1][visitedCol[stepVisited]].getCol();
                                }
                                break;

                            // RIGHT
                            case 2:
                                if(mazeMatrix[visitedRow[stepVisited]][visitedCol[stepVisited]+1].getVisited() == false)
                                {
                                    nodeFound = true;

                                    mazeMatrix[visitedRow[stepVisited]][visitedCol[stepVisited]].setRightNode(mazeMatrix[visitedRow[stepVisited]][visitedCol[stepVisited]+1]);
                                    mazeMatrix[visitedRow[stepVisited]][visitedCol[stepVisited]+1].setLeftNode(mazeMatrix[visitedRow[stepVisited]][visitedCol[stepVisited]]);

                                    row =  mazeMatrix[visitedRow[stepVisited]][visitedCol[stepVisited]+1].getRow();
                                    col =  mazeMatrix[visitedRow[stepVisited]][visitedCol[stepVisited]+1].getCol();
                                }
                                break;

                            // DOWN
                            case 3:
                                if(mazeMatrix[visitedRow[stepVisited]+1][visitedCol[stepVisited]].getVisited() == false)
                                {
                                    nodeFound = true;

                                    mazeMatrix[visitedRow[stepVisited]][visitedCol[stepVisited]].setDownNode(mazeMatrix[visitedRow[stepVisited]+1][visitedCol[stepVisited]]);
                                    mazeMatrix[visitedRow[stepVisited]+1][visitedCol[stepVisited]].setUpNode(mazeMatrix[visitedRow[stepVisited]][visitedCol[stepVisited]]);

                                    row =  mazeMatrix[visitedRow[stepVisited]+1][visitedCol[stepVisited]].getRow();
                                    col =  mazeMatrix[visitedRow[stepVisited]+1][visitedCol[stepVisited]].getCol();
                                }
                                break;

                            // LEFT
                            case 4:
                                if(mazeMatrix[visitedRow[stepVisited]][visitedCol[stepVisited]-1].getVisited() == false)
                                {
                                    nodeFound = true;

                                    mazeMatrix[visitedRow[stepVisited]][visitedCol[stepVisited]].setLeftNode(mazeMatrix[visitedRow[stepVisited]][visitedCol[stepVisited]-1]);
                                    mazeMatrix[visitedRow[stepVisited]][visitedCol[stepVisited]-1].setRightNode(mazeMatrix[visitedRow[stepVisited]][visitedCol[stepVisited]]);

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
        String output = width + "," + length + ":" + mazeMatrix[startWidth][startLength].getName() + ":" + mazeMatrix[visitedRow[length*width-1]][visitedCol[length*width-1]].getName() + ":";
        
        for (int i = 0; i < width; i++) 
        {
            for (int j = 0; j < length; j++) 
            {
                output += mazeMatrix[i][j].calculateCellOpenness(); 
            } 
        }

        System.out.println(output);

        // Write to file
        FileWriter outputFile = new FileWriter(args[2],false);
        outputFile.write(output);
        outputFile.flush();
        outputFile.close();


    } 



}
