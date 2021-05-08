package _04_control_flow._08_while_loop;

public class Main {
    public static void main(String[] args) {
        int count = 6;
        while (count != 6) {
            System.out.println("Count value is " + count);
            count++;
        }

        System.out.println("==================================");

        count = 6;
        do {
            System.out.println("Count value was " + count);
            count++;

            if (count > 100) {
                break;
            }

        } while (count != 6);
    }
}
