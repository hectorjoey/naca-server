
package fhi360.it.assetverify.controller;

import fhi360.it.assetverify.dto.UserDto;
import fhi360.it.assetverify.exception.EmailExistsException;
import fhi360.it.assetverify.exception.ResourceNotFoundException;
import fhi360.it.assetverify.model.Users;
import fhi360.it.assetverify.repository.UserRepository;
import fhi360.it.assetverify.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = {"http://localhost:3000"})
//@CrossOrigin(origins = "https://naca-client.netlify.app/")
@RestController
@RequestMapping({"/api/v1/"})
@RequiredArgsConstructor
@Slf4j
public class UsersController {
    private final UserRepository userRepository;
    private final NotificationService notificationService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping({"users"})
    public Page<Users> getUserByType(final Pageable pageable) {
        return this.userRepository.findByOrderByUserTypeAsc(pageable);
    }

    @GetMapping({"users/{keyword}"})
    public Page<Users> getAllUsers(final Pageable pageable, @PathVariable("keyword") final String keyword) {
        return this.userRepository.findAll(pageable, keyword);
    }

    @GetMapping({"user/{id}"})
    public ResponseEntity<Users> getUserById(@PathVariable("id") final Long id) throws ResourceNotFoundException {

        final Users user = this.userRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("User not found for this id :: " + id));
        return ResponseEntity.ok().body(user);
    }

    @PostMapping({"user"})
    public ResponseEntity<?> createUser(@Valid @RequestBody final Users users) throws EmailExistsException {
        final Users usersEmail = this.userRepository.findByEmail(users.getEmail());
        if (usersEmail != null) {
            throw new EmailExistsException(String.format("User with email %s already exist", users.getEmail()));
        }
        users.setPassword(this.bCryptPasswordEncoder.encode(users.getPassword()));
        return new ResponseEntity<>(this.userRepository.save(users), HttpStatus.CREATED);
    }

    @PatchMapping({"user/{id}"})
    public Users updateUser(@PathVariable("id") final Long id, @Valid @RequestBody final UserDto userDto) throws ResourceNotFoundException {
        log.debug("Update User with ID = {}", id);
        final ResourceNotFoundException ex;
        final Users users = this.userRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("User not found for this id :: " + id));
        users.setPassword(this.bCryptPasswordEncoder.encode((CharSequence) userDto.getPassword()));
        final Users updatedUser = this.userRepository.save(users);
        log.debug("Updated User {}", updatedUser);
        return this.userRepository.save(updatedUser);
    }

    @PutMapping({"user/{id}"})
    public Users updateUsers(@PathVariable("id") final Long id, @Valid @RequestBody final UserDto userDto) throws ResourceNotFoundException {
        log.debug("Update User with ID = {}", id);
        final ResourceNotFoundException ex;
        final Users users = this.userRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("User not found for this id :: " + id));
        users.setEmail(userDto.getEmail());
        users.setUserType(userDto.getUserType());
        users.setFirstname(userDto.getFirstname());
        users.setLastname(userDto.getLastname());
        final Users updatedUser = this.userRepository.save(users);
        log.debug("Updated User {}" + updatedUser);
        return this.userRepository.save(updatedUser);
    }

    @DeleteMapping({"user/{id}"})
    public Map<String, Boolean> deleteUser(@PathVariable("id") final Long id) throws ResourceNotFoundException {

        final Users user = this.userRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("User not found for this id :: " + id));
        this.userRepository.delete(user);
        final Map<String, Boolean> response = new HashMap<String, Boolean>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
