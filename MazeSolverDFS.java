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

        
    }
}