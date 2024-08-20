import { NavLink, Outlet } from "react-router-dom";

export default function AdminMain() {
  const configUrl = "/admin/config/";

  return (
    <div>
      <nav>
        <NavLink
          to={configUrl+'liquor'}
          className={({ isActive, isPending }) => {
            console.log(isActive, isPending);

            return isPending
              ? "bg-slate-600"
              : isActive
                ? "font-semibold underline"
                : "";
          }}
        >
          술
        </NavLink>
        <NavLink
          to={configUrl + "producer"}
          className={({ isActive, isPending }) => {
            console.log(isActive, isPending);

            return isPending
              ? "bg-slate-600"
              : isActive
                ? "font-semibold underline"
                : "bg-red-50";
          }}
        >
          생산자
        </NavLink>
        <NavLink
          to={configUrl + "brand"}
          className={({ isActive, isPending }) => {
            console.log(isActive, isPending);

            return isPending
              ? "bg-slate-600"
              : isActive
                ? "font-semibold underline"
                : "bg-red-50";
          }}
        >
          브랜드
        </NavLink>
        <NavLink
          to={configUrl + "cardnews"}
          className={({ isActive, isPending }) => {
            console.log(isActive, isPending);

            return isPending
              ? "bg-slate-600"
              : isActive
                ? "font-semibold underline"
                : "bg-red-50";
          }}
        >
          카드뉴스
        </NavLink>
      </nav>
      <Outlet />
    </div>
  );
}
