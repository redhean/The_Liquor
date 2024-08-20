import AdminLoginForm from "@/components/AdminLoginForm";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";

export default function AdminLogin
() {
  return (
    <div className="flex flex-col justify-center items-center bg-slate-200">
      {/* login */}
      <div className="grid grid-cols-[100px,_1fr] grid-rows-2 w-96 gap-4 bg-slate-100 m-3">
        <h1 className="text-lg col-span-2">관리자 로그인</h1>
        <div className="flex justify-end items-center text-sm">아이디</div>
        <Input type="text" placeholder="" />
        <div className="flex justify-end items-center text-sm">비밀번호</div>
        <Input type="password" placeholder="" />
        <Button className="col-span-2">로그인</Button>
      </div>
      {/* form으로 한다면 */}
      <div className="bg-slate-100 m-3 w-96">
        <AdminLoginForm />
      </div>
    </div>
  );
}
