-- TODO replace postgres sequence with bigserial
INSERT INTO users(first_name, last_name, login, email, password, role) VALUES
  ('Kirill', 'Liubun', 'kreol', 'igneuslynx@gmail.com', 'send08', 'USER'),
  ('Vadym', 'Akymov', 'vadimka', 'vadimka2995@gmail.com', 'password', 'USER');

INSERT INTO users(login, password, role) VALUES
  ('user', 'user123', 'USER'),
  ('admin', 'admin123', 'ADMIN');