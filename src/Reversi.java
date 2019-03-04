import java.util.Scanner;
import java.util.regex.Pattern;

public class Reversi {
    private static Human human = new Human();

    private static Computer computer = new Computer();

    private static Map map;

    public static void main(String[] args){
        System.out.print("Enter the board dimension:");
        String input = new Scanner(System.in).next();
        while(!Pattern.matches("^4|6|8|10$",input)){
            System.out.print("Invalid input\nEnter the board dimension:");
            input = new Scanner(System.in).next();
        }
        int dimension = Integer.parseInt(input);
        map = new Map(dimension);
        Run(map);
    }

    private static void Run(Map map){
        Check check = new Check();
        getInfo();
        boolean goJudge = computer.goFirst;
        while(check.totalCheck(human,computer,map)){
            if(goJudge){
                computer.move();
                goJudge = false;
            }else{
                human.move();
                goJudge = true;
            }
            map.reloadColorChangeList();
        }
    }

    private static void getInfo(){
        System.out.print("Computer plays (X/O):");
        String computerColor = new Scanner(System.in).next();
        while(!computerColor.equals("X") && !computerColor.equals("O")){
            System.out.print("Invalid input\nComputer plays (X/O):");
            computerColor = new Scanner(System.in).next();
        }
        setInfo(computerColor);
        map.printMap();
    }

    private static void setInfo(String computerColor){
        computer.signal = computerColor;
        computer.goFirst = computerColor.equals("X");
        computer.color = computerColor.equals("X")?"black":"white";
        computer.map = map;
        human.signal = computerColor.equals("X")?"O":"X";
        human.goFirst = !computerColor.equals("X");
        human.color = computerColor.equals("O")?"black":"white";
        human.map = map;
    }
}
