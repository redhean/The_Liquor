import { Outlet } from "react-router-dom";
import "./App.css";
import Topbar from "./components/Topbar";

function App() {
  return (
    <div className="flex flex-col min-h-screen bg-[var(--maincolor)]">
      <Topbar />
      <Outlet />
    </div>
  );
}

export default App;
