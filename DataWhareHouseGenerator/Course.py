# Course.py

import psycopg2

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

def populate_courses():
    connection = connect()
    if connection:
        cursor = connection.cursor()
        try:
            courses_data = [
                ('BES', 'Engenharia de Software', []),
                ('ADS', 'Análise e Desenvolvimento de Sistemas', []),
                ('INF', 'Informática', []),
                ('MEC', 'Mecânica', []),
                ('ELE', 'Eletrônica', []),
                ('CIV', 'Civil', []),
                ('MEC', 'Mecatrônica', []),
                ('QUI', 'Química', []),
                ('GAS', 'Gastronomia', []),
                ('BIO', 'Biologia', []),
            ]

            for course in courses_data:
                cursor.execute("SELECT id FROM public.course WHERE id = %s", (course[0],))
                existing_course = cursor.fetchone()
                if existing_course:
                    print(f"Curso com ID '{course[0]}' já existe. Ignorando inserção.")
                else:
                    insert_query = "INSERT INTO public.course (id, name, students_rm) VALUES (%s, %s, %s)"
                    cursor.execute(insert_query, course)
                    print(f"Curso '{course[1]}' inserido com sucesso.")

            connection.commit()
            print("Tabelas de cursos populadas com sucesso!")
        except psycopg2.Error as e:
            connection.rollback()
            print("Erro ao popular tabelas de cursos:", e)
        finally:
            cursor.close()
            connection.close()

def main():
    populate_courses()

if __name__ == "__main__":
    main()
