package IDE.IDE.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@AllArgsConstructor
public class OrderResponse {
    private Integer cari_id;
    private Integer reg_id;
    private String result;
}
