package ca.customer.controllers;

import ca.customer.services.CustomerService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Vivek on 25-02-2017.
 */
@RestController
@RequestMapping("/entity/")
public class EntityController {

    private CustomerService customerService;


}
