package exceptions;

public class NameException extends StringIndexOutOfBoundsException{

    public NameException(String s) {
        super(s + " this name does not contain more than one letter");
    }
}
