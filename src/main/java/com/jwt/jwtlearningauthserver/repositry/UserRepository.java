package com.jwt.jwtlearningauthserver.repositry;

import com.jwt.jwtlearningauthserver.model.CustomUserDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<CustomUserDetails, String> {
    CustomUserDetails findByUsername(String userName);
}
