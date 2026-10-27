# MODUL 9: IMPLEMENTASI UML CLASS DIAGRAM KE PROGRAM JAVA
## Proyek: Sistem Manajemen Bank Sampah

- **Nama Mahasiswa** : Akhmad Satrio Cahyo Pratama
- **NRP**            : 3125522013
- **Program Studi**  : D4 Teknik Informatika - PENS PSDKU Sumenep
- **Mata Kuliah**    : Praktikum Pemrograman Berorientasi Obyek (PBO)
- **Pertemuan / Modul**: 9 (Sembilan)
- **Dosen Pengampu** : Nirwana Haidar Hari, S.Pd., M.Kom.

---

## 1. Struktur Folder Luaran Praktikum
Sesuai format ketentuan Modul 9:
```text
P9_AkhmadSatrio_BankSampah/
├── UML/
│   ├── class_diagram_p9.png    # Gambar UML Class Diagram Resolusi Tinggi (300 DPI)
│   └── class_diagram_p9.pdf    # Dokumen Vektor UML Class Diagram
├── SourceCode/
│   ├── DapatDilacak.java       # Interface kontrak pelacakan lokasi & operasional
│   ├── Entitas.java            # Abstract Class fondasi umum sistem
│   ├── Nasabah.java            # Subclass konkret Entitas (Relasi Komposisi ke Rekening)
│   ├── Rekening.java           # Komponen keuangan penyusun Nasabah
│   ├── Sampah.java             # Entity komoditas sampah & stok gudang
│   ├── Setoran.java            # Relasi Asosiasi Transaksi (Nasabah, Sampah, BankSampah)
│   ├── BankSampah.java         # Subclass Entitas (Agregasi Sampah & Setoran, Implements DapatDilacak)
│   ├── Koperasi.java           # Subclass Entitas mitra pendanaan (Implements DapatDilacak)
│   └── Main.java               # Driver class pengujian lengkap & verifikasi konsistensi
├── Screenshot/
│   ├── hasil_eksekusi_program.png  # Screenshot terminal eksekusi program
│   └── terminal_output.txt         # Log lengkap hasil pengujian program
├── LAPORAN_P9.pdf              # Laporan resmi praktikum format PDF
├── README.md                   # Dokumentasi teknis & jawaban pertanyaan evaluasi
└── create_assets.py            # Generator visual diagram & screenshot
```

---

## 2. Hubungan UML Class Diagram dengan Implementasi Source Code Java

UML Class Diagram adalah rancangan arsitektural blueprint sistem berorientasi objek, sedangkan source code Java adalah implementasi konkretnya. Modul 9 menuntut keselarasan 100% antara rancangan dan implementasi.

### A. Pemetaan Visibility dan Enkapsulasi
| Notasi UML | Arti Visibility | Keyword Java | Penerapan dalam Proyek |
|---|---|---|---|
| `-` | `private` | `private` | Semua atribut data (`nama`, `alamat`, `saldo`, `stokKg`, `beratKg`, dll.) dienkapsulasi rapat agar tidak dapat dimodifikasi sembarangan dari luar. |
| `+` | `public` | `public` | Method interface, konstruktor, method bisnis (`setor()`, `tarik()`, `kurangiStok()`, `selesaikanSetoran()`), dan getter resmi. |
| `#` | `protected` | `protected` | Digunakan untuk atribut atau helper yang diwariskan khusus ke turunan langsung. |

### B. Pemetaan Relasi Antar-Class ke Kode Java
1. **Generalization (Inheritance / Pewarisan - Notasi Garis Solid dengan Panah Segitiga Kosong `—▷`)**:
   - `Nasabah`, `BankSampah`, dan `Koperasi` mewarisi `Entitas`.
   - **Sintaks Java**: `public class BankSampah extends Entitas`
   - **Makna**: Subclass mewarisi atribut `nama`, `alamat`, method konkret `tampilkanIdentitas()`, dan wajib mengimplementasikan abstract method `getPeran()` dan `hitungKontribusi()`.

2. **Realization (Implementasi Interface - Notasi Garis Putus-putus dengan Panah Segitiga Kosong `- - ▷`)**:
   - `BankSampah` dan `Koperasi` mengimplementasikan interface `DapatDilacak`.
   - **Sintaks Java**: `public class BankSampah extends Entitas implements DapatDilacak`
   - **Makna**: Mewujudkan kontrak standar pelacakan fisik (`getLokasi()`, `getStatusOperasional()`, `perbaruiLokasi()`).

3. **Composition (Komposisi - Notasi Garis dengan Belah Ketupat Hitam Penuh `◆—`)**:
   - `Nasabah` memiliki hubungan komposisi kuat dengan `Rekening` (Multiplicity `1 : 1`).
   - **Sintaks Java**:
     ```java
     public class Nasabah extends Entitas {
         private Rekening rekening; // Objek Rekening dibuat di dalam konstruktor Nasabah
         public Nasabah(String id, String nama, String alamat, double saldoAwal) {
             super(nama, alamat);
             this.rekening = new Rekening("REK-" + id, saldoAwal);
         }
     }
     ```
   - **Karakteristik**: Siklus hidup `Rekening` bergantung sepenuhnya pada `Nasabah`. Jika entitas nasabah dibuat, rekening otomatis terbentuk, dan tidak berdiri sendiri di luar nasabah.

