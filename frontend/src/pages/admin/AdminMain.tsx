import AddItemButton from "@/components/AddItemButton";
import { NavLink, Outlet } from "react-router-dom";

export default function AdminMain() {
  const configUrl = "/admin/config/";

  return (
    <div>
      <AddItemButton />
      <nav className="flex flex-row gap-4 text-lg bg-slate-400 justify-center py-2">
        <NavLink
          to={configUrl + "liquor"}
          className={({ isActive, isPending }) => {
            return isPending
              ? "bg-slate-600"
              : isActive
                ? "font-semibold underline underline-offset-2"
                : "";
          }}
        >
          술
        </NavLink>
        <NavLink
          to={configUrl + "producer"}
          className={({ isActive, isPending }) => {
            return isPending
              ? "bg-slate-600"
              : isActive
                ? "font-semibold underline underline-offset-2"
                : "";
          }}
        >
          생산자
        </NavLink>
        <NavLink
          to={configUrl + "brand"}
          className={({ isActive, isPending }) => {
            return isPending
              ? "bg-slate-600"
              : isActive
                ? "font-semibold underline underline-offset-2"
                : "";
          }}
        >
          브랜드
        </NavLink>
        <NavLink
          to={configUrl + "cardnews"}
          className={({ isActive, isPending }) => {
            return isPending
              ? "bg-slate-600"
              : isActive
                ? "font-semibold underline underline-offset-2"
                : "";
          }}
        >
          카드뉴스
        </NavLink>
      </nav>
      <Outlet />
    </div>
  );
}
