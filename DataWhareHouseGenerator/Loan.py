# Loan.py

import psycopg2
import random
from datetime import datetime, timedelta


def connect():
    try:
        conn = psycopg2.connect(
            host='localhost',
            port='5432',
            user='postgres',
            password='admin',
            database='BancoPopulado'
        )
        return conn
    except psycopg2.Error as e:
        print("Erro ao conectar ao banco de dados:", e)


def get_books(conn):
    cursor = conn.cursor()
    try:
        cursor.execute("SELECT id FROM public.book")
        books = cursor.fetchall()
        return books
    except psycopg2.Error as e:
        print("Erro ao obter livros:", e)
    finally:
        cursor.close()


def get_students(conn):
    cursor = conn.cursor()
    try:
        cursor.execute("SELECT rm FROM public.student")
        students = cursor.fetchall()
        return students
    except psycopg2.Error as e:
        print("Erro ao obter estudantes:", e)
    finally:
        cursor.close()


def insert_loans(conn, books, students):
    cursor = conn.cursor()
    try:
        for _ in range(100):  # Inserir 100 empréstimos
            book_id = random.choice(books)[0]
            loan_date = random_date()
            return_date = loan_date + timedelta(
                days=random.randint(7, 30))  # Data de retorno aleatória entre 7 e 30 dias após o empréstimo
            loan_status = random.choice(["BORROWED", "RETURNED", "OVERDUE", "LOST"])
            student_rm = random.choice(students)[0]

            insert_query = "INSERT INTO public.loan (book_id, loan_date, return_date, loan_status, student_rm) VALUES (%s, %s, %s, %s, %s)"
            cursor.execute(insert_query, (book_id, loan_date, return_date, loan_status, student_rm))

        conn.commit()
        print("Empréstimos inseridos com sucesso!")
    except psycopg2.Error as e:
        conn.rollback()
        print("Erro ao inserir empréstimos:", e)
    finally:
        cursor.close()


def random_date():
    # Gerar uma data aleatória entre 2020 e 2024
    return datetime(random.randint(2020, 2024), random.randint(1, 12), random.randint(1, 28))


def main():
    connection = connect()
    if connection:
        books = get_books(connection)
        students = get_students(connection)
        if books and students:
            insert_loans(connection, books, students)
        connection.close()


if __name__ == "__main__":
    main()
