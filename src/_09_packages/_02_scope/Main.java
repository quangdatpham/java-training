package _09_packages._02_scope;

public class Main {

    public static void main(String[] args) {
        String varFour = "this is private to main()";

        ScopeCheck scopeInstance = new ScopeCheck();
        scopeInstance.useInner();

        ScopeCheck.InnerClass innerClass1 = scopeInstance.new InnerClass(5);
        ScopeCheck.InnerClass innerClass2 = scopeInstance.new InnerClass(6);
        // System.out.println("varThree is not accessible here " + innerClass1.varThree);

        innerClass1.printVarThree();
        innerClass2.printVarThree();

        scopeInstance.publicVar = 2;

        innerClass1.printPublicVar();
        innerClass2.printPublicVar();
    }
}
