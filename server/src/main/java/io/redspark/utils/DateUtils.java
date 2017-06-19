package io.redspark.utils;

import io.redspark.exception.ValidationException;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lacau on 19/06/17.
 */
@Component
public class DateUtils {

    public static final String PATTERN = "yyyy-MM-dd HH:mm:ss";

    private final SimpleDateFormat FORMATTER = new SimpleDateFormat(PATTERN);

    public Date toDate(String date) {
        try {
            return FORMATTER.parse(date);
        } catch (ParseException e) {
            throw new ValidationException("Invalid date format. expected: " + PATTERN);
        }
    }
}
