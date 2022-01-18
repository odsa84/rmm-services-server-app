package com.ninjaone.test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ninjaone.test.dao.CustomerDeviceServiceDao;
import com.ninjaone.test.entities.Customer;
import com.ninjaone.test.entities.CustomerDeviceService;
import com.ninjaone.test.entities.CustomerDeviceServiceId;
import com.ninjaone.test.entities.Device;
import com.ninjaone.test.entities.Service;
import com.ninjaone.test.entities.TypeSysOperative;
import com.ninjaone.test.services.CustomerDeviceServiceSrv;

import static org.hamcrest.Matchers.hasSize;

/*import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;*/

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class CustomerDeviceServiceCtrlIntegrationTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	CustomerDeviceServiceDao cdsDao;
	
	@MockBean
	CustomerDeviceServiceSrv cdsSrv;
	
	ObjectMapper mapper = 
		    new ObjectMapper().registerModule(new JavaTimeModule())
		            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
	
	private static CustomerDeviceService cds;
	private static CustomerDeviceService cds1;
	
	@BeforeAll
	public static void setUp() {
		CustomerDeviceServiceId key = new CustomerDeviceServiceId(1, 2, 1);
		TypeSysOperative tso = new TypeSysOperative(1, "Windows Workstation");
		
		Customer customer = new Customer(1, "Osvaldo");
		Customer customer1 = new Customer(1, "Osvaldo");
		Device device = new Device(2, "android", tso);
		Device device1 = new Device(2, "android", tso);
		Service service = new Service(1, "Antivirus");
		Service service1 = new Service(1, "Antivirus");
		
		cds = new CustomerDeviceService(key, customer, device, service, 5, LocalDate.now());
		cds1 = new CustomerDeviceService(key, customer1, device1, service1, 7, LocalDate.now());
	}
	
	@Test
    public void postCustomerDeviceService_Create() throws Exception {
		
        given(cdsSrv.createCustomerService(Mockito.any(), Mockito.any(), Mockito.any())).willReturn(cds);

        mockMvc.perform(post("/customerService")
        		.contentType(MediaType.APPLICATION_JSON)
        		.content(mapper.writeValueAsBytes(cds)))
        		.andExpect(status().is(200));
        
        verify(cdsSrv, VerificationModeFactory.times(1)).createCustomerService(Mockito.any(), Mockito.any(), Mockito.any());
        reset(cdsSrv);
        
    }
	
	@Test
	public void getCustomerService_byCustomer() throws Exception {
		
		List<CustomerDeviceService> cdsLst = Arrays.asList(cds, cds1);
		
		given(cdsSrv.getServicesByCustomer(Mockito.anyLong())).willReturn(cdsLst);

        mockMvc.perform(get("/customerService/serviceByCustomer/1")
        		.contentType(MediaType.APPLICATION_JSON))
        		.andExpect(status().isOk())
        		.andExpect(jsonPath("$", hasSize(2)));
        
        verify(cdsSrv, VerificationModeFactory.times(1)).getServicesByCustomer(Mockito.anyLong());
        reset(cdsSrv);
	}
	
	@Test
	public void getCalculateMonthly() throws Exception {
		
		given(cdsSrv.calculateTotalMonthlyCost(1, LocalDate.of(2022, 1, 1) , LocalDate.of(2022, 1, 31))).willReturn((float) 17);
		
		mockMvc.perform(get("/customerService/calculateTotalMonthy/1/2022-01-01/2022-01-31")
        		.contentType(MediaType.APPLICATION_JSON))
        		.andExpect(status().isOk())
        		.andExpect(jsonPath("$.monthlyCost").value((float) 17.0));
        
        verify(cdsSrv, VerificationModeFactory.times(1)).calculateTotalMonthlyCost(Mockito.anyLong(), Mockito.any(), Mockito.any());
        reset(cdsSrv);
	}

}
