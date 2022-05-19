CREATE TABLE public.teacher
(
    id       bigint                 NOT NULL,
    name     character varying(255) NOT NULL,
    login    character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    CONSTRAINT teacher_pk PRIMARY KEY (id),
    CONSTRAINT unique_teacher UNIQUE (login)
);

CREATE TABLE public.student
(
    id         bigint                 NOT NULL,
    name       character varying(255) NOT NULL,
    login      character varying(255) NOT NULL,
    password   character varying(255) NOT NULL,
    teacher_id bigint,
    CONSTRAINT student_pk PRIMARY KEY (id),
    CONSTRAINT unique_student UNIQUE (login),
    CONSTRAINT teacher_id_fk FOREIGN KEY (teacher_id)
        REFERENCES public.teacher (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE public.coursework
(
    id          bigint                 NOT NULL,
    name        character varying(255) NOT NULL,
    description TEXT,
    teacher_id  bigint,
    CONSTRAINT coursework_pk PRIMARY KEY (id),
    CONSTRAINT unique_coursework UNIQUE (name),
    CONSTRAINT teacher_id_fk FOREIGN KEY (teacher_id)
        REFERENCES public.teacher (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE public.preference
(
    id   bigint                 NOT NULL,
    name character varying(255) NOT NULL,
    CONSTRAINT preference_pk PRIMARY KEY (id),
    CONSTRAINT unique_preference UNIQUE (name)
);

CREATE TABLE public.student_preference
(
    student_id    bigint,
    preference_id bigint,
    CONSTRAINT student_preference_pk PRIMARY KEY (preference_id, student_id),
    CONSTRAINT student_id_fk FOREIGN KEY (student_id)
        REFERENCES public.student (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT preference_id_fk FOREIGN KEY (preference_id)
        REFERENCES public.preference (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE

);

CREATE TABLE public.student_coursework_selected
(
    student_id    bigint,
    coursework_id bigint,
    CONSTRAINT student_coursework_selected_pk PRIMARY KEY (coursework_id, student_id),
    CONSTRAINT student_id_fk FOREIGN KEY (student_id)
        REFERENCES public.student (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT coursework_id_fk FOREIGN KEY (coursework_id)
        REFERENCES public.coursework (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE public.student_coursework_unselected
(
    student_id    bigint,
    coursework_id bigint,
    CONSTRAINT student_coursework_unselected_pk PRIMARY KEY (coursework_id, student_id),
    CONSTRAINT student_id_fk FOREIGN KEY (student_id)
        REFERENCES public.student (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT coursework_id_fk FOREIGN KEY (coursework_id)
        REFERENCES public.coursework (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE public.coursework_preference
(
    coursework_id bigint,
    preference_id bigint,
    CONSTRAINT coursework_preference_pk PRIMARY KEY (preference_id, coursework_id),
    CONSTRAINT coursework_id_fk FOREIGN KEY (coursework_id)
        REFERENCES public.coursework (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT preference_id_fk FOREIGN KEY (preference_id)
        REFERENCES public.preference (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

ALTER TABLE public.student
    OWNER TO postgres;

ALTER TABLE public.teacher
    OWNER TO postgres;

ALTER TABLE public.coursework
    OWNER TO postgres;

ALTER TABLE public.preference
    OWNER TO postgres;

CREATE SEQUENCE public.student_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE CACHE 1
    OWNED BY student.id;

CREATE SEQUENCE public.teacher_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE CACHE 1
    OWNED BY teacher.id;

CREATE SEQUENCE public.coursework_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE CACHE 1
    OWNED BY coursework.id;

CREATE SEQUENCE public.preference_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE CACHE 1
    OWNED BY preference.id;

ALTER TABLE public.student_id_seq
    OWNER TO postgres;

ALTER TABLE public.teacher_id_seq
    OWNER TO postgres;

ALTER TABLE public.coursework_id_seq
    OWNER TO postgres;

ALTER TABLE public.preference_id_seq
    OWNER TO postgres;