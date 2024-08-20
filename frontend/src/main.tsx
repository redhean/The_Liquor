import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import App from "./App.tsx";
import AdminLogin from "./pages/admin/AdminLogin.tsx";
import "./index.css";
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import AdminMain from "./pages/admin/AdminMain.tsx";
import AdminOutlet from "./pages/admin/AdminOutlet.tsx";

const router = createBrowserRouter([
  {
    path: "/",
    element: <App />,
    children: [
      {
        path: "/admin",
        element: <AdminOutlet />,
        children: [
          { path: "login", element: <AdminLogin /> },
          {
            path: "config",
            element: <AdminMain />,
            children: [
              {
                index: true,
                element: <div>술</div>,
              },
              {
                path: "brand",
                element: <div>브랜드</div>,
              },
              {
                path: "producer",
                element: <div>생산자</div>,
              },
              {
                path: "cardnews",
                element: <div>카드뉴스</div>,
              },
            ],
          },
        ],
      },
    ],
  },
]);

createRoot(document.getElementById("root")!).render(
  <StrictMode>
    <RouterProvider router={router} />
  </StrictMode>,
);
