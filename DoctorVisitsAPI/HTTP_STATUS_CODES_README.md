# HTTP Status Codes Más Usados en APIs REST

## ✅ 2xx – Success

| Código | Nombre     | Cuándo usarlo                                                                 |
|--------|------------|------------------------------------------------------------------------------|
| 200    | OK         | Petición exitosa (GET, PUT, DELETE). Devuelve datos si aplica.              |
| 201    | Created    | Cuando se crea un recurso exitosamente (por ejemplo, POST para insertar).   |
| 204    | No Content | Operación exitosa sin cuerpo en la respuesta (por ejemplo, DELETE).         |

## ⚠️ 4xx – Client Errors

| Código | Nombre               | Cuándo usarlo                                                               |
|--------|----------------------|------------------------------------------------------------------------------|
| 400    | Bad Request          | Petición malformada o con datos inválidos.                                  |
| 401    | Unauthorized         | Cuando no se proporcionan credenciales válidas.                             |
| 403    | Forbidden            | El usuario está autenticado, pero no tiene permisos.                        |
| 404    | Not Found            | El recurso solicitado no existe.                                            |
| 409    | Conflict             | Cuando hay conflicto en el estado del recurso (ej: duplicado).              |
| 422    | Unprocessable Entity | Datos válidos sintácticamente, pero lógicos o de negocio inválidos.         |

## ❌ 5xx – Server Errors

| Código | Nombre                 | Cuándo usarlo                                                                  |
|--------|------------------------|---------------------------------------------------------------------------------|
| 500    | Internal Server Error  | Error inesperado en el servidor (excepción no controlada, por ejemplo).        |
| 503    | Service Unavailable    | Servicio no disponible temporalmente (por mantenimiento o sobrecarga).        |
