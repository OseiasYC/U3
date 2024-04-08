# Subject_course_subject.py

import psycopg2
import random

# Disciplinas comuns por área de estudo
subjects_by_course = {
    'BES': [
        "Lógica de Programação",
        "Banco de Dados",
        "Engenharia de Software",
        "Desenvolvimento Web",
        "Testes de Software",
        "Inteligência Artificial",
        "Segurança da Informação",
        "Sistemas Operacionais",
        "Redes de Computadores",
        "Matemática Discreta",
        "Programação Orientada a Objetos",
        "Estrutura de Dados",
        "Arquitetura de Computadores",
        "Compiladores",
        "Análise de Algoritmos"
    ],
    'ADS': [
        "Lógica de Programação",
        "Banco de Dados",
        "Algoritmos e Estrutura de Dados",
        "Programação Orientada a Objetos",
        "Cálculo I",
        "Cálculo II",
        "Estatística",
        "Engenharia de Software",
        "Desenvolvimento Web",
        "Redes de Computadores",
        "Sistemas Operacionais",
        "Inteligência Artificial",
        "Análise de Sistemas",
        "Sistemas Distribuídos",
        "Banco de Dados Avançado"
    ],
    'INF': [
        "Lógica de Programação",
        "Banco de Dados",
        "Algoritmos e Estrutura de Dados",
        "Programação Orientada a Objetos",
        "Cálculo I",
        "Cálculo II",
        "Estatística",
        "Inteligência Artificial",
        "Sistemas Operacionais",
        "Redes de Computadores",
        "Engenharia de Software",
        "Desenvolvimento Web",
        "Segurança da Informação",
        "Computação Gráfica",
        "Sistemas Embarcados"
    ],
    'MEC': [
        "Física Geral",
        "Cálculo I",
        "Cálculo II",
        "Álgebra Linear",
        "Termodinâmica",
        "Mecânica dos Fluidos",
        "Desenho Técnico",
        "Materiais de Engenharia",
        "Mecânica dos Sólidos",
        "Eletromagnetismo",
        "Dinâmica",
        "Controle de Sistemas",
        "Mecânica dos Corpos Rígidos",
        "Elementos de Máquinas",
        "Engenharia Térmica"
    ],
    'ELE': [
        "Eletrônica Básica",
        "Circuitos Elétricos",
        "Sistemas Digitais",
        "Eletromagnetismo",
        "Automação Industrial",
        "Instrumentação Eletrônica",
        "Controle de Processos",
        "Eletrônica de Potência",
        "Microcontroladores",
        "Telecomunicações",
        "Sistemas de Controle",
        "Máquinas Elétricas",
        "Sistemas de Energia",
        "Eletrônica de Potência",
        "Processamento de Sinais"
    ],
    'CIV': [
        "Mecânica Geral",
        "Física Aplicada",
        "Cálculo I",
        "Cálculo II",
        "Topografia",
        "Materiais de Construção",
        "Mecânica dos Solos",
        "Estruturas de Concreto",
        "Estruturas de Aço",
        "Fundações",
        "Hidráulica",
        "Saneamento",
        "Transporte",
        "Geotecnia",
        "Engenharia de Tráfego"
    ],
    'QUI': [
        "Química Geral",
        "Química Orgânica",
        "Química Inorgânica",
        "Físico-Química",
        "Química Analítica",
        "Termodinâmica Química",
        "Química Ambiental",
        "Bioquímica",
        "Engenharia Química",
        "Química Industrial",
        "Processos Químicos",
        "Operações Unitárias",
        "Cinética Química",
        "Síntese Orgânica",
        "Espectroscopia"
    ],
    'GAS': [
        "Técnicas Culinárias",
        "Gastronomia Brasileira",
        "Cozinha Internacional",
        "Panificação",
        "Confeitaria",
        "Gastronomia Molecular",
        "Gestão de Restaurantes",
        "Gestão de Eventos",
        "Empreendedorismo na Gastronomia",
        "História da Gastronomia",
        "Enologia",
        "Nutrição",
        "Higiene e Segurança Alimentar",
        "Gastronomia Funcional",
        "Gestão de Custos em Gastronomia"
    ],
    'BIO': [
        "Biologia Celular",
        "Genética",
        "Biologia Molecular",
        "Fisiologia Humana",
        "Ecologia",
        "Botânica",
        "Zoologia",
        "Microbiologia",
        "Bioquímica",
        "Imunologia",
        "Parasitologia",
        "Evolução",
        "Biologia do Desenvolvimento",
        "Biologia Marinha",
        "Biologia Evolutiva"
    ]
    # Adicione mais disciplinas conforme necessário
}


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


def populate_subjects(conn, courses):
    cursor = conn.cursor()
    try:
        for course_id in courses:
            course_id = course_id[0]
            subjects = subjects_by_course.get(course_id, [])
            if not subjects:
                print(f"Não há disciplinas para o curso {course_id}")
                continue
            # Adicionando disciplinas para garantir que cada curso tenha pelo menos 10 disciplinas
            while len(subjects) < 15:
                random_subject = random.choice(subjects_by_course[course_id])
                if random_subject not in subjects:
                    subjects.append(random_subject)

            for subject_name in subjects:
                workload = random.choice([30, 60, 90])
                insert_query = "INSERT INTO public.subject (workload, name) VALUES (%s, %s) RETURNING id"
                cursor.execute(insert_query, (workload, subject_name))
                subject_id = cursor.fetchone()[0]

                # Associando a disciplina ao curso na tabela associativa course_subject
                insert_course_subject_query = "INSERT INTO public.course_subject (course_id, subject_id) VALUES (%s, %s)"
                cursor.execute(insert_course_subject_query, (course_id, subject_id))
                print(f"Disciplina '{subject_name}' inserida para o curso '{course_id}' com sucesso.")

        conn.commit()
        print("Tabelas de disciplinas populadas com sucesso!")
    except psycopg2.Error as e:
        conn.rollback()
        print("Erro ao popular tabelas de disciplinas:", e)
    finally:
        cursor.close()


def main():
    connection = connect()
    if connection:
        courses = get_courses(connection)
        if courses:
            populate_subjects(connection, courses)
        connection.close()

if __name__ == "__main__":
    main()
