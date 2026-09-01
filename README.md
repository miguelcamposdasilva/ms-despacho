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

### Reflexión en el uso de la IA 
Nuestra reflexión sobre el uso de la IA es que es una excelente herramienta de apoyo siempre y cuando se utilice con el debido cuidado y responsabilidad que corresponde. No reemplaza la tarea como profesional informatico, sino que la potencia y ayuda a ser mas productivo. Siempre se debe revisar lo que nos proporciona la IA ya que comete errores que pueden costar muy caro en la ejecución de un proyecto. 


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


### Cómo escribir los commits
Para que el historial quede limpiecito y se entienda qué hizo cada uno, usaremos este formato:
`tipo(alcance): descripcion en imperativo`

**Regla de oro:** Todo en minúsculas, sin tildes (para evitar cachos con la codificación) y máximo 72 caracteres de largo.

| Tipo | Cuándo usarlo | Ejemplo |
|---|---|---|
| `feat` | Cuando agregamos algo nuevo | `feat(ui): agregar pie de pagina con version del servicio` |
| `fix` | Cuando arreglamos un bug | `fix(ui): corregir titulo de la pagina de presentacion` |
| `docs` | Cosas del README o documentación | `docs: agregar changelog del microservicio despacho` |
| `chore` | Tareas de configuración, GitHub Actions, etc. | `chore(ci): agregar workflow hola mundo` |
| `test` | Agregar o modificar pruebas | `test(service): cubrir caso de despacho inexistente` |
| `refactor` | Mejorar código sin cambiar lo que hace | `refactor(repository): extraer consulta por ruta` |

*Ojo: Para los alcances (scopes), tratemos de usar solo estos:* `ui`, `api`, `service`, `repository`, `ci`, `docker`, o `docs`.

### Nombres de las ramas
El formato es: `tipo/descripcion-corta`. Todo en minúsculas, separado por guiones, sin tildes ni caracteres raros, y ojalá no más de 4 palabras para no hacerla tan larga.

- Si sale de `develop` ➡️ `feature/nombre-de-tu-rama` (Ej: `feature/pie-version`)
- Si es una urgencia en `main` ➡️ `hotfix/nombre-del-arreglo` (Ej: `hotfix/titulo-pagina`)

 **Prohibido:** Nombres genéricos como `arreglos`, `cambios-juan`, `test2` o ramas que no tengan el prefijo.


### Flujo de Merge (Pull Requests)
1. **Cero push directo:** Nadie le hace push directo a `main` ni a `develop`. Todo entra por Pull Request (PR).
2. **Revisión obligatoria:** Todo PR necesita al menos **1 aprobación** del compañero. GitHub bloquea aprobarse uno mismo, así que siempre revisa el otro.
3. El semáforo de GitHub Actions (CI) tiene que estar en verde. Si las pruebas fallan, no se mergea.
4. **Las rutas:** Las ramas `feature/*` van hacia `develop`. Las ramas `hotfix/*` van a `main` (y después hay que acordarse de pasar ese arreglo de `main` a `develop` para no perderlo).
5. Hacemos *merge commit* al fusionar para que nos quede la historia clara en el árbol.
6. Una vez aprobado y fusionado el PR, borramos la rama para no acumular basura.

### 👀 5. Revisión de código
- El que revisa tiene que mirar de verdad la pestaña de *Files changed* y dejar mínimo un comentario (aunque sea un "todo bien"). ¡No vale aprobar a ciegas!
- **Antes de subir el PR**, corre un `mvn verify` en tu compu y asegúrate de que la cobertura de pruebas siga al 100%. 
- Nos vamos turnando: un PR lo hace uno y lo revisa el otro, y al siguiente cambiamos de rol.

### 🏷️ 6. Control de Versiones
Vamos a usar versionado semántico (`MAJOR.MINOR.PATCH`) en el `pom.xml` y en el `CHANGELOG.md`.

- **PATCH** (ej: v1.0.0 a v1.0.1): Lo subimos cuando hacemos un hotfix.
- **MINOR** (ej: v1.0.0 a v1.1.0): Lo subimos cuando juntamos varias features nuevas en una release.
- **MAJOR** (ej: v1.0.0 a v2.0.0): Solo si hacemos un cambio gigante que rompa la compatibilidad de la API.

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
