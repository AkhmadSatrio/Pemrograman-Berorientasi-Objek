# UTS Praktikum Pemrograman Berorientasi Obyek
**Studi Kasus: Tipe D - Sistem Pemesanan Makanan**

- **Nama**: Akhmad Satrio Cahyo Pratama
- **NRP**: 3125522013
- **Kelas**: 3D3PSDKU-SM-IT A
- **Dosen**: Nirwana Haidar Hari, S.Pd., M.Kom.

---

### Struktur Folder
```
UTS/
├── LAPORAN_UTS.md          # Laporan praktis, rapi, dan panduan demo 3 menit
├── class-diagram.png       # Class Diagram (Gambar Resolusi Tinggi)
├── class-diagram.pdf       # Class Diagram (Format PDF)
├── generate_diagram.py     # Script generator diagram
└── src/
    ├── DapatDiberiDiskon.java   # Interface
    ├── Menu.java               # Abstract Class
    ├── Makanan.java            # Subclass Menu
    ├── Minuman.java            # Subclass Menu
    ├── PaketHemat.java         # Subclass Menu (Bundling)
    ├── Pelanggan.java          # Entity Pelanggan & Membership
    ├── StatusPesanan.java      # Enum Status Pemesanan
    ├── ItemPesanan.java        # Entity Relasi Komposisi & Asosiasi
    ├── Pesanan.java            # Aggregate Transaksi & Fitur Kreatif
    └── Main.java               # Driver & Pengujian Lengkap
```

### Cara Kompilasi & Menjalankan:
```bash
cd src
javac *.java
java Main
```
