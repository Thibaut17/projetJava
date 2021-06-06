package utils;
import javax.swing.JFormattedTextField.AbstractFormatter;

import java.util.Calendar;

import java.text.ParseException;

import java.text.SimpleDateFormat;


/**
 * Trouve sur internet pour utiliser les calendrier avec swing : https://stackoverflow.com/questions/31277001/jdatepicker-date-formatting
 */
public class DateLabelFormatter extends AbstractFormatter {

    private String datePattern = "yyyy-MM-dd";
    private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

    @Override
    /**
    * Transforme une string en valeur
    * @param text string a transformer
    */
    public Object stringToValue(String text) throws ParseException {
        return dateFormatter.parseObject(text);
    }

    @Override
    /**
     * Transforme une valeur en string
     * @param value valeur a transformer
     */
    public String valueToString(Object value) throws ParseException {
        if (value != null) {
            Calendar cal = (Calendar) value;
            return dateFormatter.format(cal.getTime());
        }

        return "";
    }

}