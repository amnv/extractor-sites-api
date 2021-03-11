CREATE SEQUENCE public.financial_releases_code_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

CREATE TABLE financial_release (
    code BIGINT NOT NULL DEFAULT nextval('financial_releases_code_id_seq'::regclass),
    description VARCHAR(50) NOT NULL,
    due_date DATE NOT NULL,
    pay_date DATE,
    amount DECIMAL(10,2) NOT NULL,
    obs VARCHAR(100),
    type VARCHAR(20) NOT NULL,
    person_fk BIGINT NOT NULL,
    category_fk BIGINT NOT NULL,
    CONSTRAINT financial_release_code_pkey PRIMARY KEY (code),
    CONSTRAINT constraint_category_fk FOREIGN KEY (category_fk)
        REFERENCES public.category(code),
    CONSTRAINT constraint_person_fk FOREIGN KEY (person_fk)
        REFERENCES public.person(code));

INSERT INTO financial_release (description, due_date, pay_date, amount, obs, type, person_fk, category_fk) values ('Salário mensal', '2017-06-10', null, 6500.00, 'Distribuição de lucros', 'RECEITA', 1, 1);
INSERT INTO financial_release (description, due_date, pay_date, amount, obs, type, person_fk, category_fk) values ('Bahamas', '2017-02-10', '2017-02-10', 100.32, null, 'DESPESA', 2, 2);
INSERT INTO financial_release (description, due_date, pay_date, amount, obs, type, person_fk, category_fk) values ('Top Club', '2017-06-10', null, 120, null, 'RECEITA', 3, 3);
INSERT INTO financial_release (description, due_date, pay_date, amount, obs, type, person_fk, category_fk) values ('CEMIG', '2017-02-10', '2017-02-10', 110.44, 'Geração', 'RECEITA', 3, 4);
INSERT INTO financial_release (description, due_date, pay_date, amount, obs, type, person_fk, category_fk) values ('DMAE', '2017-06-10', null, 200.30, null, 'DESPESA', 3, 5);
INSERT INTO financial_release (description, due_date, pay_date, amount, obs, type, person_fk, category_fk) values ('Extra', '2017-03-10', '2017-03-10', 1010.32, null, 'RECEITA', 4, 6);
INSERT INTO financial_release (description, due_date, pay_date, amount, obs, type, person_fk, category_fk) values ('Bahamas', '2017-06-10', null, 500, null, 'RECEITA', 1, 7);
INSERT INTO financial_release (description, due_date, pay_date, amount, obs, type, person_fk, category_fk) values ('Top Club', '2017-03-10', '2017-03-10', 400.32, null, 'DESPESA', 4, 8);
INSERT INTO financial_release (description, due_date, pay_date, amount, obs, type, person_fk, category_fk) values ('Despachante', '2017-06-10', null, 123.64, 'Multas', 'DESPESA', 3, 9);
INSERT INTO financial_release (description, due_date, pay_date, amount, obs, type, person_fk, category_fk) values ('Pneus', '2017-04-10', '2017-04-10', 665.33, null, 'RECEITA', 5, 10);
INSERT INTO financial_release (description, due_date, pay_date, amount, obs, type, person_fk, category_fk) values ('Café', '2017-06-10', null, 8.32, null, 'DESPESA', 1, 5);
INSERT INTO financial_release (description, due_date, pay_date, amount, obs, type, person_fk, category_fk) values ('Eletrônicos', '2017-04-10', '2017-04-10', 2100.32, null, 'DESPESA', 5, 4);
INSERT INTO financial_release (description, due_date, pay_date, amount, obs, type, person_fk, category_fk) values ('Instrumentos', '2017-06-10', null, 1040.32, null, 'DESPESA', 4, 3);
INSERT INTO financial_release (description, due_date, pay_date, amount, obs, type, person_fk, category_fk) values ('Café', '2017-04-10', '2017-04-10', 4.32, null, 'DESPESA', 4, 2);
INSERT INTO financial_release (description, due_date, pay_date, amount, obs, type, person_fk, category_fk) values ('Lanche', '2017-06-10', null, 10.20, null, 'DESPESA', 4, 1);