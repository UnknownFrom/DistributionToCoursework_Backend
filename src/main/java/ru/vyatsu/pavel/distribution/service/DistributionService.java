package ru.vyatsu.pavel.distribution.service;

import ru.vyatsu.pavel.distribution.bean.*;
import ru.vyatsu.pavel.distribution.bean.request.*;
import ru.vyatsu.pavel.distribution.bean.response.CourseworkResponse;
import ru.vyatsu.pavel.distribution.bean.response.StudentResponse;
import ru.vyatsu.pavel.distribution.domain.Coursework;
import ru.vyatsu.pavel.distribution.domain.Preference;
import ru.vyatsu.pavel.distribution.domain.Student;
import ru.vyatsu.pavel.distribution.domain.Teacher;
import ru.vyatsu.pavel.distribution.exception.AuthException;
import ru.vyatsu.pavel.distribution.exception.GetException;
import ru.vyatsu.pavel.distribution.exception.SaveException;
import ru.vyatsu.pavel.distribution.exception.UpdateException;
import ru.vyatsu.pavel.distribution.mapper.*;
import ru.vyatsu.pavel.distribution.repository.CourseworkRepository;
import ru.vyatsu.pavel.distribution.repository.PreferenceRepository;
import ru.vyatsu.pavel.distribution.repository.StudentRepository;
import ru.vyatsu.pavel.distribution.repository.TeacherRepository;

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
            throw new SaveException("Не удалось сохранить преподавателя");
        }
    }

    public CourseworkResponse saveCoursework(final CourseworkPostRequest courseworkPostRequest) {
        try {
            Teacher teacher = teacherRepository.findById(courseworkPostRequest.getTeacherId());
            if (teacher == null) {
                throw new SaveException("Не удалось сохранить курсовую работу, так как не найден преподаватель с id=" + courseworkPostRequest.getTeacherId());
            }
            CourseworkDTO courseworkDTO = courseworkMapper.mapRequestToDTO(courseworkPostRequest, teacher);
            Coursework coursework = courseworkMapper.mapDTOToEntity(courseworkDTO);
            return courseworkMapper.mapEntityToResponse(courseworkRepository.save(coursework));
        } catch (SaveException e) {
            throw new SaveException(e.getMessage());
        } catch (Exception e) {
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
            throw new SaveException("Не удалось сохранить предпочтение");
        }
    }

    public StudentResponse authStudent(final AuthPostRequest authPostRequest) {
        try {
            return studentMapper.mapEntityToResponse(studentRepository.findByLoginAndPassword(authPostRequest.getLogin(), authPostRequest.getPassword()));
        } catch (AuthException e) {
            throw new AuthException(e.getMessage());
        } catch (Exception e) {
            throw new AuthException("Ошибка при авторизации");
        }
    }

    public TeacherShort authTeacher(final AuthPostRequest authPostRequest) {
        try {
            return teacherMapper.mapEntityToShort(teacherRepository.findByLoginAndPassword(authPostRequest.getLogin(), authPostRequest.getPassword()));
        } catch (AuthException e) {
            throw new AuthException(e.getMessage());
        } catch (Exception e) {
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
            throw new GetException("Ошибка при попытке получить курсовые, принадлежащие преподавателю с id=" + teacherId);
        }
    }

    public CourseworkResponse getCoursework(long courseworkId) {
        try {
            return courseworkMapper.mapEntityToResponse(courseworkRepository.findById(courseworkId));
        } catch (Exception e) {
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
            throw new UpdateException("Ошибка при попытке обновить выбор курсовых для студента с id=" + studentId);
        }
    }

    public CourseworkResponse updateCoursework(UpdateCoursework coursework) {
        try {
            Teacher teacher = teacherRepository.findById(coursework.getTeacherId());
            return courseworkMapper.mapEntityToResponse(courseworkRepository.updateCoursework(coursework, teacher));
        } catch (UpdateException e) {
            throw new UpdateException(e.getMessage());
        } catch (Exception e) {
            throw new UpdateException("Ошибка при попытке обновить курсовую с id=" + coursework.getId());
        }
    }

    public void updatePreferencesForStudent(List<Preference> preferences, long studentId) {
        try {
            studentRepository.updateStudentPreferences(studentId, preferences);
        } catch (UpdateException e) {
            throw new UpdateException(e.getMessage());
        } catch (Exception e) {
            throw new UpdateException("Ошибка при попытке обновить предпочтения для студента с id=" + studentId);
        }
    }

    public void updatePreferencesForCoursework(List<Preference> preferences, long courseworkId) {
        try {
            courseworkRepository.updateCourseworkPreferences(courseworkId, preferences);
        } catch (UpdateException e) {
            throw new UpdateException(e.getMessage());
        } catch (Exception e) {
            throw new UpdateException("Ошибка при попытке обновить предпочтения для курсовой с id=" + courseworkId);
        }
    }

    public List<StudentResponse> getAllStudents() {
        try {
            List<StudentResponse> responseList;
            List<Student> students = studentRepository.findAll()
                .stream()
                .collect(Collectors.toList());
            responseList = students
                .stream()
                .map(studentMapper::mapEntityToResponse)
                .collect(Collectors.toList());
            return responseList;
        } catch (Exception e) {
            throw new GetException("Ошибка при попытке получить всех студентов");
        }
    }

    public List<StudentShort> getAllStudentsShort() {
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
            throw new GetException("Ошибка при попытке получить всех студентов");
        }
    }

    public StudentResponse getStudent(long studentId) {
        try {
            return studentMapper.mapEntityToResponse(studentRepository.findById(studentId));
        } catch (Exception e) {
            throw new GetException("Ошибка при попытке получить студента");
        }
    }

    public List<TeacherShort> getAllTeachers() {
        try {
            return teacherRepository.findAll()
                .stream()
                .map(teacherMapper::mapEntityToShort)
                .collect(Collectors.toList());
        } catch (Exception e) {
            throw new GetException("Ошибка при попытке получить всех преподавателей");
        }
    }

    public List<Preference> getAllPreferences() {
        try {
            return preferenceRepository.findAll()
                .stream()
                .collect(Collectors.toList());
        } catch (Exception e) {
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
            throw new GetException("Ошибка при попытке получить всех курсовых работ");
        }
    }

    public List<PairStudentCoursework> getResultDistribution() {
        try {
            List<Student> students = studentRepository.listAll();
            List<Coursework> courseworks = courseworkRepository.listAll();
            if (students.size() > courseworks.size()) {
                throw new GetException("Не удалось получить распределение курсовых, так как студентов больше, чем курсовых работ");
            }
            List<List<Integer>> matrix = DistributorService.CreateMatrix(students, courseworks);
            List<Integer> result = DistributorService.ToDistribute(matrix);
            List<StudentResponse> studentShorts = students
                .stream()
                .map(studentMapper::mapEntityToResponse)
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
}
