--Loans:
INSERT INTO Loan (student_rm, book_id, loan_status, loan_date, return_date) 
VALUES 
('1234567890', 1, 'BORROWED', '2024-03-30 10:00:00', '2024-04-10 10:00:00'),
('0987654321', 2, 'OVERDUE', '2024-03-28 15:45:00', '2024-04-07 15:45:00');

-- Books:
INSERT INTO book (title, author, amount,quantity) 
VALUES 
('The Great Gatsby', 'F. Scott Fitzgerald', 5,1),
('To Kill a Mockingbird', 'Harper Lee', 3,5),
('1984', 'George Orwell', 7,4);

