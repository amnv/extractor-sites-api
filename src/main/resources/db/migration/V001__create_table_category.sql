CREATE TABLE category (
    code BIGINT PRIMARY KEY NOT NULL DEFAULT nextval('code_id_seq'::regclass),
    nome VARCHAR(50) NOT NULL,

    CONSTRAINT code_pkey PRIMARY KEY (code)
    );


    INSERT INTO category values ('Lazer');
    INSERT INTO category values ('Alimentação');
    INSERT INTO category values ('Supermercado');
    INSERT INTO category values ('Farmácia');
    INSERT INTO category values ('Outros');