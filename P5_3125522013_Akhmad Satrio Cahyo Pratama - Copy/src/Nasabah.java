public class Nasabah extends Entitas {
    private String id;
    private Rekening rekening;

    public Nasabah(String id, String nama, String alamat, double saldoAwal) {
        super(nama, alamat);
        this.id = id;
        this.rekening = new Rekening("R-" + id, saldoAwal);
    }

    public String getId() {
        return id;
    }

    public Rekening getRekening() {
        return rekening;
    }

    public double getSaldo() {
        return rekening.getSaldo();
    }

    public void setSaldo(double saldo) {
        rekening.setSaldo(saldo);
    }

    public void tambahSaldo(double nominal) {
        rekening.tambahSaldo(nominal);
    }

    public void tampilkanData() {
        System.out.println("ID          : " + id);
        System.out.println("Nama        : " + getNama());
        System.out.println("Alamat      : " + getAlamat());
        System.out.println("No Rekening : " + rekening.getNomorRekening());
        System.out.println("Saldo       : Rp" + rekening.getSaldo());
    }
}