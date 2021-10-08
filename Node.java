import java.util.*;

// Written by   : Kyle Beattie c3303374 and Joshua Flynn c3304206
// Course       : COMP2230
// Modified     : 08/10/2021
// 
// Program Description:
// Node class stores necessary data each cell in a maze matrix
// Other methods to assists in solving or generating maze

public class Node {
 
  private Node right; // Right of current node
  private Node left; // left of current node
  private Node up; // Up of current node
  private Node down; // Down of current node

  private Node parent; // Parent of current node
  private int cellOpenness; // Openiness must be on these value 0,1,2,3
  private int row; // Node row postion
  private int col; // Node column postion
  private int name; // Name of node can be reffered as id

  private boolean startNode; // is start node?
  private boolean endNode; // is end node?
  private boolean visited; // been visted?


  private Stack<Integer> possibleDirections; // List of node possible direction up(1),down(3),left(4), right(2)

  // Setup node with name
  public Node(int id){
    this.name = id;
  }

  // Constructor
  public Node(int tRow, int tCol, int index, Stack<Integer> tPossibleDirections) 
  {
    this.right = null;
    this.left = null;
    this.up = null;
    this.down = null;
    this.cellOpenness = 0;
    this.row = tRow;
    this.col = tCol;
    this.name = index;
    this.startNode = false;
    this.endNode = false;
    this.possibleDirections = tPossibleDirections;
  }

  // Constructor
  public Node(int tRow, int tCol, int index) 
  {
    this.right = null;
    this.left = null;
    this.up = null;
    this.down = null;
    this.cellOpenness = -1;
    this.row = tRow;
    this.col = tCol;
    this.name = index;
    this.startNode = false;
    this.endNode = false;
    this.possibleDirections = new Stack<Integer>();
  }


 

  // Setters
  public void setRightNode(Node node) 
  {
    this.right = node;
  }

  public void setLeftNode(Node node) 
  {
    this.left = node;
  }

  public void setUpNode(Node node) 
  {
    this.up = node;
  }

  public void setDownNode(Node node) 
  {
    this.down = node;
  }

  public void setCellOpenness(int tCellOpenness) 
  {
    this.cellOpenness = tCellOpenness;
  }

  public void setCol(int tCol) 
  {
    this.col = tCol;
  }

  public void setName(int index) 
  {
    this.name = index;
  }

  public void setStartNode()
  {
    this.startNode = true;
  }

  public void setEndNode()
  {
    this.endNode = true;
  }

  public void setVisited()
  {
    this.visited = true;
  }

  public void unsetVisit() {
    visited = false;
  }

  public void setParent(Node node) {
    parent = node;
  }

  public void setRow(int tRow) 
  {
    this.row = tRow;
  }

  // Getters
  public Node getRightNode() 
  {
    return this.right;
  }

  public Node getLeftNode() 
  {
    return this.left;
  }

  public Node getUpNode() 
  {
    return this.up;
  }

  public Node getDownNode() 
  {
    return this.down;
  }

  public int getCellOpenness() 
  {
    return this.cellOpenness;
  }

  public int getRow() 
  {
    return this.row;
  }



  public int getCol() 
  {
    return this.col;
  }

  public int getName() 
  {
    return this.name;
  }

  public Node getParent() {
    return parent;
  }

  public boolean getVisited()
  {
    return this.visited;
  }

  // Return next directon of direction stack
  public Integer getDirection()
  {
    return this.possibleDirections.pop();
  }

  // If possible directions is empty
  public boolean isEmpty()
  {
    return this.possibleDirections.empty();
  }

  // Is start node?
  public boolean isStartNode() {
    return this.startNode;
  }

  // Is end node?
  public boolean isEndNode() {
    return this.endNode;
  }

  // Returns cell avaliable cells in form of 0,1,2,3 as stated in the assignment
  // maze generator file format
  public int calculateCellOpenness()
  {
    int cellOpenness = 0;

    if(right != null && down != null)
    {
      cellOpenness = 3;
    }
    else if(right == null && down != null)
    {
      cellOpenness = 2;
    }
    else if(right != null && down == null)
    {
      cellOpenness = 1;
    }

    return cellOpenness;
  }

  // Updates cell openiness to be able to go right
  public void rightPossible()
  {
    if(cellOpenness == 0)
    {
      cellOpenness = 1;
    }
    else if (cellOpenness == 2)
    {
      cellOpenness = 3;
    }
  }

  // Updates cell openiness to be able to go down
  public void downPossible()
  {
    if(cellOpenness == 0)
    {
      cellOpenness = 2;
    }
    else if (cellOpenness == 1)
    {
      cellOpenness = 3;
    }
  }

  // Return surrondings nodes that haven't been visted yet
  public ArrayList<Node> getAllUnivistedNodes(){

    ArrayList<Node> unvisited = new ArrayList<>();

    //Check down 
    if(down != null){
      //Check if node hasn't been visted
      if(!down.getVisited()){
        unvisited.add(down);
      }
    }

    //Check right
    if(right != null){
      //Check if node hasn't been visted
      if(!right.getVisited()){
        unvisited.add(right);
      }
    }

    //Check left
    if(left != null){
      //Check if node hasn't been visted
      if(!left.getVisited()){
        unvisited.add(left);
      }
    }

    //Check up
    if(up != null){
      //Check if node hasn't been visted
      if(!up.getVisited()){
        unvisited.add(up);
      }
    }

    //Return array of unvisted nodes
    return unvisited;
  }

}