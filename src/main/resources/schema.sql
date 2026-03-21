create table if not exists instructors (
    instructor_id serial primary key,
    instructor_name varchar(100) not null,
    email varchar(100)
);

create table if not exists courses (
    course_id serial primary key,
    course_name varchar(100) not null,
    description text,
    instructor_id int not null,
    constraint fk_course_instructor foreign key (instructor_id) references instructors (instructor_id) on delete set null on update cascade
);

create table if not exists students (
    student_id serial primary key,
    student_name varchar(100) not null,
    email varchar(100),
    phone_number varchar(20)
);

create table if not exists student_course (
    student_id int not null,
    course_id int not null,
    primary key (student_id, course_id),
    constraint fk_student foreign key (student_id) references students (student_id) on delete cascade on update cascade,
    constraint fk_course foreign key (course_id) references courses (course_id) on delete cascade on update cascade
);