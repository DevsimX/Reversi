import java.util.Scanner;

class Human extends Player{
    Human(){
        this.color = "";
        this.signal = "";
        this.goFirst = false;
    }

    void move(){
        System.out.print("Enter move for " + this.signal + " (RowCol):");
        String input = new Scanner(System.in).next();
        while(input.length() != 2 || (int)input.charAt(0) <= 96 || (int)input.charAt(0) >= 97 + map.getDimension() || (int)input.charAt(1) <= 96 || (int)input.charAt(1) >= 97 + map.getDimension() || map.getMapArray()[input.charAt(0) - 97][input.charAt(1) - 97] != null){
            System.out.print("Invalid input\n" + "Enter move for " + this.signal + " (RowCol):");
            input = new Scanner(System.in).next();
        }
        if(check.judgeFitPlace(input.charAt(0) - 97 , input.charAt(1) - 97 , this.color,map) == 0){
            Victory victory = new Victory();
            String computerSignal = this.signal.equals("O")?"X":"O";
            victory.printVictoryInfo(map.blackChessmanNumber,map.whiteChessmanNumber,4,computerSignal,map.startTime,this.signal,map.getDimension());
        }
        map.getMapArray()[input.charAt(0) - 97][input.charAt(1) - 97] = !this.goFirst?new WhiteChessman():new BlackChessman();
        if(this.goFirst)
            map.blackChessmanNumber += 1;
        else
            map.whiteChessmanNumber += 1;
        map.colorChange(map.getColorChangeList(),this.color);
        map.printMap();
    }
}
