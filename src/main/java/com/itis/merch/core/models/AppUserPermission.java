package com.itis.merch.core.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@AllArgsConstructor
public enum AppUserPermission {
  
  /*----- Categories -----*/

  CATEGORIES_VIEW("categories::view"),
  CATEGORIES_WRITE("categories::write"),


  /*----- Products -----*/

  PRODUCTS_VIEW("products::view"),
  PRODUCTS_WRITE("products::write");

  @Getter
  private final String permission;

}
