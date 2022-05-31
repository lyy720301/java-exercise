package cn.lz.spring.service.redis;

public class Human {

    private Company company;

    public Human(Company company) {
        this.company = company;
    }

    public Human() {
    }

    public Company getCompany() {
        return company;
    }
}
