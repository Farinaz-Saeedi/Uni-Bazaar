# 🛍️ Uni-Bazaar

A small Android marketplace application developed as a Database Course Project.

## 🎬 Demo

![Application Demo](demo/video/demo.gif)

---

## 📖 Overview

Uni Bazaar is a small Android marketplace application developed as a Database course project. The application simulates a marketplace environment with users, support staff, products, stores, discount codes, and requests. All application data is stored locally using SQLite.

---

## 🗄️ Database Design

The database was designed using an Enhanced Entity-Relationship (EER) model before implementation and then implemented using SQLite.

The EER diagram is included in the project documentation.

---

## ✨ Features

### 👤 User Features

Each user contains:

* 🎟️ Discount Codes
* 📝 Store Requests
* 👀 Recently Viewed Products
* 🔥 Best-Selling Products
* 🎯 Recommended Products
* 🏪 Purchased Stores

Fraudulent stores are marked with a dedicated scam badge...

Clicking on each section displays a detailed list of related records.

### 🛠️ Support Staff Features

Each support member includes:

* 📊 Operational Performance
* ✅ Verified Stores Percentage
* 🏅 Awarded Badges
* 🎁 Generated Discount Codes

Performance statistics are visualized using custom circular progress bars.

---

## 🧰 Technologies Used

* ☕ Java
* 📱 Android SDK
* 🗃️ SQLite
* 🛠️ Android Studio
* 🎨 Custom Views
* 📐 Material Design Components

---

## 🏗️ Project Structure

* Activities
* Adapters
* Database Layer
* Models
* Helper Classes
* Custom Widgets

---

## 📸 Screenshots & Demo

Screenshots and a full demonstration video of the application are available in the `demo/` directory, including all main features and user flows of the app.

---

## 🎓 Educational Purpose

This project was developed for educational purposes as part of a university Database course and focuses on database design and Android application development.

*Feel free to explore the project and enjoy the experience 😊*
