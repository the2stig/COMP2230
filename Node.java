import java.util.*;

public class Node {
 
  private Node right;
  private Node left;
  private Node up;
  private Node down;  

  private Node parent;
  private int cellOpenness;
  private int row;
  private int col;
  private int name;

  private boolean startNode;
  private boolean endNode;
  private boolean visited;

  private Stack<Integer> possibleDirections;

  public Node(int id){
    this.name = id;
  }


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


 

  public void setRightNode(Node node) 
  {
    this.right = node;
  }

  public Node getRightNode() 
  {
    return this.right;
  }

  public void setLeftNode(Node node) 
  {
    this.left = node;
  }
 
  public Node getLeftNode() 
  {
    return this.left;
  }

  public void setUpNode(Node node) 
  {
    this.up = node;
  }

  public Node getUpNode() 
  {
    return this.up;
  }

  public void setDownNode(Node node) 
  {
    this.down = node;
  }

  public Node getDownNode() 
  {
    return this.down;
  }
 
  public void setCellOpenness(int tCellOpenness) 
  {
    this.cellOpenness = tCellOpenness;
  }

  public int getCellOpenness() 
  {
    return this.cellOpenness;
  }

    public void setRow(int tRow) 
  {
    this.row = tRow;
  }

  public int getRow() 
  {
    return this.row;
  }

    public void setCol(int tCol) 
  {
    this.col = tCol;
  }

  public int getCol() 
  {
    return this.col;
  }

  public void setName(int index) 
  {
    this.name = index;
  }

  public int getName() 
  {
    return this.name;
  }

  public void setStartNode()
  {
    this.startNode = true;
  }

  public boolean isStartNode()
  {
    return this.startNode;
  }

  public void setEndNode()
  {
    this.endNode = true;
  }

  public boolean isEndNode()
  {
    return this.endNode;
  }

  public void setVisited()
  {
    this.visited = true;
  }

  public boolean getVisited()
  {
    return this.visited;
  }

  public Integer getDirection()
  {
    return this.possibleDirections.pop();
  }

  public boolean isEmpty()
  {
    return this.possibleDirections.empty();
  }

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

  public ArrayList<Node> getAllUnivistedNodes(){

    ArrayList<Node> unvisited = new ArrayList<>();
    //Check up

    if(up != null){
      //Check if node hasn't been visted
      if(!up.getVisited()){
        unvisited.add(up);
      }
    }

    //Check down 

    if(down != null){
      //Check if node hasn't been visted
      if(!down.getVisited()){
        unvisited.add(down);
      }
    }

    //Check left

    if(left != null){
      //Check if node hasn't been visted
      if(!left.getVisited()){
        unvisited.add(left);
      }
    }

    //Check right

    if(right != null){
      //Check if node hasn't been visted
      if(!right.getVisited()){
        unvisited.add(right);
      }
    }

    //Return array of unvisted nodes
    return unvisited;
  }

  public void setParent(Node node){
    parent = node;
  }

  public Node getParent(){
    return parent;
  }

  public void unsetVisit(){
    visited = false;
  }
}