package com.example.utils.result;

import java.lang.reflect.Field;

public class CheckObject {

    // 如果对象不为空，且没有空值。返回false，对象为空或者有空值，返回true
    public static boolean checkObjFieldIsNull(Object obj) throws IllegalAccessException {
        boolean flag = false;
        if(null != obj) {
            for(Field f : obj.getClass().getDeclaredFields()){
                // 如果其中含有getId的属性，那么就跳过
                if(f.getName().equals("id")){
                    continue;
                }
                f.setAccessible(true); // 在用反射时访问私有变量
                if(f.get(obj) == null || f.get(obj).equals("")){
                    flag = true;
                    return flag;
                }
                if(f.get(obj) != null&&f.get(obj).toString().trim().equals("")){
                    flag = true;
                    return flag;
                }
            }
        } else {
            flag = false;
        }
        return flag;
    }

}
