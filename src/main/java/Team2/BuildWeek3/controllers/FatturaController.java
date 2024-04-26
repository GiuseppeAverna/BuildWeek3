package Team2.BuildWeek3.controllers;

import Team2.BuildWeek3.entities.Fattura;
import Team2.BuildWeek3.exception.BadRequestException;
import Team2.BuildWeek3.payloads.FatturaDTO;
import Team2.BuildWeek3.payloads.FatturaRespDTO;
import Team2.BuildWeek3.payloads.StatoFatturaDTO;
import Team2.BuildWeek3.services.FatturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fatture")
public class FatturaController {
    @Autowired
    private FatturaService fatturaService;
@GetMapping("/cliente/{clienteId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<Fattura> findByCliente(@PathVariable long clienteId, Pageable pageable) {
    return fatturaService.findByCliente(clienteId, pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort().toString());
}

@GetMapping("/{numeroFattura}")
@PreAuthorize("hasAuthority('ADMIN')")
    public Fattura findByNumeroFattura(@PathVariable long numeroFattura) {
    return fatturaService.findByNumero(numeroFattura);}

    @PostMapping("")
    @PreAuthorize("hasAuthority('ADMIN')")
    public FatturaRespDTO save(@Validated @RequestBody FatturaDTO fattura, BindingResult validation) {
        if(validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        }
        return fatturaService.save(fattura);}

    @PatchMapping("/{numeroFattura}/update")
    @PreAuthorize("hasAuthority('ADMIN')")
    public FatturaRespDTO update(@PathVariable long numeroFattura,@Validated @RequestBody StatoFatturaDTO statoFattura, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        }
        return fatturaService.findAndUpdate(numeroFattura, statoFattura);
    }


    @DeleteMapping("/{numeroFattura}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void delete(@PathVariable long numeroFattura) {
        fatturaService.delete(numeroFattura);}
}
