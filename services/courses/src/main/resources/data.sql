-- Courses
INSERT INTO course (id, name, students_rm) VALUES 
('BES', 'Bacharelado em Engenharia de Software', ARRAY['RM1', 'RM2', 'RM3']),
('ADS', 'Análise e Desenvolvimento de Sistemas', ARRAY['RM1', 'RM2', 'RM3']);
-- Subjects
INSERT INTO subject (name, workload) VALUES 
('Arquitetura de Computadores', 60),
('Gestão do Conhecimento', 60),
('Banco de Dados I', 60);
