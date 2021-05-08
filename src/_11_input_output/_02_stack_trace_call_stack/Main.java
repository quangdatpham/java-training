package _11_input_output._02_stack_trace_call_stack;

public class Main {
    public static void main(String[] args) {
        int x = 1;
        int y = 0;
        System.out.println(divideLBYL(x, y));
        System.out.println(divideEAFP(x, y));
        // java.lang.ArithmeticException: / by zero
        // System.out.println(divide(x, y));
    }

    private static int divideLBYL(int x, int y) {
        if (y != 0) {
            return x / y;
        } else {
            return 0;
        }
    }

    private static int divideEAFP(int x, int y) {
        try {
            return x / y;
        } catch (ArithmeticException e) {
            // System.out.println(e.getLocalizedMessage());
            System.out.println(e.getMessage());
            e.printStackTrace();
            return 0;
        }
    }

    private static int divide(int x, int y) {
        return x / y;
    }
}
