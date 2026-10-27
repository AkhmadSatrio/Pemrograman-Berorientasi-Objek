import java.util.Locale;

/**
 * Abstract Class Entitas
 * Merepresentasikan fondasi umum entitas dalam Sistem Manajemen Bank Sampah.
 * Tidak dapat diinstansiasi secara langsung (menerapkan konsep abstraksi).
 */
public abstract class Entitas {
    private String nama;
    private String alamat;

    public Entitas(String nama, String alamat) {
        setNama(nama);
        setAlamat(alamat);
    }

    public String getNama() {
        return nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setNama(String nama) {
        if (nama != null && !nama.isBlank()) {
            this.nama = nama.trim();
        } else {
            System.out.println("[Validasi Gagal] Nama entitas tidak boleh kosong.");
        }
    }

    public void setAlamat(String alamat) {
        if (alamat != null && !alamat.isBlank()) {
            this.alamat = alamat.trim();
        } else {
            System.out.println("[Validasi Gagal] Alamat entitas tidak boleh kosong.");
        }
    }

    public void tampilkanIdentitas() {
        System.out.println("Nama        : " + nama);
        System.out.println("Alamat      : " + alamat);
    }

    // Abstract methods yang wajib diimplementasikan oleh subclass konkret
    public abstract String getPeran();
    public abstract double hitungKontribusi();

    public void tampilkanPeran() {
        System.out.println("Peran            : " + getPeran());
        System.out.println("Nama             : " + nama);
        System.out.println("Alamat           : " + alamat);
        System.out.println("Nilai Kontribusi : Rp" + formatRupiah(hitungKontribusi()));
    }

    public static String formatRupiah(double nilai) {
        return String.format(Locale.forLanguageTag("in-ID"), "%,.0f", nilai);
    }
}
