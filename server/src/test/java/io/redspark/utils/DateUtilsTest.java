package io.redspark.utils;

import io.redspark.exception.ValidationException;
import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by lacau on 19/06/17.
 */
public class DateUtilsTest {

    private DateUtils dateUtils = new DateUtils();

    @Test
    public void testToDate() {
        final Date date = dateUtils.toDate("2017-06-19 20:00:00");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        Assert.assertEquals(calendar.get(Calendar.YEAR), 2017);
        Assert.assertEquals(calendar.get(Calendar.MONTH), 5);
        Assert.assertEquals(calendar.get(Calendar.DAY_OF_MONTH), 19);
        Assert.assertEquals(calendar.get(Calendar.HOUR_OF_DAY), 20);
        Assert.assertEquals(calendar.get(Calendar.MINUTE), 0);
        Assert.assertEquals(calendar.get(Calendar.SECOND), 0);
    }

    @Test(expected = ValidationException.class)
    public void testToDateWhenValidationException() {
        dateUtils.toDate("06-199-2017 20:00");
    }
}
