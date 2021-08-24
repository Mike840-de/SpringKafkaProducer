package ru.mmtr.producer.config;

import lombok.AllArgsConstructor;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.DescribeClusterResult;
import org.springframework.kafka.core.KafkaAdmin;

@AllArgsConstructor
public class KafkaAdminClient {

    private KafkaAdmin kafkaAdmin;

    private void qwe(){
        AdminClient adminClient = AdminClient.create(kafkaAdmin.getConfigurationProperties());
        DescribeClusterResult describeClusterResult = adminClient.describeCluster();
    }
}
