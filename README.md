# 🎓 Sistema de Gestión Académica

Sistema integral de gestión académica universitaria desarrollado en Java con interfaz gráfica Swing, implementando patrones de diseño para una arquitectura robusta y extensible.

## ✨ Características

- **Gestión de Alumnos**: Alta de estudiantes con validación de legajos únicos
- **Gestión de Carreras**: Creación y edición de carreras con planes de estudio personalizados
- **Sistema de Materias**: Gestión de correlativas y requisitos por materia
- **Inscripciones Inteligentes**: Validación automática de correlativas según tipo de plan
- **5 Tipos de Planes**: Diferentes regímenes de cursado (A, B, C, D, E)
- **Gestión de Cursadas**: Registro de parciales, promociones automáticas y finales
- **Verificación de Graduación**: Control automático de materias obligatorias y optativas

## 🏗️ Arquitectura

El sistema está diseñado aplicando **patrones de diseño** y principios **SOLID**:

### Patrones Implementados

- **State Pattern**: Gestión de la lógica diferenciada por tipo de plan de estudio
- **Factory Pattern**: Creación de instancias de estados según el tipo de plan
- **Registry Pattern**: Control de unicidad de legajos de estudiantes

### Estructura del Proyecto

```
src/sistema/academico/
├── Alumno.java                 # Entidad alumno
├── AlumnoMateria.java         # Relación alumno-materia con estado
├── AlumnoRegistry.java        # Registry para legajos únicos
├── Carrera.java               # Entidad carrera
├── Materia.java               # Entidad materia con correlativas
├── PlanEstudio.java           # Plan de estudios con state
├── TipoPlan.java              # Enum con factory method
├── PlanState.java             # Interfaz del patrón State
├── PlanA.java                 # Implementación Plan A
├── PlanB.java                 # Implementación Plan B
├── PlanC.java                 # Implementación Plan C
├── PlanD.java                 # Implementación Plan D
├── PlanE.java                 # Implementación Plan E
├── ControladorInscripciones.java  # Lógica de negocio
├── DatosCompartidos.java      # Gestión de datos compartidos
├── VentanaAlumno.java         # GUI - Alta de alumnos
├── VentanaCarrera.java        # GUI - Alta de carreras
├── VentanaEditarCarrera.java  # GUI - Edición de carreras
├── VentanaInscribirCarrera.java  # GUI - Inscripción a carreras
├── VentanaInscripcion.java    # GUI - Gestión de inscripciones
├── MainApp.java               # Aplicación principal
└── MainJavaTest.java          # Datos de prueba
```

## 🎯 Tipos de Planes de Estudio

| Tipo | Requisitos de Inscripción |
|------|--------------------------|
| **Plan A** | Cursadas aprobadas de todas las correlativas |
| **Plan B** | Finales aprobados de todas las correlativas |
| **Plan C** | Plan A + finales aprobados de los últimos 5 cuatrimestres |
| **Plan D** | Plan A + finales aprobados de los últimos 3 cuatrimestres |
| **Plan E** | Plan B + finales aprobados de los últimos 3 cuatrimestres |

## 🛠️ Tecnologías

- **Java** (JDK 8+)
- **Swing** para la interfaz gráfica
- **Streams API** para procesamiento funcional de colecciones
- **Patrones de Diseño**: State, Factory, Registry

## 🚀 Instalación y Ejecución

### Requisitos Previos

- JDK 8 o superior instalado
- Git (opcional)

### Compilar el Proyecto

```bash
# Navegar al directorio del proyecto
cd "Sistema Academico"

# Compilar todas las clases
javac -d build/classes src/sistema/academico/*.java
```

### Ejecutar la Aplicación

```bash
# Ejecutar desde el directorio del proyecto
java -cp build/classes MainApp
```

## 💡 Uso

1. **Alta de Carrera**:
   - Crear una carrera especificando nombre, tipo de plan y cantidad de optativas
   - Agregar materias con sus correlativas

2. **Alta de Alumno**:
   - Registrar estudiantes con nombre y legajo único

3. **Inscribir a Carrera**:
   - Asignar un alumno a una carrera específica

4. **Gestión de Inscripciones**:
   - Inscribir a materias (validación automática de correlativas)
   - Aprobar cursadas
   - Registrar promociones con notas
   - Aprobar finales
   - Verificar graduación

## 📊 Validaciones Implementadas

- ✅ Legajos únicos por alumno
- ✅ Correlativas obligatorias según tipo de plan
- ✅ Nombres únicos de materias por carrera
- ✅ Cuatrimestres válidos (1-10)
- ✅ Requisitos de cuatrimestres previos aprobados
- ✅ Promoción automática con promedio ≥ 7 (dos parciales)
- ✅ Verificación de cursada aprobada antes de rendir final

## 🎨 Principios de Diseño

El proyecto sigue los **principios SOLID**:

- **S**ingle Responsibility: Cada clase tiene una única responsabilidad
- **O**pen/Closed: Extensible para nuevos tipos de plan sin modificar código existente
- **L**iskov Substitution: Los estados son intercambiables vía la interfaz
- **I**nterface Segregation: Interfaces enfocadas y mínimas
- **D**ependency Inversion: Dependencia de abstracciones, no de implementaciones concretas

## 📝 Licencia

Este proyecto fue desarrollado con fines educativos.

## 👤 Autor

**Matías Lautaro**

---

⭐ Si te resultó útil este proyecto, ¡dale una estrella en GitHub!
