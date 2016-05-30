package com.github.baev;

/**
 * @author Dmitry Baev
 */
public final class BadXmlCharactersUtils {

    /**
     * Do not instance.
     */
    BadXmlCharactersUtils() {
        throw new IllegalStateException("Don't instance BadXmlCharactersUtils");
    }

    /**
     * Detect bad xml 1.0 characters
     *
     * @param c to detect
     * @return true if specified character valid, false otherwise
     */
    public static boolean isBadXmlCharacter(char c) {
        boolean cDataCharacter = c < '\u0020' && c != '\t' && c != '\r' && c != '\n';
        cDataCharacter |= (c >= '\uD800' && c < '\uE000');
        cDataCharacter |= (c == '\uFFFE' || c == '\uFFFF');
        return cDataCharacter;
    }

    /**
     * Replace bad xml charactes in given array by space
     *
     * @param cbuf buffer to replace in
     * @param off  Offset from which to start reading characters
     * @param len  Number of characters to be replaced
     */
    public static void replaceBadXmlCharactersBySpace(char[] cbuf, int off, int len) {
        for (int i = off; i < off + len; i++) {
            if (isBadXmlCharacter(cbuf[i])) {
                cbuf[i] = '\u0020';
            }
        }
    }
}