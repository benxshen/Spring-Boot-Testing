package com.howtodoinjava.employees.controllers;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

  @GetMapping("/admin")
//  @PreAuthorize("hasAnyRole('ADMIN')")
  @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
  Iterable<String> getAdminData() {
    return List.of("protected data...");
  }

  @GetMapping("/info")
  @PreAuthorize("authenticated")  // 必須要有使用者登入過，但任何角色都可以存取
  Iterable<String> getInfoData() {
    return List.of("info data...");
  }

}
