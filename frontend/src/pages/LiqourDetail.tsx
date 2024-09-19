// {
//     ”id”:,
//     ”producer”:,
//     ”brand”:,
//     ”classifications”:,
//     ”korean_name”:,
//     ”english_name”:,
//     ”count”:,
//     ”alcohol”:,
//     ”aged”:,
//     ”price”:,
//     ”ibu”:,
//     ”is_domestic_sale”:,
//     ”description”:,
//     ”updated_at”:,
//     ”adv”:,
//     }

import { useLoaderData, useNavigate } from "react-router-dom";

type LiqourDetailRespone = {
  id: string;
  producer: string;
  brand: string;
  classfications: string;
  korean_name: string;
  english_name: string;
  count: string;
  alcohol: number;
  aged: string;
  price: number;
  ibu: number;
  is_domestic_sale: boolean;
  description: string;
  updated_at: string;
  adv: string;
};

export default function LiqourDetail() {
  const { liquor, brand } = useLoaderData();
  const naviagte = useNavigate();
  const itemTitleClassName = "text-sm text-slate-800";

  const handleBrandClick = () => {
    naviagte(`/search?brand=${brand.id}`);
  };

  return (
    <article className="space-y-12 p-4 md:px-8 pt-24">
      {/* 브랜드 서치 이동*/}
      <div
        className="flex flex-col items-center justify-center cursor-pointer hover:text-gray-400"
        onClick={handleBrandClick}
      >
        {brand.image_path ? (
          <img src={brand.image_path} alt="brand" className="w-32 rounded-sm" />
        ) : (
          <div className="w-32 h-16 rounded-sm text-center bg-gray-200 flex items-center justify-center">
            no image
          </div>
        )}
        <p className="text-base font-semibold underline">{brand.name}</p>
        <p className="text-sm">{brand.classification_name}</p>
      </div>
      {/* 술 정보(상세) */}
      <div className="flex flex-col md:grid md:grid-cols-[1fr,_2fr] gap-4">
        {/* 이미지 */}
        <div>
          {liquor.image_path ? (
            <img
              src={liquor.image_path}
              alt="liqour"
              className="w-full rounded-md border border-gray-300"
            />
          ) : (
            <div className="w-full h-64 rounded-md border border-gray-300 bg-gray-200 flex items-center justify-center">
              no image
            </div>
          )}
        </div>
        {/* 이미지 제외 설명 */}
        <div className="flex flex-col gap-4 p-4">
          {/* 제목 */}
          <div>
            <p className="text-2xl font-bold">{liquor.korean_name}</p>
            <p className="text-lg">{liquor.english_name}</p>
          </div>
          {/* 설명 */}
          <div>
            <p className="text-base">{liquor.description}</p>
          </div>
          {/* 주종 */}
          <div>
            <p className={itemTitleClassName}>주종</p>
            <p className="text-base">{liquor.classifications}</p>
          </div>
          {/* 가격 */}
          <div>
            <p className={itemTitleClassName}>가격</p>
            <p className="text-base">{liquor.price}</p>
          </div>
          {/* 도수 */}
          <div>
            <p className={itemTitleClassName}>도수</p>
            <p className="text-base">Alc. {liquor.alcohol}%</p>
          </div>
          {/* 생산자 */}
          <div>
            <p className={itemTitleClassName}>생산자</p>
            <p className="text-base">{liquor.producer}</p>
          </div>
          {/* 브랜드 */}
          <div>
            <p className={itemTitleClassName}>브랜드</p>
            <p className="text-base">{liquor.brand}</p>
          </div>
          {/* 생산국 */}
          <div>
            <p className={itemTitleClassName}>생산국</p>
            <p className="text-base">{liquor.count}</p>
          </div>
          {/* IBU */}
          <div>
            <p className={itemTitleClassName}>IBU</p>
            <p className="text-base">{liquor.ibu}</p>
          </div>
          {/* 숙성년도 */}
          <div>
            <p className={itemTitleClassName}>숙성년도</p>
            <p className="text-base">{liquor.aged}</p>
          </div>
        </div>
      </div>
    </article>
  );
}
