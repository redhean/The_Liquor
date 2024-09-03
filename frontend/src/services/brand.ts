import axios from 'axios';

// 술 상세 페이지에서 브랜드 불러오기
export function getLiqourBrand({id}: {id: number}) {
    const response =  axios.get(`${process.env.}/brand/${id}`).then((res) => {
        console.log(res); 
    };
}