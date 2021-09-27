public class MazeMatrix {
    private Node[][] matrix;
    private int width;
    private int height;

    public MazeMatrix(int width,int height,String openings){

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
                setOpenings(opens[postion],matrix[i][j]);
            }

        }
        
    }

    public int getWidth() {
        return width;
    }

    public int getHeight(){
        return height;
    }

    public void addCellOpenings(){
        

    }

    private Node getNode(int row,int col){
        return matrix[row][col];
    }

    private void linkNodes(){

        
        int row = current.getRow();
        int col = current.getCol();

        switch (opening){
            case '0':
                //Check node in same row column-1 to be ethier 1 or 3 
                current.setLeftNode(getNode(row, col-1));
            break;
            case '1':
                current.setRightNode(getNode(row, col+1));
            break;
            case '2':
                current.setDownNode(getNode(row+1, col));
            break;
            case '3':
                //Check node
                current.setRightNode(getNode(row, col+1));
                current.setDownNode(getNode(row+1, col));
            break;
        }
    }

    public int nodeConnections(int row,int col){
        return getNode(row, col).getCellOpenness();
    }

}
