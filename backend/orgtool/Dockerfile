FROM openjdk:17

RUN apt-get update && \
    apt-get install -y curl gnupg && \
    curl -s https://packagecloud.io/install/repositories/rabbitmq/rabbitmq-server/script.deb.sh | bash && \
    apt-get install -y rabbitmq-server && \
    rabbitmq-plugins enable rabbitmq_management