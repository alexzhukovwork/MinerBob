package com.mygdx.minerbob.helpers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.Date;

/**
 * Created by Алексей on 02.10.2017.
 */

public class MyDate {
    private final int DAY_SECONDS = 1000 * 60 * 60 * 24;
    private Date date;
    public MyDate() {
        date = new Date();
    }

    private long getTime() {
        String url = "https://time.is/Unix_time_now";
        Document doc = null;
        try {
            doc = Jsoup.parse(new URL(url).openStream(), "UTF-8", url);
            String[] tags = new String[] {
                    "div[id=time_section]",
                    "div[id=clock0_bg]"
            };
            Elements elements= doc.select(tags[0]);
            for (int i = 0; i <tags.length; i++) {
                elements = elements.select(tags[i]);
            }
            return Long.parseLong(elements.text() + "000");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public Date getDate() {
        long time = getTime();

        if (time != 0) {
            date.setTime(time);
            date.setSeconds(0);
            date.setMinutes(0);
            date.setHours(0);
            return date;
        }

        return null;
    }

    public boolean isNextDay(long prevDay, long nextDay) {
        System.out.println("Fucking dick prev motherfucker it is bullshit " + (nextDay - prevDay));
        return nextDay - prevDay == DAY_SECONDS;
    }


}
