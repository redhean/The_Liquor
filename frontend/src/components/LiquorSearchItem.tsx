import { useNavigate } from "react-router-dom";

type LiqourSearchItemProps = {
  id: string;
  korean_name: string;
  english_name: string;
  alcohol: number;
  classifications: string;
  image_path: string;
};

interface LiqourSearchItemResponse {
  page: number;
  liquor_list: LiqourSearchItemProps[];
}

export default function LiqourSearchItem({
  data,
}: {
  data: LiqourSearchItemProps;
}) {
  const navigate = useNavigate();

  return (
    <div
      className="grid grid-cols-[7rem,_auto] gap-4 p-2 w-full h-fit rounded-md hover:bg-orange-100 cursor-pointer"
      onClick={() => {
        navigate(`/liquor/${data.id}`);
      }}
    >
      {/* 이미지 */}
      {data.image_path ? (
        <img
          src={data.image_path}
          alt="liqour"
          className="size-28 rounded-sm"
        />
      ) : (
        <div className="size-28 rounded-sm bg-gray-200 flex items-center justify-center">
          no image
        </div>
      )}
      {/* 정보 */}
      <div className="flex flex-col justify-center gap-2 py-1">
        <p className="text-xs text-slate-400">{data.classifications}</p>
        <div>
          <p className="text-lg font-bold">{data.korean_name}</p>
          <p className="text-sm text-slate-700 leading-3">
            {data.english_name}
          </p>
        </div>
        <p className="text-sm">Alc. {data.alcohol}%</p>
      </div>
    </div>
  );
}
