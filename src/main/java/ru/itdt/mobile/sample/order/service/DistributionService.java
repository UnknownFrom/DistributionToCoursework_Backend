package ru.itdt.mobile.sample.order.service;

import ru.itdt.mobile.sample.order.bean.PreferenceDTO;
import ru.itdt.mobile.sample.order.bean.StudentDTO;
import ru.itdt.mobile.sample.order.bean.TeacherDTO;
import ru.itdt.mobile.sample.order.bean.request.PreferencePostRequest;
import ru.itdt.mobile.sample.order.bean.request.StudentPostRequest;
import ru.itdt.mobile.sample.order.bean.request.TeacherPostRequest;
import ru.itdt.mobile.sample.order.domain.Preference;
import ru.itdt.mobile.sample.order.domain.Student;
import ru.itdt.mobile.sample.order.domain.Teacher;
import ru.itdt.mobile.sample.order.exception.ShopOrderNotExistException;
import ru.itdt.mobile.sample.order.mapper.PreferenceMapper;
import ru.itdt.mobile.sample.order.mapper.StudentMapper;
import ru.itdt.mobile.sample.order.mapper.TeacherMapper;
import ru.itdt.mobile.sample.order.repository.PreferenceRepository;
import ru.itdt.mobile.sample.order.repository.StudentRepository;
import ru.itdt.mobile.sample.order.repository.TeacherRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class DistributionService {
    @Inject
    StudentRepository studentRepository;
    @Inject
    TeacherRepository teacherRepository;
    @Inject
    PreferenceRepository preferenceRepository;
    @Inject
    StudentMapper studentMapper;
    @Inject
    TeacherMapper teacherMapper;
    @Inject
    PreferenceMapper preferenceMapper;

    public Student saveStudent(final StudentPostRequest studentPostRequest) {
        StudentDTO studentDTO = studentMapper.mapRequestToDTO(studentPostRequest);
        Student student = studentMapper.mapDTOToEntity(studentDTO);

        /*student.setPreferences(studentPostRequest.getPreferences()
                .stream()
                .map(preferenceDTO -> preferenceMapper.mapDTOToEntity(preferenceDTO, student))
                .collect(Collectors.toList()));*/
        return studentRepository.save(student);
    }

    public Teacher saveTeacher(final TeacherPostRequest teacherPostRequest) {
        TeacherDTO teacherDTO = teacherMapper.mapRequestToDTO(teacherPostRequest);
        Teacher teacher = teacherMapper.mapDTOToEntity(teacherDTO);

        /*student.setPreferences(teacherPostRequest.getPreferences()
                .stream()
                .map(preferenceDTO -> preferenceMapper.mapDTOToEntity(preferenceDTO, student))
                .collect(Collectors.toList()));*/
        return teacherRepository.save(teacher);
    }

    public Preference savePreference(final PreferencePostRequest preferencePostRequest) {
        PreferenceDTO preferenceDTO = preferenceMapper.mapToDTO(preferencePostRequest);
        Preference preference = preferenceMapper.mapDTOToEntity(preferenceDTO);
        return preferenceRepository.save(preference);
    }

    public void updatePreferredTeacherForStudent(long studentId, long teacherId) {
        Teacher teacher = teacherRepository.findById(teacherId);
        studentRepository.updateStudentPreferredTeacher(studentId, teacher);
    }

    public void updatePreferencesForStudent(List<Preference> preferences, long studentId) {
        studentRepository.updateStudentPreferences(studentId, preferences);
    }
    public void test(PreferenceDTO preferenceDTO, long studentId) {
        //Preference preference = preferenceMapper.mapDTOToEntity(preferenceDTO, studentRepository.findById(studentId));
        List<Preference> preferences = preferenceRepository.listAll();
        studentRepository.updateStudentPreferences(studentId, preferences);
        //preferenceRepository.save(preference);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .collect(Collectors.toList());
    }

    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll()
                .stream()
                .collect(Collectors.toList());
    }

    public List<Preference> getAllPreferences() {
        return preferenceRepository.findAll()
                .stream()
                .collect(Collectors.toList());
    }

    public void checkAccess(long orderId, long userId) {
        Student student = studentRepository.findById(orderId);
        if (student == null) {
            throw new ShopOrderNotExistException(String.format("Заказа с id=%d не существует", orderId));
        }
    }
}
