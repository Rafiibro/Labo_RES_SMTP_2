FROM java:8

COPY MockMock-1.4.0.one-jar.jar /opt/app/mockmock.jar

#
# Our application will accept TCP connections on port 2205. Note that the EXPOSE statement
# does not make the container accessible via the host. For the container to really be accessible,
# we must either use the -p or the -P flag when using the docker run command. With -p, we can
# specify an explicit port mapping (and EXPOSE is not even required). With -P, we let Docker 
# assign random ports for each EXPOSEd port. We can then use the docker port command to know the port
# numbers that have been selected.
#

ENV MOCKMOCK_SMPT_PORT=25
ENV MOCKMOCK_HTTP_PORT=8282

EXPOSE 2525
EXPOSE 8282

#
# This is the command that is executed when the Docker container starts
#
ENTRYPOINT java -jar /opt/app/mockmock.jar -p $MOCKMOCK_SMPT_PORT -h $MOCKMOCK_HTTP_PORT
#CMD ["java", "-jar", "/opt/app/mockmock.jar", "-p", "2525", "-h", "8282"]