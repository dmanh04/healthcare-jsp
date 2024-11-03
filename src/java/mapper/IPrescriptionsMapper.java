
package mapper;

import dto.response.PrescriptionsResponse;
import java.util.List;
import models.Prescriptions;


public interface IPrescriptionsMapper {
    
    List<PrescriptionsResponse> toListPrescriptionsResponse(List<Prescriptions> list);
    
}
