package io.vkumar.controllers;


import com.sun.tools.internal.ws.wsdl.document.soap.SOAPUse;
import io.vkumar.Helpers.FormValidation;
import io.vkumar.entities.Field;
import io.vkumar.entities.Form;
import io.vkumar.entities.Option;
import io.vkumar.services.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;


@Controller
public class FormController {

    @Autowired
    private FormService formService;

    @RequestMapping(value="/form/view", method = { RequestMethod.GET, RequestMethod.POST })
    public String showform(@RequestParam("id") Long id, Model model) {

        Form form = formService.formById(id);
        List<Field> fields = formService.findFieldsByFormId(id);

        FormValidation formValidation = new FormValidation();

        for (Field field :fields) {
            formValidation.setRules(field.getIdentifier(), field.getLabel(), field.getValidations());
        }

        if(!formValidation.validate()){
            System.out.println("Validation failed .");
            model.addAttribute("form", form);
            model.addAttribute("fields", fields);
            return "forms/index";
        } else{
            System.out.println("Validation passed.");
            return "forms/dsadasdsada";
        }






    }





}




//            for (Field field :fields){
//                for (Validation validation: field.getValidations()){
////                    String methodName = "display";
////
////                    FormValidation formValidation= new FormValidation();
////
////                    Method met = null;
////                    try {
////                        met = formValidation.getClass().getMethod(methodName, null);
////                        met.invoke(formValidation, null);
////                    } catch (NoSuchMethodException e) {
////                        e.printStackTrace();
////                    } catch (IllegalAccessException e) {
////                        e.printStackTrace();
////                    } catch (InvocationTargetException e) {
////                        e.printStackTrace();
////                    }
//
//
//                }
//
//            }