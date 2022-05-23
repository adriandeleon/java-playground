package com.grokthecode;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class AwsAppTest {

  private static AwsApp awsApp;
  private final String ruleDescription = "GitLab Backup";
  private final String groupId = "sg-03e1f3f58783316c8";

  @BeforeAll
  static void setup() {
    awsApp = new AwsApp();
  }

    @Test
    void describeEc2InstancesTest() {
      awsApp.describeEc2Instances();
    }

  @Test
  void describeSecurityGroupsTest() {
    awsApp.describeEC2SecurityGroups(groupId);
  }

  @Test
  void addEC2SecurityGroupsTest() {
    final HttpClientApp httpClientApp = new HttpClientApp();
    final String ip = httpClientApp.getIPFromHttpBinToDto().getOrigin();

    awsApp.addEC2SecurityGroupRule(groupId, ruleDescription, "-1", ip);
  }

  @Test
  void removeEC2SecurityGroupRuleTest() {
    awsApp.removeEC2SecurityGroupRule(groupId, ruleDescription);
  }

  @Test
  void getIpPermissonByDescriptionTest() {
    awsApp.getIpPermissonByDescription(groupId, ruleDescription);
  }
}
