package dao.impl;

import common.utils.DBContext;
import common.utils.StringUtils;
import dao.ISerivceDAO;
import dto.criteria.ServiceCriteria;
import dto.response.PageableResponse;
import java.security.Provider.Service;
import java.util.ArrayList;
import java.util.List;
import models.Services;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ServiceDAOImpl extends DBContext implements ISerivceDAO {

    @Override
    public PageableResponse<Services> getAllSerivceByFilter(ServiceCriteria serviceCriteria) {
        List<Services> serviceList = new ArrayList<>();

        StringBuilder query = new StringBuilder("SELECT * FROM services")
                .append(buildQueryFilter(serviceCriteria))
                .append(" ORDER BY service_id")
                .append(" OFFSET ").append((serviceCriteria.getPage() - 1) * serviceCriteria.getLimit())
                .append(" ROWS FETCH NEXT ").append(serviceCriteria.getLimit()).append(" ROWS ONLY");

        try (PreparedStatement ps = connection.prepareStatement(query.toString())) {
            if (StringUtils.checkString(serviceCriteria.getSearchName())) {
                String searchName = serviceCriteria.getSearchName().trim();
                ps.setString(1, "%" + searchName + "%");
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Services service = new Services.Builder()
                            .setId(rs.getInt("service_id"))
                            .setServiceName(rs.getString("service_name"))
                            .setDescription(rs.getString("description"))
                            .setPrice(rs.getDouble("price"))
                            .setDuration(rs.getInt("duration"))
                            .setImage(rs.getString("image"))
                            .setIcon(rs.getString("icon"))
                            .setCreatedAt(rs.getDate("created_at"))
                            .setUpdatedAt(rs.getDate("updated_at"))
                            .build();
                    serviceList.add(service);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        int totalServices = getTotalServicesCount(serviceCriteria);
        int totalPage = (int) Math.ceil((double) totalServices / serviceCriteria.getLimit());

        return new PageableResponse.Builder<Services>()
                .totalPage(totalPage)
                .page(serviceCriteria.getPage())
                .size(serviceList.size())
                .data(serviceList)
                .build();
    }

    @Override
    public int getTotalServicesCount(ServiceCriteria serviceCriteria) {
        StringBuilder countQuery = new StringBuilder("SELECT COUNT(*) FROM services")
                .append(buildQueryFilter(serviceCriteria));

        try (PreparedStatement ps = connection.prepareStatement(countQuery.toString())) {
            if (StringUtils.checkString(serviceCriteria.getSearchName())) {
                ps.setString(1, "%" + serviceCriteria.getSearchName() + "%");
            }
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    private String buildQueryFilter(ServiceCriteria serviceCriteria) {
        StringBuilder query = new StringBuilder(" WHERE 1=1");
        if (StringUtils.checkString(serviceCriteria.getSearchName())) {
            query.append(" AND service_name LIKE ?");
        }
        return query.toString();
    }

    @Override
    public Services findServiceById(int id) {
        Services service = null;
        String query = "SELECT * FROM services WHERE service_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                service = new Services.Builder()
                        .setId(rs.getInt("service_id"))
                        .setServiceName(rs.getString("service_name"))
                        .setDescription(rs.getString("description"))
                        .setPrice(rs.getDouble("price"))
                        .setDuration(rs.getInt("duration"))
                        .setImage(rs.getString("image"))
                        .setIcon(rs.getString("icon"))
                        .setCreatedAt(rs.getDate("created_at"))
                        .setUpdatedAt(rs.getDate("updated_at"))
                        .build();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return service;
    }

    @Override
    public void addService(Services service) {
        String query = "INSERT INTO services (service_name, description, price, duration, image, icon) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, service.getServiceName());
            ps.setString(2, service.getDescription());
            ps.setDouble(3, service.getPrice());
            ps.setInt(4, service.getDuration());
            ps.setString(5, service.getImage());
            ps.setString(6, service.getIcon());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean existsByServiceName(String name) {
        String query = "SELECT COUNT(*) FROM services WHERE service_name = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void updateService(Services services) {
        String query = "UPDATE services SET service_name = ?, description = ?, price = ?, duration = ?, image = ?, icon = ? WHERE service_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, services.getServiceName());
            ps.setString(2, services.getDescription());
            ps.setDouble(3, services.getPrice());
            ps.setInt(4, services.getDuration());
            ps.setString(5, services.getImage());
            ps.setString(6, services.getIcon());
            ps.setInt(7, services.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteSerivceById(int id) {
        String query = "DELETE FROM services WHERE service_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean existsUpdateByServiceName(String name, int currentServiceId) {
        String query = "SELECT COUNT(*) FROM services WHERE service_name = ? AND service_id != ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, name);
            ps.setInt(2, currentServiceId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Map<Integer, Services> getIdAndService() {
        Map<Integer, Services> serviceMap = new HashMap<>();
        String query = "SELECT service_id, service_name, description, price, duration, image, icon, created_at, updated_at FROM services";

        try (PreparedStatement ps = connection.prepareStatement(query); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Services service = new Services.Builder()
                        .setId(rs.getInt("service_id"))
                        .setServiceName(rs.getString("service_name"))
                        .setDescription(rs.getString("description"))
                        .setPrice(rs.getDouble("price"))
                        .setDuration(rs.getInt("duration"))
                        .setImage(rs.getString("image"))
                        .setIcon(rs.getString("icon"))
                        .setCreatedAt(rs.getDate("created_at"))
                        .setUpdatedAt(rs.getDate("updated_at"))
                        .build();
                serviceMap.put(service.getId(), service);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return serviceMap;
    }

    @Override
    public List<Services> getAllSerivce() {
        List<Services> serviceList = new ArrayList<>();
        String query = "SELECT * FROM services ORDER BY service_id"; 
        try (PreparedStatement ps = connection.prepareStatement(query); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Services service = new Services.Builder()
                        .setId(rs.getInt("service_id"))
                        .setServiceName(rs.getString("service_name"))
                        .setDescription(rs.getString("description"))
                        .setPrice(rs.getDouble("price"))
                        .setDuration(rs.getInt("duration"))
                        .setImage(rs.getString("image"))
                        .setIcon(rs.getString("icon"))
                        .setCreatedAt(rs.getDate("created_at"))
                        .setUpdatedAt(rs.getDate("updated_at"))
                        .build();
                serviceList.add(service);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return serviceList;
    }

}
