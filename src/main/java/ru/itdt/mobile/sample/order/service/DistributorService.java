package ru.itdt.mobile.sample.order.service;

import org.eclipse.microprofile.openapi.models.security.SecurityScheme;
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
    private static Integer scoreForCoursework(Student student, Coursework coursework)
    {
        int score = 0;
        score += scoreForSupervisor(student, coursework);
        //score += scoreForCompetencies(student, coursework);
        score += scoreForPreferences(student, coursework);
        //score += scoreForChoice(student, coursework);
        return score;
    }

    /// <summary>
    /// Распределение курсовых по студентам
    /// </summary>
    /// <param name="students"></param>
    /// <param name="courseworks"></param>
    public static List<Integer> ToDistribute(List<Student> students, List<Coursework> courseworks)
    {
        List<List<Integer>> matrix = CreateMatrix(students, courseworks);
        List<Integer> courseworkForStudent = Distributor.VengrAlgorithm(matrix);
        return courseworkForStudent;
        /*for (int i = 0; i < students.size(); i++)
        {
            students[i].coursework = courseworks[courseworkForStudent[i]];
            students[i].scoreForCoursework = matrix[i][courseworkForStudent[i]];

        }*/
    }

    /// <summary>
    /// Создание матрицы соответствия баллов для каждой пары студент-курсовая
    /// </summary>
    /// <param name="students"></param>
    /// <param name="courseworks"></param>
    /// <returns></returns>
    private static List<List<Integer>> CreateMatrix(List<Student> students, List<Coursework> courseworks)
    {
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

    private static int scoreForPreferences(Student student, Coursework coursework)
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
    }

    private static int scoreForSupervisor(Student student, Coursework coursework)
    {
        int score = 0;
        if (coursework.getTeacher() != null && student.getTeacher() != null && student.getTeacher() == coursework.getTeacher())
        {
            score += Score.teacher;
        }
        return score;
    }

    /*private static int scoreForChoice(Student student, Coursework coursework)
    {
        int score = 0;
        foreach (CourseworkChoice сourseworkСhoice in student.courseworksChoice)
        {
            if(сourseworkСhoice.courseworkId == coursework.id)
            {
                switch (сourseworkСhoice.choice)
                {
                    case Choice.YES:
                        score += Score.ChoiceYes;
                        break;
                    case Choice.MAYBE:
                        score += Score.ChoiceMaybe;
                        break;
                    case Choice.NO:
                        score += Score.ChoiceNo;
                        break;
                    case Choice.NOTHING:
                        score += Score.ChoiceNothing;
                        break;
                }
            }
        }
        if (score == 0)
        {
            score += Score.ChoiceNothing;
        }
        return score;
    }*/
}
