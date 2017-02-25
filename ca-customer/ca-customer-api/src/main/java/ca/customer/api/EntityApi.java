package ca.customer.api;

import java.util.List;

/**
 * @author vivek.agrawal.
 */
public class EntityApi {

    public Long id;

    public Name name=new Name();

    public Address address=new Address();

    public String mobile;

    public String email;

    public List<Customer> customers;

    public Status status;

}
