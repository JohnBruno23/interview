import os
import time
import shutil

from config import UPLOAD_DIR

os.makedirs(UPLOAD_DIR, exist_ok=True)


class FileStorageService:

    def store_file(self, file):

        file_name = f"{int(time.time()*1000)}_{file.filename}"

        destination = os.path.join(
            UPLOAD_DIR,
            file_name
        )

        with open(destination, "wb") as buffer:
            shutil.copyfileobj(file.file, buffer)

        return destination