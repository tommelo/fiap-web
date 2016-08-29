drop database if exists portal_aluno;
create database portal_aluno;
use portal_aluno;

create table schools
(
	id int auto_increment,
    name varchar(45) not null,
    
    updated_at timestamp not null default current_timestamp on update current_timestamp,
    created_at timestamp not null default '0000-00-00 00:00:00',
    
    constraint pk_schools primary key(id)
);

insert into schools (name, updated_at, created_at) values ('Fiap - Lins II', now(), now());

create table courses
(
	id int auto_increment,    
    school_id int not null,
    name varchar(45) not null,
    
    updated_at timestamp not null default current_timestamp on update current_timestamp,
    created_at timestamp not null default '0000-00-00 00:00:00',
    
    constraint pk_courses primary key(id),
    constraint fk_courses_schools foreign key(school_id) references schools(id)
);

insert into courses (school_id, name, updated_at, created_at) values (1, 'Desenvolvimento Java - SOA e IoT', now(), now());

create table users
(
	id int auto_increment,
    course_id int not null,
    rm varchar(7) not null,
    password varchar(145) not null,
    first_name varchar(45) not null,
    last_name varchar(45) not null,
    email varchar(254) not null,
    role varchar(10) not null,
    updated_at timestamp not null default current_timestamp on update current_timestamp,
    created_at timestamp not null default '0000-00-00 00:00:00',
    
    constraint pk_users primary key(id),
    constraint fk_users_courses foreign key(course_id) references courses(id)
);

insert into users (course_id, rm, password, first_name, last_name, email, role, updated_at, created_at)
values (1, 'AD90122', '52e5b152b0130188a2c94c8ed54e9e7d', 'Admin', 'Fiap', 'admin@fiap.com.br', 'ADMIN', now(), now());

insert into users (course_id, rm, password, first_name, last_name, email, role, updated_at, created_at)
values (1, 'PR30201', '52e5b152b0130188a2c94c8ed54e9e7d', 'Emílio', 'Souza', 'emilio@fiap.com.br', 'PROFESSOR', now(), now());

insert into users (course_id, rm, password, first_name, last_name, email, role, updated_at, created_at)
values (1, 'RM30655', '52e5b152b0130188a2c94c8ed54e9e7d', 'Neilton', 'Bordan', 'mail2.tommelo@gmail.com', 'ALUNO', now(), now());

insert into users (course_id, rm, password, first_name, last_name, email, role, updated_at, created_at)
values (1, 'RM40537', '52e5b152b0130188a2c94c8ed54e9e7d', 'Tabata', 'Leite', 'tabata@gmail.com', 'ALUNO', now(), now());

insert into users (course_id, rm, password, first_name, last_name, email, role, updated_at, created_at)
values (1, 'RM50599', '52e5b152b0130188a2c94c8ed54e9e7d', 'Vagner', 'Panarello', 'vagner@gmail.com', 'ALUNO', now(), now());

create table disciplines
(
	id int auto_increment,
    course_id int not null,
    user_id int not null,
    name varchar(45) not null,
    
    updated_at timestamp not null default current_timestamp on update current_timestamp,
    created_at timestamp not null default '0000-00-00 00:00:00',
    
    constraint pk_disciplines primary key(id),
    constraint fk_disciplines_courses foreign key(course_id) references courses(id),
    constraint fk_disciplines_users foreign key(user_id) references users(id)
);

insert into disciplines (course_id, user_id, name, updated_at, created_at) values (1, 2, 'Java na Web', now(), now());

create table scores
(
	id int auto_increment,
    user_id int not null,
    discipline_id int not null,
    project_one_score int null,
    project_two_score int null,
    practical_activity_score int null,
    status varchar(45) not null default 'PENDENTE',
    updated_at timestamp not null default current_timestamp on update current_timestamp,
    created_at timestamp not null default '0000-00-00 00:00:00',
    
    constraint pk_scores primary key(id),
    constraint fk_scores_users foreign key(user_id) references users(id),
    constraint fk_scores_disciplines foreign key(discipline_id) references disciplines(id)
);

