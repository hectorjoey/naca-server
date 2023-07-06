package fhi360.it.assetverify.controller;

import fhi360.it.assetverify.dto.UserLoginDto;
import fhi360.it.assetverify.exception.ResourceNotFoundException;
import fhi360.it.assetverify.response.ApiResponse;
import fhi360.it.assetverify.service.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping({"/api/v1"})
@Slf4j
@RequiredArgsConstructor
public class LoginController {
    private final UsersService usersService;

    @PostMapping({"/login"})
    public ApiResponse login(@Valid @RequestBody final UserLoginDto loginDto) throws ResourceNotFoundException {
        return this.usersService.login(loginDto);
    }

    @PostMapping({"/android-login"})
    public ApiResponse androidLogin(@Valid final UserLoginDto loginDto) throws ResourceNotFoundException {
        return this.usersService.login(loginDto);
    }
}
