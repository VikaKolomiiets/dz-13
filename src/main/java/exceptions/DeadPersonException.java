package exceptions;

public class DeadPersonException extends NullPointerException{
    public DeadPersonException(String s1, String s2) {
        super(s1 + " " + s2 + "is a DEAD person!");
    }
}
