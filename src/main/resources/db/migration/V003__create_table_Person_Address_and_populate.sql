CREATE SEQUENCE public.person_code_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

CREATE TABLE person (
    code BIGINT NOT NULL DEFAULT nextval('person_code_id_seq'::regclass),
    name VARCHAR(50) NOT NULL,
    active BOOLEAN NOT NULL,
    street VARCHAR(50),
    number VARCHAR(10),
    extras VARCHAR(50),
    neighbor VARCHAR(20),
    cep VARCHAR(10),
    city VARCHAR(20),
    state VARCHAR(20),
    CONSTRAINT person_code_pkey PRIMARY KEY (code)
    );


INSERT INTO person values (1, 'Carlos', true,'Rua das moças', '942', null, 'Arruda', '52.120-320', 'Recife', 'PE');
INSERT INTO person values (2, 'Pedro', true, 'Rua zeferino agra', '123', '52.120-180', 'Recife', 'PE');
INSERT INTO person values (3, 'Paulo', false);
INSERT INTO person values (4, 'Francisco', false);
