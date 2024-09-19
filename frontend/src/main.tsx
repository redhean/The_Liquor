import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import App from "./App.tsx";
import AdminLogin from "./pages/admin/AdminLogin.tsx";
import "./index.css";
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import AdminMain from "./pages/admin/AdminMain.tsx";
import AdminOutlet from "./pages/admin/AdminOutlet.tsx";
import AdminSearch from "./pages/admin/AdminSearch.tsx";
import CreateLiquor from "./pages/admin/CreateLiquor.tsx";
import CreateBrand from "./pages/admin/CreateBrand.tsx";
import CreateProducer from "./pages/admin/CreateProducer.tsx";
import CreateCardnews from "./pages/admin/CreateCardnews.tsx";
import MainSearch from "./pages/MainSearch.tsx";
import SearchResult from "./pages/SearchResult.tsx";
import LiqourDetail from "./pages/LiqourDetail.tsx";
import CardNewsMain from "./pages/CardNewsMain.tsx";
import CardNewsDetail from "./pages/CardNewsDetail.tsx";
import { getLiquor, searchLiqour } from "./services/liquor.ts";
import { getLiquorBrand } from "./services/brand.ts";
import { store } from "@/store.ts";
import { Provider } from "react-redux";

const router = createBrowserRouter([
  {
    path: "/",
    element: <App />,
    children: [
      {
        path: "/admin",
        element: <AdminOutlet />,
        children: [
          { path: "login", element: <AdminLogin />, index: true },
          {
            path: "config",
            element: <AdminMain />,
            children: [
              {
                path: "liquor",
                index: true,
                element: <AdminSearch searchType="liquor" />,
              },
              {
                path: "brand",
                element: <AdminSearch searchType="brand" />,
              },
              {
                path: "producer",
                element: <AdminSearch searchType="producer" />,
              },
              {
                path: "cardnews",
                element: <AdminSearch searchType="cardnews" />,
              },
            ],
          },
          {
            path: "createliquor",
            element: <CreateLiquor />,
          },
          {
            path: "createbrand",
            element: <CreateBrand />,
          },
          {
            path: "createproducer",
            element: <CreateProducer />,
          },
          {
            path: "createcardnews",
            element: <CreateCardnews />,
          },
        ],
      },
      {
        path: "/search",
        loader: ({ request }) => {
          const url = new URL(request.url);
          const term = url.searchParams.get("term") || "";
          const classId = url.searchParams.get("class") ?? undefined;
          const alcMin = url.searchParams.get("alc-min")
            ? parseFloat(url.searchParams.get("alc-min") || "")
            : 0;
          const alcMax = url.searchParams.get("alc-max")
            ? parseFloat(url.searchParams.get("alc-max") || "")
            : 100;
          const avail = url.searchParams.get("avail") === "true";
          const brand = url.searchParams.get("brand") ?? undefined;
          const page = url.searchParams.get("page")
            ? parseInt(url.searchParams.get("page") || "")
            : 1;

          return searchLiqour({
            params: {
              term,
              class: classId,
              alcMin,
              alcMax,
              avail: true,
              brand,
              page,
            },
          });
        },
        element: <SearchResult />,
      },
      {
        path: "/liquor/:idx",
        id: "liquor",
        loader: async ({ request, params }) => {
          // const liquorId = params.idx;
          const liquor = await getLiquor({ id: params.idx });
          const brand = await getLiquorBrand({ id: params.idx });

          return {
            liquor: liquor.data,
            brand: brand.data,
          };
        },
        element: <LiqourDetail />,
      },
      {
        path: "/cardnews",
        element: <CardNewsMain />,
      },
      {
        path: "/cardnews/:idx",
        element: <CardNewsDetail />,
      },
    ],
  },
  {
    path: "/",
    index: true,
    element: <MainSearch />,
  },
]);

createRoot(document.getElementById("root")!).render(
  <StrictMode>
    <Provider store={store}>
      <RouterProvider router={router} />
    </Provider>
  </StrictMode>,
);
