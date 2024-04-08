# Student_course.py

import psycopg2
import random

# Lista de RM de estudantes
student_rms = []

# Lista de IDs de cursos
course_ids = []

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

def get_courses(conn):
    cursor = conn.cursor()
    try:
        cursor.execute("SELECT id FROM public.course")
        courses = cursor.fetchall()
        return courses
    except psycopg2.Error as e:
        print("Erro ao obter cursos:", e)
    finally:
        cursor.close()

def insert_students(conn, courses, num_students):
    cursor = conn.cursor()
    try:
        for _ in range(num_students):
            # Gerar RM único para o novo estudante
            rm = generate_rm()
            student_rms.append(rm)

            # Selecionar aleatoriamente de 1 a 3 cursos para o estudante
            random_courses = random.sample(courses, random.randint(1, 3))
            course_ids.extend(random_courses)

            # Inserir novo estudante na tabela
            insert_query = "INSERT INTO public.student (birth, rm, cpf, username, name, courses_id) VALUES (%s, %s, %s, %s, %s, %s)"
            cursor.execute(insert_query, generate_student_data(rm, random_courses))

            # Atualizar tabela de cursos com o RM do novo estudante
            update_courses_query = "UPDATE public.course SET students_rm = array_append(students_rm, %s) WHERE id = ANY(%s)"
            cursor.execute(update_courses_query, (rm, [course[0] for course in random_courses]))

        conn.commit()
        print(f"{num_students} estudantes inseridos com sucesso!")
    except psycopg2.Error as e:
        conn.rollback()
        print("Erro ao inserir estudantes:", e)
    finally:
        cursor.close()

def generate_rm():
    # Gerar um RM único para o novo estudante
    rm = ""
    while True:
        rm = "".join(random.choices("0123456789", k=10))
        if rm not in student_rms:
            break
    return rm

def generate_student_data(rm, courses):
    # Gerar dados para o novo estudante
    birth = random_birth_date()
    cpf = generate_cpf()
    username = generate_username()
    name = generate_name()
    courses_id = [course[0] for course in courses]
    return birth, rm, cpf, username, name, courses_id

def random_birth_date():
    # Gerar uma data de nascimento aleatória entre 1970 e 2005
    return f"{random.randint(1970, 2005)}-{random.randint(1, 12)}-{random.randint(1, 28)}"

def generate_cpf():
    # Gerar um CPF aleatório
    return "".join(random.choices("0123456789", k=11))

def generate_username():
    # Gerar um nome de usuário aleatório
    return "".join(random.choices("abcdefghijklmnopqrstuvwxyz", k=8))

def generate_name():
    # Gerar um nome aleatório
    first_names = ["Emma", "Noah", "Olivia", "Liam", "Ava", "William", "Sophia", "Mason", "Isabella", "James"]
    last_names = ["Smith", "Johnson", "Williams", "Brown", "Jones", "Miller", "Davis", "Garcia", "Rodriguez", "Wilson"]
    return f"{random.choice(first_names)} {random.choice(last_names)}"

def main():
    connection = connect()
    if connection:
        courses = get_courses(connection)
        if courses:
            insert_students(connection, courses, 10000)
        connection.close()

if __name__ == "__main__":
    main()
