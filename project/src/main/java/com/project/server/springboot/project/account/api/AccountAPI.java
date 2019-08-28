package com.project.server.springboot.project.account.api;

import com.project.server.springboot.project.account.exception.ResourceNotFoundException;
import com.project.server.springboot.project.account.model.Account;
import com.project.server.springboot.project.account.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class AccountAPI {

    @Autowired
    private AccountRepository accountRepository;

    @PostMapping("/accounts")
    public Account createAccount(@Valid @RequestBody Account account) {
        return accountRepository.save(account);
    }

    @GetMapping("/accounts")
    public List<Account> getAllAccount() {
        return (List<Account>) accountRepository.findAll();
    }

    @GetMapping("/accounts/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        Account account = accountRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Account not found for this id: " + id));
        return ResponseEntity.ok().body(account);
    }

    @PutMapping("/accounts/{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable(value = "id") Long id,
                                                 @Valid @RequestBody Account accountDetail) throws ResourceNotFoundException {
        Account account = accountRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Account not found for this id: " + id));
        account.setUsername(accountDetail.getUsername());
        account.setFullName(accountDetail.getFullName());
        account.setPhone(accountDetail.getPhone());
        account.setEmail(accountDetail.getEmail());
        account.setAddress(accountDetail.getAddress());

        final Account updateAccount = accountRepository.save(account);
        return ResponseEntity.ok(updateAccount);
    }

    @DeleteMapping("/accounts/{id}")
    public Map<String, Boolean> deleteAccount(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        Account account = accountRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Account not found for this id: " + id));
        accountRepository.delete(account);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
