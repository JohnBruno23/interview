import {
  Breadcrumbs,
  Link,
  Typography
} from "@mui/material";

import FileUpload from "../components/FileUpload";

function UploadPage() {
  return (
    <>
      <Breadcrumbs sx={{ mb: 3 }}>

        <Link underline="hover">
          Home
        </Link>

        <Link underline="hover">
          Documents
        </Link>

        <Typography color="text.primary">
          Upload File
        </Typography>

      </Breadcrumbs>

      <FileUpload />
    </>
  );
}

export default UploadPage;