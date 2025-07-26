# CronoAttendance

Proyecto web para gestión de asistencia de empleados mediante reconocimiento facial.
El fin de este proyecto seria poder hacerlo portable y local, pero lo hice con una estructura escalable en caso de que asi lo requiera.

## Módulos principales:
- Backend (Java - Spring Boot)
- Frontend (React - Next.js)
- Reconocimiento facial (OpenCV + Python)
- Generación de reportes (JasperReports)

## Estructura inicial
CronoAttendance
├── backend
│   ├── pom.xml
│   └── src
│       └── main
│           ├── java
│           │   └── com
│           │       └── cronoattendance
│           │           ├── controllers
│           │           ├── entities
│           │           ├── reports
│           │           ├── repositories
│           │           ├── services
│           │           └── utils
│           ├── resources
│           │   ├── application.properties
│           │   └── reports
│           │       ├── generated
│           │       └── templates
│           └── test
│               └── java
│                   └── com
│                       └── cronoattendance
├── database
│   └── asistencia.db
├── docs
│   ├── architecture.md
│   └── requirements.md
├── facial-recognition
│   ├── captures
│   ├── models
│   └── scripts
├── frontend
│   ├── api
│   ├── components
│   ├── package.json
│   ├── pages
│   ├── styles
│   └── utils
├── README.md
└── scripts