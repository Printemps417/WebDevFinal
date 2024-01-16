package com.example.todoSpringboot.service;

import com.example.todoSpringboot.entity.Account;
import com.example.todoSpringboot.mapper.AccountMapper;
import com.example.todoSpringboot.note.AuthToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountMapper accountMapper;

    public void addAccount(Account account) {
        accountMapper.addAccount(account.getAccount(), account.getPassword(), account.getStudentId(), account.getType());
    }

    public void deleteAccount(String account) {
        accountMapper.deleteAccount(account);
    }
    public void deleteAccountById(int id) {
        accountMapper.deleteAccountById(id);
    }

    public void updateAccount(Account account) {
        accountMapper.updateAccount(account.getAccount(), account.getPassword(), account.getStudentId(), account.getType());
    }

    public Account getAccountByAccount(String account) {
        return accountMapper.getAccountByAccount(account);
    }

    public List<Account> getAllAccounts() {
        return accountMapper.getAllAccounts();
    }
    public Integer getStudentIdByAccount(String account) {
        return accountMapper.getStudentIdByAccount(account);
    }
}
