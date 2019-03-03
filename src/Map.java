import java.util.ArrayList;
import java.util.Scanner;

class Map {
    private int dimension;
    private int whiteChessmanNumber = 0;
    private int blackChessmanNumber = 0;
    private Chessman [][] map;
    private String space = "  ";
    private boolean computerFirst = false;
    private String humanColor;
    private String computerColor;
    private String humanSignal;
    private String computerSignal;
    private ArrayList<String> colorChangeList = new ArrayList<>();
    private Victory victory = new Victory();
    Map(int dimension){
        this.dimension = dimension;
        map = new Chessman[this.dimension][this.dimension];
        initializeMap();
        run();
    }

    private void initializeMap(){
        for(int i = this.dimension/2 - 1 ; i <= this.dimension/2 ; i++){
            for(int j = this.dimension/2 - 1 ; j <= this.dimension/2; j++){
                map[i][j] = i == j ? new WhiteChessman():new BlackChessman();
            }
        }
        whiteChessmanNumber += 2;
        blackChessmanNumber += 2;
    }

    private void run(){
        getInfo();
        boolean goJudge = computerFirst;
        while(check()){
            if(goJudge){
                computerGo();
                goJudge = false;
            }else{
                humanGo();
                goJudge = true;
            }
            reloadColorChangeList();
        }
    }

    private boolean check(){
        checkPlace();
        checkChessman();
        return true;
    }

    private void checkPlace(){
        boolean manGo = false;
        boolean computerGo = false;
        boolean havePlace = false;
        for(int i = 0 ; i < dimension ; i++){
            for(int j = 0 ; j < dimension ; j++){
                if(map[i][j] == null){
                    if(judgeFitPlace(i,j,humanColor) != 0)
                        manGo = true;
                    if(judgeFitPlace(i,j,computerColor) != 0)
                        computerGo = true;
                    havePlace = true;
                }
            }
        }
        reloadColorChangeList();
        if(!havePlace){
            victory.printVictoryInfo(blackChessmanNumber,whiteChessmanNumber,1,countNumber());
        }else if(!manGo && !computerGo){
            victory.printVictoryInfo(blackChessmanNumber,whiteChessmanNumber,2,countNumber());
        }
    }

    private void checkChessman(){
        if(blackChessmanNumber == 0){
            victory.printVictoryInfo(blackChessmanNumber,whiteChessmanNumber,3,"O");
        }else if(whiteChessmanNumber == 0){
            victory.printVictoryInfo(blackChessmanNumber,whiteChessmanNumber,3,"X");
        }
    }

    private String countNumber(){
        return blackChessmanNumber >= whiteChessmanNumber?"X":"O";
    }

    private void reloadColorChangeList(){
        colorChangeList = new ArrayList<>();
    }

    //need to simplify
    private void computerGo(){
        int maxNumber = 0;
        int x = -1;
        int y = -1;
        ArrayList<String> colorChange = new ArrayList<>();
        for(int i = 0; i < this.dimension ; i++){
            for(int j = 0 ; j < this.dimension ; j++){
                if(map[i][j] == null){
                    int n = judgeFitPlace(i,j,computerColor);
                    if(n > maxNumber){
                        maxNumber = n;
                        x = i;
                        y = j;
                        colorChange = colorChangeList;
                    }else if(n == maxNumber && n != 0){
                        if(i < x){
                            x = i;
                            y = j;
                            colorChange = colorChangeList;
                        }else if(i == x){
                            if(j < y){
                                x = i;
                                y = j;
                                colorChange = colorChangeList;
                            }
                        }
                    }
                    reloadColorChangeList();
                }
            }
        }
        if(x != -1 & y != -1){
            map[x][y] = computerFirst ? new BlackChessman() : new WhiteChessman();
            if(computerFirst)
                blackChessmanNumber += 1;
            else
                whiteChessmanNumber += 1;
            System.out.println("Computer places " + computerSignal + " at " + (char)(x + 97) + (char)(y + 97));
            colorChange(colorChange,computerColor);
            printMap();
        }else
            System.out.print(computerSignal + " player has no valid move.");
    }

