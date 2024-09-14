package IDE.IDE.model.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReceiveOrderResponse {

    String xidmet_adi;
    String sifaris_adi;
    String pasient;
    Integer pasient_cins;
    LocalDate pasient_dogum_tarixi;
    Integer IDE_xidmet_id;
    String barcode;
    String gonderen_adi;
    LocalDateTime gonderilen_tarix;

}