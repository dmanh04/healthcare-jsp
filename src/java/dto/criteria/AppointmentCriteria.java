package dto.criteria;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AppointmentCriteria extends BaseCriteria {
    private Date startDate;
    private Date endDate;
    private List<Integer> listTimeSlotId;
    private String customerName;
    private Integer doctorId;
    private String status;
    private Integer serviceId;
    private String phone;
    private int page;
    private int limit;
    private Integer doctorIdCurrent;

    private AppointmentCriteria(Builder builder) {
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
        this.listTimeSlotId = builder.listTimeSlotId;
        this.customerName = builder.customerName;
        this.doctorId = builder.doctorId;
        this.status = builder.status;
        this.serviceId = builder.serviceId;
        this.phone = builder.phone;
        this.page = builder.page;
        this.limit = builder.limit;
        this.doctorIdCurrent = builder.doctorIdCurrent;
    }

    public static class Builder {

        private Date startDate;
        private Date endDate;
        private List<Integer> listTimeSlotId = new ArrayList<>();
        private String customerName;
        private Integer doctorId;
        private String status;
        private Integer serviceId;
        private String phone;
        private int page;
        private int limit;
        private Integer doctorIdCurrent;

        public Builder doctorIdCurrent(Integer doctorIdCurrent) {
            this.doctorIdCurrent = doctorIdCurrent;
            return this;
        }

        public Builder page(int page) {
            this.page = page;
            return this;
        }

        public Builder limit(int limit) {
            this.limit = limit;
            return this;
        }

        public Builder startDate(Date startDate) {
            this.startDate = startDate;
            return this;
        }

        public Builder endDate(Date endDate) {
            this.endDate = endDate;
            return this;
        }

        public Builder listTimeSlotId(List<Integer> listTimeSlotId) {
            this.listTimeSlotId = listTimeSlotId;
            return this;
        }

        public Builder customerName(String customerName) {
            this.customerName = customerName;
            return this;
        }

        public Builder doctorId(Integer doctorId) {
            this.doctorId = doctorId;
            return this;
        }

        public Builder status(String status) {
            this.status = status;
            return this;
        }

        public Builder serviceId(Integer serviceId) {
            this.serviceId = serviceId;
            return this;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public AppointmentCriteria build() {
            return new AppointmentCriteria(this);
        }
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public List<Integer> getListTimeSlotId() {
        return listTimeSlotId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public String getStatus() {
        return status;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public String getPhone() {
        return phone;
    }

    public int getPage() {
        return page;
    }

    public int getLimit() {
        return limit;
    }

    public Integer getDoctorIdCurrent() {
        return doctorIdCurrent;
    }

    @Override
    public String toString() {
        return "AppointmentCriteria{" + "startDate=" + startDate + ", endDate=" + endDate + ", listTimeSlotId=" + listTimeSlotId + ", customerName=" + customerName + ", doctorId=" + doctorId + ", status=" + status + ", serviceId=" + serviceId + ", phone=" + phone + ", page=" + page + ", limit=" + limit + ", doctorIdCurrent=" + doctorIdCurrent + '}';
    }    
}
