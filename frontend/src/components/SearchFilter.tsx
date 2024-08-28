import classfications from "@/assets/classfication.json";
import { Checkbox } from "./ui/checkbox";
import { useEffect, useState } from "react";
import { BiChevronDown, BiChevronUp } from "react-icons/bi";

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
  }, [parentChecked]);

  const handleChildChecked = (childId: number, childChecked: boolean) => {
    if (!childChecked) {
      setChecked(false);
    }
    onCheckChange(childId, childChecked);
  };

  return (
    <div key={item.id}>
      <div className="flex items-center justify-between bg-red-50 p-1">
        <div className="flex gap-2 items-center">
          <Checkbox
            id={item.id.toString()}
            checked={checked}
            onCheckedChange={(check) => {
              setChecked(!checked);
              onCheckChange(item.id, check === true);
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

export default function SearchFilter() {
  const handleCheckChange = (id: number, checked: boolean) => {
    console.log(
      `Checkbox with id ${id} is now ${checked ? "checked" : "unchecked"}`,
    );
  };

  return (
    <div className="flex flex-col w-64 m-4 text-sm">
      {classfications.classifications.map((item: classficationType) => (
        <ClassificationItem
          key={item.id}
          item={item}
          onCheckChange={handleCheckChange}
        />
      ))}
    </div>
  );
}
