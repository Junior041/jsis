package br.dev.ismael.jsis.domain.enterprise.entities;


public enum UserRoles {
    ADMIN("admin"),
    MANAGER("manager"),
    USER("user"),
    GUEST("guest");

    private String role;

    UserRoles(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return this.role;
    }
}
