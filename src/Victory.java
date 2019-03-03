class Victory {

    void printVictoryInfo(int blackChessmanNumber , int whiteChessmanNumber , int i ,String signal){
        switch (i){
            case 1:System.out.println("No place here!\nGame over.\nX : O = " + blackChessmanNumber + " : " + whiteChessmanNumber + "\n" + signal + " player wins.");break;
            case 2:System.out.println("Both players have no valid move.\nGame over.\nX : O = " + blackChessmanNumber + " : " + whiteChessmanNumber + "\n" + signal + " player wins.");break;
            case 3:System.out.println("Game over.\nX : O = " +  + blackChessmanNumber + " : " + whiteChessmanNumber + "\n" + signal + " player wins.");break;
            case 4:System.out.println("Invalid move.\nGame over.\n" + signal + " player wins.");break;
        }
        System.exit(0);
    }
}
