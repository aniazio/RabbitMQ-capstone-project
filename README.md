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
exit
```

Next join rabbit-3:

```bash
docker exec -i -t rabbit-3 \bash
rabbitmqctl stop_app
rabbitmqctl join_cluster rabbit@rabbit1
rabbitmqctl start_app
exit
```

You can see your cluster under http://localhost:30001 (rabbit-1) or http://localhost:30003 (rabbit-2) or http://localhost:30005 (rabbit-3). Log in with credentials guest/guest. You can set credential using env variables RABBIT_USER and RABBIT_PASS.
There you can find also information about queues and messages.

Optionally you can set cluster name:
```bash
docker exec -it rabbit-1 rabbitmqctl set_cluster_name my_cluster
docker exec -it rabbit-2 rabbitmqctl set_cluster_name my_cluster
docker exec -it rabbit-3 rabbitmqctl set_cluster_name my_cluster
```

## Project structure

There are two submodules. One is producer-app with simple app, which sends messages to the queue and the other is consumer-app, which logs received messages.

## HA Check

To check if HA is working, you can stop one of the nodes and check if the messages are still being sent and received.

```bash
docker stop rabbit-1
```

You can see that messages are still being sent and received.
After that you can start rabbit-1 again and check that it rejoins the cluster.

```bash
docker start rabbit-1
```

## Clearing container

To clear container you can use the following command:

```bash
docker-compose down -v
```
