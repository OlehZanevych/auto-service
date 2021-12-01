package org.lnu.software.testing.auto.service.controller.customer;

import io.swagger.annotations.ApiImplicitParam;
import lombok.AllArgsConstructor;
import org.lnu.software.testing.auto.service.annotation.Auth;
import org.lnu.software.testing.auto.service.dto.customer.BaseCustomerDto;
import org.lnu.software.testing.auto.service.dto.customer.CustomerDto;
import org.lnu.software.testing.auto.service.patch.CustomerPatch;
import org.lnu.software.testing.auto.service.service.customer.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Auth
@RestController
@AllArgsConstructor
@RequestMapping("customers")
public class CustomerController {

    private final CustomerService customerService;

    @Auth(isAdmin = true)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiImplicitParam(name = "Authorization", value = "JWT Token", required = true, paramType = "header", example = "Bearer access_token")
    public CustomerDto create(@RequestBody @Valid BaseCustomerDto customerDto) {
        return customerService.create(customerDto);
    }

    @GetMapping
    @ApiImplicitParam(name = "Authorization", value = "JWT Token", required = true, paramType = "header", example = "Bearer access_token")
    public List<CustomerDto> findAll() {
        return customerService.findAll();
    }

    @GetMapping("{id}")
    @ApiImplicitParam(name = "Authorization", value = "JWT Token", required = true, paramType = "header", example = "Bearer access_token")
    public CustomerDto find(@PathVariable Long id) {
        return customerService.find(id);
    }

    @Auth(isAdmin = true)
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiImplicitParam(name = "Authorization", value = "JWT Token", required = true, paramType = "header", example = "Bearer access_token")
    public void update(@PathVariable Long id, @RequestBody BaseCustomerDto customerDto) {
        customerService.update(id, customerDto);
    }

    @Auth(isAdmin = true)
    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiImplicitParam(name = "Authorization", value = "JWT Token", required = true, paramType = "header", example = "Bearer access_token")
    public void update(@PathVariable Long id, @RequestBody CustomerPatch customerPatch ) {
        customerService.patch(id, customerPatch);
    }

    @Auth(isAdmin = true)
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiImplicitParam(name = "Authorization", value = "JWT Token", required = true, paramType = "header", example = "Bearer access_token")
    public void delete(@PathVariable Long id) {
        customerService.delete(id);
    }
}
