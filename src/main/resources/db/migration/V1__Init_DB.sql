
    create table announcement (
       id  bigserial not null,
        description TEXT not null,
        heading varchar(255) not null,
        numberOfParticipants smallint default 0,
        occupied boolean default false,
        rating float4 not null check (rating<=5 AND rating>=0),
        date_time_from int8,
        date_time_to int8,
        type varchar(255) not null,
        author_id varchar(255),
        primary key (id)
    );

    create table lesson (
       id  bigserial not null,
        description TEXT not null,
        name varchar(255) not null,
        number int2 not null check (number>=1),
        module_id int8,
        primary key (id)
    );

    create table module (
       id  bigserial not null,
        name varchar(255) not null,
        number int2 not null check (number>=1),
        announcement_id int8,
        primary key (id)
    );

    create table organization (
       id  bigserial not null,
        name varchar(31) not null,
        password varchar(31) not null,
        primary key (id)
    );

    create table organization_announcement (
       Organization_id int8 not null,
        announcements_id int8 not null
    );

    create table review (
       id  bigserial not null,
        comment varchar(255),
        evaluation int2 not null check (evaluation<=5 AND evaluation>=1),
        announcement_id int8,
        user_id varchar(255),
        primary key (id)
    );

    create table timetable (
       id  bigserial not null,
        user_id varchar(255) not null,
        primary key (id)
    );

    create table timetable_announcement (
       Timetable_id int8 not null,
        announcements_id int8 not null
    );

    create table user_roles (
       user_id varchar(255) not null,
        authorities varchar(255)
    );

    create table users (
       id varchar(255) not null,
        email varchar(255),
        enabled boolean not null,
        gender varchar(255),
        lastVisit timestamp,
        locale varchar(255),
        username varchar(31) not null,
        userpic varchar(255),
        organization_id int8,
        primary key (id)
    );