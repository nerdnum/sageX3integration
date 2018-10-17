package na.com.meatco.sageX3integration.utils;

import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.converters.BigDecimalConverter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TypeMatcher {

    public static Object doConversion(Class<?> theTargetClass, String value, String format){
        if (theTargetClass.equals(LocalDate.class)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            LocalDate localDate = LocalDate.parse(value, formatter);
            return localDate;
        } else if (theTargetClass.equals(BigDecimal.class)) {
            Converter bdConverter = new BigDecimalConverter();
            ((BigDecimalConverter) bdConverter).setPattern(format);
            BigDecimal bigDecimal = bdConverter.convert(BigDecimal.class, value);
            return bigDecimal;
        } else {
            return value;
        }
    }


}
