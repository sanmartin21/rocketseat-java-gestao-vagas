package br.com.jvsanmartin.gestao_vagas.modules.candidate.useCases;

import br.com.jvsanmartin.gestao_vagas.modules.candidate.CandidateRepository;
import br.com.jvsanmartin.gestao_vagas.modules.candidate.dto.AuthCandidateRequestDTO;
import br.com.jvsanmartin.gestao_vagas.modules.candidate.dto.AuthCandidateResponseDTO;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

@Service
public class AuthCandidateUseCase {

    @Value("${security.token.secret.candidate}")
    private String secretKey;

    @Autowired
    CandidateRepository candidateRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthCandidateResponseDTO execute(AuthCandidateRequestDTO authCandidateRequestDTO) throws ArithmeticException{
        var candidate = this.candidateRepository.findByUsername(authCandidateRequestDTO.username())
                .orElseThrow(() -> {
                    throw new UsernameNotFoundException("Username/password incorrect");
                });
        var passwordMatches = this.passwordEncoder.matches(authCandidateRequestDTO.password(), candidate.getPassword());

        if(!passwordMatches){
            throw new ArithmeticException();
        }

        Algorithm algorithm = Algorithm.HMAC256("secretKey");

        var token = JWT.create().withIssuer("javagas")
                .withExpiresAt(Instant.now().plus(Duration.ofMinutes(10)))
                .withClaim("roles", Arrays.asList("candidate"))
                . withSubject(candidate.getId().toString())
                .sign(algorithm);

        var authCandidateResponse = AuthCandidateResponseDTO.builder()
                .acess_token(token)
                .build();
        return authCandidateResponse;
    }
}
