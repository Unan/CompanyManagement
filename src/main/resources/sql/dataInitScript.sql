INSERT INTO department (id, name, foundation, parent_department_id)
    VALUES (1, 'Корень-А', '20100618', null);
INSERT INTO department (id, name, foundation, parent_department_id)
    VALUES (2, 'ВторойУровень-А', '20110618', 1);
INSERT INTO department (id, name, foundation, parent_department_id)
    VALUES (3, 'ТретийУровень-А', '20120618', 2);
INSERT INTO department (id, name, foundation, parent_department_id)
    VALUES (4, 'ТретийУровень-Б', '20130618', 2);
INSERT INTO department (id, name, foundation, parent_department_id)
    VALUES (5, 'ТретийУровень-В', '20140618', 2);


INSERT INTO employee (id, email, first_name, last_name, middle_name, gender, birth_date, phone_number, hire_date,
                      fire_date, position, wage, department_id, department_head)
VALUES (1, 'emp1@mail.ru', 'Джон', 'Малкович', 'Гэвин', 'MALE', '19531209', '+11234567801', '20100618', null,
        'PROJECT_MANAGER', 100000, 1, true);
INSERT INTO employee (id, email, first_name, last_name, middle_name, gender, birth_date, phone_number, hire_date,
                      fire_date, position, wage, department_id, department_head)
VALUES (2, 'emp2@mail.ru', 'Уилл', 'Смит', null, 'MALE', '19531209', '+11234567802', '20100618', null,
        'PROJECT_MANAGER', 100000, 1, false);
INSERT INTO employee (id, email, first_name, last_name, middle_name, gender, birth_date, phone_number, hire_date,
                      fire_date, position, wage, department_id, department_head)
VALUES (3, 'emp3@mail.ru', 'Эмиль', 'Блонский', null, 'MALE', '19531209', '+11234567803', '20100618', null,
        'JAVA_DEVELOPER', 50000, 2, true);
INSERT INTO employee (id, email, first_name, last_name, middle_name, gender, birth_date, phone_number, hire_date,
                      fire_date, position, wage, department_id, department_head)
VALUES (4, 'emp4@mail.ru', 'Алекс', 'Мерсер', null, 'MALE', '19531209', '+11234567804', '20100618', null, 'QA_MANAGER',
        50000, 2, false);
INSERT INTO employee (id, email, first_name, last_name, middle_name, gender, birth_date, phone_number, hire_date,
                      fire_date, position, wage, department_id, department_head)
VALUES (5, 'emp5@mail.ru', 'Константин', 'Кириллов', null, 'MALE', '19531209', '+11234567805', '20100618', null,
        'DEVOPS_DEVELOPER', 50000, 4, true);
INSERT INTO employee (id, email, first_name, last_name, middle_name, gender, birth_date, phone_number, hire_date,
                      fire_date, position, wage, department_id, department_head)
VALUES (6, 'emp6@mail.ru', 'Наталия', 'Громова', null, 'FEMALE', '19531209', '+11234567806', '20100618', null,
        'DEVOPS_DEVELOPER', 50000, 4, false);
INSERT INTO employee (id, email, first_name, last_name, middle_name, gender, birth_date, phone_number, hire_date,
                      fire_date, position, wage, department_id, department_head)
VALUES (7, 'emp7@mail.ru', 'Алексей', 'Шевцов', null, 'MALE', '19531209', '+11234567807', '20100618', null,
        'UI_DEVELOPER', 50000, 5, true);
INSERT INTO employee (id, email, first_name, last_name, middle_name, gender, birth_date, phone_number, hire_date,
                      fire_date, position, wage, department_id, department_head)
VALUES (8, 'emp8@mail.ru', 'Андрей', 'Соколов', null, 'MALE', '19531209', '+11234567808', '20100618', null,
        'UI_DEVELOPER', 50000, 5, false);


INSERT INTO department_employees (department_id, employees_id)
    VALUES (1, 1);
INSERT INTO department_employees (department_id, employees_id)
    VALUES (1, 2);
INSERT INTO department_employees (department_id, employees_id)
    VALUES (2, 3);
INSERT INTO department_employees (department_id, employees_id)
    VALUES (2, 4);
INSERT INTO department_employees (department_id, employees_id)
    VALUES (4, 5);
INSERT INTO department_employees (department_id, employees_id)
    VALUES (4, 6);
INSERT INTO department_employees (department_id, employees_id)
    VALUES (5, 7);
INSERT INTO department_employees (department_id, employees_id)
    VALUES (5, 8)