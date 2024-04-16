-- StudentSummary
INSERT INTO student_summary (student_rm, course_id, total_course_workload_percentage, global_average, situation, shift, course_entry_date)
VALUES 
('1234567890', 'BES', 80.0, 7.0, 'STUDYING', 'MORNING', '2023-01-15'::DATE),
('0987654321', 'ADS', 75.0, 8.0, 'STUDYING', 'MORNING', '2023-02-20'::DATE);

-- StudentSubjectGrades
INSERT INTO student_subject_grades (course_id, situation, total_subject_workload, grades, abscences, student_summary_id, subject_entry_date)
VALUES 
('BES', 'STUDYING', 60, ARRAY[7.0, 8.0], 2, 1, '2023-01-15'::DATE),
('ADS', 'STUDYING', 45, ARRAY[7.0, 8.0], 1, 1, '2023-02-20'::DATE);

