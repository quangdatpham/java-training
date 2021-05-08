package _09_packages._02_scope;

public class ScopeCheck {
    public int publicVar = 0;
    private int varOne = 1;

    public ScopeCheck() {
        System.out.println("ScopeCheck created, publicVar = " + publicVar + ": varOne = " + varOne);
    }

    public int getVarOne() {
        return varOne;
    }

    public void useInner() {
        InnerClass innerClass = new InnerClass(7);
        System.out.println("varThree from outer class: " + innerClass.varThree);
    }

    // public static class InnerClass {
    public class InnerClass {
        private int varThree = 3;

        public InnerClass(int varThree) {
            this.varThree = varThree;
            System.out.println("InnerClass created, varOne is " + varOne + " and varThree is " + this.varThree);
        }

        public void printPublicVar() {
            System.out.println("publicVar: " + ScopeCheck.this.publicVar);
        }

        public void printVarThree() {
            System.out.println("varThree: " + this.varThree);
        }
    }
}
