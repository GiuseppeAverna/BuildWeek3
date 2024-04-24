package Team2.BuildWeek3.controllers;

import Team2.BuildWeek3.payloads.UserLoginDTO;
import Team2.BuildWeek3.payloads.UserLoginResponseDTO;
import Team2.BuildWeek3.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public UserLoginResponseDTO login(@RequestBody UserLoginDTO payload){
        return new UserLoginResponseDTO(this.authService.authenticateUserAndGenerateToken(payload));
    }

}