package _04_control_flow._03_code_block_if_else;

public class Main {
    public static void main(String[] args) {
        int score = 800;

        if (score < 5000 && score > 1000) {
            System.out.println("This was was less than 5000 but greater than 1000");
        } else if (score < 1000) {
            System.out.println("Your score was less than 1000");
        } else {
            System.out.println("Got here");
        }

    }
}
