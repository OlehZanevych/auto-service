package org.lnu.software.testing.auto.service.controller.customer;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.lnu.software.testing.auto.service.dto.customer.BaseCustomerDto;
import org.lnu.software.testing.auto.service.dto.customer.CustomerDto;
import org.lnu.software.testing.auto.service.service.customer.CustomerService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class CustomerControllerTest {

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    private MockMvc mockMvc;


    @Before
    public void before() {
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    public void create() throws Exception {
        String expectedResponseBody = "{\"firstName\":\"Ivan\",\"middleName\":\"Ihorovych\"," +
                "\"lastName\":\"Krivyi\",\"phone\":\"95378457623\"," +
                "\"email\":\"Ivan.Ihorovych@gmail.com\",\"info\":\"Info abount Ivan Ihorovych:)\",\"id\":47}";

        String requestBody = "{\n" +
                "  \"firstName\": \"Ivan\",\n" +
                "  \"middleName\": \"Ihorovych\",\n" +
                "  \"lastName\": \"Krivyi\",\n" +
                "  \"email\": \"Ivan.Ihorovych@gmail.com\",\n" +
                "  \"phone\": \"095378457623\",\n" +
                "  \"info\": \"Info abount Ivan Ihorovych:)\"\n" +
                "}";


        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(47L);
        customerDto.setFirstName("Ivan");
        customerDto.setMiddleName("Ihorovych");
        customerDto.setLastName("Krivyi");
        customerDto.setEmail("Ivan.Ihorovych@gmail.com");
        customerDto.setPhone("95378457623");
        customerDto.setInfo("Info abount Ivan Ihorovych:)");

        when(customerService.create(any(BaseCustomerDto.class))).thenReturn(customerDto);

        String actualResponseBody = mockMvc.perform(post("/customers")
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated()).andReturn().getResponse().getContentAsString();

        assertEquals(expectedResponseBody, actualResponseBody);

        verify(customerService).create(any(BaseCustomerDto.class));
        verifyNoMoreInteractions(customerService);
    }
}
