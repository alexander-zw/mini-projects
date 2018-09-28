package exceptions;

public class CatchError {

    public static void main(String[] args) {
        
        try {
            throw new MyError();
        } catch(Error e) {
            e.printStackTrace();
        }
        
//        throw new MyError();
        try {
            throw new MyCheckedException(); // must catch or specify
        } catch (MyCheckedException e) {
            e.printStackTrace();
        }

    }

}
