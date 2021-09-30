import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import java.util.Date;

public class MazeSolverBFS {
    
    public static void main(String[] args) {

        //Get input file from parameters
        String inputFile = args[0];

        //Load input file file
        String contents = "";

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

        double startTime = new Date().getTime();

        //Solve with BFS
        System.out.println("BREADTH FIRST SEARCH");

        ArrayList<Integer> bfsPath = mazeMatrix.solveBFS();

        System.out.println("PATH: " + bfsPath);
        System.out.println("PATH SIZE:" + mazeMatrix.getPathSize());
        System.out.println("OPTIMAL PATH: "+ mazeMatrix.findPath());


        double endTime = new Date().getTime();

        double timeTaken = endTime - startTime;

        System.out.println("TIME TAKEN: " + timeTaken + "ms");
        
    }
}
