package Team2.BuildWeek3.controllers;

import Team2.BuildWeek3.entities.Utente;
import Team2.BuildWeek3.exception.BadRequestException;
import Team2.BuildWeek3.payloads.NewUtentiDTO;
import Team2.BuildWeek3.payloads.NewUtentiRespDTO;
import Team2.BuildWeek3.services.UtentiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UtenteController {
    @Autowired
    private UtentiService utentiService;
    @GetMapping
    public Page<Utente> getAllUsers(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "10") int size,
                                    @RequestParam(defaultValue = "id") String sortBy) {
        return this.utentiService.getUsers(page, size, sortBy);
    }


    @GetMapping("/me")
    public Utente getProfile(@AuthenticationPrincipal Utente currentAuthenticatedUser){
        return currentAuthenticatedUser;
    }

    @PutMapping("/me")
    public Utente updateProfile(@AuthenticationPrincipal Utente currentAuthenticatedUser, @RequestBody Utente updatedUser){
        return this.utentiService.findByIdAndUpdate(currentAuthenticatedUser.getId(), updatedUser);
    }

    @DeleteMapping("/me")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProfile(@AuthenticationPrincipal Utente currentAuthenticatedUser){
        this.utentiService.findByIdAndDelete(currentAuthenticatedUser.getId());
    }


    @GetMapping("/{userId}")
    public Utente findById(@PathVariable long Id){
        return this.utentiService.findById(Id);
    }

    @PutMapping("/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Utente findByIdAndUpdate(@PathVariable long Id, @RequestBody Utente body){
        return this.utentiService.findByIdAndUpdate(Id, body);
    }

    @DeleteMapping("/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable long Id){
        this.utentiService.findByIdAndDelete(Id);
    }
}
