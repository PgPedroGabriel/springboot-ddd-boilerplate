package com.example.authenticationsample.infra.http.rest;

import com.example.authenticationsample.domain.user.exceptions.UserNotFound;
import com.example.authenticationsample.domain.user.models.User;
import com.example.authenticationsample.domain.user.providers.UserProvider;
import com.example.authenticationsample.infra.http.dtos.user.CreateUserDTO;
import com.example.authenticationsample.infra.http.dtos.user.CreateUserDTOMock;
import com.google.gson.Gson;
import io.jsonwebtoken.lang.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.UUID;

@SpringBootTest
@AutoConfigureMockMvc
public class UserRestControllerTest {

    @Autowired
    private UserRestController controller;

    @Autowired
    private UserProvider userProvider;

    private UUID createdId = null;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void canConsumeCreateEndpoint() throws Exception {

        CreateUserDTO dto = CreateUserDTOMock.generate();
        Gson gson = new Gson();

        String body = gson.toJson(dto);

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders
                .post("/users")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(body))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

        User user = gson.fromJson(result.andReturn().getResponse().getContentAsString(), User.class);
        this.createdId = user.getId();

        Assertions.assertNotNull(user.getId());
        Assertions.assertEquals(user.getPassword(), null);

        this.searchUser();
        this.removeUser();
    }


    @Test
    void testInvalidParameters() throws Exception {

        CreateUserDTO dto = CreateUserDTOMock.generate();
        dto.setPassword_confirm("123");
        Gson gson = new Gson();

        String body = gson.toJson(dto);

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders
                .post("/users")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(body))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());


    }

    void searchUser() throws Exception {

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders
                .get("/users/"+this.createdId))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    void removeUser() throws UserNotFound {
        if(this.createdId != null) {
            this.userProvider.remove(this.createdId);
        }
    }
}
