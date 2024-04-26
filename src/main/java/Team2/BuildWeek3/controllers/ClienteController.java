package Team2.BuildWeek3.controllers;

import Team2.BuildWeek3.entities.Cliente;
import Team2.BuildWeek3.exception.BadRequestException;
import Team2.BuildWeek3.payloads.NewClientiDTO;
import Team2.BuildWeek3.payloads.NewClientiRespDTO;
import Team2.BuildWeek3.services.ClientiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clienti")
public class ClienteController {
    @Autowired
    private ClientiService clienteService;

    @GetMapping("")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<Cliente> getAllClienti(@RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "5") int size,
                                       @RequestParam(defaultValue = "id") String sortBy) {
        return clienteService.getAllClienti(page, size, sortBy);
    }

    @GetMapping("/{clienteId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Cliente getClienteById(@PathVariable Long clienteId) {
        return clienteService.findById(clienteId);
    }

    @DeleteMapping("/{clienteId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClienteById(@PathVariable Long clienteId) {
        clienteService.deleteClienteById(clienteId);
    }

    @PatchMapping("/sedeLegale/{clienteId}/{indirizzoId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Cliente updateIndirizzoSedeLegale(@PathVariable Long clienteId, @PathVariable Long indirizzoId) {
        return clienteService.findByIdAndUpdateSedeLegale(clienteId, indirizzoId);
    }

    @PatchMapping("/sedeOperativa/{clienteId}/{indirizzoId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Cliente updateIndirizzoSedeOperativa(@PathVariable Long clienteId, @PathVariable Long indirizzoId) {
        return clienteService.findByIdAndUpdateSedeOperativa(clienteId, indirizzoId);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public NewClientiRespDTO saveClienti(@RequestBody @Validated NewClientiDTO body, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        }
        return new NewClientiRespDTO(this.clienteService.save(body).getId());
    }

}
