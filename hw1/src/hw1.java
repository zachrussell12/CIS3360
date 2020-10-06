import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.Scanner;

/*=============================================================================
| Assignment: HW 01 – Encrypting a plaintext file using the Hill cipher in the key file
|
| Author: Zachary Russell
| Language: Java
|
| To Compile: javac Hw1.java
|
| To Execute: java Hw1 hillcipherkey.txt plaintextfile.txt
| where the files in the command line are in the current directory.
| The key text contains a single digit on the first line defining the size of the key
| while the remaining lines define the key, for example:
| 3
| 1 2 3
| 4 5 6
| 7 8 9
| The plain text file contains the plain text in mixed case with spaces & punctuation.
|
| Class: CIS3360 - Security in Computing - Fall 2020
| Instructor: McAlpin
| Due Date: per 10/08/2020
|
+=============================================================================*/

public class hw1 {
    public static void main(String[] args) {
        File input1 = new File(String.valueOf(args[0]));
        File input2 = new File(String.valueOf(args[1]));

        try {

            Scanner readFile = new Scanner(input1);
            Scanner readFile2 = new Scanner(input2);

            List<String> keyFile = new ArrayList<>();

            ////////////////////////////////////Key File Read and Echo Section//////////////////////////////////////////

            System.out.println("\n\nKey matrix: \n");
            int matrixSize = Integer.parseInt(readFile.next());
            //System.out.println(matrixSize);

            while (readFile.hasNext()) {
                String plaintextS = readFile.next();
                keyFile.add(plaintextS);
            }

            readFile.close();

            String[] keyFileArray = keyFile.toArray(new String[0]);

            int i = 0;
            String matrix = "";
            String space = "  ";

            for (String s : keyFileArray) {

                if (matrixSize == 1) {
                    if (i == 1 || i == 2 || i == 3 || i == 4 || i == 5 || i == 6 || i == 7 || i == 8 || i == 9 || i == 10) {
                        System.out.printf(space + "\n");
                    }
                } else if (matrixSize == 2) {
                    if (i == 2 || i == 4 || i == 6 || i == 8 || i == 10 || i == 12 || i == 14 || i == 16 || i == 18 || i == 20) {
                        System.out.printf("  " + "\n");
                    }
                } else if (matrixSize == 3) {
                    if (i == 3 || i == 6 || i == 9 || i == 12 || i == 15 || i == 18 || i == 21 || i == 24 || i == 27 || i == 30) {
                        System.out.printf("  " + "\n");
                    }
                } else if (matrixSize == 4) {
                    if (i == 4 || i == 8 || i == 12 || i == 16 || i == 20 || i == 24 || i == 28 || i == 32 || i == 36 || i == 40) {
                        System.out.printf("  " + "\n");
                    }
                } else if (matrixSize == 5) {
                    if (i == 5 || i == 10 || i == 15 || i == 20 || i == 25 || i == 30 || i == 35 || i == 40 || i == 45 || i == 50) {
                        System.out.printf("%s"+ "\n", space);
                    }
                } else if (matrixSize == 6) {
                    if (i == 6 || i == 12 || i == 18 || i == 24 || i == 30 || i == 36 || i == 42 || i == 48 || i == 54 || i == 60) {
                        System.out.printf("  " + "\n");
                    }
                } else if (matrixSize == 7) {
                    if (i == 7 || i == 14 || i == 21 || i == 28 || i == 35 || i == 42 || i == 49 || i == 56 || i == 63 || i == 70) {
                        System.out.printf("  " + "\n");
                    }
                } else if (matrixSize == 8) {
                    if (i == 8 || i == 16 || i == 24 || i == 32 || i == 40 || i == 48 || i == 56 || i == 64 || i == 72 || i == 80) {
                        System.out.printf("  " + "\n");
                    }
                } else if (matrixSize == 9) {
                    if (i == 9 || i == 18 || i == 27 || i == 36 || i == 45 || i == 54 || i == 63 || i == 72 || i == 81 || i == 90) {
                        System.out.printf("  " + "\n");
                    }
                } else if (matrixSize == 10) {
                    if (i == 10 || i == 20 || i == 30 || i == 40 || i == 50 || i == 60 || i == 70 || i == 80 || i == 90 || i == 100) {
                        System.out.printf("  " + "\n");
                    }
                }
                matrix = matrix + s;
                System.out.printf("%s" + "  ", s);
                i++;
            }

            /////////////////////////////////////////////Text File Read and Echo////////////////////////////////////////////////////////


            List<String> plaintext = new ArrayList<>();
            System.out.print("\n");
            System.out.println("\n\nPlaintext: ");

            while (readFile2.hasNext()) {
                String plaintextS = readFile2.next();
                plaintext.add(plaintextS);
            }

            readFile2.close();

            String[] plaintextArray = plaintext.toArray(new String[0]);


            String plaintextLower = "";

            for (String plaintextConv : plaintextArray) {
                plaintextLower = plaintextLower + plaintextConv.toLowerCase();
                plaintextLower = plaintextLower.replaceAll("[^a-zA-Z]", "");

            }

            while(!((plaintextLower.length() % matrixSize) == 0)){
                plaintextLower += "x";
            }

            int upcount = 0;

            for(int y = 0; y < plaintextLower.length(); y++){
                if(upcount%80 == 0 ){
                    System.out.print("\n");
                }
                System.out.print(plaintextLower.charAt(y));
                upcount++;
            }

            System.out.print("\n");

            /////////////////////////////////Start Hill Cipher Encryption///////////////////////////////////////////////

            System.out.println("\n\nCiphertext:");
            cipherEncrypt(plaintextLower, matrix, matrixSize);

        } catch (FileNotFoundException errorCode) {
            System.out.println("Unable to read file");
        }


    }

