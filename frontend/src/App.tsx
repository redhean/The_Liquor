import { Outlet } from "react-router-dom";
import "./App.css";
import Topbar from "./components/Topbar";

function App() {
  return (
    <div className="flex flex-col h-screen">
      <Topbar />
      <Outlet />
    </div>
  );
}

export default App;
