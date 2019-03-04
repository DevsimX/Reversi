import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

class Victory {

    void printVictoryInfo(int blackChessmanNumber , int whiteChessmanNumber , int i ,String signal , Long startTime , String humanSignal , int dimension){
        switch (i){
            case 1:System.out.println("No place here!\nGame over.\nX : O = " + blackChessmanNumber + " : " + whiteChessmanNumber + "\n" + signal + " player wins.");
            record(startTime,System.currentTimeMillis(),dimension,humanSignal,blackChessmanNumber,whiteChessmanNumber);break;
            case 2:System.out.println("Both players have no valid move.\nGame over.\nX : O = " + blackChessmanNumber + " : " + whiteChessmanNumber + "\n" + signal + " player wins.");
            record(startTime,System.currentTimeMillis(),dimension,humanSignal,blackChessmanNumber,whiteChessmanNumber);break;
            case 3:System.out.println("Game over.\nX : O = " +  + blackChessmanNumber + " : " + whiteChessmanNumber + "\n" + signal + " player wins.");
            record(startTime,System.currentTimeMillis(),dimension,humanSignal,blackChessmanNumber,whiteChessmanNumber);break;
            case 4:System.out.println("Invalid move.\nGame over.\n" + signal + " player wins.");
                record(startTime,System.currentTimeMillis(),dimension,humanSignal,0,0);break;
        }
        System.exit(0);
    }

    private void record(Long startTime , Long endTime , int dimension , String humanSignal , int blackChessmanNumber , int whiteChessmanNumber){
        String black = humanSignal.equals("X")?"human":"computer";
        String white = !humanSignal.equals("X")?"human":"computer";
        String output;
        try {
            File csv = new File("./src/reversi.csv");

            BufferedWriter bw = new BufferedWriter(new FileWriter(csv,true));
            if(blackChessmanNumber == 0 && whiteChessmanNumber == 0){
                output = "Human gave up.";
            }else
                output = blackChessmanNumber + " to " + whiteChessmanNumber;
            bw.write(new SimpleDateFormat("yyyyMMdd HH:mm:ss").format(new Date())+ "," + (endTime-startTime)/1000  + "," + dimension + "*" + dimension + "," + black + "," + white + "," + output);

            bw.newLine();
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
