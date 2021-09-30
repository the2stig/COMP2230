import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class MazeSolverDFS 
{
    public static void main(String[] args) 
    {
        //Get input file from parameters
        String inputFile = "maze.dat";

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

        System.out.println("n: " + widthLength[0]);
        System.out.println("m: " + widthLength[1]);

        System.out.println("start Node: " + startNode);
        System.out.println("end Node: " + endNode);

        System.out.println("cell Openness List: " + cellOpennessList);

        int mazeWidth = Integer.parseInt(widthLength[0]);
        int mazeHeight = Integer.parseInt(widthLength[1]);

        int startPostion = Integer.parseInt(startNode);
        int endPostion = Integer.parseInt(endNode);

        //Create a new matrix and load node into it
        MazeMatrix mazeMatrix = new MazeMatrix(mazeWidth,mazeHeight,cellOpennessList);

        //Set start
        mazeMatrix.setStartNode(startPostion);

        //Set End Node
        mazeMatrix.setEndNode(endPostion);

       

        //Solve with DFS
        System.out.println("DEPTH FIRST SEARCH");
        
        ArrayList<Integer> dfsPath = mazeMatrix.solveDFS();
        System.out.println(dfsPath);
        System.out.println(mazeMatrix.getSolveSteps());
        System.out.println(dfsPath.size());


        mazeMatrix.resetPath();


        //Solve with BFS
        System.out.println("BREADTH FIRST SEARCH");

        ArrayList<Integer> bfsPath = mazeMatrix.solveBFS();

        System.out.println(bfsPath);
        System.out.println(mazeMatrix.getSolveSteps());
        System.out.println(bfsPath.size());



    }
}

