package na.com.meatco.sageX3integration.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface InvoiceField {

    public String value() default "";
    public boolean many() default false;
    public String typeOfMany() default "";

}
