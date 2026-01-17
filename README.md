# Rabbit MQ project

## Description

This project is a learning Rabbit MQ project that uses Spring Boot and RabbitMQ to create a message queue system.

## Cluster set up

To set up a RabbitMQ cluster, you can use the following command to start containers:

```bash
docker-compose up -d
```

Then to join containers to have a one cluster, you need to stop an app on rabbit-2 and rabbit-3 and join this nodes to the cluster.

First join rabbit-2:

```bash
docker exec -i -t rabbit-2 \bash
rabbitmqctl stop_app
rabbitmqctl join_cluster rabbit@rabbit1
rabbitmqctl start_app
```

Next join rabbit-3:

```bash
docker exec -i -t rabbit-3 \bash
rabbitmqctl stop_app
rabbitmqctl join_cluster rabbit@rabbit3
rabbitmqctl start_app
```

You can see your claster under http://localhost:30001. Log in with credentials admin/admin. You can set credatial using env variables RABBIT_USER and RABBIT_PASS.
