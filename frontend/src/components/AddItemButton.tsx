import { useState } from "react";
import { Button } from "./ui/button";
import { useNavigate } from "react-router-dom";

export default function AddItemButton() {
  const [visibleButtons, setVisibleButtons] = useState(false);
  const navigate = useNavigate();

  return (
    <div className="flex flex-row gap-4">
      <Button
        size="sm"
        variant={visibleButtons ? "outline" : "default"}
        onClick={() => setVisibleButtons(!visibleButtons)}
      >

        추가
      </Button>
      {visibleButtons && (
        <div className="space-x-2">
          <Button size="sm" type="button" onClick={()=>{navigate('/admin/createliquor')}}>술</Button>
          <Button size="sm" type="button" onClick={()=>{navigate('/admin/createproducer')}}>생산자</Button>
          <Button size="sm" type="button" onClick={()=>{navigate('/admin/createbrand')}}>브랜드</Button>
          <Button size="sm" type="button" onClick={()=>{navigate('/admin/createcardnews')}}>카드뉴스</Button>
        </div>
      )}
    </div>
  );
}
