alter table if exists announcement
       add constraint UK_unique_announcement_heading unique (heading);

    alter table if exists organization
       add constraint UK_unique_organization_name unique (name);

    alter table if exists organization_announcement
       add constraint UK_unique_announcements_id unique (announcements_id);

    alter table if exists timetable_announcement
       add constraint UK_unique_timetable_announcement_id unique (announcements_id);

    alter table if exists announcement
       add constraint FK_user_announcements
       foreign key (author_id)
       references users;

    alter table if exists lesson
       add constraint FK_module_lessons
       foreign key (module_id)
       references module;

    alter table if exists module
       add constraint FK_announcement_modules
       foreign key (announcement_id)
       references announcement;

    alter table if exists organization_announcement
       add constraint FK_organization_announcement_to_announcement
       foreign key (announcements_id)
       references announcement;

    alter table if exists organization_announcement
       add constraint FK_organization_announcement_to_organization
       foreign key (Organization_id)
       references organization;

    alter table if exists review
       add constraint FK_announcement_reviews
       foreign key (announcement_id)
       references announcement;

    alter table if exists review
       add constraint FK_user_reviews
       foreign key (user_id)
       references users;

    alter table if exists timetable
       add constraint FK_user_timetables
       foreign key (user_id)
       references users;

    alter table if exists timetable_announcement
       add constraint FK_timetable_announcement_to_announcement
       foreign key (announcements_id)
       references announcement;

    alter table if exists timetable_announcement
       add constraint FKtimetable_announcement_to_timetable
       foreign key (Timetable_id)
       references timetable;

    alter table if exists user_roles
       add constraint FK_user_roles
       foreign key (user_id)
       references users;

    alter table if exists users
       add constraint FK_organization_users
       foreign key (organization_id)
       references organization;