4. **Aggregation (Agregasi - Notasi Garis dengan Belah Ketupat Kosong `◇—`)**:
   - `BankSampah` mengagregasikan `Sampah` (`1 : 0..*`) dan `Setoran` (`1 : 0..*`).
   - **Sintaks Java**:
     ```java
     public class BankSampah extends Entitas {
         private List<Sampah> daftarSampah;
         private List<Setoran> riwayatSetoran;
         // Sampah diinstansiasi secara independen dan ditambahkan ke BankSampah
         public void tambahSampah(Sampah sampah) { daftarSampah.add(sampah); }
     }
     ```
   - **Karakteristik**: Objek `Sampah` dapat tetap eksis sebagai entitas komoditas meskipun belum didaftarkan ke fasilitas bank sampah tertentu.

5. **Association (Asosiasi Transaksional - Notasi Garis dengan Mata Panah `—>`)**:
   - `Setoran` mengasosiasikan `Nasabah` (`0..* : 1`) dan `Sampah` (`0..* : 1`).
   - **Sintaks Java**:
     ```java
     public class Setoran {
         private Nasabah nasabah;
         private Sampah sampah;
         private double beratKg;
         // Setoran menghubungkan nasabah penyetor dengan jenis sampah yang disetor
     }
     ```

---

## 3. Penerapan Object Responsibility dan Validasi State (Ketentuan Modul 9 Halaman 6–7)

Modul 9 menegaskan:
> *"Mahasiswa tidak diperbolehkan hanya membuat class berisi attribute + getter/setter. Setiap class harus mempunyai responsibility yang jelas dan behavior yang relevan dengan kasus proyek. Contoh kurang tepat: `setStok(-100);`. Lebih tepat: `kurangiStok(2);` karena object bertanggung jawab menjaga state-nya."*

Penerapan prinsip ini pada proyek:
1. **Class `Sampah`**:
   - **Bukan** `setStokKg(double stok)` yang rawan diisi angka minus!
   - Melainkan menggunakan method ber-responsibility:
     - `tambahStok(double kg)`: Memvalidasi `kg > 0`, menambah timbunan sampah di gudang.
     - `kurangiStok(double kg)`: Memvalidasi `kg > 0` dan memeriksa apakah `this.stokKg >= kg`. Mencegah stok fisik gudang menjadi negatif ketika disalurkan ke industri daur ulang.
2. **Class `Rekening`**:
   - **Bukan** `setSaldo(saldo - 50000)` dari luar secara ceroboh!
   - Melainkan:
     - `setor(double nominal)`: Memastikan nominal setoran bernilai positif.
     - `tarik(double nominal)`: Melakukan validasi proteksi saldo mencukupi (`saldo >= nominal`). Jika saldo kurang, penarikan ditolak dan mengembalikan `false`.
3. **Class `Setoran`**:
   - Memiliki state `statusSelesai` untuk menjamin prinsip **idempotensi**. Transaksi yang sudah diproses tidak dapat diproses ulang (`double-spending prevention`).
   - Method `selesaikanSetoran()` secara otomatis mengoordinasikan penambahan saldo pada objek `Nasabah` dan penambahan fisik stok pada objek `Sampah`.

---

## 4. Cara Kompilasi dan Menjalankan Program

Jalankan perintah berikut pada terminal di folder `SourceCode/`:
```bash
cd SourceCode
javac *.java
java Main
```

---

## 5. Jawaban Pertanyaan Evaluasi Praktikum Modul 9 (Halaman 7–8)

### Pertanyaan 1:
**Apa perbedaan class pada UML dan object pada program?**
> **Jawaban:**
> - **Class pada UML** adalah representasi abstrak (cetak biru / *blueprint*) yang mendefinisikan struktur konseptual sistem berupa spesifikasi nama kelas, visibilitas atribut, tipe data, daftar method/operasi, serta tipe hubungan logis (*generalization, realization, association, aggregation, composition*) tanpa mengalokasikan memori nyata di komputer.
> - **Object pada program** adalah entitas nyata hasil instansiasi (*instance*) dari suatu kelas di memori runtime Java (`heap memory`). Objek memiliki *state* (nilai atribut spesifik yang tersimpan pada saat itu, misalnya `Ahmad Fauzi` dengan saldo `Rp60.000`), *behavior* (eksekusi logika method aktual), dan *identity* unik yang berinteraksi secara aktif saat program berjalan.

---

