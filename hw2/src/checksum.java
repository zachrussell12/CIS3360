import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.Scanner;

/*=============================================================================
| Assignment: HW 02 – Calculating the 8, 16, or 32 bit checksum for a
| given input file
|
| Author: Zachary Russell
| Language: Java
|
| To Compile: javac Hw02.java
|
| To Execute: java Hw02 textfile.txt checksum_size
| where the files in the command line are in the current directory.
|
| The text file contains text is mixed case with spaces, punctuation,
| and is terminated by the hexadecimal ‘0A’, inclusive.
| (The 0x’0A’ is included in the checksum calculation.)
|
| The checksum_size contains digit(s) expressing the checksum size
| of either 8, 16, or 32 bits
 |
| Class: CIS3360 - Security in Computing - Fall 2020
| Instructor: McAlpin
| Due Date: 11/22/2020
|
+=============================================================================*/

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
                    echo += inScan.nextLine();
                }

                echo += '\n';


                if(checkSumSize == 8) {

                    int characterCount = echo.length();

                    int lineCounter = 0;
                    for(int g = 0; g < echo.length(); g++) {
                        if (lineCounter % 80 == 0) {
                            System.out.print("\n");
                        }
                        System.out.print(echo.charAt(g));
                        lineCounter++;
                    }

                    System.out.print("\n");
                    String[] binaryString8 = new String[echo.length()];


                    for (int i = 0; i < echo.length(); i++) {
                        binaryString8[i] = String.format("%8s", Integer.toBinaryString((int) echo.charAt(i))).replaceAll(" ", "0");
                    }

                    int newLine = binaryString8.length;
                    //System.out.println(binaryString.length);


                    /*for (int i = 0; i < binaryString.length; i++) {
                        System.out.println(binaryString[i]);
                    }*/

                    //System.out.println("\n");
                    String result = binaryString8[0];
                    String num = "";

                    for(int j = 1; j < binaryString8.length; j++){
                        int carry = 0;
                        result = binaryIterate(result.length()-1, binaryString8[j].length()-1, result, binaryString8[j], carry);
                    }
                    twosComplement(result);
                    String checksum = Integer.toHexString(Integer.parseInt(result, 2));
                    //System.out.println("Result: " + num);

                    //long checksum = new BigInteger(num, 16).longValue();
                    System.out.printf("%2d bit checksum is %8s for all %4d chars\n",
                            checkSumSize, checksum, characterCount);


                }
                else if(checkSumSize == 16){

                    if((echo.length()) % 2 != 0){
                        while((echo.length())%2 != 0){
                            echo += 'X';
                        }
                    }

                    int characterCount = echo.length();

                    int lineCounter16 = 0;
                    for(int g = 0; g < echo.length(); g++) {
                        if (lineCounter16 % 80 == 0) {
                            System.out.print("\n");
                        }
                        System.out.print(echo.charAt(g));
                        lineCounter16++;
                    }

                    System.out.print("\n");

                    //System.out.println("\n" + echo);
                    //System.out.println("\n" + echo.length()/2);
                    int counter = 0;
                    String[] combinedString = new String[echo.length()/2];

                    for(int f = 0; f < combinedString.length; f++){
                        combinedString[f] = Integer.toHexString((int) echo.charAt(counter));
                        combinedString[f] += Integer.toHexString((int) echo.charAt(counter + 1));
                        //System.out.println(combinedString[f]);
                        counter += 2;
                    }

                    int[]combinedInt = new int[combinedString.length];

                    for(int y = 0; y < combinedInt.length; y++){
                        combinedInt[y] = Integer.parseInt(combinedString[y], 16);
                        //System.out.println(combinedInt[y]);
                    }

                    /*for(int q = 0; q < combinedString.length; q++){
                        System.out.print(combinedString[q] + "  ||  ");
                    }*/

                    //System.out.println("\n" + combinedString.length);

                    String[] binaryString16 = new String[combinedString.length];

                    for (int i = 0; i < combinedString.length; i++) {
                        binaryString16[i] = String.format("%16s", Integer.toBinaryString(combinedInt[i]).replaceAll(" ", "0"));
                    }

                    /*for (int r = 0; r < combinedString.length; r++) {
                        System.out.println(binaryString16[r]);
                    }*/

                    String result16 = binaryString16[0];
                    String num = "";

                    //System.out.println("In 16");

                    for(int j = 1; j < binaryString16.length; j++){
                        int carry = 0;
                        result16 = binaryIterate(result16.length()-1, binaryString16[j].length()-1, result16, binaryString16[j], carry);
                    }
                    twosComplement(result16);
                    String checksum = Integer.toHexString(Integer.parseInt(result16, 2));
                    //System.out.println("Result: " + num);


                    //long checksum = new BigInteger(num, 16).longValue();
                    System.out.printf("%2d bit checksum is %8s for all %4d chars\n",
                            checkSumSize, checksum, characterCount);
                }
                else if(checkSumSize == 32){

                    if((echo.length()) % 4 != 0){
                        while((echo.length())%4 != 0){
                            echo += 'X';
                        }
                    }

                    int characterCount = echo.length();

                    int lineCounter32 = 0;
                    for(int g = 0; g < echo.length(); g++) {
                        if (lineCounter32 % 80 == 0) {
                            System.out.print("\n");
                        }
                        System.out.print(echo.charAt(g));
                        lineCounter32++;
                    }

                    System.out.print("\n");

                    //System.out.println("\n" + echo);
                    //System.out.println("\n" + echo.length()/4);

                    int counter = 0;
                    String[] combinedString32 = new String[echo.length()/4];


                    for(int f = 0; f < combinedString32.length; f++){
                        combinedString32[f] = Integer.toHexString((int) echo.charAt(counter));
                        combinedString32[f] += Integer.toHexString((int) echo.charAt(counter + 1));
                        combinedString32[f] += Integer.toHexString((int) echo.charAt(counter + 2));
                        combinedString32[f] += Integer.toHexString((int) echo.charAt(counter + 3));
                        //System.out.println(combinedString32[f]);
                        counter += 4;
                    }
                    //System.out.println("\n" + combinedString32.length);

                    String[] binaryString32 = new String[combinedString32.length];

                    for (int i = 0; i < combinedString32.length; i++) {
                        binaryString32[i] = String.format("%32s", Integer.toBinaryString(Integer.parseInt(combinedString32[i], 16))).replaceAll(" ", "0");
                    }

                    for (int r = 0; r < combinedString32.length; r++) {
                        System.out.println(binaryString32[r]);
                    }

                    String result32 = binaryString32[0];
                    String num = "";

                    //System.out.println("In 16");

                    for(int j = 1; j < binaryString32.length; j++){
                        int carry = 0;
                        result32 = binaryIterate(result32.length()-1, binaryString32[j].length()-1, result32, binaryString32[j], carry);
                        System.out.println(Long.toHexString(Long.parseLong(result32, 2)));
                    }

                    twosComplement(result32);
                    long checkSum = Long.parseLong(result32, 2);
                    String checksum = Long.toHexString(checkSum);
                    //System.out.println("Result: " + num);
                    checkSum = Long.parseLong(checksum, 16);
                    System.out.printf("%2d bit checksum is %8s for all %4d chars\n",
                            checkSumSize, checksum, characterCount);

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

    public static String binaryIterate(int p, int s, String numberOne, String numberTwo, int carry){
        StringBuilder result = new StringBuilder();
        int garbage;
        while(p >= 0 || s >= 0){
            int carryD = 0;

            if((p >= 0 && numberOne.charAt(p) == '1')){carryD++;}

            if(s >= 0 && numberTwo.charAt(s) == '1'){carryD++;}

            carryD += carry;

            if(carryD >= 2){carry = 1;}
            else{carry = 0;}

            int carryResult = carryD % 2 + '0';
            result.insert(0, (char) (carryResult));

            p--;
            s--;
        }

        return result.toString();
    }


    public static String twosComplement(String input){
        String complementedString = "";

        //System.out.println("\n\n" + input);

        for(int i = 0; i < input.length(); i++){
            if(input.charAt(i) == '1'){
                complementedString += '0';
            }
            else {
                complementedString += '1';
            }
        }

        StringBuilder reverser = new StringBuilder();
        reverser.append(complementedString);
        reverser.reverse();
        complementedString = reverser.toString();
        //System.out.println("\n\n" + complementedString);

        String compPlusOne = "";
        String one = "";
        if(input.length() == 32){
            one = "1";
        }
        else if(input.length() == 16) {
            one = "0000000000000001";
        }
        else
        {
            one = "00000001";
        }

        //System.out.println("\n\n" + doneString);*/
        int carry = 0;
        String doneString = binaryIterate(complementedString.length()-1, one.length()-1, complementedString, one, carry);

        //System.out.println(doneString);
        return doneString;
    }

}

/*=============================================================================
| I, Zachary Russell (za443739), affirm that this program is
| entirely my own work and that I have neither developed my code together with
| any another person, nor copied any code from any other person, nor permitted
| my code to be copied or otherwise used by any other person, nor have I
| copied, modified, or otherwise used programs created by others. I acknowledge
| that any violation of the above terms will be treated as academic dishonesty.
+=============================================================================*/