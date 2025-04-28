package br.com.jvsanmartin.gestao_vagas.modules.candidate.company.controllers;

import br.com.jvsanmartin.gestao_vagas.exceptions.UserFoundException;
import br.com.jvsanmartin.gestao_vagas.modules.candidate.company.dto.CreateJobDTO;
import br.com.jvsanmartin.gestao_vagas.modules.candidate.company.entities.CompanyEntity;
import br.com.jvsanmartin.gestao_vagas.modules.candidate.company.entities.JobEntity;
import br.com.jvsanmartin.gestao_vagas.modules.candidate.company.repositories.CompanyRepository;
import br.com.jvsanmartin.gestao_vagas.modules.candidate.company.useCases.CreateCompanyUseCase;
import br.com.jvsanmartin.gestao_vagas.modules.candidate.company.useCases.CreateJobUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/job")
public class JobController {

    @Autowired
    private CreateJobUseCase createJobUseCase;

    @PostMapping("/")
    public JobEntity create(@Valid @RequestBody CreateJobDTO createJobDTO, HttpServletRequest request, HttpServletResponse response){
        var companyId = request.getAttribute("company_id");
        var jobEntity = JobEntity.builder()
                .benefits(createJobDTO.getBenefits())
                .companyId(UUID.fromString(companyId.toString()))
                .description(createJobDTO.getDescription())
                .level(createJobDTO.getLevel())
                .build();
        return this.createJobUseCase.execute(jobEntity);
    }
}
