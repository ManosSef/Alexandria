package me.manossef.api.alexandria;

import java.util.UUID;

public final class TypeChecks {

    private TypeChecks() {
    }

    public static boolean isBoolean(String input) {

        return input.equals("true") || input.equals("false");

    }

    public static boolean isByte(String input) {

        try {

            Byte.parseByte(input);
            return true;

        } catch(NumberFormatException e) {

            return false;

        }

    }

    public static boolean isShort(String input) {

        try {

            Short.parseShort(input);
            return true;

        } catch(NumberFormatException e) {

            return false;

        }

    }

    public static boolean isInt(String input) {

        try {

            Integer.parseInt(input);
            return true;

        } catch(NumberFormatException e) {

            return false;

        }

    }

    public static boolean isLong(String input) {

        try {

            Long.parseLong(input);
            return true;

        } catch(NumberFormatException e) {

            return false;

        }

    }

    public static boolean isFloat(String input) {

        try {

            Float.parseFloat(input);
            return true;

        } catch(NumberFormatException e) {

            return false;

        }

    }

    public static boolean isDouble(String input) {

        try {

            Double.parseDouble(input);
            return true;

        } catch(NumberFormatException e) {

            return false;

        }

    }

    public static boolean isUUID(String input) {

        try {

            UUID.fromString(input);
            return true;

        } catch(IllegalArgumentException e) {

            return false;

        }

    }

    public static <T extends Enum<T>> boolean isEnum(String input, Class<T> clazz) {

        try {

            Enum.valueOf(clazz, input);
            return true;

        } catch(IllegalArgumentException e) {

            return false;

        }

    }

}
