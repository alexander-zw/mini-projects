package inheritance;

public class B implements A {
    
    public C dependent;
    
    @SuppressWarnings("unused")
    private String privateVar = "private var"; // no one can use this
    
    protected String protectedVar = "protected var"; // can be used by subclasses
    
    public String publicVar = "public var"; // can be used by any class, but with . syntax
    
    @Override
    public void showWhoIAm() {
        System.out.println("I am B.");
    }

}
