import { Link } from "react-router-dom";

export default function Topbar() {
  return (
    <div className="flex gap-8 bg-slate-300 h-auto justify-between p-4">
      <div>Logo</div>
      <div className="flex gap-4">
        <Link to="/">Home</Link>
        <Link to="/cardnews">Guide</Link>
        <Link to="/admin">Admin</Link>
      </div>
    </div>
  );
}
