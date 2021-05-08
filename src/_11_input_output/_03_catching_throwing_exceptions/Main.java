package _11_input_output._03_catching_throwing_exceptions;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int x, y, z = 1;
        // try {
        //     x = getInt();
        //     y = getInt();
        //     z = x / y;
        // } catch (NoSuchElementException e) {
        //     // exception in catch block
        //     // x = getInt();
        //     // System.out.println("NoSuchElementException");
        //
        //     // throwing an exception
        //     throw new NoSuchElementException("no suitable input");
        // } catch (ArithmeticException e) {
        //     throw new ArithmeticException("attempt to divide by zero");
        // }

        try {
            x = getInt();
            y = getInt();
            z = x / y;
        } catch (NoSuchElementException | ArithmeticException e) {
            System.out.println(e.toString());
            System.out.println("Unable to perform operation, autopilot shutting down");
        }

        System.out.println(z);
    }

    public static int getInt() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter a number");
        while (true) {
            try {
                return sc.nextInt();
            } catch (InputMismatchException e) {
                sc.nextLine();
                System.out.println("Please enter a number");
            }
        }
    }
}
