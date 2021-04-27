package com.sofisticat.management.controllers;


import com.sofisticat.management.entities.Account;
import com.sofisticat.management.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/register")
@Controller
public class AccountController {


    AccountService accountService;
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public AccountController(AccountService accountService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.accountService = accountService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @GetMapping
    public String register(Model model) {
        Account account = new Account();
        model.addAttribute("user", account);
        return "accounts/register";
    }

    @PostMapping("/save-account")
    public String saveAccount(Account account, Model model) {
        account.setPassword(bCryptPasswordEncoder.encode(account.getPassword()));
        accountService.saveAccount(account);
        return "redirect:/";
    }
}
