import { useNavigate } from "react-router-dom";
import classfications from "@/assets/classfication.json";
import { BiChevronDown, BiChevronUp } from "react-icons/bi";
import { useState } from "react";
import { Button } from "@/components/ui/button";
import { AiOutlineSearch } from "react-icons/ai";

type CardNewsItemProps = {
  id: number;
  title: string;
  firstImagePath: string;
  updateAt: string;
};

const dummyData: CardNewsItemProps[] = [
  {
    id: 1,
    title: "title",
    firstImagePath: "https://picsum.photos/300",
    updateAt: "2021-09-15",
  },
  {
    id: 2,
    title: "title",
    firstImagePath: "https://picsum.photos/300",
    updateAt: "2021-09-15",
  },
  {
    id: 3,
    title: "title",
    firstImagePath: "https://picsum.photos/300",
    updateAt: "2021-09-15",
  },
  {
    id: 4,
    title: "title",
    firstImagePath: "https://picsum.photos/300",
    updateAt: "2021-09-15",
  },
  {
    id: 5,
    title: "title",
    firstImagePath: "https://picsum.photos/300",
    updateAt: "2021-09-15",
  },
  {
    id: 6,
    title: "title",
    firstImagePath: "https://picsum.photos/300",
    updateAt: "2021-09-15",
  },
  {
    id: 7,
    title: "title",
    firstImagePath: "https://picsum.photos/300",
    updateAt: "2021-09-15",
  },
  {
    id: 8,
    title: "title",
    firstImagePath: "https://picsum.photos/300",
    updateAt: "2021-09-15",
  },
  {
    id: 9,
    title: "title",
    firstImagePath: "https://picsum.photos/300",
    updateAt: "2021-09-15",
  },
  {
    id: 10,
    title: "title",
    firstImagePath: "https://picsum.photos/300",
    updateAt: "2021-09-15",
  },
];

export default function CardNewsMain() {
  const navigate = useNavigate();

  const SearchBar = () => {
    return (
      <div className="flex flex-row justify-between items-center w-3/5 m-4 border border-[var(--accent)] bg-white px-4 rounded-full">
        <input
          type="search"
          placeholder="검색어를 입력하세요"
          className="w-full p-2 focus:outline-none"
        />
        <AiOutlineSearch
          className="size-8 p-1 cursor-pointer fill-[var(--accent)]"
          onClick={() => {
            navigate("/cardnews?");
          }}
        />
      </div>
    );
  };

  const SideMenu = ({ item }: any) => {
    const [childVisible, setChildVisible] = useState(false);

    return (
      <div key={item.id}>
        <div className="w-full p-2 flex flex-row justify-between">
          <p>{item.name}</p>
          {item.children?.length > 0 && (
            <button
              onClick={() => {
                setChildVisible(!childVisible);
              }}
            >
              {childVisible ? <BiChevronDown /> : <BiChevronUp />}
            </button>
          )}
        </div>
        {childVisible &&
          item.children?.map((child: any) => (
            <div key={child.id} className="w-full p-2 pl-4">
              <p>{child.name}</p>
            </div>
          ))}
      </div>
    );
  };

  const CardNewsItem = ({
    id,
    title,
    firstImagePath,
    updateAt,
  }: CardNewsItemProps) => {
    return (
      <div
        className="flex flex-col gap-2 hover:bg-orange-200 rounded-md cursor-pointe p-2 w-52 h-64 overflow-clip"
        onClick={() => {
          navigate(`/cardnews/${id}`);
        }}
      >
        {/* 이미지 */}
        <img src={firstImagePath} alt="liqour" className="size-48 rounded-sm" />
        {/* 정보 */}
        <p className="text-base font-semibold">{title}</p>
      </div>
    );
  };

  return (
    <div className="flex flex-col justify-center items-center mx-16 pt-24">
      {/* <h1 className="self-start text-2xl py-2">카드 뉴스</h1> */}
      {/* <SearchBar /> */}
      <div className="grid grid-cols-[14rem,_auto] m-4 gap-8 w-fit">
        {
          <div className="text-sm px-1 py-2 h-fit bg-white rounded-xl">
            {classfications.classifications.map((item) => (
              <SideMenu item={item} />
            ))}
          </div>
        }
        <div className="flex flex-col w-fit xl:grid-cols-4 lg:grid-cols-3 md:grid md:grid-cols-2 h-fit gap-2">
          {dummyData.map((item) => (
            <CardNewsItem {...item} />
          ))}
        </div>
      </div>
    </div>
  );
}
