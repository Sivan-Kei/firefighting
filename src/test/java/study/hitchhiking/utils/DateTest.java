package study.hitchhiking.utils;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
public class DateTest {

    @Test
    public void testDateFormat(){
        System.out.println(dateToString(new Date()));
    }

    private String dateToString(Date date){
        DateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
        return dateFormat.format(date);
    }
}
