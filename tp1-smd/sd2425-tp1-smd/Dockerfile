FROM smduarte/sd2425testerbase

# working directory inside docker image
WORKDIR /home/sd

ADD hibernate.cfg.xml .
ADD fctreddit.props .

ENV JAVA_OPS -XX:UseSVE=0
# copy the jar created by assembly to the docker image
COPY target/*jar-with-dependencies.jar sd2425.jar
