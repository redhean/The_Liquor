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

const dummyData = {
  id: "1",
  producer: "제조사",
  brand: "브랜드",
  classfications: "증류주",
  korean_name: "소주",
  english_name: "Soju",
  count: "대한민국",
  alcohol: 20,
  aged: "1년",
  price: 10000,
  ibu: 1,
  is_domestic_sale: true,
  description:
    "국회의 정기회는 법률이 정하는 바에 의하여 매년 1회 집회되며, 국회의 임시회는 대통령 또는 국회재적의원 4분의 1 이상의 요구에 의하여 집회된다. 대통령의 임기는 5년으로 하며, 중임할 수 없다. 각급 선거관리위원회의 조직·직무범위 기타 필요한 사항은 법률로 정한다. 대통령은 내란 또는 외환의 죄를 범한 경우를 제외하고는 재직중 형사상의 소추를 받지 아니한다.",
  updated_at: "2021-09-15",
  adv: "광고",
};

export default function LiqourDetail({
  data = dummyData,
}: {
  data?: LiqourDetailRespone;
}) {

  const itemTitleClassName = "text-sm text-slate-800";

  return (
    <div className="space-y-12 p-4 md:p-8">
      {/* 브랜드 서치 이동*/}
      <div className="flex flex-col items-center justify-center cursor-pointer hover:text-gray-400">
        <img
          src="https://picsum.photos/300/200"
          alt="brand"
          className="w-32 rounded-sm"
        />
        <p className="text-base font-semibold underline">{data.brand}</p>
        <p className="text-sm">{data.classfications}</p>
      </div>
      {/* 술 정보(상세) */}
      <div className="flex flex-col md:grid md:grid-cols-[1fr,_2fr] gap-4">
        {/* 이미지 */}
        <div>
          <img
            src="https://picsum.photos/300"
            alt="liqour"
            className="w-full rounded-md border border-gray-300"
          />
        </div>
        {/* 이미지 제외 설명 */}
        <div className="flex flex-col gap-4 p-4">
          {/* 제목 */}
          <div>
            <p className="text-2xl font-bold">{data.korean_name}</p>
            <p className="text-lg">{data.english_name}</p>
          </div>
          {/* 설명 */}
          <div>
            <p className="text-base">{data.description}</p>
          </div>
          {/* 주종 */}
          <div>
            <p className={itemTitleClassName}>주종</p>
            <p className="text-base">{data.classfications}</p>
          </div>
          {/* 가격 */}
          <div>
            <p className={itemTitleClassName}>가격</p>
            <p className="text-base">{data.price}</p>
          </div>
          {/* 도수 */}
          <div>
            <p className={itemTitleClassName}>도수</p>
            <p className="text-base">Alc. {data.alcohol}%</p>
          </div>
          {/* 생산자 */}
          <div>
            <p className={itemTitleClassName}>생산자</p>
            <p className="text-base">{data.producer}</p>
          </div>
          {/* 브랜드 */}
          <div>
            <p className={itemTitleClassName}>브랜드</p>
            <p className="text-base">{data.brand}</p>
          </div>
          {/* 생산국 */}
          <div>
            <p className={itemTitleClassName}>생산국</p>
            <p className="text-base">{data.count}</p>
          </div>
          {/* IBU */}
          <div>
            <p className={itemTitleClassName}>IBU</p>
            <p className="text-base">{data.ibu}</p>
          </div>
          {/* 숙성년도 */}
          <div>
            <p className={itemTitleClassName}>숙성년도</p>
            <p className="text-base">{data.aged}</p>
          </div>
        </div>
      </div>
    </div>
  );
}
