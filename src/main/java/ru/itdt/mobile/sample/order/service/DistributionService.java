package ru.itdt.mobile.sample.order.service;

import ru.itdt.mobile.sample.order.bean.*;
import ru.itdt.mobile.sample.order.bean.request.*;
import ru.itdt.mobile.sample.order.bean.response.AuthStudentResponse;
import ru.itdt.mobile.sample.order.bean.response.CourseworkResponse;
import ru.itdt.mobile.sample.order.bean.response.TeacherResponse;
import ru.itdt.mobile.sample.order.domain.Coursework;
import ru.itdt.mobile.sample.order.domain.Preference;
import ru.itdt.mobile.sample.order.domain.Student;
import ru.itdt.mobile.sample.order.domain.Teacher;
import ru.itdt.mobile.sample.order.exception.ShopOrderNotExistException;
import ru.itdt.mobile.sample.order.mapper.*;
import ru.itdt.mobile.sample.order.repository.CourseworkRepository;
import ru.itdt.mobile.sample.order.repository.PreferenceRepository;
import ru.itdt.mobile.sample.order.repository.StudentRepository;
import ru.itdt.mobile.sample.order.repository.TeacherRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class DistributionService {
    @Inject
    StudentRepository studentRepository;
    @Inject
    TeacherRepository teacherRepository;
    @Inject
    CourseworkRepository courseworkRepository;
    @Inject
    PreferenceRepository preferenceRepository;
    @Inject
    StudentMapper studentMapper;
    @Inject
    TeacherMapper teacherMapper;
    @Inject
    PreferenceMapper preferenceMapper;
    @Inject
    CourseworkMapper courseworkMapper;
    @Inject
    DistributionMapper distributionMapper;

    public AuthStudentResponse saveStudent(final SaveUserPostRequest saveUserPostRequest) {
        try {
            Student student = studentMapper.mapRequestToEntity(saveUserPostRequest);
            return studentMapper.mapEntityToResponse(studentRepository.save(student));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Teacher saveTeacher(final SaveUserPostRequest saveUserPostRequest) {
        try {
            Teacher teacher = teacherMapper.mapRequestToEntity(saveUserPostRequest);
            return teacherRepository.save(teacher);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Coursework saveCoursework(final CourseworkPostRequest courseworkPostRequest) {
        try {
            Teacher teacher = teacherRepository.findById(courseworkPostRequest.getTeacherId());
            CourseworkDTO courseworkDTO = courseworkMapper.mapRequestToDTO(courseworkPostRequest, teacher);
            Coursework coursework = courseworkMapper.mapDTOToEntity(courseworkDTO);
            return courseworkRepository.save(coursework);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Preference savePreference(final PreferencePostRequest preferencePostRequest) {
        try {
            if (preferencePostRequest.getName().isEmpty()) {
                throw new Exception();
            }
            Preference preference = preferenceMapper.mapToEntity(preferencePostRequest);
            return preferenceRepository.save(preference);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public AuthStudentResponse authStudent(final AuthPostRequest authPostRequest) {
        try {
            return studentMapper.mapEntityToResponse(studentRepository.findByLoginAndPassword(authPostRequest.getLogin(), authPostRequest.getPassword()));
        } catch (Exception e) {
            return null;
        }
    }

    public TeacherResponse authTeacher(final AuthPostRequest authPostRequest) {
        try {
            return teacherMapper.mapEntityToResponse(teacherRepository.findByLoginAndPassword(authPostRequest.getLogin(), authPostRequest.getPassword()));
        } catch (Exception e) {
            return null;
        }
    }

    public List<CourseworkShort> getCourseworkForTeacher(long teacherId) {
        try {
            return courseworkRepository
                    .getCourseworkList(teacherId)
                    .stream()
                    .map(courseworkMapper::mapEntityToShort)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            return null;
        }
    }

    public CourseworkResponse getCoursework(long courseworkId) {
        try {
            return courseworkMapper.mapEntityToResponse(courseworkRepository.findById(courseworkId));
        } catch (Exception e) {
            return null;
        }
    }

    public void updatePreferredTeacherForStudent(long studentId, long teacherId) {
        Teacher teacher = teacherRepository.findById(teacherId);
        studentRepository.updateStudentPreferredTeacher(studentId, teacher);
    }
    public void updateCourseworkForStudent(UpdateCourseworkForStudentRequest updateCourseworkForStudentRequest, long studentId) {
        List<Coursework> selectedCourseworkList = courseworkRepository.getCourseworkList(updateCourseworkForStudentRequest.getSelected());
        List<Coursework> unselectedCourseworkList = courseworkRepository.getCourseworkList(updateCourseworkForStudentRequest.getUnselected());
        studentRepository.updateStudentSelectedCoursework(studentId, selectedCourseworkList);
        studentRepository.updateStudentUnselectedCoursework(studentId, unselectedCourseworkList);
    }

    public void updatePreferencesForStudent(List<Preference> preferences, long studentId) {
        studentRepository.updateStudentPreferences(studentId, preferences);
    }

    public void updatePreferencesForCoursework(List<Preference> preferences, long courseworkId) {
        courseworkRepository.updateCourseworkPreferences(courseworkId, preferences);
    }

    public List<AuthStudentResponse> getAllStudents() {
        List<AuthStudentResponse> responseList;
        List<Student> students = studentRepository.findAll()
                .stream()
                .collect(Collectors.toList());
        responseList = students.stream().map(studentMapper::mapEntityToResponse).collect(Collectors.toList());
        return responseList;
    }

    public List<TeacherResponse> getAllTeachers() {
        return teacherRepository.findAll()
                .stream()
                .map(teacherMapper::mapEntityToResponse)
                .collect(Collectors.toList());
    }

    public List<Preference> getAllPreferences() {
        return preferenceRepository.findAll()
                .stream()
                .collect(Collectors.toList());
    }

    public List<CourseworkResponse> getAllCoursework() {
        List<CourseworkResponse> responseList;
        List<Coursework> students = courseworkRepository.findAll()
                .stream()
                .collect(Collectors.toList());
        responseList = students.stream().map(courseworkMapper::mapEntityToResponse).collect(Collectors.toList());
        return responseList;
    }

    public void checkAccess(long orderId, long userId) {
        Student student = studentRepository.findById(orderId);
        if (student == null) {
            throw new ShopOrderNotExistException(String.format("Заказа с id=%d не существует", orderId));
        }
    }

    public List<PairStudentCoursework> getResultDistribution() {
        List<Student> students = studentRepository.listAll();
        List<Coursework> courseworks = courseworkRepository.listAll();
        List<Integer> result = DistributorService.ToDistribute(students, courseworks);
        List<StudentShort> studentShorts = students
                .stream()
                .map(studentMapper::mapEntityToShort)
                .collect(Collectors.toList());
        List<CourseworkShort> courseworkShorts = courseworks
                .stream()
                .map(courseworkMapper::mapEntityToShort)
                .collect(Collectors.toList());
        List<PairStudentCoursework> pairStudentCourseworks = new ArrayList<>();
        for (int i = 0; i < result.size(); i++) {
            pairStudentCourseworks.add(distributionMapper
                    .mapToPair(studentShorts.get(i), courseworkShorts.get(result.get(i))));
        }
        return pairStudentCourseworks;
    }

    /*public List<PairStudentCoursework> test() {
        List<Student> students = studentRepository.listAll();
        List<Coursework> courseworks = courseworkRepository.listAll();
        List<Integer> result = DistributorService.ToDistribute(students, courseworks);
        List<StudentShort> studentShorts = students
                .stream()
                .map(studentMapper::mapEntityToShort)
                .collect(Collectors.toList());
        List<CourseworkShort> courseworkShorts = courseworks
                .stream()
                .map(courseworkMapper::mapEntityToShort)
                .collect(Collectors.toList());
        List<PairStudentCoursework> pairStudentCourseworks = new ArrayList<>();
        for (int i = 0; i < result.size(); i++) {
            pairStudentCourseworks.add(distributionMapper
                    .mapToPair(studentShorts.get(i), courseworkShorts.get(result.get(i))));
        }
        return pairStudentCourseworks;
    }*/
}
