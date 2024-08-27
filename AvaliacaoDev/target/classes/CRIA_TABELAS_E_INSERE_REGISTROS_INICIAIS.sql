CREATE TABLE exame (rowid bigint auto_increment, nm_exame VARCHAR(255));
CREATE TABLE funcionario (rowid bigint auto_increment, nm_funcionario VARCHAR(255));
CREATE TABLE exameRealizado (rowid bigint auto_increment, dt_resultado VARCHAR(255), codigo_exame bigint, Nm_exame VARCHAR(255), nm_funcionario VARCHAR(255), cod_funcionario BIGINT);
INSERT INTO exame (nm_exame) VALUES ('Acuidade Visual'), ('Urina'), ('Clinico'), ('Sangue');
INSERT INTO funcionario (nm_funcionario) VALUES ('AA'), ('BB'), ('CC'), ('DD');