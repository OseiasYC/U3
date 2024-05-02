-- Courses --
-- Courses
INSERT INTO course (id, name, students_rm) VALUES 
('BES', 'Bacharelado em Engenharia de Software', ARRAY['200019213', '200014717', '200019492']),
('ADS', 'Análise e Desenvolvimento de Sistemas', ARRAY['200016717', '200020478', '200020200']),
('CCC', 'Ciência da Computação', ARRAY['200020201', '200020202', '200020203']),
('SIS', 'Sistemas de Informação', ARRAY['200020204', '200020205', '200020206']),
('EGC', 'Engenharia da Computação', ARRAY['200020207', '200020208', '200020209']),
('GTI', 'Gestão da Tecnologia da Informação', ARRAY['200020210', '200020211', '200020212']),
('RDS', 'Redes de Computadores', ARRAY['200020213', '200020214', '200020215']),
('LOG', 'Logística', ARRAY['200020216', '200020217', '200020218']),
('ADM', 'Administração', ARRAY['200020219', '200020220', '200020221']),
('CON', 'Contabilidade', ARRAY['200020222', '200020223', '200020224']);

-- Subjects
INSERT INTO subject (name, workload) VALUES 
('Arquitetura de Computadores', 60),
('Gestão do Conhecimento', 60),
('Banco de Dados I', 60),
('Tópicos Avançados em Banco de Dados', 60),
('Compiladores', 60),
('Programação Web', 60),
('Programação Mobile', 60),
('Estrutura de Dados', 60),
('Inteligência Artificial', 60),
('Programação Orientada a Objetos', 60);

-- Course_subjects
INSERT INTO course_subjects (course_id, subject_id) VALUES 
('BES', 1),
('BES', 2),
('BES', 3),
('ADS', 1),
('ADS', 2),
('ADS', 6),
('CCC', 7),
('CCC', 4),
('CCC', 3),
('SIS', 3),
('SIS', 1),
('SIS', 2),
('SIS', 3),
('ADM', 4),
('ADM', 5),
('ADM', 6),
('GTI', 7),
('GTI', 8),
('GTI', 9),
('GTI', 10);


-- Enrollment --
-- Students
INSERT INTO Student (rm, name, username, cpf, birth, courses_id) VALUES
('200019213', 'João da Silva Moreira', 'joao.moreira', '12345678901', '15-05-1990', ARRAY['BES','ADS']),
('200014717', 'André Torres de Souza', 'andre.souza', '21324354657', '18-05-1995', ARRAY['BES']),
('200019492', 'Maria da Silva', 'maria.silva', '12345678901', '15-05-1990', ARRAY['BES']),
('200016717', 'José da Silva', 'jose.silva', '12345678901', '15-05-1990', ARRAY['ADS']),
('200020478', 'Ana da Silva', 'ana.silva', '12345678901', '15-05-1990', ARRAY['ADS']),
('200020478', 'Pedro da Silva', 'pedro.silva', '12345678901', '15-05-1990', ARRAY['ADS']),
('200020201', 'Paulo da Silva', 'paulo.silva', '12345678901', '15-05-1990', ARRAY['CCC']),
('200020201', 'Carlos da Silva', 'carlos.silva', '12345678901', '15-05-1990', ARRAY['CCC']),
('200020202', 'Lucas da Silva', 'lucas.silva', '12345678901', '15-05-1990', ARRAY['CCC']),
('200020203', 'Mariana da Silva', 'mariana.silva', '12345678901', '15-05-1990', ARRAY['SIS']);

-- Grades --
-- StudentSummary
INSERT INTO student_summary (student_rm, course_id, total_course_workload_percentage, global_average, situation, shift, course_entry_date)
VALUES 
('200019213', 'BES', 80.0, 7.0, 'STUDYING', 'MORNING', '2023-01-15'::DATE),
('200014717', 'ADS', 75.0, 8.0, 'STUDYING', 'MORNING', '2023-02-20'::DATE),
('200020000', 'CCC', 70.0, 9.0, 'GRADUATED', 'NIGHT', '2023-03-25'::DATE),
('200020001', 'SIS', 65.0, 10.0, 'GRADUATED', 'NIGHT', '2023-04-30'::DATE),
('200020002', 'EGC', 60.0, 6.0, 'GRADUATED', 'NIGHT', '2023-05-15'::DATE),
('200020003', 'GTI', 55.0, 5.0, 'GRADUATED', 'NIGHT', '2023-06-20'::DATE),
('200020004', 'RDS', 50.0, 4.0, 'GRADUATED', 'NIGHT', '2023-07-25'::DATE),
('200020005', 'LOG', 45.0, 3.0, 'GRADUATED', 'NIGHT', '2023-08-30'::DATE),
('200020006', 'ADM', 40.0, 2.0, 'GRADUATED', 'NIGHT', '2023-09-15'::DATE),
('200020007', 'CON', 35.0, 1.0, 'GRADUATED', 'NIGHT', '2023-10-20'::DATE);

