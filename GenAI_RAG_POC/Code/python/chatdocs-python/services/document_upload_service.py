import json

from database import get_connection
from services.pdf_service import PdfService
from services.gemini_embedding_service import GeminiEmbeddingService


class DocumentUploadService:

    def __init__(self):

        self.pdf_service = PdfService()
        self.embedding_service = GeminiEmbeddingService()


    def upload(self, file_name, stored_path):

        print("Extracting PDF text...")

        content = self.pdf_service.extract(
            stored_path
        )

        print("Generating embedding...")

        embedding = self.embedding_service.create_embedding(
            content
        )

        conn = get_connection()
        cursor = conn.cursor()

        sql = """
        INSERT INTO DOCUMENT_STORE
        (
            FILE_NAME,
            CONTENT,
            EMBEDDING
        )
        VALUES
        (
            :1,
            :2,
            :3
        )
        """

        cursor.execute(
            sql,
            [
                file_name,
                content,
                json.dumps(embedding)
            ]
        )

        conn.commit()

        cursor.close()
        conn.close()

        print("Document uploaded successfully")