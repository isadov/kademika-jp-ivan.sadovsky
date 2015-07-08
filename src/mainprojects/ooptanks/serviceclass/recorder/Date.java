package mainprojects.ooptanks.serviceclass.recorder;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Date {
    SimpleDateFormat sdf;
    Calendar calendar;
    public String currentData;

    public Date() {
        this.sdf =  new SimpleDateFormat("yyMMddHHmm");
        this.calendar = new GregorianCalendar(2013, 10, 28);
        calendar = Calendar.getInstance();
        currentData = sdf.format(calendar.getTime());
    }

    public String getCurrentDate() {
        calendar = Calendar.getInstance();
        currentData = sdf.format(calendar.getTime());
        return currentData;
    }
}
