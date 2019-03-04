import java.util.ArrayList;

class Computer extends Player {
    Computer(){
        this.color = "";
        this.signal = "";
        this.goFirst = false;
    }

    void move(){
        int maxNumber = 0;
        int x = -1;
        int y = -1;
        int dimension = map.getDimension();
        ArrayList<String> colorChange = new ArrayList<>();
        for(int i = 0; i < dimension ; i++){
            for(int j = 0 ; j < dimension ; j++){
                if(map.getMapArray()[i][j] == null){
                    int n = check.judgeFitPlace(i,j,this.color,map);
                    if(n > maxNumber){
                        maxNumber = n;
                        x = i;
                        y = j;
                        colorChange = map.getColorChangeList();
                    }else if(n == maxNumber && n != 0){
                        if(i < x){
                            x = i;
                            y = j;
                            colorChange = map.getColorChangeList();
                        }else if(i == x){
                            if(j < y){
                                x = i;
                                y = j;
                                colorChange = map.getColorChangeList();
                            }
                        }
                    }
                    map.reloadColorChangeList();
                }
            }
        }
        if(x != -1 & y != -1){
            map.getMapArray()[x][y] = this.goFirst ? new BlackChessman() : new WhiteChessman();
            if(this.goFirst)
                map.blackChessmanNumber += 1;
            else
                map.whiteChessmanNumber += 1;
            System.out.println("Computer places " + this.signal + " at " + (char)(x + 97) + (char)(y + 97));
            map.colorChange(colorChange,this.color);
            map.printMap();
        }else
            System.out.print(this.signal + " player has no valid move.");
    }
}
