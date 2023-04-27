package fhi360.it.assetverify.serviceImpl;

import fhi360.it.assetverify.dto.UserLoginDto;
import fhi360.it.assetverify.exception.ResourceNotFoundException;
import fhi360.it.assetverify.model.Users;
import fhi360.it.assetverify.repository.UserRepository;
import fhi360.it.assetverify.response.ApiResponse;
import fhi360.it.assetverify.service.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RequiredArgsConstructor
@Transactional
@Service
public class UserServiceImpl implements UsersService {
//    private final Logger logger;
    private final UserRepository usersRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public ApiResponse<Users> login(@Valid final UserLoginDto loginDto) throws ResourceNotFoundException {
        final Users user = this.usersRepository.findByEmail(loginDto.getEmail());
        if (user == null) {
            throw new ResourceNotFoundException(String.format("User with email %s does not exist.", loginDto.getEmail()));
        }
        if (!BCrypt.checkpw(loginDto.getPassword(), user.getPassword())) {
            throw new RuntimeException("Password Mismatch!.");
        }
        return new ApiResponse<>(200, "Login Success!", user, user.getId(), user.getUserType(), user.getEmail(), user.getFirstname(), user.getLastname(), user.getStates(), user.getProject());
    }

    @Override
    public boolean isUserAlreadyPresent(final Users user) {
        boolean isUserAlreadyExists = false;
        final Users existingUser = this.usersRepository.getByEmail(user.getEmail());
        if (existingUser != null) {
            isUserAlreadyExists = true;
        }
        return isUserAlreadyExists;
    }

    @Override
    public Users save(@RequestBody final Users user) {
        user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
        return this.usersRepository.save(user);
    }
}
