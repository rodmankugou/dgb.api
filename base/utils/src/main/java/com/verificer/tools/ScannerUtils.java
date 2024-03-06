package com.verificer.tools;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * 读取控制台输入内容的工具累
 */
public class ScannerUtils {
    public static boolean chosenByYOrN(){
        Scanner s = new Scanner(System.in);
        int sum = 0;
        String input;
        System.out.print("please enter y or n to continue:");
        while(true){
            try {
                input = s.next();
                input = input.trim();
                if(input.equalsIgnoreCase("y"))
                    return true;
                else if(input.equalsIgnoreCase("n"))
                    return false;

            } catch (InputMismatchException e) {
               throw new RuntimeException(e.getMessage(),e);
            }
        }
    }
}
