package net.theliquor.theliquor.config;

public class Responses {

    // Error
    public enum Result {
        SUCCESS,    // 0
        FAIL        // 1
    }

    // Producer
    public static final String PRODUCER_NAME_INVALID_VALUE = "생산자 이름 값이 유효하지 않습니다.";
    public static final String PRODUCER_NOT_FOUND = "생산자가 존재하지 않습니다.";
    public static final String PRODUCER_SAVE_FAILED = "생산자 저장에 실패했습니다.";
    public static final String PRODUCER_UPDATE_FAILED = "생산자 수정에 실패했습니다.";
    public static final String PRODUCER_DELETE_FAILED = "생산자 삭제에 실패했습니다.";

    // Brand
    public static final String BRAND_NOT_FOUND = "브랜드가 존재하지 않습니다.";
    public static final String BRAND_SAVE_FAILED = "브랜드 저장을 실패했습니다.";
    public static final String BRAND_UPDATE_FAILED = "브랜드 수정을 실패했습니다.";
    public static final String BRAND_DELETE_FAILED = "브랜드 삭제를 실패했습니다.";


    // Classification
    public static final String CLASSIFICATION_NOT_FOUND = "주종이 존재하지 않습니다.";

    // Liquor
    public static final String LIQUOR_NOT_FOUND = "술이 존재하지 않습니다.";
    public static final String LIQUOR_SAVE_FAILED = "술 저장을 실패했습니다.";
    public static final String LIQUOR_UPDATE_FAILED = "술 수정을 실패했습니다.";
    public static final String LIQUOR_DELETE_FAILED = "술 삭제를 실패했습니다.";

    // CardNews
    public static final String CARD_NEWS_NOT_FOUND = "카드 뉴스가 존재하지 않습니다.";
    public static final String CARD_NEWS_SAVE_FAILED = "카드 뉴스 저장을 실패했습니다.";
    public static final String CARD_NEWS_UPDATE_FAILED = "카드 뉴스 수정을 실패했습니다.";
    public static final String CARD_NEWS_DELETE_FAILED = "카드 뉴스 삭제를 실패했습니다.";

    // Image
    public static final String IMAGE_UPLOAD_FAILED = "이미지 업로드를 실패했습니다.";
    public static final String IMAGE_DELETE_FAILED = "기존 이미지 삭제를 실패했습니다.";
    public static final String INVALID_IMAGE_FILE = "이미지 파일의 형식이 유효하지 않습니다.";
    public static final String EMPTY_IMAGE_FILE = "이미지 파일이 없습니다.";
}
