package org.lnu.software.testing.auto.service.controller.customer;

import lombok.AllArgsConstructor;
import org.lnu.software.testing.auto.service.dto.customer.BaseCustomerDto;
import org.lnu.software.testing.auto.service.dto.customer.CustomerDto;
import org.lnu.software.testing.auto.service.service.customer.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("customers")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDto create(@RequestBody BaseCustomerDto customerDto) {
        return customerService.create(customerDto);
    }

    @GetMapping
    public List<CustomerDto> findAll() {
        return customerService.findAll();
    }

    @GetMapping("{id}")
    public CustomerDto find(@PathVariable Long id) {
        return customerService.find(id);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long id, @RequestBody BaseCustomerDto customerDto) {
        customerService.update(id, customerDto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        customerService.delete(id);
    }
}
