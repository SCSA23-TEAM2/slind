import { GrCaretNext } from "react-icons/gr";

const Next = () => {
  return (
    <GrCaretNext
      size="20"
      onMouseOver={({ target }) => (target.style.color = "#00B890")}
      onMouseOut={({ target }) => (target.style.color = "gray")}
    />
  );
};
export default Next;
