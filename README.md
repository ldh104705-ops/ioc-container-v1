# IoC 컨테이너 클론 v1 — 하드코딩 빈 생성

스프링의 `ApplicationContext` 를 흉내 낸 IoC 컨테이너입니다. v1 은 빈 생성을 하드코딩합니다.

- `src/main/java/com/ll/ioc/ApplicationContext.java`
  - `genBean(빈이름)` : `testPostRepository`, `testPostService`, `testFacadePostService` 를 만듭니다.
  - 만든 빈은 `Map<String, Object>` 에 보관해, 같은 이름이면 같은 객체를 돌려줍니다. (싱글톤)
  - 의존 빈은 `genBean` 으로 먼저 얻어 생성자로 주입합니다.
    - `testPostService` ← `testPostRepository`
    - `testFacadePostService` ← `testPostService`, `testPostRepository`
- spring-context 같은 DI 프레임워크와 클래스 동적 로딩(`Class.forName`)은 쓰지 않습니다.
- `build.gradle.kts`, `src/test` 는 수정하지 않았습니다.

## 테스트

```
./gradlew test
```

`ApplicationContextTest` t1~t6 통과 (JDK 25 에서 확인)
