# Book.py

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


def insert_books(conn, num_books):
    cursor = conn.cursor()
    try:
        for _ in range(num_books):
            amount = random.randint(1, 10)
            author = generate_author()
            title = generate_title()
            insert_query = "INSERT INTO public.book (amount, author, title) VALUES (%s, %s, %s)"
            cursor.execute(insert_query, (amount, author, title))

        conn.commit()
        print(f"{num_books} livros inseridos com sucesso!")
    except psycopg2.Error as e:
        conn.rollback()
        print("Erro ao inserir livros:", e)
    finally:
        cursor.close()


def generate_author():
    # Gerar um nome de autor aleatório
    first_names = ["J.K.", "Stephen", "Agatha", "George", "Tolkien", "Jane", "Charles", "Virginia", "Mark", "Ernest"]
    last_names = ["Rowling", "King", "Christie", "Orwell", "Martin", "Austen", "Dickens", "Woolf", "Twain", "Hemingway"]
    return f"{random.choice(first_names)} {random.choice(last_names)}"


def generate_title():
    # Gerar um título de livro aleatório
    titles = ["Harry Potter and the Philosopher's Stone", "The Shining", "Murder on the Orient Express", "1984",
              "A Game of Thrones", "Pride and Prejudice", "Great Expectations", "To the Lighthouse",
              "The Adventures of Huckleberry Finn", "The Old Man and the Sea"]
    return random.choice(titles)


def main():
    connection = connect()
    if connection:
        insert_books(connection, 100)  # Inserindo 100 livros
        connection.close()


if __name__ == "__main__":
    main()
