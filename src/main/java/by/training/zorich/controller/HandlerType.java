package by.training.zorich.controller;

public enum HandlerType {
    COMMAND("command"),
    LOGIN("login"),
    REGISTER("register"),
    LOCALE("changeLocale"),
    RETURN_PAGE("target"),
    ADD_TO_CART("addToCart"),
    ADD_ADDRESS("addAddress"),
    DELETE_FROM_CART("deleteCartItem"),
    URI_ADD_PERIODICAL("/admin/added"),
    SUBSCRIBE("subscribe"),
    SEARCH("/periodical/search"),
    LOGOUT("/logout"),
    PREPROCESS_ADD("/admin/add"),
    PREPROCESS_EDIT("/admin/edit"),
    PREPROCESS_CARD("/periodical/"),
    PREPROCESS_CART("/subscriber/cart"),
    PREPROCESS_PAYMENT("/subscriber/payment/"),

    ;

    final private String name;

    HandlerType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    static public HandlerType getActionParameterByName(String name) {
        for (HandlerType item : HandlerType.values()) {
            if (item.getName().equals(name)) {
                return item;
            }
        }

        return null;
    }
}
