package inheritance;

public class F extends E {
    
    public void tryUsingSuperVars() {
        
        System.out.println(publicVar); // works
        
        System.out.println(protectedVar); // works
        
//        System.out.println(privateVar); // doesn't compile
        
    }

    public static void main(String[] args) {
        A a = new F();
        a.showWhoIAm(); // multiple polymorphism
        
        X x = new X();
//        B b = (B) x; // cannot compile
        try {
            a = (A) x; // interface cast always acceptable at compile-time
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        System.out.println();
        
        x.tryUsingBVars();
        System.out.println();
        
        ((F) a).tryUsingSuperVars();
        
    }

}
