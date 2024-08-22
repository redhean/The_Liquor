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
import { AiOutlineClose, AiOutlineUpload } from "react-icons/ai";
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
  images: z.array(z.instanceof(File)),
  title: z.string(),
  classifications: z.string(),
});

export default function CardnewsForm() {
  const navigate = useNavigate();

  const form = useForm<z.infer<typeof formSchema>>({
    resolver: zodResolver(formSchema),
    defaultValues: {
      images: [],
      classifications: "0",
      title: "",
    },
  });

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
        <div className="flex flex-row">
          <h1 className="text-3xl font-semibold">
            카드뉴스 추가 {form.getValues("title")}
          </h1>
          <Button
            className="w-32"
            variant="destructive"
            size="sm"
            type="button"
          >
            취소
          </Button>
          <Button className="w-32" size="sm" type="submit">
            추가
          </Button>
        </div>
        <div className="w-full space-y-2">
          {/* 제목 */}
          <FormField
            control={form.control}
            name="title"
            render={({ field }) => (
              <FormItem>
                <FormLabel>제목</FormLabel>
                <FormControl>
                  <Input placeholder="" {...field} />
                </FormControl>
                <FormMessage />
              </FormItem>
            )}
          />
          {/* 카테고리 */}
          <FormField
            control={form.control}
            name="classifications"
            render={({ field }) => (
              <FormItem>
                <div className="flex flex-row w-full gap-2">
                  <div className="w-full">
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
                  </div>
                  <div className="w-full">
                    <FormLabel>2차 주종</FormLabel>
                    <Select>
                      <FormControl>
                        <SelectTrigger>
                          <SelectValue {...field} placeholder="1차 주종" />
                        </SelectTrigger>
                      </FormControl>
                      <SelectContent>
                        <SelectItem value="1">ㅎㅎ</SelectItem>
                        <SelectItem value="2">ㅋㅋ</SelectItem>
                        <SelectItem value="3">ㅜㅜ</SelectItem>
                      </SelectContent>
                    </Select>
                  </div>
                </div>
                <FormMessage />
              </FormItem>
            )}
          />
          {/* 카드뉴스 이미지 */}
          <div>
            <FormField
              control={form.control}
              name="images"
              render={({ field }) => (
                <FormItem>
                  <div className="grid grid-cols-3 w-fit gap-4 bg-red-300">
                    {field.value?.length > 0 &&
                      Array.from(field.value).map((image: any) => {
                        return (
                          <div className="space-y-2 relative">
                            <img
                              key={image.name}
                              src={URL.createObjectURL(image)}
                              alt={image.name}
                              className="size-64 object-cover bg-white rounded-2xl"
                            />
                            <Button
                              type="button"
                              variant="destructive"
                              size="icon"
                              className="size-4 rounded-full absolute top-0 right-2"
                              onClick={() => {
                                const currentFiles = field.value || [];
                                form.setValue(
                                  "images",
                                  currentFiles.filter(
                                    (file: any) => file !== image,
                                  ),
                                );
                              }}
                            >
                              <AiOutlineClose />
                            </Button>
                          </div>
                        );
                      })}
                    <FormLabel>
                      <div className="size-64 bg-red-500 flex justify-center items-center border border-black rounded-xl">
                        <AiOutlineUpload className="size-8" />
                      </div>
                    </FormLabel>
                  </div>
                  <FormControl>
                    <Input
                      className="hidden"
                      type="file"
                      accept="image/*"
                      multiple
                      onChange={(e) => {
                        console.log(e.target.files);
                        const files = Array.from(e.target.files || []);
                        const currentFiles = field.value || [];
                        form.setValue("images", currentFiles.concat(files));

                        // 동일 파일 재선택을 위해 초기화
                        e.target.value = "";
                      }}
                    />
                  </FormControl>
                  <FormMessage />
                </FormItem>
              )}
            />
          </div>
        </div>
      </form>
    </Form>
  );
}
