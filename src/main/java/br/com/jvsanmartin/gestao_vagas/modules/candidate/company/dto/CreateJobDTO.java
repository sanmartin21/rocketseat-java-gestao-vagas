package br.com.jvsanmartin.gestao_vagas.modules.candidate.company.dto;

import lombok.Data;

@Data
public class CreateJobDTO {
    private String description;
    private String benefits;
    private String level;
}
