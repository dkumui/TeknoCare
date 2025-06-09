# TeknoCare – Sistem Manajemen Layanan Servis Komputer

**TeknoCare** adalah aplikasi web berbasis Java yang dirancang sebagai sistem informasi untuk mengelola alur layanan servis perangkat komputer (Laptop dan PC). Aplikasi ini dibangun dengan prinsip-prinsip **Object-Oriented Programming (OOP)** dan menerapkan arsitektur **Model-View-Controller (MVC)** menggunakan teknologi **Jakarta EE (Servlet & JSP)** serta **Data Access Object (DAO)**.

Sistem ini mencakup seluruh proses bisnis, mulai dari pendaftaran pelanggan baru, pemilihan layanan dari katalog, penugasan teknisi, hingga manajemen status servis dan pembuatan laporan.

## Fitur Utama

- **Manajemen Pelanggan**  
  Pendaftaran pelanggan baru dan pencarian pelanggan yang sudah ada.

- **Katalog Layanan Dinamis**  
  Admin dapat mengelola (CRUD) daftar layanan yang ditawarkan, termasuk biaya dan deskripsi.

- **Manajemen Teknisi**  
  Admin dapat mengelola (CRUD) data teknisi, lengkap dengan spesialisasi dan status ketersediaan.

- **Proses Servis Terstruktur**  
  Alur yang jelas untuk membuat order servis baru, mulai dari pemilihan pelanggan, jenis perangkat, hingga detail kerusakan.

- **Manajemen Struk Servis**  
  Admin dapat melacak dan memperbarui status setiap order servis (misalnya: "Baru Masuk", "Dikerjakan", "Selesai").

- **Dashboard Admin Informatif**  
  Menyajikan ringkasan data penting seperti total pelanggan, jumlah servis berdasarkan status, dan total pendapatan.

- **Pemisahan Hak Akses**  
  Terdapat halaman khusus untuk admin yang dilindungi untuk melakukan manajemen data master.

---

## Arsitektur & Teknologi

| Kategori     | Teknologi / Pola Desain              |
|--------------|--------------------------------------|
| **Bahasa**   | Java 17                              |
| **Platform** | Jakarta EE 11                        |
| **Web**      | Servlet, JSP, JSTL                   |
| **Database** | MySQL                                |
| **Build Tool** | Apache Maven                       |
| **Arsitektur** | MVC-like (Controller: Servlet, View: JSP, Model: POJO) |
| **Pola Akses Data** | Data Access Object (DAO)     |
| **Frontend** | HTML, CSS, JavaScript                |
| **Server**   | Apache Tomcat                        |


## Diagram Sistem

### 1. Diagram Kelas
Menunjukkan kelas-kelas Java utama dalam proyek dan bagaimana mereka berinteraksi.  
![Editor _ Mermaid Chart-2025-06-09-190303](https://github.com/user-attachments/assets/e7a532cd-9bd0-4a5a-9b74-975d9b045074)

## Screenshot Website
![image](https://github.com/user-attachments/assets/d3f0de36-a035-4ff3-b96c-d1bac974b46d)

## Cara Menjalankan Proyek

### Prasyarat

- JDK 17 atau versi lebih baru
- Apache Maven 3.6+
- MySQL Server atau MariaDB
- Apache Tomcat 10.1+ (kompatibel dengan Jakarta EE 11)

### Langkah-langkah Instalasi

1. **Clone Repositori**
   ```bash
   git clone https://github.com/dkumui/teknocare.git
   cd teknocare

2. **Siapkan Database**

   * Buka phpMyAdmin / DBeaver / MySQL Workbench
   * Buat database baru bernama `teknocare`
   * Import file `teknocare.sql` dari root project

3. **Konfigurasi Koneksi Database**
   Ubah file `src/main/java/com/mycompany/teknocaree/util/DBConnection.java`:

   ```java
   private static final String URL = "jdbc:mysql://localhost:3306/teknocare";
   private static final String USER = "root";
   private static final String PASS = ""; // Sesuaikan password Anda
   ```

4. **Build Proyek**

   ```bash
   mvn clean install
   ```

5. **Deploy Aplikasi**

   * Salin file `.war` dari `target/` ke folder `webapps/` di dalam instalasi Tomcat.
   * Jalankan server Tomcat.

6. **Akses Aplikasi**

   ```
   http://localhost:8080/TeknoCaree/
   ```

## Struktur Proyek

```
TeknoCare/
├── src/main/
│   ├── java/com/mycompany/teknocaree/
│   │   ├── controller/     # Servlet (logika bisnis)
│   │   │   └── admin/      # Servlet khusus untuk admin
│   │   ├── dao/            # Data Access Objects
│   │   ├── model/          # POJO dan DTO
│   │   │   └── dto/
│   │   └── util/           # Kelas utilitas (DB connection)
│   ├── resources/          # Konfigurasi (persistence.xml)
│   └── webapp/             # Konten Web
│       ├── admin/          # JSP admin
│       ├── css/
│       ├── js/
│       ├── WEB-INF/
│       └── *.jsp
├── pom.xml                 # File Maven
└── teknocare.sql           # Database skema & data awal
```

## Kontributor

* [@dkumui](https://github.com/dkumui)
* [@xyzall1](https://github.com/xyzall1)
* [@ardelialaksita](https://github.com/ardelialaksita)
