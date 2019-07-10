package com.ck.orangeblogcommon.annotation;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RecordBlogView {
    String value() default "";
}
