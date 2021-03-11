UPDATE financial_release
SET type  = 'INCOME'
WHERE type = 'RECEITA';

UPDATE financial_release
SET type  = 'EXPENSES'
WHERE type = 'DESPESA';