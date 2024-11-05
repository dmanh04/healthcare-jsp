package dto.criteria;

import java.util.Date;

public class MedicalRecordCriteria extends BaseCriteria {

    private String customerName;
    private Date startDate;
    private Date endDate;

    private MedicalRecordCriteria(Builder builder) {
        this.customerName = builder.customerName;
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
        this.page = builder.page; 
        this.limit = builder.limit; 
    }

    public String getCustomerName() {
        return customerName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public int getPage() {
        return page;
    }

    public int getLimit() {
        return limit;
    }
    
    

    public static class Builder {

        private String customerName;
        private Date startDate;
        private Date endDate;
        private int page = 1; 
        private int limit = 10; 

        public Builder() {
        }

        public Builder customerName(String customerName) {
            this.customerName = customerName;
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

        public Builder page(int page) {
            this.page = page;
            return this;
        }

        public Builder limit(int limit) {
            this.limit = limit;
            return this;
        }

        public MedicalRecordCriteria build() {
            return new MedicalRecordCriteria(this);
        }
    }
}
