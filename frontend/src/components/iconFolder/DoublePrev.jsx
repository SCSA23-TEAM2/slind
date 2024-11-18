import { GrChapterPrevious } from "react-icons/gr";
const DoublePrev = () => {
  return (
    <GrChapterPrevious
      size="20"
      onMouseOver={({ target }) => (target.style.color = "#00B890")}
      onMouseOut={({ target }) => (target.style.color = "gray")}
    />
  );
};
export default DoublePrev;
