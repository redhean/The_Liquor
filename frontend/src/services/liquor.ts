import axios from "axios";

const url = import.meta.env.VITE_API_URL;
// 검색 시 사용
// liquor/search?term=&class=&alc-min=&alc-max=&avail=&brand=&page=
export type SearchLiqourParams = {
  term: string;
  alcMin?: number;
  class?: string | undefined;
  alcMax?: number;
  avail?: boolean;
  brand?: string | undefined;
  page: number;
};

export async function searchLiqour({ params }: { params: SearchLiqourParams }) {
  // 만약 파라마터에 nan이 들어가면 제거
  if (isNaN(params.alcMin as number)) delete params.alcMin;
  if (isNaN(params.alcMax as number)) delete params.alcMax;
  if (params.class && params.class.length === 0) delete params.class;
  if (params.brand && params.brand.length === 0) delete params.brand;

  return axios.get(`${url}/liquor/search`, { params });
}

// id 값으로 상세 정보 가져오기
export async function getLiquor({ id }: { id: string}) {
  return axios.get(`${url}/liquor/${id}`); 
}
