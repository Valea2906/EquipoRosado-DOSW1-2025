# EquipoRosado-DOSW1-2025


# FurniStore

Breve descripción: FurniStore es una aplicación para la gestión digital de inventario, ventas y entregas de muebles.

## Tabla de contenidos
- Instalación
- Dependencias
- Ejecutar localmente
- Endpoints (OpenAPI / Swagger)
- Estructura del proyecto
- Diagrama de contexto, casos de uso, diagrama de clases
- Estrategia de ramas (GitFlow) y convenciones de commits
- Cómo ejecutar tests y generar reporte de cobertura (JaCoCo)
- Requisitos del entorno


### Diagramas

## Dagrama de contexto 👤
![diagram context.png](docs/uml/diagram%20context.png)


- 👤 **Customer** : Cliente final que consulta el catálogo, compra muebles y realiza pedidos.
- 🖥️ **FurniStore System** : Sistema central de la aplicación que gestiona productos, pedidos, pagos y comunicación con servicios externos.
- 🔗 **Sistemas externos** :
    - **Payment Gateway**: Procesa los pagos de manera segura.
    - **Shipping Provider**: Valida la disponibilidad de productos y gestiona reposición de inventario.
    - **Supplier System**: Coordina envíos y entregas de pedidos a los clientes.

Las flechas reflejan los **flujos de información** entre los actores y el sistema:
- El *Customer* interactúa con *FurniStore System* para navegar el catálogo y realizar compras.
- El sistema se conecta con los proveedores externos para procesar pagos, coordinar envíos y mantener la disponibilidad de inventario.

👉 Este diagrama permite comprender de forma rápida:
1. **Los límites del sistema** (qué hace FurniStore y qué delega a terceros).
2. **Los actores involucrados** (usuarios y servicios externos).
3. **Las interacciones clave** de alto nivel, sin entrar en detalles técnicos internos.

## 🗂️ Diagrama de Casos de Uso 
![diagram Uso.png](docs/uml/diagram%20Uso.png)

- 🔎 **Consultar catálogo de muebles**: el cliente puede navegar y revisar los productos disponibles.
- 🛒 **Realizar pedido**: seleccionar los muebles deseados y crear un pedido en el sistema.
- 💳 **Pagar pedido**: efectuar el pago a través de la pasarela de pagos integrada.
- 📦 **Consultar estado de envío**: verificar el estado de la entrega de sus pedidos.  

## 📊 Diagrama de clases 

![diagram Clases.png](docs/uml/diagram%20Clases.png)

# Clases principales


- Se manejaron los nombres en ingles ya que se siguio el consejo del profesor y asi  sera mas practico.

**Product (abstracta)**  
Representa cualquier tipo de producto en la tienda, como sofás, sillas o camas.  
Contiene atributos básicos.  
Permite aumentar o disminuir el stock mediante los métodos increaseStock y decreaseStock.  
Es abstracta para que se puedan crear subtipos específicos de productos en el futuro.

**Customer**  
Representa a un cliente de la tienda.  
Contiene información de contacto.  
Puede realizar pedidos, representados por la relación con la clase Order.

**Order**  
Representa un pedido realizado por un cliente.  
Contiene información del pedido`.  
Permite agregar productos y calcular el total del pedido.

**Inventory**  
Representa el stock disponible de un producto.   
Permite actualizar la cantidad de stock mediante updateStock.

**Supplier**  
Representa a los proveedores que suministran productos a la tienda.  
Contiene información de contacto y métodos para enviar productos al inventario.

## 🧩 Patrones de diseño utilizados 

**Singleton:** para servicios que deben existir una única instancia (por ejemplo, `OrderService`).

**Factory Method:** permite instanciar subtipos de `Product` sin depender de la clase abstracta.

**Repository/Service Layer:** separa la lógica de negocio de los controllers, facilitando pruebas unitarias.

---

## ✅ Principios SOLID aplicados

**S – Single Responsibility:** cada clase tiene una sola responsabilidad (`Product`, `Customer`, `Order`).

**O – Open/Closed:** `Product` es abstracta y se puede extender para nuevos tipos de muebles.

**L – Liskov Substitution:** subclases de `Product` se pueden usar sin alterar el comportamiento del sistema.

**I – Interface Segregation:** cada servicio expone solo los métodos necesarios para su función.

**D – Dependency Inversion:** los controllers dependen de abstracciones (`Service`) y no de implementaciones concretas.


## 📊 Cobertura de pruebas

- Todas las clases modelo, servicios y controllers tienen pruebas unitarias con JUnit 5.

![img.png](docs/img/img.png)