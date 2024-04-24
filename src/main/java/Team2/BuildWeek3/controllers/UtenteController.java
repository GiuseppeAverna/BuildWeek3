package Team2.BuildWeek3.controllers;

import Team2.BuildWeek3.entities.Utente;
import Team2.BuildWeek3.exception.BadRequestException;
import Team2.BuildWeek3.payloads.NewUtentiDTO;
import Team2.BuildWeek3.payloads.NewUtentiRespDTO;
import Team2.BuildWeek3.services.UtentiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NewUtentiRespDTO saveUser(@RequestBody @Validated NewUtentiDTO body, BindingResult validation){

        if(validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        }

        return new NewUtentiRespDTO(this.utentiService.save(body).getId());
    }

    @GetMapping("/{userId}")
    public Utente findById(@PathVariable long userId){
        return this.utentiService.findById(userId);
    }
    @PutMapping("/{userId}")
    public Utente findByIdAndUpdate(@PathVariable long userId, @RequestBody Utente body){
        return this.utentiService.findByIdAndUpdate(userId, body);
    }
    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable long userId){
        this.utentiService.findByIdAndDelete(userId);
    }
}
