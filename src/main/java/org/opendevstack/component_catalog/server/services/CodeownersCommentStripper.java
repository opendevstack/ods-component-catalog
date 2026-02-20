package org.opendevstack.component_catalog.server.services;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class CodeownersCommentStripper {

    private CodeownersCommentStripper() {
    }

    // --- existing methods (strip(List<String>), helpers, etc.) stay unchanged ---

    /**
     * Strips comments from a CODEOWNERS file content provided as a single String.
     * Preserves:
     * - original line separators (LF, CRLF, or CR)
     * - blank lines
     *
     * @param content CODEOWNERS file content as one full string
     * @return same content with full-line and inline comments removed
     */
    public static String strip(String content) {
        if (content == null || content.isEmpty()) return content;

        // Detect original line separator (default to '\n')
        String lineSeparator = detectLineSeparator(content);

        // Split preserving empty trailing lines
        List<String> lines = List.of(content.split("\\R", -1));

        // Delegate to line-based cleaner
        List<String> cleaned = strip(lines);

        // Join back using original separator
        return String.join(lineSeparator, cleaned);
    }

    /**
     * Strips full-line and inline comments from CODEOWNERS content.
     * - Full-line: lines whose first non-whitespace char is '#'
     * - Inline: text from the first unescaped '#' to the end of the line
     * - Preserves: original leading whitespace, internal spacing, and blank lines
     * - Escaped '#': a backslash directly before '#' makes it literal
     */
    public static List<String> strip(List<String> lines) {
        List<String> out = new ArrayList<>(lines.size());

        for (String line : lines) {

            String result = null;

            if (line.isEmpty()) {
                result = line;

            } else {
                int firstNonWs = firstNonWhitespaceIndex(line);

                if (firstNonWs == -1) {
                    result = line;

                } else if (line.charAt(firstNonWs) == '#') {
                    // Full line comment -> Do nothing
                    log.debug("strip - full line comment, do nothing");

                } else {
                    result = stripInlineCommentPreserveFormat(line);
                }
            }

            if (result != null) {
                out.add(result);
            }
        }

        return out;
    }

    /**
     * Detect the line separator used in the input string.
     * Prefers the first encountered line break style.
     */
    private static String detectLineSeparator(String s) {
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '\r') {
                if (i + 1 < s.length() && s.charAt(i + 1) == '\n') {
                    return "\r\n";  // Windows
                }
                return "\r";      // old Mac
            } else if (c == '\n') {
                return "\n";      // Unix / Linux / macOS
            }
        }
        return "\n"; // fallback
    }

    private static int firstNonWhitespaceIndex(String s) {
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!Character.isWhitespace(c)) return i;
        }
        return -1;
    }

    /**
     * Returns the line with inline comment removed, preserving leading whitespace
     * and internal spacing up to the first unescaped '#'.
     * If the '#' is escaped (preceded by an odd number of backslashes), it is kept
     * as a literal and one backslash is consumed.
     * Trailing spaces before the comment marker are preserved.
     */
    private static String stripInlineCommentPreserveFormat(String line) {
        StringBuilder sb = new StringBuilder(line.length());
        int pendingBackslashes = 0;

        for (int i = 0; i < line.length(); i++) {
            char ch = line.charAt(i);

            if (ch == '\\') {
                //Accumulate the contiguous backslashes
                pendingBackslashes++;
                continue;
            }

            if (ch == '#') {
                if ((pendingBackslashes & 1) == 1) {
                    // '#' escapado: writes (n-1) backslashes
                    if (pendingBackslashes > 1) {
                        sb.append("\\".repeat(pendingBackslashes - 1));
                    }
                    sb.append('#');
                    pendingBackslashes = 0;
                    continue;
                } else {
                    // '#' not escaped: end of useful content
                    // Writes all pending backslashes (even quantity) before ending
                    if (pendingBackslashes > 0) {
                        sb.append("\\".repeat(pendingBackslashes));
                    }
                    pendingBackslashes = 0; // Restart the count
                    break; // End here without including '#'
                }
            }

            // Any other caracter: empty pending backslashes
            if (pendingBackslashes > 0) {
                sb.append("\\".repeat(pendingBackslashes));
                pendingBackslashes = 0;
            }

            sb.append(ch);
        }

        // At the end, if there are pending backslashes, write them
        if (pendingBackslashes > 0) {
            sb.append("\\".repeat(pendingBackslashes));
        }

        return sb.toString();
    }
}