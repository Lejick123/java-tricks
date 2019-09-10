FROM alpine:3.7

RUN apk update && apk upgrade && apk add bash

COPY some_project /

RUN apk add --virtual .build-dependencies musl-dev gcc libxslt-dev libxml2-dev python3-dev
RUN apk add py3-lxml \
RUN python setup.py install

ENTRYPOINT ["some_command", "--a", "default-parameter"]