import ProducerForms from "@/components/ProducerForm";
import { useEffect } from "react";

export default function CreateProducer() {
  // 새로고침 시 경고창 띄우기
  const preventCloseUnload = (e: BeforeUnloadEvent) => {
    e.preventDefault();
  };

  useEffect(() => {
    (() => {
      window.addEventListener("beforeunload", preventCloseUnload);
    })();

    return () => {
      window.removeEventListener("beforeunload", preventCloseUnload);
    };
  }, []);

  return (
    <div>
      <ProducerForms />
    </div>
  );
}
