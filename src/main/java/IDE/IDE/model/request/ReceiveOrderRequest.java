package IDE.IDE.model.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceiveOrderRequest {

    String barcode;
    Integer hospital_id;

}


