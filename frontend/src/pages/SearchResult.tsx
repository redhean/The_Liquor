import LiqourSearchItem from "@/components/LiquorSearchItem";
import SearchFilter from "@/components/SearchFilter";
import {
  Select,
  SelectContent,
  SelectItem,
  SelectLabel,
  SelectTrigger,
  SelectValue,
} from "@/components/ui/select";
import { Switch } from "@/components/ui/switch";
import { Label } from "@/components/ui/label";
import { useLoaderData, useNavigate } from "react-router-dom";
import { useSelector } from "react-redux";
import { searchTermSelector } from "@/slices/searchSlice";
import { useState } from "react";

export default function SearchResult() {
  const searchResult = useLoaderData();
  const searchTerm = useSelector(searchTermSelector);
  const navigate = useNavigate();
  const [isDomeestic, setIsDomestic] = useState<boolean>(true);

  const handleDomesticCheckedChange = (checked: boolean) => {
    navigate(`/search?term=${searchTerm}&avail=${checked}`);
    setIsDomestic(!isDomeestic);
  };

  return (
    <div className="pt-24 bg-[var(--maincolor)] px-10">
      {/* <h1 className="text-3xl text-center">Search Result</h1> */}
      <div className="flex flex-row w-full gap-4">
        <SearchFilter searchTerm={searchTerm} />
        <div className="w-full space-y-2">
          <div className="flex flex-row w-full justify-between">
            {/* 국내 판매 여부 토글 */}
            <div className="flex flex-row gap-2 justify-center items-center">
              <Switch
                id="domestic"
                onCheckedChange={handleDomesticCheckedChange}
                checked={isDomeestic}
              />
              <Label htmlFor="domestic" className="font-normal">
                국내 판매
              </Label>
            </div>
            {/* 필터링 */}
            <Select>
              <SelectTrigger className="w-[180px] bg-white rounded-xl border-gray-300 focus:ring-offset-0 focus:ring-0">
                <SelectValue placeholder="최신순" />
              </SelectTrigger>
              <SelectContent className="border-0 rounded-xl bg-white">
                <SelectItem value="1">1</SelectItem>
                <SelectItem value="2">2</SelectItem>
                <SelectItem value="3">3</SelectItem>
                <SelectItem value="4">4</SelectItem>
                <SelectItem value="5">5</SelectItem>
              </SelectContent>
            </Select>
          </div>
          <div className="flex flex-row flex-wrap gap-2">
            {searchResult.data.liquor_list.length
              ? searchResult.data.liquor_list.map((item: any) => (
                  <LiqourSearchItem key={item.id} data={item} />
                ))
              : "검색 결과가 없습니다."}
          </div>
        </div>
      </div>
    </div>
  );
}
