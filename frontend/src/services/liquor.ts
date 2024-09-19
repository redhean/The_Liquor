import axios from "axios";

const url = import.meta.env.VITE_API_URL;
// 검색 시 사용
// liquor/search?term=&class=&alc-min=&alc-max=&avail=&brand=&page=
export interface SearchLiqourParams {
  term: string;
  alcMin?: string;
  alcMax?: string;
  class?: string | undefined;
  avail: string;
  brand?: string | undefined;
  page: string;
};

export async function searchLiquor({ params }: { params: SearchLiqourParams }) {
  return axios.get(`${url}/liquor/search`, { params });
}

// id 값으로 상세 정보 가져오기
export async function getLiquor({ id }: { id: string }) {
  return axios.get(`${url}/liquor/${id}`);
}

// url 값 param으로 변환
export function parseURLtoParams(url: string): SearchLiqourParams {
  // 값 추출(string, number);
  console.log("render")

  const requestURL = new URL(url).searchParams;
  const term = requestURL.get("term") || "";
  const alcMax = requestURL.get("alcMax") || "100";
  const alcMin = requestURL.get("alcMin") || "0";
  const avail = requestURL.get("avail") || "true";
  const classificationString = requestURL.get("class") || "";
  const brandStirng = requestURL.get("brand") || "";
  const page = requestURL.get("page") || "1";

  const params: SearchLiqourParams = {
    term,
    alcMin,
    alcMax,
    page,
    avail,
    brand: brandStirng,
    class: classificationString,
  };

  return params;
}
