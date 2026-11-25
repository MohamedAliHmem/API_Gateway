# 💼 Job Recruitment Platform — Microservices

A full-stack recruitment application built as a school project at **ESPRIT**, following a microservices architecture with Spring Boot and Spring Cloud.

## 🧩 What's Inside

The backend is split into independent microservices, each handling a specific part of the recruitment process:

| Service | Description |
|---------|-------------|
| **Job** | Manage job offers (create, update, list, delete) |
| **Candidat** | Handle candidate profiles |
| **Candidature** | Track job applications |
| **Meeting** | Schedule interviews between recruiters and candidates |
| **Notification** | Send notifications to users |
| **Eureka Server** | Service discovery — lets all microservices find each other |
| **Gateway** | Single entry point (port `8091`) — routes requests to the right service |

## 🏗️ Tech Stack

- **Backend:** Java · Spring Boot · Spring Cloud (Eureka, Gateway)
- **Frontend:** Angular *(or similar — located in `/FrontEnd`)*
- **Build:** Maven (multi-module)

## 🚀 Getting Started

1. **Start Eureka** — run the discovery service first so other services can register.
2. **Start the Gateway** — it listens on port `8091` and routes traffic automatically.
3. **Start the microservices** — launch `job`, `candidat`, `candidature`, `meeting`, and `notification` in any order.
4. **Run the frontend** — open the frontend project and serve it locally.

> Each microservice registers itself with Eureka, and the gateway discovers them dynamically — no hardcoded URLs needed.

## 📂 Project Structure

```
├── backend/
│   ├── discovery/          # Eureka Server
│   ├── gatewayi/           # API Gateway (port 8091)
│   └── microservices/
│       ├── candidat/
│       ├── candidature/
│       ├── eureka-server/
│       ├── job/
│       ├── meeting/
│       └── notification/
├── FrontEnd/               # Client application
├── documentation/          # Project docs
└── README.md
```

## 📝 Notes

- This project was developed as part of the **SI (Systèmes d'Information)** course at ESPRIT.
- A walkthrough video is included: `Scenario_MicroService_explicatif.mp4`.

---

*Made with ☕ and Spring Boot.*
