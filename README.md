# structured-logs

Este proyecto es una prueba de concepto (POC) para demostrar el uso de logs estructurados en una aplicación Java con Spring Boot.

## Propósito

El objetivo principal es mostrar cómo implementar logs estructurados para facilitar el análisis y la monitorización de aplicaciones. Los logs estructurados permiten registrar información en un formato consistente y fácil de procesar, lo que facilita la búsqueda, el filtrado y la agregación de datos.

## Configuración de Logback

El archivo `logback-spring.xml` define la configuración de logging para la aplicación. Se utilizan diferentes perfiles de Spring para configurar el formato de los logs:

- **default**: Configuración por defecto de Spring Boot. Este perfil es como si no tuviera el archivo `logback-spring.xml` y no utiliza logs estructurados en formato JSON.
- **logstash-default**: Configuración para usar Logstash con el formato JSON por defecto.
- **logstash-custom**: Configuración para usar un layout personalizado (`CustomJsonLayout`) para el formato JSON.
- **logstash-custom-pattern**: Configuración para usar un patrón personalizado para el formato JSON.
- **logstash-custom-providers**: Configuración para usar providers personalizados para el formato JSON.

## Uso

Para activar un perfil específico, se puede usar la propiedad `spring.profiles.active` en el archivo `application.properties`. Por ejemplo, para activar el perfil `logstash-custom`, se puede agregar la siguiente línea:

```properties
spring.profiles.active=logstash-custom
```

También se puede ejecutar el proyecto con comandos Maven y elegir el perfil desde la línea de comandos. Por ejemplo, para ejecutar el proyecto con el perfil `logstash-custom`, se puede usar el siguiente comando:

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=logstash-custom
```

## Configuración en application.properties

El archivo `application.properties` también permite configurar el formato de los logs y personalizar el formato JSON.

- `logging.structured.format.console`: Define el formato de los logs en la consola. Puede ser `logstash` para usar el formato JSON de Logstash o la clase `pe.seek.structuredlogs.logging.CustomJsonLayout` para usar el layout personalizado.
- `logging.structured.format.file`: Define el formato de los logs en el archivo. Puede ser `logstash` para usar el formato JSON de Logstash o la clase `pe.seek.structuredlogs.logging.CustomJsonLayout` para usar el layout personalizado.
- `logging.structured.json.add.`: Permite agregar campos adicionales al formato JSON.
- `logging.structured.json.include`: Permite incluir campos específicos en el formato JSON.
- `logging.structured.json.exclude`: Permite excluir campos específicos del formato JSON.
- `logging.structured.json.rename.fieldName`: Permite renombrar campos en el formato JSON.

## CustomJsonLayout

La clase `CustomJsonLayout` (ubicada en `src/main/java/pe/seek/structuredlogs/logging/CustomJsonLayout.java`) es un layout personalizado que formatea los logs en JSON. Permite agregar campos adicionales a los logs, como el nombre del evento y el tag.

## Beneficios de los Logs Estructurados

- **Facilidad de análisis**: Los logs estructurados son más fáciles de analizar que los logs tradicionales, ya que la información se registra en un formato consistente.
- **Monitorización**: Los logs estructurados facilitan la monitorización de aplicaciones, ya que se pueden usar herramientas como Logstash y Elasticsearch para buscar, filtrar y agregar datos.
- **Depuración**: Los logs estructurados facilitan la depuración de aplicaciones, ya que se puede encontrar información relevante de forma rápida y sencilla.

## Ejemplos de Uso

### Ejemplo de Log con `CustomJsonLayout`

Un ejemplo de log formateado con `CustomJsonLayout` podría ser:

```json
{
  "timestamp": "2025-03-03 18:00:00.000",
  "level": "INFO",
  "message": "Esto es un mensaje de INFO",
  "event": "getAll",
  "tag": "pe.seek.structuredlogs.service.UserService"
}
```

### Explicación de MDC

El `MDC` (Mapped Diagnostic Context) permite agregar información contextual a los logs. En el ejemplo del `UserService`, se usa el `MDC` para agregar información del usuario al log.

### Ejemplo de Log con `StructuredArguments`

`StructuredArguments` permite agregar información estructurada a los logs. En el ejemplo del `UserService`, se usan `kv` y `v` para agregar información del usuario y su ID al log.

### Perfiles de Logback

Cada perfil de Logback define una configuración diferente para el formato de los logs. El perfil `logstash-default` es útil cuando se usa Logstash para procesar los logs.
