package Team2.BuildWeek3.controllers;

import Team2.BuildWeek3.exception.BadRequestException;
import Team2.BuildWeek3.payloads.NewUtentiDTO;
import Team2.BuildWeek3.payloads.NewUtentiRespDTO;
import Team2.BuildWeek3.payloads.UserLoginDTO;
import Team2.BuildWeek3.payloads.UserLoginResponseDTO;
import Team2.BuildWeek3.services.AuthService;
import Team2.BuildWeek3.services.UtentiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private UtentiService utentiService;

    @PostMapping("/login")
    public UserLoginResponseDTO login(@RequestBody UserLoginDTO payload) {
        return new UserLoginResponseDTO(this.authService.authenticateUserAndGenerateToken(payload));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public NewUtentiRespDTO saveUser(@RequestBody @Validated NewUtentiDTO body, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        }
        return new NewUtentiRespDTO(this.utentiService.save(body).getId());

    }
}