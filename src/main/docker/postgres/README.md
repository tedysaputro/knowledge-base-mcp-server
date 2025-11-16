# Docker Setup for Knowledge Base MCP Server

## Structure

```
docker/
├── postgres/
│   ├── Dockerfile           # Custom PostgreSQL image with pgvector
│   ├── init-vector.sql      # SQL script to enable pgvector extension
│   └── README.md            # PostgreSQL-specific documentation
├── docker-compose.yml       # Docker Compose for local development
└── README.md               # This file
```

## Quick Start

### Option 1: Using Docker Compose (Recommended for local dev)

```bash
# Start PostgreSQL with pgvector
cd docker
docker-compose up -d

# Check logs
docker-compose logs -f postgres

# Stop
docker-compose down

# Stop and remove volumes
docker-compose down -v
```

### Option 2: Build and Run Manually

```bash
# Build custom image
cd docker/postgres
docker build -t knowledge-base-postgres:latest .

# Run container
docker run -d \
  --name kb-postgres \
  -e POSTGRES_USER=subrutin \
  -e POSTGRES_PASSWORD=subrutin \
  -e POSTGRES_DB=knowledge_base \
  -p 5433:5432 \
  knowledge-base-postgres:latest
```

### Option 3: Using Quarkus Dev Services (Automatic)

Quarkus will automatically use the custom image when you run:

```bash
mvn quarkus:dev -Dquarkus.profile=local
```

The configuration in `application.yml` specifies:
```yaml
devservices:
  image-name: knowledge-base-postgres:latest
```

## Verify pgvector Installation

```bash
# Connect to database
docker exec -it kb-postgres psql -U subrutin -d knowledge_base

# Check extension
SELECT * FROM pg_extension WHERE extname = 'vector';

# Should show:
#  extname | extversion
# ---------+------------
#  vector  | 0.8.0
```

## Production Deployment

### Push to Docker Registry

```bash
# Tag for your registry
docker tag knowledge-base-postgres:latest your-registry.com/knowledge-base-postgres:latest

# Push
docker push your-registry.com/knowledge-base-postgres:latest
```

### Update application.yml for production

```yaml
"%prod":
  quarkus:
    datasource:
      jdbc:
        url: jdbc:postgresql://your-db-host:5432/knowledge_base
      username: ${DB_USERNAME}
      password: ${DB_PASSWORD}
```

## Troubleshooting

### Image not found
If Quarkus can't find the image, make sure it's built:
```bash
docker images | grep knowledge-base-postgres
```

### Extension not enabled
Check init script execution:
```bash
docker logs kb-postgres | grep vector
```

### Port already in use
Change port in docker-compose.yml or stop conflicting service:
```bash
sudo lsof -i :5433
```
