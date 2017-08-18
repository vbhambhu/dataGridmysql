package io.vkumar.Helpers;


import io.vkumar.entities.AjaxResponse;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormValidation {

    public static AjaxResponse validate(String fieldVal, String rules) {

        AjaxResponse response = new AjaxResponse();
        response.setStatus(true);

        // if rules are blank
        if(rules.isEmpty()){
            return response;
        }

        String ruleArr[] = rules.split("\\|");

        for (String rule: ruleArr){
            if(rule.contains("[") && rule.contains("]") ){
                String ruleName = rule.substring(0, rule.indexOf("[")).trim();

                if(ruleName.equals("validDate") || ruleName.equals("validTime")){
                    String ruleVal =  rule.substring(rule.indexOf("[") + 1, rule.indexOf("]"));

                    try {
                        Method method =  FormValidation.class.getMethod(ruleName, String.class, String.class);
                        response = (AjaxResponse) method.invoke(null, fieldVal, ruleVal);
                        if(!response.getStatus()) break;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else{
                    int ruleVal = Integer.parseInt(rule.substring(rule.indexOf("[") + 1, rule.indexOf("]")));

                    try {
                        Method method =  FormValidation.class.getMethod(ruleName, String.class, int.class);
                        response = (AjaxResponse) method.invoke(null, fieldVal, ruleVal);
                        if(!response.getStatus()) break;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            } else{

                try {
                    Method method =  FormValidation.class.getMethod(rule, String.class);
                    response = (AjaxResponse) method.invoke(null, fieldVal);
                    if(!response.getStatus()) break;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return response;
    }



    public static AjaxResponse greaterThan(String str, int param){
        AjaxResponse rs = new AjaxResponse();

        if(!str.matches("-?\\d+(\\.\\d+)?")){
            rs.setStatus(false);
            rs.setMsg("Field must have numeric value.");
            return rs;
        }

        int num = Integer.parseInt(str);
        if(num <= param) {
            rs.setStatus(false);
            rs.setMsg("Field must contain a number greater than "+param+".");
        } else{
            rs.setStatus(true);
        }
        return rs;
    }

    public static AjaxResponse greaterThanEqualTo(String str, int param){

        AjaxResponse rs = new AjaxResponse();

        if(!str.matches("-?\\d+(\\.\\d+)?")){
            rs.setStatus(false);
            rs.setMsg("Field must have numeric value.");
            return rs;
        }

        int num = Integer.parseInt(str);
        if(num < param) {
            rs.setStatus(false);
            rs.setMsg("Field must contain a number greater than or equal to  "+param+".");
        } else{
            rs.setStatus(true);
        }
        return rs;
    }

    public static AjaxResponse lessThan(String str, int param){
        AjaxResponse rs = new AjaxResponse();

        if(!str.matches("-?\\d+(\\.\\d+)?")){
            rs.setStatus(false);
            rs.setMsg("Field must have numeric value.");
            return rs;
        }

        int num = Integer.parseInt(str);
        if(num >= param) {
            rs.setStatus(false);
            rs.setMsg("Field must contain a number less than "+param+".");
        } else{
            rs.setStatus(true);
        }
        return rs;
    }

    public static AjaxResponse lessThanEqualTo(String str, int param){

        AjaxResponse rs = new AjaxResponse();

        if(!str.matches("-?\\d+(\\.\\d+)?")){
            rs.setStatus(false);
            rs.setMsg("Field must have numeric value.");
            return rs;
        }

        int num = Integer.parseInt(str);
        if(num > param) {
            rs.setStatus(false);
            rs.setMsg("Field must contain a number less than or equal to  "+param+".");
        } else{
            rs.setStatus(true);
        }
        return rs;
    }


    public static AjaxResponse minLength(String str, int param){
        AjaxResponse rs = new AjaxResponse();
        if(str.length() < param) {
            rs.setStatus(false);
            rs.setMsg("Field must be at least "+param+" characters in length.");
        } else{
            rs.setStatus(true);
        }
        return rs;
    }

    public static AjaxResponse maxLength(String str, int param){
        AjaxResponse rs = new AjaxResponse();
        if(str.length() > param) {
            rs.setStatus(false);
            rs.setMsg("Field cannot exceed "+param+" characters in length.");
        } else{
            rs.setStatus(true);
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

    public static AjaxResponse validEmail(String str) {
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

    public static AjaxResponse validDate(String str, String format) {
        AjaxResponse rs = new AjaxResponse();
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            sdf.parse(str);
            rs.setStatus(true);
        }
        catch(Exception ex) {
            rs.setStatus(false);
            rs.setMsg("Not valid Date.");
        }
        return rs;
    }

    public static AjaxResponse validTime(String str, String format) {
        AjaxResponse rs = new AjaxResponse();

        String mylRegExpVar = "([01]?[0-9]|2[0-3]):[0-5][0-9]";
        Pattern pVar = Pattern.compile(mylRegExpVar);
        Matcher mVar = pVar.matcher(str);

        if(!mVar.matches()){
            rs.setStatus(false);
            rs.setMsg("Not valid time.");
        } else{
            rs.setStatus(true);
        }
        return rs;
    }




}
