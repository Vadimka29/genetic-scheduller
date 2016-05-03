INSERT INTO users(id, first_name, last_name, login, email, password, role) VALUES
  (nextval('hibernate_sequence'), 'Kirill', 'Liubun', 'kreol', 'igneuslynx@gmail.com', 'send08', 'USER'),
  (nextval('hibernate_sequence'), 'Vadym', 'Akymov', 'vadimka', 'vadimka2995@gmail.com', 'password', 'USER');

INSERT INTO users(id, login, password, role) VALUES
  (nextval('hibernate_sequence'), 'user', 'user123', 'USER'),
  (nextval('hibernate_sequence'), 'admin', 'admin123', 'ADMIN');