### Pertanyaan 2:
**Bagaimana association pada UML diterapkan dalam Java?**
> **Jawaban:**
> Pada Java, *association* diterapkan dengan cara menjadikan referensi dari satu objek sebagai atribut (*field / reference variable*) di dalam objek lain, baik secara *single-reference* maupun *collection*.
> - Jika relasinya ber-multiplicity 1 (seperti `Setoran` berasosiasi dengan satu `Nasabah` dan satu `Sampah`), diterapkan sebagai field instans:
>   ```java
>   public class Setoran {
>       private Nasabah nasabah; // Asosiasi ke satu Nasabah
>       private Sampah sampah;   // Asosiasi ke satu Sampah
>   }
>   ```
> - Jika relasinya ber-multiplicity `0..*` atau `1..*` (seperti agregasi `BankSampah` dengan banyak `Sampah`), diterapkan menggunakan koleksi seperti `List<Sampah>` atau array:
>   ```java
>   public class BankSampah {
>       private List<Sampah> daftarSampah = new ArrayList<>();
>   }
>   ```
> Melalui referensi atribut ini, method objek pengasosiasi dapat memanggil behavior dari objek yang diasosiasikan (misalnya `setoran.selesaikanSetoran()` memanggil `nasabah.setorUang(...)`).

---

### Pertanyaan 3:
**Mengapa attribute sebaiknya tidak seluruhnya public?**
> **Jawaban:**
> Attribute tidak boleh dibuat `public` demi menjaga pilar fundamental **Enkapsulasi (*Information Hiding*)** dan **Integritas State**:
> 1. **Mencegah Kerusakan State (*State Inconsistency*)**: Jika atribut seperti `saldo` atau `stokKg` dibuat `public`, kode eksternal dapat langsung menulis `rekening.saldo = -9999999;` atau `sampah.stokKg = -50;` tanpa melalui logika validasi apapun, merusak aturan bisnis dunia nyata.
> 2. **Menjaga Tanggung Jawab Objek (*Object Responsibility*)**: Objek harus memegang kendali penuh atas perubahan internal dirinya sendiri. Dengan atribut `private`, perubahan state hanya dimungkinkan melalui method bisnis terkontrol (seperti `kurangiStok()` dan `tarik()`).
> 3. **Fleksibilitas Refactoring & *Low Coupling***: Perubahan implementasi internal atribut (misal mengganti tipe data atau menambahkan audit logging) tidak akan merusak kelas lain yang menggunakan objek tersebut, karena antarmuka (*interface*) method publiknya tetap stabil.

---

### Pertanyaan 4:
**Apakah source code proyek sudah konsisten dengan UML?**
> **Jawaban:**
> **Ya, source code proyek sudah 100% konsisten dengan UML Class Diagram.**
> - **Visibility**: Seluruh simbol visibility pada diagram (`-` untuk private, `+` untuk public, `#` untuk protected) diimplementasikan secara identik pada kode Java.
> - **Class & Stereotype**: Abstract class `<<abstract>> Entitas` dan interface `<<interface>> DapatDilacak` pada UML diimplementasikan dengan keyword `abstract class` dan `interface` di Java.
> - **Semua Method & Atribut**: Setiap atribut dan method (termasuk parameter dan tipe return-nya) yang tertera pada kotak diagram diwujudkan secara presisi di source code Java.
> - **Semantik Relasi**:
>   - *Generalization* diwujudkan dengan `extends Entitas`.
>   - *Realization* diwujudkan dengan `implements DapatDilacak`.
>   - *Composition* diwujudkan dengan inisialisasi `Rekening` privat di dalam konstruktor `Nasabah`.
>   - *Aggregation* diwujudkan dengan `List<Sampah>` dan `List<Setoran>` di dalam `BankSampah`.
>   - *Association* diwujudkan dengan referensi `Nasabah` dan `Sampah` di dalam kelas `Setoran`.

---

### Pertanyaan 5:
**Class mana yang memiliki responsibility paling besar dalam proyek Anda?**
> **Jawaban:**
> Class yang memiliki responsibility paling besar dalam proyek ini adalah **`BankSampah`**, didukung oleh class transaksional **`Setoran`**:
> - **`BankSampah`** bertindak sebagai *Controller / Aggregate Root* sistem:
>   1. Mengelola katalog seluruh komoditas sampah fisik dan nilai per kilogramnya (`daftarSampah`).
>   2. Menampung dan memantau riwayat seluruh mutasi transaksi penyetoran masyarakat (`riwayatSetoran`).
>   3. Mengontrol alur rantai pasok ekonomi sirkular melalui method `kirimKeIndustriDaurUlang()`, yang menguji ketersediaan fisik stok gudang sebelum diteruskan ke pabrik daur ulang.
>   4. Mewujudkan multi-kontrak: sebagai turunan `Entitas` untuk kalkulasi kontribusi omzet sirkular, dan sebagai implementasi `DapatDilacak` untuk pelacakan koordinat fasilitas fisik.
> - Sementara itu, class **`Setoran`** memegang responsibility transaksional utama yang menjembatani interaksi antara `Nasabah` (keuangan) dan `Sampah` (fisik/inventaris).
