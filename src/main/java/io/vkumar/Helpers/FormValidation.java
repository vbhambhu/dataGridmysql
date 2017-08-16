package io.vkumar.Helpers;


import io.vkumar.entities.AjaxResponse;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormValidation {




    public static AjaxResponse validate(String fieldVal, String rules) {

        AjaxResponse response = new AjaxResponse();
        response.setStatus(true);


        String ruleArr[] = rules.split("\\|");

        for (String rule: ruleArr){

            //System.out.println(rule);

            if(rule.contains("[") && rule.contains("]") ){

                String ruleName = rule.substring(0, rule.indexOf("[")).trim();
                int ruleVal = Integer.parseInt(rule.substring(rule.indexOf("[") + 1, rule.indexOf("]")));

                try {
                    Method method =  FormValidation.class.getMethod(ruleName, String.class, int.class);
                    response = (AjaxResponse) method.invoke(null, fieldVal, ruleVal);
                    if(!response.getStatus()) break;
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                //System.out.println(ruleVal);
                //System.out.println("With bracket");
            } else{

                try {
                    Method method =  FormValidation.class.getMethod(rule, String.class);
                    response = (AjaxResponse) method.invoke(null, fieldVal);
                    if(!response.getStatus()) break;
                } catch (NoSuchMethodException e) {
                e.printStackTrace();
                } catch (IllegalAccessException e) {
                e.printStackTrace();
                } catch (InvocationTargetException e) {
                e.printStackTrace();
                }


               // response = required(fieldVal);
                //if(!response.getStatus()) break;
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

        return response;
    }


    public static AjaxResponse minLength(String str, int param){

        System.out.println(str);
        System.out.println(param);

        AjaxResponse rs = new AjaxResponse();
        if(str.length() < param) {
            rs.setStatus(true);
        } else{
            rs.setStatus(false);
            rs.setMsg("Field must be at least "+param+" characters in length.");
        }
        return rs;
    }

    public static AjaxResponse required(String str){

        AjaxResponse rs = new AjaxResponse();
        if(str.isEmpty()){
            rs.setStatus(false);
            rs.setMsg("Filed is required");
        } else{
            rs.setStatus(true);
        }
        return rs;
    }


    public static AjaxResponse numeric(String str) {
        AjaxResponse rs = new AjaxResponse();
        if(!str.matches("-?\\d+(\\.\\d+)?")){
            rs.setStatus(false);
            rs.setMsg("Field must have numeric value.");
        } else{
            rs.setStatus(true);
        }
        return rs;
    }

    public static AjaxResponse decimal(String str) {
        AjaxResponse rs = new AjaxResponse();

        if(!str.matches("[0-9.]*")){
            rs.setStatus(false);
            rs.setMsg("Field must have decimal value.");
        } else{
            rs.setStatus(true);
        }
        return rs;
    }

    public static AjaxResponse valid_email(String str) {
        AjaxResponse rs = new AjaxResponse();

        String mylRegExpVar = "[A-Za-z0-9._%+-]{2,}+@[A-Za-z-]{2,}+\\.[A-Za-z]{2,}";
        Pattern pVar = Pattern.compile(mylRegExpVar);
        Matcher mVar = pVar.matcher(str);

        if(!mVar.matches()){
            rs.setStatus(false);
            rs.setMsg("Not valid email address.");
        } else{
            rs.setStatus(true);
        }
        return rs;
    }




}
