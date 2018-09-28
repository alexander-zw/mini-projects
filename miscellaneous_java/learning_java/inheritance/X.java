package inheritance;

public class X {
    
    public void tryUsingBVars() {

//        System.out.println(publicVar); // doesn't compile

        System.out.println(new B().publicVar); // works
        
        System.out.println(new B().protectedVar); // works??? WHY????

//        System.out.println(new B().privateVar); // doesn't compile
        
    }

}
