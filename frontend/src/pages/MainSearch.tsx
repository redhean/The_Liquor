import { Input } from "@/components/ui/input";
import { AiOutlineSearch } from "react-icons/ai";
import { useNavigate } from "react-router-dom";

export default function MainSearch() {
    const navigate = useNavigate();

  return (
    <div className="bg-slate-100 flex flex-col gap-16 h-2/3 justify-center items-center">
      <h1 className="text-3xl">Search Logo_The Liquor</h1>
      <div className="flex flex-row justify-center items-center w-8/12 bg-white border-b-2 border-transparent focus-within:border-slate-400 px-2">
        <Input
          type="search"
          className="text-base h-12 rounded-none border-0 focus-visible:ring-0 focus-visible:ring-offset-0 bg-transparent shadow-none"
          placeholder="Search"
        />
        <AiOutlineSearch className="size-8 p-1 cursor-pointer" onClick={()=>{navigate('/search?')}}/>
      </div>
    </div>
  );
}
