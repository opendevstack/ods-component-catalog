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

    private static String stripInlineCommentPreserveFormat(String line) {
        StringBuilder sb = new StringBuilder(line.length());
        int pending = 0;
        boolean commentStarted = false;

        for (int i = 0; i < line.length() && !commentStarted; i++) {
            char ch = line.charAt(i);

            if (ch == '\\') {
                pending++;
            } else if (ch == '#') {
                if (isEscapedHash(pending)) {
                    appendEscapedHash(sb, pending);
                    pending = 0;
                } else {
                    appendBackslashes(sb, pending);
                    pending = 0;
                    commentStarted = true;
                }
            } else {
                appendBackslashes(sb, pending);
                pending = 0;
                sb.append(ch);
            }
        }

        appendBackslashes(sb, pending);

        return sb.toString();
    }

    private static boolean isEscapedHash(int pendingBackslashes) {
        return (pendingBackslashes & 1) == 1;
    }

    private static void appendEscapedHash(StringBuilder sb, int pendingBackslashes) {
        appendBackslashes(sb, pendingBackslashes - 1);
        sb.append('#');
    }

    private static void appendBackslashes(StringBuilder sb, int count) {
        if (count > 0) {
            sb.repeat('\\', count);
        }
    }
}