FROM hseeberger/scala-sbt:17.0.2_1.6.2_3.1.1
ENV DISPLAY=host.docker.internal:0.0
WORKDIR /madn-2
ADD . /madn-2
CMD sbt run