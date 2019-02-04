# Solution

Log into Docker Hub by using the `login` command. Provide your username and enter the password.

```
$ docker login --username=bmuschko
Password:
Login Succeeded
```

Tag the existing image with your username prefix:

```
$ docker tag e1fff5cfdeba bmuschko/todo-web-service:1.0.0
```

You should find the tagged image in the list of existing images:

```
$ docker images
REPOSITORY                                 TAG                 IMAGE ID            CREATED             SIZE
bmuschko/todo-web-service                  1.0.0               e1fff5cfdeba        About an hour ago   121MB
todo-web-service                           1.0                 e1fff5cfdeba        About an hour ago   121MB
```

Push the tagged image to Docker Hub:

```
$ docker push bmuschko/todo-web-service:1.0.0
The push refers to repository [docker.io/bmuschko/todo-web-service]
dd3ad61a6351: Pushed
8bc7bbcd76b6: Layer already exists
298c3bb2664f: Layer already exists
73046094a9b8: Layer already exists
1.0.0: digest: sha256:8712443928d3f594fde6d028e6a6306c1e358e1d1865831a08d0e5ff4953b743 size: 1159
```