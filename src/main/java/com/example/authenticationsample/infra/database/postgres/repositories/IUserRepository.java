package com.example.authenticationsample.infra.database.postgres.repositories;

import com.example.authenticationsample.domain.user.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IUserRepository extends CrudRepository<User, UUID> {

    User findByEmail(String email);
}
