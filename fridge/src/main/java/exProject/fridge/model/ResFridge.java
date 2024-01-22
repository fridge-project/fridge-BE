package exProject.fridge.model;

public interface ResFridge {
    Ingredient getIngredient(); // 재료코드 - 외래키

    String getExp(); // 유통기한 - null 가능
    StorageType getStorage(); // 보관 방법

    String getMemo(); // 메모

}