-- Courses
INSERT INTO course (id, students_rm) VALUES 
('BES', ARRAY['RM1', 'RM2', 'RM3']),
('ADS', ARRAY['RM1', 'RM2', 'RM3']);

-- Subjects
INSERT INTO subject (name, workload) VALUES 
('Arquitetura de Computadores', 60),
('Gestão do Conhecimento', 60),
('Banco de Dados I', 60);
