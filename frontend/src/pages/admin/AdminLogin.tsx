import AdminLoginForm from "@/components/AdminLoginForm";

export default function AdminLogin() {
  return (
    <div className="flex flex-col justify-center items-center bg-slate-200 h-3/4">
      {/* form으로 한다면 */}
      <div className="bg-slate-100 m-3 w-96">
        <h1 className="text-xl font-bold mb-2">관리자 로그인</h1>
        <AdminLoginForm />
      </div>
    </div>
  );
}
