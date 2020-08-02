package com.example.authenticationsample.domain.user.providers;

import com.example.authenticationsample.domain.user.exceptions.UserInvalidEmail;
import com.example.authenticationsample.domain.user.exceptions.UserNotFound;
import com.example.authenticationsample.domain.user.models.User;
import com.example.authenticationsample.infra.database.postgres.repositories.IUserRepository;
import com.example.authenticationsample.infra.http.dtos.user.CreateUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class UserProvider {

    @Autowired
    private IUserRepository userRepository;

    public User saveOnDB(User user) throws UserInvalidEmail {

        User containsUser = this.userRepository.findByEmail(user.getEmail());

        if(containsUser != null && !containsUser.getId().toString().equals(user.getId().toString())) {
            throw new UserInvalidEmail("Usuário com este email já se encontra na nossa base de dados");
        }

        this.userRepository.save(user);

        return user;
    }

    public void remove(UUID id) throws UserNotFound {
        User user = this.findById(id);
        this.userRepository.delete(user);
    }

    public User findByEmail(String email) throws UserNotFound {
        User user = this.userRepository.findByEmail(email);

        if(user == null) {
            throw new UserNotFound("Usuário não encontrado");
        }

        return user;
    }


    public User findById(UUID id) throws UserNotFound {
        Optional<User> user = this.userRepository.findById(id);

        if(user.isEmpty()) {
            throw new UserNotFound("Usuário não encontrado");
        }

        return user.get();
    }
}
