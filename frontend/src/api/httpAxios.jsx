import axios from 'axios';

const httpAxios = axios.create({
  baseURL: "http://10.10.0.160:8080/",
  withCredentials: false,
  headers: {
    "Content-Type": "application/json",
  },
});

export default httpAxios;