package lapitan.vkr.ApiService.security.model;

public enum Permission {
    ADMIN("admin"),
    USERS_READ("users:read"),
    USERS_WRITE("users:write"),
    LESSONS_READ("lessons:read"),
    LESSONS_WRITE("lessons:write"),
    SUBJECTS_READ("subjects:read"),
    SUBJECTS_WRITE("subjects:write");


    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
