package lapitan.vkr.ApiService.auth.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "token", schema = "lessons_api")
public class ConfirmToken {
    @Id
    String token;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ConfirmToken that = (ConfirmToken) o;
        return getToken() != null && Objects.equals(getToken(), that.getToken());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
