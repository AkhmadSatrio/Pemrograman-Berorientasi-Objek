/**
 * Class Pelanggan
 * Mengelola data pelanggan, klasifikasi membership (Reguler, Silver, Gold),
 * serta program loyalitas poin reward.
 * 
 * Mahasiswa: Akhmad Satrio Cahyo Pratama (NRP: 3125522013)
 */
public class Pelanggan {
    private String idPelanggan;
    private String nama;
    private String nomorTelepon;
    private String tipeMember; // REGULER, SILVER, GOLD
    private int poinLoyalti;

    public Pelanggan(String idPelanggan, String nama, String nomorTelepon, String tipeMember) {
        this.idPelanggan = idPelanggan;
        setNama(nama);
        setNomorTelepon(nomorTelepon);
        setTipeMember(tipeMember);
        this.poinLoyalti = 0;
    }

    public String getIdPelanggan() {
        return idPelanggan;
    }

    public void setIdPelanggan(String idPelanggan) {
        this.idPelanggan = idPelanggan;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        // Validasi: Nama pelanggan tidak boleh kosong
        if (nama != null && !nama.trim().isEmpty()) {
            this.nama = nama.trim();
        } else {
            System.out.println("[Validasi Error] Nama pelanggan tidak boleh kosong! Diset ke 'Tamu'.");
            this.nama = "Tamu";
        }
    }

    public String getNomorTelepon() {
        return nomorTelepon;
    }

    public void setNomorTelepon(String nomorTelepon) {
        // Validasi: Nomor telepon harus berisi digit angka dan minimal 10 karakter
        if (nomorTelepon != null && nomorTelepon.matches("\\d{10,14}")) {
            this.nomorTelepon = nomorTelepon;
        } else {
            System.out.println("[Validasi Error] Format nomor telepon tidak valid (harus 10-14 digit angka). Diberikan: " + nomorTelepon);
            this.nomorTelepon = "081200000000";
        }
    }

    public String getTipeMember() {
        return tipeMember;
    }

    public void setTipeMember(String tipeMember) {
        // Validasi: Tipe membership hanya REGULER, SILVER, atau GOLD
        if (tipeMember != null && (tipeMember.equalsIgnoreCase("SILVER") || tipeMember.equalsIgnoreCase("GOLD") || tipeMember.equalsIgnoreCase("REGULER"))) {
            this.tipeMember = tipeMember.toUpperCase();
        } else {
            System.out.println("[Validasi Error] Tipe member tidak dikenal: " + tipeMember + ". Diset ke 'REGULER'.");
            this.tipeMember = "REGULER";
        }
    }

    public int getPoinLoyalti() {
        return poinLoyalti;
    }

    // ================= Fitur Kreatif: Membership & Reward Loyalty =================

    /**
     * Mendapatkan persentase diskon berdasarkan level keanggotaan.
     */
    public double getPersenDiskonMember() {
        switch (tipeMember) {
            case "GOLD":
                return 10.0; // Diskon 10%
            case "SILVER":
                return 5.0;  // Diskon 5%
            default:
                return 0.0;  // Reguler 0%
        }
    }

    public void tambahPoin(int poin) {
        if (poin > 0) {
            this.poinLoyalti += poin;
            System.out.println("[Info Poin] Pelanggan " + nama + " mendapatkan +" + poin + " poin loyalitas. Total poin: " + this.poinLoyalti);
        }
    }

    public boolean tukarPoin(int jumlahPoin) {
        if (jumlahPoin > 0 && this.poinLoyalti >= jumlahPoin) {
            this.poinLoyalti -= jumlahPoin;
            return true;
        }
        return false;
    }

    public void tampilkanProfil() {
        System.out.printf("[PELANGGAN] ID: %s | Nama: %s | Telp: %s | Member: %s (Diskon %.0f%%) | Poin: %d%n",
                idPelanggan, nama, nomorTelepon, tipeMember, getPersenDiskonMember(), poinLoyalti);
    }
}
