package lapitan.vkr.ApiService.lesson.entity;

import lapitan.vkr.ApiService.user.entity.Person;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "lesson", schema = "lessons_api")
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    @SequenceGenerator(name = "sequence", sequenceName = "sequence", allocationSize = 100)
    private Long id;

    @Column
    private String groupp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id", nullable = false)
    private Person teacher;
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JoinTable(name = "lesson_students",
            joinColumns = {@JoinColumn(name = "lesson_id")},
            inverseJoinColumns = {@JoinColumn(name = "student_id")},
            schema = "lessons_api")
    private Set<Person> lessonStudents = new HashSet<>();

    public Lesson() {
        lessonStudents=new HashSet<>();
    }

    public void addStudent(Person student){
        lessonStudents.add(student);
    }

}
