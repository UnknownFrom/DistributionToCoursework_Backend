package ru.itdt.mobile.sample.order.service;

import ru.itdt.mobile.sample.order.domain.Coursework;
import ru.itdt.mobile.sample.order.domain.Preference;
import ru.itdt.mobile.sample.order.domain.Score;
import ru.itdt.mobile.sample.order.domain.Student;

import java.util.ArrayList;
import java.util.List;

public class DistributorService {

    /// <summary>
    /// Вычисление количества очков для переданной пары студент-курсовая
    /// </summary>
    /// <param name="student"></param>
    /// <param name="coursework"></param>
    /// <returns></returns>
    private static Integer scoreForCoursework(Student student, Coursework coursework) {
        int score = 0;
        score += scoreForSupervisor(student, coursework);
        //score += scoreForCompetencies(student, coursework);
        score += scoreForPreferences(student, coursework);
        score += scoreForChoice(student, coursework);
        return score;
    }

    /// <summary>
    /// Распределение курсовых по студентам
    /// </summary>
    /// <param name="students"></param>
    /// <param name="courseworks"></param>
    public static List<Integer> ToDistribute(List<List<Integer>> matrix) {
        List<Integer> courseworkForStudent = Distributor.VengrAlgorithm(matrix);
        return courseworkForStudent;
    }

    /// <summary>
    /// Создание матрицы соответствия баллов для каждой пары студент-курсовая
    /// </summary>
    /// <param name="students"></param>
    /// <param name="courseworks"></param>
    /// <returns></returns>
    public static List<List<Integer>> CreateMatrix(List<Student> students, List<Coursework> courseworks) {
        List<List<Integer>> matrix = new ArrayList<>();

        for (Student student : students) {
            List<Integer> courseworkScore = new ArrayList<>();
            for (Coursework coursework : courseworks) {
                courseworkScore.add(scoreForCoursework(student, coursework));
            }
            matrix.add(courseworkScore);
        }
        return matrix;
    }

    /*private static int scoreForCompetencies(Student student, Coursework coursework)
    {
        int score = 0;
        if (coursework.getPreferences() != null && student.getPreferences() != null)
        {
            for (Preference preference : coursework.getPreferences())
            {
                if (student.getPreferences().contains(preference))
                {
                    score += Score.preference;
                }
            }
        }
        return score;
    }*/

    private static int scoreForPreferences(Student student, Coursework coursework) {
        int score = 0;
        if (coursework.getPreferences() != null && student.getPreferences() != null) {
            for (Preference preference : coursework.getPreferences()) {
                if (student.getPreferences().contains(preference)) {
                    score += Score.preference;
                }
            }
        }
        return score;
    }

    private static int scoreForSupervisor(Student student, Coursework coursework) {
        int score = 0;
        if (coursework.getTeacher() != null && student.getTeacher() != null && student.getTeacher() == coursework.getTeacher()) {
            score += Score.teacher;
        }
        return score;
    }

    private static int scoreForChoice(Student student, Coursework coursework) {
        int score = 0;
        for (Coursework selectedCoursework : student.getSelectedCoursework()) {
            if (selectedCoursework.equals(coursework)) {
                score += Score.selected;
                return score;
            }
        }
        for (Coursework unselectedCoursework : student.getUnselectedCoursework()) {
            if (unselectedCoursework.equals(coursework)) {
                score += Score.unselected;
                return score;
            }
        }
        return score;
    }
}
