package com.grokthecode;

import lombok.extern.log4j.Log4j2;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ec2.Ec2Client;
import software.amazon.awssdk.services.ec2.model.*;

import java.util.List;

@Log4j2
public class AwsApp {

  public void describeEc2Instances() {
    try {
      final Region region = Region.US_EAST_1;
      final Ec2Client ec2 = Ec2Client.builder().region(region).build();

      final DescribeInstancesRequest request = DescribeInstancesRequest.builder().build();
      final DescribeInstancesResponse response = ec2.describeInstances(request);

      final List<Reservation> reservations = response.reservations();
      for (Reservation reservation : reservations) {
        final List<Instance> instances = reservation.instances();

        for (Instance instance : instances) {
          System.out.println(instance.instanceId());
        }
      }
      ec2.close();
      
    } catch (Ec2Exception e) {
      System.err.println(e.awsErrorDetails().errorMessage());
      System.exit(1);
    }
  }

  public static void describeEC2SecurityGroups(final String groupId) {
    try {
      final Region region = Region.US_EAST_1;
      final Ec2Client ec2 = Ec2Client.builder().region(region).build();

      final DescribeSecurityGroupsRequest request =
          DescribeSecurityGroupsRequest.builder().groupIds(groupId).build();

      final DescribeSecurityGroupsResponse response = ec2.describeSecurityGroups(request);

      for (SecurityGroup group : response.securityGroups()) {
        System.out.printf(
            "Found Security Group with id %s, " + "vpc id %s " + "and description %s",
            group.groupId(), group.vpcId(), group.description());
      }
      ec2.close();
    } catch (Ec2Exception e) {
      System.err.println(e.awsErrorDetails().errorMessage());
      System.exit(1);
    }
  }
}
