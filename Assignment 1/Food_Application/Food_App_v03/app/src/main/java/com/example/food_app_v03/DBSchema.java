package com.example.food_app_v03;

public class DBSchema {
    public static class UserTable{
        public static final String TABLENAME = "users";
        public static class Cols{
            public static final String NAME = "name";
            public static final String EMAIL = "email";
            public static final String ADDRESS = "address";
            public static final String PHONE = "phone";
            public static final String PASSWORD = "password";
        }
    }

    public static class RestaurantTable{
        public static final String TABLENAME = "restaurants";
        public static class Cols{
            public static final String RES_ID = "resID";
            public static final String RES_NAME = "resName";
            public static final String RES_IMAGE_PATH = "resImagePath";
            public static final String RES_ADDRESS = "resAddress";
        }
    }

    public static class FoodTable{
        public static final String TABLENAME = "foods";
        public static class Cols{
            public static final String FOOD_ID = "foodID";
            public static final String FOOD_NAME = "foodName";
            public static final String FOOD_PRICE = "foodPrice";
            public static final String FOOD_DESC = "foodDesc";
            public static final String RES_ID = "resID";
            public static final String FOOD_IMAGE_PATH = "foodImagePath";
        }
    }

    public static class OrderHistoryTable{
        public static final String TABLENAME = "orderHistory";
        public static class Cols{
            public static final String ORDER_ID = "orderID";
            public static final String USER_ID = "userID";
            public static final String FOOD_ITEM = "foodItem";
            public static final String RES_ID = "res_ID";
            public static final String UNIT_PRICE = "unitPrice";
            public static final String AMOUNT = "amount";
            public static final String TOTAL_PRICE = "totalPrice";
            public static final String DATE = "date";
        }
    }
}
