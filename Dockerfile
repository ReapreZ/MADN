#FROM hseeberger/scala-sbt:8u222_1.3.5_2.13.1
#ENV DISPLAY=host.docker.internal:0.0
#RUN apt-get update && \
#    apt-get install -y --no-install-recommends openjfx && \
#    rm -rf /var/lib/apt/lists/* && \
#    apt-get install -y sbt libxrender1 libxtst6 libxi6
#WORKDIR /madn
#ADD . /madn
#EXPOSE 8080
#CMD sbt run
FROM sbtscala/scala-sbt:eclipse-temurin-jammy-11.0.17_8_1.8.2_2.13.10
WORKDIR /madn
ADD ./controller /madn/controller
ADD ./gui /madn/gui
ADD ./model /madn/model
ADD ./rest /madn/rest
ADD ./tui /madn/tui
ADD ./src /madn/src
ADD ./util /madn/util
ADD ./io /madn/io
ADD ./build.sbt /madn/build.sbt
ADD ./project/plugins.sbt /madn/project/plugins.sbt
CMD sbt