create sequence hibernate_sequence start 1 increment 1;
create table department
(
    id                   int4         not null,
    foundation           timestamp    not null,
    name                 varchar(255) not null,
    parent_department_id int4,
    primary key (id)
);
create table department_employees
(
    department_id int4 not null,
    employees_id  int4 not null
);
create table department_wage_info
(
    id              int4 not null,
    department_name varchar(255),
    wage_amount     int4,
    primary key (id)
);
create table employee
(
    id              int4         not null,
    birth_date      timestamp    not null,
    department_head boolean      not null,
    email           varchar(255) not null,
    fire_date       timestamp,
    first_name      varchar(255) not null,
    gender          varchar(255) not null,
    hire_date       timestamp    not null,
    last_name       varchar(255) not null,
    middle_name     varchar(255),
    phone_number    varchar(255) not null,
    position        varchar(255) not null,
    wage            int4         not null,
    department_id   int4,
    primary key (id)
);
alter table department
    add constraint UK_1t68827l97cwyxo9r1u6t4p7d unique (name);
alter table department_employees
    add constraint UK_glhfs8s9v6j1up1hmnu5u1roo unique (employees_id);
alter table employee
    add constraint UK_fopic1oh5oln2khj8eat6ino0 unique (email);
alter table employee
    add constraint UK_if2qx9bhvigig71puh5sow65g unique (phone_number);
alter table department
    add constraint FKbckfslosx7o8kr7bonjq3qaey foreign key (parent_department_id) references department;
alter table department_employees
    add constraint FKnoa5axqogt38rt5mr2q5sabme foreign key (employees_id) references employee;
alter table department_employees
    add constraint FKb66y4tjmeyj55r61lv12ms3kw foreign key (department_id) references department;
alter table employee
    add constraint FKbejtwvg9bxus2mffsm3swj3u9 foreign key (department_id) references department;
