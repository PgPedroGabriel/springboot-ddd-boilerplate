package com.example.authenticationsample.domain.user.providers;

import com.example.authenticationsample.domain.user.exceptions.InvalidParameters;
import com.example.authenticationsample.domain.user.exceptions.UserInvalidEmail;
import com.example.authenticationsample.domain.user.exceptions.UserNotFound;
import com.example.authenticationsample.domain.user.models.User;
import com.example.authenticationsample.domain.user.models.UserMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.AfterTestClass;

import java.util.UUID;

@SpringBootTest
public class SessionProviderTest {

    @Autowired
    private SessionProvider provider;

    @Autowired
    private UserProvider userProvider;

    private UUID createdUserId = null;

    @Test
    void invalidParametersException() throws UserNotFound, InvalidParameters {
        Assertions.assertThrows(InvalidParameters.class, () -> {
            this.provider.generateToken(null, null);
        });

        Assertions.assertThrows(InvalidParameters.class, () -> {
            this.provider.generateToken("Teste", null);
        });
    }


    @Test
    void userNotFoundException() throws UserNotFound, InvalidParameters {
        Assertions.assertThrows(UserNotFound.class, () -> {
            this.provider.generateToken("devpedrogabriel@gmail.com", "123");
        });
    }

    @Test
    void canCreateUserAndGenerateTheToken() throws UserNotFound, InvalidParameters, UserInvalidEmail {

        User user = UserMock.generate();

        userProvider.saveOnDB(user);

        this.createdUserId = user.getId();

        String token = this.provider.generateToken(user.getEmail(), user.getPlainPassword());

        Assertions.assertNotNull(token);
        Assertions.assertEquals(user.getId().toString(), this.provider.getTokenContent(token));


    }

    @AfterTestClass
    void deleteUserAfterAll() throws UserNotFound {
        if(this.createdUserId != null) {
            userProvider.remove(this.createdUserId);
        }
    }

}
