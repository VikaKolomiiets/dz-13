package exceptions;

public class ParentAgeException extends IllegalArgumentException{
    public ParentAgeException(String s1, String s2) {
        super("Dear "+s1+ " " + s2 + " both Parent have to be more than 18 years old.");
    }

    public ParentAgeException() {
        super("Woman have to be older than baby at least on 12 years");
    }
}
