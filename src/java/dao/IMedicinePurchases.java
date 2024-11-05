
package dao;

import java.util.List;
import models.MedicinePurchases;


public interface IMedicinePurchases {
    
    void addAll(List<MedicinePurchases> list);
    
    List<String> getPurchasedMedicines(int recordId);
}
