import { AiOutlineLike } from "react-icons/ai";

const Like = ({ size, color }) => {
  return <AiOutlineLike size={size} color={color ? color : "#A7A9AD"} />;
};
export default Like;
