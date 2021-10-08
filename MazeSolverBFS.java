import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

// Written by   : Kyle Beattie c3303374 and Joshua Flynn c3304206
// Course       : COMP2230
// Modified     : 08/10/2021
// 
// Program Description:
// Load file and parse maze. Solves maze using breadth first search and display results.

public class MazeSolverBFS {
    
    public static void main(String[] args) {

        //Get input file from parameters
        String inputFile = args[0];

        //Load input file file
        String contents = "";

        //get contents
        try{
            contents = new String(Files.readAllBytes(Paths.get(inputFile)));
        }catch(IOException e){
            System.out.println("Invalid file");
        }

        //Extract required information from file
        String[] parameters = contents.split(":");
        String[] widthLength = parameters[0].split(",");

        

        String startNode = parameters[1];
        String endNode = parameters[2];

        String cellOpennessList = parameters[3];

        System.out.println(contents + "\n");

        int mazeWidth = Integer.parseInt(widthLength[0]);
        int mazeHeight = Integer.parseInt(widthLength[1]);

        int startPostion = Integer.parseInt(startNode);
        int endPostion = Integer.parseInt(endNode);

        //Create a new matrix and load node into it
        MazeMatrix mazeMatrix = new MazeMatrix(mazeWidth,mazeHeight,cellOpennessList,startPostion,endPostion);

        
        System.out.println("BREADTH FIRST SEARCH");

        //Start clock
        double startTime = System.nanoTime();

        //Solve with BFS
        ArrayList<Integer> bfsPath = mazeMatrix.solveBFS();

        double endTime = System.nanoTime();
        //End clock

        //Print results
        System.out.println("PATH: " + bfsPath);
        System.out.println("PATH STEPS: " + mazeMatrix.getPathSize());
        System.out.println("OPTIMAL PATH: "+ mazeMatrix.findPath());

        double timeTaken = (endTime - startTime) / 1000000;

        System.out.println("TIME TAKEN: " + timeTaken + " ms");
        
    }
}
