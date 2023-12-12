version: '3'

services: 
    local-postgres-compose:
    image: postgres
    environment:
        POSTGRES_PASSWORD: "root"
    ports:
        - "5432:5432"
    volumes:
        - ./init.sql:/docker-entrypoint-initdb.d/init.sql
        - ./postgres-data:/var/lib/postgresql/data
    network:
        - postgres-compose-network

network:
    postgres-network-compose:
        driver: bridge
        