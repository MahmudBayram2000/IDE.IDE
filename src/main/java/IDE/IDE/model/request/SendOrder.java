package IDE.IDE.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SendOrder {

    public Integer  cari_id ;
    public Integer  xidmet_id ;
    public String   xidmet_adi ;
    public Integer sifaris_id ;
    public String  sifaris_adi ;
    public Integer lab_id ;
    public String  pasient ;
    public Integer pasient_id ;
    public Integer  pasient_cins ;
    public String  pasient_dogum_tarixi ;
    public Integer   IDE_xidmet_id ;
    public Integer  status_id;
    public String  barcode ;
    public Integer  hospital_id ;
    public Integer gonderen_id ;
    public String   gonderen_adi ;
    public String  gonderilen_tarix ;
}
