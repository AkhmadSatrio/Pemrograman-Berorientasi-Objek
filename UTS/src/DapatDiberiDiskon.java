/**
 * Interface DapatDiberiDiskon
 * Menetapkan kontrak bagi entitas yang memiliki fitur potongan harga / diskon.
 * 
 * Mata Kuliah: Praktikum Pemrograman Berorientasi Obyek
 * Mahasiswa  : Akhmad Satrio Cahyo Pratama (NRP: 3125522013)
 * Kasus      : Tipe D - Sistem Pemesanan Makanan
 */
public interface DapatDiberiDiskon {
    /**
     * Menghitung besaran nominal potongan diskon berdasarkan persentase.
     * @param persentaseDiskon persentase diskon (0 - 100)
     * @return nominal potongan harga
     */
    double hitungDiskon(double persentaseDiskon);

    /**
     * Menghitung harga akhir setelah dikurangi diskon.
     * @param persentaseDiskon persentase diskon (0 - 100)
     * @return harga setelah diskon
     */
    double getHargaSetelahDiskon(double persentaseDiskon);
}
