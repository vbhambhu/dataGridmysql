package io.vkumar.Helpers;


import io.vkumar.entities.AjaxResponse;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class FormValidation {




    public static AjaxResponse validate(String col, String rules) {

        String rule_arr[] = rules.split("\\|");

        //Pattern patronValidity = Pattern.compile("\\[([^\\]]+)]");
        //Matcher m = p.matcher(in);

        for (String rule: rule_arr){
            if(rule.contains("[") && rule.contains("]") ){
                System.out.println("With bracket");
            } else{
                System.out.println("without bracket");
            }
        }
//
//        try {
//            Method method =  this.getClass().getMethod(validations, null);
//            method.invoke(this, null);
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }
        AjaxResponse response = new AjaxResponse();
        response.setStatus(true);
        return response;
    }


    public boolean numeric() {
        System.out.println("Checking numeric");
        return false;
    }

}
