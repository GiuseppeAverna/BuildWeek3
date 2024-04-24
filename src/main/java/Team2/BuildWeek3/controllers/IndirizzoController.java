package Team2.BuildWeek3.controllers;

import Team2.BuildWeek3.entities.Indirizzo;
import Team2.BuildWeek3.services.IndirizziService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/indirizzi")
public class IndirizzoController {

    @Autowired
    private IndirizziService indirizzoService;

    @RequestMapping
    public Page<Indirizzo> getIndirizzo(@RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "10") int size,
                                        @RequestParam(defaultValue = "id") String sort) {
        return indirizzoService.getIndirizzo(page, size, sort);
    }


    @RequestMapping("/{indirizziId}")
    public Indirizzo findById(@PathVariable long indirizziId) {
        return indirizzoService.findById(indirizziId);
    }

    @DeleteMapping("/{indirizziId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteIndirizzo (Long indirizziId){ indirizzoService.deleteIndirizzo(indirizziId);
    }

}
