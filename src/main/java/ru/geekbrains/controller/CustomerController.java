package ru.geekbrains.controller;

import ru.geekbrains.controller.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.geekbrains.persist.entity.Customer;
import ru.geekbrains.persist.repo.CustomerRepository;
import ru.geekbrains.persist.repo.ProductRepository;
import ru.geekbrains.persist.repo.CustomerRepo;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    private final static Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private  CustomerRepo customerRepo;

    @GetMapping
    public String all(Model model,
                              @RequestParam(value = "first_name", required = false) String first_name,
                              @RequestParam(value = "family_name", required = false) String family_name,
                              @RequestParam("page") Optional<Integer> page,
                              @RequestParam("size") Optional<Integer> size       ) {

            logger.info("Filtering by name: {}", first_name);
        List<Customer> customerList;
//        customerList = customerRepository.findByName3(first_name,family_name);
        if ((first_name == null || first_name.isEmpty()) && (family_name == null || family_name.isEmpty())) {
           customerList = customerRepository.findAll();
        }
        else{
            customerList = customerRepository.findByNameAndFamily_name(first_name,family_name);
//            customerList=customerRepository.findByName(first_name);
//            customerList=customerRepository.findByName2();
        }
        model.addAttribute("customers", customerList);
        return "customers";
    }
    @GetMapping("/new_customer")
    public String addCustomer( Model model ) {
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        return "new_customer";
    }
    @GetMapping("/{id}")
    public String editCustomer(@PathVariable("id") Integer id, Model model) throws Exception {
        Customer customer = customerRepository.findById(id).orElseThrow(Exception::new);
        model.addAttribute("customer", customer);
        return "edit_customer";
    }
    @PostMapping("/new")
    public String addCustomer(Model model, Customer customer, BindingResult bindingResult) {
       customerRepository.save(customer);
//        List<Customer> customerList= customerRepository.findAll();
//        model.addAttribute("customers", customerList);
//        return "customers";
        return "redirect:/customers";
    }
    @PostMapping("/update")
    public String updateCustomer(Model model, Customer customer, BindingResult bindingResult) {
        customerRepository.save(customer);
//        List<Customer> customerList= customerRepository.findAll();
//        model.addAttribute("customers", customerList);
        return "redirect:/customers";
    }
    @DeleteMapping("/{id}/delete")
    public String deleteUser(@PathVariable("id") Integer id,Model model) {
        customerRepository.deleteById(id);
//        List<Customer> customerList= customerRepository.findAll();
//        model.addAttribute("customers", customerList);
//        return "customers";
        return "redirect:/customers";
    }

//    @ExceptionHandler
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public ModelAndView notFoundExeptionHandller (NotFoundException exception){
//
//        ModelAndView modelAndView = new ModelAndView("ecxeption");
//
//        modelAndView.getModel().put("customer", exception.getCustomer());
//
//            return modelAndView;
//        }


    }


