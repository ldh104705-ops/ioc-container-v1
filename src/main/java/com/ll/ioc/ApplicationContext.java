package com.ll.ioc;

import com.ll.domain.testPost.testPost.repository.TestPostRepository;
import com.ll.domain.testPost.testPost.service.TestFacadePostService;
import com.ll.domain.testPost.testPost.service.TestPostService;

import java.util.HashMap;
import java.util.Map;

/**
 * IoC 컨테이너 v1 - 빈 생성을 하드코딩한다.
 * - 빈은 이름으로 한 번만 만들어 beans 에 보관한다. (싱글톤)
 * - 의존 빈은 genBean 으로 먼저 얻어 생성자로 주입한다.
 */
public class ApplicationContext {
    private final Map<String, Object> beans = new HashMap<>();

    public ApplicationContext() {

    }

    @SuppressWarnings("unchecked")
    public <T> T genBean(String beanName) {
        Object bean = beans.get(beanName);

        if (bean == null) {
            bean = createBean(beanName);
            beans.put(beanName, bean);
        }

        return (T) bean;
    }

    private Object createBean(String beanName) {
        return switch (beanName) {
            case "testPostRepository" -> new TestPostRepository();
            case "testPostService" -> new TestPostService(
                    genBean("testPostRepository")
            );
            case "testFacadePostService" -> new TestFacadePostService(
                    genBean("testPostService"),
                    genBean("testPostRepository")
            );
            default -> throw new IllegalArgumentException("등록되지 않은 빈입니다: " + beanName);
        };
    }
}
