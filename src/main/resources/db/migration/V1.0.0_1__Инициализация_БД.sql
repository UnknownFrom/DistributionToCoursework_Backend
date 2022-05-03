CREATE TABLE public.student
(
    id       bigint                 NOT NULL,
    name     character varying(255) NOT NULL,
    login    character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    CONSTRAINT student_pk PRIMARY KEY (id)
);

CREATE TABLE public.coursework
(
    id   bigint                 NOT NULL,
    name character varying(255) NOT NULL,
    CONSTRAINT coursework_pk PRIMARY KEY (id)
);

CREATE TABLE public.teacher
(
    id            bigint                 NOT NULL,
    name          character varying(255) NOT NULL,
    login         character varying(255) NOT NULL,
    password      character varying(255) NOT NULL,
    coursework_id bigint,
    student_id    bigint,
    CONSTRAINT teacher_pk PRIMARY KEY (id),
    CONSTRAINT coursework_id_fk FOREIGN KEY (coursework_id)
        REFERENCES public.coursework (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT student_id_fk FOREIGN KEY (student_id)
        REFERENCES public.student (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
);



CREATE TABLE public.preference
(
    id            bigint                 NOT NULL,
    name          character varying(255) NOT NULL,
    student_id    bigint,
    coursework_id bigint,
    CONSTRAINT preference_pk PRIMARY KEY (id),
    CONSTRAINT student_id_fk FOREIGN KEY (student_id)
        REFERENCES public.student (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT coursework_id_fk FOREIGN KEY (coursework_id)
        REFERENCES public.coursework (id) MATCH SIMPLE
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