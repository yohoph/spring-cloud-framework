package com.jmsw.framework.core.elasticjob;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Simple {
    String cron();

    String description() default "";

    int shardingTotalCount() default 1;

    boolean disabled() default false;

    boolean overwrite() default false;
}
