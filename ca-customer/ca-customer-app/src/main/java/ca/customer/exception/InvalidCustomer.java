package ca.customer.exception;

/**
 * @author vivek.
 * If the notification request is empty
 */
public class InvalidCustomer extends RuntimeException{

    public InvalidCustomer(String message) {
        super(message);
    }

}
