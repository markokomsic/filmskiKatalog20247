package com.filmskiKatalog.controllers;

import com.filmskiKatalog.models.Role;
import com.filmskiKatalog.models.User;
import com.filmskiKatalog.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

@Controller
public class AuthController {

    @Autowired
    UserRepository userRepo;

    @GetMapping("/auth/register")
    public String add (Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "users/register";
    }

    @PostMapping("/auth/register")
    public String newUser (@Valid User user, BindingResult result, Model model){
        boolean errors = result.hasErrors();
        if (errors){
            model.addAttribute("user", user);
            return "users/register";
        } else {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String passwordEncoded = encoder.encode(user.getLozinka());
            user.setLozinka(passwordEncoded);
            user.setPotvrdaLozinke(passwordEncoded);
            user.setRoles(Collections.singleton(Role.KORISNIK)); // Pretpostavlja se da Role.KORISNIK postoji
            userRepo.save(user);
            return "redirect:/auth/login";
        }
    }

    @GetMapping("/auth/login")
    public String login (Model model){
        model.addAttribute("user", new User());
        return "users/login";
    }
}