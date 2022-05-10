package ru.itdt.mobile.sample.order.service;

import ru.itdt.mobile.sample.order.bean.*;
import ru.itdt.mobile.sample.order.bean.request.*;
import ru.itdt.mobile.sample.order.bean.response.AuthStudentResponse;
import ru.itdt.mobile.sample.order.bean.response.CourseworkResponse;
import ru.itdt.mobile.sample.order.bean.TeacherShort;
import ru.itdt.mobile.sample.order.domain.Coursework;
import ru.itdt.mobile.sample.order.domain.Preference;
import ru.itdt.mobile.sample.order.domain.Student;
import ru.itdt.mobile.sample.order.domain.Teacher;
import ru.itdt.mobile.sample.order.exception.AuthException;
import ru.itdt.mobile.sample.order.exception.GetException;
import ru.itdt.mobile.sample.order.exception.SaveException;
import ru.itdt.mobile.sample.order.exception.UpdateException;
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

    public StudentShort saveStudent(RegisterUserPostRequest request) {
        try {
            if (request.getLogin().isEmpty() || request.getPassword().isEmpty()) {
                throw new SaveException("Поле логин или пароль пустое");
            }
            Student student = studentMapper.mapRequestToEntity(request);
            return studentMapper.mapEntityToShort(studentRepository.save(student));
        } catch (SaveException e) {
            throw new SaveException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new SaveException("Не удалось сохранить студента");
        }
    }

    public TeacherShort saveTeacher(RegisterUserPostRequest request) {
        try {
            if (request.getLogin().isEmpty() || request.getPassword().isEmpty()) {
                throw new SaveException("Поле логин или пароль пустое");
            }
            Teacher teacher = teacherMapper.mapRequestToEntity(request);
            return teacherMapper.mapEntityToShort(teacherRepository.save(teacher));
        } catch (SaveException e) {
            throw new SaveException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new SaveException("Не удалось сохранить преподавателя");
        }
    }

    public CourseworkShort saveCoursework(final CourseworkPostRequest courseworkPostRequest) {
        try {
            Teacher teacher = teacherRepository.findById(courseworkPostRequest.getTeacherId());
            if (teacher == null) {
                throw new SaveException("Не удалось сохранить курсовую работу, так как не найден преподаватель с id=" + courseworkPostRequest.getTeacherId());
            }
            CourseworkDTO courseworkDTO = courseworkMapper.mapRequestToDTO(courseworkPostRequest, teacher);
            Coursework coursework = courseworkMapper.mapDTOToEntity(courseworkDTO);
            return courseworkMapper.mapEntityToShort(courseworkRepository.save(coursework));
        } catch (SaveException e) {
            throw new SaveException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new SaveException("Не удалось сохранить курсовой проект");
        }
    }

    public Preference savePreference(final PreferencePostRequest preferencePostRequest) {
        try {
            if (preferencePostRequest.getName().isEmpty()) {
                throw new SaveException("Не передано название предпочтения");
            }
            Preference preference = preferenceMapper.mapToEntity(preferencePostRequest);
            return preferenceRepository.save(preference);
        } catch (SaveException e) {
            throw new SaveException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new SaveException("Не удалось сохранить предпочтение");
        }
    }

    public AuthStudentResponse authStudent(final AuthPostRequest authPostRequest) {
        try {
            return studentMapper.mapEntityToResponse(studentRepository.findByLoginAndPassword(authPostRequest.getLogin(), authPostRequest.getPassword()));
        } catch (AuthException e) {
            throw new AuthException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new AuthException("Ошибка при авторизации");
        }
    }

    public TeacherShort authTeacher(final AuthPostRequest authPostRequest) {
        try {
            return teacherMapper.mapEntityToShort(teacherRepository.findByLoginAndPassword(authPostRequest.getLogin(), authPostRequest.getPassword()));
        } catch (AuthException e) {
            throw new AuthException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new AuthException("Ошибка при авторизации");
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
            e.printStackTrace();
            throw new GetException("Ошибка при попытке получить курсовые, принадлежащие преподавателю с id=" + teacherId);
        }
    }

    public CourseworkResponse getCoursework(long courseworkId) {
        try {
            return courseworkMapper.mapEntityToResponse(courseworkRepository.findById(courseworkId));
        } catch (Exception e) {
            e.printStackTrace();
            throw new GetException("Ошибка при попытке получить курсовую с id=" + courseworkId);
        }
    }

    public void updatePreferredTeacherForStudent(long studentId, long teacherId) {
        try {
            Teacher teacher = teacherRepository.findById(teacherId);
            studentRepository.updateStudentPreferredTeacher(studentId, teacher);
        } catch (UpdateException e) {
            throw new UpdateException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new UpdateException("Ошибка при попытке обновить предпочтительного преподавателя для студента с id=" + studentId);
        }
    }

    public void updateCourseworkForStudent(UpdateCourseworkForStudentRequest updateCourseworkForStudentRequest, long studentId) {
        try {
            List<Coursework> selectedCourseworkList = courseworkRepository.getCourseworkList(updateCourseworkForStudentRequest.getSelected());
            List<Coursework> unselectedCourseworkList = courseworkRepository.getCourseworkList(updateCourseworkForStudentRequest.getUnselected());
            studentRepository.updateStudentSelectedCoursework(studentId, selectedCourseworkList);
            studentRepository.updateStudentUnselectedCoursework(studentId, unselectedCourseworkList);
        } catch (UpdateException e) {
            throw new UpdateException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new UpdateException("Ошибка при попытке обновить выбор курсовых для студента с id=" + studentId);
        }
    }

    public void updatePreferencesForStudent(List<Preference> preferences, long studentId) {
        try {
            studentRepository.updateStudentPreferences(studentId, preferences);
        } catch (UpdateException e) {
            throw new UpdateException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new UpdateException("Ошибка при попытке обновить предпочтения для студента с id=" + studentId);
        }
    }

    public void updatePreferencesForCoursework(List<Preference> preferences, long courseworkId) {
        try {
            courseworkRepository.updateCourseworkPreferences(courseworkId, preferences);
        } catch (UpdateException e) {
            throw new UpdateException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new UpdateException("Ошибка при попытке обновить предпочтения для курсовой с id=" + courseworkId);
        }
    }

    public List<StudentShort> getAllStudents() {
        try {
            List<StudentShort> responseList;
            List<Student> students = studentRepository.findAll()
                    .stream()
                    .collect(Collectors.toList());
            responseList = students
                    .stream()
                    .map(studentMapper::mapEntityToShort)
                    .collect(Collectors.toList());
            return responseList;
        } catch (Exception e) {
            e.printStackTrace();
            throw new GetException("Ошибка при попытке получить всех студентов");
        }
    }

    public List<TeacherShort> getAllTeachers() {
        try {
            return teacherRepository.findAll()
                    .stream()
                    .map(teacherMapper::mapEntityToShort)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            throw new GetException("Ошибка при попытке получить всех преподавателей");
        }
    }

    public List<Preference> getAllPreferences() {
        try {
            return preferenceRepository.findAll()
                    .stream()
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            throw new GetException("Ошибка при попытке получить всех предпочтений");
        }
    }

    public List<CourseworkResponse> getAllCoursework() {
        try {
            List<CourseworkResponse> responseList;
            List<Coursework> students = courseworkRepository.findAll()
                    .stream()
                    .collect(Collectors.toList());
            responseList = students.stream().map(courseworkMapper::mapEntityToResponse).collect(Collectors.toList());
            return responseList;
        } catch (Exception e) {
            e.printStackTrace();
            throw new GetException("Ошибка при попытке получить всех курсовых работ");
        }
    }

    public List<PairStudentCoursework> getResultDistribution() {
        try {
            List<Student> students = studentRepository.listAll();
            List<Coursework> courseworks = courseworkRepository.listAll();
            List<List<Integer>> matrix = DistributorService.CreateMatrix(students, courseworks);
            List<Integer> result = DistributorService.ToDistribute(matrix);
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
                pairStudentCourseworks.get(i).setScore(matrix.get(i).get(result.get(i)));
            }
            return pairStudentCourseworks;
        } catch (Exception e) {
            e.printStackTrace();
            throw new GetException("Ошибка при попытке получить результат распределения курсовых работ по студентам");
        }
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
