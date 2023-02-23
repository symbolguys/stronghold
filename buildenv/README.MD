# How to get started

### Start everything
`docker compose up -d`

### Just start a single container
`docker compose up -d --build postgres`

### Restart a container
- `docker compose stop postgres`
- `docker compose rn -f postgres`
- `docker compose up -d --build postgres`