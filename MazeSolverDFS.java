import java.util.*;
import java.io.*;

public class MazeSolverDFS 
{
    public static void main(String[] args) throws IOException
    {
        System.out.println("MazeSolverDFS");

        // Read maze from input File
        BufferedReader inputFile = new BufferedReader(new FileReader(args[0]));
        String input = inputFile.readLine();

        inputFile.close();

        System.out.println(input);

        // Split input into relevant parts
        String[] inputParts = input.split(":");
        String[] widthAndLength = inputParts[0].split(",");

        int width = Integer.valueOf(widthAndLength[0]);
        int length = Integer.valueOf(widthAndLength[1]);
        

        int startNode = Integer.valueOf(inputParts[1]);
        int endNode = Integer.valueOf(inputParts[2]);

        String cellOpennessList = inputParts[3];

        // Create Node Matrix
        Node[][] mazeMatrix = new Node[width][length];

        // Populate Node Matrix and assign allowed directions
        int nodeNumber = 0;
        for (int i = 0; i < width; i++) 
        {
            for (int j = 0; j < length; j++) 
            {   
                nodeNumber++;
                mazeMatrix[i][j] = new Node(i, j, nodeNumber);

                // Assign cell openness
                mazeMatrix[i][j].setCellOpenness(cellOpennessList.charAt(nodeNumber-1));

                // Assign allowed directions
                switch (cellOpennessList.charAt(nodeNumber-1)) 
                {
                    // Right
                    case '1':
                        mazeMatrix[i][j].setRightNode(mazeMatrix[i][j+1]);
                        break;
                    // Down
                    case '2':
                        mazeMatrix[i][j].setDownNode(mazeMatrix[i+1][j]);
                        break;
                    // Both
                    case '3':
                        mazeMatrix[i][j].setRightNode(mazeMatrix[i][j+1]);
                        mazeMatrix[i][j].setDownNode(mazeMatrix[i+1][j]);
                     break;
                }

                // Set start Node
                if(nodeNumber == startNode)
                {
                    mazeMatrix[i][j].setStartNode();
                }
                // Set end Node
                else if(nodeNumber == endNode)
                {
                    mazeMatrix[i][j].setEndNode();
                }
            } 
        }


    }
}