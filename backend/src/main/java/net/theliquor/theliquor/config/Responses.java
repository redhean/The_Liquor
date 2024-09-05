package net.theliquor.theliquor.config;

public class Responses {

    // Error
    public enum Result {
        SUCCESS,    // 0
        FAIL        // 1
    }


    public static final String PRODUCER_NOT_FOUND = "생산자가 존재하지 않습니다.";
    public static final String BRAND_NOT_FOUND = "브랜드가 존재하지 않습니다.";
    public static final String CLASSIFICATION_NOT_FOUND = "주종이 존재하지 않습니다.";

    // Image
    public static final String IMAGE_UPLOAD_FAILED = "이미지 업로드를 실패했습니다.";
    public static final String INVALID_IMAGE_FILE = "이미지 파일의 형식이 유효하지 않습니다.";
    public static final String EMPTY_IMAGE_FILE = "이미지 파일이 없습니다.";
}
