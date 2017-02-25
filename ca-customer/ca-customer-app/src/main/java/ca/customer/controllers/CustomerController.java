package ca.customer.controllers;

import ca.customer.api.Customer;
import ca.customer.exception.InvalidCustomer;
import ca.customer.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/customer/")
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    @RequestMapping(path = "create", method = RequestMethod.POST)
    public Customer create(@Validated @RequestBody Customer customer)  {
        return customerService.createCustomer(customer);
    }

    @RequestMapping(path = "customer/{customerId}", method = RequestMethod.GET)
    public Customer getCustomer(@RequestParam Long customerId) throws IOException {
        if(customerId==null){
            throw new InvalidCustomer("The notification id is invalid");
        }
        return customerService.getCustomer(customerId);
    }

    @RequestMapping(path = "customers", method = RequestMethod.GET)
    public List<Customer> getCustomers() throws IOException {
        return customerService.getCustomerList();
    }

    @RequestMapping(path = "deactivate", method = RequestMethod.PUT)
    public Customer deactivateCustomer(Long customerId) throws IOException {
        if(customerId==null ){
            throw new InvalidCustomer("The notification id is invalid");
        }
        return customerService.deactivateCustomer(customerId);
    }

}
