FROM java:latest
MAINTAINER Sho Kohara <skohar@users.noreply.github.com>
WORKDIR /opt/docker
ADD opt /opt
RUN ["chown", "-R", "daemon:daemon", "."]
USER daemon
ENTRYPOINT ["bin/docker-swarm-controller"]
CMD []
