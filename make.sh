#!/usr/bin/env bash

mvn -f pom.xml clean package -Plocal -U

cp /target/zookeeper-standalone.jar bin/zookeeper-standalone.jar