    public static void cipherEncrypt(String encryptText, String matrix, int size) {
        char[] m = new char[encryptText.length()];

        //int[] cipherNums = new int[encryptText.length()];

        int [] cipherNums = convertTexttoNum(encryptText, size);

        for(int i = 0; i < matrix.length(); i++){
            m[i] = matrix.charAt(i);
        }

        //System.out.print("\n");
        /*for (int cipherNum : cipherNums) {
            System.out.print(cipherNum + " ");
        }*/

        //System.out.println("\n\n");


        int split = (int)Math.ceil((double)cipherNums.length / size);


        int[][] splitArrays = new int[split][size];

        for(int k = 0; k < split; ++k){
            int b = k * size;
            int numL = Math.min(cipherNums.length - b, size);

            int[] temporary = new int[numL];
            System.arraycopy(cipherNums, b, temporary, 0, numL);
            splitArrays[k] = temporary;
            //System.out.print(Arrays.toString(splitArrays[k]));
        }

        //System.out.println("\n\n");
        int[][] encryptedNums = new int[splitArrays.length][size];
        int counter = 0;
        int counter2 = 0;

        if(size == 1){
            for(int j = 0; j < splitArrays.length; j++){
                for(int f = 0; f < 1; f++){

                    encryptedNums[j][f] = (((Integer.parseInt(String.valueOf(matrix.charAt(counter2))) * Integer.parseInt(String.valueOf(splitArrays[j][counter]))))%26);
                    counter = 0;
                    counter2 += size;
                    if(counter2%1 == 0){
                        counter2 = 0;
                    }
                }
            }
        }
        else if(size == 2){
            for(int j = 0; j < splitArrays.length; j++){
                for(int f = 0; f < 2; f++){

                    encryptedNums[j][f] = (((Integer.parseInt(String.valueOf(matrix.charAt(counter2))) * Integer.parseInt(String.valueOf(splitArrays[j][counter]))) + ((Integer.parseInt(String.valueOf(matrix.charAt(counter2+1))) * Integer.parseInt(String.valueOf(splitArrays[j][counter+1])))))%26);
                    counter = 0;
                    counter2 += size;
                    if(counter2%4 == 0){
                        counter2 = 0;
                    }
                }
            }
        }
        else if(size == 3) {
            for(int j = 0; j < splitArrays.length; j++){
                for(int f = 0; f < 3; f++){

                    encryptedNums[j][f] = (((Integer.parseInt(String.valueOf(matrix.charAt(counter2))) * Integer.parseInt(String.valueOf(splitArrays[j][counter]))) + ((Integer.parseInt(String.valueOf(matrix.charAt(counter2+1))) * Integer.parseInt(String.valueOf(splitArrays[j][counter+1])))) + (Integer.parseInt(String.valueOf(matrix.charAt(counter2+2))) * Integer.parseInt(String.valueOf(splitArrays[j][counter+2]))))%26);
                    counter = 0;
                    counter2 += size;
                    if(counter2%9 == 0){
                        counter2 = 0;
                    }
                }
            }
        }
        else if(size == 4){
            for(int j = 0; j < splitArrays.length; j++){
                for(int f = 0; f < 4; f++){

                    encryptedNums[j][f] = (((Integer.parseInt(String.valueOf(matrix.charAt(counter2))) * Integer.parseInt(String.valueOf(splitArrays[j][counter]))) + ((Integer.parseInt(String.valueOf(matrix.charAt(counter2+1))) * Integer.parseInt(String.valueOf(splitArrays[j][counter+1])))) + (Integer.parseInt(String.valueOf(matrix.charAt(counter2+2))) * Integer.parseInt(String.valueOf(splitArrays[j][counter+2]))) + (Integer.parseInt(String.valueOf(matrix.charAt(counter2+3))) * Integer.parseInt(String.valueOf(splitArrays[j][counter+3]))))%26);
                    counter = 0;
                    counter2 += size;
                    if(counter2%16 == 0){
                        counter2 = 0;
                    }

                }
            }
        }
        else if(size == 5){
            for(int j = 0; j < splitArrays.length; j++){
                for(int f = 0; f < 5; f++){

                    encryptedNums[j][f] = (((Integer.parseInt(String.valueOf(matrix.charAt(counter2))) * Integer.parseInt(String.valueOf(splitArrays[j][counter]))) + ((Integer.parseInt(String.valueOf(matrix.charAt(counter2+1))) * Integer.parseInt(String.valueOf(splitArrays[j][counter+1])))) + (Integer.parseInt(String.valueOf(matrix.charAt(counter2+2))) * Integer.parseInt(String.valueOf(splitArrays[j][counter+2]))) + (Integer.parseInt(String.valueOf(matrix.charAt(counter2+3))) * Integer.parseInt(String.valueOf(splitArrays[j][counter+3]))) + (Integer.parseInt(String.valueOf(matrix.charAt(counter2+4))) * Integer.parseInt(String.valueOf(splitArrays[j][counter+4]))))%26);
                    counter = 0;
                    counter2 += size;
                    if(counter2%25 == 0){
                        counter2 = 0;
                    }

                }
            }
        }
        else if(size == 6){
            for(int j = 0; j < splitArrays.length; j++){
                for(int f = 0; f < 6; f++){

                    encryptedNums[j][f] = (((Integer.parseInt(String.valueOf(matrix.charAt(counter2))) * Integer.parseInt(String.valueOf(splitArrays[j][counter]))) + ((Integer.parseInt(String.valueOf(matrix.charAt(counter2+1))) * Integer.parseInt(String.valueOf(splitArrays[j][counter+1])))) + (Integer.parseInt(String.valueOf(matrix.charAt(counter2+2))) * Integer.parseInt(String.valueOf(splitArrays[j][counter+2]))) + (Integer.parseInt(String.valueOf(matrix.charAt(counter2+3))) * Integer.parseInt(String.valueOf(splitArrays[j][counter+3]))) + (Integer.parseInt(String.valueOf(matrix.charAt(counter2+4))) * Integer.parseInt(String.valueOf(splitArrays[j][counter+4]))) + (Integer.parseInt(String.valueOf(matrix.charAt(counter2+5))) * Integer.parseInt(String.valueOf(splitArrays[j][counter+5]))))%26);
                    counter = 0;
                    counter2 += size;
                    if(counter2%36 == 0){
                        counter2 = 0;
                    }

                }
            }
        }
        else if(size == 7){
            for(int j = 0; j < splitArrays.length; j++){
                for(int f = 0; f < 7; f++){

                    encryptedNums[j][f] = (((Integer.parseInt(String.valueOf(matrix.charAt(counter2))) * Integer.parseInt(String.valueOf(splitArrays[j][counter]))) + ((Integer.parseInt(String.valueOf(matrix.charAt(counter2+1))) * Integer.parseInt(String.valueOf(splitArrays[j][counter+1])))) + (Integer.parseInt(String.valueOf(matrix.charAt(counter2+2))) * Integer.parseInt(String.valueOf(splitArrays[j][counter+2]))) + (Integer.parseInt(String.valueOf(matrix.charAt(counter2+3))) * Integer.parseInt(String.valueOf(splitArrays[j][counter+3]))) + (Integer.parseInt(String.valueOf(matrix.charAt(counter2+4))) * Integer.parseInt(String.valueOf(splitArrays[j][counter+4]))) + (Integer.parseInt(String.valueOf(matrix.charAt(counter2+5))) * Integer.parseInt(String.valueOf(splitArrays[j][counter+5]))) + (Integer.parseInt(String.valueOf(matrix.charAt(counter2+6))) * Integer.parseInt(String.valueOf(splitArrays[j][counter+6]))))%26);
                    counter = 0;
                    counter2 += size;
                    if(counter2%49 == 0){
                        counter2 = 0;
                    }

                }
            }
        }
        else if(size == 8){
            for(int j = 0; j < splitArrays.length; j++){
                for(int f = 0; f < 8; f++){

                    encryptedNums[j][f] = (((Integer.parseInt(String.valueOf(matrix.charAt(counter2))) * Integer.parseInt(String.valueOf(splitArrays[j][counter]))) + ((Integer.parseInt(String.valueOf(matrix.charAt(counter2+1))) * Integer.parseInt(String.valueOf(splitArrays[j][counter+1])))) + (Integer.parseInt(String.valueOf(matrix.charAt(counter2+2))) * Integer.parseInt(String.valueOf(splitArrays[j][counter+2]))) + (Integer.parseInt(String.valueOf(matrix.charAt(counter2+3))) * Integer.parseInt(String.valueOf(splitArrays[j][counter+3]))) + (Integer.parseInt(String.valueOf(matrix.charAt(counter2+4))) * Integer.parseInt(String.valueOf(splitArrays[j][counter+4]))) + (Integer.parseInt(String.valueOf(matrix.charAt(counter2+5))) * Integer.parseInt(String.valueOf(splitArrays[j][counter+5]))) + (Integer.parseInt(String.valueOf(matrix.charAt(counter2+6))) * Integer.parseInt(String.valueOf(splitArrays[j][counter+6]))) + (Integer.parseInt(String.valueOf(matrix.charAt(counter2+7))) * Integer.parseInt(String.valueOf(splitArrays[j][counter+7]))))%26);
                    counter = 0;
                    counter2 += size;
                    if(counter2%64 == 0){
                        counter2 = 0;
                    }

                }
            }
        }
        else if(size == 9){
            for(int j = 0; j < splitArrays.length; j++){
                for(int f = 0; f < 9; f++){

                    encryptedNums[j][f] = (((Integer.parseInt(String.valueOf(matrix.charAt(counter2))) * Integer.parseInt(String.valueOf(splitArrays[j][counter]))) + ((Integer.parseInt(String.valueOf(matrix.charAt(counter2+1))) * Integer.parseInt(String.valueOf(splitArrays[j][counter+1])))) + (Integer.parseInt(String.valueOf(matrix.charAt(counter2+2))) * Integer.parseInt(String.valueOf(splitArrays[j][counter+2]))) + (Integer.parseInt(String.valueOf(matrix.charAt(counter2+3))) * Integer.parseInt(String.valueOf(splitArrays[j][counter+3]))) + (Integer.parseInt(String.valueOf(matrix.charAt(counter2+4))) * Integer.parseInt(String.valueOf(splitArrays[j][counter+4]))) + (Integer.parseInt(String.valueOf(matrix.charAt(counter2+5))) * Integer.parseInt(String.valueOf(splitArrays[j][counter+5]))) + (Integer.parseInt(String.valueOf(matrix.charAt(counter2+6))) * Integer.parseInt(String.valueOf(splitArrays[j][counter+6]))) + (Integer.parseInt(String.valueOf(matrix.charAt(counter2+7))) * Integer.parseInt(String.valueOf(splitArrays[j][counter+7]))) + (Integer.parseInt(String.valueOf(matrix.charAt(counter2+8))) * Integer.parseInt(String.valueOf(splitArrays[j][counter+8]))))%26);
                    counter = 0;
                    counter2 += size;
                    if(counter2%81 == 0){
                        counter2 = 0;
                    }

                }
            }
        }
        else if(size == 10){
            for(int j = 0; j < splitArrays.length; j++){
                for(int f = 0; f < 10; f++){

                    encryptedNums[j][f] = (((Integer.parseInt(String.valueOf(matrix.charAt(counter2))) * Integer.parseInt(String.valueOf(splitArrays[j][counter]))) + ((Integer.parseInt(String.valueOf(matrix.charAt(counter2+1))) * Integer.parseInt(String.valueOf(splitArrays[j][counter+1])))) + (Integer.parseInt(String.valueOf(matrix.charAt(counter2+2))) * Integer.parseInt(String.valueOf(splitArrays[j][counter+2]))) + (Integer.parseInt(String.valueOf(matrix.charAt(counter2+3))) * Integer.parseInt(String.valueOf(splitArrays[j][counter+3]))) + (Integer.parseInt(String.valueOf(matrix.charAt(counter2+4))) * Integer.parseInt(String.valueOf(splitArrays[j][counter+4]))) + (Integer.parseInt(String.valueOf(matrix.charAt(counter2+5))) * Integer.parseInt(String.valueOf(splitArrays[j][counter+5]))) + (Integer.parseInt(String.valueOf(matrix.charAt(counter2+6))) * Integer.parseInt(String.valueOf(splitArrays[j][counter+6]))) + (Integer.parseInt(String.valueOf(matrix.charAt(counter2+7))) * Integer.parseInt(String.valueOf(splitArrays[j][counter+7]))) + (Integer.parseInt(String.valueOf(matrix.charAt(counter2+8))) * Integer.parseInt(String.valueOf(splitArrays[j][counter+8]))) + (Integer.parseInt(String.valueOf(matrix.charAt(counter2+9))) * Integer.parseInt(String.valueOf(splitArrays[j][counter+9]))))%26);
                    counter = 0;
                    counter2 += size;
                    if(counter2%100 == 0){
                        counter2 = 0;
                    }

                }
            }
        }

        /*for (int w= 0; w < encryptedNums.length; w++) {
            System.out.print(Arrays.toString(encryptedNums[w]) + " ");
        }*/

        convertNumtoText(encryptedNums, size);

    }

