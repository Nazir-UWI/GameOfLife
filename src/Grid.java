public class Grid {                                                     //10 by 10 grid storing every position
    private Position field[][];
    
    public Grid(){
        field = new Position[10][10];

        for (int i = 0; i < 10; i++){
            for (int j = 0; j < 10; j++){
                field[i][j] = new Position();;
            }
        }
    }

    public void setValue(int i, int j, char state){
        this.field[i][j].setState(state);
    }

    public char getValue(int i, int j){
        return this.field[i][j].getState();
    }

    public boolean isFull(){
        for (int i = 0; i < 10; i++){
            for (int j = 0; j < 10; j++){
                if (this.field[i][j].getState() == '_'){
                    return false;
                }
            }
        }
        return true;
    }

}
