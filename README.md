🛢️ CNG Connect — Location & Navigation Platform

A full-stack web application that helps users locate nearby CNG (Compressed Natural Gas) 
stations using real-time GPS-based routing and an interactive map interface.
Built as a prototype to demonstrate how a national CNG monitoring system could work 
at scale across India.

 🌐 Live Demo
🔗 [https://cng-connect-vi3n.onrender.com](https://cng-connect-vi3n.onrender.com)

 🚀 Features

- 📍 Detect user's current location via GPS
- 🗺️ Display nearby CNG stations on an interactive map (Leaflet.js)
- 🔄 REST API backend built with Spring Boot
- 🧭 Turn-by-turn route navigation to selected station
- 💾 Station data stored and managed via MongoDB
- 🌐 Deployed live on Render
- 🇮🇳 Bilingual support — English & Tamil
- 📊 Real-time queue status reporting by drivers
- ⏱️ TTR (Total Time to Refill) algorithm combining live traffic + queue wait
- 🚛 Fleet & enterprise dashboard (in development)

 🛠️ Tech Stack


Frontend - HTML, CSS, JavaScript, Leaflet.js 
Backend - Java, Spring Boot, REST APIs 
Database - MongoDB 
Deployment - Render 
Tools -  Git, Postman, Maven 

⚙️ Prerequisites

- Java 17 (JDK)
- Maven
- MongoDB (local or Atlas URI)

🔧 Troubleshooting

 Error  & Fix 
 `mvn not recognized` - Install Maven and add to system PATH 
 `Address already in use` - Another app is using port 8080 — stop it or change the port 
 MongoDB connection error - Check your DB URI in `application.properties` 

🚀 Future Scope

- Government integration for real-time station data across India
- Mobile app (Android/iOS)
- Predictive queue analytics using ML
- Full fleet dispatcher dashboard

 👤 Author

**Srinivas S** — ECE Student, RMD Engineering College  
[LinkedIn](https://www.linkedin.com/in/srinivas-s-759879202/) | [GitHub](https://github.com/srinivas-cod) | 📧 srinivassriraman0923@gmail.com

 📄 License
This project is for educational and demonstration purposes only.  
© 2026 Srinivas S. All rights reserved.
