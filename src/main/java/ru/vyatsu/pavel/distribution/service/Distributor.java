package ru.vyatsu.pavel.distribution.service;

import java.util.ArrayList;
import java.util.List;

public class Distributor {

    private static int rowN;
    private static List<List<Integer>> matrix; /* Матрица эффективности matrix[студенты][курсовые] */

    /* Паросочетания  */
    private static final ArrayList<Integer> XtoY = new ArrayList<>();         /* XtoY[студенты] */
    private static final ArrayList<Integer> YtoX = new ArrayList<>();         /* YtoX[курсовые] */

    private static final ArrayList<Boolean> vx = new ArrayList<>();          /* Была ли попытка назначить данному студенту курсовую vx[студенты] */
    private static final ArrayList<Boolean> vy = new ArrayList<>();          /* Подходит ли курсовая для назначения кому-то vy[курсовые] */

    private static final ArrayList<Integer> maxRow = new ArrayList<>();       /* Под какие баллы студент ищет курсовую */
    private static final ArrayList<Integer> minCol = new ArrayList<>();       /* Компенсация при понижении верхней границы */


    public static ArrayList<Integer> VengrAlgorithm(List<List<Integer>> curMatrix) {
        matrix = curMatrix;
        int n = matrix.size();
        rowN = matrix.get(0).size();
        int INF = Integer.MAX_VALUE;
        fillList(minCol, rowN, 0);
        fillList(maxRow, n, 0);
        for (int i = 0; i < n; ++i) /* нахождение максимальных значений в каждой строчке (максимально подходящая курсовая для студента) */ {
            for (int j = 0; j < rowN; ++j) {
                maxRow.set(i, Integer.max(maxRow.get(i), matrix.get(i).get(j)));
            }
        }

        fillList(XtoY, n, -1);
        fillList(YtoX, rowN, -1);

        for (int c = 0; c < n; ) {
            fillList(vx, n, false);
            fillList(vy, rowN, false);

            int k = 0;
            for (int i = 0; i < n; ++i) {
                /* если студенту не назначена курсовая, но получилось найти подходящую */
                if (XtoY.get(i) == -1 && tryReassign(i)) {
                    ++k;
                }
            }
            /* увеличиваем количество студентов, которым назначены курсовые */
            c += k;
            /* если не получилось никому назначить новые курсовые из свободных */
            if (k == 0) {
                /* нахождение минимальной разницы между максимальным и предмаксимальным баллом за курсовую среди всех студентов с курсовой(по каждому отдельно) */
                int minDiff = INF;
                for (int i = 0; i < n; ++i) {
                    /* если студенту пытались назначить/переназначить курсовую
                     * то есть у него или нет курсовой или он занимает курсовую ,которая подходит другому студенту */
                    if (vx.get(i)) {
                        for (int j = 0; j < rowN; ++j) {
                            /* если у курсовой нет назначеного на неё студента и нет подходящего по баллам*/
                            if (!vy.get(j)) {
                                minDiff = Integer.min(minDiff, maxRow.get(i) + minCol.get(j) - matrix.get(i).get(j));
                            }
                        }
                    }
                }
                for (int i = 0; i < n; ++i) {
                    /* занижаем верхнюю границу у студентов, которым пытались назначить курсовую,
                     * так как им или назначили курсовую, или подохоящая находится на уровень по баллам ниже */
                    if (vx.get(i)) maxRow.set(i, maxRow.get(i) - minDiff);
                }
                for (int i = 0; i < rowN; i++) {
                    /* повышаем общую подходимость у курсовых, которые уже назначены кому-то (компенсация для тех, кому уже назначена курсовая) */
                    if (vy.get(i)) minCol.set(i, minCol.get(i) + minDiff);
                }
            }
        }
        return XtoY;
    }

    /// <summary>
    /// Попытка перераспределения курсовых для данного студента
    /// </summary>
    /// <param name="i"></param>
    /// <returns></returns>
    private static Boolean tryReassign(int i) {
        if (vx.get(i)) {
            return false;
        }
        /* помечаем проверенной на подходящие курсовые */
        vx.set(i, true);
        for (int j = 0; j < rowN; ++j) /* помечаем курсовую, что для неё есть подходящий студент(у которого по данной курсовой максимальный балл) */ {
            /* из-за того, что мы изменили границы, теперь студенту будут помечаться курсовые, которые для него были предмаксимальными и всё ещё никем не занятые
             * либо уже назначенные ранее курсовые данному студенту (т.к мы компенсировали этот момент) */
            if (matrix.get(i).get(j) - maxRow.get(i) - minCol.get(j) == 0) {
                vy.set(j, true); /* помечаем курсовую, как потенциально подходящую какому-то студенту */
            }
        }
        for (int j = 0; j < rowN; ++j) /* назначаем студенту максимально подходящие свободные курсовые */ {
            /* из-за заниженных границ, студенту назначится предмаксимальная по баллам курсовая,
             * но которую можно назначить, так как будут минимальные потери от его переназначения на другую курсовую,
             * а его прошлую курсовую заберёт себе другой студент (под которого мы и занизили границу)*/
            if (matrix.get(i).get(j) - maxRow.get(i) - minCol.get(j) == 0 && YtoX.get(j) == -1) {
                /* если студенту назначается другая курсовая,
                 * то текущую не надо помечать как незанятую
                 * так как вернётся результат true и текущая сразу назначится
                 * студенту, который на неё претендовал */
                XtoY.set(i, j);
                YtoX.set(j, i);
                return true;
            }
        }
        /* если не осталось свободных курсовых для данного студента(i) */
        for (int j = 0; j < rowN; ++j) {
            /* если получится переназначить другому студенту курсовую,
             * с минимальным ущербом для него(так как мы снизили границу на минимально возможное число),
             * то назначаем текущему студенту курсовую того студента */
            /* YtoX[j] - это студент, которому назначена j курсовая
             * и мы проверяем, получится ли ему назначить другую курсовую*/
            if (matrix.get(i).get(j) - maxRow.get(i) - minCol.get(j) == 0 && tryReassign(YtoX.get(j))) {
                XtoY.set(i, j);
                YtoX.set(j, i);
                return true;
            }
        }
        return false;
    }

    private static void fillList(ArrayList<Integer> list, Integer length, Integer num) {
        list.clear();
        for (int i = 0; i < length; i++) {
            list.add(num);
        }
    }

    private static void fillList(ArrayList<Boolean> list, Integer length, Boolean num) {
        list.clear();
        for (int i = 0; i < length; i++) {
            list.add(num);
        }
    }
}
