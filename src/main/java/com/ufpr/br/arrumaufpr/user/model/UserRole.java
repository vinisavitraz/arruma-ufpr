package com.ufpr.br.arrumaufpr.user.model;

public class UserRole {

    public enum Role {
        ADMIN(1, "Admin"),
        STUDENT(2, "Student");

        private final int value;
        private final String name;

        Role(int value, String name) {
            this.value = value;
            this.name = name;
        }

        public int getValue() {
            return value;
        }

        public String getName() {
            return name;
        }

        public static Role fromValue(int value) {
            for (Role role : Role.values()) {
                if (role.getValue() == value) {
                    return role;
                }
            }

            return null;
        }
    }

}
