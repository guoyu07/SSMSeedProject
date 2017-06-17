package com.github.izhangzhihao.SSMSeedProject.cucumber;

import com.fasterxml.jackson.databind.ObjectMapper;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.context.WebApplicationContext;

import java.io.UnsupportedEncodingException;

import static com.github.izhangzhihao.SSMSeedProject.cucumber.Utils.admin;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration
@ActiveProfiles("test")
public class UserResourceSteps {
    @SuppressWarnings("SpringAutowiredFieldsWarningInspection")
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @SuppressWarnings({"FieldCanBeLocal", "unused"})
    private String userName;

    private ResultActions resultActions;

    @Before
    public void setUpMockMVC() {
        mockMvc = webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Given("I use the user's name : (.*)")
    public void useCaller(String userName) {
        this.userName = userName;
    }

    @When("I request a user's info,is ROLE_ADMIN (.*)")
    public void requestGreeting(String isAdmin) throws Exception {
        if (Boolean.parseBoolean(isAdmin)) {
            resultActions = mockMvc.perform(
                    get("/User/userName/admin")
                            .with(admin)
            );
        } else {
            resultActions = mockMvc.perform(
                    get("/User/userName/admin")
            );
        }
    }

    @Then("I should get a response with HTTP status code : (.*)")
    public void shouldGetResponseWithHttpStatusCode(int statusCode) throws Exception {
        resultActions
                .andExpect(status().is(statusCode));
    }

    @And("The response should contain the message: (.*)")
    public void theResponseShouldContainTheMessage(String containsMessage) throws UnsupportedEncodingException {
        assertThat(resultActions
                        .andReturn()
                        .getResponse()
                        .getContentAsString()
                        .contains(containsMessage),
                is(true));
    }
}
