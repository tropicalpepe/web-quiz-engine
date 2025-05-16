package engine.service;

import engine.model.User;
import engine.model.request.UserRequest;
import engine.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void registerUser(UserRequest userRequest) {
        if (userRepository.existsUserByEmail((userRequest.getEmail())))
            throw new IllegalArgumentException("User with the same email already exists");

        User user = userRequest.toUser(passwordEncoder);
        System.out.println(user);
        userRepository.save(user);
    }
}
