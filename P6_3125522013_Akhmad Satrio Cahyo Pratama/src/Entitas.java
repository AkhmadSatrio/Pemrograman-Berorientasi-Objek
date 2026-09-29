import java.util.Locale;

public class Entitas {
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

    public String getPeran() {
        return "Entitas";
    }

    public void tampilkanPeran() {
        System.out.println("Peran            : " + getPeran());
        System.out.println("Nama             : " + nama);
        System.out.println("Alamat           : " + alamat);
        System.out.println("Nilai Kontribusi : Rp" + formatRupiah(hitungKontribusi()));
    }

    public double hitungKontribusi() {
        return 0;
    }

    public static String formatRupiah(double nilai) {
        return String.format(Locale.forLanguageTag("in-ID"), "%,.0f", nilai);
    }
}
