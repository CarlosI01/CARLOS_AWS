package ps.CarlosIL_AWS.ista.CarlsoIL.model.vn;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Asset {
    private byte[] content;
    private String contentType;

}
