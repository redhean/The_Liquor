import axios from 'axios';

// 검색 시 사용
// liquor/search?term=&class=&alc-min=&alc-max=&avail=&brand=&page=
export type SearchLiqourParams = {
    term: string;
    class?: number;
    alc_min?: number;
    alc_max?: number;
    avail?: boolean;
    brand?: number;
    page: number;
};

export async function searchLiqour({params}: {params: SearchLiqourParams}) {
    const url = import.meta.env.VITE_API_URL;
    console.log(url);
    return axios.get(`${url}/liquor/search`, {params});
}

// id 값으로 상세 정보 가져오기
export async function getLiqour({id}: {id: number}) {
    return axios.get(`${process.env.REACT_APP_API_URL}/liquor/${id}`);
}