package com.grokthecode;

import nl.altindag.log.LogCaptor;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class AppTest {
    private static App app;
    private static LogCaptor logCaptor;

    @BeforeAll
    static void setup() {
        app = new App();
        //logCaptor = LogCaptor.forClass(App.class);
    }

    @AfterEach
    public void clearLogs() {
        //logCaptor.clearLogs();
    }

    @AfterAll
    public static void tearDown() {
        //logCaptor.close();
    }
    
  @Test
  @Disabled
  void checkParamsFirstNameWithRequireNonNullTest() {
        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> app.checkParamsWithRequireNonNull(null, "De Leon"))
                .withMessageContaining(App.FIRST_NAME_CANNOT_BE_NULL);
  }
    @Test
    @Disabled
    void checkParamsLastNameWithRequireNonNullTest() {
        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> app.checkParamsWithRequireNonNull("Adrian", null))
                .withMessageContaining(App.LAST_NAME_CANNOT_BE_NULL);
    }

  @Test
  @Disabled
  void checkParamsFirstNameWithAnnotationTest() {
      assertThatExceptionOfType(NullPointerException.class)
              .isThrownBy(() -> app.checkParamsWithAnnotation(null, "De Leon"))
              .withMessageContaining("firstName is marked non-null but is null");
  }

    @Test
    void checkParamsFirstNameWithAnnotationTest2() {
       app.checkParamsWithAnnotation(null, null);
    }

    @Test
    @Disabled
    void checkParamsLastNameWithAnnotationLogsTest() {
        app.checkParamsWithAnnotation("Adrian", "De Leon");
        assertThat(logCaptor.getLogs())
                .hasSize(2)
                .contains(
                        "firstName: Adrian",
                        "lastName: De Leon"
                );
    }
}
