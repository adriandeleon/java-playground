package tests;

import com.grokthecode.App;

class AppTest {
    private static App app;


    //@BeforeAll
    static void setup() {
        app = new App();
        //logCaptor = LogCaptor.forClass(App.class);
    }

    //@AfterEach
    public void clearLogs() {
        //logCaptor.clearLogs();
    }

    //@AfterAll
    public static void tearDown() {
        //logCaptor.close();
    }
    
  //@Test
  //@Disabled
  void checkParamsFirstNameWithRequireNonNullTest() {
/*        Assertions.assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> app.checkParamsWithRequireNonNull(null, "De Leon"))
                .withMessageContaining(App.FIRST_NAME_CANNOT_BE_NULL);*/
  }
    //@Test
    //@Disabled
    void checkParamsLastNameWithRequireNonNullTest() {
/*        Assertions.assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> app.checkParamsWithRequireNonNull("Adrian", null))
                .withMessageContaining(App.LAST_NAME_CANNOT_BE_NULL);*/
    }

  //@Test
  //@Disabled
  void checkParamsFirstNameWithAnnotationTest() {
 /*     Assertions.assertThatExceptionOfType(NullPointerException.class)
              .isThrownBy(() -> app.checkParamsWithAnnotation(null, "De Leon"))
              .withMessageContaining("firstName is marked non-null but is null");*/
  }

    //@Test
    void checkParamsFirstNameWithAnnotationTest2() {
       app.checkParamsWithAnnotation(null, null);
    }

    //@Test
    //@Disabled
    void checkParamsLastNameWithAnnotationLogsTest() {
        app.checkParamsWithAnnotation("Adrian", "De Leon");
/*        assertThat(logCaptor.getLogs())
                .hasSize(2)
                .contains(
                        "firstName: Adrian",
                        "lastName: De Leon"
                );*/
    }
}