    //need to simplify
    private int judgeFitPlace(int x , int y , String color){
        int number = 0;
        ArrayList<String> localVariable = new ArrayList<>();
        for(int i = x - 1 ; i >= 0 && x > 1; i--){
            if(map[i][y] == null)
                break;
            if(map[i][y].color.equals(color)){
                if(i != x-1) {
                    number += x - 1 - i;
                    colorChangeList.addAll(localVariable);
                }
                break;
            }else
                localVariable.add("" + i + "" + y);
        }
        localVariable = new ArrayList<>();
//        System.out.println(colorChangeList);
        for(int i = x + 1 ; i < dimension && x < dimension - 2; i++){
            if(map[i][y] == null)
                break;
            if(map[i][y].color.equals(color)){
                if(i != x + 1){
                    number += i - 1 - x;
                    colorChangeList.addAll(localVariable);
                }
                break;
            }else
                localVariable.add("" + i + "" + y);
        }
        localVariable = new ArrayList<>();
//        System.out.println(colorChangeList);
        for(int i = y - 1 ; i >= 0 && y > 1; i--){
            if(map[x][i] == null)
                break;
            if(map[x][i].color.equals(color)){
                if(i != y-1){
                    number += y - 1 - i;
                    colorChangeList.addAll(localVariable);
                }
                break;
            }else
                localVariable.add("" + x + "" + i);
        }
        localVariable = new ArrayList<>();
//        System.out.println(colorChangeList);
        for(int i = y + 1 ; i < dimension && y < dimension - 2; i++){
            if(map[x][i] == null)
                break;
            if(map[x][i].color.equals(color)){
                if(i != y + 1){
                    number += i - 1 - y;
                    colorChangeList.addAll(localVariable);
                }
                break;
            }else
                localVariable.add("" + x + "" + i);
        }
        localVariable = new ArrayList<>();
//        System.out.println(colorChangeList);
        for(int i = x - 1 , j = y -1 ; i >= 0 && x > 1 && j >=0 && y > 1 ; i --,j--){
            if(map[i][j] == null)
                break;
            if(map[i][j].color.equals(color)){
                if(i != x - 1){
                    number += x - 1 - i;
                    colorChangeList.addAll(localVariable);
                }
                break;
            }else
                localVariable.add("" + i + "" + j);
        }
        localVariable = new ArrayList<>();
//        System.out.println(colorChangeList);
        for(int i = x + 1 , j = y + 1 ; i < dimension && x < dimension - 2 && j < dimension && y < dimension - 2 ; i++,j++){
            if(map[i][j] == null)
                break;
            if(map[i][j].color.equals(color)){
                if(i != x + 1){
                    number += i - 1 - x;
                    colorChangeList.addAll(localVariable);
                }
                break;
            }else
                localVariable.add("" + i + "" + j);
        }
        localVariable = new ArrayList<>();
//        System.out.println(colorChangeList);
        for(int i = x - 1, j = y + 1 ; i >= 0 && x > 1 && j < dimension && y < dimension - 2 ; i--,j++) {
            if (map[i][j] == null)
                break;
            if (map[i][j].color.equals(color)) {
                if (i != x - 1) {
                    number += x - 1 - i;
                    colorChangeList.addAll(localVariable);
                }
                break;
            } else
                localVariable.add("" + i + "" + j);
        }
        localVariable = new ArrayList<>();
//        System.out.println(colorChangeList);
        for(int i = x + 1 , j = y - 1 ; i < dimension && x < dimension - 2 && j >=0 && y > 1 ; i++,j--){
            if(map[i][j] == null)
                break;
            if(map[i][j].color.equals(color)){
                if(i != x + 1){
                    number += i - 1 - x;
                    colorChangeList.addAll(localVariable);
                }
                break;
            }else
                localVariable.add("" + i + "" + j);
        }
//        System.out.println(colorChangeList);
        return number;
    }

    private void colorChange(ArrayList<String> changeList, String color){
        for (String chessman:changeList
             ) {
            int x = Integer.parseInt(chessman.charAt(0)+"");
            int y = Integer.parseInt(chessman.charAt(1)+"");
            map[x][y] = color.equals("black")?new BlackChessman():new WhiteChessman();
            if(color.equals("black")){
                blackChessmanNumber += 1;
                whiteChessmanNumber -= 1;
            }else{
                blackChessmanNumber -= 1;
                whiteChessmanNumber += 1;
            }
        }
    }

    private void humanGo(){
        System.out.print("Enter move for " + humanSignal + " (RowCol):");
        String input = new Scanner(System.in).next();
        a:while(input.length() != 2 || (int)input.charAt(0) <= 96 || (int)input.charAt(0) >= 97 + dimension || (int)input.charAt(1) <= 96 || (int)input.charAt(1) >= 97 + dimension || map[input.charAt(0) - 97][input.charAt(1) - 97] != null){
            System.out.print("Invalid input\n" + "Enter move for " + humanSignal + " (RowCol):");
            input = new Scanner(System.in).next();
        }
        if(judgeFitPlace(input.charAt(0) - 97 , input.charAt(1) - 97 , humanColor) == 0){
            victory.printVictoryInfo(blackChessmanNumber,whiteChessmanNumber,4,computerSignal);
        }
        map[input.charAt(0) - 97][input.charAt(1) - 97] = computerFirst?new WhiteChessman():new BlackChessman();
        if(!computerFirst)
            blackChessmanNumber += 1;
        else
            whiteChessmanNumber += 1;
        colorChange(colorChangeList,humanColor);
        printMap();
    }

    private void getInfo(){
        System.out.print("Computer plays (X/O):");
        String computerColor = new Scanner(System.in).next();
        while(!computerColor.equals("X") && !computerColor.equals("O")){
            System.out.print("Invalid input\nComputer plays (X/O):");
            computerColor = new Scanner(System.in).next();
        }
        setComputerFirst(computerColor);
        setColor();
        printMap();
    }

    private void setColor(){
        this.humanColor = this.computerFirst?"white":"black";
        this.computerColor = this.computerFirst?"black":"white";
        this.humanSignal = this.computerFirst?"O":"X";
        this.computerSignal = this.computerFirst?"X":"O";
    }

    private void setComputerFirst(String computerColor){
        computerFirst = computerColor.equals("X");
    }

    private void printMap(){
        printFirstLine();
        printOtherLines();
    }

    private void printFirstLine(){
        System.out.print(" " + space);
        for(int i = 0 ; i < this.dimension ; i++){
            System.out.print((char)(i + 97) + space);
        }
        System.out.println();
    }

    private void printOtherLines(){
        for(int i = 0 ; i < this.dimension ; i++){
            System.out.print((char)(i + 97) + space);
            for(int j = 0 ; j < this.dimension ; j++){
                if(map[i][j] == null)
                    System.out.print("." + space);
                else
                    System.out.print(map[i][j].signal + space);
            }
            System.out.println();
        }
    }
}
