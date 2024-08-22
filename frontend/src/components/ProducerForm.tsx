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

const formSchema = z.object({
  name: z.string(),
});

export default function ProducerForms() {
  const navigate = useNavigate();

  const form = useForm<z.infer<typeof formSchema>>({
    resolver: zodResolver(formSchema),
    defaultValues: {
      name: "",
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
            생산자 추가 {form.getValues("name")}
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
        {/* 국문이름 */}
        <FormField
          control={form.control}
          name="name"
          render={({ field }) => (
            <FormItem>
              <FormLabel>생산자 이름</FormLabel>
              <FormControl>
                <Input placeholder="" {...field} />
              </FormControl>
              <FormMessage />
            </FormItem>
          )}
        />
      </form>
    </Form>
  );
}
