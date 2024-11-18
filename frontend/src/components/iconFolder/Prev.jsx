import { GrCaretPrevious } from "react-icons/gr";

const Prev = () => {
  return (
    <GrCaretPrevious
      size="20"
      onMouseOver={({ target }) => (target.style.color = "#00B890")}
      onMouseOut={({ target }) => (target.style.color = "gray")}
    />
  );
};
export default Prev;
