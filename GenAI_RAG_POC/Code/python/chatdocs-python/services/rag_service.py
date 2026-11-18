from repositories.document_repository import (
    DocumentRepository
)

from services.gemini_service import (
    GeminiService
)


class RagService:

    def __init__(self):

        self.repository = (
            DocumentRepository()
        )

        self.gemini_service = (
            GeminiService()
        )


    def ask_question(
            self,
            question: str
    ):

        documents = (
            self.repository.find_all()
        )

        context = "\n".join(
            doc["content"]
            for doc in documents
        )

        prompt = f"""
You are a helpful assistant.

Use only the provided context.

Context:

{context}

Question:

{question}

If the answer is not found
in the context then reply:

Information not found in documents.
"""

        return self.gemini_service.ask_gemini(
            prompt
        )