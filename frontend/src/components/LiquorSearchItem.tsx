import {useNavigate } from "react-router-dom";

type LiqourSearchItemProps = {
  id: string;
  korean_name: string;
  english_name: string;
  alc: number;
  classification: string;
  image_path: string;
};

interface LiqourSearchItemResponse {
  page: number;
  liquor_list: LiqourSearchItemProps[];
}

const dummyData: LiqourSearchItemResponse = {
  page: 1,
  liquor_list: [
    {
      id: "1",
      korean_name: "소주",
      english_name: "Soju",
      alc: 20,
      classification: "1차 분류 > 2차 분류",
      image_path: "https://picsum.photos/300",
    },
  ],
};

export default function LiqourSearchItem({
  data = dummyData.liquor_list[0],
}: {
  data: LiqourSearchItemProps;
}) {
  const navigate = useNavigate();

  return (
    <div
      className="grid grid-cols-[7rem,_auto] gap-4 bg-slate-200 p-2 w-full h-fit hover:bg-slate-300 cursor-pointer"s
      onClick={() => {
        navigate(`/liquor/${data.id}`);
      }}
    >
      {/* 이미지 */}
      <img src={data.image_path} alt="liqour" className="size-28 rounded-sm" />
      {/* 정보 */}
      <div className="flex flex-col justify-center gap-2 py-1">
        <p className="text-xs text-slate-400">{data.classification}</p>
        <div>
          <p className="text-lg font-bold">{data.korean_name}</p>
          <p className="text-sm text-slate-700 leading-3">
            {data.english_name}
          </p>
        </div>
        <p className="text-sm">Alc. {data.alc}%</p>
      </div>
    </div>
  );
}
