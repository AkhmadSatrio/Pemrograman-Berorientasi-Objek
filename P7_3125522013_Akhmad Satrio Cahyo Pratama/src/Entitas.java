import java.util.Locale;

/**
 * Abstract Class Entitas
 * Mewakili konsep umum entitas dalam Sistem Manajemen Bank Sampah.
 * Tidak dapat diinstansiasi secara langsung menggunakan new.
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
            this.nama = nama;
        } else {
            System.out.println("Nama tidak valid");
        }
    }

    public void setAlamat(String alamat) {
        if (alamat != null && !alamat.isBlank()) {
            this.alamat = alamat;
        } else {
            System.out.println("Alamat tidak valid");
        }
    }

    public void tampilkanIdentitas() {
        System.out.println("Nama        : " + nama);
        System.out.println("Alamat      : " + alamat);
    }

    // Abstract method: wajib diimplementasikan oleh setiap subclass konkret
    public abstract String getPeran();

    // Abstract method: perhitungan kontribusi spesifik per jenis subclass
    public abstract double hitungKontribusi();

    // Template method yang memanfaatkan abstract method getPeran() dan hitungKontribusi()
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
