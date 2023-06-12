package lapitan.vkr.ApiService.lesson.entity;

import lapitan.vkr.ApiService.subject.entity.Subject;
import lapitan.vkr.ApiService.user.entity.Person;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "lesson", schema = "lessons_api")
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lesson_generator")
    @SequenceGenerator(name="lesson_generator", sequenceName = "lesson_seq", allocationSize = 1, initialValue = 5000)
    private long id;

    @Column
    private String groupp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id", nullable = false)
    private Person teacher;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JoinTable(name = "lesson_students",
            joinColumns = {@JoinColumn(name = "lesson_id")},
            inverseJoinColumns = {@JoinColumn(name = "student_id")},
            schema = "lessons_api")
    private List<Person> lessonStudents = new ArrayList<>();

    public void addStudent(Person student){
        lessonStudents.add(student);
    }

    public void removeStudent(Person student){
        lessonStudents.remove(student);
    }

}
