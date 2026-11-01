package me.manossef.api.alexandria;

import java.util.UUID;

/**
 * Contains utility methods for checking whether a string can be safely converted to other types.
 */
public final class TypeChecks {
    private TypeChecks() {
    }

    /**
     * Returns <code>true</code> if the specified string is equal to either <code>"true"</code> or <code>"false"</code>,
     * and <code>false</code> otherwise.
     * @param input The string to check.
     * @return <code>true</code> if the string can be converted to a <code>boolean</code>, and <code>false</code>
     * otherwise.
     */
    public static boolean isBoolean(String input) {
        return input.equals("true") || input.equals("false");
    }

    /**
     * Returns <code>true</code> if the specified string can be safely converted to a <code>byte</code> (as if by
     * {@link Byte#parseByte(String)}), and <code>false</code> otherwise.
     * @param input The string to check.
     * @return <code>true</code> if the string can be converted to a <code>byte</code>, and <code>false</code>
     * otherwise.
     */
    public static boolean isByte(String input) {
        try {
            Byte.parseByte(input);
            return true;
        } catch(NumberFormatException e) {
            return false;
        }
    }

    /**
     * Returns <code>true</code> if the specified string can be safely converted to a <code>short</code> (as if by
     * {@link Short#parseShort(String)}), and <code>false</code> otherwise.
     * @param input The string to check.
     * @return <code>true</code> if the string can be converted to a <code>short</code>, and <code>false</code>
     * otherwise.
     */
    public static boolean isShort(String input) {
        try {
            Short.parseShort(input);
            return true;
        } catch(NumberFormatException e) {
            return false;
        }
    }

    /**
     * Returns <code>true</code> if the specified string can be safely converted to an <code>int</code> (as if by
     * {@link Integer#parseInt(String)}), and <code>false</code> otherwise.
     * @param input The string to check.
     * @return <code>true</code> if the string can be converted to an <code>int</code>, and <code>false</code>
     * otherwise.
     */
    public static boolean isInt(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch(NumberFormatException e) {
            return false;
        }
    }

    /**
     * Returns <code>true</code> if the specified string can be safely converted to a <code>long</code> (as if by
     * {@link Long#parseLong(String)}), and <code>false</code> otherwise.
     * @param input The string to check.
     * @return <code>true</code> if the string can be converted to a <code>long</code>, and <code>false</code>
     * otherwise.
     */
    public static boolean isLong(String input) {
        try {
            Long.parseLong(input);
            return true;
        } catch(NumberFormatException e) {
            return false;
        }
    }

    /**
     * Returns <code>true</code> if the specified string can be safely converted to a <code>float</code> (as if by
     * {@link Float#parseFloat(String)}), and <code>false</code> otherwise.
     * @param input The string to check.
     * @return <code>true</code> if the string can be converted to a <code>float</code>, and <code>false</code>
     * otherwise.
     */
    public static boolean isFloat(String input) {
        try {
            Float.parseFloat(input);
            return true;
        } catch(NumberFormatException e) {
            return false;
        }
    }

    /**
     * Returns <code>true</code> if the specified string can be safely converted to a <code>double</code> (as if by
     * {@link Double#parseDouble(String)}), and <code>false</code> otherwise.
     * @param input The string to check.
     * @return <code>true</code> if the string can be converted to a <code>double</code>, and <code>false</code>
     * otherwise.
     */
    public static boolean isDouble(String input) {
        try {
            Double.parseDouble(input);
            return true;
        } catch(NumberFormatException e) {
            return false;
        }
    }

    /**
     * Returns <code>true</code> if the specified string describes a {@link UUID} in its standard string representation,
     * and <code>false</code> otherwise.
     * @param input The string to check.
     * @return <code>true</code> if the string can be converted to a UUID, and <code>false</code> otherwise.
     */
    public static boolean isUUID(String input) {
        try {
            UUID.fromString(input);
            return true;
        } catch(IllegalArgumentException e) {
            return false;
        }
    }

    /**
     * Returns <code>true</code> if the specified string exactly matches one of the enum constants declared in the
     * specified enum class, and <code>false</code> otherwise. If the specified class is not an enum, this method
     * always returns <code>false</code>.
     * @param input The string to check.
     * @param clazz The enum class to check against.
     * @return <code>true</code> if the string exactly matches one of the specified enum's constants, and
     * <code>false</code> otherwise.
     * @param <T> The enum type.
     */
    public static <T extends Enum<T>> boolean isEnum(String input, Class<T> clazz) {
        try {
            Enum.valueOf(clazz, input);
            return true;
        } catch(IllegalArgumentException e) {
            return false;
        }
    }
}