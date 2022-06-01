ALTER TABLE public.student
    DROP CONSTRAINT teacher_id_fk;
ALTER TABLE public.student
    ADD CONSTRAINT teacher_id_fk FOREIGN KEY (teacher_id)
        REFERENCES public.teacher (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE SET NULL;