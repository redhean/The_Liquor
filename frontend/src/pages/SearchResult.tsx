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
import { useLoaderData } from "react-router-dom";

export default function SearchResult() {
  const searchResult = useLoaderData();

  return (
    <div className="bg-slate-100 h-full">
      {/* <h1 className="text-3xl text-center">Search Result</h1> */}
      <div className="flex flex-row w-full bg-yellow-100">
        <SearchFilter />
        <div className="w-full space-y-2">
          <div className="flex flex-row w-full justify-between">
            {/* 국내 판매 여부 토글 */}
            <div className="flex flex-row gap-1 justify-center items-center">
              <Switch id="domestic" />
              <Label htmlFor="domestic" className="font-normal">
                국내 판매
              </Label>
            </div>
            {/* 필터링 */}
            <Select>
              <SelectTrigger className="w-[180px] focus:ring-offset-0 focus:ring-0">
                <SelectValue placeholder="최신순" />
              </SelectTrigger>
              <SelectContent>
                <SelectItem value="1">1</SelectItem>
                <SelectItem value="2">2</SelectItem>
                <SelectItem value="3">3</SelectItem>
                <SelectItem value="4">4</SelectItem>
                <SelectItem value="5">5</SelectItem>
              </SelectContent>
            </Select>
          </div>
          <div className="flex flex-row flex-wrap gap-2">
            {searchResult.data.liquor_list.map((item: any) => (
              <LiqourSearchItem key={item.id} data={item} />
            ))}
          </div>
        </div>
      </div>
    </div>
  );
}
