package lapitan.vkr.DiscordBotService.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "subject", schema = "discord_database")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subject_gen")
    @SequenceGenerator(name = "subject_gen", sequenceName = "subject_seq", initialValue = 5000)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Category category = (Category) o;
        return getId() != null && Objects.equals(getId(), category.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
