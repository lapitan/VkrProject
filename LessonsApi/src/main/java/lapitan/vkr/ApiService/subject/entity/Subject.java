package lapitan.vkr.ApiService.subject.entity;

import lapitan.vkr.ApiService.user.entity.Person;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "subject", schema = "lessons_api")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subject_generator")
    @SequenceGenerator(name="subject_generator", sequenceName = "subject_seq", allocationSize = 1, initialValue = 5000)
    private long id;

    @Column
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecturer_id", nullable = false)
    @ToString.Exclude
    private Person lecturer;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JoinTable(name = "practice_teachers",
            joinColumns = {@JoinColumn(name = "subject_id")},
            inverseJoinColumns = {@JoinColumn(name = "teacher_id")},
            schema = "lessons_api")
    @ToString.Exclude
    private List<Person> practiceTeachers= new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Subject subject = (Subject) o;
        return Objects.equals(getId(), subject.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public void addTeacher(Person teacher){
        practiceTeachers.add(teacher);
    }

    public void deleteTeacher(Person teacher){
        practiceTeachers.remove(teacher);
    }
}
