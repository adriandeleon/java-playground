package com.grokthecode;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class AwsAppTest {

  private static AwsApp awsApp;

  @BeforeAll
  static void setup() {
    awsApp = new AwsApp();
  }

    @Test
    void describeEc2Instances() {
      awsApp.describeEc2Instances();
    }

  @Test
  void describeSecurityGroups() {
    awsApp.describeEC2SecurityGroups("sg-03e1f3f58783316c8");
  }
}
