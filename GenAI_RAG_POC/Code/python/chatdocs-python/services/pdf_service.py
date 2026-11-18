from pypdf import PdfReader


class PdfService:

    def extract(self, pdf_path: str):

        reader = PdfReader(pdf_path)

        text = ""

        for page in reader.pages:
            page_text = page.extract_text()
            if page_text:
                text += page_text

        return text