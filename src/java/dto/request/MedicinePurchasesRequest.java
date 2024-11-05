
package dto.request;

import java.util.List;


public class MedicinePurchasesRequest {
    
    private List<Integer> listPrescriptionsId;

    public MedicinePurchasesRequest() {
    }

    public MedicinePurchasesRequest(List<Integer> listPrescriptionsId) {
        this.listPrescriptionsId = listPrescriptionsId;
    }

    public List<Integer> getListPrescriptionsId() {
        return listPrescriptionsId;
    }
    
    
    
}
