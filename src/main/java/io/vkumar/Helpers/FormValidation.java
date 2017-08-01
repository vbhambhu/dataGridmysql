package io.vkumar.Helpers;


import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

public class FormValidation {

    private String rrr = "dsa";
    private HashMap<String, HashMap> field_data = new HashMap<String, HashMap>();


    public void setRules(String identifier, String label, String validations){

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        //No reason to set rules if we have no POST data
        if(!"POST".equals(request.getMethod()) || validations.isEmpty()){
            return;
        }


        HashMap<String, String> fd = new HashMap<String, String>();






//        System.out.println(identifier);
//        System.out.println(label);
//        System.out.println(validations);


    }

    public boolean validate() {

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


        return false;
    }


    public boolean numeric() {
        System.out.println("Checking numeric");
        return false;
    }

}
