import java.util.Stack;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;
import java.util.LinkedList;

// Written by   : Kyle Beattie c3303374 and Joshua Flynn c3304206
// Course       : COMP2230
// Modified     : 08/10/2021
// 
// Program Description:
// Helper class for maze solvers

//Helper class for maze solver
public class MazeMatrix {

    //2D array to store location of each cell (node)
    private Node[][] matrix;

    private Node startNode; //Start postion in maze
    private Node endNode; //Finish postion in maze

    private int width;  //Width of maze
    private int height; //Height of maze

    private int pathSize = -1; //Number of solving steps

    //Constructor that takes the 
    //Openings should be in the form of 1,2,1,0,1,0 as in the maze file
    //Start and end postion are numbered left to right, up to down
    public MazeMatrix(int height,int width,String openings,int startPostion, int endPostion){
        //Make new 2d Node array
        matrix = new Node[height][width];

        //Set length of maze
        this.height = height;
        this.width = width;

        //Populate matrix with nodes
        for(int i=0;i<height;i++){
            for(int j=0;j<width;j++){
                //Setup new node
                matrix[i][j] = new Node((i*width)+j+1);

                //If current node name is the start postion in maze
                if(matrix[i][j].getName() == startPostion){
                    startNode = matrix[i][j]; //Set start node
                }

                //If current node name is the end postion in maze
                if(matrix[i][j].getName() == endPostion){
                    endNode = matrix[i][j]; //set end node
                }

                //Set node row and column postion
                matrix[i][j].setRow(i);
                matrix[i][j].setCol(j);

            }
        }

        //Convert string to char array for processing
        char[] opens = openings.toCharArray();

        int rows = opens.length / width; //Row length
        for(int i =0;i<rows;i++){ //Loop rows 
            for(int j=0;j<width;j++){ //Loop row
                int postion = width*i + j; //Calculate postion
                linkNodes(opens[postion],i,j); //Link sourronding nodes
            }

        }

              
    }

    //Return width of maze
    public int getWidth() {
        return width;
    }

    //Return heigh of maze
    public int getHeight(){
        return height;
    }

    //Return a node using row and column
    private Node getNode(int row,int col){
        return matrix[row][col];
    }

    //Links nodes according to opeining provided
    // i is row 
    //j is column
    private void linkNodes(char opening,int i,int j){
        switch (opening){
            case '0':
                //No operation needed
            break;
            case '1':
                //Attach right node to current node
                matrix[i][j].setRightNode(matrix[i][j+1]);  
                matrix[i][j+1].setLeftNode(matrix[i][j]);   //Attach back
            break;
            case '2':
                //Attach down node to current node
                matrix[i][j].setDownNode(matrix[i+1][j]);
                matrix[i+1][j].setUpNode(matrix[i][j]);     //Attach back
            break;
            case '3':
                //Attach right node to current node
                matrix[i][j].setRightNode(matrix[i][j+1]);
                matrix[i][j+1].setLeftNode(matrix[i][j]);   //Attach back

                //Attach down node to current node
                matrix[i][j].setDownNode(matrix[i+1][j]);
                matrix[i+1][j].setUpNode(matrix[i][j]);     //Attach back
            break;
        }  
    }

    //Calulates and retrun the cell opening value
    public int nodeConnections(int row,int col){
        return getNode(row, col).getCellOpenness();
    }

    //Depth first search solver for the maze returns path with cycles
    public ArrayList<Integer> solveDFS(){
        //Paths
        ArrayList<Integer> pathSteps = new ArrayList<>();

        //Stack to store current state
        Stack<Node> stack = new Stack<Node>();

        stack.push(startNode);  //Push start node to stack
        startNode.setVisited(); //Update start node to be visited

        //Loop until stack is empty
        while(!stack.empty()){
            pathSize++; //Increase path size
            
            Node currentNode = stack.pop(); //Get node
            pathSteps.add(currentNode.getName()); //Add to path
            currentNode.setVisited();  //Set current node visited

            //Is current node end node?
            if (currentNode.getName() == endNode.getName()){
                break;  //Stop
            }

            //Get all unvisted nodes
            ArrayList<Node> unvisited = currentNode.getAllUnivistedNodes();

            //Adding cycle to the stack
            if(currentNode.getParent() != null && unvisited.size() == 0) 
            {
                stack.push(currentNode.getParent());
            }

            //loop all univisted nodes surronding current node
            for(Node node : unvisited){
                node.setParent(currentNode); //Set parent to current used to find optimal path

                //Push to stack
                stack.push(node); //Push to stack
            }
        }

        return pathSteps;  //Return path includes cycles
    }

    //Return optimal path for maze.
    //Note: that the maze must have ethier solveDFS or solveBFS executed before running this method
    public ArrayList<Integer> findPath(){
        //Path
        ArrayList<Integer> path = new ArrayList<>();

        //Walk back using parent node
        Node presentNode = endNode;

        //Loop until hit back to start node
        while(presentNode.getParent() != null){
            path.add(presentNode.getName());

            presentNode = presentNode.getParent();
        }

        //Add start node to path
        path.add(startNode.getName());

        //Flip the path in reverse order
        Collections.reverse(path);

        return path;
    }

    //Return a path for maze using breadth first search technique
    public ArrayList<Integer> solveBFS(){
        //Path
        ArrayList<Integer> pathSteps = new ArrayList<>();

        //Queue used for searching
        Queue<Node> queue = new LinkedList<>();

        //Setup for searching
        startNode.setVisited();
        queue.add(startNode);

        //Loop until queue is empty
        while(!queue.isEmpty()){
            pathSize++; //Increase path size

            Node currentNode = queue.poll(); //Get current node
            
            pathSteps.add(currentNode.getName());
            currentNode.setVisited();

            //Is current node end node?
            if (currentNode.getName() == endNode.getName()){
                break; //Stop searching
            }

            //Get all unvisted nodes
            ArrayList<Node> unvisited = currentNode.getAllUnivistedNodes();

            //Must be flipped as unvisted was ordered for depth first search
            Collections.reverse(unvisited);

            //Loop unvisted
            for(Node node : unvisited){
                //Update parent
                node.setParent(currentNode);    

                //Add to queue
                queue.add(node);
            }
        }

        return pathSteps; //Return path
    }

    //Return path size
    public int getPathSize(){
        return pathSize;
    }

}
