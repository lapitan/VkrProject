package lapitan.vkr.DiscordBotService.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "channel", schema = "discord_database")
public class Channel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "channel_gen")
    @SequenceGenerator(name = "channel_gen", sequenceName = "channel_seq", initialValue = 5000)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    @ToString.Exclude
    private Category category;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JoinTable(name = "person_channel",
                joinColumns = {@JoinColumn(name = "channel_id")},
                inverseJoinColumns = {@JoinColumn(name = "person_id")},
                schema = "discord_database")
    private List<Person> persons = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Channel channel = (Channel) o;
        return getId() != null && Objects.equals(getId(), channel.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
