package ru.itdt.mobile.sample.order.domain;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

public class Score {
    public static Integer preference=40;
    public static Integer teacher=50;
    public static Integer selected=100;
    public static Integer unselected=-50;
}
