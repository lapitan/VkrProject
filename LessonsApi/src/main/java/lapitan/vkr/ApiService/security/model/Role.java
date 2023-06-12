package lapitan.vkr.ApiService.security.model;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum Role {
    STUDENT(Set.of(
            Permission.LESSONS_READ,
            Permission.SUBJECTS_READ,
            Permission.USERS_READ
    )),
    TEACHER(Set.of(
            Permission.LESSONS_READ,
            Permission.LESSONS_WRITE,
            Permission.SUBJECTS_READ,
            Permission.USERS_READ
    )),
    LECTURER(Set.of(
            Permission.LESSONS_READ,
            Permission.LESSONS_WRITE,
            Permission.SUBJECTS_READ,
            Permission.SUBJECTS_WRITE,
            Permission.USERS_READ
    )),
    ADMIN(Set.of(
            Permission.ADMIN,
            Permission.USERS_READ,
            Permission.USERS_WRITE,
            Permission.LESSONS_READ,
            Permission.LESSONS_WRITE,
            Permission.SUBJECTS_READ,
            Permission.SUBJECTS_WRITE
    ));

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getAuthorities(){
        return permissions.stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }
}
