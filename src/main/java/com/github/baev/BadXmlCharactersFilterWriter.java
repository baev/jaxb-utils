package com.github.baev;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static com.github.baev.BadXmlCharactersUtils.replaceBadXmlCharactersBySpace;

/**
 * @author Dmitry Baev
 */
public class BadXmlCharactersFilterWriter extends FilterWriter {

    /**
     * Create a new filtered writer and use UTF-8 as encoding.
     */
    public BadXmlCharactersFilterWriter(Path out) throws IOException {
        this(out, StandardCharsets.UTF_8);
    }

    /**
     * Create a new filtered writer.
     */
    public BadXmlCharactersFilterWriter(Path out, Charset encoding) throws IOException {
        this(Files.newBufferedWriter(out, encoding));
    }

    /**
     * Create a new filtered writer.
     *
     * @param out a Writer object to provide the underlying stream.
     * @throws NullPointerException if <code>out</code> is <code>null</code>
     */
    protected BadXmlCharactersFilterWriter(Writer out) {
        super(out);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(int c) throws IOException {
        write(new char[]{(char) c}, 0, 1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(String str, int off, int len) throws IOException {
        write(str.toCharArray(), off, len);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        replaceBadXmlCharactersBySpace(cbuf, off, len);
        super.write(cbuf, off, len);
    }
}