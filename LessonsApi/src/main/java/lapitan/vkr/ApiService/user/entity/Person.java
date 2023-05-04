package lapitan.vkr.ApiService.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lapitan.vkr.ApiService.lesson.entity.Lesson;
import lapitan.vkr.ApiService.subject.entity.Subject;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "person", schema = "lessons_api")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    @SequenceGenerator(name = "sequence", sequenceName = "sequence", allocationSize = 100)
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

    @Column
    private String status;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "practiceTeachers")
    @JsonIgnore
    private Set<Subject> allowedSubjects= new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "lessonStudents")
    @JsonIgnore
    private Set<Lesson> lessons= new HashSet<>();

}
