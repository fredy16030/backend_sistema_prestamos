package com.sistema.prestamos.infrastructure.api.controller;

import com.sistema.prestamos.domain.model.Loan;
import com.sistema.prestamos.domain.service.LoanService;
import com.sistema.prestamos.infrastructure.api.dto.LoanRequestDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping("/request")
    public Mono<ResponseEntity<Loan>> requestLoan(@Valid @RequestBody LoanRequestDTO request) {
        Loan loan = new Loan();
        loan.setAmount(request.getAmount());
        loan.setTerms(request.getTerms());
        loan.setUserId(request.getUserId());
        return Mono.just(ResponseEntity.ok(loanService.requestLoan(loan)));
    }

    @PostMapping("/approve/{id}")
    public Mono<ResponseEntity<Loan>> approveLoan(@PathVariable Long id) {
        return Mono.just(ResponseEntity.ok(loanService.approveLoan(id)));
    }

    @PostMapping("/reject/{id}")
    public Mono<ResponseEntity<Loan>> rejectLoan(@PathVariable Long id) {
        return Mono.just(ResponseEntity.ok(loanService.rejectLoan(id)));
    }

    @GetMapping
    public Flux<Loan> getAllLoans() {
        return Flux.fromIterable(loanService.getAllLoans());
    }

    @GetMapping("/user/{userId}")
    public Flux<Loan> getLoansByUser(@PathVariable Long userId) {
        return Flux.fromIterable(loanService.getLoansByUser(userId));
    }
}
