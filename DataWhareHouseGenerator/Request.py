# Request.py

import psycopg2
import random


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


def insert_requests(conn, students):
    cursor = conn.cursor()
    try:
        for _ in range(100):  # Inserir 100 solicitações
            request_date = random_date()
            description = generate_description()
            status = random.choice(["OPEN", "CONCLUDED", "CANCELED"])
            student_rm = random.choice(students)[0]
            title = generate_title()
            attachment = None  # Simulando que não há anexos

            insert_query = "INSERT INTO public.request (request_date, description, status, student_rm, title, attachment) VALUES (%s, %s, %s, %s, %s, %s)"
            cursor.execute(insert_query, (request_date, description, status, student_rm, title, attachment))

        conn.commit()
        print("Requests inseridos com sucesso!")
    except psycopg2.Error as e:
        conn.rollback()
        print("Erro ao inserir Requests:", e)
    finally:
        cursor.close()


def random_date():
    # Gerar uma data aleatória entre 2020 e 2024
    return f"{random.randint(2020, 2024)}-{random.randint(1, 12)}-{random.randint(1, 28)}"


def generate_description():
    # Gerar uma descrição aleatória para a solicitação
    descriptions = ["Problema técnico", "Dúvida sobre o curso", "Solicitação de materiais", "Problema de pagamento",
                    "Outro"]
    return random.choice(descriptions)


def generate_title():
    # Gerar um título aleatório para a solicitação
    titles = ["Ajuda com aula de matemática", "Dúvida sobre prazo de entrega", "Problema com login",
              "Informações sobre evento", "Outro"]
    return random.choice(titles)


def main():
    connection = connect()
    if connection:
        students = get_students(connection)
        if students:
            insert_requests(connection, students)
        connection.close()


if __name__ == "__main__":
    main()
