import { defineConfig } from "vite";
import react from "@vitejs/plugin-react";
import path from "path";

const defaultConfig = {
  plugins: [react()],
  resolve: {
    alias: {
      "@": path.resolve(__dirname, "./src"),
    },
  },
};

export default defineConfig(({ command, mode }) => {
  if (command === "serve") {
    return {
      ...defaultConfig,
      server: {
        proxy: {
          "/api": {
            target: 'http://localhost:8080',
            changeOrigin: true,
            rewrite: (path) => path.replace(/^\/api/, ''),
          },
        },
      },
    };
  } else {
    return defaultConfig;
  }
});
