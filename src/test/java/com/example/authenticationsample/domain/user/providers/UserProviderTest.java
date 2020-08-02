package com.example.authenticationsample.domain.user.providers;

import com.example.authenticationsample.domain.user.exceptions.UserNotFound;
import com.example.authenticationsample.domain.user.models.User;
import com.example.authenticationsample.domain.user.models.UserMock;
import io.jsonwebtoken.lang.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
public class UserProviderTest {

    @Autowired
    private UserProvider provider;

    private UUID createdUserId = null;

    @Test
    void canCreateUserOnDb() {

        User user = UserMock.generate();

        try {

            user = this.provider.saveOnDB(user);

            this.createdUserId = user.getId();

            Assert.isTrue(!user.getEmail().isEmpty());

            this.canRemoveUserOnDb();

        } catch(Exception e) {
            System.out.println(e.getMessage());
            Assert.isTrue(false);
        }
    }

    void canRemoveUserOnDb() throws UserNotFound {

        Assert.isTrue(this.createdUserId != null);

        try {
            this.provider.remove(this.createdUserId);
        } catch (UserNotFound e) {
            Assert.isTrue(true);
        }
    }
}
