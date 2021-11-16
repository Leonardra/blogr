package com.blog.blogr.utility;


import org.ocpsoft.prettytime.PrettyTime;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PrettyTimeConverter {


    public static String convertDateTime(LocalDateTime date){
        PrettyTime prettyTime = new PrettyTime();
        return prettyTime.format(date);
    }
}
