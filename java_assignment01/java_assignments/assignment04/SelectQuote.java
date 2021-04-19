package java_assignments.assignment04;

import java.util.Date;
import java.util.Calendar;
import java.util.Random;

public class SelectQuote
{
    Date date = new Date();
    Calendar calendar = Calendar.getInstance();
    int day;
    int month;
    int minute;
    int second;
    Quotes q = new Quotes();
    Random r1,r2;

    byte rand[] = new byte[1];

    SelectQuote()
    {
        minute = calendar.get(Calendar.MINUTE);
        second = calendar.get(Calendar.SECOND);
        day = calendar.get(Calendar.DATE);
        month = calendar.get(Calendar.MONTH) + 1;

/*      r1 = new Random(day * month);
        day * month seed dont change much so would not prove to be an
        effective seed for quotes inquired on the same day
 */

        r2 = new Random(minute * second);
        r2.nextBytes(rand);

        rand[0] = (byte)Math.abs(rand[0]);

        if (rand[0] >= 55)
        {
            rand[0] = (byte) (rand[0]%54);
        }

//        System.out.println("rand : " + rand[0]);          // line specifically kept for testing

        q.ShowQuote(day, month, rand);
    }

}
