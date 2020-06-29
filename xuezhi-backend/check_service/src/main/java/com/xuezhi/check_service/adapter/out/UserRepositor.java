package com.xuezhi.check_service.adapter.out;

import com.xuezhi.check_service.domain.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface UserRepositor extends MongoRepository<User, String> {
    User findUserById(String id);

    User findUserByEmailAndPassword(String email, String password);

    User findByEmail(String email);


}
