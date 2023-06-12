package lapitan.vkr.ApiService.subject;

import com.vladmihalcea.sql.SQLStatementCountValidator;
import lapitan.vkr.ApiService.config.SystemJpaTest;
import lapitan.vkr.ApiService.subject.controller.SubjectController;
import lapitan.vkr.ApiService.subject.dto.SubjectDto;
import lapitan.vkr.ApiService.subject.facade.SubjectFacade;
import lapitan.vkr.ApiService.subject.repository.SubjectRepository;
import lapitan.vkr.ApiService.subject.request.SubjectRequest;
import lapitan.vkr.ApiService.user.controller.UserController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SystemJpaTest
public class SubjectTests {

    @MockBean
    AmqpTemplate amqpTemplate;

    @Autowired
    SubjectController subjectController;

    @BeforeEach
    void setUp(){
        SQLStatementCountValidator.reset();
    }

    @DisplayName("Добавить Предмет.")
    @Test
    @Rollback
    @Sql({"classpath:sql/init.sql",
            "classpath:sql/add_users.sql"})
    void createSubject(){
        //given
        SubjectRequest subjectRequest = new SubjectRequest();
        subjectRequest.setName("123");
        subjectRequest.setLecturerId(1L);

        //when
        SubjectDto subjectDto = subjectController.addSubject(subjectRequest);
        subjectDto = subjectController.getSubject(subjectDto.getId());

        //then
        assertThat(subjectDto.getName().equals("123"));
        assertThat(subjectDto.getLecturerId().equals(1L));

    }

    @DisplayName("Добавить уже существующий Предмет.")
    @Test
    @Rollback
    @Sql({"classpath:sql/init.sql",
            "classpath:sql/add_users.sql"})
    void createExistingSubject(){
    }

    @DisplayName("Добавить Предмет к несуществующему пользователю.")
    @Test
    @Rollback
    @Sql({"classpath:sql/init.sql",
            "classpath:sql/add_users.sql"})
    void createSubjectToUnrealUser(){
    }

    @DisplayName("Добавить Предмет к не лектору.")
    @Test
    @Rollback
    @Sql({"classpath:sql/init.sql",
            "classpath:sql/add_users.sql"})
    void createSubjectToNotLecturer(){
    }

    @DisplayName("Получить Предмет.")
    @Test
    @Rollback
    @Sql({"classpath:sql/init.sql",
            "classpath:sql/add_users.sql"})
    void getSubject(){
    }

    @DisplayName("Получить несуществующий Предмет.")
    @Test
    @Rollback
    @Sql({"classpath:sql/init.sql",
            "classpath:sql/add_users.sql"})
    void getUnrealSubject(){
    }

    @DisplayName("Обновить Предмет.")
    @Test
    @Rollback
    @Sql({"classpath:sql/init.sql",
            "classpath:sql/add_users.sql"})
    void updateSubject(){
    }

    @DisplayName("Обновить несуществующий Предмет.")
    @Test
    @Rollback
    @Sql({"classpath:sql/init.sql",
            "classpath:sql/add_users.sql"})
    void updateUnrealSubject(){
    }

    @DisplayName("Удаление Предмета.")
    @Test
    @Rollback
    @Sql({"classpath:sql/init.sql",
            "classpath:sql/add_users.sql"})
    void deleteSubject(){
    }

    @DisplayName("Удаление несуществующего Предмета.")
    @Test
    @Rollback
    @Sql({"classpath:sql/init.sql",
            "classpath:sql/add_users.sql"})
    void deleteUnrealSubject(){
    }

    @DisplayName("Добавление Учителя в Предмет.")
    @Test
    @Rollback
    @Sql({"classpath:sql/init.sql",
            "classpath:sql/add_users.sql"})
    void addSubjectTeacher(){
    }

    @DisplayName("Добавление несуществующего Учителя в Предмет.")
    @Test
    @Rollback
    @Sql({"classpath:sql/init.sql",
            "classpath:sql/add_users.sql"})
    void addUnrealTeacherToSubject(){
    }

    @DisplayName("Добавление Учителя в несуществующий Предмет.")
    @Test
    @Rollback
    @Sql({"classpath:sql/init.sql",
            "classpath:sql/add_users.sql"})
    void addTeacherToUnrealSubject(){
    }

    @DisplayName("Получение всех Учителей Предмета.")
    @Test
    @Rollback
    @Sql({"classpath:sql/init.sql",
            "classpath:sql/add_users.sql"})
    void getSubjectTeachers(){
    }

    @DisplayName("Получение всех Учителей несуществующего Предмета.")
    @Test
    @Rollback
    @Sql({"classpath:sql/init.sql",
            "classpath:sql/add_users.sql"})
    void getUnrealSubjectTeachers(){
    }

    @DisplayName("Удаление Учителя из Предмета.")
    @Test
    @Rollback
    @Sql({"classpath:sql/init.sql",
            "classpath:sql/add_users.sql"})
    void deleteSubjectTeacher(){
    }

    @DisplayName("Удаление несуществующего Учителя из Предмета.")
    @Test
    @Rollback
    @Sql({"classpath:sql/init.sql",
            "classpath:sql/add_users.sql"})
    void deleteUnrealTeacherFromSubject(){
    }

    @DisplayName("Удаление Учителя, которого нет в предмете, из Предмета.")
    @Test
    @Rollback
    @Sql({"classpath:sql/init.sql",
            "classpath:sql/add_users.sql"})
    void deleteNotThisTeacherFromSubject(){
    }

    @DisplayName("Удаление Учителя из Несуществующего Предмета.")
    @Test
    @Rollback
    @Sql({"classpath:sql/init.sql",
            "classpath:sql/add_users.sql"})
    void deleteTeacherFromUnrealSubject(){
    }

}
