/**
 * Interface DapatDilacak
 * Mendefinisikan kontrak perilaku pelacakan lokasi dan status operasional entitas.
 */
public interface DapatDilacak {
    /**
     * Mengambil informasi lokasi terkini dari objek.
     * @return String lokasi
     */
    String getLokasi();

    /**
     * Mengambil status operasional entitas di lokasi tersebut.
     * @return String status
     */
    String getStatusOperasional();
}
