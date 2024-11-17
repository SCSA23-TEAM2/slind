import { GrChapterNext } from "react-icons/gr";

const DoubleNext = () => {
  return (
    <GrChapterNext
      size="20"
      onMouseOver={({ target }) => (target.style.color = "#00B890")}
      onMouseOut={({ target }) => (target.style.color = "gray")}
    />
  );
};
export default DoubleNext;
