package api;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import dao.IMedicinePurchases;
import dao.IPrescriptionsDAO;
import dao.impl.MedicinePurchasesImpl;
import dao.impl.PrescriptionsDAOImpl;
import dto.request.MedicinePurchasesRequest;
import dto.response.PrescriptionsResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import mapper.IPrescriptionsMapper;
import mapper.impl.PrescriptionsMapperImpl;
import models.MedicinePurchases;
import models.Prescriptions;

@WebServlet(name = "MedicinePurchasesApiServlet", urlPatterns = {"/api/medicine-purchases"})
public class MedicinePurchasesApiController extends HttpServlet {

    private final IMedicinePurchases medicinePurchases;
    private final IPrescriptionsDAO prescriptionsDAO;
    private final IPrescriptionsMapper prescriptionsMapper;

    public MedicinePurchasesApiController() {
        this.medicinePurchases = new MedicinePurchasesImpl();
        this.prescriptionsDAO = new PrescriptionsDAOImpl();
        this.prescriptionsMapper = new PrescriptionsMapperImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        final Gson gson = new Gson();
        try (BufferedReader reader = request.getReader()) {
            MedicinePurchasesRequest medicinePurchasesRequest = gson.fromJson(reader, MedicinePurchasesRequest.class);
            if (medicinePurchasesRequest.getListPrescriptionsId() == null || medicinePurchasesRequest.getListPrescriptionsId().isEmpty()) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write(gson.toJson("Prescription IDs cannot be empty"));
                return;
            }
            List<Prescriptions> listPre = this.prescriptionsDAO.getPrescriptionsByIdIn(medicinePurchasesRequest.getListPrescriptionsId());
            List<PrescriptionsResponse> listPreRes = this.prescriptionsMapper.toListPrescriptionsResponse(listPre);
            List<MedicinePurchases> list = new ArrayList<>();
            for (PrescriptionsResponse pre : listPreRes) {
                MedicinePurchases medicinePurchases = new MedicinePurchases.Builder()
                        .prescriptionId(pre.getId())
                        .quantityPurchased(pre.getQuantityPrescribed())
                        .medicineId(pre.getMedicine().getId())
                        .totalPrice(pre.getQuantityPrescribed() * pre.getMedicine().getPrice())
                        .build();
                list.add(medicinePurchases);
            }
            this.medicinePurchases.addAll(list);
            response.getWriter().write(gson.toJson("Purcharse add successful")); 
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (JsonSyntaxException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write(gson.toJson("Invalid JSON format"));
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write(gson.toJson("An error occurred while processing the request"));
        }
    }
}
