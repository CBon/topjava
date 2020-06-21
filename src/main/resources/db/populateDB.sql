DELETE
FROM meals;
DELETE
FROM user_roles;
DELETE
FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id)
VALUES ('ROLE_USER', 100000),
       ('ROLE_ADMIN', 100001);

INSERT INTO meals (user_id, description, datetime, calories)
VALUES (100000, 'Завтрак', '2020-05-30 10:00', 500);
INSERT INTO meals (user_id, description, datetime, calories)
VALUES (100000, 'Обед', '2020-05-30 13:00', 1000);
INSERT INTO meals (user_id, description, datetime, calories)
VALUES (100000, 'Ужин', '2020-05-30 20:00', 500);
INSERT INTO meals (user_id, description, datetime, calories)
VALUES (100000, 'Ужин', '2020-05-30 00:00', 510);
INSERT INTO meals (user_id, description, datetime, calories)
VALUES (100000, 'Завтрак', '2020-05-31 10:00', 1000);
INSERT INTO meals (user_id, description, datetime, calories)
VALUES (100000, 'Обед', '2020-05-31 13:00', 500);
INSERT INTO meals (user_id, description, datetime, calories)
VALUES (100000, 'Ужин', '2020-05-31 20:00', 510);
INSERT INTO meals (user_id, description, datetime, calories)
VALUES (100000, 'Полночный ужин', '2020-05-31 00:00', 510);

INSERT INTO meals (user_id, description, datetime, calories)
VALUES (100001, 'Админ ланч', '2020-05-30 12:00', 510);
INSERT INTO meals (user_id, description, datetime, calories)
VALUES (100001, 'Админ Ужин', '2020-05-31 23:00', 1500);
