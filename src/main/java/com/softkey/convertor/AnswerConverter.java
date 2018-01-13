/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softkey.convertor;

import com.softkey.model.Answer;
import com.softkey.service.TakeTestServiceImpl;
import com.softkey.utils.Utils;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Ahmed Saad
 */
@FacesConverter(value="answerConverter")
public class AnswerConverter implements Converter {

    private final TakeTestServiceImpl service = new TakeTestServiceImpl();

    // Actions ------------------------------------------------------------------------------------
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        // Convert the unique String representation of Foo to the actual Foo object.
        if (!Utils.isEmptyString(value)) {
            return service.getAnswersByRecid(Integer.parseInt(value));
        } else {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        // Convert the Foo object to its unique String representation.
        return ((Answer) value).getRecid()+"";
    }

}
