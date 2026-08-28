package org.opendevstack.component_catalog.util;

import java.io.Serial;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.GregorianCalendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class RFC3339DateFormat extends DateFormat {
    @Serial
    private static final long serialVersionUID = 742013204909252430L;

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX", Locale.US);

    private static final DateTimeFormatter PARSER = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

    public RFC3339DateFormat() {
        calendar = new GregorianCalendar(TimeZone.getTimeZone("UTC"), Locale.US);
        numberFormat = NumberFormat.getInstance(Locale.US);
    }

    @Override
    public StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition fieldPosition) {
        var formatted = FORMATTER.format(date.toInstant().atOffset(ZoneOffset.UTC));
        toAppendTo.append(formatted);
        return toAppendTo;
    }

    @Override
    public Date parse(String source, ParsePosition pos) {
        var start = pos.getIndex();
        try {
            var text = source.substring(start);
            var dateTime = OffsetDateTime.parse(text, PARSER);
            pos.setIndex(source.length());
            return Date.from(dateTime.toInstant());
        } catch (DateTimeParseException | IndexOutOfBoundsException ex) {
            var errorIndex = start;
            if (ex instanceof DateTimeParseException dateTimeParseException
                    && dateTimeParseException.getErrorIndex() >= 0) {
                errorIndex = start + dateTimeParseException.getErrorIndex();
            }
            pos.setErrorIndex(errorIndex);
            return null;
        }
    }

}