from google import genai
from config import GEMINI_API_KEY


class GeminiService:

    def __init__(self):

        self.client = genai.Client(
            api_key=GEMINI_API_KEY
        )

        print(
            "GeminiService initialized"
        )


    def ask_gemini(self, prompt):

        try:

            response = self.client.models.generate_content(
                model="gemini-2.5-flash",
                contents=prompt
            )

            return response.text

        except Exception as ex:

            print(ex)

            return f"Error: {str(ex)}"