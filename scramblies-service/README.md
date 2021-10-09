# Scramblies service

## CLI

Run server locally
```shell
bb run-server
```

Run tests
```shell
bb test
```

Run sample query:
```shell
curl -X GET http://localhost:3000/api/scramble/check\?text\="abcd"\&word\="abc" | jq
```
