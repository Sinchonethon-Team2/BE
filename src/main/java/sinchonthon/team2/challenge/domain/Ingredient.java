package sinchonthon.team2.challenge.domain;

public enum Ingredient {
    RICE(1),
    KIMCHI(2),
    EGG(3),
    SAUSAGE(4);

    private final int code;

    Ingredient(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static Ingredient fromCode(int code) {
        for (Ingredient ingredient : values()) {
            if (ingredient.code == code) {
                return ingredient;
            }
        }
        throw new IllegalArgumentException("Invalid code: " + code);
    }
}