    public static String convertNumtoText(int[][] encryptedNums, int size){
        String cipherTextFinal = "";

        for(int i = 0; i < encryptedNums.length; i++){
            for(int j = 0; j < size; j++) {
                if (encryptedNums[i][j] == 0) {
                    cipherTextFinal += 'a';
                } else if (encryptedNums[i][j] == 1) {
                    cipherTextFinal += 'b';
                } else if (encryptedNums[i][j] == 2) {
                    cipherTextFinal += 'c';
                } else if (encryptedNums[i][j] == 3) {
                    cipherTextFinal += 'd';
                } else if (encryptedNums[i][j] == 4) {
                    cipherTextFinal += 'e';
                } else if (encryptedNums[i][j] == 5) {
                    cipherTextFinal += 'f';
                } else if (encryptedNums[i][j] == 6) {
                    cipherTextFinal += 'g';
                } else if (encryptedNums[i][j] == 7) {
                    cipherTextFinal += 'h';
                } else if (encryptedNums[i][j] == 8) {
                    cipherTextFinal += 'i';
                } else if (encryptedNums[i][j] == 9) {
                    cipherTextFinal += 'j';
                } else if (encryptedNums[i][j] == 10) {
                    cipherTextFinal += 'k';
                } else if (encryptedNums[i][j] == 11) {
                    cipherTextFinal += 'l';
                } else if (encryptedNums[i][j] == 12) {
                    cipherTextFinal += 'm';
                } else if (encryptedNums[i][j] == 13) {
                    cipherTextFinal += 'n';
                } else if (encryptedNums[i][j] == 14) {
                    cipherTextFinal += 'o';
                } else if (encryptedNums[i][j] == 15) {
                    cipherTextFinal += 'p';
                } else if (encryptedNums[i][j] == 16) {
                    cipherTextFinal += 'q';
                } else if (encryptedNums[i][j] == 17) {
                    cipherTextFinal += 'r';
                } else if (encryptedNums[i][j] == 18) {
                    cipherTextFinal += 's';
                } else if (encryptedNums[i][j] == 19) {
                    cipherTextFinal += 't';
                } else if (encryptedNums[i][j] == 20) {
                    cipherTextFinal += 'u';
                } else if (encryptedNums[i][j] == 21) {
                    cipherTextFinal += 'v';
                } else if (encryptedNums[i][j] == 22) {
                    cipherTextFinal += 'w';
                } else if (encryptedNums[i][j] == 23) {
                    cipherTextFinal += 'x';
                } else if (encryptedNums[i][j] == 24) {
                    cipherTextFinal += 'y';
                } else if (encryptedNums[i][j] == 25) {
                    cipherTextFinal += 'z';
                }
            }
        }

        int upcount = 0;

        for(int y = 0; y < cipherTextFinal.length(); y++){
            if(upcount%80 == 0){
                System.out.print("\n");
            }
            System.out.print(cipherTextFinal.charAt(y));
            upcount++;
        }

        System.out.println("\n");

        return cipherTextFinal;
    }

