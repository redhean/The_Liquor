import { Navigation, Pagination, Keyboard } from "swiper/modules";
import { Swiper, SwiperSlide } from "swiper/react";

import "swiper/css";
import "swiper/css/navigation";
import "swiper/css/pagination";
import "swiper/css/zoom";

import "./swiperStyle.css";
import { useEffect, useState } from "react";

// {
// ”classifications”:,
// ”title”:,
// ”image_count”:,
// ”image_path”:[”path”, “path”],
// }
type CardNewsDetailProps = {
  classifications: string;
  title: string;
  image_count: number;
  image_path: string[];
};

const dummyData: CardNewsDetailProps = {
  classifications: "카드뉴스 > 요기",
  title: "title",
  image_count: 2,
  image_path: [
    "https://picsum.photos/300",
    "https://picsum.photos/300",
    "https://picsum.photos/300",
    "https://picsum.photos/300",
  ],
};

export default function CardNewsDetail({
  item = dummyData,
}: {
  item?: CardNewsDetailProps;
}) {
  const [isImgFull, setIsImgFull] = useState(false);
  const handleImgClick = () => {
    console.log("img click");
    setIsImgFull(!isImgFull);
  };

  const handleEscPressed = (e: KeyboardEvent) => {
    if (e.key === "Escape") {
      setIsImgFull(false);
    }
  };

  useEffect(() => {
    window.addEventListener("keydown", handleEscPressed);

    return () => {
      window.removeEventListener("keydown", handleEscPressed);
    };
  }, []);

  return (
    <div className={"lg:mx-64 md:mx-32 my-2 mx-2 pt-24"}>
      {isImgFull && (
        <div className="fixed top-0 left-0 w-screen bg-slate-500 h-screen z-2 bg-opacity-30 backdrop-blur"></div>
      )}
      <p>{item.classifications}</p>
      <h1 className="text-3xl font-semibold">{item.title}</h1>
      <Swiper
        // install Swiper modules
        className={isImgFull ? "full-swiper" : ""}
        style={{
          "--swiper-navigation-size": isImgFull ? "44px" : "32px",
          "--swiper-navigation-color": "black",
          "--swiper-navigation-sides-offset": isImgFull ? "128px" : "8px",
          "--swiper-background-color": "transparent",
          cursor: "pointer",
        }}
        modules={[Navigation, Pagination, Keyboard]}
        centeredSlides={true}
        slidesPerView={1}
        navigation={true}
        pagination={{
          type: "fraction",
        }}
        keyboard={{ enabled: true }}
        scrollbar={{ draggable: true }}
        // onSwiper={(swiper) => console.log(swiper)}
        // onSlideChange={() => console.log("slide change")}
      >
        {item.image_path.map((path, index) => (
          <SwiperSlide key={index} onClick={handleImgClick}>
            <img
              className={`m-8 size-96 rounded-sm ${isImgFull ? "md:w-[36rem] md:h-[36rem]" : ""}`}
              src={path}
              alt="card-news"
            />
          </SwiperSlide>
        ))}
      </Swiper>
    </div>
  );
}
