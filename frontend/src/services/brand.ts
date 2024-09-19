import axios from "axios";
const url = import.meta.env.VITE_API_URL;

// 술 상세 페이지에서 브랜드 불러오기
export async function getLiquorBrand({ id }: { id: string }) {
  return axios.get(`${url}/brand/${id}`);
}
