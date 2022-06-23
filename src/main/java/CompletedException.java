/**
 * In case we tried to add clicks to an Completed state AdSet an exception will be thrown to indicate that it is impossible to add more clicks
 * this class extends the Exception class
 */
public class CompletedException extends Exception{
    public CompletedException(){
        super("\n ***** This AdSet is Completed *****");
    }
}
