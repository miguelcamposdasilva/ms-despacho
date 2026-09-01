# Despacho — Microservicio de riesgo despacho

Microservicio correspondiente al **caso caso10 — CargoClick** (Logística / courier) de la Evaluación Parcial N°1.

| | |
|---|---|
| Asignatura | JVY0101 — Java: Diseño y Construcción de Soluciones Nativas en Nube |
| Stack | Spring Boot 3.3 · Java 21 · Maven · Spring Data JPA · H2 · springdoc-openapi |
| Calidad | JaCoCo cobertura LINE 100% · Cucumber (BDD) alineado a endpoints REST |
| Entrega | Docker / Docker Compose |


## Modelo de ramificacion

### Modelos evaluados

| Modelo | Como funciona | Fortaleza | Decision |
|---|---|---|---|
| **GitFlow** | `main` + `develop` + `feature/*` + `hotfix/*` | Separa lo estable de lo que esta en integracion | **Elegido** |
| **GitHub Flow** | Solo `main` + ramas cortas con PR | Simple, ideal para despliegue continuo | Descartado: sin `develop` no hay donde acumular features entre entregas |
| **Trunk-based** | Rama unica, ramas de horas, feature flags | Integracion continua real, menos conflictos | Descartado: exige CI maduro y merges varias veces al dia |

### Modelo elegido: GitFlow

Elegimos este modelo de ramificacion debido a que es la mejor manera de ir aprendiendo como se trabaja 
a nivel profesional cuando hay proyectos grandes. Ademas el semestre de este ramo tiene planificadas varias entregas lo que calza perfectamente con releases planificadas. La rama develop permite integrar el trabajo de ambos sin dejar main inestable. Esta forma de trabajo nos permite corregir a tiempo errores, y desarrollar rapidamente nuevas funcionalidades, evitando pisar el y repetir codigos.  


## Responsabilidad (SRP)

administra los datos y la lógica del dominio de Despacho del caso caso10 (CargoClick). Su base de datos es una **H2 en memoria** (un solo microservicio por base), cumpliendo aislamiento de datos por dominio.

## Página de presentación

Al ejecutar el servicio, `http://localhost:8080/` muestra la página de presentación del microservicio con documentación y enlaces a:

- **Swagger UI**: `/swagger-ui/index.html`
- **OpenAPI (yaml)**: `/v3/api-docs.yaml`
- **ReDoc**: `/redoc.html`
- **H2 Console**: `/h2-console`

## Endpoints

| Método | Ruta | Descripción |
|--------|------|-------------|
| GET | `/api/despachos` | Lista todos los recursos |
| GET | `/api/despachos/{id}` | Obtiene un recurso por id |
| POST | `/api/despachos` | Crea un recurso |
| PUT | `/api/despachos/{id}` | Actualiza un recurso |
| DELETE | `/api/despachos/{id}` | Elimina un recurso |

## Documentación del proyecto

La documentación completa está en la carpeta [`docs/`](docs/):

- [`docs/00_Resumen.md`](docs/00_Resumen.md) — propósito, responsabilidad y tecnologías
- [`docs/01_Arquitectura.md`](docs/01_Arquitectura.md) — componentes, arquitectura y patrones
- [`docs/02_API.md`](docs/02_API.md) — contrato REST y ejemplos curl
- [`docs/03_Pruebas.md`](docs/03_Pruebas.md) — tests unitarios, cobertura y Cucumber
- [`docs/04_Despliegue.md`](docs/04_Despliegue.md)
- [`docs/05_Justificacion.md`](docs/05_Justificacion.md) — justificación del servicio: RF/RNF/seguridad cubiertos, stack y por qué cada tecnología AWS
- [`docs/diagramas/`](docs/diagramas/) — C4 (contexto, contenedores, componentes), secuencia e infraestructura AWS — Docker, Docker Compose e integración

## Cómo ejecutar locmente

```bash
mvn spring-boot:run
```

## Cómo ejecutar con Docker

```bash
docker compose up --build
# http://localhost:8080
```

## Cómo ejecutar las pruebas

```bash
mvn test      # unit tests + Cucumber
mvn verify    # + verificación de cobertura JaCoCo (100% LINE, falla si baja)
```
