import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MazeSolverDFS 
{
    public static void main(String[] args) 
    {
        //Get input file from parameters
        String inputFile = "out.dat";

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

        //Create a new matrix and load node into it
        MazeMatrix mazeMatrix = new MazeMatrix(mazeWidth,mazeHeight);

        //Load cell openings 
        mazeMatrix.addCellOpenings(cellOpennessList);     

        //Check nodes connections
        System.out.println(mazeMatrix.nodeConnections(0, 0));

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
                        mazeMatrix[i][j+1].setLeftNode(mazeMatrix[i][j]);
                        break;
                    // Down
                    case '2':
                        mazeMatrix[i][j].setDownNode(mazeMatrix[i+1][j]);
                        mazeMatrix[i+1][j].setUpNode(mazeMatrix[i][j]);
                        break;
                    // Both
                    case '3':
                        mazeMatrix[i][j].setRightNode(mazeMatrix[i][j+1]);
                        mazeMatrix[i][j+1].setLeftNode(mazeMatrix[i][j]);

                        mazeMatrix[i][j].setDownNode(mazeMatrix[i+1][j]);
                        mazeMatrix[i+1][j].setUpNode(mazeMatrix[i][j]);
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

