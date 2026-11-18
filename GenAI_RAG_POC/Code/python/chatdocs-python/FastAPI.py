from fastapi import APIRouter
from services.rag_service import RagService

router = APIRouter(
    prefix="/api/chat",
    tags=["Chat API"]
)

rag_service = RagService()


@router.post("/ask")
async def ask(question: str):

    answer = rag_service.ask_question(
        question
    )

    return {
        "question": question,
        "answer": answer
    }
