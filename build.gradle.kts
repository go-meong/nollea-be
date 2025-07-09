plugins {
    kotlin("jvm") version "1.9.25"
    kotlin("plugin.spring") version "1.9.25"
    id("org.springframework.boot") version "3.5.3"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    // Spring Boot 설정 프로퍼티 애노테이션 프로세서
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    // Spring MVC 웹 스타터
    implementation("org.springframework.boot:spring-boot-starter-web")
    // Swagger UI 및 OpenAPI 문서 자동 생성용 라이브러리
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.7.0")
    // JDBC 데이터 연동 스타터
    implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
    // JPA (Hibernate) 기반 ORM 스타터
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    // MySQL JDBC 드라이버 (버전 명시)
    runtimeOnly("com.mysql:mysql-connector-j:8.0.32")
    // MongoDB 연동 스타터
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
    // Bean Validation 지원 스타터
    implementation("org.springframework.boot:spring-boot-starter-validation")
    // Spring Security 스타터
    implementation("org.springframework.boot:spring-boot-starter-security")
    // Kotlin Reflection API
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    // SLF4J 로깅 (Spring Boot 기본 로깅 지원, 필요시 명시)
    implementation("org.springframework.boot:spring-boot-starter-logging")
    // spring ai
    implementation("org.springframework.ai:spring-ai-openai-spring-boot-starter:1.0.0-M6")
    // 개발 편의성용 핫 리로딩 도구
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    // 통합 테스트 스타터 (JUnit, Mockito 포함)
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    // Kotlin용 JUnit5 테스트 라이브러리
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    // JUnit 플랫폼 런처
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.ai:spring-ai-bom:1.0.0-M6")
    }
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
