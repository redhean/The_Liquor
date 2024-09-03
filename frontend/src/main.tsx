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
import SearchResult, {loader as searchLoader} from "./pages/SearchResult.tsx";
import LiqourDetail from "./pages/LiqourDetail.tsx";
import CardNewsMain from "./pages/CardNewsMain.tsx";
import CardNewsDetail from "./pages/CardNewsDetail.tsx";
import { searchLiqour } from "./services/liqour.ts";

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
        loader: ({request}) => {
          console.log(request);
          const url = new URL(request.url);
          const term = url.searchParams.get('term') ?? 'A';
          return searchLiqour({params: {term: term, page: 1}});
        },
        element: <SearchResult />,
      },
      {
        path: '/liquor/:idx',
        element: <LiqourDetail />,
      },
      {
        path: '/cardnews',
        element: <CardNewsMain />
      },
      {
        path: '/cardnews/:idx',
        element: <CardNewsDetail />
      }
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
    <RouterProvider router={router} />
  </StrictMode>,
);