package ca.customer.controllers;

import ca.customer.api.Customer;
import ca.customer.api.EntityApi;
import ca.customer.exception.InvalidCustomer;
import ca.customer.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * Created by Vivek on 25-02-2017.
 */
@RestController
@RequestMapping("/entity/")
public class EntityController {

    private CustomerService customerService;


    @Autowired
    public EntityController(CustomerService customerService){
        this.customerService = customerService;
    }

    @RequestMapping(path = "create/", method = RequestMethod.POST)
    public EntityApi create(@Validated @RequestBody EntityApi entityApi)  {
        return customerService.createEntity(entityApi);
    }

    @RequestMapping(path = "{entityId}", method = RequestMethod.GET)
    public EntityApi getCustomer(@PathVariable Long entityId) throws IOException {
        if(entityId==null){
            throw new InvalidCustomer("The notification id is invalid");
        }
        return customerService.getEntity(entityId);
    }

    @RequestMapping(path = "entities", method = RequestMethod.GET)
    public List<EntityApi> getEntities() throws IOException {
        return customerService.getEntityList();
    }

    @RequestMapping(path = "deactivate", method = RequestMethod.PUT)
    public EntityApi deactivateEntity(@RequestParam Long entityId) throws IOException {
        if(entityId==null ){
            throw new InvalidCustomer("The notification id is invalid");
        }
        return customerService.deactivateEntity(entityId);
    }



}
