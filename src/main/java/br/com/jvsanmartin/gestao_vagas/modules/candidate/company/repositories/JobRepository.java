package br.com.jvsanmartin.gestao_vagas.modules.candidate.company.repositories;

import br.com.jvsanmartin.gestao_vagas.modules.candidate.company.entities.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JobRepository extends JpaRepository<JobEntity, UUID> {
}
