package com.grokthecode.examples.aws;

import com.grokthecode.examples.http.HttpClientApp;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import software.amazon.awssdk.services.ec2.model.Instance;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class AwsAppTest {

  private static AwsApp awsApp;
  private final String ruleDescription = "GitLab Backup";
  private final String securityGroupId = "sg-03e1f3f58783316c8";

  @BeforeAll
  static void setup() {
    awsApp = new AwsApp();
  }

  @Test
  @Disabled
  void getInstancesByTagNameValueTest() {
    final List<String> instancesList = awsApp.getInstancesByTagName("Bitnami-GitLab-RepoServer");
    assertThat(instancesList).isNotNull();
    System.out.println(instancesList.get(0).toString());
  }

  @Test
  @Disabled
  void getInstanceFromIdTest() {
    Optional<Instance> optionalInstance = awsApp.getInstanceById("i-0f0d68fc19d641464");
    assertThat(optionalInstance.get()).isNotNull();
  }

  @Test
  @Disabled
  void getSecurityGroupFromInstanceIdTest() {
    final List<String> securityGroupIdList = awsApp.getSecurityGroupById("i-0f0d68fc19d641464");
    assertThat(securityGroupIdList).isNotNull();
  }

    @Test
    @Disabled
    void describeEc2InstancesTest() {
      awsApp.describeEc2Instances();
    }

  @Test
  @Disabled
  void describeSecurityGroupsTest() {
    awsApp.describeEC2SecurityGroups(securityGroupId);
  }

  @Test
  @Disabled
  void addEC2SecurityGroupRuleTest() {
    awsApp.addEC2SecurityGroupRule(securityGroupId, ruleDescription, "-1", getPublicIp());
  }

  @Test
  @Disabled
  void removeEC2SecurityGroupRuleTest() throws Exception {
    awsApp.removeEC2SecurityGroupRule(securityGroupId, ruleDescription);
  }

  @Test
  @Disabled
  void getIpPermissonByDescriptionTest() throws Exception {
    awsApp.getIpPermissionByDescription(securityGroupId, ruleDescription);
  }

  @Test
  @Disabled
  void updateEC2SecurityGroupRuleTest(){
    final HttpClientApp httpClientApp = new HttpClientApp();
    final String ip = httpClientApp.getIPFromHttpBinToDto().getOrigin();

    awsApp.updateEC2SecurityGroupRule(securityGroupId, ruleDescription, getPublicIp());
  }

  private String getPublicIp(){
    final HttpClientApp httpClientApp = new HttpClientApp();
    return httpClientApp.getIPFromHttpBinToDto().getOrigin();
  }

  @Test
  void updateSecurityGroupRuleWithMyIp(){
    awsApp.updateSecurityGroupRuleWithMyIp("Bitnami-GitLab-RepoServer", "Adrian De Leon");
  }
}
