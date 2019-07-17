create table department
(
    name                   varchar(50) not null,
    foundation             date   not null,
    head_email             varchar(50),
    parent_department_name varchar(50),
    primary key (name)
);
create table department_employees
(
    department_name varchar(50) not null,
    employees_email varchar(50)
);
create table department_sub_departments
(
    department_name      varchar(50) not null,
    sub_departments_name varchar(50)
);
create table employee
(
    email           varchar(50) not null,
    first_name      varchar(50) not null,
    last_name       varchar(50) not null,
    middle_name     varchar(50),
    gender          varchar(50) not null,
    birth_date      date        not null,
    phone_number    varchar(50) not null,
    hire_date       date        not null,
    fire_date       date,
    position        varchar(50),
    wage            int         not null,
    department_name varchar(50) not null,
    department_head boolean     not null,
    primary key (email)
);

alter table department_employees
    add constraint UK_department_employees_employees_email
        unique (employees_email);

alter table department_sub_departments
    add constraint UK_department_sub_departments_sub_departments_name
        unique (sub_departments_name);

alter table department
    add constraint FK_department_head_email
        foreign key (head_email) references employee;

alter table department
    add constraint FK_department_parent_department_name
        foreign key (parent_department_name) references department;

alter table department_employees
    add constraint FK_department_employees_employees_email
        foreign key (employees_email) references employee;

alter table department_employees
    add constraint FK_department_employees_department_name
        foreign key (department_name) references department;

alter table department_sub_departments
    add constraint FK_department_sub_departments_sub_departments_name
        foreign key (sub_departments_name) references department;

alter table department_sub_departments
    add constraint FK_department_sub_departments_department_name
        foreign key (department_name) references department;

alter table employee
    add constraint FK_employee_department_name
        foreign key (department_name) references department;
