![Duoc UC](https://www.duoc.cl/wp-content/uploads/2022/09/logo-0.png)
# 🧠 Semana 8 – Desarrollo Orientado a Objetos II

## 👤 Autor del proyecto
- **Nombre completo:** Javiera Puga
- **Sección:** 002A
- **Carrera:** Analista Programador Computacional
- **Sede:** Campus Online

---

## 📘 Descripción general del sistema
Este proyecto corresponde a la entrega de la semana 8 de *Desarrollo Orientado a Objetos II*. En esta instancia, se demuestra el dominio de la creación de una interfaz gráfica con Swing, y la conexión a base de datos con JSBC.

El proyecto fue desarrollado a partir de un caso contextualizado, "Speedfast", relacionado al delivery, incluyendo distintos pedidos y repartidores.

---

## 🧱 Estructura general del proyecto

```plaintext
📁 src/
├── ui/          # Clase principal con el método main
├── model/       # Clases y subclases (Pedido, Repartidor, Entrega, Dirección, PedidoComida...)
├── controller/  # Incluye la clase ConexionDB, que conecta proyecto con base de datos
├── dao/         # Clases DAO que manejar el agregar y quitar datos a la BDD
├── vista/       # Ventanas que utilizan JFrame para la interfaz gráfica
└── interfaces/  # Interfaces Despachable y Cancelable
````

---

## ⚙️ Instrucciones para clonar y ejecutar el proyecto

1. Clonar el repositorio desde GitHub:

```
git clone https://github.com/ttaxyy/SpeedFast.git
```

2. Correr el siguiente script para generar la base de datos:

```
CREATE DATABASE IF NOT EXISTS speedfast;
USE speedfast;

CREATE TABLE repartidor (
	id INT AUTO_INCREMENT PRIMARY KEY,
	nombre VARCHAR(100) NOT null,
	tieneMochila BOOL NOT NULL
);

CREATE TABLE pedido (
    id INT AUTO_INCREMENT PRIMARY KEY,
    tipo ENUM('COMIDA','ENCOMIENDA','EXPRESS'),
	estado ENUM('PENDIENTE','EN_REPARTO','ENTREGADO'),

    dir_region VARCHAR(60) NOT NULL,
    dir_comuna VARCHAR(60) NOT NULL,
    dir_calle VARCHAR(60) NOT NULL,
    dir_numero INT NOT NULL,

    peso INT NULL,
    embalaje BOOLEAN NULL
);

CREATE TABLE entrega (
	id INT AUTO_INCREMENT PRIMARY KEY,
	id_pedido INT NOT NULL,
	id_repartidor INT NOT NULL,
	fecha DATE NOT NULL,
	hora TIME NOT NULL,
	FOREIGN KEY (id_pedido) REFERENCES pedido(id),
	FOREIGN KEY (id_repartidor) REFERENCES repartidor(id)
);
```

3. Abrir el proyecto en IntelliJ IDEA o a través de la terminal.

4. Ejecutar el archivo `Main.java` desde el paquete `ui` (en IntelliJ IDEA).

5. Si se usa la terminal, navegar a `.../out/production/Speedfast` y luego ejecutar `java ui.Main`.

---

**Repositorio GitHub:** https://github.com/ttaxyy/SpeedFast.git
**Fecha de entrega:** \[1/3/2026]

---

© Duoc UC | Escuela de Informática y Telecomunicaciones | Entrega Semana 8
