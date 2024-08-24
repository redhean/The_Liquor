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
import { Textarea } from "./ui/textarea";
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
  producer: z.number(),
  brand: z.number(),
  classifications: z.string(),
  korean_name: z.string(),
  english_name: z.string(),
  country: z.string(),
  alcohol: z.number(),
  aged: z.number(),
  price: z.number(),
  ibu: z.number(),
  is_domestic_sale: z.boolean(),
  description: z.string(),
});

export default function LiquorForm() {
  const navigate = useNavigate();

  const form = useForm<z.infer<typeof formSchema>>({
    resolver: zodResolver(formSchema),
    defaultValues: {
      producer: 0,
      brand: 0,
      classifications: "0",
      korean_name: "",
      english_name: "",
      country: "",
      alcohol: 0,
      aged: 0,
      price: 0,
      ibu: 0,
      is_domestic_sale: true,
      description: "",
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
            술 추가{" "}
            <span className="text-xl">{form.getValues("korean_name")}</span>
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
        {/* 이미지와 나머지 입력 폼 컨테이너 */}
        <div className="grid grid-cols-[minmax(24rem,_1fr)_2fr] gap-4 bg-slate-100">
          {/* 이미지 */}
          <div>
            <FormField
              control={form.control}
              name="image"
              render={({ field }) => (
                <FormItem>
                  <FormLabel>
                    {field.value?.length > 0 ? (
                      <img
                        className="w-full object-contain bg-slate-400 rounded-lg border border-slate-200"
                        src={URL.createObjectURL(field.value[0])}
                      />
                    ) : (
                      <div className="w-full h-[24rem] bg-slate-400 flex justify-center items-center rounded-lg">
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
              name="korean_name"
              render={({ field }) => (
                <FormItem>
                  <FormLabel>국문 이름</FormLabel>
                  <FormControl>
                    <Input placeholder="" {...field} />
                  </FormControl>
                  <FormMessage />
                </FormItem>
              )}
            />
            {/* 영문이름 */}
            <FormField
              control={form.control}
              name="english_name"
              render={({ field }) => (
                <FormItem>
                  <FormLabel>영문 이름</FormLabel>
                  <FormControl>
                    <Input placeholder="" {...field} type="text" />
                  </FormControl>
                  <FormMessage />
                </FormItem>
              )}
            />
            {/* 설명 */}
            <FormField
              control={form.control}
              name="description"
              render={({ field }) => (
                <FormItem>
                  <FormLabel>설명</FormLabel>
                  <FormControl>
                    <Textarea placeholder="" {...field} />
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
                  <FormLabel>주종</FormLabel>
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
                  <Select>
                    <FormControl>
                      <SelectTrigger>
                        <SelectValue placeholder="2차 주종" />
                      </SelectTrigger>
                    </FormControl>
                    <SelectContent>
                      <SelectItem value="1">ㅎㅎ</SelectItem>
                      <SelectItem value="2">ㅋㅋ</SelectItem>
                      <SelectItem value="3">ㅜㅜ</SelectItem>
                    </SelectContent>
                  </Select>
                  <Select onValueChange={field.onChange}>
                    <FormControl>
                      <SelectTrigger>
                        <SelectValue placeholder="3차 주종" />
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
            {/* 브랜드 드롭다운 */}
            <FormField
              control={form.control}
              name="brand"
              render={({ field }) => (
                <FormItem>
                  <FormLabel>브랜드</FormLabel>
                  <Select onValueChange={field.onChange}>
                    <FormControl>
                      <SelectTrigger>
                        <SelectValue placeholder="브랜드" />
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
            {/* 도수 */}
            <FormField
              control={form.control}
              name="alcohol"
              render={({ field }) => (
                <FormItem>
                  <FormLabel>도수</FormLabel>
                  <FormControl>
                    <Input type="number" placeholder="" {...field} />
                  </FormControl>
                  <FormMessage />
                </FormItem>
              )}
            />
            {/* 가격대 */}
            <FormField
              control={form.control}
              name="price"
              render={({ field }) => (
                <FormItem>
                  <FormLabel>가격대</FormLabel>
                  <FormControl>
                    <Input type="number" placeholder="" {...field} />
                  </FormControl>
                  <FormMessage />
                </FormItem>
              )}
            />
            {/* 국내 판매 여부 드롭다운 */}
            <FormField
              control={form.control}
              name="is_domestic_sale"
              render={({ field }) => (
                <FormItem>
                  <FormLabel>국내 판매 여부</FormLabel>
                  <Select onValueChange={field.onChange}>
                    <FormControl>
                      <SelectTrigger>
                        <SelectValue placeholder="판매 여부" />
                      </SelectTrigger>
                    </FormControl>
                    <SelectContent>
                      <SelectItem value="1">판매</SelectItem>
                      <SelectItem value="2">미판매</SelectItem>
                    </SelectContent>
                  </Select>
                </FormItem>
              )}
            />
            {/* 생산국 */}
            <FormField
              control={form.control}
              name="country"
              render={({ field }) => (
                <FormItem>
                  <FormLabel>생산국</FormLabel>
                  <FormControl>
                    <Input type="text" placeholder="" {...field} />
                  </FormControl>
                  <FormMessage />
                </FormItem>
              )}
            />
            {/* IBU */}
            <FormField
              control={form.control}
              name="ibu"
              render={({ field }) => (
                <FormItem>
                  <FormLabel>IBU</FormLabel>
                  <FormControl>
                    <Input type="number" placeholder="" {...field} />
                  </FormControl>
                  <FormMessage />
                </FormItem>
              )}
            />
            {/* 숙성연도 드롭다운 */}
            <FormField
              control={form.control}
              name="aged"
              render={({ field }) => (
                <FormItem>
                  <FormLabel>숙성 연도</FormLabel>
                  <Select onValueChange={field.onChange}>
                    <FormControl>
                      <SelectTrigger>
                        <SelectValue placeholder="숙성 연도" />
                      </SelectTrigger>
                    </FormControl>
                    <SelectContent>
                      <SelectItem value="1">1999</SelectItem>
                      <SelectItem value="2">2000</SelectItem>
                      <SelectItem value="3">2001</SelectItem>
                    </SelectContent>
                  </Select>
                </FormItem>
              )}
            />
          </div>
        </div>
      </form>
    </Form>
  );
}
