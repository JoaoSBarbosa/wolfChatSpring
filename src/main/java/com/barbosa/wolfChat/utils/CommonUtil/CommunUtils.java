package com.barbosa.wolfChat.utils.CommonUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class CommunUtils {

    public static String getDateTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }
}
