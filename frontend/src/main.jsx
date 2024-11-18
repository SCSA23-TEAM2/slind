import { createRoot } from "react-dom/client";
import "./index.css";
import ReactDOM from "react-dom/client";
import { BrowserRouter } from 'react-router-dom'
import App from "./App.jsx";
import { AuthProvider } from './AuthContext';
createRoot(document.getElementById("root")).render(
    <AuthProvider>
<BrowserRouter>
<App />
</BrowserRouter></AuthProvider>);
