import java.util.ArrayList;

class Check {
    private Map map;

    private Victory victory = new Victory();

    private int number = 0;

    int judgeFitPlace(int x , int y , String color , Map map){
        this.map = map;
        number = 0;
        topDirection(x,y,color);
        downDirection(x,y,color);
        leftDirection(x,y,color);
        rightDirection(x,y,color);
        leftTopDirection(x,y,color);
        leftDownDirection(x,y,color);
        rightDownDirection(x,y,color);
        rightTopDirection(x,y,color);
        return number;
    }

    private void leftTopDirection(int x , int y , String color){
        ArrayList<String> localVariable = new ArrayList<>();
        for(int i = x - 1 , j = y -1 ; i >= 0 && x > 1 && j >=0 && y > 1 ; i --,j--){
            if(map.getMapArray()[i][j] == null)
                break;
            if(map.getMapArray()[i][j].color.equals(color)){
                if(i != x - 1){
                    number += x - 1 - i;
                    map.getColorChangeList().addAll(localVariable);
                }
                break;
            }else
                localVariable.add("" + i + "" + j);
        }
    }

    private void topDirection(int x , int y , String color){
        ArrayList<String> localVariable = new ArrayList<>();
        for(int i = x - 1 ; i >= 0 && x > 1; i--){
            if(map.getMapArray()[i][y] == null)
                break;
            if(map.getMapArray()[i][y].color.equals(color)){
                if(i != x-1) {
                    number += x - 1 - i;
                    map.getColorChangeList().addAll(localVariable);
                }
                break;
            }else
                localVariable.add("" + i + "" + y);
        }
    }

    private void rightTopDirection(int x , int y , String color){
        ArrayList<String> localVariable = new ArrayList<>();
        for(int i = x - 1, j = y + 1 ; i >= 0 && x > 1 && j < map.getDimension() && y < map.getDimension() - 2 ; i--,j++) {
            if (map.getMapArray()[i][j] == null)
                break;
            if (map.getMapArray()[i][j].color.equals(color)) {
                if (i != x - 1) {
                    number += x - 1 - i;
                    map.getColorChangeList().addAll(localVariable);
                }
                break;
            } else
                localVariable.add("" + i + "" + j);
        }
    }

    private void rightDirection(int x , int y , String color){
        ArrayList<String> localVariable = new ArrayList<>();
        for(int i = y + 1 ; i < map.getDimension() && y < map.getDimension() - 2; i++){
            if(map.getMapArray()[x][i] == null)
                break;
            if(map.getMapArray()[x][i].color.equals(color)){
                if(i != y + 1){
                    number += i - 1 - y;
                    map.getColorChangeList().addAll(localVariable);
                }
                break;
            }else
                localVariable.add("" + x + "" + i);
        }
    }

    private void leftDirection(int x , int y , String color) {
        ArrayList<String> localVariable = new ArrayList<>();
        for(int i = y - 1 ; i >= 0 && y > 1; i--){
            if(map.getMapArray()[x][i] == null)
                break;
            if(map.getMapArray()[x][i].color.equals(color)){
                if(i != y-1){
                    number += y - 1 - i;
                    map.getColorChangeList().addAll(localVariable);
                }
                break;
            }else
                localVariable.add("" + x + "" + i);
        }
    }

    private void leftDownDirection(int x , int y , String color){
        ArrayList<String> localVariable = new ArrayList<>();
        for(int i = x + 1 , j = y - 1 ; i < map.getDimension() && x < map.getDimension() - 2 && j >=0 && y > 1 ; i++,j--){
            if(map.getMapArray()[i][j] == null)
                break;
            if(map.getMapArray()[i][j].color.equals(color)){
                if(i != x + 1){
                    number += i - 1 - x;
                    map.getColorChangeList().addAll(localVariable);
                }
                break;
            }else
                localVariable.add("" + i + "" + j);
        }
    }

    private void downDirection(int x , int y , String color){
        ArrayList<String> localVariable = new ArrayList<>();
        for(int i = x + 1 ; i < map.getDimension() && x < map.getDimension() - 2; i++){
            if(map.getMapArray()[i][y] == null)
                break;
            if(map.getMapArray()[i][y].color.equals(color)){
                if(i != x + 1){
                    number += i - 1 - x;
                    map.getColorChangeList().addAll(localVariable);
                }
                break;
            }else
                localVariable.add("" + i + "" + y);
        }
    }

    private void rightDownDirection(int x , int y , String color){
        ArrayList<String> localVariable = new ArrayList<>();
        for(int i = x + 1 , j = y + 1 ; i < map.getDimension() && x < map.getDimension() - 2 && j < map.getDimension() && y < map.getDimension() - 2 ; i++,j++){
            if(map.getMapArray()[i][j] == null)
                break;
            if(map.getMapArray()[i][j].color.equals(color)){
                if(i != x + 1){
                    number += i - 1 - x;
                    map.getColorChangeList().addAll(localVariable);
                }
                break;
            }else
                localVariable.add("" + i + "" + j);
        }
    }

    private void checkPlace(Human human , Computer computer){
        boolean manGo = false;
        boolean computerGo = false;
        boolean havePlace = false;
        for(int i = 0 ; i < map.getDimension() ; i++){
            for(int j = 0 ; j < map.getDimension() ; j++){
                if(map.getMapArray()[i][j] == null){
                    if(judgeFitPlace(i,j,human.color,map) != 0)
                        manGo = true;
                    if(judgeFitPlace(i,j,computer.color,map) != 0)
                        computerGo = true;
                    havePlace = true;
                }
            }
        }
        map.reloadColorChangeList();
        if(!havePlace){
            victory.printVictoryInfo(map.blackChessmanNumber,map.whiteChessmanNumber,1,map.result(),map.startTime,human.signal,map.getDimension());
        }else if(!manGo && !computerGo){
            victory.printVictoryInfo(map.blackChessmanNumber,map.whiteChessmanNumber,2,map.result(),map.startTime,human.signal,map.getDimension());
        }
    }

    private void checkChessman(Human human , Computer computer){
        if(map.blackChessmanNumber == 0){
            victory.printVictoryInfo(map.blackChessmanNumber,map.whiteChessmanNumber,3,"O",map.startTime,human.signal,map.getDimension());
        }else if(map.whiteChessmanNumber == 0){
            victory.printVictoryInfo(map.blackChessmanNumber,map.whiteChessmanNumber,3,"X",map.startTime,human.signal,map.getDimension());
        }
    }

    boolean totalCheck(Human human , Computer computer , Map map){
        this.map = map;
        checkPlace(human,computer);
        checkChessman(human,computer);
        return true;
    }
}
