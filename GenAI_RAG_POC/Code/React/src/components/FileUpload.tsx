import { useState } from "react";
import axios from "axios";

import {
  Button,
  Card,
  CardContent,
  Typography,
  Box
} from "@mui/material";

import CloudUploadIcon from "@mui/icons-material/CloudUpload";

function FileUpload() {
  const [file, setFile] = useState<File | null>(null);

  const handleUpload = async () => {
    if (!file) {
      alert("Select a file");
      return;
    }

    const formData = new FormData();
    formData.append("file", file);

    await axios.post(
      "http://localhost:8080/api/files/upload",
      formData
    );

    alert("Upload Successful");
  };

  return (
    <Card elevation={4}>
      <CardContent>

        <Typography
          variant="h5"
          gutterBottom
        >
          Upload Document
        </Typography>

        <Box
          sx={{
            border: "2px dashed #1976d2",
            borderRadius: 2,
            p: 5,
            textAlign: "center",
            mb: 3,
          }}
        >
          <input
            type="file"
            onChange={(e) =>
              setFile(e.target.files?.[0] || null)
            }
          />

          {file && (
            <Typography sx={{ mt: 2 }}>
              Selected: {file.name}
            </Typography>
          )}
        </Box>

        <Button
          variant="contained"
          startIcon={<CloudUploadIcon />}
          onClick={handleUpload}
        >
          Upload
        </Button>

      </CardContent>
    </Card>
  );
}

export default FileUpload;