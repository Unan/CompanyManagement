INSERT INTO department (name, foundation, parent_department_name)
VALUES ('rootDep', '20100618', null);
INSERT INTO department (name, foundation, parent_department_name)
VALUES ('secFloorDep1', '20110618', 'rootDep');
INSERT INTO department (name, foundation, parent_department_name)
VALUES ('thirdFloorDep1', '20120618', 'secFloorDep1');
INSERT INTO department (name, foundation, parent_department_name)
VALUES ('thirdFloorDep2', '20130618', 'secFloorDep1');
INSERT INTO department (name, foundation, parent_department_name)
VALUES ('thirdFloorDep3', '20140618', 'secFloorDep1');


INSERT INTO employee (email, first_name, last_name, middle_name, gender, birth_date, phone_number, hire_date, fire_date, position, wage, department_name, department_head)
VALUES ('emp1@mail.ru', 'John', 'Malkovich', 'Gavin', 'MALE', '19531209', '+11234567889', '20100618', null, 'PROJECT_MANAGER', 100000, 'rootDep', true);

INSERT INTO employee (email, first_name, last_name, middle_name, gender, birth_date, phone_number, hire_date, fire_date, position, wage, department_name, department_head)
VALUES ('emp2@mail.ru', 'Will', 'Smith', null, 'MALE', '19531209', '+11234567889', '20100618', null, 'PROJECT_MANAGER', 100000, 'rootDep', false);

INSERT INTO employee (email, first_name, last_name, middle_name, gender, birth_date, phone_number, hire_date, fire_date, position, wage, department_name, department_head)
VALUES ('emp3@mail.ru', 'Emil', 'Blonsky', null, 'MALE', '19531209', '+11234567889', '20100618', null, 'JAVA_DEVELOPER', 50000, 'secFloorDep1', true);

INSERT INTO employee (email, first_name, last_name, middle_name, gender, birth_date, phone_number, hire_date, fire_date, position, wage, department_name, department_head)
VALUES ('emp4@mail.ru', 'Alex', 'Mercer', null, 'MALE', '19531209', '+11234567889', '20100618', null, 'QA_MANAGER', 50000, 'secFloorDep1', false);

INSERT INTO employee (email, first_name, last_name, middle_name, gender, birth_date, phone_number, hire_date, fire_date, position, wage, department_name, department_head)
VALUES ('emp5@mail.ru', 'Konstantin', 'Kirrilov', null, 'MALE', '19531209', '+11234567889', '20100618', null, 'DEVOPS_DEVELOPER', 50000, 'thirdFloorDep2', true);

INSERT INTO employee (email, first_name, last_name, middle_name, gender, birth_date, phone_number, hire_date, fire_date, position, wage, department_name, department_head)
VALUES ('emp6@mail.ru', 'Natasha', 'Gromova', null, 'FEMALE', '19531209', '+11234567889', '20100618', null, 'DEVOPS_DEVELOPER', 50000, 'thirdFloorDep2', false);

INSERT INTO employee (email, first_name, last_name, middle_name, gender, birth_date, phone_number, hire_date, fire_date, position, wage, department_name, department_head)
VALUES ('emp7@mail.ru', 'Alexey', 'Shevtsov', null, 'MALE', '19531209', '+11234567889', '20100618', null, 'UI_DEVELOPER', 50000, 'thirdFloorDep3', true);

INSERT INTO employee (email, first_name, last_name, middle_name, gender, birth_date, phone_number, hire_date, fire_date, position, wage, department_name, department_head)
VALUES ('emp8@mail.ru', 'Andrey', 'Sokolov', null, 'MALE', '19531209', '+11234567889', '20100618', null, 'UI_DEVELOPER', 50000, 'thirdFloorDep3', false);


INSERT INTO department_employees (department_name, employees_email)
VALUES ('rootDep', 'emp1@mail.ru');
INSERT INTO department_employees (department_name, employees_email)
VALUES ('rootDep', 'emp2@mail.ru');
INSERT INTO department_employees (department_name, employees_email)
VALUES ('secFloorDep1', 'emp3@mail.ru');
INSERT INTO department_employees (department_name, employees_email)
VALUES ('secFloorDep1', 'emp4@mail.ru');
INSERT INTO department_employees (department_name, employees_email)
VALUES ('thirdFloorDep2', 'emp5@mail.ru');
INSERT INTO department_employees (department_name, employees_email)
VALUES ('thirdFloorDep2', 'emp6@mail.ru');
INSERT INTO department_employees (department_name, employees_email)
VALUES ('thirdFloorDep3', 'emp7@mail.ru');
INSERT INTO department_employees (department_name, employees_email)
VALUES ('thirdFloorDep3', 'emp8@mail.ru')