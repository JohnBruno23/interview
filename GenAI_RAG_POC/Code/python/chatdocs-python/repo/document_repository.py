from database import get_connection


class DocumentRepository:

    def find_all(self):

        conn = get_connection()

        cursor = conn.cursor()

        cursor.execute("""
            SELECT
                ID,
                FILE_NAME,
                CONTENT
            FROM DOCUMENT_STORE
        """)

        rows = cursor.fetchall()

        documents = []

        for row in rows:

            documents.append({
                "id": row[0],
                "file_name": row[1],
                "content": row[2]
            })

        cursor.close()
        conn.close()

        return documents
