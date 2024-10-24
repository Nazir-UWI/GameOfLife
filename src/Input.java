import java.util.Scanner;

public class Input {                                            //gets all user inputs
    private Scanner userInput = new Scanner(System.in);

    public String getAction(){
        String action = userInput.nextLine();
        return action;
    }

    public int getNum(){
        int num = userInput.nextInt();
        userInput.nextLine();
        return num;
    }
    
}
