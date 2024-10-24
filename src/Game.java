public class Game {
    private Input input = new Input();
    private Menu menu = new Menu();
    private Grid grid = new Grid();
    private Generator generator = new Generator();
    private String action;

    public void run(){                                                  //runs the game

        do{
            this.menu.displayMenu();                    //show menu
            this.action = this.input.getAction();       //get menu action

            this.generator.setGrid(grid);               //send grid to generator

            switch(this.action){                        //perform action
                case "next":
                    this.nextGame();
                        break;
                case "skip":   
                    this.skipGame();
                        break;
                case "random":
                    this.addRandom();
                        break;
                case "reset":
                    this.resetGame();
                        break;
                case "stop":
                    this.stopGame();
                        break;
                default:
                    System.out.println(action + " is not a recognized command");
            }

            this.grid = this.generator.getGrid();       //retrieve grid from generator
            this.displayGame();

        }while (!this.action.equals("stop"));

    }

    public void displayGame(){
        for (int i = 0; i < 10; i++){
            for (int j = 0; j < 10; j++){
                System.out.print(this.grid.getValue(i, j) + " ");
            }
            System.out.println(" ");
        }
    }

    public void nextGame(){
        this.generator.generate();
    }

    public void skipGame(){
        int count = this.input.getNum();
        while (count !=0){
            this.generator.generate();
            count--;
        }
    }

    public void addRandom(){
        int count = this.input.getNum();
        this.generator.addRandom(count);
    }

    public void resetGame(){
        this.generator.resetGrid();
    }

    public void stopGame(){
        System.out.println("goodbye");
    }


    
}
