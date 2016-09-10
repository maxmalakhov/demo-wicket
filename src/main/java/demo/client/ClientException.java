package demo.client;

/**
 * Created by Max Malakhov on 9/8/16.
 */
public class ClientException extends Exception {


    public ClientException(Exception ex) {
        super(ex);
    }

    public ClientException(String message) {
        super(message);
    }
}
