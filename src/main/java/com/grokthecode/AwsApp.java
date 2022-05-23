package com.grokthecode;

import lombok.extern.log4j.Log4j2;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ec2.Ec2Client;
import software.amazon.awssdk.services.ec2.model.*;

import java.util.List;

@Log4j2
public class AwsApp {
  private final Region region = Region.US_EAST_1;

  public void describeEc2Instances() {
    try {
      final Ec2Client ec2Client =  Ec2Client.builder().region(region).build();

      final DescribeInstancesRequest request = DescribeInstancesRequest.builder().build();
      final DescribeInstancesResponse response = ec2Client.describeInstances(request);

      final List<Reservation> reservations = response.reservations();

      for (Reservation reservation : reservations) {
        final List<Instance> instances = reservation.instances();

        for (Instance instance : instances) {
          log.info(instance.instanceId());
        }
      }
      ec2Client.close();

    } catch (Ec2Exception e) {
      log.error(e.awsErrorDetails().errorMessage());
      System.exit(1);
    }
  }

  public void describeEC2SecurityGroups(final String groupId) {
    final Ec2Client ec2Client =  Ec2Client.builder().region(region).build();
    try {
      final DescribeSecurityGroupsRequest request =
          DescribeSecurityGroupsRequest.builder().groupIds(groupId).build();

      final DescribeSecurityGroupsResponse response = ec2Client.describeSecurityGroups(request);

      for (SecurityGroup group : response.securityGroups()) {
        log.info(String.format("Found Security Group with id %s, " + "vpc id %s " + "and description %s",
            group.groupId(), group.vpcId(), group.description()));
      }
      ec2Client.close();
    } catch (Ec2Exception e) {
      log.error(e.awsErrorDetails().errorMessage());
      System.exit(1);
    }
  }

  public void addEC2SecurityGroupRule(final String groupId, final String ruleDescription,
                                      final String protocol, final String ip) {
    final Ec2Client ec2Client =  Ec2Client.builder().region(region).build();

    try {
      String authorizedIp = ip + "/32";

      final IpRange ipRange = IpRange.builder()
              .description(ruleDescription)
              .cidrIp(authorizedIp)
              .build();

      final IpPermission ipPermission = IpPermission.builder()
              .ipProtocol(protocol)
              .ipRanges(ipRange)
              .build();

      final AuthorizeSecurityGroupIngressRequest authorizeSecurityGroupIngressRequest = AuthorizeSecurityGroupIngressRequest.builder()
              .groupId(groupId)
              .ipPermissions(ipPermission)
              .build();

      final AuthorizeSecurityGroupIngressResponse authorizeSecurityGroupIngressResponse = ec2Client.authorizeSecurityGroupIngress(authorizeSecurityGroupIngressRequest);

      ec2Client.close();
    } catch (Ec2Exception e) {
      log.error(e.awsErrorDetails().errorMessage());
      System.exit(1);
    }
  }

  public void removeEC2SecurityGroupRule(final String groupId, final String ruleDescription) {
    try {
      final Ec2Client ec2Client =  Ec2Client.builder().region(region).build();
      final IpPermission ipPermission = getIpPermissonByDescription(groupId, ruleDescription);

      final RevokeSecurityGroupIngressRequest revokeSecurityGroupIngressRequest = RevokeSecurityGroupIngressRequest.builder()
              .groupId(groupId)
              .ipPermissions(ipPermission)
              .build();

      final RevokeSecurityGroupIngressResponse revokeSecurityGroupIngressResponse = ec2Client.revokeSecurityGroupIngress(revokeSecurityGroupIngressRequest);

      ec2Client.close();
    } catch (Ec2Exception e) {
      log.error(e.awsErrorDetails().errorMessage());
      System.exit(1);
    }
  }

  public IpPermission getIpPermissonByDescription(final String groupId, final String ruleDescription) {
    final Ec2Client ec2Client =  Ec2Client.builder().region(region).build();
    IpPermission ipPermissionFound = null;
    try {
      final DescribeSecurityGroupsRequest request = DescribeSecurityGroupsRequest.builder()
              .groupIds(groupId)
              .build();

      final DescribeSecurityGroupsResponse response = ec2Client.describeSecurityGroups(request);
      String cidrIp = null;
      String description = null;
      String protocol = null;

      for (SecurityGroup group : response.securityGroups()) {
        for (IpPermission ipPermission : group.ipPermissions()) {
          for (IpRange ipRange : ipPermission.ipRanges()) {
            if (ipRange.description().equals(ruleDescription)) {
              log.info(ipRange.description());
              cidrIp = ipRange.cidrIp();
              description = ipRange.description();
              protocol = ipPermission.ipProtocol();
            }
          }
        }
      }
      final IpRange iprange = IpRange.builder()
              .cidrIp(cidrIp)
              .description(description)
              .build();
      ipPermissionFound = IpPermission.builder()
              .ipRanges(iprange)
              .ipProtocol(protocol)
              .build();

      ec2Client.close();

    } catch (Ec2Exception e) {
      log.error(e.awsErrorDetails().errorMessage());
      System.exit(1);
    }
    return ipPermissionFound;
  }
}
