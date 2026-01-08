
SISTEMA ACADEMICO - TRABAJO INTEGRADOR 2024
------------------------------------------

Este proyecto es un sistema de gestión académica para una facultad, desarrollado en Java con programación orientada a objetos (POO) y Swing para la interfaz gráfica.

FUNCIONALIDADES IMPLEMENTADAS
-----------------------------
- Alta de alumnos
- Alta de carreras con planes de estudio (obligatorias y optativas)
- Inscripción de alumnos a carreras
- Inscripción a materias (sólo si cumple condiciones)
- Verificación de finalización de carrera
- Precarga de 3 alumnos, 5 carreras (una de cada tipo de plan), materias con correlativas

PATRONES DE DISEÑO (GoF) APLICADOS
----------------------------------
✔ Singleton:
  - Clase: SistemaInscripcion
  - Uso: Mantiene una única instancia global del sistema para almacenar alumnos y carreras.

✔ Strategy:
  - Clases: TipoA, TipoB, TipoC, TipoD, TipoE implementan TipoPlan
  - Uso: Cada clase representa una estrategia diferente de validación de cursada según plan de estudios.

(✘) Factory, Adapter, Decorator, Composite:
  - No aplicaron en este diseño sin forzarlos innecesariamente.

DECISIONES DE DISEÑO
--------------------
- Las materias se modelaron con cuatrimestre, promocionabilidad y correlativas.
- Cada carrera define su plan de estudio con una lista de materias obligatorias y optativas.
- La validación de inscripción a materias depende del tipo de plan (A a E).
- Se usó DefaultListModel y JComboBox para interfaces visuales interactivas.
- Todas las ventanas están enlazadas desde MenuPrincipal.

EJECUCIÓN
---------
1. Abrir el proyecto en NetBeans
2. Ejecutar clase: MenuPrincipal
3. Navegar el sistema desde el menú
4. Las listas de alumnos y carreras ya incluyen datos cargados

AUTORES / ENTREGA
-----------------
Este sistema fue generado, corregido y validado para cumplir con el Trabajo Integrador 2024.
