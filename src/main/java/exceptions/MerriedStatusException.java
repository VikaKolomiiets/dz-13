package exceptions;

public class MerriedStatusException extends IllegalArgumentException{
    public MerriedStatusException(String s1, String s2 ) {
        super(s1 + " " + s2 +" your status have to be 'Married' for those action.");
    }
}
