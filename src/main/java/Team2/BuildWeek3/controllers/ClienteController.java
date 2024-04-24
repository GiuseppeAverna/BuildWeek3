package Team2.BuildWeek3.controllers;

import Team2.BuildWeek3.entities.Cliente;
import Team2.BuildWeek3.payloads.NewClientiDTO;
import Team2.BuildWeek3.services.ClientiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
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
        return clienteService.getAllClienti(page,size,sortBy);
    }

    @GetMapping("/{clienteId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public NewClientiDTO getClienteById(Long clienteId){
        return clienteService.findById(clienteId);
    }

    @DeleteMapping("/{clienteId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClienteById (Long clienteId){
        clienteService.deleteClienteById(clienteId);
    }

    @PatchMapping("/sedeLegale/{clienteId}/{indirizzoId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public NewClientiDTO updateIndirizzoSedeLegale(Long clienteId,Long indirizzoId){
        return findByIdAndUpdateSedeLegale(clienteId,indirizzoId);
    }

    @PatchMapping("/sedeOperativa/{clienteId}/{indirizzoId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Cliente updateIndirizzoSedeOperativa(Long clienteId,Long indirizzoId){
        return findByIdAndUpdateSedeOperativa(clienteId,indirizzoId);
    }






}
