import { Box } from "@mui/material";
import Header from "./components/Header";
import Sidebar from "./components/Sidebar";
import Footer from "./components/Footer";
import UploadPage from "./pages/UploadPage";

function App() {
  return (
    <Box sx={{ height: "100vh" }}>
      <Header />

      <Box sx={{ display: "flex" }}>
        <Sidebar />

        <Box
          component="main"
          sx={{
            flexGrow: 1,
            p: 3,
            bgcolor: "#f5f7fa",
            minHeight: "calc(100vh - 128px)"
          }}
        >
          <UploadPage />
        </Box>
      </Box>

      <Footer />
    </Box>
  );
}

export default App;