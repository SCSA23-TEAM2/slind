import { useEffect, useState } from "react";
import httpAxios from "../api/httpAxios";

const ImageListDisplay = ({ aPk }) => {
  const axios = httpAxios;
  const [imageUrls, setImageUrls] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  //   console.log(aPk);
  const articlePk = aPk;
  const AxiosImage = async () => {
    try {
      const response = await axios.get(`/api/image/${articlePk}`);
      const urls = response.data;
      setImageUrls(urls);
      setIsLoaded(true);
    } catch (err) {
      setError("이미지를 불러오는 데 실패했습니다.");
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    AxiosImage();
  }, [aPk]);

  if (loading) {
    return <p>이미지를 불러오는 중...</p>;
  }

  return (
    <div style={{ display: "flex", flexWrap: "wrap", gap: "10px" }}>
      {imageUrls.map((url, index) => {
        return (
          <img
            key={index}
            src={`http://10.10.0.160:8080${url}`}
            alt={`Fetched ${index + 1}`}
            style={{
              maxWidth: "400px",
              maxHeight: "400px",
              objectFit: "contain",
              border: "1px solid #ccc",
            }}
          />
        );
      })}
    </div>
  );
};

export default ImageListDisplay;
