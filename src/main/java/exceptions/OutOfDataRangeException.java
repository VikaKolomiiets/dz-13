package exceptions;

public class OutOfDataRangeException extends IllegalArgumentException{
    public OutOfDataRangeException(String s) {
        super(" is out of the date range.");
    }
}
