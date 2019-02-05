# Solution

## Running a Docker image

Start a container for the image with the following command:

```
$ docker run -d -p 8080:8080 todo-web-service:1.0
1984c2720d195d16d1a96eead5fa260e21e4ac9eadce200ab7c212bf87621ae1
```

You should be able to discover the running container with the following command:

```
$ docker container ls
CONTAINER ID        IMAGE                  COMMAND                  CREATED             STATUS              PORTS                    NAMES
1984c2720d19        todo-web-service:1.0   "java -jar /app/todo…"   15 seconds ago      Up 14 seconds       0.0.0.0:8080->8080/tcp   frosty_vaughan
```

You can run log into the Docker container with the `exec` command:

```
$ docker exec -it 1984c2720d19 sh
/ # ps -eaf | grep java
1 root      0:52 java -jar /app/todo-web-service.jar
/ # exit
```

Stopping and removing the container can be achieved with the following commands:

```
$ docker container stop 1984c2720d19
1984c2720d19
$ docker container rm 1984c2720d19
1984c2720d19
```
