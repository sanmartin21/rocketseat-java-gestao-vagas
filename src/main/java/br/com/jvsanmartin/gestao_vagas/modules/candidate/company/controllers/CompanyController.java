package br.com.jvsanmartin.gestao_vagas.modules.candidate.company.controllers;

import br.com.jvsanmartin.gestao_vagas.exceptions.UserFoundException;
import br.com.jvsanmartin.gestao_vagas.modules.candidate.company.entities.CompanyEntity;
import br.com.jvsanmartin.gestao_vagas.modules.candidate.company.useCases.CreateCompanyUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CreateCompanyUseCase CreateCompanyUseCase;

    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody CompanyEntity companyEntity){
        try {
            var result = this.CreateCompanyUseCase.execute(companyEntity);
            return ResponseEntity.ok(result);
        }
        catch (UserFoundException e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
