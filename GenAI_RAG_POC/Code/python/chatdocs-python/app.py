from fastapi import FastAPI
from fastapi import UploadFile
from fastapi import File

from models.upload_response import UploadResponse

from services.file_storage_service import FileStorageService
from services.document_upload_service import DocumentUploadService


app = FastAPI(
    title="Chat Docs API",
    description="Upload PDF and Save Embeddings",
    version="1.0.0"
)

file_storage_service = FileStorageService()
document_upload_service = DocumentUploadService()


@app.post(
    "/api/files/upload2",
    response_model=UploadResponse
)
async def upload_file(
        file: UploadFile = File(...)
):

    stored_path = file_storage_service.store_file(
        file
    )

    document_upload_service.upload(
        file.filename,
        stored_path
    )

    return UploadResponse(
        file_name=file.filename,
        stored_path=stored_path
    )