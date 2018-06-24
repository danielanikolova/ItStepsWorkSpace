package FillMatrixTask;

public class IncorrectCoordinatesException extends Exception {
    
    
    public IncorrectCoordinatesException()
    {
        super("Coordinates are not correct.");
    }
    public String getMessage()
    {
        return super.getMessage();
    }

}
