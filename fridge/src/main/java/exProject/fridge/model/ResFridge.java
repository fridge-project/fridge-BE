package exProject.fridge.model;

public interface ResFridge {
    Ingredient getIngredient(); // 재료코드 - 외래키

    String getExp(); // 유통기한 - null 가능
    String getAddDate(); // 등록날짜

    StorageType getStorage(); // 보관 방법

    String getMemo(); // 메모
    String getImageUrl(); // s3 이미지 경로

}