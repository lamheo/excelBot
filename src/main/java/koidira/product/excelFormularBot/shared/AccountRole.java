package koidira.product.excelFormularBot.shared;


public enum AccountRole {
  USER("USER"),
  ADMIN("ADMIN");

  private final String userRole;

  AccountRole(String userRole) {
    this.userRole = userRole;
  }

  public String getUserRole() {
    return userRole;
  }
}
