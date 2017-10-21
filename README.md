# spring-zookeeper-standalone-server

A zookeeper standalone server for integration testing implemented with web api using spring boot

Simple java zookeeper standalone server that can run on both windows and other OS without additional setup

# Feature

* Standalone zookeeper server that can run without additional installation on Windows, Linux and MacOS.
* Allow shut down of the zookeeper server remotely via web api call

# Usage
copy the zookeeper-standalone.jar to your directory and run the following command:

```bash
java -jar zookeeper-standalone.jar
```

This will start the zookeeper server at port 6379 and start another web server at port 7379.

To check whether the zookeeper server is alive, call the following url:

http://localhost:4181/ping

To kill the zookeeper server remotely, just call the following url:

http://localhost:4181/kill

# Note
In case you want to modify the behavior the zookeeper server and want to rebuild, you can run the make.ps1 on Windows
or make.sh on Linux or Mac.