    public static int[] convertTexttoNum(String encryptText, int size){
        while(!((encryptText.length() % size) == 0)){
            encryptText += "x";
        }

        int[] cipherNums = new int[encryptText.length()];

        for(int i = 0; i < encryptText.length(); i++){
            if(encryptText.charAt(i) == '0' || encryptText.charAt(i) == '1' || encryptText.charAt(i) == '2' || encryptText.charAt(i) == '3' || encryptText.charAt(i) == '4' || encryptText.charAt(i) == '5' || encryptText.charAt(i) == '6' || encryptText.charAt(i) == '7' || encryptText.charAt(i) == '8' || encryptText.charAt(i) == '9'){
                cipherNums[i] = Integer.parseInt(String.valueOf(encryptText.charAt(i)));
            }
            else if(encryptText.charAt(i) == 'a'){
                cipherNums[i] = 0;
            }
            else if(encryptText.charAt(i) == 'b'){
                cipherNums[i] = 1;
            }
            else if(encryptText.charAt(i) == 'c'){
                cipherNums[i] = 2;
            }
            else if(encryptText.charAt(i) == 'd'){
                cipherNums[i] = 3;
            }
            else if(encryptText.charAt(i) == 'e'){
                cipherNums[i] = 4;
            }
            else if(encryptText.charAt(i) == 'f'){
                cipherNums[i] = 5;
            }
            else if(encryptText.charAt(i) == 'g'){
                cipherNums[i] = 6;
            }
            else if(encryptText.charAt(i) == 'h'){
                cipherNums[i] = 7;
            }
            else if(encryptText.charAt(i) == 'i'){
                cipherNums[i] = 8;
            }
            else if(encryptText.charAt(i) == 'j'){
                cipherNums[i] = 9;
            }
            else if(encryptText.charAt(i) == 'k'){
                cipherNums[i] = 10;
            }
            else if(encryptText.charAt(i) == 'l'){
                cipherNums[i] = 11;
            }
            else if(encryptText.charAt(i) == 'm'){
                cipherNums[i] = 12;
            }
            else if(encryptText.charAt(i) == 'n'){
                cipherNums[i] = 13;
            }
            else if(encryptText.charAt(i) == 'o'){
                cipherNums[i] = 14;
            }
            else if(encryptText.charAt(i) == 'p'){
                cipherNums[i] = 15;
            }
            else if(encryptText.charAt(i) == 'q'){
                cipherNums[i] = 16;
            }
            else if(encryptText.charAt(i) == 'r'){
                cipherNums[i] = 17;
            }
            else if(encryptText.charAt(i) == 's'){
                cipherNums[i] = 18;
            }
            else if(encryptText.charAt(i) == 't'){
                cipherNums[i] = 19;
            }
            else if(encryptText.charAt(i) == 'u'){
                cipherNums[i] = 20;
            }
            else if(encryptText.charAt(i) == 'v'){
                cipherNums[i] = 21;
            }
            else if(encryptText.charAt(i) == 'w'){
                cipherNums[i] = 22;
            }
            else if(encryptText.charAt(i) == 'x'){
                cipherNums[i] = 23;
            }
            else if(encryptText.charAt(i) == 'y'){
                cipherNums[i] = 24;
            }else if(encryptText.charAt(i) == 'z'){
                cipherNums[i] = 25;
            }
        }

        /*for (int cipherNum : cipherNums) {
            System.out.print(cipherNum + " ");
        }*/

        return cipherNums;
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
