import AddItemButton from "@/components/AddItemButton";
import { NavLink, Outlet } from "react-router-dom";

export default function AdminMain() {
  const configUrl = "/admin/config/";

  return (
    <div className="px-10 py-2 space-y-2 mt-2">
      <AddItemButton />
      <nav className="flex flex-row gap-4 justify-center py-2 text-xl">
        <NavLink
          to={configUrl + "liquor"}
          className={({ isActive, isPending }) => {
            return isPending
              ? "bg-slate-600"
              : isActive
              ? "font-semibold underline underline-offset-4"
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
              ? "font-semibold underline underline-offset-4"
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
              ? "font-semibold underline underline-offset-4"
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
              ? "font-semibold underline underline-offset-4"
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
