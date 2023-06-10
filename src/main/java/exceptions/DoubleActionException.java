package exceptions;

public class DoubleActionException extends IllegalArgumentException{
    public DoubleActionException(String s1, String s2) {
        super(s1 + " " + s2 + "+\" can not married twice!\"");
    }
    public DoubleActionException(){
        super("The Last name cannot be changed for both partners at one time");
    }
}
