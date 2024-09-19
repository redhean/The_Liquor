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
import { createSearchParams, useNavigate } from "react-router-dom";
import { parseURLtoParams, searchLiquor } from "@/services/liquor";
import { useEffect, useState } from "react";
import { useAppSelector } from "@/hooks";

export default function SearchResult() {
  const url = window.location.href;
  const navigate = useNavigate();
  const searchTerm = useAppSelector((state)=>state.search.term);
  const params = parseURLtoParams(url);

  const [checked, setChecked] = useState(true);
  const [response, setResponse] = useState();

  const handleCheckChange = (checked: boolean) => {
    navigate({
      pathname: "/search",
      search: createSearchParams({
        ...params,
        avail: checked ? "true" : "false",
      }).toString(),
    }),
      setChecked(checked);
  };

  useEffect(() => {
    searchLiquor({ params }).then((value) => {
      setResponse(value.data);
    });
  }, [checked, searchTerm]);

  return (
    <div className="pt-24 bg-[var(--maincolor)] px-10">
      {/* <h1 className="text-3xl text-center">Search Result</h1> */}
      <div className="flex flex-row w-full gap-4">
        <SearchFilter searchTerm={params.term} />
        <div className="w-full space-y-2">
          <div className="flex flex-row w-full justify-between">
            {/* 국내 판매 여부 토글 */}
            <div className="flex flex-row gap-2 justify-center items-center">
              <Switch
                id="domestic"
                onCheckedChange={handleCheckChange}
                checked={checked}
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
            {response?.liquor_list?.length
              ? response.liquor_list.map((item: any) => (
                  <LiqourSearchItem key={item.id} data={item} />
                ))
              : "검색 결과가 없습니다."}
          </div>
          <div id="target" className="h-1 bg-red-50" />
        </div>
      </div>
    </div>
  );
}
