from google import genai
from config import GEMINI_API_KEY


class GeminiEmbeddingService:

    def __init__(self):

        self.client = genai.Client(
            api_key=GEMINI_API_KEY
        )

        print("GeminiEmbeddingService initialized")


    def create_embedding(self, text):

        response = self.client.models.embed_content(
            model="gemini-embedding-001",
            contents=text
        )

        return response.embeddings[0].values