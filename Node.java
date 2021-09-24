import java.util.*;

public class Node {
 
  private Node right;
  private Node left;
  private Node up;
  private Node down;  

  private int data;
  private int row;
  private int col;
  private int name;

  private boolean startNode;
  private boolean endNode;
  private boolean visited;

  private Stack<Integer> possibleDirections;


  public Node(int tRow, int tCol, int index, Stack<Integer> tPossibleDirections) 
  {
    this.right = null;
    this.left = null;
    this.up = null;
    this.down = null;
    this.data = -1;
    this.row = tRow;
    this.col = tCol;
    this.name = index;
    this.startNode = false;
    this.endNode = false;
    this.possibleDirections = tPossibleDirections;
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
 
  public void setData(int tData) 
  {
    this.data = tData;
  }

  public int getData() 
  {
    return this.data;
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


}