import { Outlet } from "react-router-dom";
import "./App.css";
import Topbar from "./components/Topbar";

function App() {
  return (
    <div className="h-screen">
      <Topbar />
      <Outlet />
    </div>
  );
}

export default App;
