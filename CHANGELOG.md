# Changelog — ms-despacho

Formato basado en Keep a Changelog. Versionado semántico (MAJOR.MINOR.PATCH).

## [1.0.0] — 2026-08

### Added

- Versión inicial del microservicio Despacho (Spring Boot 3.3, Java 21).
- API REST `/api/despachos` con GET, GET/{id}, POST, PUT y DELETE.
- Persistencia H2 en memoria con Spring Data JPA.
- Documentación OpenAPI: Swagger UI, ReDoc y `/v3/api-docs.yaml`.
- Pruebas unitarias y BDD con Cucumber; cobertura JaCoCo 100% LINE.
- Empaquetado con Dockerfile multi-stage y Docker Compose.
- Pie de página con la versión del servicio en la página de presentación.

### Pendiente

- La versión del pie de página está escrita a mano en `index.html`: al subir
  la versión en `pom.xml` hay que actualizarla también aquí.
  (Observación levantada en la revisión del PR #1.)
