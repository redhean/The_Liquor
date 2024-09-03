import { Link } from "react-router-dom";
import { AiOutlineSearch } from "react-icons/ai";
import { Input } from "./ui/input";

export default function Topbar() {
  const SearchBar = () => {
    return (
      <div className="flex flex-row items-center px-2 border-b-2 border-black bg-white bg-opacity-30 w-1/2">
        <Input type="text" placeholder="검색어를 입력하세요." className="text-sm rounded-none border-0 focus-visible:ring-0 focus-visible:ring-offset-0 bg-transparent shadow-none" />
        <AiOutlineSearch size={24}/>
      </div>
    );
  };

  return (
    <div className="flex gap-8 bg-slate-300 h-auto justify-between items-center p-4">
      <div>Logo</div>
      <SearchBar />
      <div className="flex gap-4">
        <Link to="/">Home</Link>
        <Link to="/cardnews">Guide</Link>
        <Link to="/admin">Admin</Link>
      </div>
    </div>
  );
}
