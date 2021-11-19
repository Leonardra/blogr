package com.blog.blogr.utility;


import org.ocpsoft.prettytime.PrettyTime;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class PrettyTimeConverter {


    public String convertDateTime(LocalDate date){
        PrettyTime prettyTime = new PrettyTime();
        return prettyTime.format(date);
    }
}
