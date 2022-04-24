package com.github.lianjiatech.retrofit.spring.boot.degrade.resilience4j;

import java.lang.annotation.*;

import com.github.lianjiatech.retrofit.spring.boot.degrade.Degrade;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;

/**
 * 应仅采用异常比例模式来控制熔断，超时导致的报错应在okhttp这一层做
 * @author 陈添明 yukdawn@gmail.com
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@Documented
@Degrade(register = Resilience4jDegradeRuleRegister.class)
public @interface Resilience4jDegrade {

    /**
     * 滑动窗口的类型
     * @return
     */
    CircuitBreakerConfig.SlidingWindowType slidingWindowType() default CircuitBreakerConfig.SlidingWindowType.TIME_BASED;
    /**
     * 窗口的大小
     * @return
     */
    int slidingWindowSize() default DEFAULT_SLIDING_WINDOW_SIZE;
    /**
     * 在单位窗口内最少需要几次调用才能开始进行统计计算
     * @return
     */
    int minimumNumberOfCalls() default DEFAULT_MINIMUM_NUMBER_OF_CALLS;
    /**
     * 单位时间窗口内调用失败率达到多少后会启动断路器
     * @return
     */
    float failureRateThreshold() default DEFAULT_FAILURE_RATE_THRESHOLD;
    /**
     * 允许断路器自动由打开状态转换为半开状态
     * @return
     */
    boolean enableAutomaticTransitionFromOpenToHalfOpen() default DEFAULT_ENABLE_AUTOMATIC_TRANSITION_FROM_OPEN_TO_HALF_OPEN;
    /**
     * 在半开状态下允许进行正常调用的次数
     * @return
     */
    int permittedNumberOfCallsInHalfOpenState() default DEFAULT_PERMITTED_NUMBER_OF_CALLS_IN_HALF_OPEN_STATE;
    /**
     * 断路器打开状态转换为半开状态需要等待秒数
     * @return
     */
    int waitDurationInOpenState() default DEFAULT_WAIT_DURATION_IN_OPEN_STATE;

    int DEFAULT_SLIDING_WINDOW_SIZE = 60;
    int DEFAULT_MINIMUM_NUMBER_OF_CALLS = 10;
    float DEFAULT_FAILURE_RATE_THRESHOLD = 60F;
    boolean DEFAULT_ENABLE_AUTOMATIC_TRANSITION_FROM_OPEN_TO_HALF_OPEN = true;
    int DEFAULT_PERMITTED_NUMBER_OF_CALLS_IN_HALF_OPEN_STATE = 5;
    int DEFAULT_WAIT_DURATION_IN_OPEN_STATE = 60;
}
