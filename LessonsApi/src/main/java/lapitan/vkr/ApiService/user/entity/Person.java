package lapitan.vkr.ApiService.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lapitan.vkr.ApiService.lesson.entity.Lesson;
import lapitan.vkr.ApiService.security.model.Role;
import lapitan.vkr.ApiService.subject.entity.Subject;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "person", schema = "lessons_api")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_gen")
    @SequenceGenerator(name = "person_gen", sequenceName = "person_seq", initialValue = 5000)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String groupp;

    @Column
    private String firstname;

    @Column
    private String lastname;

    @Column
    private boolean confirmed;

    @Enumerated(value = EnumType.STRING)
    @Column
    private Role role;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "practiceTeachers")
    @JsonIgnore
    @ToString.Exclude
    private List<Subject> allowedSubjects= new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "lessonStudents")
    @JsonIgnore
    @ToString.Exclude
    private List<Lesson> lessons= new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Person person = (Person) o;
        return Objects.equals(getId(), person.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
