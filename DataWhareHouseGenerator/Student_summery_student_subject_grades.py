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


def insert_student_summaries(conn, students, courses):
    cursor = conn.cursor()
    try:
        for student in students:
            student_rm = student[0]
            global_average = random.uniform(0, 10)
            total_course_workload_percentage = random.uniform(0, 100)
            course_id = random.choice(courses)[0]
            shift = random.choice(["MORNING", "AFTERNOON", "NIGHT"])
            situation = random.choice(["PENDING", "CURSING", "CONCLUDED", "DISAPPROVED", "STOPPED"])

            insert_query = "INSERT INTO public.student_summary (course_entry_date, global_average, total_course_workload_percentage, id, course_id, shift, situation, student_rm) VALUES (CURRENT_DATE, %s, %s, DEFAULT, %s, %s, %s, %s)"
            cursor.execute(insert_query,
                           (global_average, total_course_workload_percentage, course_id, shift, situation, student_rm))

        conn.commit()
        print("Student_Summaries inseridos com sucesso!")
    except psycopg2.Error as e:
        conn.rollback()
        print("Erro ao inserir Student_Summaries:", e)
    finally:
        cursor.close()


def insert_student_subject_grades(conn, students, courses):
    cursor = conn.cursor()
    try:
        for student in students:
            cursor.execute("SELECT id FROM public.student_summary")
            student_summary_ids = cursor.fetchall()
            student_summary_id = random.choice(student_summary_ids)[0]

            abscences = random.randint(0, 20)
            subject_entry_date = random_date()
            total_subject_workload = random.choice([30, 60, 90])
            course_id = random.choice(courses)[0]
            situation = random.choice(["PENDING", "CURSING", "CONCLUDED", "DISAPPROVED", "STOPPED"])
            grades = [random.uniform(0, 10) for _ in range(5)]  # Simulando 5 notas aleatórias

            insert_query = "INSERT INTO public.student_subject_grades (abscences, subject_entry_date, total_subject_workload, id, student_summary_id, course_id, situation, grades) VALUES (%s, %s, %s, DEFAULT, %s, %s, %s, %s)"
            cursor.execute(insert_query, (
            abscences, subject_entry_date, total_subject_workload, student_summary_id, course_id, situation, grades))

        conn.commit()
        print("Student_Subject_Grades inseridos com sucesso!")
    except psycopg2.Error as e:
        conn.rollback()
        print("Erro ao inserir Student_Subject_Grades:", e)
    finally:
        cursor.close()


def random_date():
    # Gerar uma data aleatória entre 2020 e 2024
    return f"{random.randint(2020, 2024)}-{random.randint(1, 12)}-{random.randint(1, 28)}"


def main():
    connection = connect()
    if connection:
        students = get_students(connection)
        courses = get_courses(connection)
        if students and courses:
            insert_student_summaries(connection, students, courses)
            insert_student_subject_grades(connection, students, courses)
        connection.close()


if __name__ == "__main__":
    main()
