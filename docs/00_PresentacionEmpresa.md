# CargoClick — Presentación del Caso de Negocio

**Documento para la Evaluación Parcial N°1 (EP01)**

---

## 1. Presentación de la empresa

**CargoClick** es una plataforma de logística y mensajería (courier) que permite crear envíos, cotizar fletes, coordinar recogida y entrega, seguir paquetes en tiempo real y facturar a clientes corporativos.

- **Rubro:** LogisticsTech / Courier y mensajería
- **Usuarios objetivo:** empresas remitentes, repartidores y clientes que reciben
- **Escala esperada:** miles de envíos diarios con seguimiento continuo de ubicación
- **Modelo de negocio:** tarifa por envío y contratos corporativos mensuales

## 2. Problema / necesidad de negocio

La logística depende del seguimiento en tiempo real y de eventos geográficos que ocurren en el campo (repartidores en movimiento). Un pico de envíos (Cyber Monday, fin de mes) no debe perder paquetes ni eventos GPS. CargoClick desacopla la creación de envíos, la asignación de repartidores y el tracking para escalar cada parte por separado.

## 3. Requisitos funcionales (RF)

| Código | Requisito | Dominio |
|--------|-----------|---------|
| RF-01 | Registro y autenticación de remitentes, repartidores y clientes | Usuarios |
| RF-02 | Crear y cotizar envíos (origen, destino, peso, urgencia) | Envíos |
| RF-03 | Asignar repartidor y optimizar rutas de recogida/entrega | Despacho |
| RF-04 | Registrar y consultar la trazabilidad del paquete en tiempo real | Tracking |
| RF-05 | Notificar eventos de estado al remitente y receptor | Notificaciones |
| RF-06 | Facturar envíos y administrar contratos corporativos | Facturación |
| RF-07 | Gestionar devoluciones y envíos fallidos | Devoluciones |
| RF-08 | Reportar métricas de tiempos y cumplimiento de entrega | Analítica |

## 4. Requisitos no funcionales (RNF)

| Código | Criterio | Requerimiento |
|--------|----------|---------------|
| RNF-01 | Escalabilidad | Procesar picos masivos de envíos y eventos GPS (miles de eventos/segundo) sin pérdida |
| RNF-02 | Disponibilidad | El tracking debe seguir funcionando aunque el despacho o la facturación falle |
| RNF-03 | Mantenibilidad | Aislar la asignación de rutas para actualizar el algoritmo sin tocar el tracking |
| RNF-04 | Seguridad | Protección de datos de remitentes y receptores, autenticación por rol, cifrado |
| RNF-05 | Rendimiento | Evento de tracking visible en menos de 5 segundos; cotización de envío en menos de 2 segundos |
| RNF-06 | Consistencia | Ningún evento de tracking ni envío debe perderse (colas persistentes) |

## 5. Dominios del negocio y microservicios propuestos

| Microservicio | Responsabilidad (SRP) | Justificación |
|---------------|------------------------|---------------|
| **Usuarios** | Identidad, autenticación y perfiles de los tres actores | Aislado por seguridad |
| **Envíos** | Creación, cotización y ciclo de vida del envío | Dominio transaccional central; se escala en picos |
| **Despacho** | Asignación de repartidores y optimización de rutas | Algoritmo intensivo que cambia por separado |
| **Tracking** | Ingesta de eventos GPS y consulta de trazabilidad | Alto throughput en tiempo real; requisitos propios |
| **Notificaciones** | Avisos de recogida, entrega y desvíos | Serverless, reutilizable, de alta frecuencia |
| **Facturación** | Tarifas, contratos corporativos y cobros | Datos sensibles de pago; aislado por seguridad |
| **Devoluciones** | Procesos de devolución y envíos fallidos | Reglas de negocio de excepción con ciclo propio |
| **Analítica** | Métricas de tiempos de entrega y cumplimiento | Reportes desacoplados de la operación |

## 6. Arquitectura cloud propuesta

- **Patrones de diseño:** API Gateway, Service Registry, Circuit Breaker en la integración con mapas/ruteo y pasarela de pago, Cola de mensajería (AWS SQS) para ingesta masiva de eventos GPS, event-driven para notificaciones y analítica.
- **Serverless:** AWS Lambda para ingerir eventos de tracking desde las apps de los repartidores, recalcular estados, enviar notificaciones y generar reportes; API Gateway expone la API; CloudWatch/Scheduler para procesos batch de facturación.
- **Almacenamiento:** base de datos por dominio; DynamoDB con TTL para eventos GPS temporales de alta escritura; Amazon S3 para documentación de envíos y respaldos; almacén de datos para analítica.
- **Seguridad:** autenticación JWT por rol, autorización por recurso (un remitente solo ve sus envíos), cifrado TLS y at-rest, control de acceso a datos de receptores, registro de auditoría de envíos.
- **Flujos principales:** remitente crea envío → se cotiza y confirma → evento a cola de despacho → se asigna repartidor → el repartidor envía posición GPS → Lambda ingiere el evento a DynamoDB → tracking consultable por API → eventos de estado notifican al remitente → al cierre se factura.

## 7. Pauta para el diagrama EP01 (checklist)

- [ ] Componentes cloud native y microservicios con sus funciones y relaciones (IE4)
- [ ] Patrones: API Gateway, Service Registry, Circuit Breaker, colas SQS (IE5)
- [ ] Flujos de comunicación y tecnologías cloud (Lambda, SQS, DynamoDB, S3) (IE6)
- [ ] Puntos de seguridad: autenticación por rol, autorización por recurso, cifrado, auditoría (IE7)
- [ ] Flujos de datos críticos: envío → cola → despacho → GPS → tracking → facturación, con monitoreo (IE8)
- [ ] Almacenamiento: bases por dominio, DynamoDB para GPS, S3 para documentos
- [ ] Criterios de escalabilidad, disponibilidad y mantenibilidad visibles en el diseño
