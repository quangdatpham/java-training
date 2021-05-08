package _02_datatypes;

import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {
        /*
            https://docs.oracle.com/javase/tutorial/java/nutsandbolts/datatypes.html

            Type            Size            Range of values that can be stored
            byte            1 byte          −128 to 127
            short           2 bytes         −32768 to 32767
            int             4 bytes         −2,147,483,648 to 2,147,483,647
            long            8 bytes         9,223,372,036,854,775,808 to 9,223,372,036,854,755,807
            float           4 bytes         3.4e−038 to 3.4e+038
            double          8 bytes         1.7e−308 to 1.7e+038
         */

        byte byteNum = 100;
        short shortNum = 23444;
        int intNum = 1000099999;
        long longNum = 50000 + (long) 1000000000 * (byteNum + shortNum + intNum);

        // inspecting using IntelliJ
        // byteNum = 0b01100100
        // shortNum = 0b01011011_10010100
        // intNum = 0b00111011_10011100_01010000_10011111
        // longNum = 0b00001101_11100001_00100111_00010000_01000000_00000101_11101001_01010000

        System.out.println("binary representation of byteNum = " + Integer.toBinaryString(byteNum));
        System.out.println("binary representation of shortNum = " + Integer.toBinaryString(shortNum));
        System.out.println("binary representation of intNum = " + Integer.toBinaryString(intNum));
        System.out.println("binary representation of longNum = " + Long.toBinaryString(longNum));


        System.out.println(longNum);
        System.out.println((new BigDecimal("1.2")).subtract(new BigDecimal("1.3")));
        System.out.println(2.7d % 1.3d);

        System.out.println(toMilesPerHour(1.5));
        System.out.println(toMilesPerHour(10.25));
        System.out.println(toMilesPerHour(-5.6));

    }

    public static int toMilesPerHour(double num) {
        return (int) num;
    }

    public interface Vehicle {
        default void run() {
            System.out.println("Hello World!");
        }
    }
}
