package lapitan.vkr.ApiService.subject.service;

import lapitan.vkr.ApiService.subject.dto.SubjectDto;
import lapitan.vkr.ApiService.subject.entity.Subject;
import lapitan.vkr.ApiService.subject.exception.NoSuchSubjectException;
import lapitan.vkr.ApiService.subject.mapper.SubjectMapper;
import lapitan.vkr.ApiService.subject.repository.SubjectRepository;
import lapitan.vkr.ApiService.subject.request.SubjectRequest;
import lapitan.vkr.ApiService.user.dto.UserDto;
import lapitan.vkr.ApiService.user.entity.Person;
import lapitan.vkr.ApiService.user.exception.NoSuchUserException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SubjectService {

    SubjectRepository subjectRepository;
    SubjectMapper subjectMapper;

    public SubjectService(SubjectRepository subjectRepository, SubjectMapper subjectMapper) {
        this.subjectRepository = subjectRepository;
        this.subjectMapper = subjectMapper;
    }

    @Transactional
    public Subject addSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    @Transactional
    public Subject getSubject(Long id) {
        return subjectRepository.findById(id).orElseThrow(() ->
                new NoSuchSubjectException("get subject: can't find subject with id: " + id));
    }

    @Transactional
    public Subject updateSubject(Long id, Subject subject) {
        Subject savedSubject = subjectRepository.findById(id).orElseThrow(() ->
                new NoSuchSubjectException("update subject: can't find subject with id: " + id));
        subject.setId(id);
        subject.setPracticeTeachers(savedSubject.getPracticeTeachers());
        return subjectRepository.save(subject);
    }

    @Transactional
    public void deleteSubject(Long id) {
        subjectRepository.findById(id).orElseThrow(() ->
                new NoSuchSubjectException("delete subject: can't find subject with id: " + id));
        subjectRepository.deleteById(id);
    }

    public List<Subject> getFilteredSubjects() {
        return null;
    }

    @Transactional
    public Subject addSubjectTeacher(Long id, Person teacher) {
        Subject subject = subjectRepository.findById(id).orElseThrow(() ->
                new NoSuchSubjectException("update subject: can't find subject with id: " + id));
        subject.addTeacher(teacher);
        return subjectRepository.save(subject);
    }

    @Transactional
    public List<Person> getSubjectTeachers(Long id) {
        return subjectRepository.findById(id).orElseThrow(() ->
                        new NoSuchSubjectException("update subject: can't find subject with id: " + id))
                .getPracticeTeachers();
    }

    @Transactional
    public void deleteSubjectTeacher(Long id, Person teacher) {
        Subject subject=subjectRepository.findById(id).orElseThrow(() ->
                new NoSuchSubjectException("update subject: can't find subject with id: " + id));
        if (subject.getPracticeTeachers().contains(teacher)) {
            throw new NoSuchUserException("delete teacher: can't find teacher with id: "+teacher.getId()+" in subject with id: "+id);
        }
        subject.deleteTeacher(teacher);
    }
}
