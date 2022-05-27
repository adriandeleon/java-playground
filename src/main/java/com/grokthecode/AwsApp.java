package com.grokthecode;

import lombok.extern.log4j.Log4j2;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ec2.Ec2Client;
import software.amazon.awssdk.services.ec2.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Log4j2
public class AwsApp {
  private final Region awsRegion = Region.US_EAST_1;

  public List<String> getInstancesByTagNameValue(final String tagNameValue) {
    final Ec2Client ec2Client = Ec2Client.builder().region(awsRegion).build();

    final var request = DescribeInstancesRequest.builder().build();
    final var response = ec2Client.describeInstances(request);

    final List<String> instanceIdList = new ArrayList<>();

    for (Reservation reservation : response.reservations()) {
      for (Instance instance : reservation.instances()) {
        for (Tag tag : instance.tags()) {
          if (tag.key().equals("Name")) {
            if (tag.value().contains(tagNameValue)) {
              instanceIdList.add(instance.instanceId());
            }
          }
        }
      }
    }
    return instanceIdList;
  }

  public Optional<Instance> getInstanceByInstanceId(final String instanceId){
    try {
      final Ec2Client ec2Client =  Ec2Client.builder().region(awsRegion).build();

      final var request = DescribeInstancesRequest.builder().build();
      final var response = ec2Client.describeInstances(request);

      for (Reservation reservation : response.reservations()) {
        for (Instance instance : reservation.instances()) {
          if(instance.instanceId().equals(instanceId)) {
            return Optional.of(instance);
          }
        }
      }
      ec2Client.close();

    } catch (Ec2Exception e) {
      log.error(e.awsErrorDetails().errorMessage());
      System.exit(1);
    }
    return Optional.empty();
  }

  public List<String> getSecurityGroupByInstanceId(final String instanceId){

  final Optional<Instance> optionalInstance = getInstanceByInstanceId(instanceId);
  final List<String> securityGroupId = new ArrayList<>();

     for (GroupIdentifier groupIdentifier : optionalInstance.get().securityGroups() ) {
       securityGroupId.add(groupIdentifier.groupId());
     }
     return  securityGroupId;
  }

  public void describeEc2Instances() {
    try {
      final Ec2Client ec2Client =  Ec2Client.builder().region(awsRegion).build();

      final var request = DescribeInstancesRequest.builder().build();
      final var response = ec2Client.describeInstances(request);

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
    final Ec2Client ec2Client =  Ec2Client.builder().region(awsRegion).build();
    try {
      final var request = DescribeSecurityGroupsRequest.builder().groupIds(groupId).build();
      final var response = ec2Client.describeSecurityGroups(request);

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
    final Ec2Client ec2Client =  Ec2Client.builder().region(awsRegion).build();

    try {
      final String authorizedIp = ip + "/32";

      final IpRange ipRange = IpRange.builder()
              .description(ruleDescription)
              .cidrIp(authorizedIp)
              .build();

      final IpPermission ipPermission = IpPermission.builder()
              .ipProtocol(protocol)
              .ipRanges(ipRange)
              .build();

      final var authorizeSecurityGroupIngressRequest = AuthorizeSecurityGroupIngressRequest.builder()
              .groupId(groupId)
              .ipPermissions(ipPermission)
              .build();

      final var authorizeSecurityGroupIngressResponse = ec2Client.authorizeSecurityGroupIngress(authorizeSecurityGroupIngressRequest);

      ec2Client.close();
    } catch (Ec2Exception e) {
      log.error(e.awsErrorDetails().errorMessage());
      System.exit(1);
    }
  }

  public void removeEC2SecurityGroupRule(final String groupId, final String ruleDescription) throws Exception {
    try {
      final Ec2Client ec2Client =  Ec2Client.builder().region(awsRegion).build();
      final Optional<IpPermission> optionalIpPermission = getIpPermissonByDescription(groupId, ruleDescription);
      final IpPermission ipPermission = optionalIpPermission.get();

      final var revokeSecurityGroupIngressRequest = RevokeSecurityGroupIngressRequest.builder()
              .groupId(groupId)
              .ipPermissions(ipPermission)
              .build();

      final var revokeSecurityGroupIngressResponse = ec2Client.revokeSecurityGroupIngress(revokeSecurityGroupIngressRequest);

      ec2Client.close();
    } catch (Ec2Exception e) {
      log.error(e.awsErrorDetails().errorMessage());
      System.exit(1);
    }
  }

  public Optional<IpPermission> getIpPermissonByDescription(final String groupId, final String ruleDescription) throws Exception {
    final Ec2Client ec2Client =  Ec2Client.builder().region(awsRegion).build();
    IpPermission ipPermissionFound = null;
    try {
      final var request = DescribeSecurityGroupsRequest.builder()
              .groupIds(groupId)
              .build();

      final var response = ec2Client.describeSecurityGroups(request);

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
      if (cidrIp == null) {
        throw new Exception("There is no security rule with that description");
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
    return Optional.of(ipPermissionFound);
  }

  public void updateEC2SecurityGroupRule(final String groupId, final String ruleDescription, final String ip) {
    try{
      final Optional<IpPermission> optionalIpPermission = getIpPermissonByDescription(groupId, ruleDescription);
      final String protocol = optionalIpPermission.get().ipProtocol();

      updateEC2SecurityGroupRule(groupId, ruleDescription, protocol, ip);
    } catch (Exception e) {
      log.error(e.getMessage());
      System.exit(1);
    }
  }

  public void updateEC2SecurityGroupRule(final String groupId, final String ruleDescription,
                                         final String protocol, final String ip) {
    try {
      removeEC2SecurityGroupRule(groupId, ruleDescription);
      addEC2SecurityGroupRule(groupId, ruleDescription, protocol, ip);
    } catch (Exception e) {
      log.error(e.getMessage());
      System.exit(1);
    }
  }
}
