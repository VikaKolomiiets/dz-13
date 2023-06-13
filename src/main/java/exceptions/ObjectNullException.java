package exceptions;

public class ObjectNullException extends NullPointerException{
    public ObjectNullException(String s) {
        super(s + " can not be a null");
    }
}
