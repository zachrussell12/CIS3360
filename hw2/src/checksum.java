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

                int characterCount = echo.length();


                if(checkSumSize == 8) {


                    System.out.println("\n" + echo);

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
                        result = binaryConversion(result, binaryString8[j]);
                    }
                    twosComplement(result);
                    String checksum = Integer.toHexString(Integer.parseInt(result));
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

                    System.out.println("\n" + echo);
                    //System.out.println("\n" + echo.length()/2);
                    int counter = 0;
                    int[] combinedString = new int[echo.length()/2];

                    for(int f = 0; f < combinedString.length; f++){
                        combinedString[f] = ((int)echo.charAt(counter)) + ((int)echo.charAt(counter + 1));
                        counter += 2;
                    }

                    /*for(int q = 0; q < combinedString.length; q++){
                        System.out.print(combinedString[q] + "  ||  ");
                    }*/

                    //System.out.println("\n" + combinedString.length);

                    String[] binaryString16 = new String[combinedString.length];

                    for (int i = 0; i < combinedString.length; i++) {
                        binaryString16[i] = String.format("%8s", Integer.toBinaryString(combinedString[i])).replaceAll(" ", "0");
                    }

                    /*for (int r = 0; r < combinedString.length; r++) {
                        System.out.println(binaryString16[r]);
                    }*/

                    String result16 = binaryString16[0];
                    String num = "";

                    //System.out.println("In 16");

                    for(int j = 1; j < binaryString16.length; j++){
                        result16 = binaryConversion(result16, binaryString16[j]);
                    }
                    twosComplement(result16);
                    String checksumS = Integer.toHexString(Integer.parseInt(result16));
                    //System.out.println("Result: " + num);
                    long checksum = Long.parseLong(checksumS);

                    //long checksum = new BigInteger(num, 16).longValue();
                    System.out.printf("%2d bit checksum is %8lx for all %4d chars\n",
                            checkSumSize, checksum, characterCount);
                }
                else if(checkSumSize == 32){

                    if((echo.length()) % 4 != 0){
                        while((echo.length())%4 != 0){
                            echo += 'X';
                        }
                    }

                    System.out.println("\n" + echo);
                    //System.out.println("\n" + echo.length()/4);

                    int counter = 0;
                    int[] combinedString32 = new int[echo.length()/4];

                    for(int f = 0; f < combinedString32.length; f++){
                        combinedString32[f] = ((int)echo.charAt(counter)) + ((int)echo.charAt(counter + 1) + ((int)echo.charAt(counter + 2)) + ((int)echo.charAt(3)));
                        counter += 4;
                    }

                    /*for(int q = 0; q < combinedString32.length; q++){
                        System.out.print(combinedString32[q] + "  ||  ");
                    }*/

                    //System.out.println("\n" + combinedString32.length);

                    String[] binaryString32 = new String[combinedString32.length];

                    for (int i = 0; i < combinedString32.length; i++) {
                        binaryString32[i] = String.format("%9s", Integer.toBinaryString(combinedString32[i])).replaceAll(" ", "0");
                    }

                    /*for (int r = 0; r < combinedString32.length; r++) {
                        System.out.println(binaryString32[r]);
                    }*/

                    String result32 = binaryString32[0];
                    String num = "";

                    //System.out.println("In 16");

                    for(int j = 1; j < binaryString32.length; j++){
                        result32 = binaryConversion(result32, binaryString32[j]);
                    }

                    twosComplement(result32);
                    String checksum = Integer.toHexString(Integer.parseInt(result32));
                    //System.out.println("Result: " + num);

                    //long checksum = new BigInteger(num, 16).longValue();
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

    public static String binaryConversion(String lastBinaryNumber, String binaryNumber){
        String result = "";
        StringBuilder reverser = new StringBuilder();

        //System.out.println(lastBinaryNumber + "    " + binaryNumber);

        int carry = 0;
        for(int i = 0; i < lastBinaryNumber.length(); i++){
            if(lastBinaryNumber.charAt(i) == '1' && binaryNumber.charAt(i) == '1' && carry == 0){
                result += '0';
                carry = 1;
            }
            else if(lastBinaryNumber.charAt(i) == '0' && binaryNumber.charAt(i) == '1' && carry == 0){
                result += '1';
                carry = 0;
            }
            else if(lastBinaryNumber.charAt(i) == '1' && binaryNumber.charAt(i) == '0' && carry == 0){
                result += '1';
                carry = 0;
            }
            else if(lastBinaryNumber.charAt(i) == '0' && binaryNumber.charAt(i) == '0' && carry == 0){
                result += '0';
                carry = 0;
            }
            else if(lastBinaryNumber.charAt(i) == '1' && binaryNumber.charAt(i) == '1' && carry == 1){
                result += '1';
                carry = 1;
            }
            else if(lastBinaryNumber.charAt(i) == '0' && binaryNumber.charAt(i) == '1' && carry == 1){
                result += '0';
                carry = 1;
            }
            else if(lastBinaryNumber.charAt(i) == '1' && binaryNumber.charAt(i) == '0' && carry == 1){
                result += '0';
                carry = 1;
            }
            else if(lastBinaryNumber.charAt(i) == '0' && binaryNumber.charAt(i) == '0' && carry == 1){
                result += '1';
                carry = 0;
            }
        }
        reverser.append(result);
        reverser.reverse();
        result = String.valueOf(reverser);
        //System.out.println(result);
        return result;
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

        //System.out.println("\n\n" + complementedString);

        String compPlusOne = "";
        String one = "";
        if(input.length() == 9){
            one = "000000001";
        }
        else {
            one = "00000001";
        }
        StringBuilder finalString = new StringBuilder();
        int carry = 0;

        for(int j = 0; j < complementedString.length(); j++){
            if(complementedString.charAt(j) == '1' && one.charAt(j) == '1' && carry == 0){
                compPlusOne += '0';
                carry = 1;
            }
            else if(complementedString.charAt(j) == '1' && one.charAt(j) == '0' && carry == 0){
                compPlusOne += '1';
                carry = 0;
            }
            else if(complementedString.charAt(j) == '0' && one.charAt(j) == '1' && carry == 0){
                compPlusOne += '1';
                carry = 0;
            }
            else if(complementedString.charAt(j) == '0' && one.charAt(j) == '0' && carry == 0){
                compPlusOne += '0';
                carry = 0;
            }
            else if(complementedString.charAt(j) == '1' && one.charAt(j) == '1' && carry == 1){
                compPlusOne += '1';
                carry = 1;
            }
            else if(complementedString.charAt(j) == '1' && one.charAt(j) == '0' && carry == 1){
                compPlusOne += '0';
                carry = 1;
            }
            else if(complementedString.charAt(j) == '0' && one.charAt(j) == '1' && carry == 1){
                compPlusOne += '0';
                carry = 1;
            }
            else if(complementedString.charAt(j) == '0' && one.charAt(j) == '0' && carry == 1){
                compPlusOne += '1';
                carry = 0;
            }
        }

        finalString.append(compPlusOne);
        finalString.reverse();
        String doneString = finalString.toString();

        //System.out.println("\n\n" + doneString);

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