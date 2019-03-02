import java.util.Scanner;
import java.util.regex.Pattern;

public class Reversi {

    private static int dimension;

    public static void main(String[] args){
        System.out.print("Enter the board dimension:");
        String input = new Scanner(System.in).next();
        while(!Pattern.matches("^4|6|8|10$",input)){
            System.out.print("Invalid input\nEnter the board dimension:");
            input = new Scanner(System.in).next();
        }
        dimension = Integer.parseInt(input);
        Map map = new Map(dimension);
    }
}
