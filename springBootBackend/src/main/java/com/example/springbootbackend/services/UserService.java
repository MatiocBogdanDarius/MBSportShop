package com.example.springbootbackend.services;

import com.example.springbootbackend.exceptions.ResourceNotFoundException;
import com.example.springbootbackend.model.ConfirmationToken;
import com.example.springbootbackend.model.requests.LoginRequest;
import com.example.springbootbackend.model.User;
import com.example.springbootbackend.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
        private final static String USER_NOT_FOUND_MSG =
            "user with email %s not found";

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                String.format(USER_NOT_FOUND_MSG, email)));
    }

    public String signUpUser(User user) {
        boolean userExists = userRepository
                .findByEmail(user.getEmail())
                .isPresent();

        if (userExists) {
            // TODO check of attributes are the same and
            // TODO if email not confirmed send confirmation email.

            throw new IllegalStateException("email already taken");
        }

        String encodedPassword = bCryptPasswordEncoder
                .encode(user.getPassword());

        user.setPassword(encodedPassword);

        userRepository.save(user);

        String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                user
        );

        confirmationTokenService.saveConfirmationToken(
                confirmationToken);

//        TODO: SEND EMAIL

        return token;
    }

    public User login(LoginRequest request){
        User user = (User) loadUserByUsername(request.getEmail());
        if(!bCryptPasswordEncoder.matches(request.getPassword(), user.getPassword()))
            throw new IllegalStateException("Incorrect password!");
        return user;
    }

    public int enableUser(String email) {
        return userRepository.enableUser(email);
    }

    public ResponseEntity<User> updateFavoriteProducts(Long id, User userDetails) {
    User user = userRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("User not exist with id: " + id));

    user.setFavoriteProducts(userDetails.getFavoriteProducts());

    User updateUser = userRepository.save(user);
    return ResponseEntity.ok(updateUser);
    }

//    public ResponseEntity<User> updateShoppingCart(Long id, User userDetails) {
//        User user = userRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("User not exist with id: " + id));
//
//        user.setShoppingCart(userDetails.getShoppingCart());
//
//        User updateUser = userRepository.save(user);
//        return ResponseEntity.ok(updateUser);
//    }
}
