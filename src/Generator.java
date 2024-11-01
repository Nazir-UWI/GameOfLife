import java.util.Random;

public class Generator {
    private Grid grid = new Grid();
    private Grid tempGrid = new Grid();
    private Random random = new Random();

    public void generate(){             //makes one generation of the grid, assume grid is wrapped along the x axis like a cylinder

        this.updateTempGrid();                  //update temp grid
        this.hatchingRule();                //If there are three species adjacent to each other, in the corners of a square, then a fourth is hatched
        this.updateGrid();                      //get changes from temp grid

        this.updateTempGrid();
        this.overcrowdingRule();            //If a species is surrounded by 5 or more members, then due to overcrowding it disappears.
        this.updateGrid();

    }

    public void hatchingRule(){
        for (int row = 0; row < 10; row++){
            for (int col = 0; col < 10; col++){
                if (this.grid.getValue(row, col) == '_'){   //if position is empty
                    this.hatchingRuleHelper(row, col);
                }
            }
        }
    }

    public void hatchingRuleHelper(int row, int col){                                                                       //n1 n2 n3
        char n1 = '_', n2 = '_', n3 = '_', n4 = '_', n5 = '_', n6 = '_', n7 = '_', n8 = '_';                                //n4 __ n5
                                                                                                                            //n6 n7 n8
        if (row-1 >= 0){n1 = this.grid.getValue(row-1, (col-1 + 10) % 10);}         //gets the values of each neighbour
        if (row-1 >= 0){n2 = this.grid.getValue(row-1, (col+0 + 10) % 10);}
        if (row-1 >= 0){n3 = this.grid.getValue(row-1, (col+1 + 10) % 10);}
                        n4 = this.grid.getValue(row,   (col-1 + 10) % 10);
                        n5 = this.grid.getValue(row,   (col+1 + 10) % 10);
        if (row+1 <= 9){n6 = this.grid.getValue(row+1, (col-1 + 10) % 10);}
        if (row+1 <= 9){n7 = this.grid.getValue(row+1, (col+0 + 10) % 10);}
        if (row+1 <= 9){n8 = this.grid.getValue(row+1, (col+1 + 10) % 10);}

            if(n4 == 'X' && n1 == 'X' && n2 == 'X'){        //corner 1
                this.tempGrid.setValue(row, col, 'X');
                return;
            }
            if(n2 == 'X' && n3 == 'X' && n5 == 'X'){        //corner 2
                this.tempGrid.setValue(row, col, 'X');
                return;
            }
            if(n5 == 'X' && n8 == 'X' && n7 == 'X'){        //corner 3
                this.tempGrid.setValue(row, col, 'X');
                return;
            }
            if(n7 == 'X' && n6 == 'X' && n4 == 'X'){        //corner 4
                this.tempGrid.setValue(row, col, 'X');
                return;
            }
    }

    public void overcrowdingRule(){
        for (int row = 0; row < 10; row++){
            for (int col = 0; col < 10; col++){
                if (this.grid.getValue(row, col) == 'X'){   //if position is occupied
                    this.overcrowdingRuleHelper(row, col);
                }
            }
        }
    }

    public void overcrowdingRuleHelper(int row, int col){
        int adjacentSpecies = 0;

        for (int i = -1; i <= 1; i++){
            for (int j = -1; j <= 1; j++){
                if (i == 0 && j == 0){              //skip the middle cell
                    continue;
                }

                int rowWrap = row + i;
                int colWrap = (col+j + 10) % 10;

                if (rowWrap >=0 && rowWrap < 10){
                    if (this.grid.getValue(rowWrap, colWrap) == 'X'){ 
                        adjacentSpecies++;
                    }
                }
            }
        }

        if (adjacentSpecies >= 5){
            this.tempGrid.setValue(row, col, '_');
        }

        
    }

    public void addRandom(int count){                   //add a member to a random grid space
        while(count > 0 && !this.grid.isFull()){             //will not try to add members more than 100 times
            int row = random.nextInt(10);
            int col = random.nextInt(10);

            if (this.grid.getValue(row, col) == '_'){
                this.grid.setValue(row, col, 'X');
                count--;
            }
        }
    }

    public void resetGrid(){                                //reset grid
        this.grid = new Grid();
    }

    public void setGrid(Grid grid){
        this.grid = grid;
    }

    public Grid getGrid(){
        return this.grid;
    }


    public void resetTempGrid(){                                //reset grid
        this.tempGrid = new Grid();
    }

    public void updateTempGrid(){
        for (int row = 0; row < 10; row++){
            for (int col = 0; col < 10; col++){
                char temp = this.grid.getValue(row, col);
                this.tempGrid.setValue(row, col, temp);
            }
        }
    }

    public void updateGrid(){
        for (int row = 0; row < 10; row++){
            for (int col = 0; col < 10; col++){
                char temp = this.tempGrid.getValue(row, col);
                this.grid.setValue(row, col, temp);
            }
        }
    }
}
