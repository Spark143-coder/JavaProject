# Use base image with Java (JDK 24)
FROM eclipse-temurin:24-jdk

#Install python and Ant
RUN apt-get update && apt-get install -y \
    python3 \
    python3-pip \
    ant \
    && rm -rf /var/lib/apt/lists/*

# Set the working directory
WORKDIR /app

# Copy all the files from current directory to container
COPY . .

# Set JAVA_HOME properly for Ant (actual path used by Eclipse Temurin)
ENV JAVA_HOME=/opt/java/openjdk
ENV PATH="$JAVA_HOME/bin:$PATH"

# Build Java code using Ant with JAVA_HOME set
RUN JAVA_HOME=/opt/java/openjdk ant

# Build the Java Project using Ant
RUN ant

# Making sure that python script is executable
RUN chmod u+x my_exe start.sh

# Default command to run your Python runner script
ENTRYPOINT ["./start.sh"]
