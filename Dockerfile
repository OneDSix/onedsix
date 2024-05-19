# This is an example dockerfile for a 1D6 dedicated server
# You will need to make the image yourself, sorry

# Set up Image
FROM ubuntu:22.04
RUN apt-get update && apt-get install -y openjdk-17-jdk
WORKDIR /

# Set up game
ADD server.jar .

# Its go time.
CMD ["java", "-jar", "modloader.jar", "-cr", "-t=server.jar"]
