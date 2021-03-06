-- MySQL Script generated by MySQL Workbench
-- Dom 22 Mai 2016 21:25:52 BRT
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema projetoTCC
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema projetoTCC
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `projetoTCC` DEFAULT CHARACTER SET utf8 ;
USE `projetoTCC` ;
-- -----------------------------------------------------
-- Table `projetoTCC`.`EMPREGADO`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `projetoTCC`.`EMPREGADO` (
  `CD_EMPREGADO` INT auto_increment NULL COMMENT 'Código para identificar o empregado',
  `NOME` VARCHAR(50) NOT NULL COMMENT 'NOME DO EMPREGADO',
  `SOBRENOME` VARCHAR(30) NOT NULL COMMENT 'SOBRENOME DO EMPREGADO',
  `DATA_NASC` DATE NOT NULL COMMENT 'DATA NASCIMENTO DO EMPREGADO',
  `SEXO` CHAR NOT NULL COMMENT 'SEXO F OU M DO EMPREGADO',
  `CPF` VARCHAR(14) NULL COMMENT 'CPF DO EMPREGADO',
  `RG` VARCHAR(12) NOT NULL COMMENT 'RG DO EMPREGADO.',
  `UF_RG` VARCHAR(2) NOT NULL COMMENT 'UF DO RG DO EMPREGADO',
  `NUM_CARTEIRA` VARCHAR(20) NOT NULL COMMENT 'NUMERO DA CARTEIRA DO EMPREGADO',
  `SERIE_CARTEIRA` VARCHAR(20) NOT NULL COMMENT 'SERIE DA CARTEIRA DO EMPREGADO',
  `UF_CARTEIRA` VARCHAR(2) NOT NULL COMMENT 'UF DA CARTEIRA DO EMPREGADO',
  `FOTO` VARCHAR(100) COMMENT 'NOME DA FOTO DO EMPREGADO',
  `ATIVO` CHAR NOT NULL COMMENT 'ATIVO S OU N, SE O EMPREGADO ESTÁ ATIVO OU NÃO NO SISTEMA',
  `E_MAIL` VARCHAR(100) NOT NULL COMMENT 'E-MAIL DO EMPREGADO PARA EFETUAR LOGIN',
  `SENHA` VARCHAR(20) NOT NULL COMMENT 'SENHA DO EMPREGADO',
  `NUM_DEPENDENTES` INT NOT NULL COMMENT 'NÚMERO DE DEPENDENTES DO EMPREGADOR',
  `ESTADO_CIVIL` VARCHAR(20) NOT NULL COMMENT 'ESTADO CIVIL DO EMPREGADO',
  `FK_EMPREGADOR` INT NOT NULL COMMENT 'VINCULAÇAO COM A TABELA EMPREGADOR',
  PRIMARY KEY (`CD_EMPREGADO`, `FK_EMPREGADOR`),
  INDEX `fk_EMPREGADO_EMPREGADOR1_idx` (`FK_EMPREGADOR` ASC),
  CONSTRAINT `fk_EMPREGADO_EMPREGADOR1`
    FOREIGN KEY (`FK_EMPREGADOR`)
    REFERENCES `projetoTCC`.`USUARIO` (`CD_USUARIO`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `projetoTCC`.`FERIAS`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `projetoTCC`.`FERIAS` (
  `CD_FERIAS` INT auto_increment NOT NULL COMMENT 'CODIGO PARA IDENTIFICAR FERIAS',
  `DATA_INICIO` DATE NOT NULL COMMENT 'DATA INICIAL DAS FERIAS',
  `DATA_FIM` DATE NOT NULL COMMENT 'DATA FINAL DAS FÉRIAS',
  `VENDA_FERIAS` CHAR NOT NULL COMMENT 'EMPREGADOR TEM INTERESSE DE VENDER AS FERIAS \'S\' OU \'N\'',
  `PERIODO_AQUISITIVO_INICIO` varchar(20) NOT NULL COMMENT 'REFERENCIA DE QUAL PERIODO TRABALHADO SE REFERE ESTAS FERIAS',
  `PERIODO_AQUISITIVO_FIM` varchar(20) NOT NULL COMMENT 'REFERENCIA DE QUAL PERIODO TRABALHADO SE REFERE ESTAS FERIAS',
  `SITUACAO` varchar(20) NULL COMMENT 'INFORMA SE O EMPREGADO POSSUI FERIAS CONCLUIDAS E PENDENTES',
  `VALOR` DECIMAL(10,2) NULL COMMENT 'VALOR DAS FÉRIAS',
  `DATA_PAGAMENTO` DATE NULL COMMENT 'DATA DE PAGAMENTO',
  `QTD_DIAS_FERIAS` INT NOT NULL COMMENT 'NÚMERO DE DIAS DE FÉRIAS',
  `FK_CONTRATO` INT NOT NULL COMMENT 'VINCULAÇAO COM A TABELA EMPREGADO',
  PRIMARY KEY (`CD_FERIAS`, `FK_CONTRATO`),
  INDEX `fk_FERIAS_CONTRATO1_idx` (`FK_CONTRATO` ASC),
  CONSTRAINT `fk_FERIAS_CONTRATO1`
    FOREIGN KEY (`FK_CONTRATO`)
    REFERENCES `projetoTCC`.`CONTRATO` (`CD_CONTRATO`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `projetoTCC`.`FOLHA_PAGAMENTO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `projetoTCC`.`FOLHA_PAGAMENTO` (
  `CD_FOLHA_PAGAMENTO` INT  auto_increment NOT NULL COMMENT 'CODIGO PAREA IDENTIFICAR FOLHA DE PAGAMENTO',
  `SALARIO_LIQUIDO` DECIMAL(10,2) NOT NULL COMMENT 'INFORMA SALARIO LIQUIDO DO EMPREGADO',
  `FGTS` DECIMAL(10,2) NULL COMMENT 'INFORMA O FUNDO DE GARANTIA DO EMPREGADO',
  `INSS` DECIMAL(10,2) NULL COMMENT 'INFORMA O DESCONTO DO INSS',
  `IR_RETIDO` DECIMAL(10,2) NULL COMMENT 'INFORMA O VALOR DESCONTADO DO IMPOSTO DE RENDA RETIDO',
  `VALE_TRANSPORTE` DECIMAL(10,2) NULL COMMENT 'INFORMA O VALOR DESCONTADO DO VALE TRANSPORTE',
  `TOTAL_PAGAR_HORA_EXTRA` DECIMAL(10,2) NULL COMMENT 'INFORMA O VALOR DAS HORAS EXTRAS',
  `BENEFICIOS` DECIMAL(10,2) NULL COMMENT 'INFORMA O VALOR DESCONTADO DOS BENEFÍCIOS',
  `MES_REFERENCIA` VARCHAR(20) NOT NULL COMMENT 'INFORMA O MES QUE REFERE O PAGAMENTO',
  `ANO_REFERENCIA` VARCHAR(20) NOT NULL COMMENT 'INFORMA O ANO QUE REFERE O PAGAMENTO',
  `FK_CONTRATO` INT NOT NULL COMMENT 'VINCULAÇAO COM A TABELA CONTRATO',
  PRIMARY KEY (`CD_FOLHA_PAGAMENTO`, `FK_CONTRATO`),
  INDEX `fk_FOLHA_PAGAMENTO_CONTRATO1_idx` (`FK_CONTRATO` ASC),
  CONSTRAINT `fk_FOLHA_PAGAMENTO_CONTRATO1`
    FOREIGN KEY (`FK_CONTRATO`)
    REFERENCES `projetoTCC`.`CONTRATO` (`CD_CONTRATO`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `projetoTCC`.`DECIMO_TERCEIRO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `projetoTCC`.`DECIMO_TERCEIRO` (
  `CD_DECIMO_TERCEIRO` INT  auto_increment NOT NULL COMMENT 'CODIGO PAREA IDENTIFICAR FOLHA DE PAGAMENTO',
  `DECIMO_TERCEIRO_1PARCELA` DECIMAL(10,2) NULL COMMENT 'INFORMA O VALOR DO DECIMO TERCEIRO DA PRIMEIRA PARCELA',
  `DECIMO_TERCEIRO_2PARCELA` DECIMAL(10,2) NULL COMMENT 'INFORMA VALOR DO DECIMO TERCEIRO SEGUNDA PARCELA',
  `DESCONTO_INSS_RECOLHER` DECIMAL(10,2) NOT NULL COMMENT 'INFORMA O DESCONTO DO INSS À RECOLHER',
  `FK_FOLHA_PAGAMENTO` INT NOT NULL COMMENT 'VINCULAÇAO COM A TABELA FOLHA PAGAMENTO',
  PRIMARY KEY (`CD_DECIMO_TERCEIRO`, `FK_FOLHA_PAGAMENTO`),
  INDEX `fk_FOLHA_PAGAMENTO_DECIMO_TERCEIRO1_idx` (`FK_FOLHA_PAGAMENTO` ASC),
  CONSTRAINT `fk_FOLHA_PAGAMENTO_DECIMO_TERCEIRO1`
    FOREIGN KEY (`FK_FOLHA_PAGAMENTO`)
    REFERENCES `projetoTCC`.`FOLHA_PAGAMENTO` (`CD_FOLHA_PAGAMENTO`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `projetoTCC`.`QR_CODE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `projetoTCC`.`QR_CODE` (
  `CD_QR_CODE` INT  auto_increment NOT NULL COMMENT 'CODIGO PARA IDENTIFICAR O QR CODE',
  `DATA` DATE NOT NULL COMMENT 'DATA QUE O QR CODE FOI GERADA',
  `CODIGO` VARCHAR(45) NOT NULL COMMENT 'LOCAL QUE A IMAGEM DO QR CODE ESTA ARMAZENADA',
  PRIMARY KEY (`CD_QR_CODE`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `projetoTCC`.`PONTO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `projetoTCC`.`PONTO` (
  `CD_PONTO` INT auto_increment NOT NULL COMMENT 'CODIGO PARA IDENTIFICAR O PONTO',
  `DATA` DATE NOT NULL COMMENT 'DATA QUE O EMPREGADO MARCOU PONTO',
  `HORA_ENTRADA` varchar(5) NOT NULL COMMENT 'INFORMA A HORA QUE O EMPREGADO MARCOU PONTO',
  `HORA_INICIO_INTERVALO` varchar(5) NULL COMMENT 'INFORMA O HORARIO QUE O EMPREGADO INICIOU O INTERVALO',
  `HORA_FIM_INTERVALO` varchar(5) NULL COMMENT 'INFORMA O HORARIO QUE O EMPREGADO TERMINOU O INTERVALO',
  `HORA_SAIDA` varchar(5) NULL COMMENT 'INFORMA A HORA DE SAIDA DO EMPREGADO',
  `ACAO` varchar(20) NULL COMMENT 'INFORMA A AÇÃO DO DIA (DIA COMUM, FALTA, FALTA JUSTIFICADA, FERIADO, FERIADO TRABALHADO)',
  `FK_EMPREGADO` INT NOT NULL COMMENT 'VINCULAÇAO COM A TABELA EMPREGADO',
  PRIMARY KEY (`CD_PONTO`, `FK_EMPREGADO`),
  INDEX `fk_PONTO_EMPREGADO1_idx` (`FK_EMPREGADO` ASC),
  CONSTRAINT `fk_PONTO_EMPREGADO1`
    FOREIGN KEY (`FK_EMPREGADO`)
    REFERENCES `projetoTCC`.`EMPREGADO` (`CD_EMPREGADO`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `projetoTCC`.`HORA_EXTRA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `projetoTCC`.`HORA_EXTRA` (
  `CD_HORA_EXTRA` INT auto_increment  NOT NULL COMMENT 'CODIGO PARA IDENTIFICAR A HORA EXTRA',
  `HORA_EXTRA` varchar(5) NULL COMMENT 'INFORMA A QUANTIDADE DE HORA EXTRA COMUM NO DIA',
  `HORA_EXTRA_NOTURNO` varchar(5) NULL COMMENT 'INFORMA A QUANTIDADE DE HORA EXTRA NOTURNA NO DIA',
  `FOLGA_COMPENSATORIA` BOOLEAN NULL COMMENT 'INFORMA SE TRABALHOU EM DIA DSR/FERIADO',
  `FK_PONTO` INT NOT NULL COMMENT 'VINCULAÇAO COM A TABELA PONTO',
  PRIMARY KEY (`CD_HORA_EXTRA`, `FK_PONTO`),
  INDEX `fk_PONTO_HORA_EXTRA1_idx` (`FK_PONTO` ASC),
  CONSTRAINT `fk_PONTO_HORA_EXTRA1`
    FOREIGN KEY (`FK_PONTO`)
    REFERENCES `projetoTCC`.`PONTO` (`CD_PONTO`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `projetoTCC`.`JORNADA_TRABALHO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `projetoTCC`.`JORNADA_TRABALHO` (
  `CD_JORNADA_TRABALHO` INT auto_increment NOT NULL COMMENT 'CODIGO PARA IDENTIFICAR',
  `HORA_ENTRADA` varchar(5) NULL COMMENT 'INFORMA A HORA DE ENTRADA DO EMPREGADO',
  `HORA_INICIO_INTERVALO` varchar(5) NULL COMMENT 'INFORMA O HORARIO QUE O EMPREGADO INICIA O INTERVALO',
  `HORA_FIM_INTERVALO` varchar(5) NULL COMMENT 'INFORMA O HORARIO QUE O EMPREGADO TERMINA O INTERVALO',
  `HORA_SAIDA` varchar(5) NULL COMMENT 'INFORMA A HORA DE SAIDA DO EMPREGADO',
  `DIA_SEMANA` INT NULL COMMENT 'INFORMA O DIA REFERENTE AOS HORÁRIOS',
  `DIA_FOLGA` BOOLEAN NULL COMMENT 'INFORMA SE O É UM DIA DE FOLGA PARA O EMRPEGADO',
  `DIA_MEIO_PERIODO`BOOLEAN NULL COMMENT 'INFORMA SE NO DIA O EMPREGADO TRABALHA MEIO PERIODO',
  `DIA_SEM_TRABALHO`BOOLEAN NULL COMMENT 'INFORMA SE O EMPREGADO NÃO FOI CONTRATADO PARA TRABALHAR NO DIA',
  `FK_CONTRATO` INT NOT NULL COMMENT 'VINCULAÇAO COM A TABELA CONTRATO',
  PRIMARY KEY (`CD_JORNADA_TRABALHO`, `FK_CONTRATO`),
  INDEX `fk_JORNADA_TRABALHO_CONTRATO1_idx` (`FK_CONTRATO` ASC),
  CONSTRAINT `fk_JORNADA_TRABALHO_CONTRATO1`
    FOREIGN KEY (`FK_CONTRATO`)
    REFERENCES `projetoTCC`.`CONTRATO` (`CD_CONTRATO`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `projetoTCC`.`ATIVIDADE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `projetoTCC`.`ATIVIDADE` (
  `CD_ATIVIDADE` INT auto_increment NOT NULL COMMENT 'CODIGO PARA IDENTIFICAR ATIVIDADE',
  `DATA` DATE NOT NULL COMMENT 'INFORMA DATA QUE FOI INSERIDA A ATIVIDADE',
  `TITULO` VARCHAR(45) NOT NULL COMMENT 'INFORMA O TITULO DA ATIVIDADE',
  `DESCRICAO` VARCHAR(300) NULL COMMENT 'INFORMA O QUE DEVE SER FEITO NESSA ATIVIDADE',
  `REALIZADO` CHAR NULL COMMENT 'INFORMA SE O EMPREGADO JA REALIZOU OU NÃO A ATIVIDADE',
  `FK_EMPREGADO` INT NOT NULL COMMENT 'VINCULAÇAO COM A TABELA EMPREGADO',
  PRIMARY KEY (`CD_ATIVIDADE`, `FK_EMPREGADO`),
  INDEX `fk_ATIVIDADE_EMPREGADO1_idx` (`FK_EMPREGADO` ASC),
  CONSTRAINT `fk_ATIVIDADE_EMPREGADO1`
    FOREIGN KEY (`FK_EMPREGADO`)
    REFERENCES `projetoTCC`.`EMPREGADO` (`CD_EMPREGADO`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `projetoTCC`.`CONTRATO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `projetoTCC`.`CONTRATO` (
  `CD_CONTRATO` INT auto_increment NOT NULL COMMENT 'CODIGO PARA IDENTIFICAR CONTRATO',
  `DATA_ADMISSAO` DATE NOT NULL COMMENT 'INFORMA DATA QUE O EMPREGADO FOI ADMITIDO',
  `CARGO` VARCHAR(50) NOT NULL COMMENT 'INFORMA QUAL O CARGO DO EMPREGADO',
  `SALARIO_BASE` DECIMAL(10,2) NOT NULL COMMENT 'INFORMA QUAL O SALARIO BASE DO EMPREGADO',
  `DIA_PAGAMENTO` VARCHAR(20) NOT NULL COMMENT 'INFORMA QUAL O DIA DO PAGAMENTO AO EMPREGADO',
  `VALE_TRANSPORTE_VALOR` DECIMAL(10,2) NOT NULL COMMENT 'INFORMA O VALOR DA PASSAGEM UNITARIA',
  `COMPENSACAO_DIAS` VARCHAR(45) NOT NULL COMMENT 'INFORMA A FORMA DE COMPENSAÇÃO DE DIAS TRABALHADOS',
  `REGIME_TRABALHO` VARCHAR(15) NOT NULL COMMENT 'INFORMA O TEMPO DE TRABALHO(INTEGRAL OU PARCIAL)',
  `DURACAO_SEMANAL` varchar(5) NULL COMMENT 'INFORMA A DURAÇÃO DO TRABALHO SEMANAL',
  `CONTA` VARCHAR(10) NULL COMMENT 'INFORMA A CONTA DO BANCO',
  `AGENCIA` VARCHAR(10) NULL COMMENT 'INFORMA A AGENCIA DO BANCO',
  `BANCO` VARCHAR(10) NULL COMMENT 'INFORMA O NOME DO BANCO',
  `TIPO_CONTA` VARCHAR(10) NULL COMMENT 'INFORMA O TIPO DE CONTA(CORRENTE OU POUPANÇA)',
  `DATA_DEMISSAO` DATE NULL COMMENT 'INFORMA A DATA DE DEMISSAO DO EMPREGADO',
  `VALOR_PAGAR_DEMISSAO` DECIMAL(10,2) NULL COMMENT 'INFORMA O VALOR A PAGAR DA DEMISSAO',
  `DESCONTO_BENEFICIOS` DECIMAL(10,2) NULL COMMENT 'INFORMA O VALOR A PAGAR DOS BENEFICIOS',
  `DESCRICAO_DEMISSAO` VARCHAR(45) NULL COMMENT 'INFORMA A DESCRIÇÃO DA DEMISSAO',
  `TIPO_DEMISSAO` VARCHAR(20) NULL COMMENT 'INFORMA O TIPO DE DEMISSAO',
  `ATIVO_DEMISSAO` VARCHAR(20) NULL COMMENT 'INFORMA SE O FUNCIONÁRIO FOI DEMITIDO',
  `FK_EMPREGADO` INT NOT NULL,
  `FK_USUARIO` INT NOT NULL,
  PRIMARY KEY (`CD_CONTRATO`, `FK_EMPREGADO`,`FK_USUARIO`),
  INDEX `fk_CONTRATO_EMPREGADO_idx` (`FK_EMPREGADO` ASC),
  INDEX `fk_CONTRATO_USUARIO_idx` (`FK_USUARIO` ASC),
  CONSTRAINT `fk_CONTRATO_EMPREGADO`
    FOREIGN KEY (`FK_EMPREGADO`)
    REFERENCES `projetoTCC`.`EMPREGADO` (`CD_EMPREGADO`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_CONTRATO_USUARIO`
    FOREIGN KEY (`FK_USUARIO`)
    REFERENCES `projetoTCC`.`USUARIO` (`CD_USUARIO`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `projetoTCC`.`USUARIO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `projetoTCC`.`USUARIO` (
  `CD_USUARIO` INT auto_increment NOT NULL COMMENT 'INFORMA O CODIGO DO EMPREGADOR',
  `NOME` VARCHAR(45) NOT NULL COMMENT 'INFORMA O NOME DO EMPREGADOR',
  `SOBRENOME` VARCHAR(45) NOT NULL COMMENT 'INFORMA O SOBRENOME DO EMPREGADOR',
  `CPF` VARCHAR(14) NULL COMMENT 'INFORMA O CPF DO EMPREGADOR',
  `CNPJ` VARCHAR(20) NULL COMMENT 'INFORMA O CNPJ DO EMPREGADOR',
  `RG` VARCHAR(12) NOT NULL COMMENT 'INFORMA O RG DO EMPREGADOR',
  `UF_RG` VARCHAR(2) NOT NULL COMMENT 'INFORMA O UF_RG DO EMPREGADOR',
  `ATIVO` CHAR NOT NULL COMMENT 'INFORMA SE EMPREGADOR ESTA ATIVO NO SISTEMA',
  `E_MAIL` VARCHAR(45) NOT NULL COMMENT 'INFORMA O E-MAIL DO EMPREGADOR',
  `SENHA` VARCHAR(45) NOT NULL COMMENT 'INFORMA A SENHA DO EMPREGADOR',
  PRIMARY KEY (`CD_USUARIO`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `projetoTCC`.`USUARIO_EMPREGADO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `projetoTCC`.`USUARIO_EMPREGADO` (
  `FK_USUARIO` INT  auto_increment NOT NULL COMMENT 'VINCULAÇÃO COM A TABELA USUARIO',
  `FK_EMPREGADO` INT NOT NULL COMMENT 'VINCULAÇÃO COM A TABELA EMPREGADO',
  PRIMARY KEY (`FK_USUARIO`, `FK_EMPREGADO`),
  INDEX `fk_USUARIO_EMPREGADO_2_idx` (`FK_EMPREGADO` ASC),
  CONSTRAINT `fk_USUARIO_EMPREGADO_1`
    FOREIGN KEY (`FK_USUARIO`)
    REFERENCES `projetoTCC`.`USUARIO` (`CD_USUARIO`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_USUARIO_EMPREGADO_2`
    FOREIGN KEY (`FK_EMPREGADO`)
    REFERENCES `projetoTCC`.`EMPREGADO` (`CD_EMPREGADO`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `projetoTCC`.`CONTATO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `projetoTCC`.`CONTATO` (
  `CD_CONTATO` INT auto_increment PRIMARY KEY NOT NULL COMMENT 'INFORMA O CODIGO DE CONTATO',
  `TIPO_CONTATO` VARCHAR(15) NULL COMMENT 'INFORMA O TIPO DE CONTATOSE É POR RESIDENCIA,COMERCIAL,ETC.',
  `NUMERO` VARCHAR(20) NULL COMMENT 'INFORMA O NUMERO DE TELEFONE DO EMPREGADO OU EMPREGADOR',
  `FK_USUARIO` INT NULL COMMENT 'VINCULAÇÃO COM A TABELA USUARIO',
  `FK_EMPREGADO` INT NULL COMMENT 'VINCULAÇÃO COM A TABELA EMPREGADO',
  INDEX `fk_CONTATO_1_idx` (`FK_USUARIO` ASC),
  INDEX `fk_CONTATO_2_idx` (`FK_EMPREGADO` ASC),
  CONSTRAINT `fk_CONTATO_1`
    FOREIGN KEY (`FK_USUARIO`)
    REFERENCES `projetoTCC`.`USUARIO` (`CD_USUARIO`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_CONTATO_2`
    FOREIGN KEY (`FK_EMPREGADO`)
    REFERENCES `projetoTCC`.`EMPREGADO` (`CD_EMPREGADO`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `projetoTCC`.`ENDERECO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `projetoTCC`.`ENDERECO` (
  `CD_ENDERECO` INT PRIMARY KEY auto_increment NOT NULL COMMENT 'INFORMA O CODIGO DO ENDERECO',
  `LOGRADOURO` VARCHAR(45) NOT NULL COMMENT 'INFORMA O LOGRADOURO DO EMPREGADO OU EMPREGADOR',
  `CIDADE` VARCHAR(45) NOT NULL COMMENT 'INFORMA A CIDADE DO EMPREGADO OU EMPREGADOR',
  `ESTADO` VARCHAR(45) NOT NULL,
  `NUMERO` INT NOT NULL,
  `COMPLEMENTO` VARCHAR(45) NULL,
  `CEP` VARCHAR(45) NOT NULL,
  `BAIRRO` VARCHAR(45) NOT NULL,
  `FK_USUARIO` INT NULL,
  `FK_EMPREGADO` INT NULL,
  INDEX `fk_ENDERECO_1_idx` (`FK_USUARIO` ASC),
  INDEX `fk_ENDERECO_2_idx` (`FK_EMPREGADO` ASC),
  CONSTRAINT `fk_ENDERECO_1`
    FOREIGN KEY (`FK_USUARIO`)
    REFERENCES `projetoTCC`.`USUARIO` (`CD_USUARIO`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ENDERECO_2`
    FOREIGN KEY (`FK_EMPREGADO`)
    REFERENCES `projetoTCC`.`EMPREGADO` (`CD_EMPREGADO`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `projetoTCC`.`PARAMETRO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `projetoTCC`.`PARAMETRO` (
  `CD_PARAMETRO` INT auto_increment NOT NULL,
  `DESCRICAO` VARCHAR(45) NOT NULL,
  `VALOR` DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (`CD_PARAMETRO`, `DESCRICAO`, `VALOR`))
ENGINE = InnoDB;


-- SET SQL_MODE=@OLD_SQL_MODE;
-- SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
-- SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- SELECT * FROM PONTO;
-- SELECT * FROM HORA_EXTRA;
-- SELECT * FROM USUARIO;
-- SELECT * FROM CONTRATO;
-- SELECT * FROM CONTATO;
-- SELECT * FROM ENDERECO;
-- SELECT* FROM EMPREGADO;
-- UPDATE CONTATO SET TIPO_CONTATO = 'celular' WHERE FK_USUARIO = 1;

-- SELECT MAX(CD_USUARIO) FROM USUARIO;
-- SELECT * FROM USUARIO WHERE E_MAIL = "bianca@" AND SENHA = "123";

-- select b.nome, b.cd_empregado from usuario a inner join contrato c on a.cd_usuario = c.fk_usuario
--  inner join empregado b on c.fk_empregado = b.cd_empregado WHERE b.ATIVO = 0;

-- SELECT * FROM EMPREGADO;

-- SELECT * FROM CONTRATO WHERE CD_CONTRATO = 25;


-- USE projetotcc;
-- SELECT * FROM jornada_trabalho WHERE FK_CONTRATO = 30;
-- SELECT * FROM JORNADA_TRABALHO;
-- SELECT * FROM CONTATO WHERE FK_EMPREGADO = 26;

-- select b.cd_empregado, b.nome, b.cd_empregado, b.foto from usuario a inner join contrato c on a.cd_usuario = c.fk_usuario
-- inner join empregado b on c.fk_empregado = b.cd_empregado WHERE b.ATIVO = 0 and b.fk_empregador = 1;

-- UPDATE EMPREGADO SET foto = "z" WHERE CD_EMPREGADO = 1;
-- SELECT MAX(CD_EMPREGADO) FROM EMPREGADO;

I-- NSERT INTO `projetotcc`.`folha_pagamento` (`CD_FOLHA_PAGAMENTO`, `SALARIO_LIQUIDO`, `FGTS`, `INSS`, `IR_RETIDO`, `VALE_TRANSPORTE`, `TOTAL_PAGAR_HORA_EXTRA`, `BENEFICIOS`, `MES_REFERENCIA`, `ANO_REFERENCIA`, `FK_CONTRATO`) VALUES (1, 2.2, 1.1, 2.2, 1.2, 2.2, 2.2, 2.2, '2', '2016', 1)
-- INSERT INTO `projetotcc`.`decimo_terceiro` (`CD_DECIMO_TERCEIRO`, `DECIMO_TERCEIRO_1PARCELA`, `DECIMO_TERCEIRO_2PARCELA`, `DESCONTO_INSS_RECOLHER`, `FK_FOLHA_PAGAMENTO`) VALUES (1, 2.2, 2.0, 2.0, '1');
-- INSERT INTO `projetotcc`.`decimo_terceiro` (`CD_DECIMO_TERCEIRO`, `DECIMO_TERCEIRO_1PARCELA`, `DECIMO_TERCEIRO_2PARCELA`, `DESCONTO_INSS_RECOLHER`, `FK_FOLHA_PAGAMENTO`) VALUES (2, 2.0, 2.0, 2.0, '1')

-- -----------------------------------------------------
-- ALTER TABLE tablename AUTO_INCREMENT = 1 --Para zerar a contagem do id.
-- -----------------------------------------------------

