Experimenting with Spring Webflux and Server Sent Events.

## Run

```
./run.sh
```

## Send a message to someone

```
curl -H "Content-Type: application/json" localhost:8080/message -d "{
    \"sender\": {
        \"id\": \"5\", 
        \"name\": \"evan\"
    }, 
    \"recipientId\": \"6\", 
    \"text\": \"hello\", 
    \"sentOn\": \"2019-02-20T23:10:08.062+0000\"
}"
```

## Receive messages by recipient ID

```
curl localhost:8080/events/6 -H "Last-Event-ID: 5"
```
