package IDE.IDE.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceiveOrderComponent {

    private String reg_id;
    private String gebul_id;
    private String gebul_user;
    private String gebul_tarix;
    private String result;
}
