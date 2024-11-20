import { createContext, useState, useContext, useEffect } from "react";

// Create AuthContext to manage authentication state
const AuthContext = createContext();

// AuthProvider component to wrap your application
export const AuthProvider = ({ children }) => {
  // Retrieve tokens from localStorage on load, if they exist
  const [accessToken, setAccessToken] = useState(
    localStorage.getItem("access_token") || null
  );
  const [refreshToken, setRefreshToken] = useState(
    localStorage.getItem("refresh_token") || null
  );
  console.log("처음으로 돌아간다!");
  const [isAuthenticated, setIsAuthenticated] = useState(
    localStorage.getItem("isAuthenticated") || null
  );
  // Set the tokens when the user logs in
  const login = (tokens) => {
    console.log(tokens);
    setAccessToken(tokens.access_token);
    setRefreshToken(tokens.refresh_token);
    setIsAuthenticated(true);
    localStorage.setItem("access_token", tokens.access_token);
    localStorage.setItem("refresh_token", tokens.refresh_token);
    localStorage.setItem("isAuthenticated", true);
  };

  // Remove tokens on logout
  const logout = () => {
    setAccessToken(null);
    setRefreshToken(null);
    setIsAuthenticated(false);
    localStorage.removeItem("access_token");
    localStorage.removeItem("refresh_token");
    localStorage.removeItem("isAuthenticated");
  };

  // Return the provider wrapping the children
  return (
    <AuthContext.Provider
      value={{ accessToken, refreshToken, isAuthenticated, login, logout }}
    >
      {children}
    </AuthContext.Provider>
  );
};

// Custom hook to use AuthContext in your components
export const useAuth = () => {
  return useContext(AuthContext);
};
