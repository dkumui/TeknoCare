# **TeknoCare – Sistem Manajemen Layanan Servis Komputer**

**TeknoCare** adalah aplikasi berbasis Java yang berfungsi sebagai sistem informasi sederhana untuk menangani layanan servis perangkat komputer, khususnya **Laptop** dan **PC**. Aplikasi ini memanfaatkan prinsip **Object-Oriented Programming (OOP)** dan arsitektur **berbasis komponen** menggunakan **Jakarta EE** (Java Enterprise Edition).

Dirancang dengan pendekatan modular dan bersih, TeknoCare mencakup alur mulai dari pendaftaran pelanggan, pemilihan layanan, hingga pencatatan teknisi dan pencetakan struk service.

## Fitur Utama

* Input data pelanggan dan kontak.
* Pilihan jenis layanan servis (laptop atau PC) lengkap dengan jenis kerusakan dan estimasi biaya.
* Pemilihan teknisi secara otomatis dari daftar teknisi tersedia.
* Penyimpanan dan pencetakan struk layanan termasuk tanggal servis dan tanggal selesai.
* Arsitektur berbasis controller-model (mirip MVC) dan pemisahan logika ke dalam DAO.

## Teknologi dan Arsitektur

* **Java SE + Jakarta EE 11**
* **Servlets & REST Resources** (package `controller` dan `resources`)
* **Data Access Object (DAO)** pattern untuk manipulasi data
* **JPA (Java Persistence API)** untuk persistence (lihat `persistence.xml`)
* **HTML (webapp/index.html)** untuk antarmuka pengguna awal (placeholder atau pengujian)

## Struktur Kode (Folder dan Package)

* `controller`: Logika untuk menangani request dari client (REST API atau Form).
* `dao`: Kelas untuk interaksi database (contoh: `CustomerDAO.java`).
* `model`: Entitas Java seperti `Customer`, `LayananService`, `Teknisi`, dll.
* `util`: Kelas utilitas, seperti `DBConnection.java` untuk koneksi database.
* `resources`: Konfigurasi REST (misal `JakartaRestConfiguration.java`).
* `webapp`: Antarmuka HTML awal, bisa dikembangkan lebih lanjut dengan JSP/JSF.

## Contoh Struktur Kelas

* `Customer`: Menyimpan info pelanggan (nama dan kontak).
* `Teknisi`: Menangani layanan dan memiliki fungsi pemilihan teknisi otomatis.
* `LayananService` (abstract): Superclass untuk `ServiceLaptop` dan `ServicePC`.
* `Struk`: Mewakili nota layanan, berisi customer, layanan, teknisi, dan tanggal.

### Class Diagram

![UML Class Diagram PBO](https://github.com/user-attachments/assets/3f424494-350a-49b0-8852-adef9091b18d)

## Screenshot Tampilan Aplikasi

![image](https://github.com/user-attachments/assets/e4280fcd-26e6-469f-aa7b-fdba7644abef)

## Kontributor Proyek

* @dkumui
* @xyzall1
* @ardelialaksita
