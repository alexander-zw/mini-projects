package ordinary;

public class LogicalOperators {

    public static void main(String[] args) {
        System.out.println(x() || y() && z());
        System.out.println(x() | y() && z());
        System.out.println(x() | y() & z());
    }

    public static boolean x() {
        System.out.println("x");
        return true;
    }

    public static boolean y() {
        System.out.println("y");
        return false;
    }

    public static boolean z() {
        System.out.println("z");
        return false;
    }

}
