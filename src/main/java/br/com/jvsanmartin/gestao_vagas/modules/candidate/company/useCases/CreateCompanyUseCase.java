package br.com.jvsanmartin.gestao_vagas.modules.candidate.company.useCases;

import br.com.jvsanmartin.gestao_vagas.exceptions.UserFoundException;
import br.com.jvsanmartin.gestao_vagas.modules.candidate.company.entities.CompanyEntity;
import br.com.jvsanmartin.gestao_vagas.modules.candidate.company.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateCompanyUseCase {
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public CompanyEntity execute(CompanyEntity companyEntity){
        this.companyRepository
                .findByUsernameOrEmail(companyEntity.getUsername(), companyEntity.getEmail())
                .ifPresent((user) -> {
                    throw new UserFoundException();
                });
        var password = passwordEncoder.encode(companyEntity.getPassword());
        companyEntity.setPassword(password);

        return this.companyRepository.save(companyEntity);
    }
}
