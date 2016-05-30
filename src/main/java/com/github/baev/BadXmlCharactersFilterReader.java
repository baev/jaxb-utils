package com.github.baev;

import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static com.github.baev.BadXmlCharactersUtils.replaceBadXmlCharactersBySpace;

/**
 * @author Dmitry Baev
 */
public class BadXmlCharactersFilterReader extends FilterReader {

    /**
     * Creates a new filtered reader and use UTF-8 as encoding.
     *
     * @param file to read.
     */
    public BadXmlCharactersFilterReader(Path file) throws IOException {
        this(file, StandardCharsets.UTF_8);
    }

    /**
     * Creates a new filtered reader.
     *
     * @param file to read.
     */
    public BadXmlCharactersFilterReader(Path file, Charset encoding) throws IOException {
        this(Files.newBufferedReader(file, encoding));
    }

    /**
     * Creates a new filtered reader.
     *
     * @param in a Reader object providing the underlying stream.
     * @throws NullPointerException if <code>in</code> is <code>null</code>
     */
    public BadXmlCharactersFilterReader(Reader in) {
        super(in);
    }

    /**
     * Reads characters into a portion of an array, then replace invalid XML characters
     *
     * @throws IOException If an I/O error occurs
     * @see BadXmlCharactersUtils#isBadXmlCharacter(char) by space
     */
    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        int numChars = super.read(cbuf, off, len);
        replaceBadXmlCharactersBySpace(cbuf, off, len);
        return numChars;
    }
}