package com.sistema.prestamos;

import com.sistema.prestamos.domain.model.Loan;
import com.sistema.prestamos.domain.repository.LoanRepository;
import com.sistema.prestamos.domain.service.LoanService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LoanServiceTest {

    @Mock
    private LoanRepository loanRepository;

    @InjectMocks
    private LoanService loanService;

    private Loan testLoan;

    @BeforeEach
    void setUp() {
        testLoan = new Loan();
        testLoan.setId(1L);
        testLoan.setAmount(new BigDecimal("1000.00"));
        testLoan.setTerms(12);
        testLoan.setStatus("PENDING");
        testLoan.setUserId(1L);
    }

    @Test
    void requestLoan_ShouldSetStatusToPending() {
        when(loanRepository.save(any(Loan.class))).thenReturn(testLoan);

        Loan result = loanService.requestLoan(testLoan);

        assertNotNull(result);
        assertEquals("PENDING", result.getStatus());
        verify(loanRepository, times(1)).save(any(Loan.class));
    }

    @Test
    void approveLoan_ShouldSetStatusToApproved() {
        when(loanRepository.findById(1L)).thenReturn(Optional.of(testLoan));
        when(loanRepository.save(any(Loan.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Loan result = loanService.approveLoan(1L);

        assertEquals("APPROVED", result.getStatus());
        verify(loanRepository).save(testLoan);
    }

    @Test
    void rejectLoan_ShouldSetStatusToRejected() {
        when(loanRepository.findById(1L)).thenReturn(Optional.of(testLoan));
        when(loanRepository.save(any(Loan.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Loan result = loanService.rejectLoan(1L);

        assertEquals("REJECTED", result.getStatus());
        verify(loanRepository).save(testLoan);
    }
}
