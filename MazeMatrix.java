import java.util.Stack;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;
import java.util.LinkedList;



public class MazeMatrix {
    private Node[][] matrix;

    private Node startNode;
    private Node endNode;

    private int width;
    private int height;

    private int solvingSteps;

    public MazeMatrix(int height,int width,String openings){
        matrix = new Node[height][width];

        this.height = height;
        this.width = width;

        //Populate matrix with nodes
        for(int i=0;i<height;i++){
            for(int j=0;j<width;j++){
                matrix[i][j] = new Node((i*width)+j+1);

                matrix[i][j].setRow(i);
                matrix[i][j].setCol(j);

            }
        }

        char[] opens = openings.toCharArray();

        int rows = opens.length / width;
        for(int i =0;i<rows;i++){
            for(int j=0;j<width;j++){
                int postion = width*i + j;
                linkNodes(opens[postion],i,j);
            }

        }

              
    }

    public int getWidth() {
        return width;
    }

    public int getHeight(){
        return height;
    }

    private Node getNode(int row,int col){
        return matrix[row][col];
    }

    private void linkNodes(char opening,int i,int j){
        switch (opening){
            case '0':

            break;
            case '1':
                matrix[i][j].setRightNode(matrix[i][j+1]);
                matrix[i][j+1].setLeftNode(matrix[i][j]);
            break;
            case '2':
                matrix[i][j].setDownNode(matrix[i+1][j]);
                matrix[i+1][j].setUpNode(matrix[i][j]);
            break;
            case '3':
                matrix[i][j].setRightNode(matrix[i][j+1]);
                matrix[i][j+1].setLeftNode(matrix[i][j]);

                matrix[i][j].setDownNode(matrix[i+1][j]);
                matrix[i+1][j].setUpNode(matrix[i][j]);
            break;
        }  
    }

    public void setStartNode(int postion){
        int row = Math.floorDiv(postion,width);

        int col = postion % width;

        if( col != 0){
            col--;
        }

        //Calculate start node postion
        startNode = getNode(row, col);
    }

    public void setEndNode(int postion){
        int row = Math.floorDiv(postion,width);

        int col = postion % width;

        if( col != 0){
            col--;
        }

        endNode = getNode(row, col);
    }

    public int nodeConnections(int row,int col){
        return getNode(row, col).getCellOpenness();
    }

    public ArrayList<Integer> solveDFS(){
        Stack<Node> stack = new Stack<Node>();

        stack.push(startNode);
        startNode.setVisited();

        while(!stack.empty()){
            solvingSteps++;
            Node currentNode = stack.pop();

            System.out.println("CURRENT NODE: "+currentNode.getName());

            if (currentNode.getName() == endNode.getName()){
                break;
            }

            //Get all unvisted nodes
            ArrayList<Node> unvisited = currentNode.getAllUnivistedNodes();

            for(Node node : unvisited){
                
                node.setVisited();
                node.setParent(currentNode);

                //Push to stack
                stack.push(node);
            }
        }

        return findPath();
    }

    private ArrayList<Integer> findPath(){
        ArrayList<Integer> path = new ArrayList<>();

        //Walk back using parent
        Node presentNode = endNode;

        while(presentNode.getParent() != null){
            path.add(presentNode.getName());

            presentNode = presentNode.getParent();
        }

        path.add(startNode.getName());

        //Flip the path in reverse order
        Collections.reverse(path);

        return path;
    }

    public ArrayList<Integer> solveBFS(){
        Queue<Node> queue = new LinkedList<>();

        startNode.setVisited();
        queue.add(startNode);

        while(!queue.isEmpty()){
            solvingSteps++;
            Node currentNode = queue.poll();

            if (currentNode.getName() == endNode.getName()){
                break;
            }

            //Get all unvisted nodes
            ArrayList<Node> unvisited = currentNode.getAllUnivistedNodes();

            for(Node node : unvisited){
                node.setVisited();
                node.setParent(currentNode);

                //Add to queue
                queue.add(node);
            }

        }

        return findPath();
    }

    public void resetPath(){
        solvingSteps = 0;
        for(int i=0;i<height;i++){
            for(int j=0;j<width;j++){
                matrix[i][j].setParent(null);
                matrix[i][j].unsetVisit();
            }
        }
    }

    public int getSolveSteps(){
        return solvingSteps;
    }

}
