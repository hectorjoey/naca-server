package fhi360.it.assetverify.controller;

import fhi360.it.assetverify.dto.LoanDto;
import fhi360.it.assetverify.exception.ResourceNotFoundException;
import fhi360.it.assetverify.model.Loan;
import fhi360.it.assetverify.repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping({"/api/v1/"})
@Slf4j
@RequiredArgsConstructor
public class LoanController {
    private final LoanRepository loanRepository;

    @GetMapping({"loans"})
    Page<Loan> getAllLoans(final Pageable pageable) {
        return this.loanRepository.findAll(pageable);
    }

    @GetMapping({"loan/{location}"})
    Page<Loan> getLoansByLocation(final Pageable pageable, @PathVariable("location") final String location) {
        return this.loanRepository.findByOrderByExpiryDateDesc(pageable, location);
    }

    @PostMapping({"loan"})
    ResponseEntity<?> createLoans(@RequestBody final Loan loan) {
        return new ResponseEntity<>(this.loanRepository.save(loan), HttpStatus.CREATED);
    }

    @GetMapping({"loans/{keyword}"})
    public Page<Loan> getAllLoans(final Pageable pageable, @PathVariable("keyword") final String keyword) {
        return this.loanRepository.findAll(pageable, keyword);
    }

    @GetMapping({"loan/{id}"})
    public ResponseEntity<Loan> getUserById(@PathVariable("id") final Long id) throws ResourceNotFoundException {

        final Loan loan = this.loanRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Loan not found for this id :: " + id));
        return ResponseEntity.ok().body(loan);
    }

    @PatchMapping({"loan/{id}"})
    public Loan updateLoan(@PathVariable("id") final Long id, @Valid @RequestBody final LoanDto loanDto) throws ResourceNotFoundException {
        log.debug("Update Loan with ID = {}", id);
        final ResourceNotFoundException ex;
        final Loan loan = this.loanRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Loan not found for this id :: " + id));
        final Loan updatedLoan = this.loanRepository.save(loan);
        log.debug("Updated loan {}", updatedLoan);
        return this.loanRepository.save(updatedLoan);
    }

    @PutMapping({"loan/{id}"})
    public Loan updateLoans(@PathVariable("id") final Long id, @Valid @RequestBody final LoanDto loanDto) throws ResourceNotFoundException {
        log.debug("Update Loan with ID = {}", +id);
        final ResourceNotFoundException ex;
        final Loan loan = this.loanRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Loan not found for this id :: " + id));
        loan.setLastname(loanDto.getLastname());
        final Loan updatedLoan = this.loanRepository.save(loan);
        log.debug("Updated loan {}", updatedLoan);
        return this.loanRepository.save(updatedLoan);
    }

    @DeleteMapping({"loan/{id}"})
    public Map<String, Boolean> deleteUser(@PathVariable("id") final Long id) throws ResourceNotFoundException {

        final Loan loan = this.loanRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Loan not found for this id :: " + id));
        this.loanRepository.delete(loan);
        final Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
