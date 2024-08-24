import { zodResolver } from "@hookform/resolvers/zod";
import { useForm } from "react-hook-form";
import { z } from "zod";

import { Button } from "@/components/ui/button";
import {
  Form,
  FormControl,
  FormField,
  FormItem,
  FormLabel,
  FormMessage,
} from "@/components/ui/form";
import { Input } from "@/components/ui/input";
import { useNavigate } from "react-router-dom";
import { AiOutlineUpload } from "react-icons/ai";
import {
  Select,
  SelectContent,
  SelectItem,
  SelectTrigger,
  SelectValue,
} from "@/components/ui/select";

const MAX_FILE_SIZE = 5000000;
const ACCEPTED_IMAGE_TYPES = [
  "image/jpeg",
  "image/jpg",
  "image/png",
  "image/webp",
];

const formSchema = z.object({
  image: z
    .instanceof(FileList)
    .refine((file) => file?.length > 0, "파일을 첨부해주세요.")
    .refine(
      (file) => file[0]?.size <= MAX_FILE_SIZE,
      "파일 크기는 최대 5MB입니다."
    )
    .refine(
      (file) => ACCEPTED_IMAGE_TYPES.includes(file[0]?.type),
      "지원하지 않는 파일 형식입니다."
    ),
  producer: z.string(),
  name: z.string(),
  classifications: z.string(),
  color: z.string(),
});

export default function BrandForm() {
  const navigate = useNavigate();

  const form = useForm<z.infer<typeof formSchema>>({
    resolver: zodResolver(formSchema),
    defaultValues: {
      producer: "0",
      classifications: "0",
      name: "",
      color: "#000000",
    },
  });
  const fileRef = form.register("image");

  function onSubmit(values: z.infer<typeof formSchema>) {
    // 화면 내비게이션
    console.log(values);

    // api 호출
    alert("추가되었습니다.");

    // 나중에는 다른 곳으로 내비게이션
    navigate("../config");
  }

  return (
    <Form {...form}>
      <form onSubmit={form.handleSubmit(onSubmit)} className="">
        <div className="flex flex-row justify-between p-8">
          <h1 className="text-3xl font-semibold">
            브랜드 추가{" "}
            <span className="text-xl">{form.getValues("name")}</span>
          </h1>
          <div className="space-x-2">
            <Button
              className="w-24"
              variant="destructive"
              size="sm"
              type="button"
            >
              취소
            </Button>
            <Button className="w-24" size="sm" type="submit">
              추가
            </Button>
          </div>
        </div>
        <div className="grid grid-cols-[minmax(24rem,_1fr)_2fr] gap-4 bg-slate-100">
          {/* 이미지 */}
          <div>
            <FormField
              control={form.control}
              name="image"
              render={({ field }) => (
                <FormItem>
                  <FormLabel>
                    {form.getValues("image")?.length > 0 ? (
                      <img
                        className="w-full object-contain bg-slate-400 border border-slate-200 rounded-lg"
                        src={URL.createObjectURL(form.getValues("image")[0])}
                      />
                    ) : (
                      <div className="size-auto min-h-80 bg-slate-400 h-[24rem] flex justify-center items-center rounded-lg">
                        <AiOutlineUpload className="size-8" />
                      </div>
                    )}
                  </FormLabel>
                  <FormControl>
                    <Input
                      className="hidden"
                      type="file"
                      {...fileRef}
                      accept="image/*"
                    />
                  </FormControl>
                  <FormMessage />
                </FormItem>
              )}
            />
          </div>
          {/* 이미지 제외 폼 입력값 */}
          <div className="w-full space-y-2">
            {/* 국문이름 */}
            <FormField
              control={form.control}
              name="name"
              render={({ field }) => (
                <FormItem>
                  <FormLabel>브랜드 이름</FormLabel>
                  <FormControl>
                    <Input placeholder="" {...field} />
                  </FormControl>
                  <FormMessage />
                </FormItem>
              )}
            />
            {/* 주종 드롭다운 */}
            <FormField
              control={form.control}
              name="classifications"
              render={({ field }) => (
                <FormItem>
                  <FormLabel>1차 주종</FormLabel>
                  <Select>
                    <FormControl>
                      <SelectTrigger>
                        <SelectValue placeholder="1차 주종" />
                      </SelectTrigger>
                    </FormControl>
                    <SelectContent>
                      <SelectItem value="1">ㅎㅎ</SelectItem>
                      <SelectItem value="2">ㅋㅋ</SelectItem>
                      <SelectItem value="3">ㅜㅜ</SelectItem>
                    </SelectContent>
                  </Select>
                  <FormMessage />
                </FormItem>
              )}
            />
            {/* 생산자 드롭다운 */}
            <FormField
              control={form.control}
              name="producer"
              render={({ field }) => (
                <FormItem>
                  <FormLabel>생산자</FormLabel>
                  <Select onValueChange={field.onChange}>
                    <FormControl>
                      <SelectTrigger>
                        <SelectValue placeholder="생산자" />
                      </SelectTrigger>
                    </FormControl>
                    <SelectContent>
                      <SelectItem value="1">ㅎㅎ</SelectItem>
                      <SelectItem value="2">ㅋㅋ</SelectItem>
                      <SelectItem value="3">ㅜㅜ</SelectItem>
                    </SelectContent>
                  </Select>
                </FormItem>
              )}
            />
            {/* 브랜드 색상 */}
            <FormField
              control={form.control}
              name="color"
              render={({ field }) => (
                <FormItem>
                  <FormLabel>브랜드 색상</FormLabel>
                  <div
                    className="font-medium h-10 flex flex-row items-center gap-2 m-0 p-0border border-input rounded-md px-3 py-2 bg-white focus-within:outline-none focus-within:ring-2 focus-withibn:ring-ring focus-within:ring-offset-2 focus-within:ring-black"
                    tabIndex={0}
                  >
                    <FormControl>
                      <Input
                        className="h-full w-10 border-0 m-0 p-0"
                        type="color"
                        placeholder=""
                        {...field}
                      />
                    </FormControl>
                    <FormControl>
                      <Input
                        className="h-full border-0 m-0 p-0 focus-visible:ring-0 focus-visible:ring-offset-0"
                        type="string"
                        placeholder=""
                        {...field}
                      />
                    </FormControl>
                    <FormMessage />
                  </div>
                </FormItem>
              )}
            />
          </div>
        </div>
      </form>
    </Form>
  );
}
