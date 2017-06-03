package com.github.izhangzhihao.SSMSeedProject.IntegrationTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.izhangzhihao.SSMSeedProject.Application;
import com.github.izhangzhihao.SSMSeedProject.Model.Address;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static com.github.izhangzhihao.SSMSeedProject.IntegrationTest.Utils.admin;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@SpringBootTest(classes = Application.class)
@WebAppConfiguration
@ActiveProfiles("test")
public class AddressTest extends AbstractTransactionalJUnit4SpringContextTests {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    private JacksonTester<Address> json;

    @Before
    public void setUpMockMVC() {
        mockMvc = webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    public void findAddressByIdTest() throws Exception {
        String contentAsString = mockMvc.perform(
                get("/Address/id/1")
                        .with(admin)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
        ).andReturn()
                .getResponse()
                .getContentAsString();

        Address address = this.json.parse(contentAsString).getObject();
        assertThat(address.getAddrId()).isEqualTo(1);
        assertThat(address.getCity()).isEqualTo("city");
        assertThat(address.getCountry()).isEqualTo("country");
        assertThat(address.getState()).isEqualTo("state");
        assertThat(address.getStreet()).isEqualTo("street");
        assertThat(address.getZip()).isEqualTo("zip");
    }

    @Test
    public void findAddressById403Test() throws Exception {
        mockMvc.perform(
                get("/Address/id/1")
        ).andExpect(status().isForbidden());
    }
}