-- StudentSubjectGrades
INSERT INTO student_subject_grades (course_id, situation, total_subject_workload, grades, abscences, student_summary_id, subject_entry_date) VALUES 
('BES', 'STUDYING', 60, ARRAY[7.0, 8.0], 2, 1, '2023-01-15'::DATE),
('ADS', 'STUDYING', 45, ARRAY[7.0, 8.0], 1, 1, '2023-02-20'::DATE),
('CCC', 'GRADUATED', 30, ARRAY[7.0, 8.0], 0, 1, '2023-03-25'::DATE),
('SIS', 'GRADUATED', 15, ARRAY[7.0, 8.0], 0, 1, '2023-04-30'::DATE),
('EGC', 'GRADUATED', 60, ARRAY[7.0, 8.0], 0, 1, '2023-05-15'::DATE),
('GTI', 'GRADUATED', 45, ARRAY[7.0, 8.0], 0, 1, '2023-06-20'::DATE),
('RDS', 'GRADUATED', 30, ARRAY[7.0, 8.0], 0, 1, '2023-07-25'::DATE),
('LOG', 'GRADUATED', 15, ARRAY[7.0, 8.0], 0, 1, '2023-08-30'::DATE),
('ADM', 'GRADUATED', 60, ARRAY[7.0, 8.0], 0, 1, '2023-09-15'::DATE),
('CON', 'GRADUATED', 45, ARRAY[7.0, 8.0], 0, 1, '2023-10-20'::DATE);

-- Library --
--Loans:
INSERT INTO Loan (student_rm, book_id, loan_status, loan_date, return_date) VALUES 
('200019213', 1, 'BORROWED', '2024-03-30 10:00:00', '2024-04-10 10:00:00'),
('0987654321', 2, 'OVERDUE', '2024-03-28 15:45:00', '2024-04-07 15:45:00'),
('1234567890', 3, 'RETURNED', '2024-03-25 10:00:00', '2024-04-05 10:00:00'),
('0987654321', 1, 'BORROWED', '2024-03-30 15:45:00', '2024-04-10 15:45:00'),
('1234567890', 2, 'BORROWED', '2024-03-30 10:00:00', '2024-04-10 10:00:00'),
('0987654321', 3, 'BORROWED', '2024-03-30 15:45:00', '2024-04-10 15:45:00'),
('1234567890', 1, 'BORROWED', '2024-03-30 10:00:00', '2024-04-10 10:00:00'),
('0987654321', 2, 'BORROWED', '2024-03-30 15:45:00', '2024-04-10 15:45:00'),
('1234567890', 3, 'BORROWED', '2024-03-30 10:00:00', '2024-04-10 10:00:00'),
('0987654321', 1, 'BORROWED', '2024-03-30 15:45:00', '2024-04-10 15:45:00');

-- Books:
INSERT INTO book (title, author, amount) VALUES 
('The Great Gatsby', 'F. Scott Fitzgerald', 5),
('To Kill a Mockingbird', 'Harper Lee', 3),
('1984', 'George Orwell', 7),
('Pride and Prejudice', 'Jane Austen', 2),
('The Catcher in the Rye', 'J.D. Salinger', 4),
('The Hobbit', 'J.R.R. Tolkien', 6),
('The Lord of the Rings', 'J.R.R. Tolkien', 8),
('Animal Farm', 'George Orwell', 3),
('Brave New World', 'Aldous Huxley', 5),
('The Diary of a Home', 'Anne Frank', 2);

-- Requests --
INSERT INTO request (student_rm, title, description, request_date, status) VALUES
('200019213', 'Requerimento de Horas Complementares', 'Quero validar mais horas complementares', CURRENT_TIMESTAMP, 'OPEN'),
('200020001', 'Requerimento de Aproveitamento de Disciplina', 'Quero aproveitar a disciplina de Banco de Dados I', CURRENT_TIMESTAMP, 'OPEN'),
('200020002', 'Requerimento de Trancamento de Matrícula', 'Quero trancar minha matrícula', CURRENT_TIMESTAMP, 'OPEN'),
('200020003', 'Requerimento de Colação de Grau', 'Quero colar grau', CURRENT_TIMESTAMP, 'OPEN'),
('200020004', 'Requerimento de Revisão de Prova', 'Quero revisar minha prova', CURRENT_TIMESTAMP, 'OPEN'),
('200020005', 'Requerimento de Aproveitamento de Disciplina', 'Quero aproveitar a disciplina de Banco de Dados I', CURRENT_TIMESTAMP, 'OPEN'),
('200020006', 'Requerimento de Trancamento de Matrícula', 'Quero trancar minha matrícula', CURRENT_TIMESTAMP, 'OPEN'),
('200020007', 'Requerimento de Colação de Grau', 'Quero colar grau', CURRENT_TIMESTAMP, 'OPEN'),
('200020008', 'Requerimento de Revisão de Prova', 'Quero revisar minha prova', CURRENT_TIMESTAMP, 'OPEN'),
('200020009', 'Requerimento de Aproveitamento de Disciplina', 'Quero aproveitar a disciplina de Banco de Dados I', CURRENT_TIMESTAMP, 'OPEN');
