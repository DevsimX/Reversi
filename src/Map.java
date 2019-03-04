import java.util.ArrayList;

class Map {
    private int dimension;
    int whiteChessmanNumber = 0;
    int blackChessmanNumber = 0;
    private Chessman [][] map;
    private String space = "  ";
    private ArrayList<String> colorChangeList = new ArrayList<>();
    Long startTime;

    Map(int dimension){
        startTime = System.currentTimeMillis();
        this.dimension = dimension;
        map = new Chessman[this.dimension][this.dimension];
        initializeMap();
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

    ArrayList<String> getColorChangeList(){
        return this.colorChangeList;
    }

    String result(){
        return blackChessmanNumber >= whiteChessmanNumber?"X":"O";
    }

    void reloadColorChangeList(){
        colorChangeList = new ArrayList<>();
    }

    void colorChange(ArrayList<String> changeList, String color){
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

    void printMap(){
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

    int getDimension(){
        return this.dimension;
    }

    Chessman[][] getMapArray(){
        return this.map;
    }
}
