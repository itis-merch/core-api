package com.itis.merch.core.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
public enum AppUserRole {
  CUSTOMER(Set.of(
          AppUserPermission.CATEGORIES_VIEW,
          AppUserPermission.PRODUCTS_VIEW)),
  ADMIN(Set.of(
          AppUserPermission.CATEGORIES_VIEW,
          AppUserPermission.CATEGORIES_WRITE,
          AppUserPermission.PRODUCTS_VIEW,
          AppUserPermission.PRODUCTS_WRITE));

  @Getter
  private final Set<AppUserPermission> permissions;

  public Set<SimpleGrantedAuthority> getAuthorities() {
    return getPermissions().stream()
            .map(permissions -> new SimpleGrantedAuthority(permissions.getPermission()))
            .collect(Collectors.toSet());
  }
}
