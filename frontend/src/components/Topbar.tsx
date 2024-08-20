import { Link } from "react-router-dom";
import "./TopBar.css";

export default function Topbar() {
  return (
    <div>
      <Link to="/">Home</Link>
      <Link to="/admin">Admin</Link>
    </div>
  );
}
