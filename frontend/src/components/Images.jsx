import React, { useState } from "react";

const ImageUploader = ({ onImagesChange }) => {
  const [images, setImages] = useState([]);

  const handleDrop = (event) => {
    event.preventDefault();
    const files = Array.from(event.dataTransfer.files);

    const newImages = files.filter((file) => file.type.startsWith("image/"));
    if (images.length + newImages.length > 5) {
      alert("최대 5개의 이미지만 업로드할 수 있습니다.");
      return;
    }

    const imageUrls = newImages.map((file) =>
      Object.assign(file, { preview: URL.createObjectURL(file) })
    );
    const updatedImages = [...images, ...imageUrls].slice(0, 5);
    setImages(updatedImages);
    onImagesChange(updatedImages);
  };

  const handleRemove = (index) => {
    const updatedImages = images.filter((_, i) => i !== index);
    setImages(updatedImages);
    onImagesChange(updatedImages);
  };

  const handleDragOver = (event) => {
    event.preventDefault();
  };

  return (
    <div>
      <div
        onDrop={handleDrop}
        onDragOver={handleDragOver}
        style={{
          border: "2px dashed #ccc",
          padding: "20px",
          textAlign: "center",
          cursor: "pointer",
          marginBottom: "10px",
        }}
      >
        이미지를 드래그 앤 드롭 하세요 (최대 5개)
      </div>

      <div style={{ display: "flex", flexWrap: "wrap", gap: "10px" }}>
        {images.map((image, index) => (
          <div
            key={index}
            style={{
              position: "relative",
              display: "inline-block",
              width: "100px",
              height: "100px",
            }}
          >
            <img
              src={image.preview}
              alt={`Uploaded ${index}`}
              style={{
                width: "100%",
                height: "100%",
                objectFit: "cover",
                borderRadius: "5px",
              }}
            />
            <button
              onClick={() => handleRemove(index)}
              style={{
                position: "absolute",
                top: "-5px",
                right: "-5px",
                backgroundColor: "red",
                color: "white",
                border: "none",
                borderRadius: "50%",
                width: "20px",
                height: "20px",
                cursor: "pointer",
              }}
            >
              ×
            </button>
          </div>
        ))}
      </div>
    </div>
  );
};

export default ImageUploader;
