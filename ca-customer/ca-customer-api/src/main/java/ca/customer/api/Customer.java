package ca.customer.api;

/**
 * @author vivek.agrawal.
 */
public class Customer {

    public Long id;

    public Name name=new Name();

    public Address address=new Address();

    public String mobile;

    public String email;

    public Status status;

    public Rating rating;

    public Long entityId;

}
