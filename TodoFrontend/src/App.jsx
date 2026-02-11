import { BrowserRouter, Routes, Route } from "react-router-dom";
import Login from "./pages/login";
import Todo from "./pages/Todo";
import ProtectedRoute from "./route/ProtectedRoute";
import SignUp from "./pages/Singup";


export default function App() {
  return (
    <BrowserRouter>
    <Routes>
      <Route path="/login" element={<Login />} />
      <Route path="/signup" element={<SignUp/>} />
      <Route path="/" element={
        <ProtectedRoute>
          <Todo />
        </ProtectedRoute>
      } />
    </Routes>
    </BrowserRouter>
  )
}