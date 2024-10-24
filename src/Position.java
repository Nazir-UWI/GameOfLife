public class Position {                                     //single grid position
    private char state;

    public Position(){
        this.state = '_';
    }

    public char getState(){
        return this.state;
    }

    public void setState(char inChar){
        this.state = inChar;
    }
}
