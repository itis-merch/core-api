package com.itis.merch.core.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

/**
 * The {@code AppUserPermission} enum represents the different
 * permissions available to users in the application. Each
 * permission is represented by a constant value and has a
 * corresponding string value that can be used to check for
 * authorization.
 */
@AllArgsConstructor
public enum AppUserPermission {

  /*----- Categories -----*/

  /**
   * Permission to view categories.
   */
  CATEGORIES_VIEW("categories::view"),

  /**
   * Permission to write (create, edit, or delete) categories.
   */
  CATEGORIES_WRITE("categories::write"),


  /*----- Products -----*/

  /**
   * Permission to view products.
   */
  PRODUCTS_VIEW("products::view"),

  /**
   * Permission to write (create, edit, or delete) products.
   */
  PRODUCTS_WRITE("products::write");

  /**
   * The string value of the permission.
   */
  @Getter
  private final String permission;

}
