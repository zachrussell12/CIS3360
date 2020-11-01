import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static java.lang.System.exit;

public class checksum {
    public static void main (String[] args){
        File input1 = new File(String.valueOf(args[0]));
        int checkSumSize = Integer.parseInt(String.valueOf(args[1]));
        try {
            if (String.valueOf(args[1]).equals("8") || String.valueOf(args[1]).equals("16") || String.valueOf(args[1]).equals("32")) {

                Scanner inScan = new Scanner(input1);

                String echo = "";
                while(inScan.hasNext()){
                    echo += inScan.next();
                }

                char[] inFileChars = new char[echo.length()];

                for(int i = 0; i < echo.length(); i++){
                    inFileChars[i] = echo.charAt(i);
                }
                System.out.println("\n" + echo + "\n");

                for(int i = 0; i < echo.length(); i++){
                    System.out.println(inFileChars[i]);
                }

                String[] binaryArray = new String[inFileChars.length];

                binaryArray = toBinary(inFileChars);

                System.out.printf("\n");


                for(int i = 0; i < binaryArray.length; i++){
                    System.out.println(binaryArray[i]);
                }


            }
            else {
                System.err.printf("Valid checksum sizes are 8, 16, or 32\n");
                exit(0);
            }
        }
        catch(FileNotFoundException errorCode){
            System.out.println("File not found");
        }
    }
    
    public static String[] toBinary(char[] charArray){
        String[] binaryArray = new String[charArray.length];

        for(int i = 0; i < charArray.length; i++){
            int val = (int) charArray[i];

            while(val > 0){
                if(val % 2 == 1){
                    binaryArray[i] += '1';
                }
                else{
                    binaryArray[i] += '0';
                }
                val = val/2;
            }
        }

        return binaryArray;
    }


}
