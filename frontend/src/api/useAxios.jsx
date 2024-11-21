import httpAxios from "./httpAxios";
import { useAuth } from "../AuthContext";
import { useNavigate } from "react-router-dom";
const useAxios = () => {
  const navigate = useNavigate();
  const { accessToken, refreshToken, logout, setAccessToken, setRefreshToken } =
    useAuth();

  // Create an Axios instance with default headers
  const axiosInstance = httpAxios.create({
    baseURL: "http://10.10.0.160:8080",
    //baseURL : "http://localhost:8080/", // Replace with your API base URL
    headers: {
      "Content-Type": "application/json",
    },
  });

  // Add the access_token to the Authorization header of each request
  axiosInstance.interceptors.request.use(
    (config) => {
      if (accessToken) {
        config.headers["Authorization"] = `Bearer ${accessToken}`;
      }
      return config;
    },
    (error) => {
      return Promise.reject(error);
    }
  );

  // Interceptor to handle token expiration and refreshing the access token
  axiosInstance.interceptors.response.use(
    (response) => response,
    async (error) => {
      // If the access token has expired (e.g., 401 error), use the refresh token
      if (error.response && error.response.status === 401 && refreshToken) {
        try {
          const refreshResponse = await httpAxios.post(
            "http://10.10.0.160:8080/", // Your refresh token API endpoint
            { refresh_token: refreshToken }
          );

          const newAccessToken = refreshResponse.data.access_token;
          const newRefreshToken = refreshResponse.data.refresh_token;

          // Update tokens in context and localStorage
          setAccessToken(newAccessToken);
          setRefreshToken(newRefreshToken);

          localStorage.setItem("access_token", newAccessToken);
          localStorage.setItem("refresh_token", newRefreshToken);

          // Retry the original request with the new access token
          error.config.headers["Authorization"] = `Bearer ${newAccessToken}`;
          return axiosInstance(error.config);
        } catch (refreshError) {
          logout(); // If refresh fails, log out
        }
      }

      return Promise.reject(error);
    }
  );

  return axiosInstance;
};

export default useAxios;
