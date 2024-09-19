import classfications from "@/assets/classfication.json";
import { Checkbox } from "./ui/checkbox";
import { useEffect, useState } from "react";
import { BiChevronDown, BiChevronUp } from "react-icons/bi";
import { useNavigate } from "react-router-dom";
import { useAppSelector } from "@/hooks";

type classficationType = {
  id: number;
  name: string;
  parent_id?: number | null;
  children?: classficationType[];
};

function ClassificationItem({
  item,
  parentChecked = false,
  onCheckChange,
}: {
  item: classficationType;
  parentChecked?: boolean;
  onCheckChange: (childId: number, childChecked: boolean) => void;
}) {
  const [visible, setVisible] = useState(false);
  const [checked, setChecked] = useState(false);

  useEffect(() => {
    setChecked(parentChecked);
    onCheckChange(item.id, parentChecked);
  }, [parentChecked]);

  const handleChildChecked = (childId: number, childChecked: boolean) => {
    if (!childChecked) {
      setChecked(false);
    }
    onCheckChange(childId, childChecked);
  };

  return (
    <div key={item.id}>
      <div className="flex items-center justify-between p-1">
        <div className="flex gap-2 items-center">
          <Checkbox
            id={item.id.toString()}
            className="bg-white border-[var(--accent)]"
            checked={checked}
            onCheckedChange={(check) => {
              setChecked(!checked);
              onCheckChange(item.id, !checked);
            }}
          />
          <label htmlFor={item.id.toString()}>{item.name}</label>
        </div>
        {(item.children?.length ?? 0) > 0 && (
          <button className="" onClick={() => setVisible(!visible)}>
            {visible ? <BiChevronUp /> : <BiChevronDown />}
          </button>
        )}
      </div>
      {item.children && visible && (
        <div className="pl-4">
          {item.children.map((child) => (
            <ClassificationItem
              key={child.id}
              item={child}
              parentChecked={checked}
              onCheckChange={handleChildChecked}
            />
          ))}
        </div>
      )}
    </div>
  );
}

export default function SearchFilter({ searchTerm }: { searchTerm: string }) {
  const navigate = useNavigate();
  const [clickedClassifications, setClickedClassifications] = useState<
    number[]
  >([]);

  const handleCheckChange = (id: number, checked: boolean) => {
    if (checked) {
      const classSet = new Set([...clickedClassifications, id]);
      setClickedClassifications([...classSet].sort((a, b) => a - b));
    } else {
      setClickedClassifications(
        clickedClassifications.filter((item) => item !== id),
      );
    }
  };

  // useEffect(() => {
  //   navigate(
  //     `/search?term=${searchTerm}&class=${clickedClassifications.join(",")}`,
  //   );
  // }, [clickedClassifications]);

  return (
    <section className="flex flex-col w-72 px-2 py-4 gap-2">
      {/* <span className="text-xl">상세 검색</span> */}
      <div className="text-sm px-1 py-2 h-fit bg-white rounded-xl">
        <span className="font-base text-base flex justify-center">주종</span>
        <hr className="border-black border-t-1 mb-2"/>
        {classfications.classifications.map((item: classficationType) => (
          <ClassificationItem
            key={item.id}
            item={item}
            onCheckChange={handleCheckChange}
          />
        ))}
      </div>
    </section>
  );
}
