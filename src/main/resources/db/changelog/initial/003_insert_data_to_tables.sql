insert into users(email, password, enabled)
values
    ('izlax6002@gmail.com', '$2a$10$2a.SIAPUpKTp5Cz6OA5VOOY.mtPQCzy2GaCYhf6lxchUmWSIWIl7m', true), --password: qwe
    ('izlax1@gmail.com', '$2a$10$2a.SIAPUpKTp5Cz6OA5VOOY.mtPQCzy2GaCYhf6lxchUmWSIWIl7m', true),--password: qwe
    ('izlax2@gmail.com', '$2a$10$2a.SIAPUpKTp5Cz6OA5VOOY.mtPQCzy2GaCYhf6lxchUmWSIWIl7m', true),--password: qwe
    ('izlax3@gmail.com', '$2a$10$2a.SIAPUpKTp5Cz6OA5VOOY.mtPQCzy2GaCYhf6lxchUmWSIWIl7m', true);--password: qwe

insert into category(name)
values
    ('IMAGE'),
    ('VIDEO'),
    ('DOCUMENT');

insert into authority(authority)
values
    ('FULL'),
    ('READ_POSTS'),
    ('WRITE_POSTS'),
    ('READ_COMMENTS'),
    ('WRITE_COMMENTS'),
    ('EDIT_COMMENTS'),
    ('DELETE_COMMENTS');

insert into role(role)
values
    ('ADMIN'),
    ('USER');

insert into roles_authority(role_id, authority_id)
values
    ((select id from role where role = 'ADMIN'), (select id from authority where authority = 'FULL')),
    ((select id from role where role = 'USER'), (select id from authority where authority = 'READ_POSTS')),
    ((select id from role where role = 'USER'), (select id from authority where authority = 'WRITE_POSTS')),
    ((select id from role where role = 'USER'), (select id from authority where authority = 'READ_COMMENTS')),
    ((select id from role where role = 'USER'), (select id from authority where authority = 'WRITE_COMMENTS'));

insert into usr_role(usr_id, role_id)
values
    ((select id from users where email = 'izlax6002@gmail.com'), (select id from role where role = 'ADMIN')),
    ((select id from users where email = 'izlax1@gmail.com'), (select id from role where role = 'USER')),
    ((select id from users where email = 'izlax2@gmail.com'), (select id from role where role = 'USER')),
    ((select id from users where email = 'izlax3@gmail.com'), (select id from role where role = 'USER'));


