CREATE SEQUENCE public.code_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

CREATE TABLE category (
    code BIGINT NOT NULL DEFAULT nextval('code_id_seq'::regclass),
    nome VARCHAR(50) NOT NULL,

    CONSTRAINT code_pkey PRIMARY KEY (code)
    );


INSERT INTO category values (1, 'Lazer');
INSERT INTO category values (2, 'Alimentação');
INSERT INTO category values (3, 'Supermercado');
INSERT INTO category values (4, 'Farmácia');
INSERT INTO category values (5, 'Outros');