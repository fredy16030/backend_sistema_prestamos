package com.sistema.prestamos.infrastructure.persistence.repository;

import com.sistema.prestamos.domain.model.Loan;
import com.sistema.prestamos.domain.repository.LoanRepository;
import com.sistema.prestamos.infrastructure.persistence.entity.LoanEntity;
import com.sistema.prestamos.infrastructure.persistence.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class MySqlLoanRepository implements LoanRepository {

    private final JpaLoanRepository jpaLoanRepository;
    private final JpaUserRepository jpaUserRepository;

    public MySqlLoanRepository(JpaLoanRepository jpaLoanRepository, JpaUserRepository jpaUserRepository) {
        this.jpaLoanRepository = jpaLoanRepository;
        this.jpaUserRepository = jpaUserRepository;
    }

    @Override
    public Loan save(Loan loan) {
        LoanEntity entity = toEntity(loan);
        LoanEntity saved = jpaLoanRepository.save(entity);
        return toDomain(saved);
    }

    @Override
    public Optional<Loan> findById(Long id) {
        return jpaLoanRepository.findById(id).map(this::toDomain);
    }

    @Override
    public List<Loan> findByUserId(Long userId) {
        return jpaLoanRepository.findByUserId(userId).stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Loan> findAll() {
        return jpaLoanRepository.findAll().stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    private LoanEntity toEntity(Loan loan) {
        UserEntity userEntity = jpaUserRepository.findById(loan.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        LoanEntity entity = new LoanEntity();
        entity.setId(loan.getId());
        entity.setAmount(loan.getAmount());
        entity.setTerms(loan.getTerms());
        entity.setStatus(loan.getStatus());
        entity.setCreatedAt(loan.getCreatedAt());
        entity.setUser(userEntity);
        return entity;
    }

    private Loan toDomain(LoanEntity entity) {
        Loan loan = new Loan();
        loan.setId(entity.getId());
        loan.setAmount(entity.getAmount());
        loan.setTerms(entity.getTerms());
        loan.setStatus(entity.getStatus());
        loan.setCreatedAt(entity.getCreatedAt());
        loan.setUserId(entity.getUser().getId());
        return loan;
    }
}
